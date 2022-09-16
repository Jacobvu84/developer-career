package jacob.vu.coffee.services;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageService implements IStorageService {

    public final Path rootLocation = Paths.get("uploads");

    public ImageStorageService(){
        try {
            Files.createDirectories(rootLocation);
        }catch (IOException e){
            throw new RuntimeException("Can't initialize storage",e);
        }
    }

    private boolean isImageFile(MultipartFile file){
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("haha");
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            //check file is image ?
            if(!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file");
            }
            //file must be <= 5Mb
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if(fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <= 5Mb");
            }
            //File must be rename, why ?
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName+"."+fileExtension;
            Path destinationFilePath = this.rootLocation.resolve(
                            Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new RuntimeException(
                        "Cannot store file outside current directory.");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }
        catch (IOException exception) {
            throw new RuntimeException("Failed to store file.", exception);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public byte[] loadAsResource(String filename) {
        return new byte[0];
    }

    @Override
    public void deleteAll() {

    }
}
