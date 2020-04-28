package com.tsystems.javaschool.advertstand.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="emails")
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }


}
