package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Provincia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ing. Andres Gomez
 */
@Stateless
@Path("dto.provincia")
public class ProvinciaFacadeREST extends AbstractFacade<Provincia> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public ProvinciaFacadeREST() {
        super(Provincia.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Provincia entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Provincia entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Provincia find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Provincia> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Provincia> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    // METODO PARA LISTAR LAS PROVINCIAS
    @POST
    @Path("listarprovincia")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarProvincias(String denoDepartamento) {
        Gson g = new Gson();
        
        TypedQuery<String> tq = em.createNamedQuery("Provincia.findByDepartamento", String.class);
        tq.setParameter("denoDepartamento", denoDepartamento);
        
        List<String> resultList = tq.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaProvincias(resultList);
        
        return g.toJson(listaMapas);
    }

    // METODO PARA LISTAR LAS PROVINCIAS EN MAPAS
    private List<Map<String, Object>> listarMapaProvincias(List<String> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        
        for (String provincia : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("denoProvincia", provincia);
            listaMapas.add(mapa);
        }
        
        return listaMapas;
    }
}
