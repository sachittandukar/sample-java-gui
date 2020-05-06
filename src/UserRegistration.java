import Helpers.DBUtils;

import java.sql.*;

public class UserRegistration {
    private Connection con;

    public UserRegistration() {
        try {
            con = DBUtils.getDbConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    ResultSet get() {
        try {
            String select = "SELECT * FROM users";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    void insert(String firstName, String lastName, String username, String password,
                int age, String gender) {
        try {
            String insert = "INSERT INTO users (first_name, last_name, username, password, age, gender) " +
                    "VALUES (?,?,?,?,?,?)";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setInt(5, age);
            statement.setString(6, gender);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    void update(int id, String firstName, String lastName, String username, String password,
                int age, String gender) {
        try {
            String update = "UPDATE users SET first_name = ?, last_name=?, username =?,password = ?, " +
                    "age=?, gender =? WHERE  id = ?";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setInt(5, age);
            statement.setString(6, gender);
            statement.setInt(7, id);

            statement.executeUpdate();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void delete(int id){
        String delete = "DELETE FROM users WHERE id = ?";

        try {
            PreparedStatement statement = con.prepareStatement(delete);
            statement.setInt(1, id);
            statement.execute();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
