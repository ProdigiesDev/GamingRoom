/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.gui.Chat;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;
import tn.gamingroom.chat.server.ServerApplication;
import tn.gamingroom.entities.Client;
import tn.gamingroom.entities.Membre;
import tn.gamingroom.outils.Env;
import tn.gamingroom.services.MembreServices;

/**
 * FXML Controller class
 *
 * @author Dah
 */
public class ChatController implements Initializable {

    @FXML
    private JFXListView<String> listChat;
    @FXML
    private JFXTextField msg;

    private ArrayList<Thread> threads= new ArrayList<Thread>();;
    
    private Client client;
    @FXML
    private static Label ngUsers;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        MembreServices membreServices=new MembreServices();
        Membre membre=membreServices.getById(2);
        try {
            client = new Client("", Env.getPort(), membre.getNom());
            
            listChat.setItems(client.chatLog);
            Thread clientThread = new Thread(client);
            clientThread.setDaemon(true);
            clientThread.start();
            threads.add(clientThread);
					
            }
	catch(ConnectException e){
                JOptionPane.showMessageDialog(null,"Invalid host name, try again");
                e.printStackTrace();
	} catch (IOException ex) {
               ex.printStackTrace();
        }
    }    

    private void envoyerMsg(ActionEvent event) {
        client.writeToServer(msg.getText());
	msg.clear();

    }

    @FXML
    private void keyPresed(KeyEvent event) {
         if (event.getCode().equals(KeyCode.ENTER)) {
            envoyerMsg(null);
        }
    }
    
    	
    
}
