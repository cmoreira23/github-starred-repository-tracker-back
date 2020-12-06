package com.example.githubstarredrepositorytrackerback.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Camilla Cyrino
 *
 */
@Entity
public class GithubRepositoryEntryLog implements Serializable {

	private static final long serialVersionUID = 221418430473347093L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private LocalDateTime genarateDate;

	@Column(nullable = false)
	private int hash;

	@Column(nullable = false)
	private String language;

	@Column(nullable = false)
	private Integer page;

	/**
	 * Constructor without params
	 * 
	 */
	public GithubRepositoryEntryLog() {
	}

	/**
	 * Constructor with params
	 * 
	 * @param genarateDate {@link LocalDateTime}
	 * @param hash         {@link int}
	 * @param language     {@link String}
	 * @param page         {@link Integer}
	 */
	public GithubRepositoryEntryLog(LocalDateTime genarateDate, int hash, String language, Integer page) {
		super();
		this.genarateDate = genarateDate;
		this.hash = hash;
		this.language = language;
		this.page = page;
	}

	/**
	 * @return the genarateDate {@link LocalDateTime}
	 */
	public LocalDateTime getGenarateDate() {
		return genarateDate;
	}

	/**
	 * @param genarateDate the genarateDate to set {@link LocalDateTime}
	 */
	public void setGenarateDate(LocalDateTime genarateDate) {
		this.genarateDate = genarateDate;
	}

	/**
	 * @return the hash {@link Long}
	 */
	public int getHash() {
		return hash;
	}

	/**
	 * @param hash the hash to set {@link Long}
	 */
	public void setHash(int hash) {
		this.hash = hash;
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
	 * @return the page {@link String}
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set {@link String}
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

}
