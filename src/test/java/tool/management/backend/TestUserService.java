package tool.management.backend;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tool.management.backend.services.UserService;

public class TestUserService {

    @Autowired
    UserService userService;

    @Test
    public void getCompanyOfUser() {
        userService.getCompanyOfUser(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYWEiLCJpYXQiOjE3NTEyNDE0MTcsImV4cCI6MTc1MTMyNzgxN30.uym7xF1fsviVBrLmBpJRYUGfI1Sc7BgBJhanwa3f6Rw");
    }

}
