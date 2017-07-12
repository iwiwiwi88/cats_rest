package pl.iwi.jersey.restful.helpers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.iwi.jersey.restful.cats.Cat;

public class CatsJsonService {
	final public File FILE = new File("src\\main\\java\\resources\\cats.json");
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	public boolean addCat(Cat cat) {
		return true;
	}

	public boolean addCats(List<Cat> cats) {

		return true;
	}

	public List<Cat> getCats() {
		List<Cat> cats = new ArrayList<Cat>();
		return cats;
	}

	public boolean exists(String name) {

		return true;
	}
	
	public String constructJson(List<Cat> cats) {
		return gson.toJson(cats);
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
