/**
 * 
 */
package com.example.githubstarredrepositorytrackerback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.example.githubstarredrepositorytrackerback.dto.GithubRepositoryDTO;
import com.example.githubstarredrepositorytrackerback.model.GithubRepository;
import com.example.githubstarredrepositorytrackerback.model.GithubRepositoryEntryLog;
import com.example.githubstarredrepositorytrackerback.model.Owner;
import com.example.githubstarredrepositorytrackerback.repository.GithubRepositoryRepository;

/**
 * @author Camilla Cyrino
 *
 */
@Service
public class GithubRepositoryService {

	private static final Logger log = LoggerFactory.getLogger(GithubRepositoryService.class);

	@Autowired
	private GithubRepositoryRepository repository;

	@Autowired
	private GithubRepositoryEntryLogService entryLogService;

	@Autowired
	private OwnerService ownerService;

	/**
	 * Returns all GithubRepository
	 * 
	 * @return githubRepositories {@link List}<{@link GithubRepository}>
	 * @throws Exception {@link Exception}
	 */
	public List<GithubRepository> getAll() throws Exception {
		return repository.findAll();
	}

	/**
	 * Retrieves an GithubRepository by its id.
	 * 
	 * @param id {@link Long}
	 * @return githubRepository {@link Optional}<{@link GithubRepository}>
	 * @throws Exception {@link Exception}
	 */
	public Optional<GithubRepository> getById(final Long id) throws Exception {
		return repository.findById(id);
	}

	/**
	 * Saves GithubRepository entity
	 * 
	 * @param githubRepository {@link GithubRepository}
	 * @throws Exception {@link Exception}
	 */
	public void save(final GithubRepository githubRepository) throws Exception {
		try {
			repository.saveAndFlush(githubRepository);
		} catch (Exception e) {
			log.error("Error trying to create or update GithubRepository with gitId: %d\\n Error --> %s\\n",
					githubRepository.getGitId(), e);
			throw new Exception(e);
		}
	}

	/**
	 * Deletes the GithubRepository with the given id
	 * 
	 * @param id {@link Long}
	 * @throws Exception {@link Exception}
	 */
	public void deleteById(final Long id) throws Exception {
		repository.deleteById(id);
	}

	/**
	 * Deletes all GithubRepository
	 * 
	 * @throws Exception {@link Exception}
	 */
	public void deleteAll() throws Exception {
		repository.deleteAll();
	}

	/**
	 * Deletes a given GithubRepository
	 * 
	 * @param githubRepository {@link GithubRepository}
	 * @throws Exception {@link Exception}
	 */
	public void deleteByGithubRepository(final GithubRepository githubRepository) throws Exception {
		repository.delete(githubRepository);
	}

	/**
	 * Returns all GithubRepository by its language
	 * 
	 * @param language {@link String}
	 * @return githubRepositories {@link List}<{@link GithubRepository}>
	 * @throws Exception {@link Exception}
	 */
	public List<GithubRepository> getAllByLanguage(final String language) throws Exception {
		return repository.findByLanguage(language.toLowerCase());
	}

	/**
	 * Returns all GithubRepository and filter by language was not null
	 * 
	 * @param language {@link String}
	 * @return githubRepositories {@link List}<{@link GithubRepository}>
	 * @throws Exception {@link Exception}
	 */
	public List<GithubRepository> getAll(final String language) throws Exception {
		List<GithubRepository> githubRepositories = new ArrayList<>();
		if (language == null)
			getAll().forEach(githubRepositories::add);
		else
			getAllByLanguage(language).forEach(githubRepositories::add);

		return githubRepositories;
	}

	/**
	 * Create or update a GithubRepository
	 * 
	 * @param elements {@link GithubRepository}
	 */
	private void createOrUptadeGithubRepository(GithubRepository elements) {
		try {
			Owner owner = ownerService.createOrUpdate(elements.getOwner());
			GithubRepository githubRepository = elements;
			githubRepository.setLanguage(elements.getLanguage().toLowerCase());
			Boolean update = false;
			GithubRepository githubRepositoryDB = getGithubRepositoryFromDB(elements, update, owner);

			if ((githubRepositoryDB == null) || update)
				save(githubRepository);
		} catch (Exception e) {
			log.error("Error trying to create or update owner with gitId:" + elements.getGitId(), e);
		}
	}

