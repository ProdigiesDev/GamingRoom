/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.interfaces;

import java.util.ArrayList;
import java.util.List;
import tn.gamingroom.entities.Membre;

/**
 *
 * @author Sonia
 */
public interface IMembre<T> {
     public int ajouterMembre(T m);
     public int ajouterCoach(T m);
     public int sumprimerMembres(T m);
     public int modifierMembres(T m);
     public int modifierMembreParAdmin(T m);
     public List<T> DisplayMembres();
     public T Login(String a,String b);
     public List<T> RechercherMembres(String x);
     public ArrayList<T> TrierParId();
     public int forgotPassword(String email,String newpassword);
     public int lastId();
     public int getPointParid(int id);
     public String autotext();
     public int activerCompte(T m);
     public int getBandurParid(int id);
     public String getDescParId(int id);
     public String getEmailParId(int id);
     public int modifierMDPParMembre(int id,String nvmdp);
     public Membre getById(int id);
     public Membre getMemberById(int idM);
     public List<String>  GetEmail();
     public List<String> RechercherMembresProfil();
     public Membre getMembreByEmail(String email);
     public boolean EmailExiste(String email);
    
}
