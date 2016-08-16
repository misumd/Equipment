package md.convertit.hydraulicsystem.services;

import java.util.List;

import md.convertit.hydraulicsystem.domain.Equipment;

public interface FileService {
	void saveAll(List<Equipment> equipments, String path) throws Exception;

	List<Equipment> readAll(String path) throws Exception;

}
