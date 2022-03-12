package br.com.ilsn.demoCrud.services;

import java.util.List;

public interface GenericService<T> {

	public List<T> findAll();
	public T create(T obj);
	public Boolean delete(Long id);
	public T update(Long id, T obj) throws Exception;
	/*
	 * Return T element by id
	 */
	public T show(Long id);
}
