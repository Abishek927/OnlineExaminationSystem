package com.online.exam.controller;

import com.online.exam.dto.CourseDto;
import com.online.exam.helper.ApiResponse;

import com.online.exam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/user/{uId}/fac/{facId}/cat/{catId}/course/create")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<?> createCourseController(@PathVariable Long uId, @PathVariable Long facId, @PathVariable Long catId, @RequestBody CourseDto courseDto) throws Exception {
                CourseDto course1=this.courseService.createCourse(uId,facId,catId,courseDto);
                if(course1==null){
                    return new ResponseEntity<>(new ApiResponse("Something went wrong!!!",false), HttpStatusCode.valueOf(500));
                }
                return new ResponseEntity<>(course1,HttpStatusCode.valueOf(200));
    }


    @DeleteMapping("/user/{uId}/fac/{fId}/cat/{catId}/course/delete")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<ApiResponse> deleteCourseController(@PathVariable Long uId,@PathVariable Long fId,@PathVariable Long catId,@RequestParam("courseId")Long courseId) throws Exception {
        String message=this.courseService.deleteCourse(uId, fId, catId, courseId);
        if(message.isEmpty()){
            return new ResponseEntity<>(new ApiResponse("Something went wrong!!!!",false),HttpStatusCode.valueOf(500));
        }
        return new ResponseEntity<>(new ApiResponse(message,true),HttpStatusCode.valueOf(200));

    }

    @GetMapping("/user/{uId}/fac/{fId}/cat/read/{catId}")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<?> getCourseByCategoryController(@PathVariable Long uId,@PathVariable Long fId,@PathVariable Long catId) throws Exception {
        List<CourseDto> courseDtos=this.courseService.getCoursesByCategory(uId, fId, catId);
        if(courseDtos.isEmpty()){
            return new ResponseEntity<>(new ApiResponse("There is no courses for the given category",true),HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(courseDtos,HttpStatusCode.valueOf(200));

    }
    @PutMapping("/user/{uId}/fac/{fId}/cat/{catId}/course/update/{cId}")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<CourseDto> updateCourseController(@PathVariable Long uId,@PathVariable Long fId,@PathVariable Long catId,@PathVariable Long cId,@RequestBody CourseDto courseDto) throws Exception {
            CourseDto course=this.courseService.updateCourse(uId, fId, catId, cId, courseDto);



        return new ResponseEntity<>(course,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/user/{uId}/fac/{fId}/cat/{catId}/course/{cId}")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<?> getCourseByIdController(@PathVariable Long uId,@PathVariable Long fId,@PathVariable Long catId,@PathVariable Long cId) throws Exception {
        CourseDto courseDto=this.courseService.getCourseById(uId, fId, catId, cId);
        return new ResponseEntity<>(courseDto,HttpStatusCode.valueOf(200));
    }
    @GetMapping("/user/{userId}/readAll")
    @PreAuthorize("hasAuthority('manage_course')")
    ResponseEntity<?> getAllCourse(@PathVariable Long userId)
    {
        List<CourseDto> courseDtos=this.courseService.getAllCourse(userId);
        if(!courseDtos.isEmpty()){
            return new ResponseEntity<>(courseDtos,HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>(new ApiResponse("there is no category created by the given user with id "+userId,false),HttpStatusCode.valueOf(200));
    }



}
