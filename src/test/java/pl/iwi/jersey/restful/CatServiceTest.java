package pl.iwi.jersey.restful;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import pl.iwi.jersey.restful.zoo.Cat;
import pl.iwi.jersey.restful.zoo.CatService;

public class CatServiceTest {

	@Test
	public void getCatsTest() {
		CatService cs = new CatService();
		List<Cat> cats = cs.getCats();
		for (Cat cat : cats) {
			System.out.println(cat.toString());
		}
		Assert.assertTrue("There should be more than 0 cats!!!", cats.size()>0);
	}
	
	@Test
	public void addCatTest() {
		Cat cat = new Cat("Mruczek", "salami");
		CatService service = new CatService();
		
		service.addCat(cat);
		
		Assert.assertTrue("Cat Mruczek should be in our file!!!", service.catExists(cat.getName()));
	}
}
