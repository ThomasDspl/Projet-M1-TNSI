package org.projet.api;

import javax.persistence.Id;

public class Image {
	
	@Id
	private int id;
	private int idUser;
	private String chemin;
	private String nomImage;
	private String classe;
	
	public Image(final int idUser, final String chemin, final String nomImage, final String classe) {
		this.idUser = idUser;
		this.chemin = chemin;
		this.nomImage = nomImage;
		this.classe = classe;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getChemin() {
		return chemin;
	}

	public void setChemin(String chemin) {
		this.chemin = chemin;
	}

	public String getNomImage() {
		return nomImage;
	}

	public void setNomImage(String nomImage) {
		this.nomImage = nomImage;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	

}
