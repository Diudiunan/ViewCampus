package com.qin.viewcampus.util;

import com.qin.viewcampus.service.IUserMessageService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

public class EventAuditBySystemTest {
    private IUserMessageService iUserMessageService;

    @Test
    public void test1(){
        ApplicationContext context = GetBean.getApplicationContext();
        System.out.println(context);
        iUserMessageService = context.getBean(IUserMessageService.class);
        iUserMessageService.SetUserMessageById("Xc5e5EWzdidpVvVfUP9P7huUY",1,"");
    }
}
