package Rodriguez.Ev2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Venta;
import Rodriguez.Ev2.Repositories.VentaRepository;

@Service
public class VentaService {
    @Autowired private VentaRepository repositorioVentas;

    public Venta createVenta(Venta ventaToBeSaved){
        return repositorioVentas.save(ventaToBeSaved);
    }

    public Venta getVenta(int idVentaToBeRead){
        return repositorioVentas.findById(idVentaToBeRead).orElse(null);
    }

    public void removeVenta(int idVentaToBeDeleted){
        repositorioVentas.deleteById(idVentaToBeDeleted);
    }

    public void removeVenta(Venta ventaToBeDeleted){
        repositorioVentas.delete(ventaToBeDeleted);
    }
}
