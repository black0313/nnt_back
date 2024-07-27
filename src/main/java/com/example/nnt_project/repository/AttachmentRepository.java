package com.example.nnt_project.repository;


import com.example.nnt_project.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AttachmentRepository extends JpaRepository<Attachment, UUID> {

    List<Attachment> findAllByName(String name);
    Optional<Attachment> findByName(String name);
    Optional<Attachment> findAllById(UUID id);
    void deleteByName(String name);
}
