package com.orderManage.controller.backend;

import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;
import com.orderManage.pojo.Worker;
import com.orderManage.service.IUserService;
import com.orderManage.service.IWorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Create by LZ
 */
@Controller
@RequestMapping("/manage/worker")
public class WorkerManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IWorkerService iWorkerService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Worker worker){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        }
        //校检是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类逻辑
            return iWorkerService.addOrUpdateWorker(worker);
        }else{
            return ServerResponse.createByError("无权限操作，需要管理员权限！");
        }
    }

    @RequestMapping("set_worker_status.do")
    @ResponseBody
    public ServerResponse setWorkerStatus(HttpSession session, Integer workerId, Integer status){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        }
        //校检是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            return iWorkerService.setWorkerStatus(workerId,status);
        }else{
            return ServerResponse.createByError("无权限操作，需要管理员权限！");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(HttpSession session,Integer workerId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iWorkerService.getWorker(workerId);
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iWorkerService.getAllWorker();
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

}
