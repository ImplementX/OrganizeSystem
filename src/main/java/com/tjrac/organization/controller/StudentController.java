package com.tjrac.organization.controller;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tjrac.organization.pojo.*;
import com.tjrac.organization.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/student-api")
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    StudentCourseService studentCourseService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseApplicationService courseApplicationService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    TagService tagService;
    @Autowired
    GradeService gradeService;
    @Autowired
    Gson gson;

    private static final Logger LOGGER= LoggerFactory.getLogger(StudentController.class);

    @RequestMapping( "/get-my-course" )
    @ResponseBody
    public String myCourse(@RequestAttribute("userTypeId") Integer studentId ) {

        List< StudentCourse > studentCourseList = studentCourseService.listStudentCourseByStudentId( studentId );
        List< JsonObject > list = new ArrayList<>();

        for ( StudentCourse studentCourse : studentCourseList ) {
            Course c = courseService.getCourse( studentCourse.getCourseId() );

            Organization organization = organizationService.getOrganization( c.getOrganizationId() );
            Teacher teacher = teacherService.getTeacher( c.getTeacherId() );
            List< Schedule > schedules = scheduleService.listScheduleByCourseId( c.getCourseId( ) );
            JsonObject obj = new JsonObject();
            Tag tag = tagService.getTag( c.getCourseTag( ) );
            obj.addProperty( "courseId",c.getCourseId() );
            obj.addProperty( "courseName",c.getCourseName() );
            obj.addProperty( "tagId",c.getCourseTag());
            obj.addProperty( "tagName",tag.getTagName());
            obj.addProperty( "organizationId", c.getOrganizationId());
            obj.addProperty( "organizationName", organization.getOrganizationName());
            obj.addProperty( "teacherId",c.getTeacherId() );
            obj.addProperty( "teacherName",teacher.getTeacherName() );
            obj.addProperty( "teacherTel",teacher.getTeacherTel() );
//            obj.addProperty( c.get );
            list.add( obj );

        }

        return gson.toJson( list );
    }

    @RequestMapping("/get-new-course")
    @ResponseBody
    public String newCourse(@RequestAttribute("userTypeId") Integer studentId){
        HashSet<Integer> removeSet = new HashSet<>(  );
        List< StudentCourse > studentCourses = studentCourseService.listStudentCourseByStudentId( studentId );

        for ( StudentCourse sc : studentCourses ) {
            removeSet.add( sc.getCourseId() );
        }
        List< CourseApplication > courseApplications =  courseApplicationService.listByStudentId(  studentId );
        for ( CourseApplication ca : courseApplications ) {
            removeSet.add( ca.getCourseId() );
        }

        List<JsonObject> list = new ArrayList<>(  );
       List<Course> courseList =  courseService.listCourseNotFull();
        for ( Course c : courseList ) {
           if ( removeSet.contains( c.getCourseId() ) || c.getCourseIsFull() ==1){
               continue;
           }
           else {
               Organization organization = organizationService.getOrganization( c.getOrganizationId() );
               Teacher teacher = teacherService.getTeacher( c.getTeacherId() );
               Tag tag = tagService.getTag( c.getCourseTag( ) );
               JsonObject obj = new JsonObject();
               obj.addProperty( "courseId",c.getCourseId() );
               obj.addProperty( "courseName",c.getCourseName() );
               obj.addProperty( "tagId",c.getCourseTag());
               obj.addProperty( "tagName",tag.getTagName());
               obj.addProperty( "organizationId", c.getOrganizationId());
               obj.addProperty( "organizationName", organization.getOrganizationName());
               obj.addProperty( "teacherId",c.getTeacherId() );
               obj.addProperty( "teacherName",teacher.getTeacherName() );
               obj.addProperty( "teacherTel",teacher.getTeacherTel() );
               list.add( obj );

           }
        }

     return  gson.toJson( list ) ;
    }

    @RequestMapping("/get-my-application")
    @ResponseBody
    public String myApplication(@RequestAttribute("userTypeId") int studentId){
        List<JsonObject> list = new ArrayList<>(  );
        List< CourseApplication > courseApplications = courseApplicationService.listByStudentId( studentId );

        for ( CourseApplication ca : courseApplications ) {
            JsonObject obj = new JsonObject();
            Course c = courseService.getCourse( ca.getCourseId( ) );
            Teacher teacher = teacherService.getTeacher( c.getTeacherId( ) );
            obj.addProperty( "applicationId",ca.getCourseApplicationId());
            obj.addProperty( "courseId",c.getCourseId() );
            obj.addProperty( "courseName",c.getCourseName() );
            obj.addProperty( "teacherId",c.getTeacherId() );
            obj.addProperty( "teacherName",teacher.getTeacherName() );
            obj.addProperty( "teacherTel",teacher.getTeacherTel() );
            obj.addProperty( "applicationResult", ca.getCourseApplicationResult());
            switch ( ca.getCourseApplicationResult() ){
                case 0:obj.addProperty( "result","<span class=\"label label-warning\">待审阅</span>" );break;
                case 1:obj.addProperty( "result","<span class=\"label label-success\">已通过</span>" );break;
                case 2:obj.addProperty( "result","<span class=\"label label-danger\">未通过</span>" );break;
            }

            switch ( ca.getCourseApplicationType() ){
                case 0:obj.addProperty( "type","退课" );break;
                case 1:obj.addProperty( "type","选课" );break;

            }
            list.add( obj );
        }
        return  gson.toJson( list )  ;
    }


    @RequestMapping("/apply-new-course")
    @ResponseBody
    public String applyCourse(@RequestAttribute("userTypeId")int studentId,int courseId){
        CourseApplication courseApplication = new CourseApplication( );
        courseApplication.setCourseId( courseId );
        courseApplication.setStudentId(studentId);
        courseApplication.setCourseApplicationType( 1 );
        courseApplication.setCourseApplicationResult( 0 );
        JsonObject jsonObject = new JsonObject( );
        if ( courseApplicationService.saveCourseApplication( courseApplication ) ) {
            jsonObject.addProperty( "message", "申请成功" );
        }else {
            jsonObject.addProperty( "message", "申请失败" );
        }
        return jsonObject.toString();
    }

    @RequestMapping("/apply-remove-course")
    @ResponseBody
    public String removeCourse(@RequestAttribute("userTypeId")int studentId,int courseId){
        CourseApplication courseApplication = new CourseApplication( );
        courseApplication.setCourseId( courseId );
        courseApplication.setStudentId(studentId);
        courseApplication.setCourseApplicationType( 0 );
        courseApplication.setCourseApplicationResult( 0 );
        JsonObject jsonObject = new JsonObject( );
        if ( courseApplicationService.saveCourseApplication( courseApplication ) ) {
            jsonObject.addProperty( "message", "申请成功" );
        }else {
            jsonObject.addProperty( "message", "申请失败" );
        }

        return jsonObject.toString();
    }

    @RequestMapping("/get-course-details")
    @ResponseBody
        public String getCourseDetails(@RequestAttribute("userTypeId")int studentId,int courseId) {

        Course course = courseService.getCourse( courseId );
        Teacher teacher = teacherService.getTeacher( course.getTeacherId( ) );
        Organization organization = organizationService.getOrganization( course.getOrganizationId( ) );
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty( "courseName",course.getCourseName());
        jsonObject.addProperty( "courseDetail",course.getCourseDescription());
        jsonObject.addProperty( "teacherName", teacher.getTeacherName() );
        jsonObject.addProperty( "tel", teacher.getTeacherTel() );
        jsonObject.addProperty( "WeChat", teacher.getTeacherWechat() );
        jsonObject.addProperty( "QQ", teacher.getTeacherQq() );
        jsonObject.addProperty( "organizationName", organization.getOrganizationName() );
        jsonObject.addProperty( "organizationDetails", organization.getOrganizationDescription() );
        jsonObject.addProperty( "time",gson.toJson( scheduleService.listScheduleByCourseId( courseId ) )  );
        return jsonObject.toString();
    }

    @RequestMapping("/cancel-application")
    @ResponseBody
    public String cancelApplication(int applicationId) {
        JsonObject jsonObject = new JsonObject();
        System.out.println(applicationId+"-------------------------------------------------------------------" );
        if ( courseApplicationService.removeCourseApplication( applicationId ) )
        {
            jsonObject.addProperty( "message", "申请已取消" );
        }
        else {
            jsonObject.addProperty( "message", "取消失败！" );
        }

        return jsonObject.toString();

    }


    @RequestMapping("/get-my-grade")
    @ResponseBody
    public String myGrade(@RequestAttribute("userTypeId")int studentId){
        List< Grade > grades = gradeService.listGradeByStudentId( studentId );
        List<JsonObject> list  = new ArrayList<>(  );
        for ( Grade grade : grades ) {
            Course course = courseService.getCourse( grade.getCourseId( ) );
            Teacher teacher = teacherService.getTeacher( course.getTeacherId( ) );
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty( "courseName",course.getCourseName());
            jsonObject.addProperty( "courseId",course.getCourseId());
            jsonObject.addProperty( "teacherName", teacher.getTeacherName() );
            jsonObject.addProperty( "teacherTel", teacher.getTeacherTel() );
            int num = grade.getGradeNumber();
            jsonObject.addProperty( "grade", num);
            if ( num >= 60 ){
                jsonObject.addProperty( "result", "<span class=\"label label-success\">通过</span>");
            }else {
                jsonObject.addProperty( "result", "<span class=\"label label-danger\">未通过</span>");
            }
            list.add( jsonObject );

        }
        return gson.toJson( list );
    }


}
