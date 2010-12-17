package org.nms.spider.beans.impl;

/**
 * A regex definition.
 * <p>
 * The prefix, the postfix, a Java REGEX and boolean values to remove prefix and postfix.
 * Default values:
 * <ul>
 * <li> removePrefix = FALSE </li>
 * <li> removePostfix = FALSE </li>
 * <li> regex = "(.*?)"</li>
 * </ul>
 * </p>
 * @author daviz
 *
 */
public class RegexDefinition {

	private String prefix;
	
	private String postfix;
	
	private boolean removePrefix = Boolean.FALSE;
	
	private boolean removePostfix = Boolean.FALSE;

	private String regex = "(.*?)";
	
	public String toString(){
		StringBuffer sb = new StringBuffer()
			.append(prefix).append("-")
			.append(regex).append("-")
			.append(postfix).append("|")
			.append("Remove Pre/Post").append(removePrefix).append('/').append(removePostfix);
		return sb.toString();
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

	public boolean isRemovePostfix() {
		return removePostfix;
	}

	public void setRemovePostfix(boolean removePostfix) {
		this.removePostfix = removePostfix;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}
	
}
