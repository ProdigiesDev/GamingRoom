/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import tn.gamingroom.entities.Avis;
import tn.gamingroom.interfaces.IAvis;
import tn.gamingroom.outils.ApiCall;
import tn.gamingroom.outils.Env;
import tn.gamingroom.outils.MyConnection;

/**
 *
 * @author Dah
 */
public class AvisService implements IAvis {

    private Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public int ajouterAvis(Avis avis) {
        int nb = 0;
        HashMap<String, String> headers = new HashMap();
        String review=avis.getAvis().replaceAll(" ", "%25");
        System.out.println(review);
        headers.put("Accept", "application/json");
        HttpURLConnection conn = ApiCall.callApi("https://api.meaningcloud.com/sentiment-2.1?verbose=y&key=" + Env.getKeyMeaningcloudApi() + "&lang=en&txt=" + review + "&model=general", headers);

        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            JSONObject obj = new JSONObject(br.readLine());
            String reqAjouter = "insert into avis (avis,member_id,sentiment) values(?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(reqAjouter);
            ps.setString(1, avis.getAvis());
            ps.setInt(2, avis.getMember_id());
            System.out.println(obj.getString("score_tag"));
            ps.setString(3,  obj.getString("score_tag"));
            nb = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return nb;
    }

    @Override
    public List<Avis> getListAvis() {

        List<Avis> avis = new ArrayList();

        try {
            String req = "select * from avis order by id desc";
            Statement s = cnx.createStatement();
            ResultSet resultSet = s.executeQuery(req);
            while (resultSet.next()) {
                avis.add(new Avis(resultSet.getInt("id"), resultSet.getString("avis"), resultSet.getInt("member_id"), resultSet.getString("sentiment")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return avis;
    }

    public List<Avis> getPostivesAvis() {

        List<Avis> avis = new ArrayList();

        try {
            String req = "select * from avis where sentiment like('P%') order by id  desc";
            Statement s = cnx.createStatement();
            ResultSet resultSet = s.executeQuery(req);
            while (resultSet.next()) {
                avis.add(new Avis(resultSet.getInt("id"), resultSet.getString("avis"), resultSet.getInt("member_id"), resultSet.getString("sentiment")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return avis;
    }

    private String getAvisSentiment(String avis) {
        HashMap<String, String> headers = new HashMap();
        headers.put("Accept", "application/json");
        HttpURLConnection conn = ApiCall.callApi("https://api.meaningcloud.com/sentiment-2.1?verbose=y&key=" + Env.getKeyMeaningcloudApi() + "&lang=en&txt=" + avis + "&model=general", headers);
        try {
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP Error code : "
                        + conn.getResponseCode());
            }
            InputStreamReader in = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(in);
            JSONObject obj = new JSONObject(br.readLine());
            return obj.getString("score_tag");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return "";
    }

}
