package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Datospersonales;
import java.util.ArrayList;
import java.util.Date;
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
@Path("dto.datospersonales")
public class DatospersonalesFacadeREST extends AbstractFacade<Datospersonales> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public DatospersonalesFacadeREST() {
        super(Datospersonales.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Datospersonales entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Datospersonales entity) {
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
    public Datospersonales find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Datospersonales> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Datospersonales> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @GET
    @Path("listardatos")
    public String listarDatosPersonales() {
        Gson g = new Gson();
        TypedQuery<Object[]> query = em.createNamedQuery("Datospersonales.listar", Object[].class);

        List<Object[]> resultList = query.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaDatos(resultList);

        return g.toJson(listaMapas);

    }

    private List<Map<String, Object>> listarMapaDatos(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Object[] result : resultList) {
            Integer idPersona = (Integer) result[0];
            String denoTipoDocumento = (String) result[1];
            String docuPersona = (String) result[2];
            String apPaPersona = (String) result[3];
            String apMaPersona = (String) result[4];
            String nombPersona = (String) result[5];
            String celuPersona = (String) result[6];
            String emailPersona = (String) result[7];

            Map<String, Object> mapa = new HashMap<>();
            mapa.put("idPersona", idPersona);
            mapa.put("denoTipoDocumento", denoTipoDocumento);
            mapa.put("docuPersona", docuPersona);
            mapa.put("apPaPersona", apPaPersona);
            mapa.put("apMaPersona", apMaPersona);
            mapa.put("nombPersona", nombPersona);
            mapa.put("celuPersona", celuPersona);
            mapa.put("emailPersona", emailPersona);

            listaMapas.add(mapa);
        }
        return listaMapas;
    }

    public static void main(String[] args) {
        DatospersonalesFacadeREST a = new DatospersonalesFacadeREST();
        String res = a.listarDatosPersonales();

        System.out.println(res);
    }
}
