package com.qin.viewcampus.service;

import com.qin.viewcampus.entity.User;
import com.qin.viewcampus.util.UserProof;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private IUserService iUserService;

    @Test
    void userisexisttest(){
        System.out.println(iUserService.UserIsExist("as"));
    }

    @Test
    void savetest(){
        UserProof userProof = new UserProof();
        userProof.setOpenid("hhgh");
        userProof.setName("fghghf");
        iUserService.SaveUser(userProof);
    }

    @Test
    void deletetest(){
        iUserService.DeleteUserById("");
    }

    @Test
    void stateeventsofuser(){
        List<String> df = new ArrayList<>();
        System.out.println(iUserService.StateEventsOfUser(df,true).size()==0);
    }
    @Test
    void fse(){
        User user = new User();
        String id = user.getUserId();
        System.out.println(id);
    }
}
