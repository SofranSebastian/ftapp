package com.fta;

import java.io.IOException;
import javafx.fxml.FXML;

public class StartUpPageController {

    @FXML
    private void switchToSignUpCoach() throws IOException {
        App.setRoot("signUpCoach");
    }

    @FXML
    private void switchToSignUpCustomer() throws IOException {
        App.setRoot("signUpCustomer");
    }
}
