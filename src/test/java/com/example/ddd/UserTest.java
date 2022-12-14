package com.example.ddd;

import com.example.ddd.domain.model.Email;
import com.example.ddd.domain.model.Guid;
import com.example.ddd.domain.model.User;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void test(){
        User user= User.of(Guid.of("1234"),
                "Son", "HuiJeong", Email.of("abc@efg.com"), ()->true);
    }
}
