package org.nms.spider.engine.impl;

import java.util.List;

import org.nms.anxova.process.beans.IElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Base implementation for the SPIDER.
 * The first aproach for the SPIDER implementation.
 * 
 * @author daviz
 *
 */
public class BaseSpider extends AbstractSpider {

	private static final Logger log = LoggerFactory.getLogger(BaseSpider.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public void spider() {
		
		log.info("Starting spider");
		// 1. Preprocess elements
		log.info("Starting preprocessing processor");
		List<IElement> preprocessedElements = this.getPreprocessor().process(this.getInitialSet());
		
		
		// 2. PostProcess elements
		log.info("Starting postprocessing processor");
		this.getPostprocessor().process(preprocessedElements);
		

		log.info("End of spider");
		
		
	}

}
