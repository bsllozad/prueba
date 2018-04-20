package co.edu.usbcali.demo.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class TestConfigXml {
	
	private final static Logger log = LoggerFactory.getLogger(TestConfigXml.class);

	@Test
	void test() {
		ApplicationContext app = new ClassPathXmlApplicationContext("appCon.xml");
		assertNotNull(app, "El app es nulo");
		
		Vehiculo vehiculo = (Vehiculo)app.getBean(Vehiculo.class);
		assertNotNull(vehiculo, "El Vehiculo es nulo");
		
		log.info("Marca: " + vehiculo.getMarca());
		log.info("Color: " + vehiculo.getColor());
		log.info("Cilindraje: " + vehiculo.getMotor().getCilindraje());
	}

}
