package com.yihg.sales.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Startup {

	public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"applicationContext.xml"});
        context.start();
		System.out.println("@@@@@@@@@@@@@@@@@@sales启动成功@@@@@@@@@@@@@@@@@@@@@");
        synchronized (Startup.class)
		{
			while (true) 
				try
				{
					Startup.class.wait();
				}
				catch (Throwable e) { 
					e.printStackTrace();
				}
		}

    }
    
}