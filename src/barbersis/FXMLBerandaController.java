/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbersis;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;


/**
 *
 * @author Kandos
 */
public class FXMLBerandaController implements Initializable {
    
    @FXML
    private Label label;

    @FXML
    private JFXRadioButton pot1;

    @FXML
    private ToggleGroup potong;

    @FXML
    private JFXRadioButton pot2;

    @FXML
    private JFXRadioButton pot3;

    @FXML
    private JFXRadioButton pot4;

    @FXML
    private JFXButton btnHitung;

    @FXML
    private JFXTextArea tekarea;

    @FXML
    private JFXTextField total;

    @FXML
    private JFXTextField bayar;

    @FXML
    private JFXButton btnDetail;

    @FXML
    private JFXButton btnHapus;
    
    @FXML
    private JFXRadioButton pot5;

    @FXML
    private ToggleGroup tambah;

    @FXML
    private JFXRadioButton pot6;

    
    int harga;
    int hargatambah = 0;
    int totalbayar;
    String tampilTotalBayar, ket;
    String ketTambah = "";
    int kembalian;
    String uangkembalian;

    @FXML
    void detail(ActionEvent event) {
        if(pot1.isSelected()){     
            ket = "3 2 1";
        }
        else if(pot2.isSelected()){     
            ket = "2 1 0";
        }
        else if(pot3.isSelected()){     
            ket = "1 1 1";
        }
        else if(pot4.isSelected()){     
            ket = "Plontos";
        }
        if(pot5.isSelected()){
            ketTambah = "Kumis";
        }
        else if(pot6.isSelected()){
            ketTambah = "Jenggot";
        }
        
        String uangbayar = bayar.getText();
        int mbayar =Integer.parseInt(bayar.getText());
        kembalian = mbayar - totalbayar;
        uangkembalian=String.valueOf(kembalian);
        String kt = String.valueOf(ketTambah);
        tekarea.setText("Jenis Potongan : " + ket 
                +"\nTambahan : " + kt
                +"\nTotal Harga : " + totalbayar
                +"\nBayar : " + uangbayar
                +"\nKembalian : " + uangkembalian);
        
        String jp = String.valueOf(ket);
        String tmb = String.valueOf(kt);
        String ttl = String.valueOf(totalbayar);
        String byr = String.valueOf(uangbayar);
        String kmbl = String.valueOf(uangkembalian);
        try{
            try(Statement statement = (Statement) koneksi.GetConnection().createStatement()){
                statement.executeUpdate("INSERT INTO data VALUES ('"+jp+"', '"+tmb+"', '"+ttl+"', '"+byr+"', '"+kmbl+"')");
            } 
            
        } catch (Exception ex) {
            
        }
    }

    @FXML
    void hapus(ActionEvent event) {
        pot1.setSelected(false);
        pot2.setSelected(false);
        pot3.setSelected(false);
        pot4.setSelected(false);
        pot5.setSelected(false);
        pot6.setSelected(false);
        total.setText("");
        bayar.setText("");
        tekarea.setText("");
    }

    @FXML
    void hitung(ActionEvent event) {
        if(pot1.isSelected()){     
            harga = 8000;
        }
        else if(pot2.isSelected()){     
            harga = 9000;
        }
        else if(pot3.isSelected()){     
            harga = 10000;
        }
        else if(pot4.isSelected()){     
            harga = 12000;
        }
        
        if(pot5.isSelected()){
            hargatambah = 2000;
        }
        else if(pot6.isSelected()){
            hargatambah = 3000;
        }
        
        
        totalbayar = harga + hargatambah;
        tampilTotalBayar=String.valueOf(totalbayar);
        total.setText(tampilTotalBayar);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
