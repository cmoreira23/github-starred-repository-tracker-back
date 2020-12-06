package com.example.githubstarredrepositorytrackerback.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Owner extends EntityModel {

	private static final long serialVersionUID = -3067992659049832804L;

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
	 * @param avatar {@link String}
	 * @param type   {@link String}
	 * @param url    {@link String}
	 */
	public Owner(final Long gitId, final String avatar, final String type, final String url) {
		super(gitId);
		setAvatar(avatar);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(avatar, name, type, url);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Owner))
			return false;
		Owner other = (Owner) obj;
		return Objects.equals(avatar, other.avatar) && Objects.equals(name, other.name)
				&& Objects.equals(type, other.type) && Objects.equals(url, other.url);
	}

}
