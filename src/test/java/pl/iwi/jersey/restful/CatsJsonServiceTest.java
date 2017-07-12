package pl.iwi.jersey.restful;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import pl.iwi.jersey.restful.cats.Cat;
import pl.iwi.jersey.restful.helpers.CatsJsonService;

public class CatsJsonServiceTest {

	CatsJsonService cjs = new CatsJsonService();
	
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

	private List<Cat> generateCats() {
		List<Cat> cats = new ArrayList<Cat>();
		cats.add(new Cat("Miki","mice"));
		cats.add(new Cat("Bonifacy","pork"));
		cats.add(new Cat("Maurycy","water"));
		return cats;
	}

}
