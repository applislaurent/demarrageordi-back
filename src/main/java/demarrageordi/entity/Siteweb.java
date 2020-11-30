package demarrageordi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.URL;

@Entity
@Table
public class Siteweb {

	// Attributs de classe

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@URL
	@Column(name = "url")
	private String url;

	// Constructeurs

	public Siteweb() {
	}

	public Siteweb(String url) {
		this.url = url;
	}

	// Getter-setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
