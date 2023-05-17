package com.qin.viewcampus.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventDaoInformationTest {

    @Autowired
    private EventInformationDao eventInformationDao;

    @Test
    void getsitetest(){

        System.out.println(eventInformationDao.selectById("100001"));
    }

    @Test
    void splitstringtest(){
        String s = eventInformationDao.selectById("100001").getEventSite();
        System.out.println(s);
        String[] ss = "54.215,54.554".split(",");
        System.out.println(ss[0]);
    }
}
