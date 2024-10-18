package br.com.xfrontier.housekeeper.core.listeners;

import jakarta.persistence.PreRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.xfrontier.housekeeper.core.models.Picture;
import br.com.xfrontier.housekeeper.core.services.storage.adapters.StorageService;

@Component
public class PictureEntityListener {

    @Autowired
    private static StorageService storageService;


    public void setStorageService(StorageService storageService) {
        PictureEntityListener.storageService = storageService;
    }

    @PreRemove
    private void preRemove(Picture picture) {
        storageService.remove(picture.getFilename());
    }

}
