package sample;

/*Первое окно*/

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button authSignInButton;

    @FXML
    private Button LoginSignUpButton;

    @FXML
    void initialize() {
        authSignInButton.setOnAction(actionEvent -> {
            String loginText = login_field.getText().trim();
            String loginPassword = password_field.getText().trim();

            if (!loginText.equals("") && !loginPassword.equals(""))
                loginUser(loginText, loginPassword);

            else
                System.out.println("Вы не заполнили поля!");
        });

      //нажимае кнопку "Зарегистрироваться"
        LoginSignUpButton.setOnAction(actionEvent -> {
       openNewScene("/sample/signUp.fxml");
        });
    }

    //передаем логин и пароль в базу данных
    private void loginUser(String loginText, String loginPassword) {
    DatabaseHandler dbHandler = new DatabaseHandler(); //создаем объект класса, чтобы обращаться к его методам.
        User user = new User();     //создаем объект User
        user.setUsername(loginText); //получаем для него логин из введенного поля авторизации
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);

       int  counter =0;
       while (true){        //считаем залогиненых пользователей c таким логином и паролем
           try {
               if (!result.next()) break;
           } catch (SQLException e) {
               e.printStackTrace();
           }   counter++;  }

       if (counter >= 1) {                          //если такой пользователь есть, переход на второе окно
         openNewScene("/sample/app.fxml");
       }
       else {
           Shake userLoginAnim = new Shake(login_field);    //создаем объект класса Shake, в констурктор передается
           Shake userPassAnim = new Shake(password_field);

        userLoginAnim.playAnim();        //трясем поле логина
        userPassAnim.playAnim();        //трясем поле пароля
       }
    }

   //метод отрисовки окна
    public void openNewScene (String window){
        LoginSignUpButton.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();

    }
}