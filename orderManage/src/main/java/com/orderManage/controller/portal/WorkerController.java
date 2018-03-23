package com.orderManage.controller.portal;

import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;
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
@RequestMapping("/worker/")
public class WorkerController {

    @Autowired
    private IWorkerService iWorkerService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请先登陆后再进行操作！");
        }
        return iWorkerService.getAllWorker();
    }
}
