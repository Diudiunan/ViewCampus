package com.qin.util;

import java.time.LocalDate;
import java.time.LocalTime;

public class MessageIdFactory {

	public static String GetTableLabel(){
        return String.valueOf(LocalDate.now()).replace("-","").substring(5,8);
    }

    //���ϵͳ��Ϣ��id
    public static String GetMID(){
        return String.valueOf(LocalTime.now()).replace(":","").replace(".","");
    }
}
