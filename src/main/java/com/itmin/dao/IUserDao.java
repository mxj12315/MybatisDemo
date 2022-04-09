package com.itmin.dao;

import com.itmin.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有的用户
     * @return 用户列表
     */
    List<User> findAll();

    /**
     * 通过id查询
     * @return 查询到的用户
     */
    User getUserById(int id);

    /**
     * 插入用户
     * @param user 要插入的用户对象
     * @return 插入成功的条数
     */
    int addUser(User user);

    /**
     * 更新数据
     * @param user 更新的用户
     * @return 更新成功的条数
     */
    int upDateUser(User user);

    /**
     * 删除用户
     */
    int deleteUser(int id);

    /**
     * 根据名字查询
     */
    List<User> getUserByName(String name);

    /**
     * 统计id 用户总数
     */
    int getCount();

    /**
     * 保存用户，同时拿到新增后的id值
     */
    int saveUser(User user);


    /**
     * 使用map
     */
    User getUserByIdMap(Map<String,Object> map);


}
