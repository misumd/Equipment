package md.convertit.hydraulicsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;


import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.services.FileService;
import md.convertit.hydraulicsystem.services.impl.JsonFileService;
import md.convertit.hydraulicsystem.util.DemoData;

public class JsonFileServiceTest {
	
	FileService fs = new JsonFileService();
	final String PATH = "equipments.txt";
	final int TOTAL_DEMO_EQUIPMENTS = 50;
	
public void prepare() throws Exception{
		
		List<Equipment> equipmentsList = DemoData.getDemoUsers(TOTAL_DEMO_EQUIPMENTS);
		fs.saveAll(equipmentsList, PATH);
		
	}
@Test
public void saveAllTest() throws Exception{
	
		
	List<Equipment> equipmentsList = DemoData.getDemoUsers(TOTAL_DEMO_EQUIPMENTS);
	assertNotNull(equipmentsList);
	fs.saveAll(equipmentsList, PATH);
}
@Test
public void readAllTest() throws Exception{
	List<Equipment> equipmentsList = fs.readAll(PATH);
	
	assertNotNull(equipmentsList);
	assertFalse(equipmentsList.isEmpty());
	assertEquals(TOTAL_DEMO_EQUIPMENTS, equipmentsList.size());
	
}
}
