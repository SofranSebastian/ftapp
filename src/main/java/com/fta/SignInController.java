package com.fta;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class SignInController {

    private JSONArray jarr = new JSONArray();

    @FXML
    private TextField username, password;

    @FXML
    private void switchToStartUpPage() throws IOException, ParseException {
        App.setRoot("primary");
    }

    @FXML
    private void logIn() throws IOException {

        JSONObject temp;
        JSONParser jp = new JSONParser();

        try {
            FileReader file1 = new FileReader("users.json");
            jarr = (JSONArray) jp.parse(file1);

            if (jarr != null) {

                int size = jarr.size();
                int flag = 0;

                for (int i = 0; i < size; i++) {

                    temp = (JSONObject) jarr.get(i);
                    System.out.println(temp);

                    if (temp.get("User").equals(username.getText())) {

                        if (temp.get("Pass").equals(password.getText())) {
                            if (temp.get("Type").equals("Coach")) {
                                App.setRoot("coachWelcome");
                            }
                            else if (temp.get("Type").equals("Customer")) {
                                App.setRoot("customerWelcome");
                            }
                        } else {
                            Alert a = new Alert(Alert.AlertType.WARNING);
                            a.setContentText("Wrong password!Try again");
                            a.show();
                        }
                        flag = 1;
                    }
                }

                if (flag == 0) {
                    Alert a = new Alert(Alert.AlertType.WARNING);
                    a.setContentText("Register first!");
                    a.show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
