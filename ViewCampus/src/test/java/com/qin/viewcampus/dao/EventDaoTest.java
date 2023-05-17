package com.qin.viewcampus.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qin.viewcampus.entity.Event;
import com.qin.viewcampus.entity.EventInformation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class EventDaoTest {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventInformationDao eventInformationDao;

    @Test
    void testgetbyid(){
        eventDao.selectById("100001");
    }

    @Test
    void testgetdetailbyid(){
        String id = "100001";
        ArrayList<Object> eventList= new ArrayList<Object>();
        Event event = eventDao.selectById(id);
        EventInformation eventInformation = eventInformationDao.selectById(id);
        eventList.add(event);
        eventList.add(eventInformation);
        System.out.println(eventList);
    }

    @Test
    void testgetpage(){
        IPage page = new Page(2,3);
        eventDao.selectPage(page,null);
    }

    @Test
    void testgetall(){
        eventDao.selectList(null);
    }

    @Test
    void testgetactiveevents(){
        //eventDao.GetActiveEvents();
    }

    @Test
    void testgetallcondition(){
        String conditions = "developer";
        LambdaQueryWrapper<Event> qw = new LambdaQueryWrapper<>();
        qw.like(conditions != null,Event::getEventName, conditions);
        eventDao.selectList(qw);
    }

    @Test
    void testinsert(){
        Event event = new Event();
        event.setEventId("999999");
        event.setEventName("qq");
        event.setEventSponsor("rr");
        event.setStatusApproval(1);
        event.setStatusEnd(0);
        eventDao.insert(event);
    }

    @Test
    void testsave(){
        Event event = new Event();
        event.setEventId("999999");
        event.setStatusEnd(1);
        eventDao.updateById(event);
    }

    @Test
    void testdelet(){
        eventDao.deleteById("999999");
    }
}
