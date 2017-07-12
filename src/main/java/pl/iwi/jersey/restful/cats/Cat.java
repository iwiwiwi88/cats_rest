package pl.iwi.jersey.restful.cats;

public class Cat {

	private String name = null;
	
	private String favouriteFood = null;
	
	public Cat(String name, String favFood) {
		this.name = name;
		this.favouriteFood = favFood;
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
		return favouriteFood;
	}

	public void setFavFood(String favFood) {
		this.favouriteFood = favFood;
	}
	
	public String toString() {
		return name+" the cat likes to eat "+favouriteFood;
		
	}
}
