/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import tn.gamingroom.entities.Cours;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.entities.ParticipantsCours;
import tn.gamingroom.interfaces.IParticipantsCours;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author eyatr
 */
public class ServiceParticipantsCours implements IParticipantsCours {

    @Override
    public int ajouterParticipant(int membre_id, int cour_id ) {
        int nb_ajouter=0;
        try {
            String requete = "INSERT INTO participantcours(membre_id,cour_id)" + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, membre_id);
            pst.setInt(2, cour_id);
            System.out.println(cour_id+"sss");

            nb_ajouter=pst.executeUpdate();
            if (nb_ajouter <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Inscription effectuée avec succès");
            }
          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_ajouter;
    }

    @Override
    public List<ParticipantsCours> DisplayParticipants() {
        List<ParticipantsCours> PartCours = new ArrayList<>();
        try {
            String requeteDs = "SELECT * FROM participantcours ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                ParticipantsCours p = new ParticipantsCours();
                p.setId(rs.getInt("id"));
                p.setMembre_id(rs.getInt("membre_id"));
                p.setCour_id(rs.getInt("cour_id"));

                PartCours.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PartCours;
    }

     @Override
    public List<Membre> getListeParticipants(int idE) {
        List<Membre> membreList = new ArrayList<>();
        try {
            String requete = "SELECT m.* FROM membre m, participantcours p where m.id=p.membre_id AND p.cour_id="+idE;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Membre m = new Membre();
                m.setId(rs.getInt("id"));
                m.setNom(rs.getString("nom"));
                m.setPrenom(rs.getString("prenom"));
                m.setDate_naissance(rs.getDate("date_naissance"));
                m.setGenre(Membre.Genre.valueOf(rs.getString("genre")));
                m.setTel(rs.getString("numero_tel"));
                m.setEmail(rs.getString("email"));
                m.setPassword(rs.getString("password"));
                m.setImage(rs.getString("image"));
                m.setRole(Membre.Role.valueOf(rs.getString("role")));
                m.setBan_duration(rs.getInt("ban_duration"));
                membreList.add(m);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return membreList;
    }

    @Override
    public int verifInscrit(int idM,int idC) {
        int nbLigne=0;
        try {
            String requeteDs = "SELECT count(id) as nbLigne FROM participantcours where membre_id="+idM+" AND cour_id="+idC;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            if (rs.next()) {
                if(rs.getInt("nbLigne")>0){
                    nbLigne= 0;
                }else nbLigne= 1;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nbLigne;
        
    }
}
