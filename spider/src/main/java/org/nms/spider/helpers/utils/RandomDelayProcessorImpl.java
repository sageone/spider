package org.nms.spider.helpers.utils;

import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Random delay processor.
 * <p>
 * Defined for a human web navigation simulation.
 * </p>
 * @author daviz
 *
 */
public class RandomDelayProcessorImpl extends AbstractProcessor {

	/**
	 * Log.
	 */
	private Logger log = LoggerFactory
			.getLogger(RandomDelayProcessorImpl.class);
	/**
	 * The minimun delay (in miliseconds)
	 */
	private Long minDelay;

	/**
	 * The maximum delay (in miliseconds)
	 */
	private Long maxDelay;

	@Override
	public List<IElement> process(List<IElement> elements) {

		try {
			Thread.sleep(getRandomDelay(this.minDelay, this.maxDelay));
		} catch (InterruptedException e) {
			log.warn("The delay has been interrupted! {} ", e.getCause());
		}
		return elements;
	}

	/**
	 * Obtains a random long value between min and max.
	 * @param min The minimun delay value.
	 * @param max The maximun delay value.
	 * @return
	 */
	private Long getRandomDelay(Long min, Long max) {

		if (min == null || max == null || min >= max) {

			return 1000L;
		} else {
			// delay long for debug purposes
			Long delay = ((long)(Math.random() * (double)Math.abs(max - min))) * 1000L;
			// [DBG]
			log.debug("Delay calculated [{},{}]" + delay, min, max);
			return delay;
		}
	}

	public Long getMinDelay() {
		return minDelay;
	}

	public void setMinDelay(Long minDelay) {
		this.minDelay = minDelay;
	}

	public Long getMaxDelay() {
		return maxDelay;
	}

	public void setMaxDelay(Long maxDelay) {
		this.maxDelay = maxDelay;
	}

}
