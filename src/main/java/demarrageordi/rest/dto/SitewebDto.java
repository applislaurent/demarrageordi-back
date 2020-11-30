package demarrageordi.rest.dto;

import org.springframework.data.annotation.Id;

import io.swagger.annotations.ApiModelProperty;

public class SitewebDto {

	// Attributs de classe

	@Id
	private int id;

	@ApiModelProperty(value = "Adresse du site web", required = true)
	private String url;

	public int getId() {
		return id;
	}

	// Constructeurs

	public SitewebDto() {
	}

	public SitewebDto(String url) {
		this.url = url;
	}

	// Getter-setters
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
