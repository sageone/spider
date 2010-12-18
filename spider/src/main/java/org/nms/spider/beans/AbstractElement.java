package org.nms.spider.beans;

import java.io.Serializable;

/**
 * Abstract Element implementation for IIdentificable
 * @author daviz
 *
 * @param <T>
 */
public abstract class AbstractElement<T extends Serializable> 
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
