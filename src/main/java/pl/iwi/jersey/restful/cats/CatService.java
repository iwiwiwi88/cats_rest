package pl.iwi.jersey.restful.cats;

import java.util.List;
import pl.iwi.jersey.restful.helpers.CatsFileService;

public class CatService {

	CatsFileService fileService = new CatsFileService();
	
	public boolean addCat(Cat cat) {
		if (catExists(cat.getName())) {
			fileService.updateCatLine(getCat(cat.getName()), cat);
			return true;
		}
		fileService.addCatLine(cat);
		return catExists(cat.getName());
	}

	public List<Cat> getCats() {
		return fileService.getCatsFromFile();
	}

	public Cat getCat(String name) {
		Cat cat = null;
		for (Cat c : getCats()) {
			if (c.getName().equals(name)) {
				cat = c;
				break;
			}
		}
		return cat;
	}

	public boolean catExists(String name) {
		Cat cat = getCat(name);
		return cat != null;
	}
}
