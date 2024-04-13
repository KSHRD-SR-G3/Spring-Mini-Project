package org.example.srg3springminiproject.repository;

import org.apache.ibatis.annotations.*;
import org.example.srg3springminiproject.model.User;
import org.example.srg3springminiproject.model.request.ForgetRequest;

@Mapper
public interface UserRepository {
    @Results(id = "authMapper", value = {
            @Result(property = "userId",column = "user_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password")
    })
    @Select("INSERT INTO users_tb (email, password) VALUES (#{user.email}, #{user.password}) RETURNING *")
    User save(@Param("user") User newUser);

    @Select("SELECT * FROM users_tb WHERE email = #{email}")
    @ResultMap("authMapper")
    User getUserByEmail(@Param("email") String email);

    @Select("UPDATE users_tb SET password = #{pass.password} WHERE email = #{email} RETURNING *")
    @ResultMap("authMapper")
    User updatePassword(@Param("pass") ForgetRequest forgetRequest , @Param("email") String email);
}
