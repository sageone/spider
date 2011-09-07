package org.nms.spider.engine.impl;

import java.io.Serializable;
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
public class BaseSpider<N extends Serializable> extends AbstractSpider<N> {

	private static final Logger log = LoggerFactory.getLogger(BaseSpider.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public void spider() {
		
		log.info("Starting spider");
		// 1. Preprocess elements
		log.info("Starting preprocessing processor");
		List<IElement> preprocessedElements = null;
		if(this.getPreprocessor()!=null){
			preprocessedElements = this.getPreprocessor().process(this.getInitialSet());
		}else{
			log.warn("No preprocessors found!");
		}
		
		
		// 2. PostProcess elements
		log.info("Starting postprocessing processor");
		if(this.getPostprocessor()!=null){
			this.getPostprocessor().process(preprocessedElements);
		}else{
			log.warn("No postprocessors found!");
		}
		

		log.info("End of spider");
		
		
	}

}
