package org.nms.spider.helpers;

import java.util.List;

import org.nms.spider.beans.IElement;

public interface IProcessorHelper {

	@SuppressWarnings("rawtypes")
	List<IElement> process(List<IElement> elements);
}
