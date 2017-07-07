package pl.iwi.jersey.restful.zoo;

import java.util.ArrayList;
import java.util.List;

public class CatsZoo {

	List<Cat> cats = new ArrayList<Cat>();

	public CatsZoo() {
		cats.add(new Cat("Piki", "water"));
		cats.add(new Cat("Maurycy", "cream"));
		cats.add(new Cat("Bonifacy", "mice"));
	}

	public void addCat(String name, String favFood) {
		cats.add(new Cat(name, favFood));
	}

	public void addCat(Cat cat) {
		cats.add(cat);
	}

	public List<Cat> getCats() {
		return cats;
	}

	public Cat getCat(String name) {
		for (Cat cat : cats) {
			if (cat.getName().equals(name)) {
				return cat;
			}
		}
		return null;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Cat cat : cats) {
			sb.append(cat+"\n");
		}
		return sb.toString();
	}
}
