package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Tipodocumento;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
@Path("dto.tipodocumento")
public class TipodocumentoFacadeREST extends AbstractFacade<Tipodocumento> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public TipodocumentoFacadeREST() {
        super(Tipodocumento.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tipodocumento entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tipodocumento entity) {
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
    public Tipodocumento find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipodocumento> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipodocumento> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    // METODO PARA LISTAR LOS TIPOS DE DOCUMENTO
    @GET
    @Path("listartipodocumento")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTipoDocumento() {
        Gson g = new Gson();

        TypedQuery<Tipodocumento> query = em.createNamedQuery("Tipodocumento.findAll", Tipodocumento.class);
        List<Tipodocumento> list = query.getResultList();
        List<Map<String, Object>> mapList = listarMapaTipoDocumento(list);

        return g.toJson(mapList);
    }

    // METODO PARA LISTAR LOS MAPAS DE TIPO DE DOCUMENTO 
    private List<Map<String, Object>> listarMapaTipoDocumento(List<Tipodocumento> list) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Tipodocumento tipoDocumento : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("idTipoDocumento", tipoDocumento.getIdTipoDocumento());
            map.put("denoTipoDocumento", tipoDocumento.getDenoTipoDocumento());
            mapList.add(map);
        }

        return mapList;
    }

    // METODO PARA OBTENER EL TIPO DE DOCUMENTO POR DENO TIPO DOCUMENTO
    public Tipodocumento obtenerTipoDocumento(String denoTipoDocumento) {
        TypedQuery<Tipodocumento> tq = em.createNamedQuery("Tipodocumento.findByDenoTipoDocumento", Tipodocumento.class);
        tq.setParameter("denoTipoDocumento", denoTipoDocumento);

        try {
            Tipodocumento tipoDocumento = tq.getSingleResult();
            return tipoDocumento;
        } catch (NoResultException e) {
            return null;
        }
    }
}
