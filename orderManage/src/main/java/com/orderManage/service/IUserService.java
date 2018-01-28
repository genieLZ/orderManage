package com.orderManage.service;

import com.mmall.common.ServiceResponse;
import com.mmall.pojo.User;


public interface IUserService {
    ServiceResponse<User> login(String username, String password);

    ServiceResponse<String> register(User user);

    ServiceResponse<String> checkValid(String str, String type);

    ServiceResponse selectQuestion(String username);

    ServiceResponse<String> checkAnswer(String username, String question, String anwser);

    ServiceResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken);

    ServiceResponse<String> restPassword(String passwordOld, String passwordNew, User user);

    ServiceResponse<User> updateInformation(User user);

    ServiceResponse<User> getInformation(Integer userId);

    ServiceResponse checkAdminRole(User user);
}
