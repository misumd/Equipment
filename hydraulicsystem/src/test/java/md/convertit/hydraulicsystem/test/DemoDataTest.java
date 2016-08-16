package md.convertit.hydraulicsystem.test;

import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;
import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.util.DemoData;

public class DemoDataTest {
	
	
@Test
public void demoTestData(){
	List<Equipment> list = new DemoData().getDemoUsers(50);
	assertNotNull(list);
	assertFalse(list.isEmpty());
	
	
}

}
