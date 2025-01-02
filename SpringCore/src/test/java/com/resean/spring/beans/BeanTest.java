package com.resean.spring.beans;

import com.resean.spring.context.ClassPathXmlApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("bean.xml");
        ExcuteSevice excuteSevice=(ExcuteSevice)classPathXmlApplicationContext.getBean("excuteSevice");
        excuteSevice.excute();

    }

}
