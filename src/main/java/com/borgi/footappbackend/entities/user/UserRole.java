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
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userRoleId;
    private String userRoleName;
    @OneToMany(mappedBy = "userRole")
    private List<UserRefrence> userRefrenceList;

    @OneToMany(mappedBy = "userRole")
    private List<UserRolePermission> userRolePermissionList;
}
