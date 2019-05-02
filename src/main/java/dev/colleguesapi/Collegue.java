package dev.colleguesapi;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Controller;

@Entity
@Table(name="COLLEGUE")
public class Collegue {	

	@Id
	@Column(name="MATRICULE")
	private String matricule ;
	
	@Column(name="NOM", nullable=false, unique=false)
	private String nom ;
	@Column(name="PRENOMS", nullable=false, unique=false)
	private String prenoms ;
	@Column(name="EMAIL", unique=true)
	private String email ;
	@Column(name="DATE_NAISSANCE", nullable=false, unique=false)
	private LocalDate dateDeNaissance ;
	@Column(name="PHOTO_URL")
	private String photoUrl ;
	
	/**
	 * Empty default constructor
	 */
	public Collegue() {
		super();
	}
	/**
	 * @param matricule
	 * @param nom
	 * @param prenoms
	 * @param email
	 * @param dateDeNaissance
	 * @param photoUrl
	 */
	public Collegue(String matricule, String nom, String prenoms, String email, LocalDate dateDeNaissance,
			String photoUrl) {
		super();
		this.matricule = matricule;
		this.nom = nom;
		this.prenoms = prenoms;
		this.email = email;
		this.dateDeNaissance = dateDeNaissance;
		this.photoUrl = photoUrl;
	}
	/**
	 * @return the matricule
	 */
	public String getMatricule() {
		return matricule;
	}
	/**
	 * @param matricule the matricule to set
	 */
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenoms
	 */
	public String getPrenoms() {
		return prenoms;
	}
	/**
	 * @param prenoms the prenoms to set
	 */
	public void setPrenoms(String prenoms) {
		this.prenoms = prenoms;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the dateDeNaissance
	 */
	public LocalDate getDateDeNaissance() {
		return dateDeNaissance;
	}
	/**
	 * @param dateDeNaissance the dateDeNaissance to set
	 */
	public void setDateDeNaissance(LocalDate dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}
	/**
	 * @return the photoUrl
	 */
	public String getPhotoUrl() {
		return photoUrl;
	}
	/**
	 * @param photoUrl the photoUrl to set
	 */
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}	
}
