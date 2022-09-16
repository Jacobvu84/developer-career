package jacob.vu.coffee.services;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface IStorageService {

    public String storeFile(MultipartFile file);
    public Stream<Path> loadAll();

    public Path load(String filename);

    // download file
    // Resource loadAsResource(String filename);
    // view on browser
    byte[] loadAsResource(String filename);

    void deleteAll();

}
