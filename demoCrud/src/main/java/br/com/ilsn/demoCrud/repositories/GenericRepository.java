package br.com.ilsn.demoCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


@NoRepositoryBean
public interface GenericRepository<T> extends JpaRepository<T, Long> {

}
