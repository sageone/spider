package org.nms.spider.helpers.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.nms.anxova.process.IProcessor;
import org.nms.anxova.process.beans.IElement;
import org.nms.anxova.process.impl.AbstractProcessor;
import org.nms.spider.beans.impl.TypedElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Url processor for String based elements.
 * <p>
 * The element must contain the URL to process.
 * </p>
 * <p>
 * Configurable user agent and connection time out.
 * </p>
 * @author daviz
 *
 */
public class UrlProcessorImpl extends AbstractProcessor<String,String>  implements
		IProcessor<String,String>{

	private static final Logger log = LoggerFactory
			.getLogger(UrlProcessorImpl.class);

	/**
	 * User agent for url connection.
	 * <p>
	 * Default : Mozilla/4.0.
	 * </p>
	 */
	private String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)";
	
	/**
	 * Connection time out. Default 10sec (10000 ms).
	 */
	private int timeOut = 10000;
	
	@Override
	public List<IElement<String>> process(IElement<String> element) {
		ArrayList<IElement<String>> result = new ArrayList<IElement<String>>();

		if(element!=null) {

			String urlString = element.getElement();

			log.info("Preprocessing url : " + urlString);

			TypedElement te = new TypedElement();
			te.setId(urlString);
			te.setType("htmlcontent");

			try {
				URL url = new URL(urlString);
				URLConnection connection = url.openConnection();
				// [DBG]
				Set<String> keys = connection.getRequestProperties().keySet();
				for(String key : keys){
					log.debug("Key {} value {} " , key, connection.getRequestProperties().get(key));
				}
				// [ENDDBG]
				
				
				connection.setAllowUserInteraction(false); 
				connection.setConnectTimeout(timeOut);
				connection.setDoOutput(true);
				connection.addRequestProperty("User-Agent", 
				    userAgent);

				
				if ((connection.getContentType() != null)
						&& !connection.getContentType().toLowerCase()
								.startsWith("text/")) {

				}
				
				InputStream is = connection.getInputStream();
				Reader r = new InputStreamReader(is);
				CharBuffer cbuff = CharBuffer.allocate(1000);
				StringBuffer sb = new StringBuffer();
				while (r.read(cbuff) != -1) {

					log.trace("CBUFF-" + cbuff.length());

					for (int i = 0; i < 1000 - cbuff.length(); i++) {
						sb.append(cbuff.get(i));
					}
					sb.append(cbuff.toString());

					cbuff = CharBuffer.allocate(1000);
				}
				log.trace("SB:" + sb.toString());

				te.setElement(sb.toString());
				result.add(te);
			} catch (Exception e) {
				log.error("Error trying to process the URL.", e);

			}
		}

		return result;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	/**
	 * @return the timeOut
	 */
	public int getTimeOut() {
		return timeOut;
	}

	/**
	 * @param timeOut the timeOut to set
	 */
	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

}
