package org.nms.spider.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleStandAloneMultipleSpider {

	/**
	 * The Logger
	 */
	private final static Logger log = LoggerFactory
			.getLogger(SimpleStandAloneMultipleSpider.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		log.info("Starting spider");
		if (args == null || args.length == 0) {
			log.error("Must indicate the xml configuration file. This file must be in the classpath");
			System.exit(-1);
		}
		try {
			for (String ctx : args) {
				ContextSpiderStarter.startSingleSpiderContext(ctx);

			}

		} catch (Exception e) {
			log.error("Errors during execution : {}", e.getMessage());
			log.error("Exception:", e);
			System.exit(-1);
		}
		log.info("End of spider");

		System.exit(0);
	}

}
