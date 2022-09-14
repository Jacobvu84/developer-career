package jacob.vu.coffee.repositories;

import jacob.vu.coffee.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Name method needs to follow convention: findBy<nameAttribute>
    List<Product> findByName(String name);

    List<Product> findByProductId(Long id);

}
