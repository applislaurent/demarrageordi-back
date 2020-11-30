package demarrageordi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Logiciel {

	// Attributs de classe

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "nom")
	private String nom;

	@Column(name = "repertoire")
	private String repertoire;

	// Constructeurs

	public Logiciel() {
	}

	public Logiciel(String nom, String repertoire) {
		this.nom = nom;
		this.repertoire = repertoire;
	}

	// Getter-setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRepertoire() {
		return repertoire;
	}

	public void setRepertoire(String repertoire) {
		this.repertoire = repertoire;
	}

}
