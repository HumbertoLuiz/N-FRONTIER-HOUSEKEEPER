package br.com.xfrontier.housekeeper.core.services.storage.providers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.xfrontier.housekeeper.api.controllers.StorageRestController;
import br.com.xfrontier.housekeeper.core.models.Picture;
import br.com.xfrontier.housekeeper.core.repository.PictureRepository;
import br.com.xfrontier.housekeeper.core.services.storage.adapters.StorageService;
import br.com.xfrontier.housekeeper.core.services.storage.exceptions.StorageServiceException;

@Service
public class LocalStorageService implements StorageService {

	private final Path folderUpload = Paths.get("uploads");

	@Autowired
	private PictureRepository pictureRepository;

	@Override
	public Picture save(MultipartFile file) throws StorageServiceException {
		try {
			return trySave(file);
		} catch (IOException exception) {
			throw new StorageServiceException(exception.getLocalizedMessage());
		}
	}

	public Resource findPicture(String filename) {
		var file = folderUpload.resolve(filename);
		try {
			return new UrlResource(file.toUri());
		} catch (MalformedURLException e) {
		throw new StorageServiceException(e.getLocalizedMessage());
		}
	}

	private Picture trySave(MultipartFile file) throws IOException {
		if (!Files.exists(folderUpload)) {
			Files.createDirectories(folderUpload);
		}
		var picture = generateModelPicture(file);
		Files.copy(file.getInputStream(), folderUpload.resolve(picture.getFilename()));
		return pictureRepository.save(picture);
	}

    private Picture generateModelPicture(MultipartFile file) throws IOException {
		var picture = new Picture();
		var filename = generateNewFilename(file.getOriginalFilename());
        var url= linkTo(methodOn(StorageRestController.class).findPicture(filename)).toString();
		picture.setFilename(filename);
		picture.setContentLength(file.getSize());
		picture.setContentType(file.getContentType());
        picture.setUrl(url);
		return picture;
	}

    private String generateNewFilename(String filenameOriginal) {
        var ext= filenameOriginal.split("\\.")[1];
        var filename= UUID.randomUUID().toString() + "." + ext;
        return filename;
    }

	@Override
	public void remove(String filename) throws StorageServiceException {
	}

}
