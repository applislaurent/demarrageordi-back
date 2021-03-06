package demarrageordi.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.SystemProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/application-test.properties")
public class CreationBatchServiceTests {

	@Autowired
	CreationBatchService creationBatchService;

	@Test
	public void contextLoads() {

	}

	@Before
	public void initTest() {
		// Supprimer le fichier bat s'il existe déjà
		File fichierBat = new File("Demarrage_sites_et_logiciels.bat");
		Path path = Paths.get("Demarrage_sites_et_logiciels.bat");

		try {
			if (Files.exists(path)) {
				FileUtils.forceDelete(fichierBat);
			}
		} catch (IOException e) {
			System.out.println("***************************************");
			System.out.println("Le fichier bat n'a pu être supprimé, erreur: " + e.getMessage());
			System.out.println("***************************************");
		}
	}

	@After
	public void supprimerLesBatchsApresTest() {
		String root = SystemProperties.get("user.dir");
		File baseAppli = new File(root);
		try {
			for (File file : baseAppli.listFiles()) {
				if (file.getPath().contains("Demarrage_sites_et_logiciels_")) {
					FileUtils.forceDelete(file);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {
		System.out.println("***************************************");
		System.out.println("OS Architecture : " + System.getProperty("os.arch"));
		System.out.println("OS Name : " + System.getProperty("os.name"));
		System.out.println("OS Version : " + System.getProperty("os.version"));
		System.out.println("Data Model : " + System.getProperty("sun.arch.data.model"));
		System.out.println("***************************************");

	}

	@Test
	public void creationFichierBatchCasNominalOK() {

		// Créer des logiciels et sitewebs

		List<Logiciel> logiciels = new ArrayList<>();
		Logiciel logiciel1 = new Logiciel();
		logiciel1.setNom("Notepad++");
		logiciel1.setRepertoire("C:\\Program Files (x86)");
		logiciels.add(logiciel1);
		Logiciel logiciel2 = new Logiciel();
		logiciel2.setNom("CCleaner");
		logiciel2.setRepertoire("C:\\Program Files\\CCleaner");
		logiciels.add(logiciel2);

		List<Siteweb> sitesWeb = new ArrayList<>();
		Siteweb siteWeb1 = new Siteweb();
		siteWeb1.setUrl("https://framateam.org/gj-montastruc/channels/town-square");
		Siteweb siteWeb2 = new Siteweb();
		siteWeb2.setUrl("https://webmail1n.orange.fr/webmail/fr_FR/inbox.html?FromSubmit=true&dub=1");
		sitesWeb.add(siteWeb1);
		sitesWeb.add(siteWeb2);

		// Simuler une requête depuis un navigateur Firefox

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox");

		try {
			// Appeler le service de création du fichier bat
			File fichierBatch = creationBatchService.createBatch(logiciels, sitesWeb, request);
			// Vérifier la taille du fichier
			assertEquals(fichierBatch.length(), 468);

		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

	@Test
	public void creationFichierBatchSansLogicielsOK() {

		// Créer une liste de logiciels vides, et une liste de sitewebs

		List<Logiciel> logiciels = new ArrayList<>();

		List<Siteweb> sitesWeb = new ArrayList<>();
		Siteweb siteWeb1 = new Siteweb();
		siteWeb1.setUrl("https://framateam.org/gj-montastruc/channels/town-square");
		Siteweb siteWeb2 = new Siteweb();
		siteWeb2.setUrl("https://webmail1n.orange.fr/webmail/fr_FR/inbox.html?FromSubmit=true&dub=1");
		sitesWeb.add(siteWeb1);
		sitesWeb.add(siteWeb2);

		// Simuler une requête depuis un navigateur Firefox

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox");

		try {
			// Appeler le service de création du fichier bat
			File fichierBatch = creationBatchService.createBatch(logiciels, sitesWeb, request);
			// Vérifier la taille du fichier
			assertEquals(fichierBatch.length(), 337);
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

	@Test
	public void creationFichierBatchSansSitewebsOK() {

		// Créer une liste de logiciels, et une liste de sitewebs vide

		List<Logiciel> logiciels = new ArrayList<>();
		Logiciel logiciel1 = new Logiciel();
		logiciel1.setNom("Notepad++");
		logiciel1.setRepertoire("C:\\Program Files (x86)");
		logiciels.add(logiciel1);
		Logiciel logiciel2 = new Logiciel();
		logiciel2.setNom("CCleaner");
		logiciel2.setRepertoire("C:\\Program Files\\CCleaner");
		logiciels.add(logiciel2);

		List<Siteweb> sitesWeb = new ArrayList<>();

		// Simuler une requête depuis un navigateur Firefox

		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox");

		try {
			// Appeler le service de création du fichier bat
			File fichierBatch = creationBatchService.createBatch(logiciels, sitesWeb, request);
			// Vérifier la taille du fichier
			assertEquals(fichierBatch.length(), 185);
		} catch (Exception e) {
			Assert.fail("La création du batch aurait du réussir");
		}

	}

// Test utile seulement dans la version complète de l'application,
// dans laquelle on vérifie/cherche les chemins logiciels
//	@Test
//	public void creationFichierBatchRepertoireLogicielInvalideKO() {
//
//		// Simuler une requête depuis un navigateur Firefox
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox");
//
//		try {
//
//			List<Logiciel> logiciels = new ArrayList<>();
//			Logiciel logiciel1 = new Logiciel();
//			logiciel1.setNom("CCleaner");
//			// nom de répertoire trop court
//			logiciel1.setRepertoire("C:\\");
//			Logiciel logiciel2 = new Logiciel();
//			logiciel2.setNom("Algobox");
//			logiciel2.setRepertoire("C:\\Program Files (x86)");
//			logiciels.add(logiciel1);
//			logiciels.add(logiciel2);
//
//			List<Siteweb> sitesWeb = new ArrayList<>();
//			Siteweb siteWeb1 = new Siteweb();
//			siteWeb1.setUrl("https://framateam.org/gj-montastruc/channels/town-square");
//			Siteweb siteWeb2 = new Siteweb();
//			siteWeb2.setUrl("https://webmail1n.orange.fr/webmail/fr_FR/inbox.html?FromSubmit=true&dub=1");
//			sitesWeb.add(siteWeb1);
//			sitesWeb.add(siteWeb2);
//
//			File fichierBatch = creationBatchService.createBatch(logiciels, sitesWeb, request);
//			Assert.fail("La création du batch aurait du échouer");
//
//		} catch (Exception e) {
//			assertEquals(e.getMessage(),
//					"La génération du fichier a échoué : Un logiciel n'a pu être trouvé, svp soyez un peu plus précis dans le nom du répertoire de CCleaner");
//		}
//
//	}

// Test utile seulement dans la version complète de l'application,
// dans laquelle on vérifie les urls
//	@Test
//	public void creationFichierBatchUrlSiteWebKO() {
//
//		// Simuler une requête depuis un navigateur Firefox
//		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox");
//
//		try {
//
//			List<Logiciel> logiciels = new ArrayList<>();
//			Logiciel logiciel1 = new Logiciel();
//			logiciel1.setNom("CCleaner");
//			// nom de répertoire trop court
//			logiciel1.setRepertoire("C:\\Program Files");
//			Logiciel logiciel2 = new Logiciel();
//			logiciel2.setNom("Algobox");
//			logiciel2.setRepertoire("C:\\Program Files (x86)");
//			logiciels.add(logiciel1);
//			logiciels.add(logiciel2);
//
//			List<Siteweb> sitesWeb = new ArrayList<>();
//			Siteweb siteWeb1 = new Siteweb();
//			siteWeb1.setUrl("https://framateam.org/  /channels/town-square");
//			Siteweb siteWeb2 = new Siteweb();
//			siteWeb2.setUrl("https://webmail1n.orange.fr/webmail/fr_FR/inbox.html?FromSubmit=true&dub=1");
//			sitesWeb.add(siteWeb1);
//			sitesWeb.add(siteWeb2);
//
//			File fichierBatch = creationBatchService.createBatch(logiciels, sitesWeb, request);
//			Assert.fail("La création du batch aurait du échouer");
//
//		} catch (Exception e) {
//			assertEquals(e.getMessage(),
//					"La génération du fichier a échoué : L'adresse de ce site c'est n'est pas correcte: https://framateam.org/  /channels/town-square");
//		}
//
//	}

}
