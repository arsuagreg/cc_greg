package com.jagdon.careconnect.sysarch.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
    
    @Id
    private int id;
    private String name;
    private int age;
    private String email;
    private String username;
    private String password;
    private String confirmPassword;
    
    public User() {
    }


    public User(int id, String name, int age, String email, String username, String password){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setId(int id) {
        this.id = id;
    }



    public int getAge() {
        return age;
    }



    public void setAge(int age) {
        this.age = age;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}