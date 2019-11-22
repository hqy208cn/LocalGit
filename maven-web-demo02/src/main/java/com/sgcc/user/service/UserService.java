package com.sgcc.user.service;

import com.sgcc.user.pojo.User;

public interface UserService {
    //根据id查找
    public User getUserById(Integer userid);

    //添加一条数据
    public int insert(User user);
    
    //删除一条数据
    public int delete(Integer userid);
}