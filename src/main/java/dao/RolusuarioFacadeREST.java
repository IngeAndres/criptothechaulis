/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Rol;
import dto.Rolusuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

/**
 *
 * @author carlo
 */
@Stateless
@Path("dto.rolusuario")
public class RolusuarioFacadeREST extends AbstractFacade<Rolusuario> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public RolusuarioFacadeREST() {
        super(Rolusuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Rolusuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Rolusuario entity) {
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
    public Rolusuario find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rolusuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rolusuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    @POST
    @Path("asignarrol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String asignarRolUsuario(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            int idRol = request.get("tipoPrestamo").getAsInt();
            String idUsuario = request.get("nombPersona").getAsString();

            // Verificar si el nombre de usuario ya está registrado en Rolusuario
            boolean usuarioRegistrado = verificarUsuarioRegistrado(idUsuario);

            if (!usuarioRegistrado) {
                try {
                    em.getTransaction().begin();

                    Rolusuario rolUsuario = new Rolusuario();

                    rolUsuario.setCodRol(idRol);
                    rolUsuario.setIdUsuario(idUsuario);
                    rolUsuario.setActvRol(true);

                    em.persist(rolUsuario);
                    em.getTransaction().commit();
                    response.addProperty("success", Boolean.TRUE);
                } catch (Exception e) {
                    response.addProperty("success", Boolean.FALSE);
                }
                response.addProperty("resultado", "ok");
            } else {
                response.addProperty("success", Boolean.FALSE);
                response.addProperty("resultado", "Usuario ya registrado en Rolusuario");
            }
        } else {
            response.addProperty("resultado", "error");
        }

        return gson.toJson(response);
    }

    @POST
    @Path("quitarrol")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String quitarRolUsuario(String data, @Context HttpHeaders headers) throws ParseException {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            String idUsuario = request.get("nombPersona").getAsString();

            boolean rolActualizado = actualizarActRol(idUsuario);

            if (rolActualizado) {
                response.addProperty("success", Boolean.TRUE);
                response.addProperty("resultado", "ok");
            } else {
                response.addProperty("success", Boolean.FALSE);
                response.addProperty("resultado", "No se pudo actualizar el campo actRol del usuario");
            }
        } else {
            response.addProperty("resultado", "error");
        }

        return gson.toJson(response);
    }

// Método para actualizar el campo actRol del usuario
    private boolean actualizarActRol(String idUsuario) {
        try {
            TypedQuery<Rolusuario> query = em.createQuery("SELECT r FROM Rolusuario r WHERE r.idUsuario = :idUsuario", Rolusuario.class);
            query.setParameter("idUsuario", idUsuario);
            Rolusuario rolUsuario = query.getSingleResult();

            // Actualizar el campo actRol a false si se encontró el registro
            if (rolUsuario != null) {
                rolUsuario.setActvRol(false);
                em.getTransaction().begin();
                em.merge(rolUsuario);
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            // Manejar excepciones aquí
            e.printStackTrace();
        }
        return false;
    }

// Método para verificar si un usuario ya está registrado en Rolusuario
    private boolean verificarUsuarioRegistrado(String idUsuario) {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(r) FROM Rolusuario r WHERE r.idUsuario = :idUsuario", Long.class);
        query.setParameter("idUsuario", idUsuario);
        long count = query.getSingleResult();
        return count > 0;
    }

    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
