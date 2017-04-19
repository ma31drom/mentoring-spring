package com.epam.mentoring.dao;

import java.util.List;

import com.epam.mentoring.dao.model.IdAwareModel;

public interface CrudOpertaions<T extends IdAwareModel> {

	boolean create(T obj);

	int createBatch(List<T> objs);

	boolean delete(T obj);

	boolean update(T obj);

	T get(Integer id);

}
