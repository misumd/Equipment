package md.convertit.hydraulicsystem.services.impl;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.services.FileService;

public class JsonFileService implements FileService {

	private static final Logger log = Logger.getLogger(JsonFileService.class.getName());
	
	private Gson gson = new Gson();

	private File file;

	public void saveAll(List<Equipment> equipments, String path) throws Exception {
		file = new File(path);
		FileWriter fw = new FileWriter(path);
		
		gson.toJson(equipments, fw);
		
		fw.close();
		
		log.log(Level.INFO, "objects saved to: " + file.getAbsolutePath());

	}

	public List<Equipment> readAll(String path) throws Exception {
file = new File(path);
		
		FileReader fr = new FileReader(file);
		Type type = new TypeToken<List<Equipment>>(){}.getType();
		List<Equipment> equipments = gson.fromJson(fr, type);
		log.log(Level.INFO, String.format("Loaded from file %s total %d objects", file.getAbsolutePath(),equipments.size()));
		
		return equipments;
	}

}
