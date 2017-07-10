package pl.iwi.jersey.restful.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
			writer.write(formatCatEntry(cat));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public boolean addCatLines(List<Cat> cats) {
		try {
			FileWriter fw = new FileWriter(FILE, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.write(formatCatEntry(cats.get(0)));
			cats.remove(0);
			for (Cat cat : cats) {
				writer.newLine();
				writer.write(formatCatEntry(cat));
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	private String formatCatEntry(Cat cat) {
		return cat.getName() + "," + cat.getFavFood();
	}

	public boolean updateCatLine(Cat newCat) {
		List<Cat> cats = getCatsFromFile();
		for (Cat cat : cats) {
			if (cat.getName().equals(newCat.getName())) {
				cat.setFavFood(newCat.getFavFood());
				break;
			}
		}
		rewriteCatFile(cats);
		return exists(newCat.getName());
	}

	public boolean rewriteCatFile(List<Cat> cats) {
		try {
			new PrintWriter(FILE).close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		addCatLines(cats);
		return true;
	}

	public List<Cat> getCatsFromFile() {
		List<Cat> cats = new ArrayList<Cat>();
		for (String line : getLines()) {
			String[] splitted = line.split(",");
			cats.add(new Cat(splitted[0], splitted[1]));
		}
		return cats;
	}

	public boolean exists(String name) {
		boolean exists = false;
		for (Cat c : getCatsFromFile()) {
			if (c.getName().equals(name)) {
				exists = true;
				break;
			}
		}
		return exists;
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

	public boolean deleteCatLine(String name) {
		if (!exists(name)) {
			System.out.println("Cat "+name+" didn't exist anyway");
			return true;
		}
		List<Cat> cats = getCatsFromFile();
		int catsId = -1;
		for (int i = 0; i < cats.size(); i++) {
			if (cats.get(i).getName().equals(name)) {
				catsId = i;
				break;
			}
		}
		cats.remove(catsId);
		rewriteCatFile(cats);
		return !exists(name);
	}
}
