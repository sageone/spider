package org.nms.spider.helpers.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractBaseProcessor;
import org.nms.spider.helpers.IProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Chain of processors implementation.
 * <p>
 * Straightforward implementation for a chain of processors.<br/>
 * Must set a coherent chain (this coherence will be checked), where the R of the step-1 is the O for the next step.<br/>
 * The first O must be the same for the chainprocessor, and the last R must be the R for the chainprocessor
 * </p>
 * @author daviz
 *
 */
public class ChainedProcessorImpl<O extends Serializable,R extends Serializable> 
extends AbstractBaseProcessor<O,R>
implements IProcessor<O,R>{

	private static final Logger log = LoggerFactory.getLogger(ChainedProcessorImpl.class);

	/**
	 * The processors chain.
	 */
	private List<IProcessor> processorsChain = new ArrayList<IProcessor>();
	
	
	public List<IProcessor> getProcessorsChain() {
		return processorsChain;
	}

	/**
	 * Sets the processors chain list.
	 * @param processors The processors chain list. Order of the list is the order of the chain.
	 */
	public void setProcessorsChain(List<IProcessor> processors) {
		this.processorsChain = processors;
	}

	
	/**
	 * Adds a processor to the processing chain.
	 * @param processor
	 */
	public void addProcessor(IProcessor processor){
		processorsChain.add(processor);
	}
	
	
	
 	@Override
	public List<IElement<R>> process(IElement<O> element) {
 		List<IElement<R>> result = null;
 		if(!verifyProcessorsChain()){
 			log.error("The processors chain was not verified! Returning null as result.");
 			return null;
 		}else{
 		
			int step = 0;
			if(this.processorsChain!= null && this.processorsChain.size()>0){
				result = new ArrayList<IElement<R>>();
				List<IElement<?>> intermediateResult = new ArrayList<IElement<?>>();
				intermediateResult.add(element);
				log.info("The chain processors is of {} processors.",processorsChain.size());
				for(IProcessor p:processorsChain){
					intermediateResult =p.process(intermediateResult);
					log.debug("Processed step {}",step++);
				}
				
				// Copy elements to result
				for(IElement e:intermediateResult){
					result.add(e);
				}
				
			}else{
				log.warn("No processors to use. Returning null.");
				result = null;
			}
 		}
		
		
		
		return result;
	}


	/**
	 * Validates the processor chain.
	 * <p>
	 * Test the chain of correct O and R types. 
	 * The R type for the step of the chain must be the O type of the step+1.
	 * </p>
	 * @return
	 */
	private boolean verifyProcessorsChain(){
		// Check the origin
		// TODO
		
		// Check the chain
		for(int i=0;i<this.processorsChain.size()-1;i++){
			IProcessor originProcessor = this.processorsChain.get(i);
			IProcessor destinationProcessor = this.processorsChain.get(i+1);
			
			if(!destinationProcessor.getOriginClass().getClass().getName()
					.equalsIgnoreCase(originProcessor.getResultClass().getClass().getName())){
				return false;
			}
		}
		// Check the result
		// TODO
		
		return true;
	}
}
