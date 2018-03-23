package com.orderManage.service.impl;

import com.google.common.collect.Lists;
import com.orderManage.common.Const;
import com.orderManage.common.ServerResponse;
import com.orderManage.dao.*;
import com.orderManage.pojo.*;
import com.orderManage.service.IEvaluateService;
import com.orderManage.vo.EvaluateListVo;
import com.orderManage.vo.EvaluateVo;
import com.orderManage.vo.SkillEvaluateVo;
import com.orderManage.vo.WorkerEvaluateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Create by LZ
 */
@Service("iEvaluateService")
public class EvaluateServiceImpl implements IEvaluateService{

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private EvaluateMapper evaluateMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SkillMapper skillMapper;

    @Autowired
    private WorkerMapper workerMapper;

    public ServerResponse setEvaluate(Integer userId,Long orderNo, String evaluateText){
        if(userId!=null && orderNo!=null && evaluateText!=null){
            Order order = orderMapper.selectByUserIdAndOrderNo(userId,orderNo);
            if(order.getStatus() <= Const.OrderStatusEnum.ORDER_SUCCESS.getCode()){
                return ServerResponse.createByError("订单未完成！");
            }
            Evaluate evaluate = evaluateMapper.selectByOrderNo(orderNo);
            if(evaluate == null){
                evaluate.setOrderno(orderNo);
                evaluate.setEvaluate(evaluateText);
                evaluate.setStatus(Const.EvaluateStatusEnum.EVALUATED.getCode());
                int rowCount = evaluateMapper.insert(evaluate);
                if(rowCount>0){
                    return ServerResponse.createBySuccess("评价成功！");
                }
                return ServerResponse.createByError("评价失败！");
            }else if(evaluate.getStatus() == Const.EvaluateStatusEnum.NO_EVALUATE.getCode()){
                evaluate.setOrderno(orderNo);
                evaluate.setEvaluate(evaluateText);
                evaluate.setStatus(Const.EvaluateStatusEnum.EVALUATED.getCode());
                int rowCount = evaluateMapper.insertSelective(evaluate);
                if(rowCount>0){
                    return ServerResponse.createBySuccess("评价成功！");
                }
                return ServerResponse.createByError("评价失败！");
            }else if(evaluate.getStatus() <= Const.EvaluateStatusEnum.EVALUATED.getCode()){
                return ServerResponse.createByError("该订单已评价！");
            }
        }
        return ServerResponse.createByError("参数错误！");
    }


    public ServerResponse<SkillEvaluateVo> getSkillEvaluateList(Integer skillId, Integer userId){
        if(skillId != null && userId !=null){
            Skill skill = skillMapper.selectByPrimaryKey(skillId);
            Worker worker = workerMapper.selectByPrimaryKey(skill.getWorkerId());
            SkillEvaluateVo skillEvaluateVo = new SkillEvaluateVo();
            skillEvaluateVo.setSkillName(skill.getSkillname());
            skillEvaluateVo.setWorkerName(worker.getUsername());
            skillEvaluateVo.setPhone(worker.getPhone());
            skillEvaluateVo.setPrice(skill.getPrice());
            List<Order> orderList = orderMapper.selectBySkillId(skillId);
            List<EvaluateListVo> evaluateListVoList = Lists.newArrayList();
            for(Order order:orderList){
                Evaluate evaluate = evaluateMapper.selectByOrderNo(order.getOrderno());
                EvaluateListVo evaluateListVo = this.assembleEvaluateListVo(evaluate,userId);
                evaluateListVoList.add(evaluateListVo);
            }
            skillEvaluateVo.setEvaluateListVoList(evaluateListVoList);
            return ServerResponse.createBySuccess(skillEvaluateVo);
        }
        return ServerResponse.createByError("参数错误！");
    }

    private EvaluateListVo assembleEvaluateListVo(Evaluate evaluate,Integer userId){
        EvaluateListVo evaluateListVo = new EvaluateListVo();
        User user = userMapper.selectByPrimaryKey(userId);
        evaluateListVo.setUsername(user.getUsername());
        evaluateListVo.setEvaluate(evaluate.getEvaluate());
        evaluateListVo.setCreateTime(evaluate.getCreateTime());
        return evaluateListVo;
    }


    public ServerResponse<WorkerEvaluateVo> getWorkerEvaluateList(Integer workerId, Integer userId){
        if(workerId != null){
            Worker worker = workerMapper.selectByPrimaryKey(workerId);
            WorkerEvaluateVo workerEvaluateVo = new WorkerEvaluateVo();
            workerEvaluateVo.setName(worker.getUsername());
            workerEvaluateVo.setPhone(worker.getPhone());
            workerEvaluateVo.setStatus(worker.getStatus());
            List<EvaluateVo> evaluateVoList = Lists.newArrayList();
            List<Skill> skillList = skillMapper.selectByWorkerId(workerId);
            for(Skill skill:skillList){
                List<Order> orderList = orderMapper.selectBySkillId(skill.getId());
                for(Order order:orderList){
                    Evaluate evaluate = evaluateMapper.selectByOrderNo(order.getOrderno());
                    EvaluateVo evaluateVo = this.assembleEvaluateVo(evaluate,userId,skill.getSkillname());
                    evaluateVoList.add(evaluateVo);
                }
            }
            workerEvaluateVo.setEvaluateVoList(evaluateVoList);
            return ServerResponse.createBySuccess(workerEvaluateVo);
        }
        return ServerResponse.createByError("参数错误！");
    }

    private EvaluateVo assembleEvaluateVo(Evaluate evaluate, Integer userId,String skillname){
        EvaluateVo evaluateVo = new EvaluateVo();
        User user = userMapper.selectByPrimaryKey(userId);
        evaluateVo.setSkillname(skillname);
        evaluateVo.setUsername(user.getUsername());
        evaluateVo.setEvaluate(evaluate.getEvaluate());
        evaluateVo.setCreateTime(evaluate.getCreateTime());
        return evaluateVo;
    }


}
