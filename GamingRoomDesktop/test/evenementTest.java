
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import tn.gamingroom.entities.Evenement;
import tn.gamingroom.outils.MyConnection;
import tn.gamingroom.services.EvenementService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asus
 */
public class evenementTest {

    public static void main(String[] args) {
//        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-mm-aaaa");
        Date d = new Date(System.currentTimeMillis());
        EvenementService es = new EvenementService();
        Evenement e = new Evenement();
        MyConnection mc = MyConnection.getInstance();
        //MyConnection mc1 = MyConnection.getInstance();

    

    }

}
