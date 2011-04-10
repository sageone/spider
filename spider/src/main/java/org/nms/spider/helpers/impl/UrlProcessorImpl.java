package org.nms.spider.helpers.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.nms.spider.beans.IElement;
import org.nms.spider.beans.impl.TypedElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlProcessorImpl extends AbstractProcessor implements
		IProcessorHelper {

	private static final Logger log = LoggerFactory
			.getLogger(UrlProcessorImpl.class);

	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		ArrayList<IElement> result = new ArrayList<IElement>();

		for (IElement el : elements) {

			String urlString = (String) el.getElement();

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
				connection.setDoOutput(true);
				connection.addRequestProperty("User-Agent", 
				    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

				
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

}
