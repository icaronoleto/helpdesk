package br.com.ilsn.demoCrud.services;

import br.com.ilsn.demoCrud.models.Role;

public interface RoleService extends GenericService<Role>{

	public Role findByName(String name);
}
