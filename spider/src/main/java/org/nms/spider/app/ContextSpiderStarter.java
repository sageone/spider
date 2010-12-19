package org.nms.spider.app;

import java.util.List;

import org.nms.spider.engine.ISpider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextSpiderStarter {

	public static final Logger log = LoggerFactory
			.getLogger(ContextSpiderStarter.class);

	public static void startSingleSpiderContext(String contextFile)
			throws Exception {
		try {
			// 1. Load Spring context (arg[0])
			ISpider s;
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					contextFile);

			s = (ISpider) ctx.getBean("spider");
			// 2. Start SPIDER
			s.spider();

		} catch (Exception e) {
			log.error("Errors during execution : {}", e.getMessage());
			log.error("Exception:", e);
			throw e;
		}
	}

	public static void startMultiSpiderContext(String contextFile)
			throws Exception {

		try {
			// 1. Load Spring context (arg[0])
			List<ISpider> spiders;
			ApplicationContext ctx = new ClassPathXmlApplicationContext(
					contextFile);

			spiders = (List<ISpider>) ctx.getBean("spiders");
			// 2. Start each spider

			for (ISpider s : spiders) {
				try {
					log.info("Executing spider {}.", s.toString());
					s.spider();
					log.info("End of spider {} execution.", s.toString());
				} catch (Exception e) {
					log.error("Error during execution of a spider. Continuing with next spider");
				}
			}

		} catch (Exception e) {
			log.error("Errors during execution : {}", e.getMessage());
			log.error("Exception:", e);
			throw e;
		}
	}
}
