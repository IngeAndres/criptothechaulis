package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Tipocuenta;
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
@Path("dto.tipocuenta")
public class TipocuentaFacadeREST extends AbstractFacade<Tipocuenta> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public TipocuentaFacadeREST() {
        super(Tipocuenta.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tipocuenta entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tipocuenta entity) {
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
    public Tipocuenta find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipocuenta> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipocuenta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    // METODO PARA LISTAR LOS TIPOS DE CUENTA
    @GET
    @Path("listartipocuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTipoCuenta() {
        Gson g = new Gson();

        TypedQuery<Tipocuenta> tq = em.createNamedQuery("Tipocuenta.findAll", Tipocuenta.class);
        List<Tipocuenta> list = tq.getResultList();
        List<Map<String, Object>> mapList = listarMapaTipoCuenta(list);

        return g.toJson(mapList);
    }

    // METODO PARA LISTAR LOS MAPAS DE TIPO DE CUENTA
    private List<Map<String, Object>> listarMapaTipoCuenta(List<Tipocuenta> list) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Tipocuenta tipoCuenta : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("idTipoCuenta", tipoCuenta.getIdTipoCuenta());
            map.put("denoTipoCuenta", tipoCuenta.getDenoTipoCuenta());
            mapList.add(map);
        }

        return mapList;
    }
    
    // METODO PARA OBTENER EL TIPO DE CUENTA POR DENO TIPO CUENTA
    public Tipocuenta obtenerTipoCuenta(String denoTipoCuenta) {
        TypedQuery<Tipocuenta> tq = em.createNamedQuery("Tipocuenta.findByDenoTipoCuenta", Tipocuenta.class);
        tq.setParameter("denoTipoCuenta", denoTipoCuenta);

        try {
            Tipocuenta tipoCuenta = tq.getSingleResult();
            return tipoCuenta;
        } catch (NoResultException e) {
            return null;
        }
    }
}
