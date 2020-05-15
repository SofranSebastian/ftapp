package com.fta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Scanner;

public class CustomerController {

    @FXML
    private ChoiceBox<String> choiceBox;
    private int flag = 0;

    public JSONArray jarr = new JSONArray();

    private String fileName;

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

        String weight;
        weight = (String) temp.get("Weight");
        System.out.println("" +weight);

        //Getting the time period input from GUI
        int tp = Integer.parseInt(timePeriod.getText());

        //Getting the desired weight input from GUI
        int dw = Integer.parseInt(desiredWeight.getText());

        //Getting the actual weight of the user
        int aw = Integer.parseInt(weight);

        int differenceWeight = aw - dw;
        int ans = (differenceWeight)/(tp);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Desired Body Plan");
        a.setHeaderText("Below you can see how much you need to work on your body until you reach your goal.");
        a.setContentText("The goal for a brand new day is: " + ans + "kilogram/day");
        a.show();
    }

    @FXML
    private TextField burnedCalories;

    private File newFile;

    @FXML
    private void showTotalCalories() throws IOException {

        JSONParser jp = new JSONParser();
        JSONObject temp;
        try{
            FileReader file = new FileReader("userUnique.json");
            jarr = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        temp = (JSONObject) jarr.get(0);

       fileName = "calories/calories_" + temp.get("User")+".txt";

       newFile = new File(fileName);

        if(newFile.createNewFile()){
            System.out.println("File created");
        }else{
            System.out.println("Already created");
        }

        int noCalories = 0;

        try{
            Scanner scannerText = new Scanner(newFile);

            while(scannerText.hasNextLine()){
                noCalories = scannerText.nextInt();
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Here is your journey champ!");
        a.setHeaderText("Total amount of burned calories");
        a.setContentText("Until now you burned " + noCalories + " calories");
        a.show();
    }

    @FXML
    private void caloriesToBeAdded(){

        int noCalories = 0;

        try{
            Scanner scannerText = new Scanner(newFile);

            while(scannerText.hasNextLine()){
                noCalories = scannerText.nextInt();
            }
            scannerText.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        int newNoCalories;
        int inputCalories = Integer.parseInt(burnedCalories.getText());;

        newNoCalories = noCalories + inputCalories;

        String newNoCaloriesString = String.valueOf(newNoCalories);

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Never give up champ!");
        a.setHeaderText("New amount of calories burned");
        a.setContentText("Today you burned " + inputCalories + " calories");
        a.show();

        try{
            FileWriter fw = new FileWriter(newFile);
            fw.write(newNoCaloriesString);
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void logOut() throws IOException{
        App.setRoot("signIn");
    }

    @FXML
    private TextField coachUsername, message;
    private JSONArray jrr = new JSONArray();

    @FXML
    private void send()
    {
        JSONParser jp = new JSONParser();
        JSONObject temp;
        try{
            FileReader file = new FileReader("userUnique.json");
            jarr = (JSONArray) jp.parse(file);
        }catch(Exception e){
            e.printStackTrace();
        }
        temp = (JSONObject) jarr.get(0);

        String userCustomer = (String) temp.get("User");

        JSONObject jo = new JSONObject();
        jp = new JSONParser();

        try {
            FileReader file = new FileReader("messagesCoaches.json");
            jrr = (JSONArray) jp.parse(file);
        } catch(Exception e) {
            e.printStackTrace();
        }

        jo.put("User", userCustomer);
        jo.put("Coach", coachUsername.getText());
        jo.put("Message", message.getText());

        jrr.add(jo);

        try {
            FileWriter file = new FileWriter("messagesCoaches.json");
            file.write(jrr.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Request");
        a.setHeaderText("Your request has been sent");
        a.setContentText("The coach will see it and analyse it");
        a.show();
    }

    @FXML
    private void inbox(){
        JSONParser jp = new JSONParser();
        JSONArray jarr1 = new JSONArray();
        JSONObject temp;

        try{
            FileReader file = new FileReader("userUnique.json");
            jarr1 = (JSONArray) jp.parse(file);
            file.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        temp = (JSONObject) jarr1.get(0);

        String customerUsername = (String) temp.get("User");

        try{
            FileReader file = new FileReader("messagesCustomers.json");
            jarr1 = (JSONArray) jp.parse(file);
            file.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        String messageToDisplay = "";

        String coachUsername, coachMessage, coachStatus;

        for(int i = 0 ; i < jarr1.size() ; i++ ) {

            temp = (JSONObject) jarr1.get(i);

            if(temp.get("Customer").equals(customerUsername)) {
                if(temp.get("Status").equals("Proposal")){
                    coachUsername = (String) temp.get("Coach");
                    coachMessage = (String) temp.get("Message");
                    messageToDisplay = messageToDisplay + "From: " + coachUsername + "\nProposal: " + coachMessage + "\n";
                } else {
                    coachUsername = (String) temp.get("Coach");
                    coachMessage = (String) temp.get("Message");
                    coachStatus = (String) temp.get("Status");
                    messageToDisplay = messageToDisplay + "From: " + coachUsername + "\nResponse: " + coachMessage + "\nTraining Request: " + coachStatus + "\n";
                }
            }


        }


        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle("Notifications");
        a.setHeaderText("Training Requests Responses/Proposals");
        a.setContentText(messageToDisplay);
        a.show();
    }
}
