package com.orderManage.service.impl;

import com.google.common.collect.Lists;
import com.orderManage.common.ResponseCode;
import com.orderManage.common.ServerResponse;
import com.orderManage.dao.SkillMapper;
import com.orderManage.dao.WorkerMapper;
import com.orderManage.pojo.Skill;
import com.orderManage.pojo.Worker;
import com.orderManage.service.IWorkerService;
import com.orderManage.vo.SkillListVo;
import com.orderManage.vo.WorkerVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Create by LZ
 */
@Service("iWorkerService")
public class WorkerServiceImpl implements IWorkerService{

    private Logger logger = LoggerFactory.getLogger(WorkerServiceImpl.class);

    @Autowired
    private WorkerMapper workerMapper;

    @Autowired
    private SkillMapper skillMapper;

    public ServerResponse addOrUpdateWorker(Worker worker){
        if(worker!=null){
            if(worker.getId() != null){
                int rowCount = workerMapper.updateByPrimaryKey(worker);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("师傅更新成功！");
                }
                return ServerResponse.createByError("师傅更新失败！");
            }else{
                worker.setStatus(1);
                int rowCount =  workerMapper.insert(worker);
                if(rowCount > 0){
                    return ServerResponse.createBySuccess("新增师傅成功！");
                }
                return ServerResponse.createByError("新增师傅失败！");
            }
        }
        return ServerResponse.createByError("参数不正确！");
    }

    public ServerResponse<String> setWorkerStatus(Integer workerId,Integer status){
        if(workerId == null || status == null){
            return ServerResponse.createByError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Worker worker = new Worker();
        worker.setId(workerId);
        worker.setStatus(status);
        int rowCount = workerMapper.updateByPrimaryKeySelective(worker);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("修改师傅状态成功！");
        }
        return ServerResponse.createByError("修改师傅状态失败！");
    }

    public ServerResponse getWorker(Integer workerId){
        if(workerId==null){
            return ServerResponse.createByError(ResponseCode.ILLEGAL_ARGUMENT.getCode(),ResponseCode.ILLEGAL_ARGUMENT.getDesc());
        }
        Worker worker = workerMapper.selectByPrimaryKey(workerId);
        List<Skill> skillList = skillMapper.selectByWorkerId(workerId);
        List<SkillListVo> skillListVoList = Lists.newArrayList();
        for(Skill skill:skillList){
            SkillListVo skillListVo = assembleSkillListVo(skill);
            skillListVoList.add(skillListVo);
        }
        WorkerVo workerVo = assembleWorkerVo(worker,skillListVoList);
        return ServerResponse.createBySuccess(workerVo);

    }

    public SkillListVo assembleSkillListVo(Skill skill){
        SkillListVo skillListVo = new SkillListVo();
        skillListVo.setSkillName(skill.getSkillname());
        skillListVo.setPrice(skill.getPrice());
        return skillListVo;
    }

    public WorkerVo assembleWorkerVo(Worker worker, List<SkillListVo> skillListVoList){
        WorkerVo workerVo = new WorkerVo();
        workerVo.setUsername(worker.getUsername());
        workerVo.setAge(worker.getAge());
        workerVo.setPhone(worker.getPhone());
        workerVo.setStatus(worker.getStatus());
        workerVo.setSkillList(skillListVoList);
        return workerVo;
    }

    public ServerResponse<List<Worker>> getAllWorker(){

        List<Worker> workerList = workerMapper.selectAllWorker();
        if(CollectionUtils.isEmpty(workerList)){
            logger.info("没有找到师傅列表");
        }
        return ServerResponse.createBySuccess(workerList);
    }

}
