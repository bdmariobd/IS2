package testRunner;

import org.junit.runner.RunWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.platform.runner.*;
import org.junit.platform.suite.api.SelectPackages;;
@RunWith(JUnitPlatform.class)
@SelectPackages({"tIntegracion","tNegocio"}) 
public class testSuite {

	@BeforeAll
	void saveData() {
		
	}
	
	@AfterAll
	void restoreData() {
		
	}
}
