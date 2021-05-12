package pdm.clothshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pdm.clothshop.models.Color;

public interface ColorRepository extends JpaRepository<Color,String> {

}
