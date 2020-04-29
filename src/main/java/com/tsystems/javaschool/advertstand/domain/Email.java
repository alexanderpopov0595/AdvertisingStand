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
    private String address;

    @Column(name="password")
    private String password;

    public void setId(long id){
        this.id=id;
    }
    public long getId(){
        return id;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getAddress(){
        return address;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return password;
    }


}
