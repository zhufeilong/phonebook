package hackajob.phonebook.test.repository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hackajob.phonebook.repository.IdGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdGeneratorTest {
	
	@Autowired
	private IdGenerator generator1;
	
	@Autowired
	private IdGenerator generator2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetNextId() {
		Assert.assertEquals(1, generator1.getNextId());
		Assert.assertEquals(2, generator1.getNextId());
		Assert.assertEquals(1, generator2.getNextId());
		Assert.assertEquals(2, generator2.getNextId());
	}

}
