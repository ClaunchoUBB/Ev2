package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Services.CotizacionService;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.MuebleService;
import Rodriguez.Ev2.Services.VarianteService;
import Rodriguez.Ev2.Services.VentaService;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class CotizacionTest {
    
    @Autowired
    private ItemService serivicioItem;
    @Autowired
    private CotizacionService servicioCotizacion;
    @Autowired
    private MuebleService servicioMueble;
    @Autowired
    private VentaService seriviocVenta;
    @Autowired
    private VarianteService servicioVariante;


    @Test
    public void createCotizacionTest(){
        Cotizacion cotizacion = new Cotizacion();

        Cotizacion cotizacionEnDB = servicioCotizacion.saveCotizacion(cotizacion);

        assertEquals(0, cotizacionEnDB.getTotal());
        assertEquals(0, cotizacionEnDB.getItems().size());
        assertEquals(cotizacion.getIdCotizacion(), cotizacionEnDB.getIdCotizacion());
        
    }

}
