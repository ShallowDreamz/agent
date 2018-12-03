package com.TYServer.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Random;

public class RandomNumberUtil {
    public static final Logger LOGGER = LoggerFactory.getLogger(RandomNumberUtil.class);
    public static final char[] CHARAS={'1','2', '3', '4', '5', '6', '7', '8','9'};
    public static Random random=new Random();
    @Test
    public static void getRandomNumber(){
        StringBuilder buffer=new StringBuilder();
        for (int i = 0; i <4; i++) {
            buffer.append(CHARAS[random.nextInt(CHARAS.length)]);
        }
        LOGGER.info(buffer.toString());
    }
   /* @Test
    public void test(){
        ErrorServiceImpl errorService = new ErrorServiceImpl();
        ErrorResponseParam errorResponseParam = errorService.responseParam(10);
        LOGGER.info(errorResponseParam.toString());
    }*/
}
