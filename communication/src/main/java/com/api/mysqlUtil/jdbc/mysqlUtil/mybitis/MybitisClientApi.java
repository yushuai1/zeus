package com.api.mysqlUtil.jdbc.mysqlUtil.mybitis;

import com.github.abel533.sql.SqlMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.api.mysqlUtil.jdbc.mysqlUtil.mybitis.MybitisUtil.getSqlSession;


public class MybitisClientApi {

    private SqlSession session=null;

    private SqlMapper sqlMapper = null;

    public  MybitisClientApi(String ip,int port,String database,String name,String password){
        session = getSqlSession(ip,port,database,name,password);
        sqlMapper = new SqlMapper(session);
    }

    public  <T> T selectOne(String sql, Class<T> resultType){
        return sqlMapper.selectOne(sql,resultType);
    }

    public <T> List<T> selectList(String sql, Class<T> resultType){
        return sqlMapper.selectList(sql,resultType);
    }

    public int insert(String sql, Object value){
        int result = sqlMapper.insert(sql,value);
//        session.commit();
        return result;
    }

    public int insertMany(String sql, List<Object> value){
        int result = 0;
        for (Object o:value) {
            result = sqlMapper.insert(sql,o)+result;
        }
//        session.commit();
        return result;
    }

    public int insert(String sql){
        int result = sqlMapper.insert(sql);
//        session.commit();
        return result;
    }
    public int delete(String sql, Object value) {
        return sqlMapper.delete(sql,value);
    }

    public int delete(String sql) {
        int result = sqlMapper.delete(sql);
        return result;
    }
    public int update(String sql, Object value) {
        int result = sqlMapper.update(sql,value);
        return result;
    }
    public int update(String sql) {
        int result = sqlMapper.update(sql);
        return result;
    }

    public static void main(String[] args) {

        MybitisClientApi session = new MybitisClientApi(
                "localhost",3306,"test","root","root");

//        List<TestUser> testUser = session.selectList
//                    ("select u.id,u.name,u.age,t.classes from user as u , timees as t",TestUser.class);
//
//        System.out.println("---"+testUser.get(0).toString());
//
//        User user = new User("nihao",3,3);
//        session.insert("INSERT into user values(#{name},#{id},#{age} )",user);
//        List<Test> t = null;
        long t1 = System.currentTimeMillis();
        for(int i=100;i<10100;i++){
            session.insert("INSERT INTO test (`namea`, `age`) VALUES ('asd', 11);");
//              t=session.selectList("select * from user_join",Test.class);
        }
        System.out.println((System.currentTimeMillis()-t1));


    }
}
