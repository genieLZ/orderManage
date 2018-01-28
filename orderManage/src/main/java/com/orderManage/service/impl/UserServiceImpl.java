package com.orderManage.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServiceResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServiceResponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount==0){
            return ServiceResponse.createByError("用户名不存在！");
        }

        //todo 密码登录MD5
        String MD5password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,MD5password);
        if(user == null){
            return ServiceResponse.createByError("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess("登录成功！",user);
    }

    public ServiceResponse<String> register(User user){
        ServiceResponse validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        int resultCount = userMapper.checkEmail(user.getEmail());
        if(resultCount>0){
            return ServiceResponse.createByError("邮箱已存在！");
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        resultCount = userMapper.insert(user);
        if(resultCount==0){
            return ServiceResponse.createByError("注册失败！");
        }
        return ServiceResponse.createBySuccess("注册成功！");
    }

    public ServiceResponse<String> checkValid(String str,String type){
        if(StringUtils.isNotBlank(type)){
            //开始校验
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount>0){
                    return ServiceResponse.createByError("用户名已存在！");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount>0){
                    return ServiceResponse.createByError("邮箱已存在！");
                }
            }
        }else{
            return ServiceResponse.createByError("参数错误！");
        }
        return ServiceResponse.createBySuccess("校验成功！");
    }

    public ServiceResponse selectQuestion(String username){
        ServiceResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServiceResponse.createByError("用户不存在！");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(StringUtils.isNotBlank(question)){
            return ServiceResponse.createBySuccess(question);
        }
        return ServiceResponse.createBySuccess("找回密码的问题是空的");
    }

    public ServiceResponse<String> checkAnswer(String username,String question,String anwser){
        int resultCount = userMapper.checkAnswer(username,question, anwser);
        if(resultCount>0){
            //说明问题及问题答案都是这个用户的而且正确
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServiceResponse.createBySuccess(forgetToken);
        }
        return ServiceResponse.createByError("问题的答案错误！");
    }

    public ServiceResponse<String> forgetRestPassword(String username,String passwordNew,String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return ServiceResponse.createByError("参数错误，token需要传递");
        }
        ServiceResponse validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            return ServiceResponse.createByError("用户不存在！");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return ServiceResponse.createByError("token无效或过期");
        }

        if(StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username,md5Password);
            if(rowCount > 0){
                return ServiceResponse.createBySuccess("修改密码成功！");
            }
        }else{
            return ServiceResponse.createByError("token错误，请重新获取重置密码的token");
        }
        return ServiceResponse.createByError("修改密码失败！");
    }

    public ServiceResponse<String> restPassword(String passwordOld,String passwordNew,User user){
        //防止横向越权，要校检一下这个用户的旧密码，一定要指定是这个用户，因为我们会查询一个count(1)，如果不指定id，那么结果就是true，count>0
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getId());
        if(resultCount == 0){
            return ServiceResponse.createByError("旧密码错误！");
        }
        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServiceResponse.createBySuccess("密码更新成功！");
        }
        return ServiceResponse.createByError("密码更新失败！");
    }

    public ServiceResponse<User> updateInformation(User user){
        //username是不能被更新的
        //email也要进行校验，校验新的email是否已经存在，并且存在的email如果相同的话，不能是我们当前这个用户的。
        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
        if(resultCount >0){
            return ServiceResponse.createByError("email已存在，请更换email再尝试更新！");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhone(user.getPhone());
        updateUser.setQuestion(user.getQuestion());
        updateUser.setAnswer(user.getAnswer());
        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            return ServiceResponse.createBySuccess("更新个人信息成功！",updateUser);
        }
        return ServiceResponse.createByError("更新个人信息失败！");
    }

    public ServiceResponse<User> getInformation(Integer userId){
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServiceResponse.createByError("找不到当前用户");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServiceResponse.createBySuccess(user);
    }

    /**
     * 校检是否是管理员
     * @param user
     * @return
     */
    public ServiceResponse checkAdminRole(User user){
        if(user!= null && user.getRole().intValue() == Const.Role.ROLE_ADMIN){
            return ServiceResponse.createBySuccess();
        }
        return ServiceResponse.createByError();
    }

}
