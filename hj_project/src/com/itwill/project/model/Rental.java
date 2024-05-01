package com.itwill.project.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Rental {
	public static final class Entity {
		// 데이터베이스 테이블 이름을 상수로 선언.
		public static final String TBL_RENTALS = "RENTALS";

		// 데이터베이스 RENTAL 테이블의 컬럼 이름들을 상수로 선언.
		public static final String COL_ID = "ID";
		public static final String COL_NAME = "NAME";
		public static final String COL_EMAIL = "EMAIL";
		public static final String COL_PASSWORD = "PASSWORD";
		public static final String COL_CONTENT = "CONTENT";
		public static final String COL_GENRE = "GENRE";
		public static final String COL_CREATED_TIME = "CREATED_TIME";
		public static final String COL_MODIFIED_TIME = "MODIFIED_TIME";
	}
	
	private int id;
	private String name;
	private String email;
	private String password;
	private String content;
	private String genre;
	private LocalDateTime createdTime;
	private LocalDateTime modifiedTime;
	
	public Rental() {}

	public Rental(int id, String name, String email, String password, String content, String genre,
			LocalDateTime createdTime, LocalDateTime modifiedTime) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.content = content;
		this.genre = genre;
		this.createdTime = createdTime;
		this.modifiedTime = modifiedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	@Override
	public String toString() {
		return "Rental [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", content="
				+ content + ", genre=" + genre + ", createdTime=" + createdTime + ", modifiedTime=" + modifiedTime
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(content, createdTime, email, genre, id, modifiedTime, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rental other = (Rental) obj;
		return Objects.equals(content, other.content) && Objects.equals(createdTime, other.createdTime)
				&& Objects.equals(email, other.email) && Objects.equals(genre, other.genre) && id == other.id
				&& Objects.equals(modifiedTime, other.modifiedTime) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	


	
	
}
