package pl.iwi.jersey.restful.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import pl.iwi.jersey.restful.cats.Cat;

public class CatsFileService {

	final public File FILE = new File("src\\main\\java\\resources\\cats.txt");
	
	public boolean addCatLine(Cat cat) {
		try {
			FileWriter fw = new FileWriter(FILE, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(cat.getName() + "," + cat.getFavFood());
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateCatLine(Cat oldCat, Cat newCat) {
		// TODO not yet implemented
		return false;
	}
	
	public List<Cat> getCatsFromFile() {
		List<Cat> cats = new ArrayList<Cat>();
		for (String line : getLines()) {
			String[] splitted = line.split(",");
			cats.add(new Cat(splitted[0], splitted[1]));
		}
		return cats;
	}
	
	@SuppressWarnings("resource")
	private List<String> getLines() {
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(FILE));
			for (String line; (line = br.readLine()) != null;) {
				lines.add(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
}
