package md.convertit.hydraulicsystem.util;

import java.util.ArrayList;
import java.util.List;

import md.convertit.hydraulicsystem.domain.Equipment;

public class DemoData {
public static List<Equipment> getDemoUsers(int totalObjects) {
		
		List<Equipment> list = new ArrayList<Equipment>();
		
		for (int i = 0; i < totalObjects; i++) {
			
			Equipment equipment = new Equipment();
			
			equipment.setName("Name: " + i);
			equipment.setDescription("Description: " + i);
			equipment.setTag("Tag: " + i);
			equipment.setPrice( + i);
			equipment.setInStock(true);
			
			list.add(equipment);
			
		}
		return list;

}
}
