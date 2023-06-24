package com.learning.core.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.learning.core.models.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long> {

	Optional<Picture> findByFilename(String filename);
}
