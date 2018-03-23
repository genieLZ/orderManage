package com.orderManage.controller.portal;

import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;
import com.orderManage.service.ISkillService;
import com.orderManage.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Create by LZ
 */
@Controller
@RequestMapping("/skill/")
public class SkillController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISkillService iSkillService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iSkillService.getAllSkill();
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }

}
