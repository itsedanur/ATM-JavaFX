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

public class RegisterController {
    //İnjectior
    //(VERİ TABANI İŞLEMLERİ İÇİN)
    private UserDAO userDAO;
    //Parametresiz constructer
    public RegisterController() {
        userDAO = new UserDAO();
    }
    /// ////////////////////////////////////////////////////////////////////////////////
    /// FXML field (text field yapıları eklenecek sebebi ise kullanıcıdan veri almak)
    @FXML//bunu ekleme sebebi javaFX ile çalımak için
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField emailField;


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
            register();
        }
    } // onEnterPressed
    /// //////////////////////////////////////////////////////////////////////////////////////
    /// Register ( Kullanıcının giriş işlemini gerçekleştiren metot )
    @FXML
    public void register(){
        //kullanıcı giriş yaparken username - password almak
        String username,password,email;
        username = usernameField.getText();
        password = passwordField.getText();
        email = emailField.getText();

        //optionalUserDTO VT'ye  eklenecek
        // optionalUserDTO(Veri tabanına ekle)
        Optional<UserDTO> optionalRegisterUserDTO = Optional.ofNullable(UserDTO.builder()
                .id(0) // Create
                .userName(username)
                .password(password)
                .email(email)
                .build());
        
        if(optionalRegisterUserDTO.isPresent()){//eğer veri boş değilse 1
            UserDTO userDTO=optionalRegisterUserDTO.get();//Veri alınır

            //Eger bağarılı ise toast mesaji göster
            showAlert("Başarılı","KAYIT başarılı",Alert.AlertType.INFORMATION);

            //Kayıt başarılı ise Admin paneline geç
            switchToLoginPane();
        }else{
            //Eger bilgiler doğru değilse kayıt olmamıstri
            showAlert("BASARISIZ","Giriş BAŞARISIZ ,KAYDOLUN",Alert.AlertType.ERROR);

        }
    }

    /// //////////////////////////////////////////////////////////////////////////////////////
    // Sayfalar Arasında Geçiş (LOGIN -> REGISTER)
    // Login (Switch)
    //  // Eğer Kayıt işlemi başarılıysa Login ekranına gitsin
    @FXML
    private void switchToLoginPane() {
        try {
            // FXML Dosyalarını Yükle (Kayıt ekranının FXML dosyasını yüklüyoruz)
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/enesuzun/ibb_ecodation_javafx/view/login.fxml"));
            Parent parent = fxmlLoader.load();

            // Var olan sahneyi alıp ve değiştirmek
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(parent));

            // Pencere başlığını 'Kayıt Ol' olarak ayarlıyalım
            stage.setTitle("giriş yap");

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
