///**
// *  Copyright (c) 2015 Steve Hannah
// *
// *  Permission is hereby granted, free of charge, to any person obtaining a copy
// *  of this software and associated documentation files (the "Software"), to deal
// *  in the Software without restriction, including without limitation the rights
// *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// *  copies of the Software, and to permit persons to whom the Software is
// *  furnished to do so, subject to the following conditions:
// *
// *  The above copyright notice and this permission notice shall be included in
// *  all copies or substantial portions of the Software.
// *
// *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// *  THE SOFTWARE.
// */
package com.esprit.gamingroom;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.rad.models.Entity;
import com.codename1.rad.nodes.ActionNode;
import com.codename1.rad.nodes.ViewNode;
import static com.codename1.rad.ui.UI.action;
import static com.codename1.rad.ui.UI.actions;
import static com.codename1.rad.ui.UI.icon;
import com.codename1.rad.ui.chatroom.ChatBubbleView;
import com.codename1.rad.ui.chatroom.ChatRoomView;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.esprit.gamingroom.gui.Reaction.HomeForm;
import com.esprit.gamingroom.gui.Reaction.HomeForm;
import com.esprit.gamingroom.utils.Statics;
import java.util.Arrays;
import java.util.Date;
import com.codename1.io.websocket.WebSocket;
import com.codename1.io.websocket.WebSocketState;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
public class ChatControllerForm extends Form{

    public static WebSocket sock;

     public static final ActionNode send = action(icon(FontImage.MATERIAL_SEND));

        private Form current;
    Container chatContainer;
    public static final String SERVER_URL = Statics.WEBSOCKET_URL;
    Dialog ip ;
    boolean sendUserName=false;
    public ChatControllerForm() {
        ip= new InfiniteProgress().showInifiniteBlocking();
        start();
       this.setTitle("GamingRoomChat");
        this.setLayout(new BorderLayout());
        
        Container south = new Container();
        final TextField tf = new TextField();
        Button send = new Button(new Command("") {

            @Override
            public void actionPerformed(ActionEvent evt) {
                if (sock.getReadyState() == WebSocketState.OPEN) {
                    if(!sendUserName){
                        
                        sock.send("Dah");
                        sendUserName=true;
                    }
                    sock.send(tf.getText());
                    tf.setText("");
                } else {
                    Dialog.show("", "The socket is not open", "OK", null);
                }
                
            }
             
        });
        send.setMaterialIcon(FontImage.MATERIAL_SEND);
        chatContainer = new Container();
        chatContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        south.addComponent(tf);
        south.addComponent(send);
        this.addComponent(BorderLayout.SOUTH, south);
        this.addComponent(BorderLayout.CENTER, chatContainer);
        this.setFormBottomPaddingEditingMode(true);
        
    }

    /**
     * Creates a view model for the chat room.
     *
     * @return
     */
    private Entity createViewModel() {
        ChatRoomView.ViewModel room= new ChatRoomView.ViewModel();

        ChatBubbleView.ViewModel message = new ChatBubbleView.ViewModel();
     
        return room;
    }
  void setUset() {
        if (sock.getReadyState() == WebSocketState.OPEN) {
                    System.out.println("Open");
                    sock.send("Dah");
                } else {
                    System.out.println("Closed");
                    Dialog.show("Dialog", "Loading chat...", "OK", null);
                    sock.reconnect();
                }
    }
      public void start() {

        System.out.println("About to start socket");

        sock = new WebSocket(SERVER_URL) {

            @Override
            protected void onOpen() {
                System.out.println("In onOpen");
                System.out.println("Ready state: " + sock.getReadyState());
                ip.dispose();

            }

            @Override
            protected void onClose(int statusCode, String reason) {
                System.out.println("Closing: " + sock.getReadyState());
                Display.getInstance().callSerially(new Runnable() {
                    public void run() {
                    }
                });

            }

            @Override
            protected void onMessage(final String messageTxt) {
                System.out.println("Received message " + messageTxt);
                System.out.println("Ready state: " + sock.getReadyState());
                Display.getInstance().callSerially(new Runnable() {

                    
                    public void run() {
                        if (chatContainer == null) {
                            return;
                        }
                        SpanLabel label = new SpanLabel();
                        label.setText(messageTxt);
                        chatContainer.addComponent(label);
                        chatContainer.animateHierarchy(100);
                    }

                });
            }

            @Override
            protected void onError(Exception ex) {

                if (sock == null) {
                    System.out.println("Error while socket is null: " + ex.getMessage());
                } else {

                    System.out.println("Ready state: " + sock.getReadyState());
                    System.out.println("in onError " + ex.getMessage());
                    //Log.e(ex);
                }
            }

            @Override
            protected void onMessage(byte[] message) {
                System.out.println("Received bytes " + message.length);
                System.out.println(Arrays.toString(message));

            }

        }.autoReconnect(10000);

        System.out.println("Sending connect");
        System.out.println("Ready State: " + sock.getReadyState());
        sock.connect();
        setUset();

    }

    public void stop() {
    }

    public void destroy() {
    }
}
