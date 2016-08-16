package md.convertit.hydraulicsystem.dao;

import java.util.List;

import md.convertit.hydraulicsystem.domain.Equipment;

public interface EquipmentDao {

	boolean save(Equipment equipment);
	
	
	List<Equipment> findAll();
	
	boolean update(Equipment newEquipment, Long id);
	
	boolean delete(Long id);
	
	long count();
	
	
}
