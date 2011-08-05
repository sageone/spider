package org.nms.spider.engine;

import java.io.Serializable;

import org.nms.common.INameable;

public interface ISpider<N extends Serializable> extends INameable<N>{
	
	
	public void spider();

}
