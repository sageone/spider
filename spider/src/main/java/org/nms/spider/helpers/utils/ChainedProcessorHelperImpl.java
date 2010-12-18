package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Chains the preprocessors process.
 * 
 * @author daviz
 *
 */
public class ChainedProcessorHelperImpl 
extends AbstractProcessor
implements IProcessorHelper{

	private static final Logger log = LoggerFactory.getLogger(ChainedProcessorHelperImpl.class);

	/**
	 * The processors chain.
	 */
	private List<IProcessorHelper> processorsChain = new ArrayList<IProcessorHelper>();
	
	/**
	 * The processors chain for each step.
	 */
	private ChainedProcessorHelperImpl perStepChainProcessor;
	
	public List<IProcessorHelper> getProcessorsChain() {
		return processorsChain;
	}

	/**
	 * Sets the processors chain list.
	 * @param processors The processors chain list. Order of the list is the order of the chain.
	 */
	public void setProcessorsChain(List<IProcessorHelper> processors) {
		this.processorsChain = processors;
	}

	
	/**
	 * Adds a processor to the processing chain.
	 * @param processor
	 */
	public void addProcessor(IProcessorHelper processor){
		processorsChain.add(processor);
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		int step = 0;
		if(this.processorsChain!= null && this.processorsChain.size()>0){
			
			log.info("The chain processors is of {} processors.",processorsChain.size());
			
			for(IProcessorHelper p:processorsChain){
				
				if(elements.size() == 0){
					log.warn("No elements to process. Exit the process chain. Step "+ step);
					return elements;
				}
				
				log.info("Starting processor "+p.getClass().toString());
				log.info("Elements to process : " + elements.size());
				elements = p.process(elements);
				log.info("Elements obtained : " + elements.size());
				
				if(perStepChainProcessor!=null){
					elements = perStepChainProcessor.process(elements);
				}
				
				step++;
			}
			
		}else{
			log.warn("No processors to use. Returning the same elements.");
		}
		
		return elements;
	}

	public ChainedProcessorHelperImpl getPerStepChainProcessor() {
		return perStepChainProcessor;
	}

	public void setPerStepChainProcessor(
			ChainedProcessorHelperImpl perStepChainProcessor) {
		this.perStepChainProcessor = perStepChainProcessor;
	}

	
}
