package org.nms.spider.helpers.impl;

import org.nms.anxova.filter.IFilter;
import org.nms.anxova.process.beans.IElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filters the elements that String version of Element containts the string filter.
 * @author daviz
 *
 */
public class ContainsStringFilterImpl implements IFilter{

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(ContainsStringFilterImpl.class);
	
	/**
	 * The String filter.
	 */
	private String filter;
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean passes(IElement e) {
		if(filter==null || filter.equals("")){
			log.warn("Null or '' string filter value. Nothing to compare, element PASS the filter.");
			
			return true;
		}
		return e.getElement().toString().contains(filter);
	}

	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	
}
