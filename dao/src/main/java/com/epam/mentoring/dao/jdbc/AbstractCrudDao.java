package com.epam.mentoring.dao.jdbc;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.epam.mentoring.dao.CrudOpertaions;
import com.epam.mentoring.dao.model.IdAwareModel;
import com.google.common.base.CaseFormat;

public abstract class AbstractCrudDao<T extends IdAwareModel> implements CrudOpertaions<T>, InitializingBean {

	@Autowired
	private JdbcTemplate template;

	@Autowired
	private DataSourceTransactionManager txm;

	@Override
	public boolean create(T obj) {

		Map<String, String> params = getPropertiesAsMap(obj);
		String columns = params.keySet().stream().map(t -> "\"" + t + "\"").collect(Collectors.joining(","));
		String sqlParamKeys = params.keySet().stream().map(params::get).collect(Collectors.joining(","));
		String sql = new StringBuilder("INSERT INTO \"").append(getTableName()).append("\" (").append(columns)
				.append(") VALUES (").append(sqlParamKeys).append(")").toString();

		return 1 == template.update(sql);
	}

	@Override
	@Transactional
	public int createBatch(List<T> objs) {
		return objs.stream().filter(this::create).collect(Collectors.toList()).size();
	}

	@Override
	public boolean update(T obj) {
		Map<String, String> params = getPropertiesAsMap(obj);
		return 1 == template.update(new StringBuilder("UPDATE \"").append(getTableName()).append("\" SET ")
				.append(params.keySet().stream().filter(t -> "ID".equals(t)).map(t -> "\"" + t + "\"=" + params.get(t))
						.collect(Collectors.joining(", ")))
				.append(" WHERE \"ID\"=").append(params.get("ID")).toString());
	}

	@Override
	public T get(Integer id) {
		return template.queryForObject(selectStringBuilder().append(" WHERE \"ID\"=").append(id.toString()).toString(),
				new BeanPropertyRowMapper<T>(getModelClass()));
	}

	@Override
	public boolean delete(T obj) {
		return 1 == template.update(new StringBuilder("DELETE FROM \"").append(getTableName())
				.append("\" WHERE \"ID\"=").append(obj.getId().toString()).toString());
	}

	private Map<String, String> getPropertiesAsMap(T obj) {
		BeanPropertySqlParameterSource beanPropertySqlParameterSource = new BeanPropertySqlParameterSource(obj);
		String[] readablePropertyNames = beanPropertySqlParameterSource.getReadablePropertyNames();
		return Arrays.stream(readablePropertyNames).filter(t -> !"class".equalsIgnoreCase(t))
				.collect(Collectors.toMap(this::underscoredUppercase, name -> {
					Object value = beanPropertySqlParameterSource.getValue(name);
					String valueOf = String.valueOf(value);
					if (value instanceof String) {
						return "'" + valueOf + "'";
					}
					if (value instanceof Timestamp) {
						return "parsedatetime('" + valueOf + "', 'yyyy-MM-dd hh:mm:ss.S')";
					}
					return valueOf;
				}));
	};

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(template, "JdbcTemplate must not be null");
		Assert.notNull(txm, "This DAO needs tx manager for work");
	}

	protected abstract Class<T> getModelClass();

	protected abstract String getTableName();

	protected StringBuilder selectStringBuilder() {
		return new StringBuilder("SELECT * FROM \"").append(getTableName()).append("\"");
	}

	private String underscoredUppercase(String string) {
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, string);
	}
}
