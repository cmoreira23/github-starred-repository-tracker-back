package com.example.githubstarredrepositorytrackerback.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.githubstarredrepositorytrackerback.model.GithubRepository;
import com.example.githubstarredrepositorytrackerback.model.GithubRepositoryEntryLog;
import com.example.githubstarredrepositorytrackerback.service.GithubRepositoryService;

/**
 * @author Camilla Cyrino
 *
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class GithubRepositoryController {

	@Autowired
	private GithubRepositoryService service;

	@GetMapping("/githubRepositories")
	public ResponseEntity<List<GithubRepository>> getAllGithubRepositories(
			@RequestParam(required = false) String language) {
		try {
			List<GithubRepository> githubRepositories = service.getAll(language);

			if (githubRepositories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(githubRepositories, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/refresh")
	public ResponseEntity<HttpStatus> refreshGithubRepositories(@RequestParam(required = true) String language,
			@RequestParam(required = false) Integer page) {
		try {
			service.refreshGithubRepositories(language, page);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/githubRepositoriesLog")
	public ResponseEntity<List<GithubRepositoryEntryLog>> getGithubRepositoryLog() {
		try {
			List<GithubRepositoryEntryLog> log = service.getGithubRepositoryLog();
			return new ResponseEntity<>(log, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
