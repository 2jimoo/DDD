package com.example.ddd.webapp.out.repository.User;

import com.example.ddd.webapp.out.Const;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@Table(name = Const.USERS)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    //DB 삽입, 갱신 정보
    private Instant insertedAt;
    private String insertedBy;
    private Instant modifiedAt;
    private String modifiedBy;
}
