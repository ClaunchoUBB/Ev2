package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Services.MuebleService;

@RestController
@RequestMapping("/ev2/muebles")
public class MuebleController {

    @Autowired
    private MuebleService servicioMueble;

    @GetMapping
    public List<Mueble> getAll(){
        return servicioMueble.getAllMuebles();
    }

    @GetExchange("/{id}")
    public ResponseEntity<Mueble> getById(@PathVariable int id){
        Mueble m = servicioMueble.getMueble(id);
        return (m!=null) ? ResponseEntity.ok(m) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Mueble> create(@RequestBody Mueble mueble) {
        return ResponseEntity.ok(servicioMueble.saveMueble(mueble));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> update(@PathVariable int id, @RequestBody Mueble reemplazo) {
        Mueble updated = servicioMueble.updateMueble(id, reemplazo);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        servicioMueble.deleteMueble(id);
        return ResponseEntity.noContent().build();
    }
}
