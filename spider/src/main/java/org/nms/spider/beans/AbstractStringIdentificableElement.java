package org.nms.spider.beans;

import java.io.Serializable;

import org.nms.anxova.process.beans.IElement;

/**
 * Abstract Element implementation for IIdentificable
 * @author daviz
 *
 * @param <T>
 */
public abstract class AbstractStringIdentificableElement<T extends Serializable> 
implements IElement<T> {
	
	/**
	 * The String ID.
	 */
	private String id;
	
	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public boolean isequals(String id){
		if(id==null) return false;
		
		return id.equals(this.getId());
	}
}
