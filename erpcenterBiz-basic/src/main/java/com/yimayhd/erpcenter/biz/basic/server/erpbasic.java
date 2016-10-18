package com.yimayhd.erpcenter.biz.basic.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class erpbasic {

	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        context.start();
		System.out.println("@@@@@@@@@@@@@@@@@@basic启动成功@@@@@@@@@@@@@@@@@@@@@");
        synchronized (erpbasic.class)
		{
			while (true) 
				try
				{
					erpbasic.class.wait();
				}
				catch (Throwable e) { 
					e.printStackTrace();
				}
		}

    }
    
}