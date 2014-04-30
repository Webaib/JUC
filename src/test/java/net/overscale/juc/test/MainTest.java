package net.overscale.juc.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.overscale.juc.Juc;
import net.overscale.juc.test.res.SimpleObj;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {

	private SimpleObj so1;

	private SimpleObj so2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		so1 = new SimpleObj(12, "qqq", 1.2D, new String[] { "a", "ar", "asd" },
				new double[] { 1.0D, 1.1D });

		so2 = new SimpleObj(11, "qqqa", 1.21D,
				new String[] { "a", "as", "asd" }, new double[] { 1.0D, 1.2D });
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws IllegalArgumentException, IllegalAccessException {
		System.out.println("State1, obj1: " + so1);
		System.out.println("State1, obj2: " + so2 + "\n");

		assertFalse(so1.equals(so2));

		assertFalse(Juc.compareAndSync(so1, so2, false));

		assertFalse(so1.equals(so2));

		assertFalse(Juc.compareAndSync(so1, so2, true));

		System.out.println("\nState2, obj1: " + so1);
		System.out.println("State2, obj2: " + so2 + "\n");

		assertTrue(so1.equals(so2));

	}

}
