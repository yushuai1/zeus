package com.api.mysqlUtil.jdbc.mysqlUtil.mybitis;


import com.api.mysqlUtil.jdbc.mysqlUtil.config.MyDruidDataSourceFactory;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.managed.ManagedTransactionFactory;


public class MybitisUtil {


    private volatile static SqlSession sqlSession = null;

    private MybitisUtil() {
    }

    public static SqlSession getSqlSession(String ip,int port,String database,String name,String password) {

        if (sqlSession == null) {
            synchronized (MybitisUtil.class) {
                if (sqlSession == null) {

                    String urlMysql="jdbc:mysql://"+ip+":"+port+"/+"+database+"?serverTimezone=UTC";

                    DataSourceFactory d = new MyDruidDataSourceFactory(name,password,urlMysql);
                    TransactionFactory transactionFactory = new ManagedTransactionFactory();
                    Environment environment = new Environment("development",
                            transactionFactory ,d.getDataSource());
                    Configuration cfg = new Configuration(environment);
                    SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder()
                            .build(cfg);
                    sqlSession = sqlMapper.openSession();
                }
            }
        }

        return sqlSession;
    }

    public static void closeSession(SqlSession session) {
        session.close();
    }

}
