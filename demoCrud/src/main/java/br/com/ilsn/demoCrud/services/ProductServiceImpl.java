package br.com.ilsn.demoCrud.services;

import org.springframework.stereotype.Service;

import br.com.ilsn.demoCrud.models.Product;
import br.com.ilsn.demoCrud.repositories.ProductRepository;

@Service
public class ProductServiceImpl extends GenericServiceImpl<Product> implements ProductService{

	public ProductServiceImpl(ProductRepository repository) {
		super(repository);

	}

}
