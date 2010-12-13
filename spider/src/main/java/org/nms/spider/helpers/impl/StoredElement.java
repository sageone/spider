package org.nms.spider.helpers.impl;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spider_storedelements")
public class StoredElement implements Serializable{

	@Id
	private String id;
}
