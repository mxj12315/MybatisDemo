package com.itmin;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Mybatis工具类
 */
public class MybatisUtils {
    public static SqlSessionFactory factory;

    static {
        try {
            // 1.读取配置文件
            InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
            // 2.创建SqlSessionFactory工厂
            SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
            // 构建factory工厂
            factory = builder.build(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSession getSession() {
        return factory.openSession();
    }

}
