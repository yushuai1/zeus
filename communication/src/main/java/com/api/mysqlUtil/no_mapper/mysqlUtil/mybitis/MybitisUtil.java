package com.api.mysqlUtil.no_mapper.mysqlUtil.mybitis;

import com.api.mysqlUtil.no_mapper.mysqlUtil.config.MyDruidDataSourceFactory;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.InputStream;


public class MybitisUtil {

    static SqlSessionFactory factory = null;
    /**
     * 创建连接
     */
    public static SqlSession getSession() {
        SqlSession session = null;
        try {
            session = factory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return session;
    }


    private MybitisUtil() {
    }


    public static SqlSessionFactory getSessionFactory(String ip, int port, String database, String name, String password) throws Exception {
        if (factory == null) {
            synchronized (MybitisUtil.class) {
                if (factory == null) {
                    String urlMysql = "jdbc:mysql://" + ip + ":" + port + "/+" + database + "?serverTimezone=UTC";
                    DataSourceFactory d = new MyDruidDataSourceFactory(name, password, urlMysql);
                    InputStream is = UtilTool.getXml("com.api.mysqlUtil.no_mapper.sdk_entry","com.api.mysqlUtil.no_mapper.sdk_mapper");
                    XMLConfigBuilder parser = new XMLConfigBuilder(is, null, null);
                    Configuration cfg = parser.parse();
                    //关闭自动提交
                    TransactionFactory transactionFactory = new JdbcTransactionFactory();
                    Environment environment = new Environment("work",
                            transactionFactory, d.getDataSource());
                    cfg.setEnvironment(environment);
                    factory = new SqlSessionFactoryBuilder()
                            .build(cfg);
                }
            }
        }
        return factory;
    }
}
