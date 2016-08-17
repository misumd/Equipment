package md.convertit.hydraulicsystem.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import md.convertit.hydraulicsystem.dao.EquipmentDao;
import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.util.ConnectionUtil;

public class EquipmentDaoImpl implements EquipmentDao {

	private static final Logger log = Logger.getLogger(EquipmentDaoImpl.class.getName());

	private Connection conn;

	private PreparedStatement ps;

	@Override
	public boolean save(Equipment equipment) {

		try {
			conn = ConnectionUtil.getConnection();
			String sql = "INSERT INTO `mihai`.`equipment_data` (`name`, `description`, `tag`, `price`, `in_stock`) VALUES (?, ?, ?, '?', '?');";
			// obtin PreparedStatement de la connection
			ps = conn.prepareStatement(sql);
			ps.setString(1, equipment.getName());
			ps.setString(2, equipment.getDescription());
			ps.setString(3, equipment.getTag());
			ps.setDouble(4, equipment.getPrice());
			ps.setBoolean(5, equipment.isInStock());

			int affectedRows = ps.executeUpdate();
			log.info(String.format("Saved object, total affected rows: %d", affectedRows));
			return true;
		} catch (SQLException e) {
			// e.printStackTrace();
			log.severe(String.format("Exception: %s", e.getMessage()));
		}

		return false;
	}

	@Override
	public List<Equipment> findAll() {
		List<Equipment> equipmentList = new ArrayList<>();
		try {
			conn = ConnectionUtil.getConnection();
			String sql = "SELECT * FROM equipment_data";
			ps = conn.prepareStatement(sql);
			ResultSet set = ps.executeQuery();
			while (set.next()) {
				// column name: id
				long id = set.getInt("id"); // dami valoarea pentru coloana ID
				String equipmentname = set.getString("name");
				String description = set.getString("description");
				String tag = set.getString("tag");
				double price = set.getDouble(3);
				boolean inStock = set.getBoolean(0);
				// cream un obiect de tip User in care vom seta valorile de mai
				// sus
				Equipment equipment = new Equipment();
				equipment.setId(id);
				equipment.setName(equipmentname);
				equipment.setDescription(description);
				equipment.setTag(tag);
				equipment.setPrice(price);
				equipment.setInStock(inStock);
				
				equipmentList.add(equipment);
				// adaugam obiectul user in Lista
				
				log.info(String.format("Added new user to list: %s", equipment.toString()));
			}
		} catch (Exception e) {
			log.severe(String.format("Fatal error: %s", e.getMessage()));
		}
		log.info(String.format("Retrieved from database %d users", equipmentList.size()));
		
		return equipmentList;
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
