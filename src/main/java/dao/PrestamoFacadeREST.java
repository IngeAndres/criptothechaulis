package dao;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dto.Cuenta;
import dto.Detalleprestamo;
import service.AbstractFacade;
import dto.Prestamo;
import dto.Tipocomprobante;
import dto.Tipoinformacionbien;
import dto.Tipoprestamo;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ing. Andres Gomez
 */
@Stateless
@Path("dto.prestamo")
public class PrestamoFacadeREST extends AbstractFacade<Prestamo> {

    @PersistenceContext(unitName = "com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU")
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_CriptoTheChaulis_war_1.0-SNAPSHOTPU");
    private EntityManager em = emf.createEntityManager();

    public PrestamoFacadeREST() {
        super(Prestamo.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Prestamo entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Prestamo entity) {
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
    public Prestamo find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Prestamo> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Prestamo> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path("llenarcombobox")
    public String llenarCombos() {

        TypedQuery<Tipoprestamo> queryTipoPrestamo = em.createNamedQuery("Tipoprestamo.findAll", Tipoprestamo.class);
        TypedQuery<Tipocomprobante> queryTipoComprobante = em.createNamedQuery("Tipocomprobante.findAll", Tipocomprobante.class);
        TypedQuery<Tipoinformacionbien> queryTipoInformacion = em.createNamedQuery("Tipoinformacionbien.findAll", Tipoinformacionbien.class);

        List<Tipoprestamo> listaTipoPrestamo = queryTipoPrestamo.getResultList();
        List<Tipocomprobante> listaTipoComprobante = queryTipoComprobante.getResultList();
        List<Tipoinformacionbien> listaTipoInformacion = queryTipoInformacion.getResultList();

        String jsondata = obtenerDatosCampos(listaTipoPrestamo, listaTipoComprobante, listaTipoInformacion);

        return jsondata;
    }

    private String obtenerDatosCampos(List<Tipoprestamo> ListTipoPrestamo, List<Tipocomprobante> ListTipoComprobante, List<Tipoinformacionbien> ListTipoInformacion) {
        List<Map<String, Object>> TipoprestamosMapList = new ArrayList<>();
        for (Tipoprestamo Tipoprestamo : ListTipoPrestamo) {
            Map<String, Object> TipoprestamoMap = new HashMap<>();
            TipoprestamoMap.put("ID", Tipoprestamo.getIdTipoPrestamo());
            TipoprestamoMap.put("NOMBRE", Tipoprestamo.getDenoTipoPrestamo());
            TipoprestamoMap.put("CATEGORIA", Tipoprestamo.getCategoriaTipoPrestamo());
            TipoprestamosMapList.add(TipoprestamoMap);
        }

        List<Map<String, Object>> TipocomprobanteMapList = new ArrayList<>();
        for (Tipocomprobante Tipoprestamo : ListTipoComprobante) {
            Map<String, Object> TipocomprobanteMap = new HashMap<>();
            TipocomprobanteMap.put("IDT", Tipoprestamo.getIdTipoComprobante());
            TipocomprobanteMap.put("NOMBRET", Tipoprestamo.getDenoTipoComprobante());
            TipocomprobanteMapList.add(TipocomprobanteMap);
        }

        List<Map<String, Object>> TipoinformacionMapList = new ArrayList<>();
        for (Tipoinformacionbien Tipoinfo : ListTipoInformacion) {
            Map<String, Object> TipoinformacionMap = new HashMap<>();
            TipoinformacionMap.put("IDI", Tipoinfo.getIdTipoInformacionBien());
            TipoinformacionMap.put("NOMBREI", Tipoinfo.getDenoTipoInformacionBien());
            TipoinformacionMapList.add(TipoinformacionMap);
        }

        Map<String, List<?>> data = new HashMap<>();
        data.put("Tipoprestamos", TipoprestamosMapList);
        data.put("Tipocomprobante", TipocomprobanteMapList);
        data.put("Tipoinformacion", TipoinformacionMapList);

        Gson gson = new Gson();
        String jsonData = gson.toJson(data);
        return jsonData;

    }

    public Integer registrarDetPrestamos(Detalleprestamo registrarDetPrest) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registrarDetPrest);
            em.getTransaction().commit();
            em.refresh(registrarDetPrest); // Actualiza el objeto con el ID generado

            return registrarDetPrest.getIdDetallePrestamo();
        } catch (Exception ex) {
            return 0;
        }
    }

    public boolean registrarPrestamos(Prestamo registrarPrest) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(registrarPrest);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @POST
    @Path("insertarPrestamo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertDatos(String data) throws ParseException {
        Gson g = new Gson();

        JsonObject request = JsonParser.parseString(data).getAsJsonObject();
        JsonObject responses = new JsonObject();

        int IdTipoInfo = Integer.parseInt(request.get("IdTipoInformacionBien").getAsString());
        String CuotasAdicionales = (request.get("CuotasAdicionales").getAsString());
        Double Monto = Double.valueOf(request.get("Monto").getAsString());
        String MonedaName = (request.get("Moneda").getAsString());
        Double Tasa = Double.valueOf(request.get("Tasa").getAsString());
        int Tiempo = Integer.parseInt(request.get("Tiempo").getAsString());
        String FechaPago = (request.get("FechaPago").getAsString());
        int IdTipoPrestamo = Integer.parseInt(request.get("IdTipoPrestamo").getAsString());
        String NumbCuenta = request.get("NumeroCuenta").getAsString();
        int IdTipoComprobante = Integer.parseInt(request.get("IdTipoComprobante").getAsString());
        Character cuotasAdicionales = null;
        if (CuotasAdicionales != null && !CuotasAdicionales.isEmpty()) {
            char primerCaracter = CuotasAdicionales.charAt(0);
            cuotasAdicionales = primerCaracter;
        }

        LocalDate fechaPagoDate = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fechaPagoDate = LocalDate.parse(FechaPago, formatter);
        } catch (DateTimeParseException e) {

            System.out.println("Error al convertir la fecha. Fecha proporcionada: " + FechaPago);// Manejo de la excepción si ocurre un error al parsear la fecha
        }

        TipoinformacionbienFacadeREST tipIn = new TipoinformacionbienFacadeREST();
        CuentaFacadeREST cuent = new CuentaFacadeREST();
        TipoprestamoFacadeREST tipPre = new TipoprestamoFacadeREST();
        TipocomprobanteFacadeREST tipCom = new TipocomprobanteFacadeREST();

        Tipoinformacionbien IngTipoInfo = tipIn.findTipoinformacionbien(IdTipoInfo);
        Cuenta IngCuenta = cuent.obtenerPorNumCuenta(NumbCuenta);
        Tipoprestamo IngTipoPres = tipPre.findTipoprestamo(IdTipoPrestamo);
        Tipocomprobante IngTipoComp = tipCom.findTipocomprobante(IdTipoComprobante);

        Detalleprestamo registrarDetPrest = new Detalleprestamo();
        registrarDetPrest.setIdTipoInformacionBien(IngTipoInfo);
        registrarDetPrest.setCuotasAdicionales((cuotasAdicionales));
        registrarDetPrest.setMonto(Monto);
        registrarDetPrest.setMoneda(MonedaName);
        registrarDetPrest.setTasa(Tasa);
        registrarDetPrest.setTiempo(Tiempo);
        registrarDetPrest.setFechaPago(java.sql.Date.valueOf(fechaPagoDate));

        int resultadoDet = registrarDetPrestamos(registrarDetPrest);

        if (resultadoDet != 0) {
            Prestamo registrarPrestamo = new Prestamo();

            DetalleprestamoFacadeREST detPres = new DetalleprestamoFacadeREST();
            Detalleprestamo IngDetalle = detPres.findDetalleprestamo(resultadoDet);

            registrarPrestamo.setIdTipoPrestamo(IngTipoPres);
            registrarPrestamo.setIdCuenta(IngCuenta);
            registrarPrestamo.setIdTipoComprobante(IngTipoComp);
            registrarPrestamo.setIdDetallePrestamo(IngDetalle);

            boolean resultado = registrarPrestamos(registrarPrestamo);

            if (resultado) {
                responses.addProperty("resultado", "ok");
                responses.addProperty("idDetalle", resultadoDet);
                responses.addProperty("TipoPrest", IngTipoPres.getDenoTipoPrestamo());

            } else {
                responses.addProperty("resultado", "error");
            }
        }
        return g.toJson(responses);

    }

    @GET
    @Path("listaraporte/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String ListarAporteMensual(@PathParam("id") Integer id) {

        DetalleprestamoFacadeREST detPres = new DetalleprestamoFacadeREST();

        Detalleprestamo detalle = detPres.findDetalleprestamo(id);
        Gson g = new Gson();
        JsonObject responses = new JsonObject();

        if (detalle != null) {
            Date fechaDate = detalle.getFechaPago();
            Instant instant = fechaDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDate fechaLocalDate = instant.atZone(zoneId).toLocalDate();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String fechaFormateada = fechaLocalDate.format(formatter);
            responses.addProperty("resultado", "ok");
            responses.addProperty("Fecha", fechaFormateada);
            responses.addProperty("Meses", detalle.getTiempo());
            responses.addProperty("Monto", detalle.getMonto());
            responses.addProperty("Tasa", detalle.getTasa());

        } else {
            responses.addProperty("resultado", "error");

        }
        return g.toJson(responses);

    }
}
