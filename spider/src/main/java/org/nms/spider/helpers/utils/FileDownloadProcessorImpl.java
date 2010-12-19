/**
 * 
 */
package org.nms.spider.helpers.utils;

import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;

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

	/**
	 * The download path. Default is the actual directory.
	 */
	private String downloadPath = "./";

	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {
		for (IElement e : elements) {
			FileDownloadProcessorImpl.downloadUrl(e.getElement().toString(),
					downloadPath);
		}

		return elements;
	}

	/**
	 * Downloads the file form the url, into the downloadPath
	 * 
	 * @param url
	 *            The url .
	 * @param downloadPath
	 *            The path.
	 */
	private final static void downloadUrl(String url, String downloadPath) {

		// TODO Implement!
	}
}
