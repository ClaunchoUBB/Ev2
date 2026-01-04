package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
