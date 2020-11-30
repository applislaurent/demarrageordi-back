package demarrageordi.rest;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import demarrageordi.rest.dto.LogicielDto;
import demarrageordi.rest.dto.LogicielsEtSitesDto;
import demarrageordi.rest.dto.SitewebDto;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class CreationBatchControllerTest {

	private MockMvc mockMvc;

	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext ctx;
	private String baseUrl;

	@Before
	public void initialize() {

		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		baseUrl = "/demarrageordi";
	}

	@Test
	public void creerBatchTestCasNominalOK() {

		// Création d'un logiciel
		List<LogicielDto> logicielsDto = new ArrayList<>();
		LogicielDto logicielDto = new LogicielDto();
		logicielDto.setNom("CCleaner");
		logicielDto.setRepertoire("C:\\Program Files");
		logicielsDto.add(logicielDto);

		// Création d'un site web
		List<SitewebDto> siteswebDto = new ArrayList<>();
		SitewebDto sitewebDto = new SitewebDto();
		sitewebDto.setUrl("https://www.google.fr");
		siteswebDto.add(sitewebDto);

		// Création de l'ensemble logiciels et sitewebs
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

	@Test
	public void creerBatchTestPasDeLogicielsOK() {

		// Création d'une liste de logiciels vide
		List<LogicielDto> logicielsDto = new ArrayList<>();

		// Création d'une liste de sitewebs, comprenant un site web
		List<SitewebDto> siteswebDto = new ArrayList<>();
		SitewebDto sitewebDto = new SitewebDto();
		sitewebDto.setUrl("https://www.google.fr");
		siteswebDto.add(sitewebDto);

		// Création de l'ensemble logiciels et sitewebs
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

	@Test
	public void creerBatchTestPasDeSitewebsOK() {

		// Création d'une liste de logiciels, comprenant un logiciel
		List<LogicielDto> logicielsDto = new ArrayList<>();
		LogicielDto logicielDto = new LogicielDto();
		logicielDto.setNom("CCleaner");
		logicielDto.setRepertoire("C:\\Program Files");
		logicielsDto.add(logicielDto);

		// Création d'une liste de sites vide
		List<SitewebDto> siteswebDto = new ArrayList<>();

		// Création de l'ensemble logiciels et sitewebs
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

	@Test
	public void creationFichierBatchRepertoireLogicielInvalideKO() {

		// Création d'un logiciel
		List<LogicielDto> logicielsDto = new ArrayList<>();
		LogicielDto logicielDto = new LogicielDto();
		logicielDto.setNom("CCleaner");
		logicielDto.setRepertoire("C:\\");
		logicielsDto.add(logicielDto);

		// Création d'un site web
		List<SitewebDto> siteswebDto = new ArrayList<>();
		SitewebDto sitewebDto = new SitewebDto();
		sitewebDto.setUrl("https://www.google.fr");
		siteswebDto.add(sitewebDto);

		// Création de l'ensemble logiciels et sitewebs
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			ResultActions messageErreur = this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
			assertTrue(messageErreur.andReturn().getResponse().getContentAsString().equals(
					"La gÃ©nÃ©ration du fichier a Ã©chouÃ© : Un logiciel n'a pu Ãªtre trouvÃ©, svp soyez un peu plus prÃ©cis dans le nom du rÃ©pertoire de CCleaner"));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du échouer");
		}

	}

	@Test
	public void creationFichierBatchUrlSiteWebKO() {

		// Création d'un logiciel
		List<LogicielDto> logicielsDto = new ArrayList<>();
		LogicielDto logicielDto = new LogicielDto();
		logicielDto.setNom("CCleaner");
		logicielDto.setRepertoire("C:\\Program Files");
		logicielsDto.add(logicielDto);

		// Création d'un site web
		List<SitewebDto> siteswebDto = new ArrayList<>();
		SitewebDto sitewebDto = new SitewebDto();
		sitewebDto.setUrl("https://framateam.org/  /channels/town-square");
		siteswebDto.add(sitewebDto);

		// Création de l'ensemble logiciels et sitewebs
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			ResultActions messageErreur = this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
			assertTrue(messageErreur.andReturn().getResponse().getContentAsString().equals(
					"La gÃ©nÃ©ration du fichier a Ã©chouÃ© : L'adresse de ce site c'est n'est pas correcte: https://framateam.org/  /channels/town-square"));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du échouer");
		}

	}

	@Test
	public void creationFichierBatchAucunParametreKO() {

		// CAS 1 : Aucune info saisie
		LogicielsEtSitesDto LogicielsEtSitesDto = new LogicielsEtSitesDto(null, null);

		// Conversion du dto au format json
		String LogicielsEtSitesDtoString = json(LogicielsEtSitesDto);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDtoString);

		try {
			ResultActions messageErreur = this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
			assertTrue(messageErreur.andReturn().getResponse().getContentAsString()
					.equals("La gÃ©nÃ©ration du fichier a Ã©chouÃ© : Vous n'avez pas saisi assez d'infos"));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du échouer");
		}

		// CAS 2 : infos saisies vides
		List<LogicielDto> logicielsDto = new ArrayList<>();
		List<SitewebDto> siteswebDto = new ArrayList<>();
		LogicielsEtSitesDto LogicielsEtSitesDto2 = new LogicielsEtSitesDto(logicielsDto, siteswebDto);

		// Conversion du dto au format json
		String LogicielsEtSitesDto2String = json(LogicielsEtSitesDto2);

		// Créer une requête post, depuis un navigateur firefox
		MockHttpServletRequestBuilder requestBuilder2 = post(baseUrl + "/creer.batch")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox")
				.contentType(MediaType.APPLICATION_JSON).content(LogicielsEtSitesDto2String);

		try {
			ResultActions messageErreur = this.mockMvc.perform(requestBuilder).andExpect(status().is(200));
			assertTrue(messageErreur.andReturn().getResponse().getContentAsString()
					.equals("La gÃ©nÃ©ration du fichier a Ã©chouÃ© : Vous n'avez pas saisi assez d'infos"));
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du échouer");
		}

	}

	/**
	 * Convertir un dto en json
	 * 
	 * @param o
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String json(Object o) {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		try {
			this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		} catch (HttpMessageNotWritableException | IOException e) {
			// TODO Auto-generated catch block
			throw new UnsupportedOperationException("Auto-generated method stub", e);
		}
		return mockHttpOutputMessage.getBodyAsString();
	}

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
	}

}
