package com.tjrac.organization.controller;

import ch.qos.logback.core.joran.conditional.ElseAction;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tjrac.organization.pojo.*;
import com.tjrac.organization.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.accessibility.AccessibleText;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teacher-api")
public class TeacherController {
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
    AttendanceService attendanceService;
    @Autowired
    Gson gson;

    @RequestMapping( "/get-my-course" )
    @ResponseBody
    public String myCourse(@RequestAttribute("userTypeId") Integer teacherId ) {
        List< Course > courseList = courseService.listCourseByTeacherId( teacherId );

        List< JsonObject > list = new ArrayList<>();

        for ( Course c: courseList ) {

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

    @RequestMapping( "/get-course-list" )
    @ResponseBody
    public String courseList(@RequestAttribute("userTypeId") Integer teacherId ) {
        List< Course > courseList = courseService.listCourseByTeacherId( teacherId );

        return gson.toJson( courseList );
    }

    @RequestMapping("/get-my-application")
    @ResponseBody
    public String myApplication(@RequestAttribute("userTypeId") int teacherId){
        List<JsonObject> list = new ArrayList<>(  );
        List< CourseApplication > courseApplications = new ArrayList<>(  );
        List< Course > courseList = courseService.listCourseByTeacherId( teacherId );
        for ( Course course : courseList ) {
            System.out.println(course.toString() );
            courseApplications.addAll(   courseApplicationService.listByCourseId( course.getCourseId() )) ;
        }


        for ( CourseApplication ca : courseApplications ) {
            System.out.println(ca.toString() );
            JsonObject obj = new JsonObject();

            Course c = courseService.getCourse( ca.getCourseId( ) );
            if (ca.getCourseApplicationResult() == 0) {
                Student student = studentService.getStudent( ca.getStudentId( ) );
                obj.addProperty( "applicationId", ca.getCourseApplicationId( ) );
                obj.addProperty( "courseId", c.getCourseId( ) );
                obj.addProperty( "courseName", c.getCourseName( ) );
                obj.addProperty( "studentId", ca.getStudentId());
                obj.addProperty( "studentName", student.getStudentName());
                obj.addProperty( "studentTel", student.getStudentTel() );
                obj.addProperty( "applicationResult", ca.getCourseApplicationResult( ) );
                obj.addProperty( "result", "<span class=\"label label-warning\">待审阅</span>" );
                switch ( ca.getCourseApplicationType( ) ) {
                    case 0:
                        obj.addProperty( "type", "<span class=\"label label-danger\">退课申请</span>" );
                        break;
                    case 1:
                        obj.addProperty( "type", "<span class=\"label label-success\">选课申请</span>" );
                        break;

                }
                list.add( obj );
            }
        }
        return  gson.toJson( list )  ;
    }

    @RequestMapping("/permitApplication")
    @ResponseBody
    public String permitApplication(int applicationId){
        CourseApplication courseApplication = courseApplicationService.getCourseApplication( applicationId );
        courseApplication.setCourseApplicationResult(1);
        JsonObject obj = new JsonObject( );
        boolean b = false;
        if ( courseApplication.getCourseApplicationType() == 1) {

            StudentCourse studentCourse = new StudentCourse( );
            studentCourse.setCourseId( courseApplication.getCourseId( ) );
            studentCourse.setStudentId( courseApplication.getStudentId( ) );
            b = studentCourseService.saveStudentCourse( studentCourse );
        }
        else if(  courseApplication.getCourseApplicationType() == 0 ){
            StudentCourse studentCourse = studentCourseService.getByStudentIdAndCourseId( courseApplication.getStudentId( ), courseApplication.getCourseId( ) );
            b = studentCourseService.removeStudentCourse(  studentCourse.getStudentCourseId()  );
            System.out.println(courseApplication.getCourseApplicationId()+ "----------------------------------------");
        }



        if ( b && courseApplicationService.updateCourseApplication( courseApplication )){

            obj.addProperty( "message", "申请已通过！");
        }else {
            obj.addProperty( "message", "审批失败！");
        }

        return obj.toString();
    }

    @RequestMapping("/refuseApplication")
    @ResponseBody
    public String refuseApplication(int applicationId){
        CourseApplication courseApplication = courseApplicationService.getCourseApplication( applicationId );
        courseApplication.setCourseApplicationResult(2);
        JsonObject obj = new JsonObject();
        if ( courseApplicationService.updateCourseApplication( courseApplication ) ){
            obj.addProperty( "message", "申请已拒绝！");
        }else {
            obj.addProperty( "message", "审批失败！");
        }

        return obj.toString();
    }

    @RequestMapping("/new-course")
    @ResponseBody
    public String newCourse( @RequestAttribute("userTypeId") int teacherId,
                             @RequestParam("course-type") String courseType,
                             @RequestParam("course-name")String courseName,
                             @RequestParam("course-description")String courseDescription,
                             @RequestParam("course-organization")int organizationId,
                             @RequestParam("course-tag")int tagId,
                             @RequestParam("is-full")int isFull){
        Course course = new Course( );
        course.setCourseDescription( courseDescription );
        course.setCourseName( courseName );
        course.setCourseTag( tagId );
        course.setOrganizationId( organizationId );
        course.setCourseIsFull( isFull );
        course.setTeacherId( teacherId );
        course.setCourseType( courseType );
        JsonObject jsonObject = new JsonObject( );
        if(courseService.saveCourse( course )){
           jsonObject.addProperty( "message","创建成功！" );
        }else {
            jsonObject.addProperty( "message","创建失败！" );
        }
        return jsonObject.toString();

    }


    @RequestMapping("/update-course")
    @ResponseBody
    public String newCourse( int  courseId,
            @RequestAttribute("userTypeId") int teacherId,
                             @RequestParam("course-type") String courseType,
                             @RequestParam("course-name")String courseName,
                             @RequestParam("course-description")String courseDescription,
                             @RequestParam("course-organization")int organizationId,
                             @RequestParam("course-tag")int tagId,
                             @RequestParam("is-full")int isFull){
        Course course = new Course( );
        course.setCourseId( courseId );
        course.setCourseDescription( courseDescription );
        course.setCourseName( courseName );
        course.setCourseTag( tagId );
        course.setOrganizationId( organizationId );
        course.setCourseIsFull( isFull );
        course.setTeacherId( teacherId );
        course.setCourseType( courseType );
        JsonObject jsonObject = new JsonObject( );
        if(courseService.updateCourse( course )){
            jsonObject.addProperty( "message","修改成功！" );
        }else {
            jsonObject.addProperty( "message","修改失败！" );
        }
        return jsonObject.toString();

    }

    @RequestMapping("/course-form")
    @ResponseBody
    public String courseFormInit(@RequestAttribute("userTypeId") int teacherId){
        List< Organization > organizations = organizationService.listAll( );
        List< Tag > tags = tagService.listAll( );
        JsonObject obj = new JsonObject();
        obj.addProperty( "tags",gson.toJson( tags ) );
        obj.addProperty( "organizations",gson.toJson( organizations ) );
        return obj.toString();
    }

    @RequestMapping("/get-course")
    @ResponseBody
    public String getCourse(int courseId){
        Course course = courseService.getCourse( courseId );
        return gson.toJson( course );
    }
    @RequestMapping("/remove-course")
    @ResponseBody
    public String removeCourse(int courseId){
        boolean b = courseService.removeCourse( courseId );
        JsonObject obj = new JsonObject();
        if ( b ){
            obj.addProperty( "message","删除成功！" );
        }
        else {
            obj.addProperty( "message","删除失败！" );

        }


        return obj.toString();
    }

    @RequestMapping("/student-list")
    @ResponseBody
    public String studentList(int courseId){
        System.out.println( "courseId:"+ courseId);
        List< StudentCourse > studentCourses = studentCourseService.listStudentCourseByCourseId( courseId );
        List<Student> students  = new ArrayList<>(  );
        for ( StudentCourse sc : studentCourses ) {
            Integer studentId = sc.getStudentId( );
            Student student = studentService.getStudent( studentId );
            students.add( student );
        }

        return gson.toJson( students );
    }

    @RequestMapping("/remove-student")
    @ResponseBody
    public String removeStudent(int studentId,int courseId){

        StudentCourse studentCourse = studentCourseService.getByStudentIdAndCourseId( studentId,courseId );
        JsonObject obj = new JsonObject();
        if ( studentCourseService.removeStudentCourse( studentCourse.getStudentCourseId() ) ){
            obj.addProperty( "message","移除成功！" );
        }
        else {
            obj.addProperty( "message","移除失败！" );

        }
        return obj.toString();
    }

    @RequestMapping("/get-schedule")
    @ResponseBody
    public String getSchedule(int courseId){

        List< Schedule > schedules = scheduleService.listScheduleByCourseId( courseId );
        return gson.toJson( schedules );
    }
    @RequestMapping("/add-schedule")
    @ResponseBody
    public String addSchedule(int courseId,String scheduleDate,String schedulePlace){
        Schedule schedule = new Schedule();
        schedule.setCourseId( courseId );
        schedule.setScheduleDate( scheduleDate );
        schedule.setSchedulePlace( schedulePlace );
        JsonObject obj = new JsonObject();
        if ( scheduleService.saveSchedule( schedule ) ) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/remove-schedule")
    @ResponseBody
    public String addSchedule(int scheduleId){

        JsonObject obj = new JsonObject();
        if ( scheduleService.removeSchedule( scheduleId )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/attendance-list")
    @ResponseBody
    public String attendanceList(int courseId){
        List< Attendance > attendances = attendanceService.listByCourseId( courseId );
        List<JsonObject>  list = new ArrayList<>(  );
        for ( Attendance attendance : attendances ) {
            Student student = studentService.getStudent( attendance.getStudentId( ) );
            Schedule schedule = scheduleService.getSchedule( attendance.getScheduleId( ) );
            JsonObject obj = new JsonObject();
            obj.addProperty( "studentName",student.getStudentName() );
            obj.addProperty( "studentGender",student.getStudentGender() );
            obj.addProperty( "attendanceId",attendance.getAttendanceId() );
            obj.addProperty( "studentNumber",student.getStudentNumber() );
            obj.addProperty( "studentMajor",student.getStudentMajor() );
            obj.addProperty( "studentDepartment",student.getStudentDepartment() );
            obj.addProperty( "studentId",student.getStudentId() );
            obj.addProperty( "studentTel",student.getStudentTel() );
            obj.addProperty( "time",schedule.getScheduleDate() );
            obj.addProperty( "place",schedule.getSchedulePlace() );
            list.add( obj );
        }






        return gson.toJson( list );
    }

    @RequestMapping("/add-attendance")
    @ResponseBody
    public String addAttendance(int studentId,int courseId,int scheduleId){
        Attendance attendance = new Attendance();
        attendance.setCourseId( courseId );
        attendance.setStudentId( studentId );
        attendance.setScheduleId( scheduleId );
        JsonObject obj = new JsonObject();
        if ( attendanceService.saveAttendance( attendance )) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/remove-attendance")
    @ResponseBody
    public String removeAttendance(int attendanceId){
        JsonObject obj = new JsonObject();
        if ( attendanceService.removeAttendance( attendanceId )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }


    @RequestMapping("/init-attendance")
    @ResponseBody
    public String initAttendance(int courseId){
        List< StudentCourse > studentCourses = studentCourseService.listStudentCourseByCourseId( courseId );
        List<Student> students  = new ArrayList<>(  );
        for ( StudentCourse sc : studentCourses ) {
            Integer studentId = sc.getStudentId( );
            Student student = studentService.getStudent( studentId );
            students.add( student );
        }

        List< Schedule > schedules = scheduleService.listScheduleByCourseId( courseId );


        JsonObject obj = new JsonObject();
        obj.addProperty( "students", gson.toJson( students ) );
        obj.addProperty( "schedules", gson.toJson( schedules ) );
        return obj.toString();
    }
    @RequestMapping("/students-grade")
    @ResponseBody
    public String studentsGrade(int courseId){
        List< StudentCourse > studentCourses = studentCourseService.listStudentCourseByCourseId( courseId );
        List<JsonObject> objs = new ArrayList<>(  );
        for ( StudentCourse sc : studentCourses ) {
            JsonObject obj = new JsonObject();
            Integer studentId = sc.getStudentId( );
            Student student = studentService.getStudent( studentId );
            Grade grade = gradeService.getGradeByCourseIdAndStudentId( courseId, studentId );
            if ( grade == null ){
                obj.addProperty( "grade","" );
            }else {
                obj.addProperty( "grade",grade.getGradeNumber() );
                obj.addProperty( "gradeId",grade.getGradeId() );
            }
            obj.addProperty( "studentId",student.getStudentId() );
            obj.addProperty( "studentNumber",student.getStudentNumber() );
            obj.addProperty( "studentName",student.getStudentName() );
            obj.addProperty( "studentGender",student.getStudentGender() );
            obj.addProperty( "studentAge",student.getStudentAge() );
            obj.addProperty( "studentMajor",student.getStudentMajor() );
            obj.addProperty( "studentDepartment",student.getStudentDepartment() );


            objs.add( obj );
        }

        return gson.toJson( objs );
    }

    @RequestMapping("/update-grade")
    @ResponseBody
    public String updateGrade(int courseId,int studentId,int gradeNumber){


            JsonObject obj = new JsonObject();
            boolean result = false;
            Grade grade = gradeService.getGradeByCourseIdAndStudentId( courseId, studentId );
            if ( grade==null ){
                grade = new Grade( );
                grade.setCourseId( courseId );
                grade.setStudentId( studentId );
                grade.setGradeNumber( gradeNumber );
                result =  gradeService.saveGrade( grade );
            }else {
                grade.setGradeNumber( gradeNumber );
                result = gradeService.updateGrade( grade );
            }
        if ( result) {
            obj.addProperty( "message", "更新成功！" );
        }else {
            obj.addProperty( "message", "更新失败！" );
        }
        return obj.toString();


    }


    @RequestMapping("/organization-list")
    @ResponseBody
    public String organizationList(){
        List< Organization > organizations = organizationService.listAll( );
        ArrayList< JsonObject > list = new ArrayList<>( );
        for ( Organization organization : organizations ) {
            Tag tag = tagService.getTag( organization.getOrganizationTags( ) );
            JsonObject obj = new JsonObject();
            obj.addProperty( "organizationName",organization.getOrganizationName() );
            obj.addProperty( "organizationDescription",organization.getOrganizationDescription() );
            obj.addProperty( "organizationId",organization.getOrganizationId());
            obj.addProperty( "organizationOwner",organization.getOrganizationOwner());
            obj.addProperty( "organizationViceOwner",organization.getOrganizationViceOwner());
            obj.addProperty( "organizationTag",tag.getTagName());
            list.add( obj );
        }






        return gson.toJson( list );
    }

    @RequestMapping("/remove-organization")
    @ResponseBody
    public String removeOrganization(int organizationId){
        JsonObject obj = new JsonObject();
        if ( organizationService.removeOrganization( organizationId )) {
            obj.addProperty( "message", "删除成功！" );
        }else {
            obj.addProperty( "message", "删除失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/add-organization")
    @ResponseBody
    public String addOrganization(String organizationName, int organizationOwner, int organizationViceOwner,int  organizationTag,String organizationDescription){
        Organization organization = new Organization( );
        organization.setOrganizationName( organizationName );
        organization.setOrganizationDescription( organizationDescription );
        organization.setOrganizationOwner( organizationOwner );
        organization.setOrganizationViceOwner( organizationViceOwner );
        organization.setOrganizationTags( organizationTag );
        JsonObject obj = new JsonObject();
        if ( organizationService.saveOrganization( organization )) {
            obj.addProperty( "message", "添加成功！" );
        }else {
            obj.addProperty( "message", "添加失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/get-organization")
    @ResponseBody
    public String getOrganization(int  organizationId){
        Organization organization = organizationService.getOrganization( organizationId );
        return gson.toJson( organization );
    }

    @RequestMapping("/update-organization")
    @ResponseBody
    public String getOrganization(int organizationId ,String organizationName, int organizationOwner, int organizationViceOwner,int  organizationTag,String organizationDescription){
        JsonObject obj = new JsonObject();
        Organization organization = new Organization( );
        organization.setOrganizationId( organizationId );
        organization.setOrganizationName( organizationName );
        organization.setOrganizationDescription( organizationDescription );
        organization.setOrganizationOwner( organizationOwner );
        organization.setOrganizationViceOwner( organizationViceOwner );
        organization.setOrganizationTags( organizationTag );
        if ( organizationService.updateOrganization( organization )) {
            obj.addProperty( "message", "修改成功！" );
        }else {
            obj.addProperty( "message", "修改失败！" );
        }
        return obj.toString();
    }

    @RequestMapping("/tag-list")
    @ResponseBody
    public String tagList(){
        List< Tag > tags = tagService.listAll( );

        return gson.toJson( tags );
    }


}
