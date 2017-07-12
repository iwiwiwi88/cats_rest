package pl.iwi.jersey.restful.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import pl.iwi.jersey.restful.cats.Cat;

public class CatsJsonService {
	final public File FILE = new File("src\\main\\java\\resources\\cats.json");
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	private Type listType = new TypeToken<List<Cat>>() {
	}.getType();

	public boolean addCats(List<Cat> newCats) {
		List<Cat> oldCats = getCats();
		oldCats.addAll(newCats);
		writeToFile(constructJson(oldCats));
		return true;
	}
	
	public boolean addCat(Cat cat) {
		if (exists(cat.getName())) {
			return updateCat(cat);
		} else {
			List<Cat> cats = getCats();
			cats.add(cat);
			writeToFile(constructJson(cats));
		}
		return exists(cat.getName());
	}

	public boolean updateCat(Cat cat) {
		List<Cat> cats = getCats();
		for (Cat c : cats) {
			if (c.getName().equals(cat.getName())) {
				c.setFavouriteFood(cat.getFavouriteFood());
				writeToFile(constructJson(cats));
				return true;
			}
		}
		return false;
	}
	
	public boolean deleteCat(String name) {
		List<Cat> cats = getCats();
		for (int i = 0; i<cats.size(); i++) {
			if (cats.get(i).getName().equals(name)) {
				cats.remove(i);
				writeToFile(constructJson(cats));
				break;
			}
		}
		return !exists(name);
	}

	public List<Cat> getCats() {
		List<Cat> cats = new ArrayList<Cat>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			cats = gson.fromJson(br, listType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cats;
	}
	
	public Cat getCat(String name) {
		for (Cat cat : getCats()) {
			if (cat.getName().equals(name)) {
				return cat;
			}
		}
		return null;
	}

	public boolean exists(String name) {
		for (Cat cat : getCats()) {
			if (cat.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public String constructJson(List<Cat> cats) {
		return gson.toJson(cats);
	}
	
	public String constructJson(Cat cat) {
		return gson.toJson(cat);
	}

	public void writeToFile(String json) {
		try {
			FileWriter writer = new FileWriter(FILE);
			writer.write(json);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