	/**
	 * Retrieves a GithubRepository by its GitIds and if there are changes update
	 * 
	 * @param elements {@link GithubRepository}
	 * @param update   {@link Boolean}
	 * @param owner    {@link Owner}
	 * @return githubRepository {@link Optional}<{@linkGithubRepository}>
	 */
	private GithubRepository getGithubRepositoryFromDB(GithubRepository elements, Boolean update, Owner owner) {
		GithubRepository githubRepositoryDB = repository.findByGitId(elements.getGitId());
		if (githubRepositoryDB != null) {
			GithubRepository githubRepository = githubRepositoryDB;
			update = isChanged(elements, githubRepository, update, owner);
			if (update)
				prepareToUpdate(elements, owner, githubRepository);
		}
		return githubRepositoryDB;
	}

	/**
	 * Prepares GithubRepository to update
	 * 
	 * @param elements         {@link GithubRepository}
	 * @param owner            {@link Owner}
	 * @param githubRepository {@link GithubRepository}
	 */
	private void prepareToUpdate(GithubRepository elements, Owner owner, GithubRepository githubRepository) {
		githubRepository.setForksCount(elements.getForksCount());
		githubRepository.setLanguage(elements.getLanguage());
		githubRepository.setName(elements.getName());
		githubRepository.setOwner(owner);
	}

	/**
	 * Checks if the GithubRepository has been changed
	 * 
	 * @param elements         {@link GithubRepository}
	 * @param githubRepository {@link GithubRepository}
	 * @param update           {@link Boolean}
	 * @return update {@link Boolean}
	 */
	private Boolean isChanged(GithubRepository elements, GithubRepository githubRepository, Boolean update,
			final Owner owner) {
		if (!githubRepository.equals(elements))
			update = true;
		return update;
	}

	/**
	 * Connect to githubAPI
	 * 
	 * @return {@link List}<{@linkGithubRepository}>
	 */
	private GithubRepositoryDTO getGithubRepositoryFromGithubAPI(final String language, final Integer page)
			throws HttpClientErrorException {
		try {
			RestTemplateBuilder builder = new RestTemplateBuilder();
			builder.build();
			RestTemplate restTemplate = new RestTemplate();

			String url = "https://api.github.com/search/repositories?q=language:" + language + "&sort=stars&page="
					+ page;
			ResponseEntity<GithubRepositoryDTO> response = restTemplate.exchange(url, HttpMethod.GET, null,
					new ParameterizedTypeReference<GithubRepositoryDTO>() {
					});
			if (response.getStatusCode() != HttpStatus.OK) {
				return null;
			}
			return response.getBody();

		} catch (HttpClientErrorException e) {
			log.error("Error trying to consumes github API \\n  Error --->", e);
			throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
		}
	}

	/**
	 * Consumes Github Api to update GithubRepositories
	 * 
	 * @param language {@link String}
	 * @throws Exception {@link Exception}
	 */
	public void refreshGithubRepositories(final String language) throws Exception {
		GithubRepositoryDTO repositoryDTO = new GithubRepositoryDTO();
		Integer pageCount = 1;
		while (repositoryDTO != null && pageCount <= 30) {
			repositoryDTO = getGithubRepositoryFromGithubAPI(language, pageCount);
			if (repositoryDTO != null) {
				int hash = repositoryDTO.hashCode();
				boolean update = entryLogService.isLogChanged(language, pageCount, hash);
				if (update) {
					getGithubRepositories(repositoryDTO);
					entryLogService.saveOrUpdateLog(language, pageCount, hash);
				}
			}
			pageCount++;
		}
	}

	/**
	 * @param repositoryDTO
	 */
	private void getGithubRepositories(GithubRepositoryDTO repositoryDTO) {
		List<GithubRepository> githubRepositories = repositoryDTO.getGithubRepository();
		githubRepositories.forEach(this::createOrUptadeGithubRepository);
	}

	/**
	 * Returns all GithubRepositoryEntryLog
	 * 
	 * @return GithubRepositoryEntryLog
	 *         {@link List}<{@link GithubRepositoryEntryLog}>
	 */
	public List<GithubRepositoryEntryLog> getGithubRepositoryLog() {
		return entryLogService.getAll();
	}
}
