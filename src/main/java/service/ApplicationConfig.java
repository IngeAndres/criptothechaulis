package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Ing. Andres Gomez
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(dao.CuentaFacadeREST.class);
        resources.add(dao.DatospersonalesFacadeREST.class);
        resources.add(dao.DepartamentoFacadeREST.class);
        resources.add(dao.DetalleprestamoFacadeREST.class);
        resources.add(dao.DistritoFacadeREST.class);
        resources.add(dao.OperacionescuentaspropiasFacadeREST.class);
        resources.add(dao.OperacionesotrascuentasFacadeREST.class);
        resources.add(dao.PrestamoFacadeREST.class);
        resources.add(dao.ProvinciaFacadeREST.class);
        resources.add(dao.TipocomprobanteFacadeREST.class);
        resources.add(dao.TipocuentaFacadeREST.class);
        resources.add(dao.TipodocumentoFacadeREST.class);
        resources.add(dao.TipoinformacionbienFacadeREST.class);
        resources.add(dao.TipooperacionFacadeREST.class);
        resources.add(dao.TipoprestamoFacadeREST.class);
        resources.add(dao.TipousuarioFacadeREST.class);
        resources.add(dao.UsuarioFacadeREST.class);
    }
    
}
