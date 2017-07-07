package pl.iwi.jersey.restful.zoo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CatService {

	String path = "src\\main\\java\\resources\\cats.txt";
	File file = new File(path);

	public boolean addCat(Cat cat) {
		if (catExists(cat.getName()))
			// TODO editCat(cat);
			return true;
		try {
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(cat.getName() + "," + cat.getFavFood());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return catExists(cat.getName());
	}

	public List<Cat> getCats() {
		List<Cat> cats = new ArrayList<Cat>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] x = line.split(",");
				cats.add(new Cat(x[0], x[1]));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cats;
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
