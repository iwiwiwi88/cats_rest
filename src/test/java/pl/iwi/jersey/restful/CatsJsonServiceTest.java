package pl.iwi.jersey.restful;

import java.util.ArrayList;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import pl.iwi.jersey.restful.cats.Cat;
import pl.iwi.jersey.restful.json.CatsJsonService;

public class CatsJsonServiceTest {

	static CatsJsonService cjs = new CatsJsonService();

	@BeforeClass
	public static void setup() {
		System.out.println("=== SETUP BEFORE TEST ===");
		cjs.writeToFile(cjs.constructJson(generateCats()));
	}
	
	@AfterClass
	public static void cleanUp() {
		System.out.println("=== CLEAN UP AFTER TESTS ===");
		cjs.writeToFile(cjs.constructJson(generateCats()));
	}

	@Test
	public void writeToFileTest() {
		List<Cat> cats = generateCats();
		String json = cjs.constructJson(cats);
		cjs.writeToFile(json);
	}

	@Test
	public void constructJsonTest() {
		List<Cat> cats = generateCats();
		String json = cjs.constructJson(cats);
		System.out.println(json);
	}

	@Test
	public void getCatsTest() {
		List<Cat> cats = cjs.getCats();
		for (Cat cat : cats) {
			System.out.println(cat);
		}
	}

	@Test
	public void existsTest() {
		Assert.assertTrue("Bonifacy should exist", cjs.exists("Bonifacy"));
		Assert.assertFalse("Burek is a dog, it shouldn't be here",
				cjs.exists("Burek"));
	}

	@Test
	public void updateCatTest() {
		Cat cat = new Cat("Bonifacy", "cream");
		Assert.assertTrue("Cat couldn't be updated", cjs.updateCat(cat));
		Assert.assertEquals("Cat's food wasn't updated",
				cjs.getCat("Bonifacy").getFavouriteFood(), "cream");
	}

	@Test
	public void addCatTest() {
		Cat cat = new Cat("Behemot", "alcohol");
		cjs.addCat(cat);
		Assert.assertTrue("Behemot wasn't added", cjs.exists(cat.getName()));
	}
	
	@Test 
	public void deleteCatTest() {
		String name = "Miki";
		cjs.deleteCat(name);
		Assert.assertFalse("Miki wasn't deleted",cjs.exists(name));
	}

	private static List<Cat> generateCats() {
		List<Cat> cats = new ArrayList<Cat>();
		cats.add(new Cat("Miki", "mice"));
		cats.add(new Cat("Bonifacy", "pork"));
		cats.add(new Cat("Maurycy", "water"));
		return cats;
	}

}
