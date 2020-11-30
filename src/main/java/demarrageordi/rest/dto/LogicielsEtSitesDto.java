package demarrageordi.rest.dto;

import java.util.List;

public class LogicielsEtSitesDto {

	List<LogicielDto> logicielDtos;

	List<SitewebDto> sitewebDtos;

	public LogicielsEtSitesDto() {

	}

	public LogicielsEtSitesDto(List<LogicielDto> logicielDtos, List<SitewebDto> sitewebDtos) {

		this.logicielDtos = logicielDtos;
		this.sitewebDtos = sitewebDtos;
	}

	public List<LogicielDto> getLogicielDtos() {
		return logicielDtos;
	}

	public void setLogicielDtos(List<LogicielDto> logicielDtos) {
		this.logicielDtos = logicielDtos;
	}

	public List<SitewebDto> getSitewebDtos() {
		return sitewebDtos;
	}

	public void setSitewebDtos(List<SitewebDto> sitewebDtos) {
		this.sitewebDtos = sitewebDtos;
	}

}
