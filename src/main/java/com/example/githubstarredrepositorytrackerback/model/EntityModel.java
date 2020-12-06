package com.example.githubstarredrepositorytrackerback.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Camilla Cyrino
 *
 */
@MappedSuperclass
public abstract class EntityModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private Long id;

	@Column(nullable = false)
	@JsonProperty(value = "id")
	private Long gitId;

	/**
	 * Constructor without params
	 * 
	 */
	public EntityModel() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructor with params
	 * 
	 * @param gitId {@link Long}
	 */
	public EntityModel(final Long gitId) {
		setGitId(gitId);
	}

	/**
	 * @return id {@link Long}
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * @return the gitId {@link Long}
	 */
	public Long getGitId() {
		return gitId;
	}

	/**
	 * @param gitId the gitId to set {@link Long}
	 */
	public void setGitId(Long gitId) {
		this.gitId = gitId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gitId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof EntityModel))
			return false;
		EntityModel other = (EntityModel) obj;
		return Objects.equals(gitId, other.gitId);
	}

}
