package br.com.ilsn.demoCrud.services;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ilsn.demoCrud.repositories.GenericRepository;


public abstract class GenericServiceImpl<T> implements GenericService<T>{

	@Autowired
	private GenericRepository<T> repository;
	
	public GenericServiceImpl(GenericRepository<T> repository) {
		this.repository = repository;
	}
	
	@Override
	public List<T> findAll() {
		return this.repository.findAll();
	}

	@Override
	public T create(T obj) {
		return this.repository.save(obj);
	}

	@Override
	public Boolean delete(Long id) {
		T obj = findById(id);
		
		if(obj != null) {
			this.repository.delete(obj);
			return true;
		}
		
		return false;
	}

	@Override
	public T show(Long id) {
		return findById(id);
	}

	@Override
	public T update(Long id, T obj) throws Exception {
		T objExists = findById(id);
		
		if (objExists != null) {
			for(Field f: obj.getClass().getDeclaredFields()) {
				try {
					String methodNameGet = "get"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					String methodNameSet = "set"+f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					Object value = obj.getClass().getMethod(methodNameGet, null).invoke(obj, null);
					objExists.getClass().getMethod(methodNameSet, obj.getClass().getDeclaredField(f.getName()).getType()).invoke(objExists, value);
				}catch (Exception e) {
					throw new Exception("Update Error: "+e.getMessage());
				}
			}
			
			this.repository.save(objExists);
			
			return obj;
		}
		
		return null;
	}
	
	private T findById(Long id) {
		return this.repository.findById(id).orElse(null);
	}

}
