package br.com.ilsn.demoCrud.services;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ilsn.demoCrud.models.Role;
import br.com.ilsn.demoCrud.models.User;
import br.com.ilsn.demoCrud.repositories.GenericRepository;
import br.com.ilsn.demoCrud.repositories.RolesRepository;
import br.com.ilsn.demoCrud.repositories.UserRepository;

@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{

	/*@Autowired
	private UserRepository repository;
	@Autowired
	private RolesRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, RolesRepository roleRepository) {
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public List<User> findAll() {
		return this.repository.findAll();
	}

	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role userRole = this.roleRepository.findByName("USER");
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		
		return this.repository.save(user);
	}

	@Override
	public Boolean delete(Long id) {
		User user = findById(id);
		
		if(user != null) {
			this.repository.delete(user);
			return true;
		}
		
		return false;
	}

	@Override
	public User show(Long id) {
		return findById(id);
	}

	@Override
	public Boolean update(Long id, User user) {
		User userExists = findById(id);
		
		if (userExists != null) {
			userExists.setId(user.getId());
			userExists.setName(user.getName());
			userExists.setLastName(user.getLastName());
			userExists.setEmail(user.getEmail());
			userExists.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
			userExists.setActive(user.getActive());
			
			Role userRole = this.roleRepository.findByName(user.getRoles().iterator().next().getName());
			
			userExists.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
			
			this.repository.save(userExists);
			
			return true;
		}
		
		return false;
	}*/
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RolesRepository roleRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, RolesRepository roleRepository) {
		super(repository);
		this.repository = repository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public User create(User user) {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		
		Role userRole = this.roleRepository.findByName("USER");
		
		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		return super.create(user);
	}
	
	@Override
	public User update(Long id, User user) throws Exception {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		return super.update(id, user);
	}

	@Override
	public List<User> findAllWhereRoleEquals(Long role_id, Long user_id) {
		return this.repository.findAllWhereRoleEquals(role_id, user_id);
	}	

	@Override
	public User findCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		String username = auth.getName();
		
		User userLogged = this.repository.findByEmail(username);
		
		return userLogged;
	}
}
