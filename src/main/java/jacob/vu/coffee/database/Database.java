package jacob.vu.coffee.database;

import jacob.vu.coffee.models.Product;
import jacob.vu.coffee.repositories.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    private static final Logger logger = LoggerFactory.getLogger(Database.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository){
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Product productA = new Product("Iphone 14", 2022, 800.0, "");
                Product productB = new Product("VSmart Pro", 2021, 900.0, "");
                logger.info("insert database" + productRepository.save(productA));
                logger.info("insert database" + productRepository.save(productB));
            }
        };
    }
}
