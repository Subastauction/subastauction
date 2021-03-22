package edu.eci.arsw.subastauction.model;

import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Usuarios")
public class Usuario {

    private String name,email,phone,password;
    private Date date;
    public Usuario(String name, String email, String phone, Date date, String password) {
        this.name=name;
        this.email=email;
        this.date=date;
        this.phone=phone;
        this.password=password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getDate() {
        return date;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
