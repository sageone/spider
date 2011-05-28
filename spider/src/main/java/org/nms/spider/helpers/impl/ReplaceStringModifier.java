package org.nms.spider.helpers.impl;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.IElementModifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReplaceStringModifier implements IElementModifier {

	/**
	 * The logger
	 */
	private final static Logger log = LoggerFactory.getLogger(ReplaceStringModifier.class);
	
	/**
	 * The string to replace.
	 */
	private String toReplace;
	
	/**
	 * The replacer for the string.
	 */
	private String replacer;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public IElement modify(IElement e) {
		
		if(toReplace == null || toReplace.equals("")){
			log.warn("No string to replace. Nothing to do");
			return e;
		}
		if(replacer == null ){
			log.warn("NULL string replacement. Nothing to do. Use empty string to replace for no value.");
			return e;
		}
		e.setElement(e.getElement().toString().replaceAll(toReplace,replacer));
		
		log.debug("Replacing [{}] : result [{}]",replacer+"/"+toReplace,e.getElement().toString());
		return e;
	}

	public String getToReplace() {
		return toReplace;
	}

	public void setToReplace(String toReplace) {
		this.toReplace = toReplace;
	}

	public String getReplacer() {
		return replacer;
	}

	public void setReplacer(String replacer) {
		this.replacer = replacer;
	}

}
