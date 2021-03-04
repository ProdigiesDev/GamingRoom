/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.entities.Notification;
import tn.gamingroom.entities.ReactionEv;
import tn.gamingroom.interfaces.IEvenement;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Asus
 */
public class EvenementService implements IEvenement {

    @Override
    public void ajoutEvenement(Evenement t) {
        try {
            String requete = "INSERT INTO evenement(nomevent,datedeb,datefin,image,categorie_id,nbremax_participant,description,lieu,lienyoutube)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNomEvent());
            pst.setDate(2, t.getDateDeb());
            pst.setDate(3, t.getDateFin());
            pst.setString(4, t.getImage());
            pst.setInt(5, t.getCategorie_id());
            pst.setInt(6, t.getNbreMax_participant());
            pst.setString(7, t.getDescription());
            pst.setString(8, t.getLieu());
            pst.setString(9, t.getLienYoutube());

            pst.executeUpdate();
            System.out.println("Evenement inserée");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifierEvenement(Evenement t) {
        try {
            String requete = "UPDATE evenement SET nomevent=? , datedeb=? , datefin=? , image=? , categorie_id=? , nbremax_participant=? , description=? , lieu=? , lienyoutube=? WHERE idevent=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNomEvent());
            pst.setDate(2, t.getDateDeb());
            pst.setDate(3, t.getDateFin());
            pst.setString(4, t.getImage());
            pst.setInt(5, t.getCategorie_id());
            pst.setInt(6, t.getNbreMax_participant());
            pst.setString(7, t.getDescription());
            pst.setString(8, t.getLieu());
            pst.setString(9, t.getLienYoutube());
            pst.setInt(10, t.getIdevent());
            pst.executeUpdate();
            System.out.println("Evenement modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void suppressionEvenement(Evenement t) {
        try {
            String requete = "DELETE FROM evenement where idevent=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, t.getIdevent());
            int b = pst.executeUpdate();
            if (b <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Evenement supprimée");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> listerEvenement() {
        List<Evenement> evenementList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM evenement";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));
                evenementList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }

    @Override
    public void reagirEvenement(ReactionEv rE) {
        try {
            String requette = "INSERT INTO reactionev(interaction,commentaire,evenement_id,membre_id)"
                    + "VALUES (?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requette);
            pst.setInt(1, rE.getInteraction());
            pst.setString(2, rE.getCommentaire());
            pst.setInt(3, rE.getEvenement_id());
            pst.setInt(4, rE.getMembre_id());

            pst.executeUpdate();
            System.out.println("Réaction ajouté");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimerReacC(ReactionEv rE) {
        try {
            String requette = "DELETE FROM reactionev where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requette);
            pst.setInt(1, rE.getId());
            int b = pst.executeUpdate();
            if (b <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Reaction supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Evenement> chercherEvenement(String s) {
        ArrayList<Evenement> events = new ArrayList<>();
        try {
            String requette = "select * from evenement where nomevent like '%" + s + "%' or categorie_id =(select categorie_id from categorie where nomcategorie ='" + s + "') or lieu like '%" + s + "%'";

            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requette);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));
                events.add(e);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        if (events.isEmpty()) {
            System.out.println("Aucun résultat");
        }
        return events;
    }

    @Override
    public Evenement findById(int id) {
        Evenement e = new Evenement();
        try {
            String requete = "SELECT * FROM evenement where idevent=" + id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return e;
    }

    @Override
    public void sinscrirEvenement(int idE, int idM) {
        try {
            String requete = "SELECT COUNT(p.evenement_id) as nbE, e.nbremax_participant as nbMaxE FROM participant p, evenement e where p.evenement_id=" + idE + " and e.idevent= " + idE;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {

                if (rs.getInt("nbE") < rs.getInt("nbMaxE")) {
                    System.out.println("if2");
                    requete = "INSERT INTO participant(evenement_id,member_id,round) VALUES (?,?,?)";
                    PreparedStatement pst = MyConnection.getInstance().getCnx()
                            .prepareStatement(requete);
                    pst.setInt(1, idE);
                    pst.setInt(2, idM);
                    pst.setInt(3, 1);
                    pst.executeUpdate();
                    System.out.println("inscription effectuée");
                } else {
                    if (!this.repartitionDual(idE)) {
                        System.out.println("Evenement saturé");
                    }

                }
            } else {
                System.out.println("zzzzzzz");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean repartitionDual(int idE) {
        try {
            List<Integer> randomMember = new ArrayList<>();
            String requete = "select member_id from participant where evenement_id=" + idE;
            Statement st2 = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs2 = st2.executeQuery(requete);
            while (rs2.next()) {
                randomMember.add(rs2.getInt("member_id"));
            }
            Random rand = new Random();
            int randomElement;
            HashMap<Integer, String> randDuals = new HashMap<>();
            char dual = 'A';
            int i = 1;
            while (!randomMember.isEmpty()) {
                //randomElement = randomMember.get(rand.nextInt(randomMember.size()-1));
                int randomnumber = rand.nextInt(randomMember.size());
                randomElement = randomMember.get(randomnumber);

                String s = new String(dual + "");
                System.out.println("randomElement" + randomnumber);
                randDuals.put(randomElement, s);

                System.out.println(randomMember.remove(randomnumber));
                System.out.println("randomMember.size() " + randomMember.size());
                if (i % 2 == 0) {

                    dual = (char) (dual + 1);
                }
                i++;
            }
            randDuals.forEach((key, value) -> {
                System.out.println(key + " " + value);
            });
            for (Map.Entry<Integer, String> entry : randDuals.entrySet()) {
                try {
                    requete = "UPDATE participant SET duel = ? WHERE member_id = ? and evenement_id= ?";
                    PreparedStatement pst = MyConnection.getInstance().getCnx()
                            .prepareStatement(requete);
                    pst.setString(1, entry.getValue());
                    pst.setInt(2, entry.getKey());
                    pst.setInt(3, idE);
                    int b = pst.executeUpdate();
                    if (b <= 0) {
                        System.out.println("Un probème est survenu!");
                        return false;
                    } else {
                        //Notifier les participant que la répartition est fait
                        requete = "select m.prenom,m.nom from membre m, participant p where m.id=p.member_id and p.member_id <> " + entry.getKey() + " and p.duel ='" + entry.getValue() + "' ";
                        Statement st3 = MyConnection.getInstance().getCnx()
                                .createStatement();
                        ResultSet rs3 = st3.executeQuery(requete);
                        while (rs3.next()) {
                            NotificationService nS = new NotificationService();
                            nS.ajoutEvenement(new Notification(entry.getKey(), "Duel evenement :" + this.findById(idE).getNomEvent(), "Votre adversaire est : " + rs3.getString("prenom") + " " + rs3.getString("nom")));
                        }

                    }

                } catch (SQLException ex) {
                    Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public List<Evenement> triEvenement() {
        ArrayList<Evenement> evenementList = new ArrayList<>();
        try {

            String requete = "select * from evenement ORDER BY datedeb";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));
                evenementList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }

    @Override
    public List<Evenement> upComingEvents() {
        ArrayList<Evenement> evenementList = new ArrayList<>();
        try {

            String requete = "select * from evenement where datedeb "+java.time.LocalDate.now();
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);
            while (rs.next()) {
                Evenement e = new Evenement();
                e.setIdevent(rs.getInt("idevent"));
                e.setNomEvent(rs.getString("nomevent"));
                e.setDateDeb(rs.getDate("datedeb"));
                e.setDateFin(rs.getDate("datefin"));
                e.setImage(rs.getString("image"));
                e.setCategorie_id(rs.getInt("categorie_id"));
                e.setNbreMax_participant(rs.getInt("nbremax_participant"));
                e.setDescription(rs.getString("description"));
                e.setLieu(rs.getString("lieu"));
                e.setLienYoutube(rs.getString("lienyoutube"));
                evenementList.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return evenementList;
    }

}
