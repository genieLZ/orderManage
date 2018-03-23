package com.orderManage.controller.portal;

import com.orderManage.common.Const;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;
import com.orderManage.service.IEvaluateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Create by LZ
 */
@Controller
@RequestMapping("/evaluate/")
public class EvaluateController {

    private static final Logger logger = LoggerFactory.getLogger(EvaluateController.class);

    @Autowired
    private IEvaluateService iEvaluateService;

    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse addEvaluate(HttpSession session, Long orderNo, String evaluateText){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iEvaluateService.setEvaluate(user.getId(),orderNo,evaluateText);
    }

    @RequestMapping("get_skill_evaluate_list.do")
    @ResponseBody
    public ServerResponse skillEvaluateList(HttpSession session, Integer skillId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iEvaluateService.getSkillEvaluateList(skillId,user.getId());
    }

    @RequestMapping("get_worker_evaluate_list.do")
    @ResponseBody
    public ServerResponse WorkerEvaluateList(HttpSession session, Integer workerId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByError(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iEvaluateService.getWorkerEvaluateList(workerId,user.getId());
    }
}
