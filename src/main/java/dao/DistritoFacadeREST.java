package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Distrito;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
@Path("dto.distrito")
public class DistritoFacadeREST extends AbstractFacade<Distrito> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public DistritoFacadeREST() {
        super(Distrito.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Distrito entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Distrito entity) {
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
    public Distrito find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Distrito> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Distrito> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    public int obtenerIdDistrito(String denoDistrito) {
        try {
            Query query = em.createNamedQuery("Distrito.findByDenoDistrito");
            query.setParameter("denoDistrito", denoDistrito);
            List<Distrito> results = query.getResultList();
            if (!results.isEmpty()) {
                return results.get(0).getIdDistrito();
            } else {
                return 0;
            }
        } finally {
            em.close();
        }
    }

    @GET
    @Path("listardistrito")

    public String listarDistritos() {
        Gson g = new Gson();
        TypedQuery<Distrito> query = em.createNamedQuery("Distrito.findAll", Distrito.class);

        List<Distrito> resultList = query.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaDatos(resultList);

        return g.toJson(listaMapas);
    }

    private List<Map<String, Object>> listarMapaDatos(List<Distrito> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Distrito distrito : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("IdDistrito", distrito.getIdDistrito());
            mapa.put("DenoDistrito", distrito.getDenoDistrito());
            mapa.put("ProvDistrito", distrito.getProvDistrito());
            mapa.put("DepaDistrito", distrito.getDepaDistrito());

            listaMapas.add(mapa);
        }
        return listaMapas;
    }
    
    public static void main(String[] args) {
        DistritoFacadeREST dfrest = new DistritoFacadeREST();
        System.out.println(dfrest.listarDistritos());
    }
}
