package demarrageordi.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import demarrageordi.entity.Logiciel;
import demarrageordi.entity.Siteweb;

public interface CreationBatchService {

	public File createBatch(List<Logiciel> logiciels, List<Siteweb> sitesWeb, HttpServletRequest request)
			throws Exception;

}
