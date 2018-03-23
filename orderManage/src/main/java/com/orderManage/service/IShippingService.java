package com.orderManage.service;

import com.github.pagehelper.PageInfo;
import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.Shipping;

/**
 * Create by LZ
 */
//地址模块接口
public interface IShippingService {

    //前台
    //新建地址
    ServerResponse add(Integer userId, Shipping shipping);

    //删除地址
    ServerResponse<String> del(Integer userId, Integer shippingId);

    //更新地址
    ServerResponse update(Integer userId, Shipping shipping);

    //查找地址
    ServerResponse<Shipping> select(Integer userId, Integer shippingId);

    //浏览地址列表
    ServerResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
