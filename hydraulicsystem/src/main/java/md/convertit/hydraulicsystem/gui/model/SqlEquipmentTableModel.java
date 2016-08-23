package md.convertit.hydraulicsystem.gui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import md.convertit.hydraulicsystem.dao.impl.EquipmentDaoImpl;
import md.convertit.hydraulicsystem.domain.Equipment;

public class SqlEquipmentTableModel extends AbstractTableModel {
	
private static final long serialVersionUID = -6649486573625737660L;
	
	EquipmentDaoImpl equipmentDao = new EquipmentDaoImpl();
	
	// store our users in memory
	private List<Equipment> equipments = new ArrayList<>();
	// store column names
	private String[] columnNames = {"ID", "name", "description", "tag", "price", "in_stock", "path_symbols"};
		
	public SqlEquipmentTableModel() {
		super();
		equipments = equipmentDao.findAll();
	}
	
	// is called when table need to know how many column table will have
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	// is called when table need to know how many rows table will have
	@Override
	public int getRowCount() {
		return equipments.size();
	}

	// is called when table need to know what value need to show in cell
	// where cell have this coordinates: (row and column)
	@Override
	public Object getValueAt(int row, int column) {
		// "ID", "Username", "Password", "Email"
		Equipment equipment = equipments.get(row);
		if(column == 0){
			return equipment.getId();
		} else if(column == 1){
			return equipment.getName();
		} else if(column == 2){
			return equipment.getDescription();
		} else if(column == 3){
			return equipment.getTag();
		} else if(column == 4){
			return equipment.getPrice();			
		} else if(column == 5){
			return equipment.isInStock();			
			
		} else if(column ==6){
			return equipment.getPath_symbols();
		}
		return "no data";
	}

	// is called when table need to know how to name a column at index: column
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	// method that will add a user to our table model.
	public void addEquipment(Equipment equipment){
		// add user to database
		equipmentDao.save(equipment);
		// update model user list
		equipments = equipmentDao.findAll();
		// fire an event to table that content of the model was changed
		
		fireTableDataChanged();
	}
	
	
	public void removeUser(int row){
		
		equipmentDao.delete((long) row);
		// get updated list of users from database
	equipments = equipmentDao.findAll();
		
		fireTableDataChanged();
	}

	// method that will return a user from our table model by row.
	public Equipment getEquipment(int row) {
		return equipments.get(row);
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

}
