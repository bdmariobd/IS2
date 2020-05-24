package testRunner;

import org.junit.runner.RunWith;
import org.junit.platform.runner.*;
import org.junit.platform.suite.api.SelectPackages;;
@RunWith(JUnitPlatform.class)
@SelectPackages({"tIntegracion","tNegocio"}) 
public class testSuite {

}
