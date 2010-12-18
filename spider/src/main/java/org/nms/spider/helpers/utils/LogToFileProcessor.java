package org.nms.spider.helpers.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.beans.impl.StringElement;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Processor that logs to a FILE the elements.
 * @author daviz
 *
 */
public class LogToFileProcessor implements IProcessorHelper {

	/**
	 * The logger
	 */
	private static final Logger log = LoggerFactory.getLogger(LogToFileProcessor.class);
	
	/**
	 * The file PATH	
	 */
	private String path;
	
	/**
	 * The file base name. Datetime will be added and .log
	 */
	private String fileName;
	
	/**
	 * Log the ID of the element. Default TRUE.
	 */
	private boolean logId = Boolean.TRUE;
	
	/**
	 * Log the Element value. Default FALSE;
	 */
	private boolean logElement = Boolean.FALSE;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		
		if(elements==null || elements.size()==0){
			elements = new ArrayList<IElement>();
			StringElement e = new StringElement();
			e.setId("No elements to log");
			e.setElement("No elements to log");
		}
		String fileName = generateFileName();
		try{
		    // Create file 
		    FileWriter fstream = new FileWriter(fileName);
		    BufferedWriter out = new BufferedWriter(fstream);
		    for(IElement e:elements){    
		    	if(this.isLogId()){
		    		out.write(e.getId().toString());
		    		out.write(" ");
		    	}
		    	if(this.isLogElement()){
		    		out.write(e.getElement().toString());
		    	}
		    	
		    	if(this.isLogId()||this.isLogElement()){
		    		out.write("\n");
		    	}
		    }
		    //Close the output stream
		    out.close();
		    
		    // Flush and close the fstream.
		    }catch (Exception e){//Catch exception if any
		    	log.error("Error writing to file {}",fileName);
		    	log.error("Exception",e);
		    }
		
		return elements;
	}

	private String generateFileName(){
		
		return this.path+this.fileName+System.currentTimeMillis()+".log";
	}

	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public boolean isLogId() {
		return logId;
	}


	public void setLogId(boolean logId) {
		this.logId = logId;
	}


	public boolean isLogElement() {
		return logElement;
	}


	public void setLogElement(boolean logElement) {
		this.logElement = logElement;
	}

}
