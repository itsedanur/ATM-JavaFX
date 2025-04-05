package com.enesuzun.ibb_ecodation_javafx.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public interface IGenericsMethod<T> {
    /// /////////////////////////////////////////////////////////
    //generics metot (list,find)
    //resultSet'ten userDTO oluşturmayı tek bir yardımcı metot ile bu şekilde yapacağız
    public T mapToObjectDTO(ResultSet resultSet) throws SQLException;

    //dizi elemanları gelebilir( değişken , birden fazla olabilir )
    //ID VEYA NAME ile veri çekilince bu ortak metot kullanılacak
    //generics ile tek kayıt döndüren metot
    public Optional<T> selectSingle(String sql, Object... params);

    /// ///////////////////////////////////////////////////////
}
