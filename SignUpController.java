package sample;

/*Класс регистрации пользователя*/

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField signUpName;

    @FXML
    private TextField signUpLastName;

    @FXML
    private TextField SignUpCountry;

    @FXML
    private CheckBox signUpCheckBoxMale;

    @FXML
    private CheckBox signUpCheckBoxFemale;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {
            signUpNewUser();
 });

    }

    //передаем в базу данных зарегистрировавшегося пользователя
    private void  signUpNewUser(){
        DatabaseHandler dbHandler = new DatabaseHandler();
        String firstname = signUpName.getText();
        String lastname = signUpLastName.getText();
        String username = login_field.getText();
        String password = password_field.getText();
        String location = SignUpCountry.getText();
        String  gender = "";
       if (signUpCheckBoxMale.isSelected())
           gender = "Мужской";
       else
           gender="Женский";

       User user = new User(firstname, lastname, username, password, location, gender);
        dbHandler.signUpUser(user);

    }
}
