package com.example.githubstarredrepositorytrackerback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.githubstarredrepositorytrackerback.model.Owner;

/**
 * @author Camilla Cyrino
 *
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {
	@Query("SELECT o FROM Owner o WHERE o.gitId = ?1 ")
	Owner findByGitId(final Long gitId);
}
