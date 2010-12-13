package org.nms.spider.helpers.impl;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.IElementModifier;

public class AppenderElementModifierImpl implements IElementModifier{

	/**
	 * The string to append to the END of the element value (must be a string)
	 */
	private String appendString;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IElement modify(IElement e) {
		
		e.setElement(e.getElement().toString() + appendString);
		e.setId(e.getElement());
		return e;
	}

	public String getAppendString() {
		return appendString;
	}

	public void setAppendString(String appendString) {
		this.appendString = appendString;
	}

	
}
