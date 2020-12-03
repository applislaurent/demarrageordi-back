package demarrageordi.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;
import demarrageordi.rest.dto.LogicielMapper;
import demarrageordi.rest.dto.LogicielsEtSitesDto;
import demarrageordi.rest.dto.SitewebMapper;
import demarrageordi.service.CreationBatchService;

@RestController
@RequestMapping("/demarrageordi/")
@CrossOrigin(origins = "*")
@ResponseBody
public class CreationBatchController {

	@Autowired
	CreationBatchService creationBatchService;

	@PostMapping(path = "creer.batch")
	public Object creationBatch(@RequestBody LogicielsEtSitesDto logicielsEtSitesDto, HttpServletRequest request)
			throws Exception {

		System.out.println("*********************************************************************");
		System.out.println("*************************     request path      ************************");
		System.out.println(request.getUserPrincipal());
		System.out.println(request.getContextPath());
		System.out.println(request.getUserPrincipal());
		System.out.println("*********************************************************************");

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
