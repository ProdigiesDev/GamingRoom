/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import java.sql.Date;

/**
 *
 * @author Asus
 */
public class Notification {
    public int id;
    public int to;
    public String title;
    public String body;
    public Date date;

    public Notification(int to, String title, String body) {
        this.to = to;
        this.title = title;
        this.body = body;
    }
    
    

    public Notification(int to, String title, String body, Date date) {
        this.to = to;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public Notification(int id, int to, String title, String body, Date date) {
        this.id = id;
        this.to = to;
        this.title = title;
        this.body = body;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "notification{" + "id=" + id + ", to=" + to + ", title=" + title + ", body=" + body + ", date=" + date + '}';
    }
    
    
}
