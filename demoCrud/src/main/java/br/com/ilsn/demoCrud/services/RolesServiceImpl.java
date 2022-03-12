package br.com.ilsn.demoCrud.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ilsn.demoCrud.models.Role;
import br.com.ilsn.demoCrud.repositories.RolesRepository;

@Service
public class RolesServiceImpl extends GenericServiceImpl<Role> implements RoleService {

	@Autowired
	private RolesRepository repository;
	
	public RolesServiceImpl(RolesRepository repository) {
		super(repository);
		this.repository = repository;
	}
	
	@Override
	public Role create(Role role) {
		role.setName(role.getName().toUpperCase());
		return super.create(role);
	}

	@Override
	public Role findByName(String name) {
		return this.repository.findByName(name);
	}
}
