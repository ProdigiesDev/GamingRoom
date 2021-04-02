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
import tn.gamingroom.entities.Courslm;
import tn.gamingroom.interfaces.ICours;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author eyatr
 */
public class ServiceCours implements ICours {

    @Override
    public int ajouterCours(Cours c) {

        int nb_ajouter=0;
        try {
            String requete = "INSERT INTO cour(nomCours,description,nb_participant ,date_creation,tags,categorie_id,imagecours,lienYoutube,membre_id)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, c.getNomCours());
            pst.setString(2, c.getDescription());
            pst.setInt(3, c.getNb_participants());
            
            pst.setDate(4, c.getDate_creation());
            pst.setString(5, c.getTags());
            pst.setInt(6, c.getCategorie_id());
            pst.setString(7, c.getImage());
            pst.setString(8, c.getLienYoutube());
            pst.setInt(9, c.getMembre_id());
            nb_ajouter=pst.executeUpdate();
            
            if (nb_ajouter <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Cours ajouté");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_ajouter;

    }
    
   

    @Override
    public int supprimerCours(int id) {
        int nb_supprim=0;
        try {
            String requeteSP = "DELETE FROM cour where id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteSP);
            pst.setInt(1, id);
            nb_supprim=pst.executeUpdate();
            
            if (nb_supprim <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Cours supprimé");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_supprim;
    }
//TODO update image url
    @Override
    public int updateCours(Cours c) {
        int nb_up=0;
        try {
            
            String requeteUp = "UPDATE cour SET nomCours=?, description=? ,tags=? ,categorie_id=? ,nb_participant=?,imagecours=? , lienYoutube=? WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requeteUp);
            pst.setString(1, c.getNomCours());
            pst.setInt(8, c.getId());
            pst.setString(6, c.getImage());
            pst.setString(7, c.getLienYoutube());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getTags());
            pst.setInt(4, c.getCategorie_id());
            pst.setInt(5, c.getNb_participants());
            nb_up=pst.executeUpdate();
            if (nb_up <= 0) {
                System.out.println("Verifiez vos données");
            } else {
                System.out.println("Cours modifié");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb_up;
    }

    @Override
    public List<Cours> displayCours() {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "SELECT c.*,ca.nomcategorie from cour c , categorie ca where ca.idcat=c.categorie_id";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
               // c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
             
                    
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursList;
    }
    public List<Cours> displayCours(int member_id) {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "SELECT c.*,ca.nomcategorie from cour c , categorie ca where ca.idcat=c.categorie_id and membre_id="+member_id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
               // c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
             
                    
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursList;
    }
    @Override
    public List<Cours> displayCoursByCoach(int membre_id) {
        System.out.println("idM "+membre_id);
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "select * from cour  where membre_id="+membre_id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                Cours c = new Cours();
                //c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                //c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
             
                    
                CoursList.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         System.out.println("couList "+CoursList);
        return CoursList;
    }


    @Override
    public List searchCours(String x) {
        List<Cours> CoursListx = new ArrayList<>();
        try {
            String requete = "Select * from cour where nomCours like '%" + x + "%' or description like '%" + x + "%'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Cours c = new Cours();
                
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                //c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                
                CoursListx.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return CoursListx;
    }

    @Override
    public List trierCoursID() {
        ArrayList<Cours> listCours = new ArrayList<>();
        try {

            String requete = "select * from cour ORDER BY id DESC";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);
           
            while (rs.next()) {
                  Cours c= new Cours();

                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                //c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
                c.setCategorie_id(rs.getInt("categorie_id"));                
                listCours.add(c);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listCours;
    }

    @Override
    public Cours findById(int id) {
        Cours c = new Cours();
        try {
            String requete = "SELECT * FROM cour where id=" + id;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                //c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

    @Override
    public List<Cours> displayprefcours(int membre_id) {
        List<Cours> CoursList = new ArrayList<>();
        try {
            String requeteDs = "select * from cour where categorie_id in ( select categorie_id from categori_membre where membre_id='" + membre_id + "'  order by categorie_id asc)";
            //String requeteDs = "select * from cour where not EXISTS (select * from cours where idcat in (select id from categori_membre where membre_id=2)";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requeteDs);
            //ResultSet rs1 = st.executeQuery(requeteDs1);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
                CoursList.add(c);
            }

            requeteDs = "select * from cour where id not in( select id from cour where categorie_id in ( select categorie_id from categori_membre where membre_id='" + membre_id + "' ) ) ";
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
            rs = st.executeQuery(requeteDs);
            while (rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNomCours(rs.getString("nomCours"));
                c.setDescription(rs.getString("description"));
                c.setNb_participants(rs.getInt("nb_participant"));
                c.setMembre_id(rs.getInt("membre_id"));
                c.setDate_creation(rs.getDate("date_creation"));
                c.setTags(rs.getString("tags"));
                c.setCategorie_id(rs.getInt("categorie_id"));
                c.setImage(rs.getString("imagecours"));
                c.setLienYoutube(rs.getString("lienYoutube"));
                CoursList.add(c);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return CoursList;
    }
    
    
    @Override
    public List<String> AutocompleteSearch() {
        List<String> l = new ArrayList<>();
        try {
            String requete = "SELECT DISTINCT e.nomCours as nom from cour e where e.nomCours is not null";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                l.add(rs.getString("nom"));
            }
            requete = "SELECT DISTINCT e.description as description from cour e where e.description is not null ";
            st = MyConnection.getInstance().getCnx()
                    .createStatement();
            rs = st.executeQuery(requete);
            while (rs.next()) {
                l.add(rs.getString("description"));
            }
           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return l;
    }

}
