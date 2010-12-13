package org.nms.spider.beans;

import java.io.Serializable;

import org.nms.spider.common.IIdentificable;

/**
 * A generic element
 * @author daviz
 *
 */
public interface IElement<T extends Serializable>
extends IIdentificable<String>{

	T getElement();
	
	void setElement(T e);
}
