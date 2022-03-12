package br.com.ilsn.demoCrud.repositories;

import org.springframework.stereotype.Repository;

import br.com.ilsn.demoCrud.models.Role;

@Repository
public interface RolesRepository extends GenericRepository<Role> {
	Role findByName(String name);
}
