/**
 * 
 */
package org.nms.spider.helpers.utils;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements a processor that downloads the files defined by the URL in the
 * element.
 * <p>
 * It does not transform the elements, returns the same elements. TODO The
 * downloaded files (the complete path+name) have to be stored.
 * </p>
 * 
 * @author daviz
 * 
 */
public class FileDownloadProcessorImpl extends AbstractProcessor {

	
	public final static String HEADER_CONTENTDISPOSITION = "Content-Disposition";
	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory
			.getLogger(FileDownloadProcessorImpl.class);
	/**
	 * The download path. Default is the actual directory.
	 */
	private String downloadPath = "./";

	/**
	 * The downloaded file prefix name.
	 */
	private String prefixFileName = "";

	/**
	 * The downloaded file extension
	 */
	private String fileExtension = "";

	/**
	 * The user agent. Default : mozilla 4
	 */
	private String userAgent = "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)";
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		for (IElement e : elements) {
			try {
				this.downloadUrl(
						e.getElement().toString(), generateFileName());
			} catch (IOException ioe) {
				log.error("Error downloading file {}", e.getElement()
						.toString());
				log.error("Exception:", ioe);
				log.info("Processing next file download.");
			}
		}

		return elements;
	}

	/**
	 * Generates the file name.
	 * 
	 * @return The generated filename.
	 */
	public String generateFileName() {

		return this.downloadPath + "/" + this.getPrefixFileName()
				+ "DOWNLOADED" + System.currentTimeMillis() + "."
				+ this.getFileExtension();
	}

	/**
	 * Downloads the file form the url, into the file with name Filename (with
	 * path info)
	 * 
	 * @param urlString
	 *            The url .
	 * @param fileName
	 *            The file name with path info.
	 * @throws IOException
	 */
	public void downloadUrl(String urlString, String fileName)
			throws IOException {

		// Connect and retrieve the input stream
		URL url = new URL(urlString);

		URLConnection connection = url.openConnection();
		connection.setAllowUserInteraction(false);         
		connection.setDoOutput(true);
		connection.addRequestProperty("User-Agent", 
		    userAgent);
		InputStream in = connection.getInputStream();

		Map<String,List<String>> headers = connection.getHeaderFields();
		
		
		// Obtain the file name from headers
		String headerFileName = this.getHeaderFilename(headers);
		if(headerFileName!=null && !"".equals(headerFileName)){
			fileName = this.getDownloadPath() + headerFileName;
		}
		// Process the readed bytes and write to the output buffer stream
		ByteArrayOutputStream tmpOut = new ByteArrayOutputStream();

		byte[] buf = new byte[512];
		while (true) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}
			tmpOut.write(buf, 0, len);
		}
		in.close();
		// Write to the file
		log.debug("Writing output to file : {}", fileName);

		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(tmpOut.toByteArray());
		fos.close();
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}

	public String getPrefixFileName() {
		return prefixFileName;
	}

	public void setPrefixFileName(String prefixFileName) {
		this.prefixFileName = prefixFileName;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public String getHeaderFilename(Map<String,List<String>> headers ){
		String result = null;
		
		//[DBG]
//		for(String key :headers.keySet()){
//			log.debug("HEADER KEY {}",key);
//			for(String header:headers.get(key)){
//				log.debug("[{}] - [{}]",key,header);
//				
//			}
//			
//		}
//		
//		log.debug("GETFILE : {}" ,url.getFile());
		//[ENDDBG]
		
		try{
			
			// Obtain the header value list
			List<String> contentDispositionHeaderListValues = headers.get(FileDownloadProcessorImpl.HEADER_CONTENTDISPOSITION);

			// If has value
			if(contentDispositionHeaderListValues!=null && !contentDispositionHeaderListValues.isEmpty()){
				// Get the first value in the list
				String headerValue = contentDispositionHeaderListValues.get(0);
				
				// Parse the filename
				int end = headerValue.length()-1;
				int start = headerValue.lastIndexOf("=") + 2;
				
				log.debug("Substring of {} range [ {} ] ",headerValue, start + "-" + end);
				
				result = headerValue.substring(start,end);
				
			}else{
				log.warn("No Content-Disposition header found. Can't obtain the filename!");
			}
		}catch(Exception e){
			log.warn("Error obtaining content-disposition filename",e);
		}
		return result;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	
	
	
	
	
	
	
	
	
}
