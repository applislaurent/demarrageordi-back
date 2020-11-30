package demarrageordi.rest.dto;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

public class LogicielDto {

	// Attributs de classe

	@Id
	private int id;

	@ApiModelProperty(value = "nom du logiciel", required = true)
	private String nom;

	@ApiModelProperty(value = "Repertoire dans lequel se trouve le logiciel", required = true)
	private String repertoire;

	// Constructeurs

	public LogicielDto() {
	}

	public LogicielDto(String nom, String repertoire) {
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
