package org.projet.api;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "User")
public class User {
	
	@Id
	@XmlTransient
	private int id;
	private String name;
	private String surname;
	private int score;
	private String email;
	@XmlTransient
	private String password;
	private String pseudo;

	public User() {
	}

	public User(final String name, final String surname, final int score) {
		this.name = name;
		this.surname = surname;
		this.score = score;
		this.email = "";
		this.password = "";
	}

	public User(final String name, final String surname, final int score, final String email, final String password, final String pseudo) {
		this.name = name;
		this.surname = surname;
		this.score = score;
		this.setEmail(email);
		this.setPassword(password);
		this.pseudo = pseudo;
	}

	
	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
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

}
