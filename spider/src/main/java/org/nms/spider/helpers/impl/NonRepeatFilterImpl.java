package org.nms.spider.helpers.impl;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.IFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This filter stores the ID's of elements to know if the spider has inspected the element before.
 * <p>
 * A persisten store system is needed for multiple execution filtering purposes.
 * This allows to not repeat access to some elements (i.e web access).
 * The base for non-repeating inspection the same element twice is using it's ID.
 * </p>
 * @author daviz
 *
 */
public class NonRepeatFilterImpl implements IFilter {

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(NonRepeatFilterImpl.class);
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean passes(IElement e) {
		
		log.warn("NOT IMPLEMENTED!");
		
		// TODO Implement the non-repeat element inspection.
		
		return true;
	}

	
}
