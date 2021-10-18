package rest;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import Entities.Carrera;
import Entities.Estudiante;
import Entities.Genero;
import Services.EstudianteService;

@Path("/estudiantes")
public class EstudianteREST {

	private EstudianteService es = new EstudianteService();
	
	public EstudianteREST() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Path("/{carrera}/{origen}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiante(@PathParam("carrera") Carrera carrera, @PathParam("origen") String origen) {
		return this.es.getEstudiantes(carrera, origen);
	}
	
	@GET
	@Path("/{genero}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> getEstudiante(@PathParam("genero") Genero genero) {
		return this.es.getEstudiantes(genero);
	}
	
	@GET
	@Path("/{libreta}")
	@Produces(MediaType.APPLICATION_JSON)
	public Estudiante getEstudiante(@PathParam("libreta") int libreta) {
		return this.es.getEstudiante(libreta);
	}
	
	@GET
	@Path("/{order}/{direction}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estudiante> get(@PathParam("order") String order, @PathParam("direction") String direction) {
		return this.es.getEstudiantes(order, direction);
	}
	
	@PUT
	//@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public void put(Estudiante e, Carrera c) {
		this.es.addCarrera(e, c, 1);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String post(Estudiante e) {
		this.es.addEstudiante(e);
		System.out.println(e.toString());
		return e.toString();
	}

}
