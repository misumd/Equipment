package md.convertit.hydraulicsystem.dao.impl;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;


import md.convertit.hydraulicsystem.dao.EquipmentDao;
import md.convertit.hydraulicsystem.domain.Equipment;

public class EquipmentDaoImpl implements EquipmentDao {
	
	private static final Logger log = Logger.getLogger(EquipmentDaoImpl.class.getName());
	
	Connection conn;

	@Override
	public boolean save(Equipment equipment) {
		
		return false;
	}

	@Override
	public List<Equipment> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Equipment newEquipment, Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
