package dao;

import service.AbstractFacade;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Usuario;
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
    public void edit(@PathParam("id") Integer id, Usuario entity) {
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
    public Usuario find(@PathParam("id") Integer id) {
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

    // INICIO DE SESIÓN
    @POST
    @Path("iniciarsesion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String iniciarSesionUsuario(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String logi = request.get("logi").getAsString();
        String pass = request.get("pass").getAsString();
        Usuario u = buscarUsuario(logi);

        if (u != null) {
            if (u.getPassUsuario().equals(pass)) {
                tokenServer = JWT.generateToken(logi);
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

    // CÓDIGO QR
    @POST
    @Path("generarurl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String generarURLUsuario(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String logi = request.get("logi").getAsString();
        Usuario u = buscarUsuario(logi);

        if (u != null) {
            boolean isAuth = generarURL(response, logi, u);
            response.addProperty("auth", isAuth);
        }

        return g.toJson(response);
    }

    // AUTENTICACIÓN DE DOS FACTORES
    @POST
    @Path("autenticacion")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validarAutenticacion(String data, @Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String logi = request.get("logi").getAsString();
        String code = request.get("code").getAsString();
        String tokenClient = extraerTokenHeader(headers);
        Usuario u = buscarUsuario(logi);

        if (u != null) {
            em.getTransaction().begin();
            String logiToken = JWT.verifyToken(tokenClient);

            if (logi.equals(logiToken)) {
                boolean resultado = autenticarCodigo(u, code);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    // ACTUALIZACIÓN DE CONTRASEÑA
    @PUT
    @Path("cambiarcontrasena")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String cambiarContrasenaUsuario(String data, @Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String logi = request.get("logi").getAsString();
        String pass = request.get("pass").getAsString();
        String newPass1 = request.get("newpass1").getAsString();
        String newPass2 = request.get("newpass2").getAsString();
        String tokenClient = extraerTokenHeader(headers);
        Usuario u = buscarUsuario(logi);

        if (u != null) {
            em.getTransaction().begin();
            String logiToken = JWT.verifyToken(tokenClient);

            if (logi.equals(logiToken)) {
                String resultado = cambiarContrasena(u, pass, newPass1, newPass2);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    // BÚSQUEDA DE USUARIO
    private Usuario buscarUsuario(String deno) {
        TypedQuery<Usuario> tq = em.createNamedQuery("Usuario.findByDenoUsuario", Usuario.class);
        tq.setParameter("denoUsuario", deno);
        Usuario u = tq.getSingleResult();
        return (u != null) ? u : null;
    }

    // GENERACIÓN DE URL OTP
    private boolean generarURL(JsonObject response, String logi, Usuario u) {
        String issuer = "TheChaulis";
        String user = logi;
        String auth = u.getAutenticacion();

        if (auth == null || auth.isEmpty()) {
            secret = Base32.random();
            String otpAuthURL = "otpauth://totp/" + issuer + ":" + user + "?secret=" + secret + "&issuer=" + issuer;
            response.addProperty("otpauthurl", otpAuthURL);
            return true;
        }

        return false;
    }

    // VALIDACIÓN DEL CÓDIGO DE 6 DÍGITOS
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

    // OBTENCIÓN DEL TOKENCLIENT DE LA AUTHORIZATION BEARER
    private String extraerTokenHeader(HttpHeaders headers) {
        String authorizationHeader = headers.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }

        return null;
    }

    // VALIDACIÓN DE CONTRASEÑA
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
}
