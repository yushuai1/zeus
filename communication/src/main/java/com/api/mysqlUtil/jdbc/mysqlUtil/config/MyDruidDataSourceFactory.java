package com.api.mysqlUtil.jdbc.mysqlUtil.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


public class MyDruidDataSourceFactory implements DataSourceFactory {
    private Properties props;

    private String username;
    private String password;
    private String url;

    public MyDruidDataSourceFactory(String username, String password,String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        String driver = "com.mysql.jdbc.Driver";

        dds.setValidationQuery("SELECT 1 FROM DUAL");
        dds.setDriverClassName(driver);
        dds.setUrl(url);
        dds.setUsername(username);
        dds.setPassword(password);
        dds.setMaxActive(100);
        dds.setKeepAlive(true);
        dds.setInitialSize(5);
        dds.setMinIdle(5);
        dds.setMaxWait(60000);
        dds.setTestOnBorrow(false);
        dds.setTestOnReturn(false);
        dds.setTestWhileIdle(true);
        dds.setTimeBetweenEvictionRunsMillis(60000);
        dds.setMinEvictableIdleTimeMillis(25200000);
        dds.setRemoveAbandoned(true);
        dds.setRemoveAbandonedTimeout(1800);
        try {
            dds.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 其他配置可以根据MyBatis主配置文件进行配置
        try {
            dds.init();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dds;
    }

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }
}
