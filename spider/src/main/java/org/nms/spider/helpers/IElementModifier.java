package org.nms.spider.helpers;

import org.nms.spider.beans.IElement;

public interface IElementModifier {

	/**
	 * Modifies the element.
	 * @param e The element to modify.
	 * @return The modified element.
	 */
	@SuppressWarnings("rawtypes")
	public IElement modify(IElement e);
}
