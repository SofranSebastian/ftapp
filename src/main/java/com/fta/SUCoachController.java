package com.fta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.*;
import java.util.*;

public class SUCoachController {

        public JSONArray jrr = new JSONArray();

        @FXML
        private TextField username, password, fullname, adresa, numartelefon, varsta;

        @FXML
        private void submitData() throws IOException, ParseException {

                JSONObject jo = new JSONObject();
                JSONParser jp = new JSONParser();

                try {
                        FileReader file = new FileReader("users.json");
                        jrr = (JSONArray) jp.parse(file);
                } catch(Exception e) {
                        e.printStackTrace();
                }

                jo.put("Type", "Coach");
                jo.put("User", username.getText());
                jo.put("Pass", password.getText());
                jo.put("Full Name", fullname.getText());
                jo.put("Adresa", adresa.getText());
                jo.put("Number", numartelefon.getText());
                jo.put("Age", varsta.getText());
                jrr.add(jo);

                if(checkAvailability(jo)==1) {
                        System.out.println("Already registered");
                }else {
                        try {
                                FileWriter file = new FileWriter("users.json");
                                file.write(jrr.toJSONString());
                                file.close();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                }
        }

        @FXML
        private int checkAvailability(JSONObject jsonObject) {

                int size = jrr.size();

                JSONObject temp;

                for(int i = 0; i <size - 1; i++) {

                        temp = (JSONObject) jrr.get(i);

                        if (temp.get("User").equals(jsonObject.get("User"))) {

                                Alert a = new Alert(Alert.AlertType.WARNING);
                                a.setContentText("Already registered. Press Back and Sign In");
                                a.show();
                                return 1;
                        }
                }
                return 0;
        }

        @FXML
        private void switchToStartUpPage() throws IOException {
            App.setRoot("primary");
        }

}


