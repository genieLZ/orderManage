package com.orderManage.service.impl;

import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.dao.SkillMapper;
import com.orderManage.dao.WorkerMapper;
import com.orderManage.pojo.Skill;
import com.orderManage.pojo.Worker;
import com.orderManage.service.ISkillService;
import com.orderManage.vo.SkillVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Create by LZ
 */
@Service("iSkillService")
public class SkillServiceImpl implements ISkillService{

    private Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private SkillMapper skillMapper;

    public ServerResponse addOrUpdateSkill(Skill skill){
        if(skill!=null){
            if(skill.getId() != null){
                int rowCount = skillMapper.updateByPrimaryKey(skill);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("技能更新成功！");
                }
                return ServerResponse.createByError("技能更新失败！");
            }else{
                int rowCount =  skillMapper.insert(skill);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("新增技能成功！");
                }
                return ServerResponse.createByError("新增技能失败！");
            }
        }
        return ServerResponse.createByError("参数不正确！");
    }

    public ServerResponse getSkill(Integer skillId){
        if(skillId==null){
            return ServerResponse.createByError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Skill skill = skillMapper.selectByPrimaryKey(skillId);
        Worker worker = workerMapper.selectByPrimaryKey(skill.getWorkerId());
        String workerName = worker.getUsername();
        SkillVo skillVo = assembleSkillVo(skill,workerName);
        return ServerResponse.createBySuccess(skillVo);
    }

    public SkillVo assembleSkillVo(Skill skill, String workerName){
        SkillVo skillVo = new SkillVo();
        skillVo.setSkillName(skill.getSkillname());
        skillVo.setWorkerName(workerName);
        skillVo.setPrice(skill.getPrice());
        return skillVo;
    }

    public ServerResponse<List<Skill>> getAllSkill(){

        List<Skill> skillList = skillMapper.selectAllSkill();
        if(CollectionUtils.isEmpty(skillList)){
            logger.info("没有找到技能列表");
        }
        return ServerResponse.createBySuccess(skillList);
    }
}
