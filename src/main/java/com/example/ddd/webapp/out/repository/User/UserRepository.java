package com.example.ddd.webapp.out.repository.User;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);

    class UserProxy extends User {
        public UserProxy(Guid userId, String firstName, String lastName, Email email) {
            super(userId, firstName, lastName, email);
        }
    }

}
