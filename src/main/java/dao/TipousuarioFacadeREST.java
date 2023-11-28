package dao;

import com.google.gson.Gson;
import service.AbstractFacade;
import dto.Tipousuario;
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
@Path("dto.tipousuario")
public class TipousuarioFacadeREST extends AbstractFacade<Tipousuario> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public TipousuarioFacadeREST() {
        super(Tipousuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tipousuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Tipousuario entity) {
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
    public Tipousuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipousuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tipousuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    // METODO PARA LISTAR LOS TIPOS DE USUARIO
    @GET
    @Path("listartipousuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTipoUsuario() {
        Gson g = new Gson();

        TypedQuery<Tipousuario> tq = em.createNamedQuery("Tipousuario.findAll", Tipousuario.class);
        List<Tipousuario> resultList = tq.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaTiposUsuario(resultList);

        return g.toJson(listaMapas);
    }

    // METODO PARA LISTAR LOS TIPOS DE USUARIO EN MAPAS
    private List<Map<String, Object>> listarMapaTiposUsuario(List<Tipousuario> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();

        for (Tipousuario tipoDocumento : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("idTipoUsuario", tipoDocumento.getIdTipoUsuario());
            mapa.put("denoTipoUsuario", tipoDocumento.getDenoTipoUsuario());
            listaMapas.add(mapa);
        }

        return listaMapas;
    }
}
