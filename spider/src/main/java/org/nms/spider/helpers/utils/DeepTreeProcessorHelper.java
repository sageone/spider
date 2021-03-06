package org.nms.spider.helpers.utils;

import java.util.ArrayList;
import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.AbstractProcessor;
import org.nms.spider.helpers.IProcessorHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DEEP processor that is like a SEARCHER in tree (filesystem, web based...)
 * 
 * @author daviz
 * 
 */
public class DeepTreeProcessorHelper extends AbstractProcessor implements
		IProcessorHelper {

	private final static Logger log = LoggerFactory
			.getLogger(DeepTreeProcessorHelper.class);

	/**
	 * The processor that generates the in-deep-search elements.
	 */
	private IProcessorHelper deepSearchElementGeneratorProcessor;

	/**
	 * The processor that obtain the elements.
	 */
	private IProcessorHelper elementProcessor;

	@SuppressWarnings("rawtypes")
	@Override
	public List<IElement> process(List<IElement> elements) {

		log.debug("Starting deep processing for {} elements", elements.size());

		List<IElement> result = new ArrayList<IElement>();

		for (IElement e : elements) {

			List<IElement> elementsProcessed = elementProcessor.process(e);

			log.debug(
					"Processing element {} : obtained {}",
					e,
					(elementsProcessed == null) ? "null" : elementsProcessed
							.size());
			result.addAll(elementsProcessed);

			log.debug("Processing DEEP element {}", e);

			List<IElement> deepSearchElements = deepSearchElementGeneratorProcessor
					.process(elementsProcessed);

			if (deepSearchElements != null && deepSearchElements.size() != 0) {
				log.debug("Going deeper.");
				result.addAll(this.process(deepSearchElements));
			}

		}

		log.debug("************** Returning {} elements", result.size());
		return result;
	}

	public IProcessorHelper getElementProcessor() {
		return elementProcessor;
	}

	public void setElementProcessor(IProcessorHelper elementProcessor) {
		this.elementProcessor = elementProcessor;
	}

	public IProcessorHelper getDeepSearchElementGeneratorProcessor() {
		return deepSearchElementGeneratorProcessor;
	}

	public void setDeepSearchElementGeneratorProcessor(
			IProcessorHelper deepSearchElementGeneratorProcessor) {
		this.deepSearchElementGeneratorProcessor = deepSearchElementGeneratorProcessor;
	}

}
