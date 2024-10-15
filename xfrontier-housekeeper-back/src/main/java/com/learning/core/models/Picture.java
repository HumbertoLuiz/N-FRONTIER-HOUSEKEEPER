package com.learning.core.models;

import java.io.Serializable;

import com.learning.core.listeners.PictureEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@EntityListeners(PictureEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
@Table(name = "picture")
public class Picture extends IdBaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Column(nullable = false, unique = true)
    private String filename;

    @Column(name = "content_length", nullable = false)
    private Long contentLength;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(nullable = false)
    private String url;
}
