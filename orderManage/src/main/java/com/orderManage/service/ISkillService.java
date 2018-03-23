package com.orderManage.service;

import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.Skill;

import java.util.List;

/**
 * Create by LZ
 */
//技能接口
public interface ISkillService {

    //新增技能
    ServerResponse addOrUpdateSkill(Skill skill);

    //获取技能信息
    ServerResponse getSkill(Integer skillId);

    //获取技能列表
    ServerResponse<List<Skill>> getAllSkill();

}
