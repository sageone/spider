package org.nms.spider.beans.impl;


import org.nms.spider.beans.AbstractElement;

/**
 * A Generic element using STRING as elements.
 * <p>
 * Valid for URLs.
 * </p>
 * @author daviz
 *
 */
public class StringElement
extends AbstractElement<String> 
{

	
	private String element;
	
	@Override
	public boolean isequals(String id) {
		return id.equals(super.getId());
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
		sb.append("[ID=").append(super.getId()).append("]")
			.append("[E:").append((this.element==null)?"null":this.element.toString()).append("]");
		return sb.toString();	
		
	}

}
