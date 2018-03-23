package com.orderManage.service;

import com.orderManage.common.ServerResponse;
import com.orderManage.pojo.User;

/**
 * Create by LZ
 */
//用户接口
public interface IUserService {

    //登录
    ServerResponse<User> login(String username, String password);

    //注册
    ServerResponse<String> register(User user);

    //校验用户名
    ServerResponse<String> checkValid(String str,String type);

    //获取当前用户信息
    ServerResponse<User> getInformation(Integer userId);

    //校验是否管理员
    ServerResponse checkAdminRole(User user);

}
