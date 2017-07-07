package pl.iwi.jersey.restful;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pl.iwi.jersey.restful.zoo.Cat;
import pl.iwi.jersey.restful.zoo.CatService;

@Path("cats")
public class CatsResource {

	CatService service = new CatService();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getCats() {
		StringBuilder sb = new StringBuilder();
		for (Cat cat : service.getCats()) {
			sb.append(cat.toString()+"\n");
		}
		return sb.toString();
	}

	@GET
	@Path("{catName}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCat(@PathParam("catName") String catName) {
		Cat cat = service.getCat(catName);
		if (cat != null) {
			return cat.toString();
		} else {
			return "No cat with name: " + catName;
		}
	}

	@POST
	@Path("post/{catName}/{favFood}")
	@Produces(MediaType.TEXT_PLAIN)
	public String addCat(@PathParam("catName") String catName,
			@PathParam("favFood") String favFood) {
		service.addCat(new Cat(catName, favFood));
		return "New cat was born: " + service.getCat(catName).toString();
	}

}