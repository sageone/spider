package org.nms.spider.engine.impl;

import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.engine.ISpider;
import org.nms.spider.helpers.IProcessor;

/**
 * Abstract Spider class implementation.
 * This must be the BASE for the real implementations.
 * 
 * @author daviz
 *
 */
public abstract class AbstractSpider implements ISpider{
	
	/**
	 * The initial set for the spider.
	 */
	@SuppressWarnings("rawtypes")
	private List<IElement> initialSet;
	/**
	 * The preprocessor for the spider engine.
	 */
	private IProcessor preprocessor;
	
	/**
	 * The postprocessor for the spider engine.
	 */
	private IProcessor postprocessor;
	
	
	private String name;
	
	@SuppressWarnings("rawtypes")
	public List<IElement> getInitialSet() {
		return initialSet;
	}

	@SuppressWarnings("rawtypes")
	public void setInitialSet(List<IElement> initialSet) {
		this.initialSet = initialSet;
	}

	public IProcessor getPostprocessor() {
		return postprocessor;
	}

	public void setPostprocessor(IProcessor postprocessor) {
		this.postprocessor = postprocessor;
	}


	public void setPreprocessor(IProcessor preprocessor) {
		this.preprocessor = preprocessor;
	}

	public IProcessor getPreprocessor() {
		return preprocessor;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
