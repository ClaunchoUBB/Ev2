package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Services.CotizacionService;

@RestController
@RequestMapping("/ev2/cotizaciones")
public class CotizacionController {
    
    @Autowired
    private CotizacionService cotizacionService;

    @GetMapping
    public List<Cotizacion> getAll(){
        return cotizacionService.getAllCotizaciones();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> getCotizacionById(@PathVariable int id) {
        Cotizacion cotizacion = cotizacionService.getCotizacion(id);
        if (cotizacion != null) {
            return ResponseEntity.ok(cotizacion);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Cotizacion> createCotizacion(@RequestBody Cotizacion cotizacion) {
        Cotizacion nuevaCotizacion = cotizacionService.saveCotizacion(cotizacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCotizacion);
    }
    
    @PutMapping("/{idCotizacion}/items/{idItem}")
    public ResponseEntity<Cotizacion> addItemToCotizacion(
            @PathVariable int idCotizacion, 
            @PathVariable int idItem) {
        
        Cotizacion cotizacion = cotizacionService.getCotizacion(idCotizacion);
        if (cotizacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        try {
            cotizacionService.ItemToCotizacion(idCotizacion, idItem);
            Cotizacion cotizacionActualizada = cotizacionService.getCotizacion(idCotizacion);
            return ResponseEntity.ok(cotizacionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{idCotizacion}/items/{idItem}")
    public ResponseEntity<Cotizacion> removeItemFromCotizacion(
            @PathVariable int idCotizacion, 
            @PathVariable int idItem) {
        
        Cotizacion cotizacion = cotizacionService.getCotizacion(idCotizacion);
        if (cotizacion == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        try {
            cotizacionService.removeItemFromCotizacion(idCotizacion, idItem);
            Cotizacion cotizacionActualizada = cotizacionService.getCotizacion(idCotizacion);
            return ResponseEntity.ok(cotizacionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCotizacion(@PathVariable int id) {
        Cotizacion cotizacion = cotizacionService.getCotizacion(id);
        if (cotizacion != null) {
            cotizacionService.removeCotizacion(cotizacion);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}