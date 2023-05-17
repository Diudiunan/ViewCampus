package com.qin.viewcampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.viewcampus.dao.EventDao;
import com.qin.viewcampus.dao.EventInformationDao;
import com.qin.viewcampus.entity.Event;
import com.qin.viewcampus.entity.EventInformation;
import com.qin.viewcampus.service.IEventInformationService;
import com.qin.viewcampus.service.IEventService;
import com.qin.viewcampus.service.IUserMessageService;
import com.qin.viewcampus.util.EventAuditBySystem;
import com.qin.viewcampus.util.EventIdFactory;
import com.qin.viewcampus.util.EventRecommend;
import com.qin.viewcampus.util.EventsToMarkers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class EventServiceImpl extends ServiceImpl<EventDao, Event> implements IEventService {

    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventInformationDao eventInformationDao;
    @Autowired
    private IEventInformationService iEventInformationService;
    @Autowired
    private IUserMessageService IMS;
    @Autowired
    private UserEventServiceImpl userEventService;

    //通过id获得event的所有信息
    @Override
    public ArrayList<Object> GetEventAllInformation(String id){
        ArrayList<Object> eventList= new ArrayList<>();
        Event event = eventDao.selectById(id);
        EventInformation eventInformation = eventInformationDao.selectById(id);
        eventList.add(event);
        eventList.add(eventInformation);
        return eventList;
    }

    //通过id删除event，包括了events表和event_information表的信息
    @Override
    public Boolean DeleteEventById(String id) {
        return eventDao.deleteById(id)== eventInformationDao.deleteById(id)==true;
    }

    //保存活动，向events和event_informations表添加信息
    @Override
    public Boolean SaveEvent(Event event, EventInformation eventInformation, String uid){
        //活动工厂
        Callable<String> callableId =  new EventIdFactory();
        FutureTask<String> futureTaskId = new FutureTask<>(callableId);
        Thread threadId = new Thread(futureTaskId);
        threadId.start();
        //活动审查
        Callable<Integer> callableAp = new EventAuditBySystem(uid, eventInformation, event);
        FutureTask<Integer> futureTaskAp = new FutureTask<>(callableAp);
        Thread threadAp = new Thread(futureTaskAp);
        threadAp.start();
        try {
            String eid = futureTaskId.get();
            Integer pass = futureTaskAp.get();
            event.setEventId(eid);
            eventInformation.setEventId(eid);
            //是否通过
            event.setStatusApproval(pass);
            event.setStatusEnd(0);
            //添加和更新
            Boolean iEvent = eventDao.insert(event) ==1;
            Boolean iEventInformation = eventInformationDao.insert(eventInformation) ==1;
            Boolean updateUserEvent = userEventService.UpdateEventIdById(uid,eid);
            return iEvent==iEventInformation==updateUserEvent==true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    //从events表中获取一些处于激活状态的event并将数据转为markers形式为前端提供服务
    @Override
    public List<EventsToMarkers> GetActiveMarkers(Integer filter) {
        List<EventsToMarkers> mList = new ArrayList<>();
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("event_id", "event_name", "event_sponsor","event_grade")
                .eq("status_approval",1)
                .eq("status_end",0)
                .eq("event_grade",filter);
        List<Event> aList = eventDao.selectList(queryWrapper);
        System.out.println(aList);
        for (Event event: aList){
            EventsToMarkers eventsToMarkers = new EventsToMarkers();
            eventsToMarkers.setEventId(event.getEventId());
            eventsToMarkers.setEventName(event.getEventName());
            eventsToMarkers.setEventSponsor(event.getEventSponsor());
            //获得坐标数组
            //String[] site = eventInformationDao.GetSite(event.getEventId().split(",");
            String[] site = eventInformationDao.selectById(event.getEventId())
                    .getEventSite()
                    .split(",");
            eventsToMarkers.setEventLongitude(site[1]);
            eventsToMarkers.setEventLatitude(site[0]);
            mList.add(eventsToMarkers);
        }
        return mList;
    }

    //获得所有已激活的event
    @Override
    public List<Event> GetActiveEvents() {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("event_id", "event_name", "event_sponsor")
                .eq("status_approval",1)
                .eq("status_end",0);
        return eventDao.selectList(queryWrapper);
    }

    //获得所有失活的event
    @Override
    public List<Event> GetOffEvents() {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("event_id", "event_name", "event_sponsor")
                .eq("status_approval",0)
                .eq("status_end",1);
        return eventDao.selectList(queryWrapper);
    }

    //获得被推荐的已激活的活动
    @Override
    public List<Event> GetRecommendActiveEvents(int currentpage, int pagesize, int recommend) {
        QueryWrapper<Event> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .select("event_id", "event_name", "event_sponsor","event_grade")
                .eq("status_approval",1)
                .eq("status_end",0)
                //起始坐标(currentpage-1)*pagesize  结点坐标 currentpage*pagesize
                .last("limit "+pagesize+" offset "+(currentpage-1)*pagesize);
        List<Event> re_list = new EventRecommend(eventDao.selectList(queryWrapper), recommend).GetRecommendList();
        while(re_list.size()<pagesize){
            currentpage++;
            queryWrapper.clear();
            queryWrapper
                    .select("event_id", "event_name", "event_sponsor","event_grade")
                    .eq("status_approval",1)
                    .eq("status_end",0)
                    .last("limit "+pagesize+" offset "+(currentpage-1)*pagesize);
            List<Event> repeatList = new EventRecommend(eventDao.selectList(queryWrapper), recommend).GetRecommendList();
            if(repeatList.size()==0){
                break;
            }else{
                re_list.addAll(repeatList);
            }
        }
        return re_list;
    }

    //按要求获得分页，包括激活态的和失效态的活动
    @Override
    public IPage<Event> GetPage(int currentpage, int pagesize) {
        IPage page = new Page(currentpage, pagesize);
        eventDao.selectPage(page,null);
        return page;
    }

    //实时更新活动状态
    //@PostConstruct
    @Async
    @Scheduled(cron = "0 0/1 * * * *")
    @Override
    public void UpdateEvent() {
        if (true){
            List<Event> eventList = GetActiveEvents();
            List<String> idList = new ArrayList<>();
            for(Event event: eventList){
                idList.add(event.getEventId());
            }
            QueryWrapper<EventInformation> qe = new QueryWrapper<>();
            qe.in("event_id",idList);
            List<EventInformation> eventI_list =  iEventInformationService.list(qe);
            for(EventInformation eventI: eventI_list){
                LocalDate ld = LocalDate.now();
                LocalTime lt = LocalTime.now();
                String re = ld+" "+lt;
                if(re.substring(0,19).compareTo(eventI.getEventEnd())>=1){
                    UpdateWrapper<Event> ue = new UpdateWrapper<>();
                    ue.set("status_end",1);
                    ue.eq("event_id",eventI.getEventId());
                    eventDao.update(null,ue);
                }
            }
        }
        System.out.println("活动状态更新一次");
    }
}
