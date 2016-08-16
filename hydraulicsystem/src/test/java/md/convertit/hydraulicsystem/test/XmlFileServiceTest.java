package md.convertit.hydraulicsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.services.FileService;
import md.convertit.hydraulicsystem.services.impl.XmlFileService;
import md.convertit.hydraulicsystem.util.DemoData;



public class XmlFileServiceTest {
	FileService fs = new  XmlFileService();
	final String PATH = "equipments.xml";
	final int TOTAL_DEMO_USERS = 50;
	

	@Test
	public void prepare() throws Exception{
		
		List<Equipment> usersList = DemoData.getDemoUsers(TOTAL_DEMO_USERS);
		fs.saveAll(usersList, PATH);
		
	}
	@Test
	public void readAllTest() throws Exception{
		List<Equipment> userList = fs.readAll(PATH);
		
		assertNotNull(userList);
		assertFalse(userList.isEmpty());
		assertEquals(TOTAL_DEMO_USERS, userList.size());
		
	}
}
