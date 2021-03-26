/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.tests;

import tn.gamingroom.entities.*;
import tn.gamingroom.services.AvisService;
import tn.gamingroom.services.JeuxService;
import tn.gamingroom.services.ReclamationService;
import tn.gamingroom.services.ScoreService;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;
import tn.gamingroom.outils.ApiCall;
import tn.gamingroom.outils.Env;
/**
 *
 * @author Dah
 */
public class InteractionTests {
    
    static AvisService avisService=new AvisService();
    static JeuxService jeuxService=new JeuxService();
    static ReclamationService reclamationService=new ReclamationService();
    static ScoreService scoreService=new ScoreService();
    static Scanner scan=new Scanner(System.in);
    
    public static void main(String arg[]){
        
        displayMenu();
    }
    
    static void displayMenu(){
        int chx=5;
        do{
            System.out.println("------------------------Gestion interaction------------------------");
            System.out.println("1) Avis");
            System.out.println("2) Jeux");
            System.out.println("3) Score");
            System.out.println("4) Reclamation");
            System.out.println("5) Quitter");
            chx=scan.nextInt();
            switch(chx){
                case 1:gestionAvis();break;
                case 2:gestionJeux();break;
                case 3:gestionScore();break;
                case 4:gestionReclamation();break;
            }
        }while(chx!=5);
    }
    
    static void displayAvisStatistic(){
        System.out.println("Chargement en cours...");
        List<Avis> as=avisService.getListAvis();
        int nbpos=0;
        int nbneg=0;
        int neutre=0;
        for(Avis avi:as){
            String text=avi.getAvis().replaceAll(" ","%20");
            HashMap<String,String> headers=new HashMap();
            headers.put("Accept", "application/json");
            HttpURLConnection conn=ApiCall.callApi("https://api.meaningcloud.com/sentiment-2.1?verbose=y&key="+Env.keyMeaningcloudApi+"&lang=en&txt="+text+"&model=general", headers);
            try {
               if (conn.getResponseCode() != 200) {
                   throw new RuntimeException("Failed : HTTP Error code : "
                           + conn.getResponseCode());
               }
               InputStreamReader in = new InputStreamReader(conn.getInputStream());
               BufferedReader br = new BufferedReader(in);
               JSONObject obj = new JSONObject(br.readLine());
               String sentimentRes=obj.getString("score_tag");
               switch(sentimentRes){
                   case "P+":case "P":case"NEU":nbpos++;break;
                   case "N":case "N+":nbneg++;break;
                   default:neutre++;break;
               }
               

           } catch (Exception e) {
               System.out.println("Exception in NetClientGet:- " + e);
           }
            finally{
               conn.disconnect();
            }
        }
        nbpos=nbpos*100/as.size();
        nbneg=nbneg*100/as.size();
        neutre=neutre*100/as.size();
        System.out.println("Avis Positive : "+nbpos+"% ,Négative : "+nbneg+"%"+" Neutre : "+neutre+"%");
        
    }
    
    static void gestionAvis(){
        int chx=4;
        do{
            System.out.println("------------------------Gestion Avis------------------------");
            System.out.println("1) Liste Avis");
            System.out.println("2) Ajouter");
            System.out.println("3) Avis Statistic");
            System.out.println("4) Retour");
            chx=scan.nextInt();
            switch(chx){
                case 1:System.out.println(avisService.getListAvis());break;
                case 2:ajouterAvis();break;
                case 3:displayAvisStatistic();break;
            }
        }while(chx!=4);
    }
    
    static void ajouterAvis(){
        Avis a=new Avis();
        System.out.print("Membre Id : ");
        a.setMember_id(scan.nextInt());
        scan.nextLine();
        System.out.print("Votre Avis : ");
        a.setAvis(scan.nextLine());
        
        System.out.println("Etes-vous sûr de vouloir ajouter cet avis !");
        System.out.println(a);
        
        if(valider()){
            int res=avisService.ajouterAvis(a);
            if(res > 0)
                System.out.println("Votre avis a été ajouter");
            else
                System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
        }
        
    }
    
