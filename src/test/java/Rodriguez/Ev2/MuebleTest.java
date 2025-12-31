package Rodriguez.Ev2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Services.MuebleService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class MuebleTest {
    
    @Autowired
    private MuebleService servicioMueble;

    protected static Mueble buildMueble(){
        Mueble muebleCreado = new Mueble();
        
        return muebleCreado;
    }


}
