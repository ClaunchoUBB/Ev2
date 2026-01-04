package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Venta;
import Rodriguez.Ev2.Services.CotizacionService;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.MuebleService;
import Rodriguez.Ev2.Services.VentaService;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class VentaTest {

    @Autowired
    private VentaService servicioVenta;

    @Autowired
    private CotizacionService servicioCotizacion;

    @Autowired
    private MuebleService servicioMueble;

    @Autowired
    private ItemService servicioItem;

    @Test
    public void crearVentaConStockSuficienteTest() {
        Mueble mueble = MuebleTest.buildMueble();
        mueble.setPrecioBase(10000);
        mueble.setStock(50);
        Mueble muebleGuardado = servicioMueble.saveMueble(mueble);
        
        Item item = new Item();
        item.setCantidad(10);
        Item itemGuardado = servicioItem.setMuebleItemCreate(item, muebleGuardado);
        itemGuardado.setPrecioUnitario();
        itemGuardado = servicioItem.saveItem(itemGuardado);
        
        Cotizacion cotizacion = servicioCotizacion.saveCotizacion(new Cotizacion());
        servicioCotizacion.addItemToCotizacion(
            cotizacion, 
            itemGuardado
        );
        
        Venta venta = servicioVenta.crearVenta(cotizacion.getIdCotizacion());
        
        assertNotNull(venta, "La venta debe crearse exitosamente");
        assertNotNull(venta.getIdVenta());
        assertNotNull(venta.getFecha());
        assertEquals(cotizacion.getIdCotizacion(), venta.getCotizacion().getIdCotizacion());
        
        Mueble muebleActualizado = servicioMueble.getMueble(muebleGuardado.getIdMueble());
        assertEquals(40, muebleActualizado.getStock());
    }



}
