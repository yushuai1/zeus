package com.api.mysqlUtil.no_mapper.sdk_mapper;

import com.api.mysqlUtil.no_mapper.sdk_entry.Student;
import org.apache.ibatis.annotations.Insert;


public interface StudentMapper {
    @Insert("insert into student(name,age) values(#{name,jdbcType=VARCHAR},#{age,jdbcType=INTEGER})")
    public int insertStudent(Student student);
//
//    @Update("update t_student set name=#{name},age=#{age} where id=#{id}")
//    public int updateStudent(Student student);
//
//    @Delete("delete from t_student where id=#{id}")
//    public int deleteStudent(int id);
//
//    @Select("select * from t_student where id=#{id}")
//    public Student getStudentById(Integer id);
//
//    @Select("select * from t_student")
//    @Results(
//            {
//                    @Result(id=true,column="id",property="id"),
//                    @Result(column="name",property="name"),
//                    @Result(column="age",property="age")
//            }
//    )
//    public List<Student> findStudents();

}
