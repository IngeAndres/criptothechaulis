package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Cuenta;
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
@Path("dto.cuenta")
public class CuentaFacadeREST extends AbstractFacade<Cuenta> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public CuentaFacadeREST() {
        super(Cuenta.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Cuenta entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Cuenta entity) {
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
    public Cuenta find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cuenta> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Cuenta> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("listarcuenta")
    public String listarCuentasEmp() {
        Gson g = new Gson();
        TypedQuery<Object[]> query = em.createNamedQuery("Cuenta.listarcuentaEmp", Object[].class);

        List<Object[]> resultList = query.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaCuentaEmp(resultList);

        return g.toJson(listaMapas);
    }
    
    private List<Map<String, Object>> listarMapaCuentaEmp(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Object[] result : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("apPaPersona", result[0]);
            mapa.put("apMaPersona", result[1]);
            mapa.put("nombPersona", result[2]);
            mapa.put("denoTipoCuenta", result[3]);
            mapa.put("numbCuenta", result[4]);
            mapa.put("saldoDisponible", result[5]);
            mapa.put("saldoContable", result[6]);
            mapa.put("estadoCuenta", result[7]);
            mapa.put("fechaApertura", result[8]);

            listaMapas.add(mapa);
        }
        return listaMapas;
    }
    
     public Cuenta obtenerPorNumCuenta(String NumberCuenta) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createNamedQuery("Cuenta.findByNumbCuenta");
            query.setParameter("numbCuenta", NumberCuenta);

            List<Cuenta> results = query.getResultList();

            if (!results.isEmpty()) {
                return results.get(0);
            } else {
                return null;
            }
        } finally {
            em.close();
        }
    }
}
