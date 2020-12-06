package com.example.githubstarredrepositorytrackerback.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.githubstarredrepositorytrackerback.model.GithubRepository;

/**
 * @author Camilla Cyrino
 *
 */
public interface GithubRepositoryRepository extends JpaRepository<GithubRepository, Long> {

	Optional<GithubRepository> findByGitId(final Long gitId);

	List<GithubRepository> findByLanguage(final String language);

}
