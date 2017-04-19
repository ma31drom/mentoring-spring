package com.epam.mentoring.service.impl;

import java.util.List;

import com.epam.mentoring.dao.CrudOpertaions;
import com.epam.mentoring.dao.model.IdAwareModel;
import com.epam.mentoring.service.SimpleCrudService;

public abstract class AbstractCrudService<T extends IdAwareModel> implements SimpleCrudService<T> {

	@Override
	public boolean create(T obj) {
		return getDao().create(obj);
	}

	@Override
	public int createBatch(List<T> objs) {
		return getDao().createBatch(objs);
	}

	@Override
	public boolean delete(T obj) {
		return getDao().delete(obj);
	}

	@Override
	public boolean update(T obj) {
		return getDao().update(obj);
	}

	@Override
	public T get(Integer id) {
		return getDao().get(id);
	}

	protected abstract CrudOpertaions<T> getDao();
}
