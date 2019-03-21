package com.api.mysqlUtil.config;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import com.api.mysqlUtil.mybitis.MybitisUtil;
import org.apache.ibatis.datasource.DataSourceFactory;
import com.alibaba.druid.pool.DruidDataSource;


public class MyDruidDataSourceFactory implements DataSourceFactory {
    private Properties props;

    @Override
    public DataSource getDataSource() {
        DruidDataSource dds = new DruidDataSource();
        String driver = "com.mysql.jdbc.Driver";
        if (MybitisUtil.urlMysql.contains("postgresql")) {
            driver = "org.postgresql.Driver";
        } else {
            dds.setValidationQuery("SELECT 1 FROM DUAL");
        }
        dds.setDriverClassName(driver);
        dds.setUrl(MybitisUtil.urlMysql);
        dds.setUsername(MybitisUtil.nameMysql);
        dds.setPassword(MybitisUtil.pwdMysql);
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
