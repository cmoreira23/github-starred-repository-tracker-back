package com.example.githubstarredrepositorytrackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.githubstarredrepositorytrackerback.model.GithubRepositoryEntryLog;

/**
 * @author Camilla Cyrino
 *
 */
public interface GithubRepositoryEntryLogRepository extends JpaRepository<GithubRepositoryEntryLog, Long> {
	@Query("SELECT g FROM GithubRepositoryEntryLog g WHERE g.hash = ?1 ")
	GithubRepositoryEntryLog findByHash(final Long hash);

	@Query("SELECT g FROM GithubRepositoryEntryLog g WHERE g.language = ?1  and g.page= ?2 ")
	GithubRepositoryEntryLog findByLanguageAndPage(final String language, final Integer page);
}
