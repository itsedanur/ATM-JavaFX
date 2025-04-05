package com.enesuzun.ibb_ecodation_javafx.controller;

import com.enesuzun.ibb_ecodation_javafx.dao.UserDAO;
import com.enesuzun.ibb_ecodation_javafx.dto.UserDTO;
import com.enesuzun.ibb_ecodation_javafx.utils.SpecialColor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

import java.awt.*;
import java.util.Optional;

//Bağlayıcı bir alan olacak
//Java ile XML arasında köprü görür
public class LoginController  {
    //İnjectior
    //(VERİ TABANI İŞLEMLERİ İÇİN)
    private UserDAO userDAO;
    //Parametresiz constructer
    public LoginController() {
        userDAO = new UserDAO();
    }
    /// ////////////////////////////////////////////////////////////////////////////////
    /// FXML field (text field yapıları eklenecek sebebi ise kullanıcıdan veri almak)
    @FXML//bunu ekleme sebebi javaFX ile çalımak için
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    /// ////////////////////////////////////////////////////////////////////////////////
    /// ShowAlert (Kullanıcıya bilgi veya hata mesajları göstermek için kullanılan yardımcı metot)
    /// INFORMATION:BILGI,  ERROR: HATA
    private void showAlert(String title, String message, Alert.AlertType type) {
        // Alert nesnesi oluşturuyoruz ve parametre olarak alınan başlık, mesaj ve tipi ayarlıyoruz
        Alert alert = new Alert(type);
        // Title
        alert.setTitle(title);
        // Message
        alert.setContentText(message);
        // Alert penceresini gösteriyoruz ve kullanıcıdan bir yanıt bekliyoruz
        alert.showAndWait();
    } //end showAlert
    /// //////////////////////////////////////////////////////////////////////////////////////
    /// Klavyeden ENTER tuşuna bastığımda giriş yapsın
    @FXML
    private void specialOnEnterPressed(KeyEvent keyEvent) {
        // Eğer basılan tuş ENTER ise
        if (keyEvent.getCode() == KeyCode.ENTER) {
            // Eğer Enter'a basarsam login() sayfasına gitsin
            login();
        }
    } // onEnterPressed
    /// //////////////////////////////////////////////////////////////////////////////////////
    /// LOGIN ( Kullanıcının giriş işlemini gerçekleştiren metot )
    @FXML
    public void login(){
        //kullanıcı giriş yaparken username - password almak
        String username,password;
        username = usernameField.getText();
        password = passwordField.getText();

        //optionalUserDTO VT'ye  eklenecek
        Optional<UserDTO> optionalLoginUserDTO=userDAO.loginUser(username,password);
        if(optionalLoginUserDTO.isPresent()){//eğer veri boş değilse 1
            UserDTO userDTO=optionalLoginUserDTO.get();//Veri alınır

            //Eger bağarılı ise toast mesaji göster
            showAlert("Başarılı","Giriş başarılı",Alert.AlertType.INFORMATION);

            //Kayıt başarılı ise Admin paneline geç
            openAdminPage();
        }else{
            //Eger bilgiler doğru değilse kayıt olmamıstri
            showAlert("BASARISIZ","Giriş BAŞARISIZ ,KAYDOLUN",Alert.AlertType.ERROR);

        }
    }
    /// //////////////////////////////////////////////////////////////////////////////////////////
    /// Admin paneline geçişi sağlar(DASHBOARD)
    public void openAdminPage(){
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource
                    ("/com/enesuzun/ibb_ecodation_javafx/view/admin.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            //admin sayfasına veri gönderelim
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Admin panel' olarak ayarlıyalım
            stage.setTitle("ADMİİN PANEL"+usernameField);
            // Sahneyi göster
            stage.show();

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(SpecialColor.RED + "ADMİN Sayfasında yönlendirilmedi" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "ADMİN Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    }
    /// //////////////////////////////////////////////////////////////////////////////////////
    /// Sayfalar Arasında Geçiş (LOGIN -> REGISTER)
    // Login (Switch)
    //  // Eğer Kayıt işlemi başarılıysa Login ekranına gitsin
    @FXML
    private void switchToRegister() {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/enesuzun/ibb_ecodation_javafx/view/register.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Kayıt Ol' olarak ayarlıyalım
            stage.setTitle("Kayıt Ol");
            // Sahneyi göster
            stage.show();

        } catch (Exception e) {
            //throw new RuntimeException(e);
            System.out.println(SpecialColor.RED + "Login Sayfasında yönlendirilmedi" + SpecialColor.RESET);
            e.printStackTrace();
            showAlert("Hata", "Login Ekranı Yüklenemedi", Alert.AlertType.ERROR);
        }
    } //end switchToLogin

}




















