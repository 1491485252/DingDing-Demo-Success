package com.cat.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cat.model.User;

@Mapper
public interface UserMapper {

    User getUser(User user);

}