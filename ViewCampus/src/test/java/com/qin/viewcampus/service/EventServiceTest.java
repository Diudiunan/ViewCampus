package com.qin.viewcampus.service;

import com.qin.viewcampus.dao.EventDao;
import com.qin.viewcampus.util.EventIdFactory;
import com.qin.viewcampus.util.MessageIdFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
public class EventServiceTest {
    @Autowired
    private IEventService iEventService;
    @Autowired
    private EventDao eventDao;

    @Test
    void testGetById(){

    }

    @Test
    void testSave(){

    }
    @Test
    void testUpdate(){

    }
    @Test
    void testGetAll(){

    }
    @Test
    void testGetPage(){
        System.out.println(iEventService.GetRecommendActiveEvents(1,4,1));
        //System.out.println(iEventService.GetRecommendActiveEvents(2,3));
    }
    @Test
    void getactivemarkerstest(){
        Integer filter = 0;
        System.out.println(iEventService.GetActiveMarkers(filter));
    }

    @Test
    void sdasd(){
        String df = "2028-04-05 07:30:00";
        String ty = "2023-04-17 00:39:00";
        LocalDate as = LocalDate.now();
        LocalTime ds = LocalTime.now();
        LocalDateTime wd = LocalDateTime.now();
        String re = as+" "+ds;
        System.out.println(re.replace("-",""));
        System.out.println(df);
        System.out.println(wd);
        System.out.println(re.substring(0,19));
        System.out.println(df);
        System.out.println("现在的时间和df比较");
        System.out.println(re.compareTo(df));
        System.out.println(re);
        System.out.println(ty);
        System.out.println("现在的时间tf比较");
        System.out.println(re.compareTo(ty));
    }

    @Test
    void ferw(){

        MessageIdFactory ms = new MessageIdFactory();
        System.out.println(ms.GetMID());
        System.out.println(ms.GetTableLabel());
    }

    @Test
    void hgfh(){
        String theDate = String.valueOf(LocalDate.now()).replace("-","");
        String theTime = String.valueOf(LocalTime.now()).replace(":","");
        String uid = theDate.substring(2,7) + theTime.replace(".","");
        System.out.println(uid);
    }
}
