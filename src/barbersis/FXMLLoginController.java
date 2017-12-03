/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbersis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXPasswordField;
import com.mysql.jdbc.Driver;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Kandos
 */
public class FXMLLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField username;
     
    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXButton btnSignin;

    @FXML
    private JFXButton btnSignup;

    @FXML
    private Label inilabel;
    
    @FXML
    private void signIn(ActionEvent event) {
        Connection connection;
        PreparedStatement ps;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/barbersis?", "root", "");
            ps = connection.prepareStatement("SELECT * FROM `akun` WHERE `akunuser` = ? AND `akunpass` = ?");
            ps.setString(1, username.getText());
            ps.setString(2, password.getText());
            ResultSet result = ps.executeQuery();
            if (result.next()) {
                try {
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
            
            // Load the new fxml
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("FXMLBeranda.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            
            // Create new stage (window), then set the new Scene
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("BarberSis");
            stage.show();
            
        } catch (IOException e) {
            System.out.println("Failed to create new Window." + e);
        }
            } else {
                inilabel.setText("Salah!");
                password.setText("");
                username.requestFocus();
            }
        } catch (SQLException ex) {
            inilabel.setText("Gagal!");
        }
    }

    @FXML
    private void signUp(ActionEvent event) {
        String user = username.getText();
        String p = password.getText();
        
        try{
            try(Statement statement = (Statement) koneksi.GetConnection().createStatement()){
                statement.executeUpdate("INSERT INTO akun VALUES ('"+user+"', '"+p+"')");
            } 
            inilabel.setText("Daftar Berhasil");
        } catch (Exception ex) {
            inilabel.setText("GAGAL! Silahkan Ulangi");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
