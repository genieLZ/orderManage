package com.orderManage.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.orderManage.common.Const;
import com.orderManage.common.ServerResponse;
import com.orderManage.dao.*;
import com.orderManage.pojo.*;
import com.orderManage.service.IOrderService;
import com.orderManage.vo.OrderListVo;
import com.orderManage.vo.OrderVo;
import com.orderManage.vo.ShippingVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;

/**
 * Create by LZ
 */
@Service("iOrderService")
public class OrderServiceImpl implements IOrderService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    public ServerResponse createOrder(Integer userId, Integer shippingId,Integer skillId){
        if(userId!=null && shippingId!=null && skillId!=null){
            Skill skill = skillMapper.selectByPrimaryKey(skillId);
            Order order = this.assembleOrder(userId,shippingId,skill);
            if(order == null){
                return ServerResponse.createByError("生成订单错误！");
            }
            OrderVo orderVo = assembleOrderVo(order);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByError("参数错误！");
    }

    private Order assembleOrder(Integer userId,Integer shippingId,Skill skill){
        Order order = new Order();
        long orderNo = this.generateOrderNo();
        order.setOrderno(orderNo);
        order.setUserId(userId);
        order.setSkillId(skill.getId());
        order.setStatus(Const.OrderStatusEnum.NO_PAY.getCode());
        order.setPrice(skill.getPrice());
        order.setShippingId(shippingId);
        //发货时间等等
        //付款时间等等
        int rowCount = orderMapper.insert(order);
        if(rowCount > 0){
            return order;
        }
        return null;
    }

    private long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime+new Random().nextInt(100);
    }

    private OrderVo assembleOrderVo(Order order){
        OrderVo orderVo = new OrderVo();
        Skill skill = skillMapper.selectByPrimaryKey(order.getSkillId());
        Shipping shipping = shippingMapper.selectByPrimaryKey(order.getShippingId());
        ShippingVo shippingVo = assembleShippingVo(shipping);
        order.setOrderno(order.getOrderno());
        orderVo.setSkillname(skill.getSkillname());
        orderVo.setShippingVo(shippingVo);
        orderVo.setStatus(order.getStatus());
        orderVo.setPrice(order.getPrice());
        orderVo.setCreateTime(order.getCreateTime());
        orderVo.setEvaluateStatus(null);
        orderVo.setEvaluate(null);
        return orderVo;
    }

    private ShippingVo assembleShippingVo(Shipping shipping){
        ShippingVo shippingVo = new ShippingVo();
        User user = userMapper.selectByPrimaryKey(shipping.getUserId());
        shippingVo.setName(user.getUsername());
        shippingVo.setPhone(shipping.getPhone());
        shippingVo.setAddress(shipping.getAddress());
        return shippingVo;
    }

    public ServerResponse<String> cancel(Integer userId,Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId,orderNo);
        if (order == null){
            return ServerResponse.createByError("该用户此订单不存在！");
        }
        if(order.getStatus() != Const.OrderStatusEnum.NO_PAY.getCode()){
            return ServerResponse.createByError("已付款，无法取消订单！");
        }
        Order updateOrder = new Order();
        updateOrder.setId(order.getId());
        updateOrder.setStatus(Const.OrderStatusEnum.CANCELED.getCode());

        int row = orderMapper.updateByPrimaryKeySelective(updateOrder);
        if(row > 0){
            return ServerResponse.createBySuccess("订单取消成功！");
        }
        return ServerResponse.createByError("订单取消失败！");
    }

    public ServerResponse<OrderVo> getOrderDetail(Integer userId,Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId,orderNo);
        if(order != null){
            OrderVo orderVo = assembleOrderVo(order);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByError("没有找到该订单！");
    }

    public ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectByUserId(userId);
        List<OrderListVo> orderListVoList = assembleOrderListVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private List<OrderListVo> assembleOrderListVoList(List<Order> orderList){
        List<OrderListVo> orderListVoList = Lists.newArrayList();
        for (Order order:orderList){
            OrderListVo orderListVo = assembleOrderListVo(order);
            orderListVoList.add(orderListVo);
        }
        return orderListVoList;
    }

    private OrderListVo assembleOrderListVo(Order order){
        OrderListVo orderListVo = new OrderListVo();
        orderListVo.setOrderno(order.getOrderno());
        Skill skill = skillMapper.selectByPrimaryKey(order.getSkillId());
        orderListVo.setSkillname(skill.getSkillname());
        orderListVo.setPrice(order.getPrice());
        orderListVo.setStatus(order.getStatus());
        if(order.getStatus() == Const.OrderStatusEnum.ORDER_SUCCESS.getCode()){
            Evaluate evaluate = evaluateMapper.selectByOrderNo(order.getOrderno());
            orderListVo.setEvaluateStatus(evaluate.getStatus());
            return orderListVo;
        }
        orderListVo.setEvaluateStatus(null);
        return orderListVo;
    }

    public ServerResponse queryOrderPayStatus(Integer userId,Long orderNo){
        Order order = orderMapper.selectByUserIdAndOrderNo(userId,orderNo);
        if(order == null){
            return ServerResponse.createByError("用户没有该订单！");
        }
        if(order.getStatus() >= Const.OrderStatusEnum.PAID.getCode()){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }

    //backend
    public ServerResponse<PageInfo> manageList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Order> orderList = orderMapper.selectAllOrder();
        List<OrderListVo> orderListVoList = assembleOrderListVoList(orderList);
        PageInfo pageResult = new PageInfo(orderList);
        pageResult.setList(orderListVoList);
        return ServerResponse.createBySuccess(pageResult);
    }

    public ServerResponse<OrderVo> manageDetail(Long orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            OrderVo orderVo = assembleOrderVo(order);
            return ServerResponse.createBySuccess(orderVo);
        }
        return ServerResponse.createByError("订单不存在！");
    }

    public ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        Order order = orderMapper.selectByOrderNo(orderNo);
        if(order != null){
            OrderVo orderVo = assembleOrderVo(order);
            PageInfo pageResult = new PageInfo(Lists.newArrayList(order));
            pageResult.setList(Lists.newArrayList(orderVo));
            return ServerResponse.createBySuccess(pageResult);
        }
        return ServerResponse.createByError("订单不存在！");
    }

    public ServerResponse<String> manageSetWorking(Long orderNo){
        Order order = orderMapper.selectByOrderNo(orderNo);
        if (order != null){
            if(order.getStatus() == Const.OrderStatusEnum.PAID.getCode()){
                order.setStatus(Const.OrderStatusEnum.SHIPPED.getCode());
                orderMapper.updateByPrimaryKeySelective(order);
                return ServerResponse.createBySuccess("出工成功！");
            }
        }
        return ServerResponse.createByError("订单不存在！");
    }



}
