package com.itmin.test;


import com.itmin.MybatisUtils;
import com.itmin.dao.IUserDao;
import com.itmin.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MybatisTest {
    SqlSession session = MybatisUtils.getSession();
    IUserDao mapper = session.getMapper(IUserDao.class);

    @Test
    public void test() {
//        // 1.读取配置文件
//        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
//        // 2.创建SqlSessionFactory工厂
//        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
//        SqlSessionFactory factory = builder.build(in);
//        // 3.使用工厂生产SqlSession对象
//        SqlSession session = factory.openSession();

//        SqlSession session = MybatisUtils.getSession();
//        // 4、使用SqlSession对象创建Dao接口的代理对象
//        IUserDao mapper = session.getMapper(IUserDao.class);
        // 5.使用代理对象执行方法
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
        // 6.释放资源
//        in.close();
        session.close();
    }

    @Test
    public void test1() {
        User user = mapper.getUserById(41);
        System.out.println(user);
        // 释放资源
        session.close();
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("曹操");
        user.setAddress("陕西西安");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        user.setBirthday(df.parse("2014-03-25 17:47:362"));
        user.setSex("男");

        int i = mapper.addUser(user);
        session.commit();
        if (i > 0) System.out.println("提交成功" + i + "条");
        else System.out.println("添加失败");
        session.close();
    }

    @Test
    public void upDateUser() {
        User user = new User();
        user.setId(41);
        user.setUsername("孙权");
        user.setAddress("福建");

        int i = mapper.upDateUser(user);
        session.commit();
        if (i > 0) System.out.println("更新成功" + i + "条");
        else System.out.println("更新失败");
        session.close();
    }

    @Test
    public void deleteTest() {

        int i = mapper.deleteUser(46);
        if (i > 0) System.out.println("删除成功" + i + "条");
        else System.out.println("删除失败");
        session.close();
    }

    @Test
    public void getUserByName() {
        List<User> userList = mapper.getUserByName("%小%");
        if (userList.size() > 0) {
            System.out.printf("查询到%d条数据\n",userList.size());
            for (User user : userList) {
                System.out.println(user);
            }
        } else System.out.println("没有查询到");
        session.close();
    }

    @Test
    public void getCount(){
        int count = mapper.getCount();
        System.out.printf("统计共有%d个用户",count);
        session.close();
    }

    @Test
    public void saveUser() {
        User user = new User();
        // user.setId(41);   // 这里不能设置id 否则主键id重复
        user.setUsername("mybatis insert data");
        user.setAddress("GuangDong");
        user.setSex("男");
        user.setBirthday(new Date());
        //保存之前的数据User{id=null, username='mybatis insert data', birthday=Fri Apr 08 22:58:51 CST 2022, sex='男',
        // address='GuangDong'}
        System.out.println("保存之前的数据" + user);
        int i = mapper.saveUser(user);
        System.out.printf("插入成功%d条数据",i);
        //保存之后的数据User{id=62, username='mybatis insert data', birthday=Fri Apr 08 22:58:51 CST 2022, sex='男',
        // address='GuangDong'}
        System.out.println("保存之后的数据"+ user);
        session.close();

    }


    @Test
    public void getUserByIdMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("mapKeyId",51);
        map.put("mapKeyName","刘备");
        User userByIdMap = mapper.getUserByIdMap(map);

        System.out.println(userByIdMap);
        session.close();
    }
}
