package org.nms.spider.helpers.impl;

import org.nms.anxova.modifiers.IElementModifier;
import org.nms.anxova.process.beans.IElement;

public class PrependerElementModifierImpl implements IElementModifier{

	/**
	 * The string to prepend at the INIT of the element value (must be a string)
	 */
	private String prependString;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IElement modify(IElement e) {
		
		e.setElement(prependString + e.getElement().toString());
		e.setId(e.getElement());
		return e;
	}

	public String getPrependString() {
		return prependString;
	}

	public void setPrependString(String prependString) {
		this.prependString = prependString;
	}

}
