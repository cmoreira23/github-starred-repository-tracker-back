package com.example.githubstarredrepositorytrackerback.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner implements Serializable {

	private static final long serialVersionUID = -8907519037022270780L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(nullable = false)
	@JsonProperty(value = "id")
	private Long gitId;

	@Column(nullable = false)
	@JsonProperty(value = "login")
	private String name;

	@Column(nullable = false)
	@JsonProperty(value = "avatar_url")
	private String avatar;

	@Column(nullable = false)
	private String type;

	@Column(nullable = false)
	@JsonProperty(value = "html_url")
	private String url;

	/**
	 * Constructor without params
	 * 
	 */
	public Owner() {
	}

	/**
	 * Constructor with params
	 * 
	 * @param gitId  {@link Long}
	 * @param name   {@link String}
	 * @param avatar {@link String}
	 * @param type   {@link String}
	 * @param url    {@link String}
	 */
	public Owner(final Long gitId, final String name, final String avatar, final String type, final String url) {
		setGitId(gitId);
		setAvatar(avatar);
		setName(name);
		setType(type);
		setUrl(url);
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
	 * @return the avatar {@link String}
	 */
	public String getAvatar() {
		return avatar;
	}

	/**
	 * @param avatar the avatar to set {@link String}
	 */
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	/**
	 * @return the type {@link String}
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set {@link String}
	 */
	public void setType(String type) {
		this.type = type;
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
		return Objects.hash(avatar, gitId, name, type, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Owner))
			return false;
		Owner other = (Owner) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(gitId, other.gitId)
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type)
				&& Objects.equals(url, other.url);
	}

}
