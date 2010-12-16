package org.nms.spider.app;

import org.nms.spider.beans.IElement;
import org.nms.spider.engine.ISpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleStandAloneSpider {

	/**
	 * The Logger
	 */
	private final static Logger log = LoggerFactory.getLogger(SimpleStandAloneSpider.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		log.info("Starting spider");
		if(args == null || args.length==0){
			log.error("Must indicate the xml configuration file. This file must be in the classpath");
			System.exit(-1);
		}
		try{
			// 1. Load Spring context (arg[0])
			ISpider s;
			ApplicationContext ctx = new ClassPathXmlApplicationContext(args[0]);
			
			s = (ISpider)ctx.getBean("spider");
			// 2. Start SPIDER
			s.spider();
		}catch(Exception e){
			log.error("Errors during execution : {}",e.getMessage());
			log.error("Exception:",e);
			System.exit(-1);
		}
		log.info("End of spider");
		
		System.exit(0);
	}

}