    static void gestionJeux(){
        int chx=5;
        do{
            System.out.println("------------------------Gestion Jeux------------------------");
            System.out.println("1) Liste Jeux");
            System.out.println("2) Ajouter");
            System.out.println("3) Modifier");
            System.out.println("4) Supprimer");
            System.out.println("5) Retour");
            chx=scan.nextInt();
            switch(chx){
                case 1:System.out.println(jeuxService.getAll());break;
                case 2:ajouterJeux();break;
                case 3:modifierJeux();break;
                case 4:supprimerJeux();break;
            }
        }while(chx!=5);
    }
    static void ajouterJeux(){
        Jeux jeux=new Jeux();
        System.out.println("Donner nom de jeu : ");scan.nextLine();
        jeux.setNom(scan.nextLine());
        System.out.println("Donner description de jeu : ");
        jeux.setDescriString(scan.nextLine());
        
        System.out.println("Etes-vous sûr de vouloir ajouter cet Jeux !");
        System.out.println(jeux);
        if(valider()){
             int res=jeuxService.ajouter(jeux);
            if(res > 0)
                System.out.println("Jeux a été ajouter");
            else
                System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
        
        }
        
    }
    
    static void supprimerJeux(){
        System.out.print("Donner jeux id : ");
        int id=scan.nextInt();
        int nbSupp=jeuxService.supprimer(id);
        if(nbSupp > 0)
           System.out.println("Jeux été supprimer");
        else
           System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
    
    }
    
    static void modifierJeux(){
        System.out.print("Donner jeux id : ");
        Jeux j=new Jeux(scan.nextInt(),"csgo","description csgo",Jeux.Type.Desktop);
        int nbModifier=jeuxService.modifier(j);
        if(nbModifier > 0)
           System.out.println("Jeux été modifier");
        else
           System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
    }
    
    static void gestionScore(){
        int chx=4;
        do{
            System.out.println("------------------------Gestion Score------------------------");
            System.out.println("1) Liste Score");
            System.out.println("2) Ajouter");
            System.out.println("3) Modifier");
            System.out.println("4) Retour");
            chx=scan.nextInt();
            switch(chx){
                case 1:listeScoreByJeuxId();break;
                case 2:ajouterScore();break;
                case 3:modifierScore();break;
            }
        }while(chx!=4);
    }
    static void listeScoreByJeuxId(){
        System.out.print("Donner jeux id : ");
        int id=scan.nextInt();
        System.out.println(scoreService.getScoreByJeuId(id));
    }
    static void ajouterScore(){
        Score score=new Score();
        
        System.out.print("Donner jeux id : ");
        score.setJeux_id(scan.nextInt());
        
        System.out.print("Donner member id : ");
        score.setMemebre_id(scan.nextInt());
        
        
        System.out.print("Donner score : ");
        score.setScore(scan.nextInt());
        System.out.println("Etes-vous sûr de vouloir ajouter cet Score !");
        System.out.println(score);
        
        if(valider()){
            int res=scoreService.ajouterScore(score);
            if(res > 0)
                System.out.println("Score a été ajouter");
            else
                System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
        }
        
    }
    static void modifierScore(){
         Score score=new Score();
        
        System.out.print("Donner  id : ");
        score.setId(scan.nextInt());
        
        System.out.print("Donner score : ");
        score.setScore(scan.nextInt());
        
       
        int res=scoreService.updateScore(score);
           if(res > 0)
               System.out.println("Score a été modifier");
            else
               System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
        
    }
    static void gestionReclamation(){
        int chx=3;
        do{
            System.out.println("------------------------Gestion Reclamation------------------------");
            System.out.println("1) Liste Reclamation");
            System.out.println("2) Ajouter");
            System.out.println("3) Retour");
            chx=scan.nextInt();
            switch(chx){
                case 1:System.out.println(reclamationService.getListReclamation());break;
                case 2:ajouterReclamation();break;
            }
        }while(chx!=3);
    }
    static void ajouterReclamation(){
        Reclamation rec=new Reclamation();
        System.out.print("Membre Id : ");
        rec.setMembre_id(scan.nextInt());
        scan.nextLine();
        System.out.print("Sujet : ");
        rec.setSujet(scan.nextLine());
        System.out.print("Contenue : ");
        rec.setContenue(scan.nextLine());
        
        System.out.println("Etes-vous sûr de vouloir ajouter cet Reclamation !");
        System.out.println(rec);
        
        if(valider()){
            int res=reclamationService.ajouterReclamation(rec);
            if(res > 0)
                System.out.println("Reclamation a été ajouter");
            else
                System.out.println("Une erreur s'est produite, veuillez réessayer plus tard");
        }
    }
    static boolean valider(){
        boolean valider=false;
        String chx="";
        do{
           System.out.println("Oui|Non");
           chx=scan.nextLine();
           valider=!chx.toLowerCase().equals("oui");
           if(valider)
               valider=!chx.toLowerCase().equals("non");
           
        }while(valider);
        
        return chx.toLowerCase().equals("oui");
    }
}
