package az.asifkazim.bol.repository;


import az.asifkazim.bol.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findAllByOrderByProductIdAsc();

    List<Product> findByStatusOrderByProductIdAsc(String status);

    List<Product> findByNameContains(String status);

    List<Product> findByStatusAndNameContains(String status, String name);
}
