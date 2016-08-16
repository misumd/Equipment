package md.convertit.hydraulicsystem.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;






@XmlRootElement
public class UserXmlHelper {
	
	private List<Equipment> equipmentsList;
	public List<Equipment> getEquipmentList(){
		return equipmentsList;
	}
	@XmlElement
	public void setEquipmentList(List<Equipment> equipmentsList) {
		this.equipmentsList = equipmentsList;
	}
}
