package org.nms.spider.helpers;

import org.nms.spider.common.IIdentificable;
import org.nms.spider.common.INameable;

public abstract class AbstractProcessor implements IIdentificable<String>, INameable {

	private String id;
	
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isequals(String id){
		if(id== null) return false;
		
		return id.equals(this.id);
	}
	
	public String toString(){
		
		StringBuffer sb = new StringBuffer();
		sb.append("[").append(id).append("]-")
		.append("[").append(name).append("]");
		
		return sb.toString();
	}
	
}
