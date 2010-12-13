package org.nms.spider.beans.impl;


import org.nms.spider.beans.IElement;

/**
 * A Generic element using STRING as elements.
 * <p>
 * Valid for URLs.
 * </p>
 * @author daviz
 *
 */
public class GenericElement implements IElement<String>{

	private String id;
	
	private String element;
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id){
		this.id = id;
	}
	@Override
	public boolean isequals(String id) {
		return id.equals(this.id);
	}


	@Override
	public String getElement() {
		return element;
	}

	@Override
	public void setElement(String e) {
		this.element = e;
	}
	
	/**
	 * Overriding the toString method to show ID and ELEMENT values (String values)
	 */
	public String toString(){

		StringBuffer sb = new StringBuffer();
		sb.append("[ID=").append(this.id).append("]")
			.append("[E:").append((this.element==null)?"null":this.element.toString()).append("]");
		return sb.toString();	
		
	}

}
