package org.nms.spider.helpers.utils;

import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A Processor just for logging activity.
 * <p>
 * Logs the processed elements. Uses the toString method to log the elements.
 * </p>
 * @author daviz
 *
 */
public class LogProcessorImpl 
extends AbstractProcessor
implements IProcessor{

	private final static Logger log = LoggerFactory.getLogger(LogProcessorImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		if(elements==null || elements.size()==0){
			sb.append("No elements to log\n");
		}
		for(IElement e:elements){
			sb.append(e.toString()).append("\n");
		}
		
		log.info(sb.toString());
		
		return elements;
	}

}
