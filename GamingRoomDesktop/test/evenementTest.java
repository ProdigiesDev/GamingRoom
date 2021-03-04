
import java.sql.Date;
import static java.sql.JDBCType.NULL;
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
        DateTimeFormatter f = DateTimeFormatter.ofPattern("1998-03-26");
        Date d = new Date(System.currentTimeMillis());
        EvenementService es = new EvenementService();
        Evenement e = new Evenement("comp1",d,d,"image",1,6,"description","lieu","yooutuube"); 
        MyConnection mc = MyConnection.getInstance();
        //MyConnection mc1 = MyConnection.getInstance();
        es.ajoutEvenement(e);

    

    }

}
