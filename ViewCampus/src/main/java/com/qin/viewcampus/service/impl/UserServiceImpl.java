package com.qin.viewcampus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qin.viewcampus.dao.*;
import com.qin.viewcampus.entity.*;
import com.qin.viewcampus.service.IUserService;
import com.qin.viewcampus.util.UserProof;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserInformationDao userInformationDao;
    @Autowired
    private UserEventDao userEventDao;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private EventInformationDao eventInformationDao;

    //通过id删除user，包括了users表和user_information表的信息
    @Override
    public Boolean DeleteUserById(String id) {
        return userDao.deleteById(id)==userInformationDao.deleteById(id)==true;
    }

    //创建新用户，包括了users表、user_information表和user_events表的信息
    @Override
    public Boolean SaveUser(UserProof userProof) {
        String id = userProof.getOpenid();
        String name = userProof.getName();
        if(UserIsExist(id)){
            return false;
        }else {
            User user = new User(id,"",1,1);
            UserInformation userInformation = new UserInformation(id,name,"","");
            UserEvent userEvent = new UserEvent(id,"");
            Boolean iUser =  userDao.insert(user) ==1;
            Boolean iUserInformation = userInformationDao.insert(userInformation) == 1;
            Boolean iUserEvent = userEventDao.insert(userEvent) ==1;
            //Boolean iUser =  userDao.InsertUser(id,"1925050999",1,1);
            //Boolean iUserInformation = userInformationDao.InsertUserInformation(id,name,"","");
            //Boolean iUserEvent = userEventDao.InsertUserEvents(id,"");
            return iUser==iUserInformation==iUserEvent==true;
        }
    }

    //通过id查询用户是否存在
    @Override
    public Boolean UserIsExist(String id) {
        return  userDao.selectById(id)!=null;
    }


    //以活动id集和激活态为依据获取用户的event的所有信息
    @Override
    public List<ArrayList<Object>> StateEventsOfUser(List<String> idList, Boolean status) {
        List<ArrayList<Object>> eventListOfUser = new ArrayList<>();
        //要求激活态
        if(status){
            for(String str: idList){
                ArrayList<Object> al = new ArrayList<>();
                Event event = eventDao.selectById(str);
                EventInformation eventInformation = eventInformationDao.selectById(str);
                if(null == event|| null == event){
                    break;
                }
                //event是激活态
                if(event.getStatusApproval()==(status?1:0) && event.getStatusEnd()==(status?0:1)){
                    al.add(event);
                    al.add(eventInformation);
                    eventListOfUser.add(al);
                }
            }
        }
        //要求失活态
        else{
            for(String str: idList){
                ArrayList<Object> al = new ArrayList<>();
                Event event = eventDao.selectById(str);
                EventInformation eventInformation = eventInformationDao.selectById(str);
                if(null == event|| null == event){
                    break;
                }
                //event是激活态
                if(event.getStatusApproval()==(status?0:1) && event.getStatusEnd()==(status?1:0)){

                }
                //event是失活态
                else {
                    al.add(event);
                    al.add(eventInformation);
                    eventListOfUser.add(al);
                }
            }
        }
        //返回要求的活动列表
        return eventListOfUser;
    }
}
