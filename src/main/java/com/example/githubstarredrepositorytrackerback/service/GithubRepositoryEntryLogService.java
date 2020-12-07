package com.example.githubstarredrepositorytrackerback.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.githubstarredrepositorytrackerback.model.GithubRepositoryEntryLog;
import com.example.githubstarredrepositorytrackerback.repository.GithubRepositoryEntryLogRepository;

/**
 * @author Camilla Cyrino
 *
 */
@Service
public class GithubRepositoryEntryLogService {

	private static final Logger log = LoggerFactory.getLogger(GithubRepositoryEntryLogService.class);

	@Autowired
	GithubRepositoryEntryLogRepository repository;

	/**
	 * @param language
	 * @param page
	 * @param hash
	 * @return
	 */
	public boolean isLogChanged(final String language, final Integer page, final int hash) {
		GithubRepositoryEntryLog log = repository.findByLanguageAndPage(language, page);
		if (log != null && (log.getHash() == hash)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param language
	 * @param page
	 * @param hash
	 * @throws Exception
	 */
	public void saveOrUpdateLog(final String language, final Integer page, final int hash) throws Exception {
		try {
			GithubRepositoryEntryLog log = repository.findByLanguageAndPage(language, page);
			if (log == null) {
				log = new GithubRepositoryEntryLog();
				log.setLanguage(language);
				log.setPage(page);
			}
			log.setHash(hash);
			log.setGenarateDate(LocalDateTime.now());
			repository.saveAndFlush(log);
		} catch (Exception e) {
			log.error("Error trying to create or update a LOG of language: %s  and  page: %d \\n Error --> %s",
					language, page, e);
			throw new Exception(e);
		}
	}

	/**
	 * Returns all GithubRepositoryEntryLog
	 * 
	 * @return GithubRepositoryEntryLog
	 *         {@link List}<{@link GithubRepositoryEntryLog}>
	 */
	public List<GithubRepositoryEntryLog> getAll() {
		return repository.findAll();
	}
}
