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
import java.time.LocalDate;
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

    @GET
    @Path("listardatos")
    public String listarDatosPersonales() {
        Gson g = new Gson();
        TypedQuery<Object[]> query = em.createNamedQuery("Datospersonales.listar", Object[].class);

        List<Object[]> resultList = query.getResultList();
        List<Map<String, Object>> listaMapas = listarMapaDatos(resultList);

        return g.toJson(listaMapas);

    }

    private List<Map<String, Object>> listarMapaDatos(List<Object[]> resultList) {
        List<Map<String, Object>> listaMapas = new ArrayList<>();
        for (Object[] result : resultList) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("idPersona", result[0]);
            mapa.put("denoTipoDocumento", result[1]);
            mapa.put("docuPersona", result[2]);
            mapa.put("apPaPersona", result[3]);
            mapa.put("apMaPersona", result[4]);
            mapa.put("nombPersona", result[5]);
            mapa.put("celuPersona", result[6]);
            mapa.put("emailPersona", result[7]);

            listaMapas.add(mapa);
        }
        return listaMapas;
    }
    
    public boolean insertarDatosPersonales(Datospersonales datosPersonales) {
        em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(datosPersonales);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    
    @POST
    @Path("insertarDatos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertDatos(String data) throws ParseException{
        Gson g = new Gson();
        JsonObject response = new JsonObject();
        JsonObject request = JsonParser.parseString(data).getAsJsonObject();

        String tipodocumento = request.get("documento").getAsString();
        String docuPersona = request.get("numerodoc").getAsString();
        String RUC = request.get("ruc").getAsString();
        String NombPersona = request.get("nombre").getAsString();
        String ApPaPersona = request.get("paterno").getAsString();
        String ApMaPersona = request.get("materno").getAsString();
        String GenePersona = request.get("genero").getAsString();
        String Fecha = request.get("fecha").getAsString();
        String DirePersona = request.get("direccion").getAsString();
        String CeluPersona = request.get("celular").getAsString();
        String EmailPersona = request.get("email").getAsString();
        String Distrito = request.get("distrito").getAsString();
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date FechPersona = formatoFecha.parse(Fecha);
        
        TipodocumentoFacadeREST tipdoc = new TipodocumentoFacadeREST();
        int IdTipoDocumento = tipdoc.obtenerIdTipDoc(tipodocumento);
        
        DistritoFacadeREST distric = new DistritoFacadeREST();
        int IdDistrito = distric.obtenerIdDistrito(Distrito);
        
        Datospersonales datosPersonales = new Datospersonales();
        datosPersonales.setDocuPersona(docuPersona);
    datosPersonales.setRuc(RUC);
    datosPersonales.setNombPersona(NombPersona);
    datosPersonales.setApPaPersona(ApPaPersona);
    datosPersonales.setApMaPersona(ApMaPersona);
    datosPersonales.setGenePersona(GenePersona.charAt(0));
    datosPersonales.setFechPersona(FechPersona);
    datosPersonales.setDirePersona(DirePersona);
    datosPersonales.setCeluPersona(CeluPersona);
    datosPersonales.setEmailPersona(EmailPersona);
    
    datosPersonales.setIdTipoDocumento(new Tipodocumento(IdTipoDocumento));
    datosPersonales.setIdDistrito(new Distrito(IdDistrito));
    
    boolean insertar = insertarDatosPersonales(datosPersonales);

    if (insertar) {
        response.addProperty("success", Boolean.TRUE);
    } else {
        response.addProperty("success", Boolean.FALSE);
    }

    return g.toJson(response);
    }
    
}
