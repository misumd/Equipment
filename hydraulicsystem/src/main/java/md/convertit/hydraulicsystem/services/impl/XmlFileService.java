package md.convertit.hydraulicsystem.services.impl;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.domain.UserXmlHelper;
import md.convertit.hydraulicsystem.services.FileService;

public class XmlFileService implements FileService{
	private static final Logger log = Logger.getLogger(XmlFileService.class.getName());
	private File file;

	@SuppressWarnings("restriction")
	public void saveAll(List<Equipment> equipments, String path) throws Exception {
		File file = new File(path);
		JAXBContext jaxbContext = JAXBContext.newInstance(UserXmlHelper.class);
		Marshaller marshaler = jaxbContext.createMarshaller();
		marshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		
		UserXmlHelper helper = new UserXmlHelper();
		helper.setEquipmentList(equipments);		
		marshaler.marshal(helper, file);
		
	
		
		log.log(Level.INFO, String.format("saved objects in file: %s", file.getAbsolutePath()));
		
	}

	public List<Equipment> readAll(String path) throws Exception {
		file = new File(path);
		JAXBContext jaxbContext = JAXBContext.newInstance(UserXmlHelper.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		UserXmlHelper helper = (UserXmlHelper) unmarshaller.unmarshal(file);
		
		log.log(Level.INFO, String.format("Return total of %d objects from %s", helper.getEquipmentList().size(), file.getAbsolutePath()));
		
		
		return helper.getEquipmentList();
		
	}

}
