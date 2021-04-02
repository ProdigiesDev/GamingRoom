/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.lang.reflect.Member;
import java.sql.Date;
import java.sql.Timestamp;
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
    public int ajouterMembre(Membre m) {
        int nbAjoutMembre = 0;
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
            nbAjoutMembre
                    = ps.executeUpdate();

            System.out.println("Membre ajouté ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbAjoutMembre;
    }

    @Override
    public int ajouterCoach(Membre m) {
        int nbAjoutCoach = 0;
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
            nbAjoutCoach = ps.executeUpdate();
            System.out.println("Coach ajouté ");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbAjoutCoach;
    }

    @Override
    public int sumprimerMembres(Membre m) {
        int nbSuppMembre = 0;
        try {
            String requete = "DELETE FROM membre where id=? AND role != 'Admin'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, m.getId());
            nbSuppMembre = pst.executeUpdate();
            System.out.println("Membre supprimé");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbSuppMembre;
    }

    @Override
    public int modifierMembres(Membre m) {
        int nbModifierMembre = 0;
        try {
            String requete = "UPDATE membre SET nom=?,prenom=?,date_naissance=?,genre=?,numero_tel=?,email=?,image=? WHERE id=? and role != 'Admin'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, m.getNom());
            pst.setString(2, m.getPrenom());
            pst.setDate(3, m.getDate_naissance());
            pst.setString(4, m.getGenre().toString());
            pst.setString(5, m.getTel());
            pst.setString(6, m.getEmail());
            pst.setString(7, m.getImage());
            pst.setInt(8, m.getId());
            nbModifierMembre = pst.executeUpdate();
            System.out.println("Modification membre avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbModifierMembre;
    }

    @Override
    public int modifierMembreParAdmin(Membre m) {
        int nbModifierParAdmin = 0;
        try {
            String requete = "UPDATE membre SET point=?,active=?,ban_duration=?,last_timeban=? WHERE id=? AND role != 'Admin' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setInt(1, m.getPoint());
            pst.setBoolean(2, m.getActive());
            pst.setInt(3, m.getBan_duration());
            pst.setTimestamp(4, m.getLast_timeban());
            pst.setInt(5, m.getId());
            nbModifierParAdmin = pst.executeUpdate();
            System.out.println("Modification membre avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbModifierParAdmin;

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
                m.setPoint(rs.getInt("point"));
                m.setActive(rs.getBoolean("active"));
                m.setBan_duration(rs.getInt("ban_duration"));
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
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
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
                return m;
            }
            System.out.println("email not found");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Membre> RechercherMembres(String x) {
        ArrayList<Membre> ListMembre = new ArrayList<>();
        try {
            String requete = "Select * from membre  where  role!='Admin' and  email like '%" + x + "%' or nom like '%" + x + "%' or prenom like '%" + x + "' ";
            System.out.println("aa: "+x);
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
                    System.out.println("pff: "+rs.getString("email"));
                    m.setPassword(rs.getString("password"));
                    m.setImage(rs.getString("image"));
                    m.setRole(Membre.Role.valueOf(rs.getString("role")));
                    m.setPoint(rs.getInt("point"));
                    m.setDescription("description");
                    m.setActive(rs.getBoolean("active"));
                    m.setBan_duration(rs.getInt("ban_duration"));
                    m.setLast_timeban(rs.getTimestamp("last_timeban"));
                    System.out.println("x:" +x);
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

//            String requete = "select * from membre where role != 'Admin' ORDER BY id DESC ";
            String requete = "select * from membre where role != 'Admin' ORDER BY nom ";
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
                m.setLast_timeban(rs.getTimestamp("last_timeban"));

                ListMembre.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return ListMembre;
    }

    @Override
    public int forgotPassword(String email, String newpassword) {
        int res = 0;

        try {
            String requete = "SELECT password,email FROM membre where email= '" + email + "' ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {

                try {
                    requete = "update membre set password = ? where email=?";
                    PreparedStatement ps2 = new MyConnection().getCnx().prepareStatement(requete);

                    ps2.setString(2, rs.getString("email"));
                    ps2.setString(1, BCrypt.hashpw(newpassword, BCrypt.gensalt()));

                    res = ps2.executeUpdate();
                    if (res > 0) {
                        System.out.println("PWD changé");
                    } else {
                        System.out.println("Réessayer");
                    }

                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return res;
    }

    @Override
    public int lastId() {
        int id = 0;
        try {
            String requete = "SELECT nvl(max(id),0) FROM membre ";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.next()) {

                id = rs.getInt(1);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return id;
    }

    @Override
    public String autotext() {
        String text = " ";
        try {

            String requete = "Select * from membre";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                text = rs.getString("email");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return text;

    }

    @Override
    public int getPointParid(int id) {
        int point = 0;
        try {

            String requete = "select point from membre where id = '" + id + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            System.out.println("id" + id);
            if (rs.next()) {
                point = rs.getInt("point");
                System.out.println("Points" + rs.getInt("point"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return point;
    }

    @Override
    public int activerCompte(Membre m) {
        int nbactive = 0;
        try {
            String requete = "UPDATE membre SET active=? WHERE id=? AND role != 'Admin' ";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setBoolean(1, m.getActive());
            pst.setInt(2, m.getId());
            nbactive = pst.executeUpdate();
            System.out.println("Modification avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbactive;
    }

    @Override
    public int getBandurParid(int id) {
        int ban_dur = 0;
        try {

            String requete = "select * from membre where id = '" + id + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.next()) {
                ban_dur = rs.getInt("ban_duration");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return ban_dur;
    }

    @Override
    public String getDescParId(int id) {
        String desc = "";
        try {

            String requete = "select * from membre  where  id = '" + id + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.next()) {
                desc = rs.getString("description");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return desc;
    }

    @Override
    public String getEmailParId(int id) {
        String email = "";
        try {

            String requete = "select * from membre  where  id = '" + id + "'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            if (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return email;
    }

    @Override
    public int modifierMDPParMembre(int id, String nvmdp) {
        int nbModifierMembre = 0;
        try {
            String requete = "UPDATE membre SET password=? WHERE id='" + id + "'";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, BCrypt.hashpw(nvmdp, BCrypt.gensalt()));

            nbModifierMembre = pst.executeUpdate();
            System.out.println("Modification membre avec succées");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return nbModifierMembre;
    }

    @Override
    public Membre getById(int id) {
        try {
            String requete = "SELECT * FROM membre where id = " + id;
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
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
                return m;
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public Membre getMemberById(int idM) {
        Membre m = new Membre();
        try {
            String requete = "SELECT * FROM membre where id=" + idM;
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
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
                m.setPoint(rs.getInt("point"));
                m.setDescription("description");
                m.setActive(rs.getBoolean("active"));
                m.setBan_duration(rs.getInt("ban_duration"));
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m;
    }

    @Override
    public List<String> GetEmail() {
        List<String> email = new ArrayList<>();
        try {

            String requete = "select * from membre";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                email.add(rs.getString("email"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return email;
    }

    @Override
    public List<String> RechercherMembresProfil() {
        List<String> infos = new ArrayList<>();
        try {

            String requete = "select * from membre where role not like 'Admin' AND role not like 'Membre'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);

            while (rs.next()) {

                infos.add(rs.getString("email"));
                infos.add(rs.getString("nom"));
                infos.add(rs.getString("prenom"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return infos;
    }

    @Override
    public Membre getMembreByEmail(String email) {
        System.out.println("email "+email);
        Membre m = new Membre();
        try {
            String requete = "SELECT * FROM membre where email='" + email+"'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete);
            if (rs.next()) {
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
                m.setPoint(rs.getInt("point"));
                m.setDescription("description");
                m.setActive(rs.getBoolean("active"));
                m.setBan_duration(rs.getInt("ban_duration"));
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return m;
    }

    @Override
    public boolean EmailExiste(String email) {
        try {
            String requete = "select count(email) as em from membre  where  email='" + email+"'";
            Statement st = MyConnection.getInstance().getCnx()
                    .createStatement();
            ResultSet rs = st.executeQuery(requete); 
            
            if(rs.next()){
                return rs.getInt("em")>0;
            }
            
             
            
        } catch (SQLException ex) {
            Logger.getLogger(MembreServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
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
                m.setLast_timeban(rs.getTimestamp("last_timeban"));
                membreList.add(m);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return membreList;
    }


}
