package glaces;

import geometrie.Point;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Valentin.
 */
public class OceanUnitTest {
	@Test
	public void testOcean() {
		Ocean ocean = new Ocean(2, 20, 10);
		Assert.assertEquals("Nb iceberg", 2, ocean.getNbIceberg(), 0);
		Assert.assertEquals("Width", 20, ocean.getWidth(), 0);
		Assert.assertEquals("Height", 10, ocean.getHeight(), 0);
	}

	@Test
	public void testGetMatriceOcean() {
		Assert.assertTrue(false);
	}
}
