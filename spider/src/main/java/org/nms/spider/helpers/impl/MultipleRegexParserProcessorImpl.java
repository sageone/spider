package org.nms.spider.helpers.impl;

import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.beans.impl.RegexDefinition;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MultipleRegexParserProcessorImpl implements IProcessorHelper{

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory.getLogger(MultipleRegexParserProcessorImpl.class);
	
	
	private List<RegexDefinition> regexs;
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		if(elements== null || elements.size()==0){
			log.warn("No elements to process.Nothing to do");
			return elements;
		}
		if(regexs!=null || regexs.size()!=0){
			
			List<IElement> result = new ArrayList<IElement>();
			
			for(RegexDefinition rd:regexs){
				
				// Create the regex parser for the regex definition.
				RegexParserProcessorImpl rgParser = new RegexParserProcessorImpl();
				rgParser.setPrefix(rd.getPrefix());
				rgParser.setPostfix(rd.getPostfix());
				rgParser.setRemovePostFix(rd.isRemovePostfix());
				rgParser.setRemovePrefix(rd.isRemovePrefix());
				rgParser.setRegex(rd.getRegex());
				
				// Process
				try{
				List<IElement> rgPResult = rgParser.process(elements);
				if(rgPResult==null || rgPResult.size()==0){
					log.warn("No results for regex parser.Regex : " + rd.toString());
				}else{
					result.addAll(rgPResult);
					
				}
				}catch(Exception e){
					log.error("Error while processing regex : {}",rd.toString());
					log.error("Exception : ",e);
				}
				
				
			}
			
			return result;
		}else{
			log.warn("No regex to apply. Nothing to do.");
			return elements;
		}
	}

}
