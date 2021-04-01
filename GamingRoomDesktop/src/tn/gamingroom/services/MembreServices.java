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
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.interfaces.IMembre;
import tn.gamingroom.outils.MyConnection;
import java.util.Scanner;

/**
 *
 * @author Sonia
 */
public class MembreServices implements IMembre<Membre> {

    @Override
    public void ajouterMembre(Membre m) {
        try {
            String requete = "INSERT INTO membre(nom,prenom,date_naissance,genre,numero_tel,email,password,image,role,active)" + "VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setDate(3, m.getDate_naissance());
            ps.setString(4, m.getGenre().toString());
            ps.setString(5, m.getTel());
            ps.setString(6, m.getEmail());
            ps.setString(7, BCrypt.hashpw(m.getPassword(), BCrypt.gensalt()));
            ps.setString(8, m.getImage());
            ps.setString(9, m.getRole().toString());
            ps.setBoolean(10, true);
            ps.executeUpdate();
            System.out.println("Membre ajouté ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void ajouterCoach(Membre m) {
        try {
            String requete = "INSERT INTO membre (nom,prenom,date_naissance,genre,numero_tel,email,password,image,role,description,active)" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = new MyConnection().getCnx().prepareStatement(requete);
            ps.setString(1, m.getNom());
            ps.setString(2, m.getPrenom());
            ps.setDate(3, m.getDate_naissance());
            ps.setString(4, m.getGenre().toString());
            ps.setString(5, m.getTel());
            ps.setString(6, m.getEmail());
            ps.setString(7, BCrypt.hashpw(m.getPassword(), BCrypt.gensalt()));
            ps.setString(8, m.getImage());
            ps.setString(9, m.getRole().toString());
            ps.setString(10, m.getDescription());
            ps.setBoolean(11, false);
            ps.executeUpdate();
            System.out.println("Coach ajouté ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sumprimerMembres(Membre m) {
        try {
            String requete = "DELETE FROM membre where id=? AND role != 'Admin'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, m.getId());
            pst.executeUpdate();
            System.out.println("Membre supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierMembres(Membre m) {
        try {
            String requete = "UPDATE membre SET nom=?,prenom=?,date_naissance=?,genre=?,numero_tel=?,email=?,password=?,image=? WHERE id=? and role != 'Admin'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getPrenom());
            pst.setDate(3, m.getDate_naissance());
            pst.setString(4, m.getGenre().toString());
            pst.setString(5, m.getTel());
            pst.setString(6, m.getEmail());
            pst.setString(7, m.getPassword());
            pst.setString(8, m.getImage());
            pst.setInt(9, m.getId());
            pst.executeUpdate();
            System.out.println("Modification membre avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifierMembreParAdmin(Membre m) {
        try {
            String requete = "UPDATE personne SET point=?,active=?,ban_duration=?,last_timeban=? WHERE id=? AND role != 'Admin' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, m.getPoint());
            pst.setBoolean(2, m.getActive());
            pst.setInt(3, m.getBan_duration());
            pst.setDate(4, m.getLast_timeban());
            pst.setInt(5, m.getId());
            pst.executeUpdate();
            System.out.println("Modification membre avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public List<Membre> DisplayMembres() {
        List<Membre> membreList = new ArrayList<>();
        try {
            String requete = "SELECT * FROM membre where role != 'Admin'";
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
                m.setLast_timeban(rs.getDate("last_timeban"));
                membreList.add(m);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return membreList;
    }

    @Override
    public Membre Login(String email, String password) {

        try {
            String requete = "Select * from membre where email='" + email + "' ";
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                String pswd = rs.getString("password");
                if (!BCrypt.checkpw(password, pswd)) {
                    return null;
                }
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
                m.setLast_timeban(rs.getDate("last_timeban"));
                return m;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Membre> RechercherMembres(String x) {
        ArrayList<Membre> ListMembre = new ArrayList<>();
        try {
            String requete = "Select * from membre where email like '%" + x + "%' or nom like '%" + x + "%' or prenom like '%" + x + "%'";
            PreparedStatement st = MyConnection.getInstance().getCnx().prepareStatement(requete);
            ResultSet rs = st.executeQuery();
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
                m.setLast_timeban(rs.getDate("last_timeban"));

                ListMembre.add(m);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        if (ListMembre.isEmpty()) {
            System.out.println("NOT FOUND");
        }
        return ListMembre;
    }

    @Override
    public ArrayList<Membre> TrierParId() {

        ArrayList<Membre> ListMembre = new ArrayList<>();
        try {

            String requete = "select * from membre where role != 'Admin' ORDER BY id DESC ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet rs = pst.executeQuery(requete);

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
                m.setLast_timeban(rs.getDate("last_timeban"));

                ListMembre.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMembre;
    }

    @Override
    public void fPwd(int id) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter votre num tel");

        String tel = myObj.nextLine();

        System.out.println("Enter votre email");

        String email = myObj.nextLine();

        try {
            String requete = "SELECT password,email FROM membre where email= '" + email + "' and numero_tel='" + tel + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
                System.out.println("Enter Un nouveau mdp");

                String mdp1 = myObj.nextLine();
                System.out.println("ReEnter votre mdp");

                String mdp2 = myObj.nextLine();
                if (mdp1.equals(mdp2)) {
                    try {
                        requete = "update membre set password = ? where email=?";
                        PreparedStatement ps2 = new MyConnection().getCnx().prepareStatement(requete);

                        ps2.setString(2, rs.getString("email"));
                        ps2.setString(1, BCrypt.hashpw(mdp1, BCrypt.gensalt()));

                        int b = ps2.executeUpdate();
                        if (b > 0) {
                            System.out.println("PWD changé");
                        } else {
                            System.out.println("Réessayer");
                        }

                    } catch (SQLException ex) {
                        System.err.println(ex.getMessage());
                    }
                }
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public String findById(int idM) {
        String s = "";
        try {
            String requete = "SELECT * FROM membre where id=" + idM;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                s += rs.getString("prenom") + " " + rs.getString("nom");

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    @Override
    public Membre getMemberById(int idM) {
        Membre m = new Membre();
        try {
            String requete = "SELECT * FROM membre where id=" + idM;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if(rs.next()) {
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
                m.setLast_timeban(rs.getDate("last_timeban"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m;
    }

    @Override
    public List<Membre> getListeParticipants(int idE) {
        List<Membre> membreList = new ArrayList<>();
        try {
            String requete = "SELECT m.* FROM membre m, participant p where m.id=p.member_id AND p.evenement_id="+idE;
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
                m.setLast_timeban(rs.getDate("last_timeban"));
                membreList.add(m);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return membreList;
    }

}
