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

import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Services.VarianteService;

@RestController
@RequestMapping("/ev2/variantes")
public class VarianteController {
    
    @Autowired
    private VarianteService varianteService;
    
    @GetMapping
    public ResponseEntity<List<Variante>> getAllVariantes() {
        List<Variante> variantes = varianteService.getAllVariantes();
        return ResponseEntity.ok(variantes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Variante> getVarianteById(@PathVariable int id) {
        Variante variante = varianteService.readVariante(id);
        if (variante != null) {
            return ResponseEntity.ok(variante);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Variante> createVariante(@RequestBody Variante variante) {
        Variante nuevaVariante = varianteService.saveVariante(variante);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVariante);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Variante> updateVariante(@PathVariable int id, @RequestBody Variante variante) {
        Variante varianteActualizada = varianteService.updateVariante(id, variante);
        if (varianteActualizada != null) {
            return ResponseEntity.ok(varianteActualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVariante(@PathVariable int id) {
        Variante variante = varianteService.readVariante(id);
        if (variante != null) {
            varianteService.deleteVariante(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}