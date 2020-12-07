package com.example.githubstarredrepositorytrackerback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.githubstarredrepositorytrackerback.model.GithubRepository;

/**
 * @author Camilla Cyrino
 *
 */
public interface GithubRepositoryRepository extends JpaRepository<GithubRepository, Long> {
	
	@Query("SELECT g FROM GithubRepository g WHERE g.gitId = ?1 ")
	GithubRepository findByGitId(final Long gitId);

	@Query("SELECT g FROM GithubRepository g WHERE g.language = ?1 ")
	List<GithubRepository> findByLanguage(final String language);

}
