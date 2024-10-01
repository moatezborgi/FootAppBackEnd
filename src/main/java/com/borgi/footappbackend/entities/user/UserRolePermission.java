package com.borgi.footappbackend.entities.user;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class UserRolePermission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UserRolePermissionId;
    private String UserRolePermissionName;
    private boolean isActiveUserRolePermission;
    @ManyToOne
    private UserRole userRole;
}
