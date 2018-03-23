package com.orderManage.service;

import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.Worker;

import java.util.List;

/**
 * Create by LZ
 */
//师傅接口
public interface IWorkerService {

    //新增师傅
    ServerResponse addOrUpdateWorker(Worker worker);

    //修改师傅状态
    ServerResponse<String> setWorkerStatus(Integer workerId,Integer status);

    //获取师傅列表
    ServerResponse<List<Worker>> getAllWorker();

    //查看师傅
    ServerResponse getWorker(Integer workerId);

}
