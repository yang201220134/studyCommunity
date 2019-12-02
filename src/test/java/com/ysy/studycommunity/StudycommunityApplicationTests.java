package com.ysy.studycommunity;

import com.ysy.studycommunity.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudycommunityApplicationTests {

    @Test
    void contextLoads() {
        User user = new User();
        user.setName("ysy");
        System.out.println(user);
    }

}
