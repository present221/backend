package pdm.clothshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pdm.clothshop.models.Brand;

public interface BrandRepository extends JpaRepository<Brand,Integer>{
}
