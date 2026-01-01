package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Enum.tamano;
import Rodriguez.Ev2.Services.MuebleService;
import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class MuebleTest {
    
    @Autowired
    private MuebleService servicioMueble;

    public static Mueble buildMueble(){
        Mueble muebleCreado = new Mueble();
        muebleCreado.setEstado(true);
        muebleCreado.setMaterial("Madera");
        muebleCreado.setNombreMueble("Silla de prueba");
        muebleCreado.setTamano(tamano.GRANDE);
        muebleCreado.setPrecioBase(10000);
        muebleCreado.setTipo("Silla");
        return muebleCreado;
    }


    //El primer test sirve para comprobar si el mueble se guarda en la DB
    @Test
    public void saveTest(){
        Mueble muebleTesteo = buildMueble();

        servicioMueble.saveMueble(muebleTesteo);

        Mueble muebleEnDatabase = servicioMueble.getMueble(muebleTesteo.getIdMueble());

        assertEquals(muebleTesteo, muebleEnDatabase);
    }


    


}
