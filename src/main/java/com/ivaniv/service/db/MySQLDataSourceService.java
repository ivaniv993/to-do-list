package com.ivaniv.service.db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by iivaniv on 20.10.2016.
 */
public class MySQLDataSourceService {

    private static volatile DataSource dataSource;

    private DataSource init(){

        Properties props = new Properties();
        MysqlDataSource dataSource = null;
        try{
            props.load(getClass().getClassLoader().getResourceAsStream("db.properties"));

            Class.forName(props.getProperty("driver"));
            dataSource = new MysqlDataSource();
            dataSource.setURL(props.getProperty("url"));
            dataSource.setUser(props.getProperty("username"));
            dataSource.setPassword(props.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSource;
    }


    public DataSource getInstance(){
        if ( dataSource == null ){
            synchronized (MySQLDataSourceService.class){
                if ( dataSource == null ){
                    dataSource = init();
                }
            }
        }
        return dataSource;
    }
}
