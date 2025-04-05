package com.enesuzun.ibb_ecodation_javafx.dao;

import com.enesuzun.ibb_ecodation_javafx.database.SingletonDBConnection;
import com.enesuzun.ibb_ecodation_javafx.database.SingletonPropertiesDBConnection;
import com.enesuzun.ibb_ecodation_javafx.dto.UserDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


///
/// Burada interface'i başka bir interface'e implemente etmek için extends kullanılır
/// (Tahminimce aynı tür oldukları için bu keywords seçilmiştir)
///

public interface IDaoImplements <T> extends ICrud<T> , ILogin<T> , IGenericsMethod<T>{

    //gövdeli metot yazılacak(Java 8 ile geliyor
    default Connection iDaoImplementsDatabaseConnection(){
        //return SingletonDBConnection.getInstance().getConnection();

        // Singleton Config
        return SingletonPropertiesDBConnection.getInstance().getConnection();

    }

}
