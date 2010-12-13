package org.nms.spider.helpers;

import java.util.List;

import org.nms.spider.beans.IElement;

public interface IElementParser {

	/**
	 * Parses a list of elements.
	 * 
	 * @param elements List of elements
	 * @return List of elements parsed
	 */
	@SuppressWarnings("rawtypes")
	public List<IElement> parse(IElement...elements);
	
	
}
