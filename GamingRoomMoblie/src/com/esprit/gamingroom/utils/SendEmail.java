///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.esprit.gamingroom.utils;
//
//import java.util.Properties;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author Sonia
// */
//public class SendEmail {
//     public static boolean sendMail(String recepient,String HeaderText,String text) throws MessagingException {
//        System.out.println("preparing to send email");
//        Properties properties = new Properties();
//        
//        properties.put("mail.smtp.auth","true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        properties.put("mail.smtp.port", "587");
//        
//        String myAccountEmail = "Gaming2020Room@gmail.com";
//        String password="gamingroom2020";
//        
//        Session session = Session.getDefaultInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(myAccountEmail, password);
//            }
//            
//        } );
//        Message message = prepareMessage(session,myAccountEmail,recepient,HeaderText,text);
//        Transport.send(message);
//        //System.out.println("message sent successfully");
//        return true;
//      
//        
//    }
//    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String HeaderText, String text){
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject(HeaderText);
//            message.setText(text);
//            return message;
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return null;
//    }
//    
//}
