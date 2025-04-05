package com.enesuzun.ibb_ecodation_javafx.database;


import com.enesuzun.ibb_ecodation_javafx.utils.SpecialColor;

import java.sql.*;

//bu yapı ile database'e bağlantı kurmak isteniyor
public class SingletonDBConnection {
    //Fields
    //database'e bağlanmak istiyorsak 3 tane yapı vardır bunlar üzerinden devam ederiz
    //singelton design pattern yapısında private çok kullanılır

    //database information data
    private static final String URL = "jdbc:h2:./h2db/user_management";
    //private static final String URL = "jdbc:h2:./h2db/user_management?" + "AUTO_SERVER=TRUE";
    //private static final String URL = "jdbc:h2:~/h2db/user_management"; //kök dizin
    private static final String USERNAME="sa";
    private static final String PASSWORD="";

    //Singleton Desing pattern
    private static SingletonDBConnection instance;
    //Database Connection
    private Connection connection;


    //Parametresiz constructer (NOT : SİNGELTON YAPISI İÇİN PRİVATE OLMASI GEREKLİDİR)
    //EĞER BİR CONSTRUCTERDA PRİVATE KULLANIYORSAK O CLASS'IN DIŞARIDAN NESNE YARATMASINI ENGELLEMEK İSTİYORUZSUR (SOLİD 1)
    private SingletonDBConnection(){
        try{
            //JDBC Yükle (Neolduğuna bak)
            Class.forName("org.h2.Driver");
            //bağlantı oluşturmak için yazarız
            this.connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println(SpecialColor.GREEN +"VERİTABANI BAĞLANTISI BASARILI"+SpecialColor.RESET);

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(SpecialColor.RED +"VERİTABANI BAĞLANTISI BASARISIZ"+SpecialColor.RESET);
            throw new RuntimeException("VERİTABANI BAĞLANTISI BASARISIZ");
        }
    }


    //Singleton design instance
    public static synchronized SingletonDBConnection getInstance(){
        if(instance==null){
            instance = new SingletonDBConnection();
        }
        return instance;
    }
    //Bağlantı nesnesini çağırma
    public Connection getConnection() {
        return connection;
    }

    //Veri tabanı bağlantısını kapatma
    public  static void closeConnection(){
        if(instance != null && instance.connection != null){
            try{
                instance.connection.close();
                System.out.println(SpecialColor.RED+" Veri tabanı bağlantısı kapatıldı "+SpecialColor.RESET);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //database test
    public static void dataSet()  throws SQLException{
        // Singleton Instance ile Bağlantıyı Al
        SingletonDBConnection dbInstance = SingletonDBConnection.getInstance();
        Connection conn = dbInstance.getConnection();

        Statement stmt = conn.createStatement();

        // Örnek bir tablo oluştur
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(255), "
                + "email VARCHAR(255))";
        stmt.execute(createTableSQL);
        System.out.println("Users tablosu oluşturuldu!");

        // Veri Ekleme
        String insertDataSQL = "INSERT INTO Users (name, email) VALUES "
                + "('Ali Veli', 'ali@example.com'), "
                + "('Ayşe Fatma', 'ayse@example.com')";
        stmt.executeUpdate(insertDataSQL);
        System.out.println("Veriler eklendi!");

        // Veri Okuma
        String selectSQL = "SELECT * FROM Users";
        ResultSet rs = stmt.executeQuery(selectSQL);

        System.out.println("\nUsers Tablosu İçeriği:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Email: " + rs.getString("email"));
        }
        // Bağlantıyı Kapat
        SingletonDBConnection.closeConnection();
    }
    public static void main(String[] args) throws SQLException {
        //dataSet();
    }






}//END CLASS



/*public static Connection getConnection() throws SQLException {
        if(connection==null || connection.isClosed()){
            try{
                connection= DriverManager.getConnection(URL,USERNAME,PASSWORD);
                System.out.println(SpecialColor.GREEN +"VERİTABANI BAĞLANTISI BASARILI"+SpecialColor.RESET);
            }catch (SQLException sqlException){
                sqlException.printStackTrace();
                System.out.println(SpecialColor.RED +"VERİTABANI BAĞLANTISI BASARISIZ"+SpecialColor.RESET);
                throw new RuntimeException("VERİTABANI BAĞLANTISI BASARISIZ");
            }
        }
        return connection;
    }*/