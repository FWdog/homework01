package com.homework.pojo;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author czm
 * @version 1.0
 */

public class MySqlCon {

    private Connection con = null;

    public MySqlCon() throws Exception {

        Properties prop = new Properties();
        prop.load(new FileInputStream("src/main/resources/druid.properties"));

        //获取连接池对象
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);

        //获取数据库连接
        con = dataSource.getConnection();


    }

    public Connection getCon() {
        return con;
    }
}
