package com.learning.core.listeners;

import javax.persistence.PreRemove;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learning.core.models.Picture;
import com.learning.core.services.storage.adapters.StorageService;

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