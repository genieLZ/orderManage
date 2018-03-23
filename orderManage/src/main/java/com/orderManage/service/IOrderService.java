package com.orderManage.service;

import com.github.pagehelper.PageInfo;
import com.orderManage.common.ServerResponse;
import com.orderManage.vo.OrderVo;

/**
 * Create by LZ
 */
//订单接口
public interface IOrderService {

    //前台
    //创建订单
    ServerResponse createOrder(Integer userId, Integer shippingId, Integer skillId);

    //取消订单
    ServerResponse<String> cancel(Integer userId,Long orderNo);

    //获取用户订单详细信息
    ServerResponse<OrderVo> getOrderDetail(Integer userId,Long orderNo);

    //获取用户订单列表
    ServerResponse<PageInfo> getOrderList(Integer userId, int pageNum, int pageSize);

    //查询订单支付状态
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);

    //后台
    //管理员获取全部订单列表
    ServerResponse<PageInfo> manageList(int pageNum,int pageSize);

    //管理员获取订单详细信息
    ServerResponse<OrderVo> manageDetail(Long orderNo);

    //查找订单
    ServerResponse<PageInfo> manageSearch(Long orderNo,int pageNum,int pageSize);

    //设置订单状态
    ServerResponse<String> manageSetWorking(Long orderNo);

}
