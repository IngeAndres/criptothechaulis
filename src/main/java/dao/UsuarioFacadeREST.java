package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.AbstractFacade;
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
import javax.ws.rs.core.MediaType;
import org.jboss.aerogear.security.otp.Totp;
import org.jboss.aerogear.security.otp.api.Base32;
import security.JwtManager;

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

    //<editor-fold defaultstate="collapsed" desc="Inicio de sesión">
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String iniciarSesion(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject jo = JsonParser.parseString(data).getAsJsonObject();

        String logi = jo.get("logi").getAsString();
        String pass = jo.get("pass").getAsString();

        Usuario u = searchUsuario(logi);

        if (u != null) {
            if (u.getPassUsuario().equals(pass)) {
                tokenServer = JwtManager.generateToken(logi);
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Código QR">
    @POST
    @Path("otpauthurl")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String generarOtpAuthURL(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject jo = JsonParser.parseString(data).getAsJsonObject();

        String logi = jo.get("logi").getAsString();
        Usuario u = searchUsuario(logi);

        if (u != null) {
            boolean isAuth = generateOtpAuthURL(response, logi, u);
            response.addProperty("auth", isAuth);
        }

        return g.toJson(response);
    }

    private boolean generateOtpAuthURL(JsonObject response, String logi, Usuario u) {
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
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Autenticación">
    @POST
    @Path("auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String validarAutenticacion(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject jo = JsonParser.parseString(data).getAsJsonObject();

        String logi = jo.get("logi").getAsString();
        String code = jo.get("code").getAsString();
        String tokenClient = jo.get("token").getAsString();

        Usuario u = searchUsuario(logi);

        if (u != null) {
            em.getTransaction().begin();
            String logiToken = JwtManager.verifyToken(tokenClient);

            if (logi.equals(logiToken)) {
                boolean resultado = authenticateUser(u, code);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    private boolean authenticateUser(Usuario u, String code) {
        String auth = u.getAutenticacion();
        if (auth == null || auth.isEmpty()) {
            u.setAutenticacion(secret);
        } else {
            secret = auth;
        }

        Totp totp = new Totp(secret);
        return code.equals(totp.now());
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cambio de contraseña">
    @PUT
    @Path("cambiarclave")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String cambiarClave(String data) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject jo = JsonParser.parseString(data).getAsJsonObject();

        String logi = jo.get("logi").getAsString();
        String pass = jo.get("pass").getAsString();
        String newPass = jo.get("newpass").getAsString();
        String tokenClient = jo.get("token").getAsString();
        Usuario u = searchUsuario(logi);

        if (u != null) {
            em.getTransaction().begin();
            String logiToken = JwtManager.verifyToken(tokenClient);

            if (logi.equals(logiToken)) {
                String resultado = changePassword(u, pass, newPass);
                response.addProperty("resultado", resultado);
                em.getTransaction().commit();
            } else {
                response.addProperty("resultado", "error");
            }
        }

        return g.toJson(response);
    }

    private String changePassword(Usuario u, String pass, String newPass) {
        if (u.getPassUsuario().equals(pass)) {
            if (u.getPassUsuario().equals(newPass)) {
                return "equals";
            } else {
                u.setPassUsuario(newPass);
                return "ok";
            }
        } else {
            return "passinc";
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Buscar usuario">
    private Usuario searchUsuario(String deno) {
        TypedQuery<Usuario> tq = em.createNamedQuery("Usuario.findByDenoUsuario", Usuario.class);
        tq.setParameter("denoUsuario", deno);
        Usuario u = tq.getSingleResult();
        return u != null ? u : null;
    }
    //</editor-fold>
}
