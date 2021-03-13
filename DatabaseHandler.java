package sample;
import java.sql.*;

/*Подключение к базе данных*/

public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:postgresql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser(User user){
        //Вставляем в табличку USER_Table данные
        String insert = "INSERT INTO " + Const.USER_Table + "("  + Const.USERS_FIRSTNAME + ","
                + Const.USERS_LASTNAME +"," + Const.USERS_USERNAME + "," + Const.USERS_PASSWORD + ","
                + Const.USERS_LOCATION + "," + Const.USERS_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";

        //подключаемся к базе данных
        try {
            PreparedStatement prSt= getDbConnection().prepareStatement(insert); //передаем готовый запрос insert

            prSt.setString(1, user.getFirstname());                  //передаем данные в базу данных
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getUsername());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getLocation());
            prSt.setString(6, user.getGender());

            prSt.executeUpdate();       //обновить базу данных
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user){        //возвращаем пользователя из базы данных
        ResultSet resSet=null;
        String select = "SELECT * FROM " + Const.USER_Table + " WHERE " + Const.USERS_USERNAME + "=? AND "+ Const.USERS_PASSWORD + "=?";
        try {
            PreparedStatement prSt= getDbConnection().prepareStatement(select); // и передаем готовый запрос insert

            prSt.setString(1, user.getUsername());                  //передаем данные в базу данных
            prSt.setString(2, user.getPassword());
           resSet = prSt.executeQuery();       //get data

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return resSet;
    }
}
