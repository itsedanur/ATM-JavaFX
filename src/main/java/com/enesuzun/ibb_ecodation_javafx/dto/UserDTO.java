package com.enesuzun.ibb_ecodation_javafx.dto;
/// DTO (Data Transfer Object)
/// Açılımı: Data Transfer Object
/// Tanım: Bir uygulama içinde veya farklı sistemler arasında veri taşımak için kullanılan nesnelerdir. DTO’lar sadece veri taşır; herhangi bir iş mantığı içermez.
/// Kullanım Amacı: Veritabanı veya API'den alınan verileri UI veya iş katmanına aktarmak için kullanılır.
import com.enesuzun.ibb_ecodation_javafx.utils.ERole;
import lombok.*;

//LOMBOK
// Getter-setter
@Getter
@Setter
//Parametreli Constructer
//@AllArgsConstructor
//parametresiz Constructer
@NoArgsConstructor
@ToString
@Builder

//UserDTO
public class UserDTO {
    //Fields
    private int id;
    private String userName;
    private String password;
    private String email;
    private ERole role;

    public UserDTO(int id, String userName, String password, String email,ERole eRole) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role=eRole;
    }
}//END CLASS
