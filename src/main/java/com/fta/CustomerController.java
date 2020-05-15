package com.fta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class CustomerController {

    @FXML
    private ChoiceBox<String> choiceBox;
    private int flag = 0;

    private SignInController getData = new SignInController();

    public JSONArray jarr = new JSONArray();



    @FXML
    private void selectMuscleArea() throws IOException {
        choiceBox.setValue("Chest");

        if (flag == 0) {
            choiceBox.getItems().addAll("Chest", "Back", "Arms", "Legs", "Abs");
            flag = 1;
        }

    }

    @FXML
    public void showTraining()
    {
        if(choiceBox.getValue().equals("Chest")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Chest Workout");
            a.setHeaderText("Building a Bigger Chest");
            a.setContentText(   "4x10 Dumbbell Squeeze Press\n" +
                                "4x5 Decline press-up\n" +
                                "4x15 Chest dips"   );
            a.show();
        }else if(choiceBox.getValue().equals("Back")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Back Workout");
            a.setHeaderText("Massive Back Workout");
            a.setContentText("3x(6-8) Deadlifts\n"+
                            "3x(12-15) Stiff Arm Pulldown\n"+
                            "3x8 Dumbbell Rows");
            a.show();
        }else if(choiceBox.getValue().equals("Arms")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Arms Workout");
            a.setHeaderText("Reduce Flabby Arms");
            a.setContentText("2x30 Reverse Curl\n"+
                    "3x15 Seated Tricep Press\n"+
                    "3x10 Tricep Dumbbell Kickback");
            a.show();
        }else if(choiceBox.getValue().equals("Legs")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Legs Workout");
            a.setHeaderText("Fat Burning Leg ");
            a.setContentText("2x60 Banded Bridge\n"+
                    "3x45 Burpees\n"+
                    "3x20 Tip-Toe Squat");
            a.show();
        }else if(choiceBox.getValue().equals("Abs")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Abs Workout");
            a.setHeaderText("Total Abs Defined");
            a.setContentText("3x30 Reverse Crunches\n"+
                    "4x20 Sitting Twists\n"+
                    "3x35 Butterfly Sit-Ups");
            a.show();
        }
    }

    @FXML
    private TextField desiredWeight, timePeriod;

    @FXML
    private void calculateDesiredWeight(){

        JSONParser jp = new JSONParser();
        JSONObject temp;
        try{
            FileReader file = new FileReader("userUnique.json");
            jarr = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        temp = (JSONObject) jarr.get(0);

        String nustiu;
        nustiu = (String) temp.get("Weight");
        System.out.println("" +nustiu);

        //Getting the time period input from GUI
        int tp = Integer.parseInt(timePeriod.getText());

        //Getting the desired weight input from GUI
        int dw = Integer.parseInt(desiredWeight.getText());

        //Getting the actual weight of the user
        int aw = Integer.parseInt(nustiu);

        int differenceWeight = aw - dw;
        int ans = (differenceWeight)/(tp);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Desired Body Plan");
        a.setHeaderText("Below you can see how much you need to work on your body until you reach your goal.");
        a.setContentText("The goal for a brand new day is: " + ans + "kilogram/day");
        a.show();
    }



    }
