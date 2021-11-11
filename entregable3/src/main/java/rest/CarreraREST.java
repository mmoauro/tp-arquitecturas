package rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import Dto.InformacionCarreraDTO;
import Entities.Carrera;
import Services.CarreraService;
import Services.EstudianteService;

@Path("/carreras")
public class CarreraREST {
	private CarreraService cs = new CarreraService();
       
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Carrera> getCarreras(){
        return this.cs.getCarrerasWithStudents();
    }


    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public List<InformacionCarreraDTO> getCarrerasReport() {
    	return this.cs.getCarrerasReport();
    }


}
