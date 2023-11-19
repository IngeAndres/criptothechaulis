package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Datospersonales;
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
        TypedQuery<Object[]> query = em.createNamedQuery("Datospersonales.listar", Object[].class);

        List<Object[]> DatosPersonales = query.getResultList();
        List<Map<String, Object>> DatosPersonalesMapList = new ArrayList<>();

        for (Object[] DatosPersonalesData : DatosPersonales) {
            Map<String, Object> DatosPersonalesMap = new HashMap<>();
            DatosPersonalesMap.put("ID", DatosPersonalesData[0]);
            DatosPersonalesMap.put("TIPO_DOCUMENTO", DatosPersonalesData[1]);
            DatosPersonalesMap.put("NUMERO_DOCUMENTO", DatosPersonalesData[2]);
            DatosPersonalesMap.put("APELLIDO_PATERNO", DatosPersonalesData[3]);
            DatosPersonalesMap.put("APELLIDO_MATERNO", DatosPersonalesData[4]);
            DatosPersonalesMap.put("NOMBRE", DatosPersonalesData[5]);
            DatosPersonalesMap.put("CELULAR", DatosPersonalesData[6]);
            DatosPersonalesMap.put("CORREO_ELECTRONICO", DatosPersonalesData[7]);
            DatosPersonalesMapList.add(DatosPersonalesMap);
        }

        Gson gson = new Gson();
        return gson.toJson(DatosPersonalesMapList);
    }

    public static void main(String[] args) {
        DatospersonalesFacadeREST Datos = new DatospersonalesFacadeREST();
        String lista = Datos.listarDatosPersonales();
        System.out.println(lista);
    }
}
