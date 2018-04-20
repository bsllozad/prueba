package co.edu.usbcali.banco.jpa;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

class TestCliente {
	
	private static final Logger log = LoggerFactory.getLogger(TestCliente.class);
	
	static EntityManagerFactory entityManagerFactory = null;
	static EntityManager entityManager = null;
	
	BigDecimal clieId = new BigDecimal(142020);
	
	@BeforeAll
	public static void iniciar() {
		log.info("Ejecuto el beforeAll");
		
		entityManagerFactory = Persistence.createEntityManagerFactory("banco-logica");
		entityManager = entityManagerFactory.createEntityManager();
		
	}
	
	@AfterAll
	public static void finalizar() {
		log.info("Ejecuto el AfterAll");
		entityManager.close();
		entityManagerFactory.close();
	}
	
	@BeforeEach
	public void antes() {
		log.info("Ejecuto el before");
	}
	
	@AfterEach
	public void despues() {
		log.info("Ejecuto el after");
	}
	

	@Test
	@DisplayName("CrearCliente")
	void atest() {
		log.info("Se ejecuta la prueba a");
		assertNotNull(entityManager, "El EntityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente, "El cliente ya existe");
		
		cliente= new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Av siempre viva 123");
		cliente.setEmail("correo@email.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("5555 55 55");
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 2L);
		assertNotNull(tipoDocumento, "El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("Consultar Cliente")
	void btest() {
		log.info("Se ejecuta la prueba b");
		assertNotNull(entityManager, "El EntityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		log.info("Id: " + cliente.getClieId());
		log.info("Nombre: " + cliente.getNombre());
	}
	
	@Test
	@DisplayName("ModificarCliente")
	void ctest() {
		log.info("Se ejecuta la prueba c");
		assertNotNull(entityManager, "El EntityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		cliente.setActivo('N');
		cliente.setClieId(clieId);
		cliente.setDireccion("Av siempre viva 123 c");
		cliente.setEmail("correo@email.com");
		cliente.setNombre("Homero Simpson");
		cliente.setTelefono("5555 55 55");
		
		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 2L);
		assertNotNull(tipoDocumento, "El tipo de documento no existe");
		
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("EliminarCliente")
	void dtest() {
		log.info("Se ejecuta la prueba d");
		assertNotNull(entityManager, "El EntityManager es nulo");
		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente, "El cliente no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("Consultar Todos los clientes")
	void etest() {
		log.info("Se ejecuta la prueba e");
		assertNotNull(entityManager, "El EntityManager es nulo");
		String jpql = "SELECT cli FROM Cliente cli";
		@SuppressWarnings("unchecked")
		List<Cliente> clientes = entityManager.createQuery(jpql).getResultList();
		
		clientes.forEach(cliente -> {
			log.info("Id: " + cliente.getClieId() + " - " + "Nombre: " + cliente.getNombre());
		});
		
	}

}

