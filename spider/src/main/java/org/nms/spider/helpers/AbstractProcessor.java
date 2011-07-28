package org.nms.spider.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.nms.spider.beans.IElement;
import org.nms.spider.common.IIdentificable;
import org.nms.spider.common.INameable;
import org.slf4j.Logger;

/**
 * Abstract processor.
 * <p>
 * Provides a default implementation for element list processing.
 * </p>
 * @author daviz
 * 
 * <O> The origin generic type for the elements.
 * <R> The resulting generic type for the elements.
 *
 */
public abstract class AbstractProcessor<O extends Serializable,R extends Serializable> implements IIdentificable<String>,
		INameable, IProcessor<O,R> {

	/**
	 * The origin object. Used for validation purposes.
	 */
	private O origin;
	
	/**
	 * The resulting object. Used for validation purposes.
	 */
	private R result;
	
	/**
	 * Log.
	 */
	Logger log = LoggerFactory.getLogger(AbstractProcessor.class);
	
	/**
	 * The processor ID.
	 */
	private String id;

	/**
	 * The processor name.
	 */
	private String name;

	/**
	 * Flag of active processor.
	 */
	private boolean active = Boolean.TRUE;

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

	public boolean isequals(String id) {
		if (id == null)
			return false;

		return id.equals(this.id);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("[").append(id).append("]-").append("[").append(name)
				.append("]");

		return sb.toString();
	}

	/**
	 * Implementation of the process list of elements.
	 * <p>
	 * If the processor is not active, it returns NULL.
	 * </p>
	 * TODO daviz Provide a way to pass the processor and do not transform elements. Must be a just transform form O to R element generic type?
	 */
	public List<IElement<R>> process(List<IElement<O>> elements){
		
		if(!active){
			log.warn("This processor [name:{}] is not active, nothing done!",name);
			return null;
		}
		List<IElement<R>> result = new ArrayList<IElement<R>>();
		if(elements!=null && elements.size()!=0){
			log.debug("To process : {} elements",elements.size());
			
			for(IElement<O> e:elements){
				result.addAll(this.process(e));
			}
		}
		
		log.debug("Obtained {} elements",result.size());
		
		return result;
	}

	/**
	 * An implementation for a single element process method, using the list
	 * abstract one.
	 * 
	 * @param e
	 *            The element to process
	 * @return The result of processing the element as a 1-element list.
	 */
	public abstract List<IElement<R>> process(IElement<O> e);
	
	/**
	 * Obtains the origin class of the genericType O.
	 * <p>
	 * For validation purposes.
	 * </p>
	 * @return The origin Class.
	 */
	@SuppressWarnings("rawtypes")
	public Class getOriginClass(){
		return origin.getClass();
	}
	
	/**
	 * Obtains the resulting class of the genericType R.
	 * <p>
	 * For validation purposes.
	 * </p>
	 * @return The resulting Class.
	 */
	@SuppressWarnings("rawtypes")
	public Class getResultClass(){
		return result.getClass();
	}
	
}
