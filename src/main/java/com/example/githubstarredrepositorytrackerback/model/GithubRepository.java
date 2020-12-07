package com.example.githubstarredrepositorytrackerback.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository implements Serializable {

	private static final long serialVersionUID = 6624106349129096682L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(nullable = false)
	@JsonProperty(value = "id")
	private Long gitId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	@JsonProperty(value = "forks_count")
	private Long forksCount;

	@Column(nullable = false)
	private String language;

	@Column(nullable = false)
	@JsonProperty(value = "stargazers_count")
	private Long stargazersCount;

	@Column(nullable = false)
	private String url;

	@JoinColumn(name = "owner_id")
	@ManyToOne(cascade = {CascadeType.ALL})	
	private Owner owner;

	/**
	 * Constructor without params
	 * 
	 */
	public GithubRepository() {
	}

	/**
	 * Constructor with params
	 * 
	 * @param name            {@link String}
	 * @param forksCount      {@link Long}
	 * @param language        {@link String}
	 * @param stargazersCount {@link Long}
	 * @param url             {@link String}
	 * @param owner           {@link Owner}
	 */
	public GithubRepository(final Long gitId, final String name, final Long forksCount, final String language,
			final Long stargazersCount, final String url, final Owner owner) {
		this.setGitId(gitId);
		this.setName(name);
		this.setForksCount(forksCount);
		this.setLanguage(language);
		this.setStargazersCount(stargazersCount);
		this.setUrl(url);
		this.setOwner(owner);
	}

	/**
	 * @return the name {@link String}
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set {@link String}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the forksCount {@link Long}
	 */
	public Long getForksCount() {
		return forksCount;
	}

	/**
	 * @param forksCount the forksCount to set {@link Long}
	 */
	public void setForksCount(Long forksCount) {
		this.forksCount = forksCount;
	}

	/**
	 * @return the language {@link String}
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language the language to set {@link String}
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the stargazersCount {@link Long}
	 */
	public Long getStargazersCount() {
		return stargazersCount;
	}

	/**
	 * @param stargazersCount the stargazersCount to set {@link Long}
	 */
	public void setStargazersCount(Long stargazersCount) {
		this.stargazersCount = stargazersCount;
	}

	/**
	 * @return the url {@link String}
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set {@link String}
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the owner {@link Owner}
	 */
	public Owner getOwner() {
		return owner;
	}

	/**
	 * @param owner the owner to set {@link Owner}
	 */
	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	/**
	 * @return the gitId {@link Long}
	 */
	public Long getGitId() {
		return gitId;
	}

	/**
	 * @param gitId the owner to set {@link Long}
	 */
	public void setGitId(Long gitId) {
		this.gitId = gitId;
	}

	/**
	 * @param id the owner to set {@link Long}
	 */
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(forksCount, gitId, language, name, owner, stargazersCount, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof GithubRepository))
			return false;
		GithubRepository other = (GithubRepository) obj;
		return Objects.equals(forksCount, other.forksCount) && Objects.equals(gitId, other.gitId)
				&& Objects.equals(language, other.language) && Objects.equals(name, other.name)
				&& Objects.equals(owner, other.owner) && Objects.equals(stargazersCount, other.stargazersCount)
				&& Objects.equals(url, other.url);
	}

}
