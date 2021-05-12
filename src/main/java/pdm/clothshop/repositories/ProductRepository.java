package pdm.clothshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pdm.clothshop.models.Product;



public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value ="select MAX(productId) from Product")
    public Integer lastedProductId();
}
