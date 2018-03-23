package com.orderManage.controller.backend;

import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.Skill;
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
@RequestMapping("/manage/skill")
public class SkillManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ISkillService iSkillService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Skill skill){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录！");
        }
        //校检是否是管理员
        if(iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //增加处理分类逻辑
            return iSkillService.addOrUpdateSkill(skill);
        }else{
            return ServerResponse.createByError("无权限操作，需要管理员权限！");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(HttpSession session,Integer skillId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员！");
        }
        if(iUserService.checkAdminRole(user).isSuccess()){
            //业务逻辑
            return iSkillService.getSkill(skillId);
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
            return iSkillService.getAllSkill();
        }else{
            return ServerResponse.createByError("没有权限操作！");
        }
    }
}
