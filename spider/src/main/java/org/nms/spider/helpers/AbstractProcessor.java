package org.nms.spider.helpers;

import java.util.List;

import org.nms.spider.beans.IElement;
import org.nms.spider.common.IIdentificable;
import org.nms.spider.common.INameable;

public abstract class AbstractProcessor implements IIdentificable<String>,
		INameable, IProcessorHelper {

	private String id;

	private String name;

	private boolean active = Boolean.TRUE;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isequals(String id) {
		if (id == null)
			return false;

		return id.equals(this.id);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("[").append(id).append("]-").append("[").append(name)
				.append("]");

		return sb.toString();
	}

	@SuppressWarnings("rawtypes")
	public abstract List<IElement> process(List<IElement> elements);

}
