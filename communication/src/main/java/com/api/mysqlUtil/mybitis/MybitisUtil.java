package com.api.mysqlUtil.mybitis;

import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.mysqlUtil.Test.TestUser;
import com.api.mysqlUtil.Test.User;
import com.api.mysqlUtil.util.UtilTool;
import com.github.abel533.sql.SqlMapper;
import com.mongodb.client.MongoDatabase;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;

public class MybitisUtil {

    public static String urlMysql="jdbc:mysql://localhost:3309/test?serverTimezone=UTC";
    public static String pwdMysql="123";
    public static String nameMysql="123";
    private final static Map<String,SqlSession> SqlSessionClientHashMap = new ConcurrentHashMap<>();

    private volatile static SqlSession sqlSession = null;

    private MybitisUtil() {
    }

    public static SqlSession getSqlSession(String ip,int port,String database,String name,String password,String dbType) {
        String key = ip+port+database+name+password+dbType;
        if (SqlSessionClientHashMap.get(key) == null) {
            synchronized (MybitisUtil.class) {
                if (SqlSessionClientHashMap.get(key) == null) {
                    if (dbType.equals(UtilTool.MYSQL)){
                        urlMysql="jdbc:mysql://"+ip+":"+port+"/+"+database+"?serverTimezone=UTC";
                    }
                    if (dbType.equals(UtilTool.POSTGRES)){
                        urlMysql="jdbc:postgresql://"+ip+":"+port+"/"+database;
                    }
                    pwdMysql = password;
                    nameMysql = name;

                    String resource = "map/MyBatisConfig.xml";
                    Reader reader = null;
                    SqlSession sqlSession;
                    try {
                        reader = Resources.getResourceAsReader(resource);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder()
                            .build(reader);
                    sqlSession = sqlMapper.openSession();
                    SqlSessionClientHashMap.put(key,sqlSession);
                }
            }

        }

        return SqlSessionClientHashMap.get(key);
    }



    public static void closeSession(SqlSession session) {
        session.close();
    }

}
