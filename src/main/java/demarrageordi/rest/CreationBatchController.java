package demarrageordi.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;
import demarrageordi.rest.dto.LogicielDto;
import demarrageordi.rest.dto.LogicielMapper;
import demarrageordi.rest.dto.LogicielsEtSitesDto;
import demarrageordi.rest.dto.SitewebDto;
import demarrageordi.rest.dto.SitewebMapper;
import demarrageordi.service.CreationBatchService;

@Controller
@RequestMapping("/demarrageordi/")
@CrossOrigin(origins = "*")
public class CreationBatchController {

	@Autowired
	CreationBatchService creationBatchService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		String message = "Hello Spring Boot + JSP";

		model.addAttribute("message", message);

		return "index.jsp";
	}

	@PostMapping(path = "creer.batch")
	public Object creationBatch(@RequestParam("nomLogiciel1") String nomLogiciel1,
			@RequestParam("repertoireLogiciel1") String repertoireLogiciel1,
			@RequestParam("urlSiteweb1") String urlSiteweb1, LogicielsEtSitesDto logicielsEtSitesDto,
			HttpServletRequest request) throws Exception {

		List<LogicielDto> logicielDtos = new ArrayList<>();
		logicielDtos.add(new LogicielDto(nomLogiciel1, repertoireLogiciel1));
		logicielsEtSitesDto.setLogicielDtos(logicielDtos);

		List<SitewebDto> siteWebDtos = new ArrayList<>();
		siteWebDtos.add(new SitewebDto(urlSiteweb1));
		logicielsEtSitesDto.setSitewebDtos(siteWebDtos);

		List<Logiciel> logiciels = LogicielMapper.toLogiciels(logicielsEtSitesDto.getLogicielDtos());
		List<Siteweb> sitesWeb = SitewebMapper.toSitewebs(logicielsEtSitesDto.getSitewebDtos());

		try {

			File batch = creationBatchService.createBatch(logiciels, sitesWeb, request);

			HttpHeaders header = new HttpHeaders();
			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + batch.getName());
			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
			header.add("Pragma", "no-cache");
			header.add("Expires", "0");

			Path path = Paths.get(batch.getAbsolutePath());
			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

			return ResponseEntity.ok().headers(header).contentLength(batch.length())
					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

		} catch (Exception e) {
			String messageErreur = e.getLocalizedMessage();
			return ResponseEntity.status(200).headers(new HttpHeaders())
					.contentType(MediaType.parseMediaType("application/string")).body(messageErreur);
		}

	}

	@GetMapping(path = "telecharger.batch")
	public ResponseEntity<Resource> telechargerBatch() throws IOException {

		final String NOM_FICHIER = "Demarrage_sites_et_logiciels.bat";
		File batch = new File(NOM_FICHIER);

		HttpHeaders header = new HttpHeaders();
		header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + NOM_FICHIER);
		header.add("Cache-Control", "no-cache, no-store, must-revalidate");
		header.add("Pragma", "no-cache");
		header.add("Expires", "0");

		Path path = Paths.get(batch.getAbsolutePath());
		ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

		return ResponseEntity.ok().headers(header).contentLength(batch.length())
				.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);

	}
}
