package Rodriguez.Ev2.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Venta;
import Rodriguez.Ev2.Repositories.CotizacionRepository;
import Rodriguez.Ev2.Repositories.MuebleRepository;
import Rodriguez.Ev2.Repositories.VentaRepository;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repositoryVenta;

    @Autowired
    private CotizacionRepository repositoryCotizacion;

    @Autowired
    private MuebleRepository repositoryMueble;

    public Venta saveVenta(Venta ventaToBeSaved) {
        return repositoryVenta.save(ventaToBeSaved);
    }

    public Venta getVenta(int idVentaToBeRead) {
        return repositoryVenta.findById(idVentaToBeRead).orElse(null);
    }

    public List<Venta> getAllVentas(){
        return repositoryVenta.findAll();
    }

    public void removeVenta(int idVentaToBeDeleted) {
        repositoryVenta.deleteById(idVentaToBeDeleted);
    }

    public void removeVenta(Venta ventaToBeDeleted) {
        repositoryVenta.delete(ventaToBeDeleted);
    }

    public boolean checkStock(Cotizacion cotizacionToBeChecked) {
        for (Item item : cotizacionToBeChecked.getItems()) {

            Mueble mueble = item.getMueble();

            int cantidadRequerida = item.getCantidad();

            if (mueble.getStock() < cantidadRequerida) {
                return false;
            }
        }

        return true;
    }

    @Transactional
    public Venta crearVenta(int idCotizacion){
        Cotizacion cotizacion = repositoryCotizacion.findById(idCotizacion).orElse(null);

        if (cotizacion == null) {
            return null;
        }

        if (cotizacion.getItems().isEmpty()){
            return null;
        }

        if (!checkStock(cotizacion)) {
            return null;
        }

        for (Item item : cotizacion.getItems()) {
            Mueble mueble = item.getMueble();
            mueble.setStock(mueble.getStock() - item.getCantidad());
            repositoryMueble.save(mueble);
        }

        Venta ventaNueva = new Venta();
        ventaNueva.setCotizacion(cotizacion);

        return repositoryVenta.save(ventaNueva);
    }
}
