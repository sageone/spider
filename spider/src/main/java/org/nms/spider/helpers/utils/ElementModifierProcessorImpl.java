package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.anxova.modifiers.IElementModifier;
import org.nms.anxova.process.IProcessor;
import org.nms.anxova.process.beans.IElement;
import org.nms.anxova.process.impl.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElementModifierProcessorImpl 
extends AbstractProcessor
implements IProcessor{

	private static final Logger log = LoggerFactory.getLogger(ElementModifierProcessorImpl.class.toString());
	
	
	private List<IElementModifier> modifiers;
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		if(modifiers==null || modifiers.size()==0){
			log.warn("No modifiers set. Nothing to do. Returning same elements.");
			return elements;
		}
		
		log.info("Applying {} modifiers",modifiers.size());
		
		for(IElement e:elements){
			for(IElementModifier m:modifiers){
				e = m.modify(e);
			}
		}
		
		return elements;
	}

	public void addModifier(IElementModifier modifier){
		if(modifiers==null){
			modifiers = new ArrayList<IElementModifier>();
			
		}
		modifiers.add(modifier);
	}
	public List<IElementModifier> getModifiers() {
		return modifiers;
	}

	public void setModifiers(List<IElementModifier> modifiers) {
		this.modifiers = modifiers;
	}

}
