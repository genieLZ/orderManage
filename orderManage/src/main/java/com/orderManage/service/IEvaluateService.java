package com.orderManage.service;

import com.orderManage.common.ServerResponse;
import com.orderManage.vo.WorkerEvaluateVo;

/**
 * Create by LZ
 */
//评价接口
public interface IEvaluateService {

    //前台
    //用户评价
    ServerResponse setEvaluate(Integer userId, Long orderNo, String evaluateText);

    //获取技能评价
    ServerResponse getSkillEvaluateList(Integer skillId,Integer userId);

    //获取师傅评价
    ServerResponse<WorkerEvaluateVo> getWorkerEvaluateList(Integer workerId, Integer userId);


}
