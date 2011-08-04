package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.anxova.process.IProcessor;
import org.nms.anxova.process.beans.IElement;
import org.nms.anxova.process.impl.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This helper offers the processing of the elements by more than one processor, at same time, not 
 * in a row (as opposite for ChainedProcessorHelperImpl)
 * <p>
 * Not a threaded implementation.
 * </p>
 * @author daviz
 *
 */
public class ParalelProcessorHelperImpl 
extends AbstractProcessor
implements IProcessor{

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(ParalelProcessorHelperImpl.class);
	
	/**
	 * The processors.
	 */
	private List<IProcessor> processors;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		if(elements==null || elements.size()==0){
			log.warn("No elements to process!");
			return elements;
		}
		if(processors==null ||processors.size()==0){
			log.warn("No processors to apply. Nothing to do.");
			return elements;
		}
		List<IElement> result = new ArrayList<IElement>();
		
		for(IProcessor p:processors){
			List<IElement> pResult = p.process(elements);
			if(pResult==null || pResult.size()==0){
				log.info("No results for processor " + p.getClass().toString());
			}else{
				result.addAll(result);
			}
		}
		
		return result;
	}

	public List<IProcessor> getProcessors() {
		return processors;
	}

	public void setProcessors(List<IProcessor> processors) {
		this.processors = processors;
	}

}
