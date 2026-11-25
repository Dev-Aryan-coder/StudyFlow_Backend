package com.example.StudyFlow.repository.mongo;

import com.example.StudyFlow.document.WebsiteLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebsiteLayoutRepository extends MongoRepository<WebsiteLayout, String> {

    Optional<WebsiteLayout> findByProjectId(String projectId);

    Optional<WebsiteLayout> findBySlug(String slug);

    boolean existsBySlug(String slug);

    boolean existsByProjectId(String projectId);

    void deleteByProjectId(String projectId);
}
