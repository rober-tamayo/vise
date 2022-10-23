package net.codejava;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findAllByBrand(String brand);
	
	List<Product> findByPriceGreaterThanEqual(float price);
	
}
