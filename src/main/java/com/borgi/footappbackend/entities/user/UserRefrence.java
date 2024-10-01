package com.borgi.footappbackend.entities.user;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class UserRefrence implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int userSystemId;
    private  String userFirstName;
    private  String userLastName;
    private  String userEmail;
    private  String userPassword;
    private  String userPhone;
    private boolean isActiveAccount;

    @ManyToOne
    private UserRole userRole;

    @OneToMany(mappedBy = "userRefrence")
    private List<UserRefreshToken> userRefreshTokenList;
}
