package com.fta;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.jar.JarInputStream;

public class CoachController {

    JSONArray jarr1 = new JSONArray();
    JSONArray jarr2 = new JSONArray();

    @FXML
    private void logOut() throws IOException {
        App.setRoot("signIn");
    }

    private String userCustomer, customerMessage, userCoach;

    @FXML
    private void inbox()
    {
        JSONParser jp = new JSONParser();
        JSONObject temp;

        try{
            FileReader file = new FileReader("coachUnique.json");
            jarr1 = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }

        temp = (JSONObject) jarr1.get(0);

        userCoach = (String) temp.get("User");

        try{
            FileReader file = new FileReader("messagesCoaches.json");
            jarr1 = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }

        String messageToDisplay = "";

        for(int i = 0 ; i < jarr1.size() ; i++ ) {

            temp = (JSONObject) jarr1.get(i);

            if(temp.get("Coach").equals(userCoach)) {
                userCustomer = (String) temp.get("User");
                customerMessage = (String) temp.get("Message");
                messageToDisplay = messageToDisplay + "From: " + userCustomer + "\nMessage: " + customerMessage + "\n";
            }
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Notifications");
        a.setHeaderText("Training Requests from clients");
        a.setContentText(messageToDisplay);
        a.show();
    }

    @FXML
    private TextField cuUsr, status, cMsg;

    @FXML
    public void send(){
        JSONParser jp = new JSONParser();
        JSONObject temp;

        try{
            FileReader file = new FileReader("coachUnique.json");
            jarr1 = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        temp = (JSONObject) jarr1.get(0);

        String coachUsername = (String) temp.get("User");

        JSONObject jo = new JSONObject();
        jp = new JSONParser();

        try {
            FileReader file = new FileReader("messagesCustomers.json");
            jarr2 = (JSONArray) jp.parse(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        jo.put("Customer", cuUsr.getText());
        jo.put("Coach", coachUsername);
        jo.put("Message", cMsg.getText());
        jo.put("Status",status.getText());

        jarr2.add(jo);

        try {
            FileWriter file = new FileWriter("messagesCustomers.json");
            file.write(jarr2.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Reponse");
        a.setHeaderText("Your message has been sent");
        a.setContentText("The client will see it ");
        a.show();
    }

    @FXML
    private TextField cMsg2, cuUsr2;

    @FXML
    private void sendProposal(){
        JSONParser jp = new JSONParser();
        JSONObject temp;
        JSONArray jarr = null;
        JSONArray jarr2 = null;
        try{
            FileReader file = new FileReader("coachUnique.json");
            jarr = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        temp = (JSONObject) jarr.get(0);

        String coachUsername = (String) temp.get("User");

        JSONObject jo = new JSONObject();
        jp = new JSONParser();

        try {
            FileReader file = new FileReader("messagesCustomers.json");
            jarr2 = (JSONArray) jp.parse(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        jo.put("Customer", cuUsr2.getText());
        jo.put("Coach", coachUsername);
        jo.put("Status","Proposal");
        jo.put("Message", cMsg2.getText());

        jarr2.add(jo);

        try {
            FileWriter file = new FileWriter("messagesCustomers.json");
            file.write(jarr2.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Proposal");
        a.setHeaderText("Your proposal has been sent");
        a.setContentText("The customer will see it and analyse it");
        a.show();


    }
}
