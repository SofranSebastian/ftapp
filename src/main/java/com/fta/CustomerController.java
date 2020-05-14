package com.fta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

public class CustomerController {

    @FXML
    private ChoiceBox<String> choiceBox;
    private int flag = 0;

    @FXML
    private void selectMuscleArea() throws IOException {
        choiceBox.setValue("Chest");

        if (flag == 0) {
            choiceBox.getItems().addAll("Chest", "Back", "Arms", "Legs", "Abs");
            flag = 1;
        }

        if(choiceBox.getValue().equals("Arms")){
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("baga flotari imputitule ca arati ca un cablu hdmi");
            a.show();
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

    }
