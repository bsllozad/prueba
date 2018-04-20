package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManagerFactory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
class TestUsuario {
	
	private final static Logger log = LoggerFactory.getLogger(TestUsuario.class);
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Test
	void test() {
		assertNotNull(entityManagerFactory, "El entityManagerFactory es nulo");
		
	}

}