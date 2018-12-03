package com.TYServer.service.memService.memServiceImpl;

import com.TYServer.service.memService.MEMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class AddMEMServiceImpl implements MEMService {
    public static List objects = Collections.synchronizedList(new ArrayList());
    public  static final Logger logger = LoggerFactory.getLogger(AddMEMServiceImpl.class);
    @Override
    public void addMEM() {
        List list = new ArrayList();
        for (int i = 0; i < 10000; i++) {
            List list1 = new ArrayList();
            list1.add("1234567890");
            list.add(list1);
        }
        objects.add(list);
        int freemem= (int) (Runtime.getRuntime().freeMemory()/1024/1024);
        int totalmem= (int) (Runtime.getRuntime().totalMemory()/1024/1024);
        int maxmem= (int) (Runtime.getRuntime().maxMemory()/1024/1024);
        int usedMemory=totalmem-freemem;

        float result= (float) (usedMemory*1.0/maxmem*100);
        logger.info(new Date()+"-------used Memory:"+usedMemory+"-------freepercent:"+(100-result)+"%");
    }

    @Override
    public void clearMEM() {
        objects.clear();
        Runtime.getRuntime().gc();
        int freemem= (int) (Runtime.getRuntime().freeMemory()/1024/1024);
        int totalmem= (int) (Runtime.getRuntime().totalMemory()/1024/1024);
        int maxmem= (int) (Runtime.getRuntime().maxMemory()/1024/1024);
        int usedMemory=totalmem-freemem;

        float result= (float) (usedMemory*1.0/maxmem*100);
        logger.info(new Date()+"-------used memory clean,and gc!!!-------used Memory:"+usedMemory+"-------freepercent:"+(100-result));
    }
}
