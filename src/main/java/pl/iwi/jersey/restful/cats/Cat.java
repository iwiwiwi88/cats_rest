package pl.iwi.jersey.restful.cats;

public class Cat {

	private String name = null;
	
	private String favFood = null;
	
	public Cat(String name, String favFood) {
		this.name = name;
		this.favFood = favFood;
	}

	public Cat(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFavFood() {
		return favFood;
	}

	public void setFavFood(String favFood) {
		this.favFood = favFood;
	}
	
	public String toString() {
		return name+" the cat likes to eat "+favFood;
		
	}
}
