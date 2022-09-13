package jacob.vu.coffee.repositories;

import jacob.vu.coffee.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
