package org.nms.spider.helpers.impl;

import java.util.ArrayList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.nms.spider.beans.IElement;
import org.nms.spider.beans.impl.TypedElement;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegexParserProcessorImpl implements IProcessorHelper {

	/**
	 * Logger
	 */
	private static final Logger log = LoggerFactory.getLogger(RegexParserProcessorImpl.class);
	
	/**
	 * Prefix for the link search.
	 */
	private String prefix;
	
	/**
	 * Postfix for the link search
	 */
	private String postfix;
	
	/**
	 * Removes the prefix in the result element.
	 */
	private boolean removePrefix = Boolean.FALSE;
	
	/**
	 * Removes the postfix in the result element.
	 */
	private boolean removePostFix = Boolean.FALSE;
	
	/**
	 * The default REGEX used for matching is (.*?).
	 * 
	 */
	private String regex = "(.*?)";

	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		
		List<IElement> result = new ArrayList<IElement>();
		for(IElement e:elements){
			
			
			
			Pattern p = Pattern.compile(prefix+regex+postfix);
			
			
			Matcher m = p.matcher(e.getElement().toString());
			
			while(m.find()){
				TypedElement te = new TypedElement();
				te.setType("htmllink");
				
				log.info("Found one element!");
				String found = m.group();
				found = processResult(found);
				te.setId(found);
				te.setElement(found);
				log.info(found);
				result.add(te);
			}
			
		}
		return result;
	}

	
	private String processResult(String result){
		String processed = result;
		if(removePrefix){
			processed = processed.substring(processed.length()-prefix.length(),processed.length());
		}
		if(removePostFix){
			processed = processed.substring(0,processed.length()-postfix.length());
		}
		return processed;
	}
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public boolean isRemovePrefix() {
		return removePrefix;
	}

	public void setRemovePrefix(boolean removePrefix) {
		this.removePrefix = removePrefix;
	}

	public boolean isRemovePostFix() {
		return removePostFix;
	}

	public void setRemovePostFix(boolean removePostFix) {
		this.removePostFix = removePostFix;
	}


	public String getRegex() {
		return regex;
	}


	public void setRegex(String regex) {
		this.regex = regex;
	}


}
