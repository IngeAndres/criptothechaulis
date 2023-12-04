package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static dao.UsuarioFacadeREST.secret;
import service.AbstractFacade;
import dto.Operacionesotrascuentas;
import dto.Usuario;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.jboss.aerogear.security.otp.Totp;
import security.JWT;

/**
 *
 * @author Ing. Andres Gomez
 */
@Stateless
@Path("dto.operacionesotrascuentas")
public class OperacionesotrascuentasFacadeREST extends AbstractFacade<Operacionesotrascuentas> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public OperacionesotrascuentasFacadeREST() {
        super(Operacionesotrascuentas.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Operacionesotrascuentas entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Operacionesotrascuentas entity) {
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
    public Operacionesotrascuentas find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Operacionesotrascuentas> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Operacionesotrascuentas> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    // METODO PARA LA OBTENCION DEL TOKEN CLIENT DE LA AUTHORIZATION BEARER
    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    //METODO PARA LISTAR LAS TRANSFERENCIAS
    @GET
    @Path("listartransferencias")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarTransferencias(@Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (true) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Operacionesotrascuentas.listar", Object[].class);
            List<Object[]> list = tq.getResultList();
            List<Map<String, Object>> mapList = listarMapaTransferencias(list);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(mapList));
        } else {
            response.addProperty("resultado", "error");
        }
        return g.toJson(response);
    }

    // METODO PARA LISTAR LOS MAPAS DE LAS TRANSFERENCIAS
    private List<Map<String, Object>> listarMapaTransferencias(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Object[] result : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("CODTRANSFER", result[0]);
            mapa.put("NUMECUENTAORIGEN", result[1]);
            mapa.put("APPAORIGEN", result[2]);
            mapa.put("APMAORIGEN", result[3]);
            mapa.put("NOMBORIGEN", result[4]);
            mapa.put("NUMECUENTADESTINO", result[5]);
            mapa.put("APPADESTINO", result[6]);
            mapa.put("APMADESTINO", result[7]);
            mapa.put("NOMBDESTINO", result[8]);
            mapa.put("MONTO", result[9]);
            mapa.put("MONEDA", result[10]);
            mapa.put("FECHA", result[11]);

            listaMapas.add(mapa);
        }
        return listaMapas;
    }

    /*
    //METODO PARA TRANSFERIR DINERO
    @POST
    @Path("transferirdinero")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String transferirDinero(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();
        
        String numbCntaOrig = request.get("cuentaOrigen").getAsString();
        String numbCntaDest = request.get("cuentaDestino").getAsString();
        double monto = request.get("monto").getAsDouble();
        String moneda = request.get("moneda").getAsString();
        
        Operacionesotrascuentas cntaOrig = null;
        
    }
    
    //METODO PARA OBTENER LA CUENTA POR NUMERO DE CUENTA
    private Operacionesotrascuentas getCntaWithNumbCnta (String numbCnta) {
        TypedQuery<Object[]> tq = em.createNamedQuery("O")
    }*/
}
