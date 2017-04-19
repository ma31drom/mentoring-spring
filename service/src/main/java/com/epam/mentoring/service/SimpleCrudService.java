package com.epam.mentoring.service;

import java.util.List;

public interface SimpleCrudService<T> {

	boolean create(T obj);

	int createBatch(List<T> objs);

	boolean delete(T obj);

	boolean update(T obj);

	T get(Integer id);

}
