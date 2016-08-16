package md.convertit.hydraulicsystem.daoImpl;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import md.convertit.hydraulicsystem.dao.EquipmentDao;
import md.convertit.hydraulicsystem.dao.impl.EquipmentDaoImpl;
import md.convertit.hydraulicsystem.domain.Equipment;

public class EquipmentDaoImplTest {
	private EquipmentDao dao = new EquipmentDaoImpl();
	
	@Test
	public void testSave(){
		
		Equipment equipment = new Equipment();
		
		equipment.setName("testEquipment");
		equipment.setDescription("testDescription");
		equipment.setTag("testTag");
		equipment.setPrice(50);
		equipment.setInStock(true);
	
		
		boolean result = dao.save(equipment);
		
		Assert.assertTrue(result);
	}
	
	@Test
	public void testFindAll(){
		List<Equipment> allEquipments= dao.findAll();
		Assert.assertNotNull(allEquipments);
		Assert.assertFalse(allEquipments.isEmpty());
		Assert.assertTrue(allEquipments.size()>0);
		
		
	}
	
	@Test
	public void testUpdate(){
		final Long Equipment_ID = 5L;
		
		Equipment newEquipment = new Equipment();
		
		newEquipment.setName("new_name");
		newEquipment.setDescription("new_description");
		newEquipment.setTag("new_tag");
		newEquipment.setPrice(70);
		newEquipment.setInStock(true);
		
		
		boolean result =dao.update(newEquipment, Equipment_ID);
		Assert.assertTrue(result);
	}
	
	@Test
	public void testDelete(){
		
		final Long Equipment_ID = 4L;
		boolean result = dao.delete(Equipment_ID);
		Assert.assertTrue(result);
	}

}
