package demarrageordi.service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;
import demarrageordi.exception.MessageExceptionConstantes;

@Service("CreationBatchService")
public class CreationBatchServiceImpl implements CreationBatchService {

	private static final Logger logger = LoggerFactory.getLogger(CreationBatchServiceImpl.class);

	/**
	 * Méthode principale appelée pour la création du fichier bat. Vérifie les
	 * données saisaies, créer le fichier bat, écrit le fichier, ouvre la fenêtre
	 * d'enregistrement du fichier
	 * 
	 * @param logiciels  la liste de les logiciels à ouvrir au démarrage du bat
	 * @param sitesWeb   la liste des sites webs à ouvrir au démarrage du bat
	 * @param navigateur le navigateur à utiliser pour l'ouverture des sites web
	 * 
	 * @return fichierBatch le fichier bat
	 * 
	 * @throws Exception
	 */
	@Override
	public File createBatch(List<Logiciel> logiciels, List<Siteweb> sitesWeb, HttpServletRequest request)
			throws Exception {

		String idUnique = UUID.randomUUID().toString().substring(0, 15);

		String nomFichierBatch = "Demarrage_sites_et_logiciels_" + idUnique + ".bat";
		File fichierBatch = new File(nomFichierBatch);

		try {

			validInformations(logiciels, sitesWeb);
			List<String> cheminsLogiciels = searchLogicielsPaths(logiciels);
			fichierBatch = writeFile(fichierBatch, cheminsLogiciels, sitesWeb, request);

		} catch (Exception e) {
			logger.info("************* LOG INFO ************* :" + e.getMessage());
			throw new Exception(createExceptionMessage(e), e);
		}
		return fichierBatch;

	}

	/**
	 * Vérifie que l'utilisateur a saisi des informations (soit logiciels, soit
	 * sites web)
	 * 
	 * @param logiciels
	 * @param sitesWeb
	 * @throws Exception
	 */
	private void validInformations(List<Logiciel> logiciels, List<Siteweb> sitesWeb) throws Exception {

		if (logiciels.isEmpty() && sitesWeb.isEmpty()) {
			throw new Exception(MessageExceptionConstantes.AUCUNE_INFO_SAISIE);
		}

	}

	/**
	 * Suppression d'éventuels guillemets et ajout si besoin d'extension .exe
	 *
	 * @param logiciels
	 * @return
	 * @throws Exception
	 */
	private List<String> searchLogicielsPaths(List<Logiciel> logiciels) throws Exception {

		List<String> cheminsLogiciels = new ArrayList<>();

		for (Logiciel logiciel : logiciels) {
			String nomLogiciel = logiciel.getNom();
			if (nomLogiciel.startsWith("\"") || nomLogiciel.startsWith("'")) {
				nomLogiciel = nomLogiciel.substring(1);
			}
			if (nomLogiciel.endsWith("\"") || nomLogiciel.endsWith("'")) {
				nomLogiciel = nomLogiciel.substring(0, nomLogiciel.length() - 1);
			}
			if (!nomLogiciel.endsWith(".exe") && !nomLogiciel.endsWith(".EXE")) {
				nomLogiciel = nomLogiciel + ".exe";
			}
			cheminsLogiciels.add(nomLogiciel);
		}
		return cheminsLogiciels;

	}

	/**
	 * 
	 * Ecrit le contenu du fichier batch à partir des listes de logiciels et sites
	 * web
	 * 
	 * @param fichierBatch
	 * @return
	 * @throws Exception
	 */
	private File writeFile(File fichierBatch, List<String> repertoiresLogiciels, List<Siteweb> sitesWeb,
			HttpServletRequest request) throws Exception {

		// écrire le fichier
		try {
			PrintWriter writer;
			writer = new PrintWriter(new FileWriter(fichierBatch));
			writer.println("REM LANCEMENT AUTOMATIQUE SITES WEB");
			writer.println("@echo off");
			writer = addApps(writer, repertoiresLogiciels);
			if (!sitesWeb.isEmpty()) {
				writer = addWebsites(writer, sitesWeb, request);
			}
			writer.println("exit");
			writer.close();
		} catch (IOException e) {
			throw new IOException(MessageExceptionConstantes.ERREUR_ECRITURE_FICHIER, e);
		}

		return fichierBatch;

	}

	/**
	 * Ecrit la liste des sitewebs dans le fichier bat
	 * 
	 * @param writer
	 * @param sitewebs
	 * @param request
	 *
	 * @return writer
	 * @throws Exception
	 */
	private PrintWriter addWebsites(PrintWriter writer, List<Siteweb> sitewebs, HttpServletRequest request)
			throws Exception {

		// Identifier le navigateur utilisé
		String nomNavigateur = getClientBrowser(request);
		if (nomNavigateur == null || nomNavigateur.isBlank()) {
			throw new Exception(MessageExceptionConstantes.ERREUR_IDENTIFICATION_NAVIGATEUR);
		}
		writer.print("SET BROWSER=");
		writer.print(StringUtils.trimWhitespace(StringUtils.uncapitalize(nomNavigateur)) + ".exe");
		writer.println("");
		writer.println("");

		for (

		Siteweb site : sitewebs) {
			writer.println("start /min %BROWSER% " + site.getUrl());
			writer.println("SET WAIT_TIME=3");
			writer.println("timeout %WAIT_TIME%");
			writer.println("");
		}

		return writer;

	}

