package demarrageordi.rest.dto;

import java.util.ArrayList;
import java.util.List;

import demarrageordi.entity.Siteweb;

public class SitewebMapper {

	public static List<Siteweb> toSitewebs(List<SitewebDto> sitewebDtos) {

		List<Siteweb> sitewebs = new ArrayList<Siteweb>();
		if (sitewebDtos != null && !sitewebDtos.isEmpty()) {
			for (SitewebDto s : sitewebDtos) {
				sitewebs.add(toSiteweb(s));
			}
		}

		return sitewebs;

	}

	public static Siteweb toSiteweb(SitewebDto sitewebDto) {

		Siteweb siteWeb = new Siteweb();
		if (sitewebDto != null) {
			siteWeb.setId(sitewebDto.getId());
			siteWeb.setUrl(sitewebDto.getUrl());
		}
		return siteWeb;

	}

	public static List<SitewebDto> toSitewebDtos(List<Siteweb> sitewebs) {

		List<SitewebDto> sitewebDtos = new ArrayList<SitewebDto>();
		if (sitewebs != null && !sitewebs.isEmpty()) {
			for (Siteweb s : sitewebs) {
				sitewebDtos.add(toSitewebDto(s));
			}
		}
		return sitewebDtos;

	}

	public static SitewebDto toSitewebDto(Siteweb siteweb) {

		SitewebDto siteWebDto = new SitewebDto();
		if (siteweb != null) {
			siteWebDto.setId(siteweb.getId());
			siteWebDto.setUrl(siteweb.getUrl());
		}
		return siteWebDto;

	}

}
