/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.gamingroom.chat.server;

/**
 *
 * @author Dah
 */

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tn.gamingroom.outils.Env;


public class ServerApplication extends Application {
	public static ArrayList<Thread> threads;
        private static final int port=Env.getPort();
	public static void main(String[] args){
		launch();
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		threads = new ArrayList<Thread>();
		primaryStage.setTitle("GamingRoom Chat Server");
		try {
                    Server server = new Server(port);
                    Thread serverThread = (new Thread(server));
                    serverThread.setName("Server Thread");
                    serverThread.setDaemon(true);
                    serverThread.start();
                    threads.add(serverThread);
                    /* Change the view of the primary stage */
                    primaryStage.hide();
                    primaryStage.setScene(makeServerUI(server));
                    primaryStage.show();
		}
		catch (Exception e) {
					// TODO Auto-generated catch block
					
		}
		
	}

	
	public Scene makeServerUI(Server server){
		/* Make the root pane and set properties */
		GridPane rootPane = new GridPane();
		rootPane.setAlignment(Pos.CENTER);
		rootPane.setPadding(new Insets(20));
		rootPane.setHgap(10);
		rootPane.setVgap(10);
		
		/* Make the server log ListView */
		Label logLabel = new Label("Server Log");
		ListView<String> logView = new ListView<String>();
		ObservableList<String> logList = server.serverLog;
		logView.setItems(logList);
		
		/* Make the client list ListView */
		Label clientLabel = new Label("Clients Connected");
		ListView<String> clientView = new ListView<String>();
		ObservableList<String> clientList = server.clientNames;
		clientView.setItems(clientList);
		
		/* Add the view to the pane */
		rootPane.add(logLabel, 0, 0);
		rootPane.add(logView, 0, 1);
		rootPane.add(clientLabel, 0, 2);
		rootPane.add(clientView, 0, 3);
		
		/* Make scene and return it,
		 * Scene has constructor (Parent, Width, Height)
		 *  */
		return new Scene(rootPane, 400, 600);
	}
}