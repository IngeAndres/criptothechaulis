package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import service.AbstractFacade;
import dto.Cuenta;
import dto.Tipocuenta;
import dto.Usuario;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
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

    //METODO PARA LISTAR LAS CUENTAS ACTIVAS
    @GET
    @Path("listarcuentas")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String listarCuentasActivas(@Context HttpHeaders headers) {
        Gson g = new Gson();
        JsonObject response = new JsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            TypedQuery<Object[]> tq = em.createNamedQuery("Cuenta.listarCuentasActivas", Object[].class);
            tq.setParameter("estadoCuenta", "Activo");
            List<Object[]> list = tq.getResultList();
            List<Map<String, Object>> mapList = listarMapaCuentasActivas(list);
            response.addProperty("resultado", "ok");
            response.add("datos", g.toJsonTree(mapList));
        } else {
            response.addProperty("resultado", "error");
        }
        return g.toJson(response);
    }

    // METODO PARA LISTAR LOS MAPAS DE LAS CUENTAS ACTIVAS
    private List<Map<String, Object>> listarMapaCuentasActivas(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Object[] result : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("idCuenta", result[0]);
            mapa.put("docuPersona", result[1]);
            mapa.put("apPaPersona", result[2]);
            mapa.put("apMaPersona", result[3]);
            mapa.put("nombPersona", result[4]);
            mapa.put("denoTipoCuenta", result[5]);
            mapa.put("numbCuenta", result[6]);
            mapa.put("saldoDisponible", result[7]);
            mapa.put("saldoContable", result[8]);
            mapa.put("estadoCuenta", result[9]);
            mapa.put("fechaApertura", result[10]);

            listaMapas.add(mapa);
        }
        return listaMapas;
    }

    // METODO PARA OBTENER LA CUENTA POR EL NUMBCUENTA
    public Cuenta findByNumbCuenta(String numbCuenta) {
        TypedQuery<Cuenta> tq = em.createNamedQuery("Cuenta.findByNumbCuenta", Cuenta.class);
        tq.setParameter("numbCuenta", numbCuenta);

        try {
            Cuenta cuenta = tq.getSingleResult();
            return cuenta;
        } catch (NoResultException e) {
            return null;
        }
    }

    // METODO PARA OBTENER EL NUEVO NUMBCUENTA
    public int obtenerNuevoNumbCuenta() {
        TypedQuery<String> tq = em.createNamedQuery("Cuenta.findLastNumbCuenta", String.class);
        tq.setMaxResults(1);

        try {
            String ultimoNumbCuenta = tq.getSingleResult();
            int numbCuenta = Integer.parseInt(ultimoNumbCuenta.substring(8)) + 1;
            return numbCuenta;
        } catch (NoResultException e) {
            return 0;
        }
    }

    // METODO PARA INSERTAR LA CUENTA
    @POST
    @Path("insertarcuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertarCuenta(String data, @Context HttpHeaders headers) throws ParseException {
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            String idUsuario = request.get("idUsuario").getAsString();
            String denoTipoCuenta = request.get("denoTipoCuenta").getAsString();
            double saldoDisponible = request.get("saldoDisponible").getAsDouble();
            double saldoContable = request.get("saldoContable").getAsDouble();

            Date fechaApertura = Calendar.getInstance().getTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaApertura);
            calendar.add(Calendar.YEAR, 5);
            Date fechaCierre = calendar.getTime();

            String codEntidad = "0017";
            String codSucursal = "0300";
            int nuevoNumbCuenta = obtenerNuevoNumbCuenta() == 0 ? 100001 : obtenerNuevoNumbCuenta();
            String numCuenta = String.format("%08d", nuevoNumbCuenta);

            String numbCuenta = codEntidad + codSucursal + numCuenta;

            String cod3Entidad = codEntidad.substring(codEntidad.length() - 3);
            String cod3Sucursal = codSucursal.substring(codSucursal.length() - 3);
            String ceros = "0000";
            String numCuentaConCeros = ceros + numCuenta;
            String numeroAleatorio = String.format("%02d", new Random().nextInt(100));
            String cci = cod3Entidad + cod3Sucursal + numCuentaConCeros + numeroAleatorio;

            Tipocuenta tipoCuenta = new TipocuentaFacadeREST().obtenerTipoCuenta(denoTipoCuenta);
            Usuario usuario = new Usuario(idUsuario);
            try {
                em.getTransaction().begin();
                Cuenta cuenta = new Cuenta();
                cuenta.setIdUsuario(usuario);
                cuenta.setIdTipoCuenta(tipoCuenta);
                cuenta.setNumbCuenta(numbCuenta);
                cuenta.setCci(cci);
                cuenta.setSaldoDisponible(saldoDisponible);
                cuenta.setSaldoContable(saldoContable);
                cuenta.setEstadoCuenta("Activo");
                cuenta.setFechaApertura(fechaApertura);
                cuenta.setFechaCierre(fechaCierre);

                em.persist(cuenta);
                em.getTransaction().commit();
                response.addProperty("success", Boolean.TRUE);
            } catch (Exception e) {
                response.addProperty("success", Boolean.FALSE);
            }
            response.addProperty("resultado", "ok");
        } else {
            response.addProperty("resultado", "error");
        }

        return g.toJson(response);
    }

    // METODO PARA ELIMINAR LA CUENTA
    @POST
    @Path("inhabilitarcuenta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String inhabilitarCuenta(String data, @Context HttpHeaders headers) {
        Gson gson = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tokenClient = extraerTokenHeader(headers);
        String idToken = JWT.verifyToken(tokenClient);

        if (idToken != null) {
            int idCuenta = request.get("idCuenta").getAsInt();

            try {
                em.getTransaction().begin();
                Cuenta cuenta = em.find(Cuenta.class, idCuenta);

                if (cuenta != null) {
                    cuenta.setEstadoCuenta("Inactivo");
                    em.merge(cuenta);
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
