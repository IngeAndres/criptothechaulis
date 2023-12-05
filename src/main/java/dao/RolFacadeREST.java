/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Datospersonales;
import dto.Distrito;
import dto.Rol;
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
import security.AES;

/**
 *
 * @author carlo
 */
@Stateless
@Path("dto.rol")
public class RolFacadeREST extends AbstractFacade<Rol> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public RolFacadeREST() {
        super(Rol.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Rol entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Rol entity) {
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
    public Rol find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rol> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rol> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("listarrol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarDatosPersonales(@Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Rol.listar", Object[].class);
            List<Object[]> list = tq.getResultList();
            List<Map<String, Object>> mapList = listarRol(list);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(mapList));
        } else {
            response.addProperty("resultado", "error");
        }

        return g.toJson(response);
    }

    @POST
    @Path("insertarrol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarDatosPersonales(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {

            String nombPersona = request.get("nombPersona").getAsString();

            String fecha = request.get("fechPersona").getAsString();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechPersona = sdf.parse(fecha);

            try {
                em.getTransaction().begin();

                Rol datosPersonales = new Rol();

                datosPersonales.setNombRol(nombPersona);

                datosPersonales.setFechRol(fechPersona);

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

    @POST
    @Path("editarrol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String editarRol(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            int idRol = request.get("idPersona").getAsInt();
            String nombRol = request.get("nombPersona").getAsString();
            String fecha = request.get("fechPersona").getAsString();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date fechRol = sdf.parse(fecha);

            try {
                em.getTransaction().begin();
                Rol rol = em.find(Rol.class, idRol);

                if (rol != null) {
                    rol.setNombRol(nombRol);
                    rol.setFechRol(fechRol);

                    em.merge(rol);
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

    @GET
    @Path("llenarcomboboxrol")
    public String llenarCombosRol() {
        TypedQuery<Rol> queryRoles = em.createNamedQuery("Rol.findAll", Rol.class);

        List<Rol> listaRoles = queryRoles.getResultList();

        String jsondata = obtenerDatosRoles(listaRoles);

        return jsondata;
    }

    private String obtenerDatosRoles(List<Rol> listaRoles) {
        List<Map<String, Object>> rolesMapList = new ArrayList<>();
        for (Rol rol : listaRoles) {
            Map<String, Object> rolMap = new HashMap<>();
            rolMap.put("ID", rol.getCodRol());
            rolMap.put("NOMBRE", rol.getNombRol());
            rolMap.put("FECHA", rol.getFechRol());
            // Agregar otros campos relevantes de Rol si es necesario

            rolesMapList.add(rolMap);
        }

        Map<String, List<?>> data = new HashMap<>();
        data.put("Roles", rolesMapList);

        Gson gson = new Gson();
        String jsonData = gson.toJson(data);
        return jsonData;
    }

    private List<Map<String, Object>> listarRol(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();

        for (Object[] result : resultList) {
            Map<String, Object> map = new HashMap<>();
            map.put("idRol", result[0]);
            map.put("NombreRol", result[1]);
            map.put("FechaRol", AES.encrypt(result[2] + "", "pato"));
            listaMapas.add(map);
        }

        return listaMapas;
    }

    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

}
