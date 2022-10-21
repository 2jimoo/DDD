package com.example.ddd.webapp.out.repository.User;

import com.example.ddd.webapp.out.Const;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = Const.USER)
public class UserEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
