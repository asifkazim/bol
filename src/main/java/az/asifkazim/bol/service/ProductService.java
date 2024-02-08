package az.asifkazim.bol.service;


import az.asifkazim.bol.entity.Product;

import java.util.List;

public interface ProductService {
	List<Product> getAllProducts();

	List<Product> getAllProductsByName(String name);

	List<Product> getAllProductsForCatalog(String status);

	Product saveProduct(Product product);
	
	Product getProductById(Long id);
	
	Product updateProduct(Product product);
	
	void deleteProductById(Long id);

	List<Product> getAllCatalogsByName(String name,String status);
}
