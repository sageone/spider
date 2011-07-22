package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Adds a waiting interval time after each element processed. 
 * 
 * This interval can be fix or random [minMilisPeriod - maxMilisPeriod] (default [0-1000] ) 
 * <p>
 * The interval is measured in miliseconds.
 * </p>
 * 
 * TODO Test this processor.
 * 
 * @author daviz
 *
 */
public class IntervalProcessorImpl 
extends AbstractProcessor
implements IProcessor{

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(IntervalProcessorImpl.class);
	
	private long minMilisPeriod = 0;
	
	/**
	 * The maximum time sleepint between element processing, in miliseconds.
	 */
	private long maxMilisPeriod = 1000;
	
	/**
	 * FLAG for random period. The random period is in range [0-maxMilisPerior]
	 */
	private boolean randomPeriod = Boolean.FALSE;
	
	/**
	 * The processor for the elements.
	 */
	private IProcessor processor;
	
	public void IntervalProcessor(IProcessor processor){
		
		this.processor = processor;
		
	}
	
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		log.debug("Starting interval processor. Random [ {} ] range [ {} ]",randomPeriod,minMilisPeriod+"-"+maxMilisPeriod);
		
		List<IElement> result = new ArrayList<IElement>();
		
		for(IElement e:elements){
			result = processor.process(e);
			
			try{
				sleep();
			}catch(InterruptedException ie){
				// nothing to do!
				log.warn("Exception during sleep!",e);
			}
		}
		return result;
	}

	public long getMinMilisPeriod() {
		return minMilisPeriod;
	}

	public void setMinMilisPeriod(long minMilisPeriod) {
		this.minMilisPeriod = minMilisPeriod;
	}

	public long getMaxMilisPeriod() {
		return maxMilisPeriod;
	}

	public void setMaxMilisPeriod(long maxMilisPeriod) {
		this.maxMilisPeriod = maxMilisPeriod;
	}

	public IProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(IProcessor processor) {
		this.processor = processor;
	}
	
	public boolean isRandomPeriod() {
		return randomPeriod;
	}

	public void setRandomPeriod(boolean randomPeriod) {
		this.randomPeriod = randomPeriod;
	}

	private void sleep() throws InterruptedException{
		
		// Calculate range absolute value.
		long sleepPeriod = this.getMaxMilisPeriod() - this.getMinMilisPeriod();
		
		// Random flag
		if(randomPeriod){
			sleepPeriod = (long) Math.floor(sleepPeriod * Math.random());
			
		}
		// Normalize due min value
		sleepPeriod = sleepPeriod + this.getMinMilisPeriod();
		
		log.debug("Random[ {} ] sleep time : ",randomPeriod,sleepPeriod);
		
		Thread.sleep(sleepPeriod);
	}

}
