package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Datospersonales;
import dto.Tipousuario;
import service.AbstractFacade;
import dto.Usuario;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import security.JWT;

/**
 *
 * @author Ing. Andres Gomez
 */
@Stateless
@Path("dto.usuario")
public class UsuarioFacadeREST extends AbstractFacade<Usuario> {

    public static String secret;
    public static String tokenServer;

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public UsuarioFacadeREST() {
        super(Usuario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Usuario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") String id, Usuario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") String id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Usuario find(@PathParam("id") String id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Usuario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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

    // METODO PARA INICIAR SESION
    @POST
    @Path("iniciarsesion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String iniciarSesionUsuario(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String idUsuario = request.get("idUsuario").getAsString();
        String passUsuario = request.get("passUsuario").getAsString();
        Usuario u = em.find(Usuario.class, idUsuario);
        String usuario = obtenerNombresUsuario(idUsuario);

        if (u != null) {
            if (u.getPassUsuario().equals(passUsuario)) {
                tokenServer = JWT.generateToken(idUsuario);
                response.addProperty("usuario", usuario);
                response.addProperty("status", 200);
                response.addProperty("token", tokenServer);
            } else {
                response.addProperty("status", 401);
            }
        } else {
            response.addProperty("status", 404);
        }

        return g.toJson(response);
    }

    // METODO PARA OBTENER LOS NOMBRES DEL USUARIO
    private String obtenerNombresUsuario(String idUsuario) {
        TypedQuery<Object[]> tq = em.createNamedQuery("Usuario.obtenerNombresUsuario", Object[].class);
        tq.setParameter("idUsuario", idUsuario);

        try {
            Object[] usuario = tq.getSingleResult();
            return usuario[0] + " " + usuario[1];
        } catch (NoResultException e) {
            return null;
        }
    }

    // METODO PARA GENERAR EL CODIGO QR
    @POST
    @Path("generarurl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String generarURLUsuario(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String idUsuario = request.get("idUsuario").getAsString();
        Usuario u = em.find(Usuario.class, idUsuario);

        if (u != null) {
            boolean isAuth = generarURL(response, idUsuario, u);
            response.addProperty("auth", isAuth);
        }

        return g.toJson(response);
    }

    // METODO PARA AUTENTICAR EL FACTOR DE 2 PASOS
    @POST
    @Path("autenticacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validarAutenticacion(String data, @Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String idUsuario = request.get("idUsuario").getAsString();
        String code = request.get("code").getAsString();
        String tokenClient = extraerTokenHeader(headers);
        Usuario u = em.find(Usuario.class, idUsuario);

        if (u != null) {
            em.getTransaction().begin();
            String idToken = JWT.verifyToken(tokenClient);

            if (idUsuario.equals(idToken)) {
                boolean resultado = autenticarCodigo(u, code);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    // METODO PARA ACTUALIZAR LA CONTRASEÑA
    @PUT
    @Path("cambiarcontrasena")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String cambiarContrasenaUsuario(String data, @Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String idUsuario = request.get("idUsuario").getAsString();
        String passUsuario = request.get("passUsuario").getAsString();
        String newPass1 = request.get("newPass1").getAsString();
        String newPass2 = request.get("newPass2").getAsString();
        String tokenClient = extraerTokenHeader(headers);
        Usuario u = em.find(Usuario.class, idUsuario);

        if (u != null) {
            em.getTransaction().begin();
            String idToken = JWT.verifyToken(tokenClient);

            if (idUsuario.equals(idToken)) {
                String resultado = cambiarContrasena(u, passUsuario, newPass1, newPass2);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    // METODO PARA GENERAR EL URL OTP
    private boolean generarURL(JsonObject response, String idUsuario, Usuario u) {
        String issuer = "TheChaulis";
        String user = idUsuario;
        String auth = u.getAutenticacion();

        if (auth == null || auth.isEmpty()) {
            secret = Base32.random();
            String otpAuthURL = "otpauth://totp/" + issuer + ":" + user + "?secret=" + secret + "&issuer=" + issuer;
            response.addProperty("otpauthurl", otpAuthURL);
            return true;
        }

        return false;
    }

    // METODO PARA VALIDAR EL CODIGO DE 6 DIGITOS
    private boolean autenticarCodigo(Usuario u, String code) {
        String auth = u.getAutenticacion();

        if (auth == null || auth.isEmpty()) {
            u.setAutenticacion(secret);
        } else {
            secret = auth;
        }

        Totp totp = new Totp(secret);
        return code.equals(totp.now());
    }

    // METODO PARA VALIDAR LA CONTRASEÑA
    private String cambiarContrasena(Usuario u, String pass, String newPass1, String newPass2) {
        if (u.getPassUsuario().equals(pass)) {
            if (newPass1.equals(newPass2)) {
                if (u.getPassUsuario().equals(newPass1)) {
                    return "equals";
                } else {
                    u.setPassUsuario(newPass1);
                    return "ok";
                }
            } else {
                return "newpassinc";
            }
        } else {
            return "passinc";
        }
    }

    // METODO PARA LISTAR LOS USUARIOS DE TIPO CLIENTE
    @GET
    @Path("listarclientes")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarClientes(@Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Usuario.listar", Object[].class);
            Tipousuario tipoUsuario = em.find(Tipousuario.class, 2);
            tq.setParameter("idTipoUsuario", tipoUsuario);
            List<Object[]> list = tq.getResultList();
            List<Map<String, Object>> mapList = listarMapaClientes(list);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(mapList));
        } else {
            response.addProperty("resultado", "error");
        }
        return g.toJson(response);
    }

    // METODO PARA LISTAR LOS MAPAS DE CLIENTES
    private List<Map<String, Object>> listarMapaClientes(List<Object[]> list) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Object[] usuario : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("idUsuario", usuario[0]);
            map.put("denoTipoDocumento", usuario[1]);
            map.put("docuPersona", usuario[2]);
            map.put("apPaPersona", usuario[3]);
            map.put("apMaPersona", usuario[4]);
            map.put("nombPersona", usuario[5]);
            mapList.add(map);
        }

        return mapList;
    }

    // METODO PARA LISTAR LOS ID USUARIO
    @GET
    @Path("listaridusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarIdUsuario() {
        Gson g = new Gson();

        TypedQuery<Usuario> tq = em.createNamedQuery("Usuario.findAll", Usuario.class);
        List<Usuario> list = tq.getResultList();
        List<Map<String, Object>> mapList = listarMapaIdUsuario(list);

        return g.toJson(mapList);
    }

    // METODO PARA LISTAR LOS MAPAS DE ID USUARIO
    private List<Map<String, Object>> listarMapaIdUsuario(List<Usuario> list) {
        List<Map<String, Object>> mapList = new ArrayList<>();

        for (Usuario usuario : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("idUsuario", usuario.getIdUsuario());
            mapList.add(map);
        }

        return mapList;
    }

    // METODO PARA INSERTAR EL USUARIO
    @POST
    @Path("insertarusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarDatosPersonales(String data, @Context HttpHeaders headers) {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            String denoTipoUsuario = request.get("denoTipoUsuario").getAsString();
            String idUsuario = request.get("idUsuario").getAsString();
            String passUsuario = request.get("passUsuario").getAsString();

            Tipousuario tipoUsuario = new TipousuarioFacadeREST().obtenerTipoUsuario(denoTipoUsuario);
            Datospersonales datosPersonales = new DatospersonalesFacadeREST().obtenerDatosPersonales(idUsuario);
            try {
                em.getTransaction().begin();
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(idUsuario);
                usuario.setIdTipoUsuario(tipoUsuario);
                usuario.setIdPersona(datosPersonales);
                usuario.setPassUsuario(passUsuario);

                em.persist(usuario);
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

    // METODO PARA ELIMINAR EL USUARIO
    @POST
    @Path("eliminarusuario")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminarUsuario(String data, @Context HttpHeaders headers) {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            String idUsuario = request.get("idUsuario").getAsString();

            try {
                em.getTransaction().begin();
                Usuario usuario = em.find(Usuario.class, idUsuario);

                if (usuario != null) {
                    em.remove(usuario);
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

    // METODO PARA OBTENER EL TOKEN CLIENT DE LA AUTHORIZATION BEARER
    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }
}
