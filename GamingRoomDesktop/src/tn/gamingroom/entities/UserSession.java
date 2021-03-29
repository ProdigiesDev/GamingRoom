/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.entities;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Sonia
 */
public class UserSession {
    private static UserSession instance;

    private Membre user;
    private Membre.Role role;

    public UserSession(Membre user, Membre.Role role) {
        System.out.println(" i am here with : "+user);
        this.user = user;
        this.role = role;
        instance=this;
    }

    public static UserSession getInstance() {
//        if(instance == null) {
//           return null;
//        }
        
       return instance;
    }

    public Membre getUser() {
        return user;
    }

    public Membre.Role getPrivileges() {
        return role;
    }

    public void cleanUserSession() {
        user=null;// or null
        role = null;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + user.getNom() + '\'' +
                ", role=" + role +
                '}';
    }
}