	/**
	 * Retourne le nom du navigateur utilisé
	 * (https://gist.github.com/c0rp-aubakirov/a4349cbd187b33138969)
	 * 
	 * @param request
	 * 
	 * @return nomNavigateur
	 */
	private String getClientBrowser(HttpServletRequest request) {
		final String detailsNavigateur = request.getHeader("User-Agent");
		final String navigateur = detailsNavigateur.toLowerCase();

		String nomNavigateur = "";

		// Navigateur
		if (navigateur.contains("msie")) {
			String substring = detailsNavigateur.substring(detailsNavigateur.indexOf("MSIE")).split(";")[0];
			nomNavigateur = substring.split(" ")[0].replace("MSIE", "IE") + "-" + substring.split(" ")[1];
		} else if (navigateur.contains("safari") && navigateur.contains("version")) {
			nomNavigateur = (detailsNavigateur.substring(detailsNavigateur.indexOf("Safari")).split(" ")[0])
					.split("/")[0] + "-"
					+ (detailsNavigateur.substring(detailsNavigateur.indexOf("Version")).split(" ")[0]).split("/")[1];
		} else if (navigateur.contains("opr") || navigateur.contains("opera")) {
			if (navigateur.contains("opera"))
				nomNavigateur = (detailsNavigateur.substring(detailsNavigateur.indexOf("Opera")).split(" ")[0])
						.split("/")[0] + "-"
						+ (detailsNavigateur.substring(detailsNavigateur.indexOf("Version")).split(" ")[0])
								.split("/")[1];
			else if (navigateur.contains("opr"))
				nomNavigateur = ((detailsNavigateur.substring(detailsNavigateur.indexOf("OPR")).split(" ")[0])
						.replace("/", "-")).replace("OPR", "Opera");
		} else if (navigateur.contains("chrome")) {
			nomNavigateur = (detailsNavigateur.substring(detailsNavigateur.indexOf("Chrome")).split(" ")[0])
					.replace("/", "-");
		} else if ((navigateur.indexOf("mozilla/7.0") > -1) || (navigateur.indexOf("netscape6") != -1)
				|| (navigateur.indexOf("mozilla/4.7") != -1) || (navigateur.indexOf("mozilla/4.78") != -1)
				|| (navigateur.indexOf("mozilla/4.08") != -1) || (navigateur.indexOf("mozilla/3") != -1)) {
			// nomNavigateur=(detailsNavigateur.substring(detailsNavigateur.indexOf("MSIE")).split("
			// ")[0]).replace("/", "-");
			nomNavigateur = "Netscape-?";

		} else if (navigateur.contains("firefox")) {
			nomNavigateur = (detailsNavigateur.substring(detailsNavigateur.indexOf("Firefox")).split(" ")[0])
					.replace("/", "-");
		} else if (navigateur.contains("rv")) {
			nomNavigateur = "IE";
		} else {
			nomNavigateur = "UnKnown, More-Info: " + detailsNavigateur;
		}

		// Rechercher uniquement le nom 'simple' du navigateur (ex: firefox et non
		// firefox-83.0
		nomNavigateur = getSimpleName(nomNavigateur);

		return nomNavigateur;
	}

	private String getSimpleName(String nomNavigateur) {

		if (nomNavigateur.contains("IE")) {
			nomNavigateur = "IE";
		} else if (nomNavigateur.contains("Safari")) {
			nomNavigateur = "Safari";
		} else if (nomNavigateur.contains("Opera")) {
			nomNavigateur = "Opera";
		} else if (nomNavigateur.contains("Chrome")) {
			nomNavigateur = "Chrome";
		} else if (nomNavigateur.contains("Netscape")) {
			nomNavigateur = "Netscape";
		} else if (nomNavigateur.contains("Firefox")) {
			nomNavigateur = "Firefox";
		}
		return nomNavigateur;
	}

	/**
	 * Ecrit la liste des logiciels à ouvrir dans le fichier bat
	 * 
	 * @param writer
	 * @param repertoiresLogiciels
	 * 
	 * @return writer
	 */
	private PrintWriter addApps(PrintWriter writer, List<String> repertoiresLogiciels) {

		for (String repertoire : repertoiresLogiciels) {
			writer.println("start \"\" \"" + repertoire + "\"");
			writer.println("SET WAIT_TIME=3");
			writer.println("timeout %WAIT_TIME%");
			writer.println("");
		}
		return writer;

	}

	private String createExceptionMessage(Exception e) {

		String messageErreur = MessageExceptionConstantes.ECHEC;
		messageErreur = messageErreur + e.getMessage();

		return messageErreur;
	}

}
