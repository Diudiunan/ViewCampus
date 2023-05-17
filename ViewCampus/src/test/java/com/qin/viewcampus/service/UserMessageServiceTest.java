package com.qin.viewcampus.service;

import com.qin.viewcampus.entity.UserMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SpringBootTest
public class UserMessageServiceTest {

    @Autowired
    private IUserMessageService iUserMessageService;

    @Test
    void selectbyid(){
        List<UserMessage> messageList1 = iUserMessageService.list();
        List<String> messageList2 = iUserMessageService.GetUserMessageById("qin");
        System.out.println(messageList1);
        System.out.println(messageList2);
    }

    @Test
    void senttest(){
        Future<Boolean> as = iUserMessageService.SetUserMessageById("qin",1,"ok");
        try {
            System.out.println(as.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
