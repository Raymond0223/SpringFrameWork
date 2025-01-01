package com.resean.spring.bean;

import com.resean.spring.ClassPathXmlApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("bean.xml");
        ExcuteSevice excuteSevice=(ExcuteSevice)classPathXmlApplicationContext.getBean("excuteSevice");
        excuteSevice.excute();

    }

}
