package az.asifkazim.bol.service.impl;

import az.asifkazim.bol.entity.Product;
import az.asifkazim.bol.repository.ProductRepository;
import az.asifkazim.bol.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        super();
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAllByOrderByProductIdAsc();
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.findByNameContains(name);
    }

    @Override
    public List<Product> getAllProductsForCatalog(String status) {
        return productRepository.findByStatusOrderByProductIdAsc(status);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllCatalogsByName(String status, String name) {
        return productRepository.findByStatusAndNameContains(status, name);
    }

}
