package com.api.mysqlUtil.no_mapper.test;


import com.api.mysqlUtil.no_mapper.mysqlUtil.mybitis.MybitisUtil;
import com.api.mysqlUtil.no_mapper.sdk_entry.Student;
import com.api.mysqlUtil.no_mapper.sdk_mapper.StudentMapper;
import org.apache.ibatis.session.SqlSession;

public class StudentTest {


    public static void main(String[] args) throws Exception {
        long t1 = System.currentTimeMillis();
        StudentMapper studentMapper = null;
        for (int i=0;i<100;i++){
            SqlSession sqlSession=MybitisUtil.getSessionFactory("localhost",3306,"test","root","root").openSession();
            studentMapper=sqlSession.getMapper(StudentMapper.class);
            Student student=new Student("nn",11);
            studentMapper.insertStudent(student);
            sqlSession.commit();
            sqlSession.close();
        }
        System.out.println(System.currentTimeMillis()-t1);

    }
//    private SqlSession sqlSession=null;
//    private StudentMapper studentMapper=null;
    /**
     * 测试方法前调用
     * @throws Exception
     */
//    @Before
//    public void setUp() throws Exception {
//        sqlSession=MybitisUtil.getSessionFactory("localhost",3306,"test","root","root").openSession();
//        studentMapper=sqlSession.getMapper(StudentMapper.class);
//    }

    /**
     * 测试方法后调用
     * @throws Exception
     */
//    @After
//    public void tearDown() throws Exception {
//        sqlSession.close();
//    }
//
//    @Test
//    public void testInsert() {
//
//        Student student=new Student("琪琪",11);
//        studentMapper.insertStudent(student);
//        sqlSession.commit();
//    }

//    @Test
//    public void testUpdate() {
//        logger.info("更新学生");
//        Student student=new Student(6,"琪琪2",12);
//        studentMapper.updateStudent(student);
//        sqlSession.commit();
//    }
//
//    @Test
//    public void testDelete() {
//        logger.info("删除学生");
//        studentMapper.deleteStudent(6);
//        sqlSession.commit();
//    }
//
//    @Test
//    public void testGetById() {
//        logger.info("通过ID查找学生");
//        Student student=studentMapper.getStudentById(1);
//        System.out.println(student);
//    }
//
//    @Test
//    public void testFindStudents() {
//        logger.info("查找所有学生");
//        List<Student> studentList=studentMapper.findStudents();
//        for(Student student:studentList){
//            System.out.println(student);
//        }
//    }
}

