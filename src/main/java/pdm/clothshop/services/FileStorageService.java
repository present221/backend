package pdm.clothshop.services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
@Service
public class FileStorageService implements StorageService {
    private final Path root = Paths.get("image");
    @Override
    public void init() {
        try{
            Files.createDirectory(root);
        }catch (IOException e){
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


    @Override
    public void store(MultipartFile file) {
        try{
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new RuntimeException("Could not store the image file." + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {
        try{
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if(resource.exists() || resource.isReadable()){
                return resource;
            }else {
                throw new RuntimeException("Could not read the image file.");
            }
        }catch (MalformedURLException e){
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(String filename){
        try {
            Files.delete(Paths.get(this.root+"/"+filename));
        } catch (IOException e) {
            throw new RuntimeException("Could not delete the image file.");
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll() {
        try{
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        }catch (IOException e){
            throw new RuntimeException("Could not load image files.");
        }
    }

    public ResponseEntity<byte[]> readImage(String filename){
//        System.out.println("Filename : " + filename);
        try {
            FileInputStream fileInputStream = new FileInputStream("image" + "/" + filename);
            byte[] image = fileInputStream.readAllBytes();
            fileInputStream.close();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
        } catch (FileNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The image not found");
        }
        catch (IOException e) {
            System.out.println("IO Exception"+e);
            return null;
        }
    }
}