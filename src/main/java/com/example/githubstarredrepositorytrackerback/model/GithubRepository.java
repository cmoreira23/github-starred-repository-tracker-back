package com.example.githubstarredrepositorytrackerback.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepository extends EntityModel {

	private static final long serialVersionUID = -5143483849331060563L;

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
	@ManyToOne
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
		super(gitId);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(forksCount, language, name, owner, stargazersCount, url);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GithubRepository))
			return false;
		GithubRepository other = (GithubRepository) obj;
		return Objects.equals(forksCount, other.forksCount) && Objects.equals(language, other.language)
				&& Objects.equals(name, other.name) && Objects.equals(owner, other.owner)
				&& Objects.equals(stargazersCount, other.stargazersCount) && Objects.equals(url, other.url);
	}

}
