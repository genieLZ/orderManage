package com.orderManage.service;

import com.github.pagehelper.PageInfo;
import com.mmall.common.ServiceResponse;
import com.mmall.pojo.Shipping;

/**
 * Create by LZ
 */
public interface IShippingService {

    ServiceResponse add(Integer userId, Shipping shipping);

    ServiceResponse<String> del(Integer userId, Integer shippingId);

    ServiceResponse update(Integer userId, Shipping shipping);

    ServiceResponse<Shipping> select(Integer userId, Integer shippingId);

    ServiceResponse<PageInfo> list(Integer userId, int pageNum, int pageSize);
}
