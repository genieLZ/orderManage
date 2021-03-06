package com.orderManage.controller.backend;

import com.github.pagehelper.PageInfo;
import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;
import com.orderManage.service.IOrderService;
import com.orderManage.service.IUserService;
import com.orderManage.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Create by LZ
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;


    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpSession session, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iOrderService.manageList(pageNum,pageSize);
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderDetail(HttpSession session, Long orderNo){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iOrderService.manageDetail(orderNo);
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpSession session, Long orderNo,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iOrderService.manageSearch(orderNo,pageNum,pageSize);
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

    @RequestMapping("set_working.do")
    @ResponseBody
    public ServerResponse<String> orderSetWorking(HttpSession session, Long orderNo){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iOrderService.manageSetWorking(orderNo);
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }
}
