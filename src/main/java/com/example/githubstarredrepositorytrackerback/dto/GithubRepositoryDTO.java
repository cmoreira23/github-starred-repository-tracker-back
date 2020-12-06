package com.example.githubstarredrepositorytrackerback.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import com.example.githubstarredrepositorytrackerback.model.GithubRepository;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepositoryDTO implements Serializable {

	private static final long serialVersionUID = -37528865461927222L;

	@JsonProperty(value = "total_count")
	private String totalCount;

	@JsonProperty(value = "incomplete_results")
	private Boolean incompleteResults;

	@JsonProperty(value = "items")
	private List<GithubRepository> githubRepository;

	/**
	 * @return the totalCount {@link String}
	 */
	public String getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set {@link String}
	 */
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the incompleteResults {@link Boolean}
	 */
	public Boolean getIncompleteResults() {
		return incompleteResults;
	}

	/**
	 * @param incompleteResults the incompleteResults to set {@link String}
	 */
	public void setIncompleteResults(Boolean incompleteResults) {
		this.incompleteResults = incompleteResults;
	}

	/**
	 * @return the githubRepository {@link List} <{@link GithubRepository}>
	 */
	public List<GithubRepository> getGithubRepository() {
		return githubRepository;
	}

	/**
	 * @param githubRepository the githubRepository to set {@link List}
	 *                         <{@link GithubRepository}>
	 */
	public void setGithubRepository(List<GithubRepository> githubRepository) {
		this.githubRepository = githubRepository;
	}

	@Override
	public int hashCode() {
		return Objects.hash(githubRepository);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GithubRepositoryDTO))
			return false;
		GithubRepositoryDTO other = (GithubRepositoryDTO) obj;
		return Objects.equals(githubRepository, other.githubRepository);
	}

}
