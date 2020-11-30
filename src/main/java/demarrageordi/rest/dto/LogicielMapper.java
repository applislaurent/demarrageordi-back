package demarrageordi.rest.dto;

import java.util.ArrayList;
import java.util.List;

import demarrageordi.entity.Logiciel;

public class LogicielMapper {

	public static List<Logiciel> toLogiciels(List<LogicielDto> logicielDtos) {

		List<Logiciel> logiciels = new ArrayList<Logiciel>();
		if (logicielDtos != null && !logicielDtos.isEmpty()) {
			for (LogicielDto l : logicielDtos) {
				logiciels.add(toLogiciel(l));
			}
		}

		return logiciels;

	}

	public static Logiciel toLogiciel(LogicielDto logicielDto) {

		Logiciel logiciel = new Logiciel();
		if (logicielDto != null) {
			logiciel.setId(logicielDto.getId());
			logiciel.setNom(logicielDto.getNom());
			logiciel.setRepertoire(logicielDto.getRepertoire());
		}
		return logiciel;

	}

	public static List<LogicielDto> toLogicielDtos(List<Logiciel> logiciels) {

		List<LogicielDto> logicielDtos = new ArrayList<LogicielDto>();
		if (logiciels != null && !logiciels.isEmpty()) {
			for (Logiciel l : logiciels) {
				logicielDtos.add(toLogicielDto(l));
			}
		}

		return logicielDtos;

	}

	public static LogicielDto toLogicielDto(Logiciel logiciel) {

		LogicielDto logicielDto = new LogicielDto();
		if (logiciel != null) {
			logicielDto.setId(logiciel.getId());
			logicielDto.setNom(logiciel.getNom());
			logicielDto.setRepertoire(logiciel.getRepertoire());
		}
		return logicielDto;

	}

}
