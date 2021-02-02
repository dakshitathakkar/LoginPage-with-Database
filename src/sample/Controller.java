package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.sql.*;

public class Controller {
    Connection con;
    PreparedStatement pst;
    ResultSet rs;

    @FXML
    private TextField txtuname;
    @FXML
    private Button btn;
    @FXML
    private PasswordField txtpass;


    public void login(ActionEvent actionEvent) {
        String uName = txtuname.getText();
        String pass = txtpass.getText();

        if (uName.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(null, "Blank Not Allowed");
            txtuname.setText("");
            txtpass.setText("");
            txtuname.requestFocus();
        }
        else if(!uName.matches("[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+")){
            JOptionPane.showMessageDialog(null,"Invalid email-id");
            txtuname.setText("");
            txtpass.setText("");
            txtuname.requestFocus();
        }

        else {
            try {
//                forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");

                pst = con.prepareStatement("SELECT * FROM user WHERE username=? and password=?");
                pst.setString(1, uName);
                pst.setString(2, pass);
                rs = pst.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Login Successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Login Failed");
                    txtuname.setText("");
                    txtpass.setText("");
                    txtuname.requestFocus();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
