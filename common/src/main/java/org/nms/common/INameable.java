package org.nms.common;

import java.io.Serializable;

public interface INameable<T extends Serializable> {

	public T getName();
	
	public void setName(T name);
}
