package org.nms.spider.helpers.impl;

import java.util.Collections;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.nms.spider.beans.IElement;
import org.nms.spider.helpers.IFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * This filter stores the ID's of elements to know if the spider has inspected
 * the element before.
 * <p>
 * A persisten store system is needed for multiple execution filtering purposes.
 * This allows to not repeat access to some elements (i.e web access). The base
 * for non-repeating inspection the same element twice is using it's ID.
 * </p>
 * 
 * @author daviz
 * 
 */
public class NonRepeatFilterImpl implements IFilter {

	/**
	 * The max ID length (for the database column size)
	 */
	private int maxIdLen = 1000;

	/**
	 * The logger.
	 */
	private final static Logger log = LoggerFactory
			.getLogger(NonRepeatFilterImpl.class);

	/**
	 * The jdbc template.
	 */
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private String tableName;

	private String idTableColumnName;

	/**
	 * Sets the datasource to use.
	 * 
	 * @param dataSource
	 */
	public void setDataSource(BasicDataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
				dataSource);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean passes(IElement e) {

		if (e.getId() != null) {
			// When
			if (e.getId().toString().length() > this.getMaxIdLen()) {
				e.setId(e.getId().toString()
						.substring(0, this.getMaxIdLen() - 1));
			}
			if (existsElement(e)) {
				log.info("The element {} exists!", e);
				return false;
			}
			storeElement(e);
			return true;
		} else {
			return false;
		}

	}

	private boolean existsElement(IElement e) {

		if (e.getId() == null) {
			log.warn("NULL element ID!");
			return false;
		}
		StringBuffer sqlSB = new StringBuffer().append("SELECT count(0) FROM ")
				.append(tableName).append(" WHERE ")
				.append(this.idTableColumnName).append(" = :id ");

		String sql = sqlSB.toString();

		log.trace("Exist SQL:{}", sql);

		Map<String, String> namedParameters = Collections.singletonMap("id", e
				.getId().toString());

		int result = this.namedParameterJdbcTemplate.queryForInt(sql,
				namedParameters);

		return (result != 0);
	}

	private void storeElement(IElement e) {
		if (e.getId() == null) {
			log.warn("NULL element ID!");
			return;
		}
		StringBuffer sqlSB = new StringBuffer().append("INSERT INTO ")
				.append(tableName).append("(").append(this.idTableColumnName)
				.append(")").append(" VALUES (:id) ");

		Map<String, String> namedParameters = Collections.singletonMap("id", e
				.getId().toString());

		this.namedParameterJdbcTemplate.update(sqlSB.toString(),
				namedParameters);
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getIdTableColumnName() {
		return idTableColumnName;
	}

	public void setIdTableColumnName(String idTableColumnName) {
		this.idTableColumnName = idTableColumnName;
	}

	public int getMaxIdLen() {
		return maxIdLen;
	}

	public void setMaxIdLen(int maxIdLen) {
		this.maxIdLen = maxIdLen;
	}

}
