package com.online.exam;

import com.online.exam.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnlineExaminationSystemApplication{


    public static void main(String[] args) {
        SpringApplication.run(OnlineExaminationSystemApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


//    @Override
//    public void run(String... args) throws Exception {
//       List<UserRole> userRole=  new ArrayList<UserRole>();
//        User user=new User();
//        user.setUserName("amrit1022");
//        user.setUserEmail("amrit94@gmail.com");
//        user.setUserFirstName("amrit");
//        user.setUserLastName("thapa");
//        user.setUserPhoneNumber("98102223");
//        user.setUserPassword("vudey");
//
//
//
//
//
//        Role role=new Role();
//        role.setRoleId(40L);
//        role.setRoleName("Admin");
//        role.setUserRoles(userRole);
//
//
//
//        UserRole userRole1=new UserRole();
//        userRole1.setRole(role);
//        userRole1.setUser(user);
//        userRole.add(userRole1);
//
//        User resultUser=this.userService.createUser(user,userRole);
//        if(resultUser!=null){
//            System.out.println("Successfully add the user");
//        }










}
