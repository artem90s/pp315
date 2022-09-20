package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String lastName;
    private Byte age;

    public User(Long id, String name, String lastName, Byte age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public User (){}

    public User (Long id, String name, String lastName){
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
}
