package com.example.githubstarredrepositorytrackerback.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.githubstarredrepositorytrackerback.model.GithubRepository;
import com.example.githubstarredrepositorytrackerback.model.Owner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GithubRepositoryServiceTest {

	@Autowired
	private GithubRepositoryService service;

	@Autowired
	private OwnerService ownerService;

	@Test
	public void shouldReturnAllGithubRepository() throws Exception {

		// prapare
		final Long gitId = 1L;
		createGithubRepository(gitId);

		// execute
		List<GithubRepository> githubRepositories = service.getAll();

		// validate
		assertNotNull(githubRepositories);

	}

	@Test
	public void shouldReturnAllGithubRepositoryByLanguage() throws Exception {
		String language = "language";

		// execute
		List<GithubRepository> githubRepositories = service.getAllByLanguage(language);

		// validate
		assertNotNull(githubRepositories);

	}
	
	@Test
	public void shouldNotReturnAllGithubRepositoryByLanguageIsNotRecords() throws Exception {
		String language = "languageNotFound";

		// execute
		List<GithubRepository> githubRepositories = service.getAllByLanguage(language);

		// validate
		assertNotNull(githubRepositories);

	}


	private void createGithubRepository(final Long gitId) throws Exception {
		Owner owner = new Owner(gitId, "name", "avatar", "type", "url");
		owner = ownerService.save(owner);
		GithubRepository githubRepository = new GithubRepository(gitId, "name", gitId, "language", gitId, "url", owner);
		service.save(githubRepository);

	}
}
