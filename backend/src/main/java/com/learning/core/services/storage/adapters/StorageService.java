package com.learning.core.services.storage.adapters;

import org.springframework.web.multipart.MultipartFile;
import com.learning.core.models.Picture;
import com.learning.core.services.storage.exceptions.StorageServiceException;

public interface StorageService {

    Picture save(MultipartFile file) throws StorageServiceException;

    void remove(String filename) throws StorageServiceException;

}