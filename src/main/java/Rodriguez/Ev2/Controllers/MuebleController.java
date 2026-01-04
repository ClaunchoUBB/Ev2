package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
