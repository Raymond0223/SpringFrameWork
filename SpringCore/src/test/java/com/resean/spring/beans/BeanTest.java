package com.resean.spring.beans;

import com.resean.spring.context.ClassPathXmlApplicationContext;
import com.resean.spring.core.BeanException;

public class BeanTest {

    public static void main(String[] args) throws BeanException {
        ClassPathXmlApplicationContext classPathXmlApplicationContext=new ClassPathXmlApplicationContext("bean.xml");
        ExcuteSevice excuteSevice=(ExcuteSevice)classPathXmlApplicationContext.getBean("excuteSevice");
        excuteSevice.excute();

    }

}
