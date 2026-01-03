package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
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
    private ItemService servicioItem;
    @Autowired
    private CotizacionService servicioCotizacion;
    @Autowired
    private MuebleService servicioMueble;
    @Autowired
    private VentaService servicioVenta;
    @Autowired
    private VarianteService servicioVariante;

    @Test
    public void createCotizacionTest() {
        Cotizacion cotizacion = new Cotizacion();

        Cotizacion cotizacionEnDB = servicioCotizacion.saveCotizacion(cotizacion);

        assertEquals(0, cotizacionEnDB.getTotal());
        assertEquals(0, cotizacionEnDB.getItems().size());
        assertEquals(cotizacion.getIdCotizacion(), cotizacionEnDB.getIdCotizacion());

    }

    @Test
    public void addItemToCotizacionTest() {
        Mueble mueble = MuebleTest.buildMueble();
        // El valor base del mueble de buildMueble es 10000

        Mueble muebleEnDB = servicioMueble.saveMueble(mueble);

        Item item = new Item();
        item.setCantidad(3);
        Item itemEnDB = servicioItem.setMuebleItemCreate(item, muebleEnDB);

        itemEnDB.setPrecioUnitario();
        // El precio/unidad debería ser 10000
        itemEnDB = servicioItem.saveItem(itemEnDB);
        // Lo actualizamos en la db

        Cotizacion cotizacion = new Cotizacion();

        Cotizacion cotizacionEnDB = servicioCotizacion.saveCotizacion(cotizacion);

        servicioCotizacion.addItemToCotizacion(cotizacionEnDB, itemEnDB);

        cotizacionEnDB = servicioCotizacion.saveCotizacion(cotizacionEnDB);
        // actualizamos la cotización en la DB

        assertEquals(1, cotizacionEnDB.getItems().size());
        assertTrue(cotizacionEnDB.getItems().contains(itemEnDB));
        assertEquals(30000,cotizacionEnDB.getTotal());
        //El total debería ser 10000 * 3 que es la cantidad del item en la db por el precio del mueble

        itemEnDB = servicioItem.getItem(itemEnDB.getIdItem());
        assertEquals(cotizacionEnDB.getIdCotizacion(), 
                     itemEnDB.getCotizacion().getIdCotizacion()); 
    }

    


}