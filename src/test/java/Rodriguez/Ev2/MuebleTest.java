package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    public static Mueble buildMueble() {
        Mueble muebleCreado = new Mueble();
        muebleCreado.setEstado(true);
        muebleCreado.setMaterial("Madera");
        muebleCreado.setNombreMueble("Silla de prueba");
        muebleCreado.setTamano(tamano.GRANDE);
        muebleCreado.setPrecioBase(10000);
        muebleCreado.setTipo("Silla");
        return muebleCreado;
    }

    // El primer test sirve para comprobar si el mueble se guarda en la DB
    @Test
    public void testCreateMueble() {
        Mueble muebleTesteo = buildMueble();

        servicioMueble.saveMueble(muebleTesteo);

        Mueble muebleEnDatabase = servicioMueble.getMueble(muebleTesteo.getIdMueble());

        assertEquals(muebleTesteo, muebleEnDatabase);
    }

    @Test
    public void testDeleteMueble() {

        Mueble muebleTesteo = buildMueble();

        servicioMueble.saveMueble(muebleTesteo);

        servicioMueble.deleteMueble(muebleTesteo.getIdMueble());

        assertNull(servicioMueble.getMueble(muebleTesteo.getIdMueble()));

    }

    @Test
    public void testUpdateMueble() {

        Mueble muebleTesteo = buildMueble();

        servicioMueble.saveMueble(muebleTesteo);

        Mueble otroMueble = buildMueble();

        otroMueble.setMaterial("Plata");
        otroMueble.setNombreMueble("Silla de plata");
        otroMueble.setPrecioBase(80000);
        otroMueble.setStock(80);

        servicioMueble.updateMueble(muebleTesteo.getIdMueble(), otroMueble);

        assertEquals(servicioMueble.getMueble(muebleTesteo.getIdMueble()).getNombreMueble(), "Silla de plata");
        assertEquals(servicioMueble.getMueble(muebleTesteo.getIdMueble()).getStock(), 80);
        assertEquals(servicioMueble.getMueble(muebleTesteo.getIdMueble()).getPrecioBase(), 80000);
        assertEquals(servicioMueble.getMueble(muebleTesteo.getIdMueble()).getMaterial().toLowerCase(),"plata");

    }

}
