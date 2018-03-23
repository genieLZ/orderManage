package com.orderManage.dao;

import com.orderManage.pojo.Skill;

import java.util.List;

public interface SkillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Skill record);

    int insertSelective(Skill record);

    Skill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Skill record);

    int updateByPrimaryKey(Skill record);

    List<Skill> selectByWorkerId(Integer workerId);

    List<Skill> selectAllSkill();
}