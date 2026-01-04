package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rodriguez.Ev2.Model.Venta;
import Rodriguez.Ev2.Services.VentaService;

@RestController
@RequestMapping("/ev2/ventas")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;
    
    @GetMapping
    public ResponseEntity<List<Venta>> getAllVentas() {
        List<Venta> ventas = ventaService.getAllVentas();
        return ResponseEntity.ok(ventas);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Venta> getVentaById(@PathVariable int id) {
        Venta venta = ventaService.getVenta(id);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping("/cotizacion/{idCotizacion}")
    public ResponseEntity<?> crearVentaDesdeCotizacion(@PathVariable int idCotizacion) {
        Venta nuevaVenta = ventaService.crearVenta(idCotizacion);
        
        if (nuevaVenta == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("No se pudo crear la venta. Verifique que la cotización existe, tiene items y hay stock suficiente.");
        }
        
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
    }
    
        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVenta(@PathVariable int id) {
        Venta venta = ventaService.getVenta(id);
        if (venta != null) {
            ventaService.removeVenta(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
