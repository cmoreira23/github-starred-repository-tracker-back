package com.example.githubstarredrepositorytrackerback.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.githubstarredrepositorytrackerback.model.Owner;

/**
 * @author Camilla Cyrino
 *
 */
public interface OwnerRepository extends JpaRepository<Owner, Long> {

	Optional<Owner> findByGitId(final Long gitId);
}
