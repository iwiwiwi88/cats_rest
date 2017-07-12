package pl.iwi.jersey.restful;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pl.iwi.jersey.restful.cats.Cat;
import pl.iwi.jersey.restful.json.CatsJsonService;

@Path("cats")
public class CatsResource {

	CatsJsonService service = new CatsJsonService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getCats() {
		return service.constructJson(service.getCats());
	}

	@GET
	@Path("{catName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCat(@PathParam("catName") String catName) {
		Cat cat = service.getCat(catName);
		if (cat != null) {
			return service.constructJson(cat);
		} else {
			return "No cat with name: " + catName;
		}
	}

	@POST
	@Path("post/{catName}/{favFood}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addCat(@PathParam("catName") String catName,
			@PathParam("favFood") String favFood) {
		service.addCat(new Cat(catName, favFood));
		return service.constructJson(service.getCat(catName));
	}

	@DELETE
	@Path("delete/{catName}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCat(@PathParam("catName") String catName) {
		if (service.deleteCat(catName)) {
			return service.constructJson(service.getCats());
		} else {
			return "No cat with name: " + catName;
		}
	}
}