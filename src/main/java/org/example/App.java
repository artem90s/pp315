package org.example;

import org.example.config.MyConfig;
import org.example.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;

public class App 
{
    public static void main(String[]args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication=context.getBean("communication", Communication.class);
        List<User> allUsers=communication.allUsers();
//        System.out.println(allUsers);
        User user = new User(3L,"James","Brown", (byte) 33);
        communication.addUser(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.updateUser(user);
        communication.deleteUser(3L);



    }
}
