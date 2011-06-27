package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Chained processor IN DEEP execution.
 * Process each element and goes deep the processor chain to obtain the elements.
 * @author daviz
 *
 */
public class PerElementChainedProcessorHelperImpl extends ChainedProcessorHelperImpl
implements IProcessorHelper{

	/**
	 * Logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(PerElementChainedProcessorHelperImpl.class);
	
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		
		List<IElement> result = new ArrayList<IElement>();
		if(elements!=null){
			
			for(IElement e : elements){
				List<IElement> oneElementList = new ArrayList<IElement>();
				oneElementList.add(e);
				result.addAll(processChainPerElement(super.getProcessorsChain(),oneElementList));
			}
			
			return result;
		}else{
			log.warn("No elements to process. Returning NULL");
			return null;
		}
	}
	
	/**
	 * Goes deep the processor chain for each element obtained.
	 * @param processors
	 * @param elements
	 * @return
	 */
	private List<IElement> processChainPerElement(List<IProcessorHelper> processors,List<IElement> elements){
		
		if(processors!=null && processors.size()>0){
			List<IElement> result = new ArrayList<IElement>();
			for(IElement e:elements){
				List<IElement> processedElements = processors.get(0).process(e);
					// Generate the new processors chain list
					List<IProcessorHelper> nextStepProcessorList = new ArrayList<IProcessorHelper>();
					nextStepProcessorList.addAll(processors);
					nextStepProcessorList.remove(0);
				result.addAll(processChainPerElement(nextStepProcessorList,processedElements));
			}
			return result;
		}else{
			return elements;
		}
		
	}
	

}
