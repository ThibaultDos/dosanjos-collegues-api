package dev.colleguesapi;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NOTES")
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "COMMENTAIRE", nullable = false, unique = false)
	private String commentaire;
	@Column(name = "DATE_COMMENTAIRE", nullable = false, unique = false)
	private LocalDateTime dateCommentaire = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name = "MATRICULE")
	private Collegue collegue;

	public Notes(Integer id, String commentaire, LocalDateTime dateCommentaire) {
		this.id = id;
		this.commentaire = commentaire;
		this.dateCommentaire = dateCommentaire;
	}

	public Notes(String commentaire, Collegue collegue) {
		this.commentaire = commentaire;
		this.collegue = collegue;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public LocalDateTime getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(LocalDateTime dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

}
