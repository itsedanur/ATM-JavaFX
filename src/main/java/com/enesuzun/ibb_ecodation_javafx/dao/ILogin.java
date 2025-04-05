package com.enesuzun.ibb_ecodation_javafx.dao;

import java.util.Optional;


public interface ILogin <T>{
    //login için gerekli yapılar bunlar
    Optional <T> loginUser(String userName,String password);
}
