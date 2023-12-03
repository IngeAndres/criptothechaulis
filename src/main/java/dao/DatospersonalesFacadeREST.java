package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.AbstractFacade;
import dto.Datospersonales;
import dto.Distrito;
import dto.Tipodocumento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import security.JWT;

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

    // METODO PARA LISTAR LOS DATOS PERSONALES
    @POST
    @Path("listardatospersonales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDatosPersonales(@Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Datospersonales.listar", Object[].class);
            List<Object[]> list = tq.getResultList();
            List<Map<String, Object>> mapList = listarMapaDatosPersonales(list);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(mapList));
        } else {
            response.addProperty("resultado", "error");
        }

        return g.toJson(response);
    }

    // METODO PARA LISTAR LOS DATOS PERSONALES EN MAPAS
    private List<Map<String, Object>> listarMapaDatosPersonales(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();

        for (Object[] result : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("idPersona", result[0]);
            map.put("denoTipoDocumento", result[1]);
            map.put("docuPersona", result[2]);
            map.put("apPaPersona", result[3]);
            map.put("apMaPersona", result[4]);
            map.put("nombPersona", result[5]);
            map.put("celuPersona", result[6]);
            map.put("emailPersona", result[7]);
            listaMapas.add(map);
        }

        return listaMapas;
    }

    // METODO PARA OBTENER LOS DATOS PERSONALES POR IDPERSONA
    @POST
    @Path("obtenerdatospersonales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String obtenerDatosPersonalesPorId(int idPersona, @Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Datospersonales.obtenerDatosPersonalesPorId", Object[].class);
            tq.setParameter("idPersona", idPersona);
            Object[] usuario = tq.getSingleResult();

            Map<String, Object> map = new HashMap<>();
            map.put("idPersona", usuario[0]);
            map.put("denoTipoDocumento", usuario[1]);
            map.put("docuPersona", usuario[2]);
            map.put("ruc", usuario[3]);
            map.put("apPaPersona", usuario[4]);
            map.put("apMaPersona", usuario[5]);
            map.put("nombPersona", usuario[6]);
            map.put("genePersona", usuario[7]);
            map.put("fechPersona", usuario[8]);
            map.put("direPersona", usuario[9]);
            map.put("celuPersona", usuario[10]);
            map.put("emailPersona", usuario[11]);
            map.put("denoDistrito", usuario[12]);
            map.put("denoProvincia", usuario[13]);
            map.put("denoDepartamento", usuario[14]);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(map));
        } else {
            response.addProperty("resultado", "error");
        }

        return g.toJson(response);
    }

    // METODO PARA OBTENER DATOS PERSONALES POR DOCUPERSONA
    public Datospersonales obtenerDatosPersonales(String docuPersona) {
        TypedQuery<Datospersonales> tq = em.createNamedQuery("Datospersonales.findByDocuPersona", Datospersonales.class);
        tq.setParameter("docuPersona", docuPersona);

        try {
            Datospersonales tipoDocumento = tq.getSingleResult();
            return tipoDocumento;
        } catch (NoResultException e) {
            return null;
        }
    }

    // METODO PARA LISTAR DOCUPERSONA
    @GET
    @Path("listardocupersona")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDocuPersona() {
        Gson g = new Gson();

        TypedQuery<String> query = em.createNamedQuery("Datospersonales.obtenerDocuSinUsuario", String.class);
        List<String> resultList = query.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaDocuPersona(resultList);

        return g.toJson(listaMapas);
    }

    // METODO PARA LISTAR DOCUPERSONA EN MAPAS
    private List<Map<String, Object>> listarMapaDocuPersona(List<String> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();

        for (String result : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("docuPersona", result);
            listaMapas.add(mapa);
        }

        return listaMapas;
    }

    // METODO PARA INSERTAR LOS DATOS PERSONALES
    @POST
    @Path("insertardatospersonales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarDatosPersonales(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            String denoTipoDocumento = request.get("denoTipoDocumento").getAsString();
            String docuPersona = request.get("docuPersona").getAsString();
            String ruc = request.get("ruc").getAsString();
            String nombPersona = request.get("nombPersona").getAsString();
            String apPaPersona = request.get("apPaPersona").getAsString();
            String apMaPersona = request.get("apMaPersona").getAsString();
            char genePersona = request.get("genePersona").getAsString().charAt(0);
            String fecha = request.get("fechPersona").getAsString();
            String direPersona = request.get("direPersona").getAsString();
            String celuPersona = request.get("celuPersona").getAsString();
            String emailPersona = request.get("emailPersona").getAsString();
            String denoDistrito = request.get("denoDistrito").getAsString();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechPersona = sdf.parse(fecha);

            Tipodocumento tipoDocumento = new TipodocumentoFacadeREST().obtenerTipoDocumento(denoTipoDocumento);
            Distrito distrito = new DistritoFacadeREST().obtenerDistrito(denoDistrito);

            try {
                em.getTransaction().begin();
                Datospersonales datosPersonales = new Datospersonales();

                datosPersonales.setDocuPersona(docuPersona);
                datosPersonales.setRuc(ruc);
                datosPersonales.setNombPersona(nombPersona);
                datosPersonales.setApPaPersona(apPaPersona);
                datosPersonales.setApMaPersona(apMaPersona);
                datosPersonales.setGenePersona(genePersona);
                datosPersonales.setFechPersona(fechPersona);
                datosPersonales.setDirePersona(direPersona);
                datosPersonales.setCeluPersona(celuPersona);
                datosPersonales.setEmailPersona(emailPersona);
                datosPersonales.setIdTipoDocumento(tipoDocumento);
                datosPersonales.setIdDistrito(distrito);

                em.persist(datosPersonales);
                em.getTransaction().commit();
                response.addProperty("success", Boolean.TRUE);
            } catch (Exception e) {
                response.addProperty("success", Boolean.FALSE);
            }
            response.addProperty("resultado", "ok");
        } else {
            response.addProperty("resultado", "error");
        }

        return gson.toJson(response);
    }

    // METODO PARA EDITAR LOS DATOS PERSONALES
    @POST
    @Path("editardatospersonales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editarDatosPersonales(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            int idPersona = request.get("idPersona").getAsInt();
            String denoTipoDocumento = request.get("denoTipoDocumento").getAsString();
            String docuPersona = request.get("docuPersona").getAsString();
            String ruc = request.get("ruc").getAsString();
            String nombPersona = request.get("nombPersona").getAsString();
            String apPaPersona = request.get("apPaPersona").getAsString();
            String apMaPersona = request.get("apMaPersona").getAsString();
            char genePersona = request.get("genePersona").getAsString().charAt(0);
            String fecha = request.get("fechPersona").getAsString();
            String direPersona = request.get("direPersona").getAsString();
            String celuPersona = request.get("celuPersona").getAsString();
            String emailPersona = request.get("emailPersona").getAsString();
            String denoDistrito = request.get("denoDistrito").getAsString();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechPersona = sdf.parse(fecha);

            Tipodocumento tipoDocumento = new TipodocumentoFacadeREST().obtenerTipoDocumento(denoTipoDocumento);
            Distrito distrito = new DistritoFacadeREST().obtenerDistrito(denoDistrito);

            try {
                em.getTransaction().begin();
                Datospersonales datosPersonales = em.find(Datospersonales.class, idPersona);

                if (datosPersonales != null) {
                    datosPersonales.setDocuPersona(docuPersona);
                    datosPersonales.setRuc(ruc);
                    datosPersonales.setNombPersona(nombPersona);
                    datosPersonales.setApPaPersona(apPaPersona);
                    datosPersonales.setApMaPersona(apMaPersona);
                    datosPersonales.setGenePersona(genePersona);
                    datosPersonales.setFechPersona(fechPersona);
                    datosPersonales.setDirePersona(direPersona);
                    datosPersonales.setCeluPersona(celuPersona);
                    datosPersonales.setEmailPersona(emailPersona);
                    datosPersonales.setIdTipoDocumento(tipoDocumento);
                    datosPersonales.setIdDistrito(distrito);

                    em.merge(datosPersonales);
                    em.getTransaction().commit();
                    response.addProperty("success", Boolean.TRUE);
                } else {
                    response.addProperty("success", Boolean.FALSE);
                }
            } catch (Exception e) {
                response.addProperty("success", Boolean.FALSE);
            }
            response.addProperty("resultado", "ok");
        } else {
            response.addProperty("resultado", "error");
        }

        return gson.toJson(response);
    }

    // METODO PARA ELIMINAR LOS DATOS PERSONALES
    @POST
    @Path("eliminardatospersonales")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarDatosPersonales(String data, @Context HttpHeaders headers) {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            int idPersona = request.get("idPersona").getAsInt();

            try {
                em.getTransaction().begin();
                Datospersonales datosPersonales = em.find(Datospersonales.class, idPersona);

                if (datosPersonales != null) {
                    em.remove(datosPersonales);
                    em.getTransaction().commit();
                    response.addProperty("success", Boolean.TRUE);
                } else {
                    response.addProperty("success", Boolean.FALSE);
                }
            } catch (Exception ex) {
                response.addProperty("success", Boolean.FALSE);
            }
            response.addProperty("resultado", "ok");
        } else {
            response.addProperty("resultado", "error");
        }

        return gson.toJson(response);
    }

    // METODO PARA LA OBTENCION DEL TOKEN CLIENT DE LA AUTHORIZATION BEARER
    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
