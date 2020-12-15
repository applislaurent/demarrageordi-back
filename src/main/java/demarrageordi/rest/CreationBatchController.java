package demarrageordi.rest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileUtils;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;
import demarrageordi.rest.dto.LogicielMapper;
import demarrageordi.rest.dto.LogicielsEtSitesDto;
import demarrageordi.rest.dto.SitewebMapper;
import demarrageordi.service.CreationBatchService;

@Controller
@RequestMapping("/demarrageordi/")
// Tous accès autorisés à l'API
// @CrossOrigin(origins = "*")
// Accès front en localhost
// @CrossOrigin(origins = "http://localhost:4200")
//Accès front depuis github
@CrossOrigin(origins = "https://applislaurent.github.io/demarrageordi")

public class CreationBatchController {

	@Autowired
	CreationBatchService creationBatchService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		String message = "Hello Spring Boot + JSP";

		model.addAttribute("message", message);

		return "index";
	}

//	@PostMapping(path = "creer.batch")
//	public Object creationBatch(@RequestParam("nomLogiciels") String nomLogiciels,
//			@RequestParam("repertoireLogiciels") String repertoireLogiciels, @RequestParam("urls") String urls,
//			HttpServletRequest request) throws Exception {
//
//		// Logiciels
//
//		String[] listeNomsLogiciels = nomLogiciels.split(",");
//		String[] listeRepertoiresLogiciels = repertoireLogiciels.split(",");
//
//		LogicielsEtSitesDto logicielsEtSitesDto = new LogicielsEtSitesDto();
//		List<LogicielDto> logicielDtos = new ArrayList<>();
//
//		int nbreLogiciels = listeNomsLogiciels.length;
//		int i = 0;
//		while (i < nbreLogiciels) {
//			logicielDtos.add(new LogicielDto(listeNomsLogiciels[i], listeRepertoiresLogiciels[i]));
//			i++;
//		}
//
//		// Sitewebs
//
//		String[] listeUrlSitewebs = urls.split(",");
//		List<SitewebDto> sitewebDtos = new ArrayList<>();
//		int nbreSitewebs = listeUrlSitewebs.length;
//		i = 0;
//		while (i < nbreSitewebs) {
//			sitewebDtos.add(new SitewebDto(listeUrlSitewebs[i]));
//			i++;
//		}
//
//		logicielsEtSitesDto.setLogicielDtos(logicielDtos);
//		logicielsEtSitesDto.setSitewebDtos(sitewebDtos);
//
//		List<Logiciel> logiciels = LogicielMapper.toLogiciels(logicielsEtSitesDto.getLogicielDtos());
//		List<Siteweb> sitesWeb = SitewebMapper.toSitewebs(logicielsEtSitesDto.getSitewebDtos());
//
//		try
//
//		{
//
//			File batch = creationBatchService.createBatch(logiciels, sitesWeb, request);
//
//			HttpHeaders header = new HttpHeaders();
//			header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + batch.getName());
//			header.add("Cache-Control", "no-cache, no-store, must-revalidate");
//			header.add("Pragma", "no-cache");
//			header.add("Expires", "0");
//
//			Path path = Paths.get(batch.getAbsolutePath());
//			ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
//
//			return ResponseEntity.ok().headers(header).contentLength(batch.length())
//					.contentType(MediaType.parseMediaType("application/octet-stream")).body(resource);
//
//		} catch (Exception e) {
//			request.setAttribute("errorMessage", e.getLocalizedMessage());
//			return e.getLocalizedMessage();
//		}
//
//	}

	@PostMapping(path = "creer.batch")
	public ResponseEntity creationBatch(@RequestBody LogicielsEtSitesDto logicielsEtSitesDto,
			HttpServletRequest request) throws Exception {

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

			// Préparer la réponse
			ResponseEntity<ByteArrayResource> reponse = ResponseEntity.ok().headers(header)
					.contentLength(batch.length()).contentType(MediaType.parseMediaType("application/octet-stream"))
					.body(resource);

			// Supprimer le fichier batch du serveur
			FileUtils.forceDelete(batch);

			// Envoyer la réponse
			return reponse;

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
