package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Services.CotizacionService;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.MuebleService;
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
        assertEquals(30000, cotizacionEnDB.getTotal());
        // El total debería ser 10000 * 3 que es la cantidad del item en la db por el
        // precio del mueble

        itemEnDB = servicioItem.getItem(itemEnDB.getIdItem());
        assertEquals(cotizacionEnDB.getIdCotizacion(),
                itemEnDB.getCotizacion().getIdCotizacion());
    }

    @Test
    public void addMultipleItemsToCotizacionTest() {
        Mueble muebleA = MuebleTest.buildMueble();
        muebleA.setPrecioBase(100);
        muebleA = servicioMueble.saveMueble(muebleA);
        Mueble muebleB = MuebleTest.buildMueble();
        muebleB.setPrecioBase(10);
        muebleB = servicioMueble.saveMueble(muebleB);
        Mueble muebleC = MuebleTest.buildMueble();
        muebleC.setPrecioBase(1);
        muebleC = servicioMueble.saveMueble(muebleC);

        Item itemA = new Item();
        itemA.setCantidad(3);
        Item itemB = new Item();
        itemB.setCantidad(2);
        Item itemC = new Item();
        itemC.setCantidad(1);

        servicioItem.setMuebleItemCreate(itemC, muebleC);
        itemC.setPrecioUnitario();
        itemC = servicioItem.saveItem(itemC);

        servicioItem.setMuebleItemCreate(itemB, muebleB);
        itemB.setPrecioUnitario();
        itemB = servicioItem.saveItem(itemB);

        servicioItem.setMuebleItemCreate(itemA, muebleA);
        itemA.setPrecioUnitario();
        itemA = servicioItem.saveItem(itemA);

        Cotizacion cotizacionEntidad = servicioCotizacion.saveCotizacion(new Cotizacion());
        servicioCotizacion.ItemToCotizacion(cotizacionEntidad.getIdCotizacion(), itemA.getIdItem());
        servicioCotizacion.ItemToCotizacion(cotizacionEntidad.getIdCotizacion(), itemB.getIdItem());
        servicioCotizacion.ItemToCotizacion(cotizacionEntidad.getIdCotizacion(), itemC.getIdItem());
        Cotizacion cotizacionEnDB = servicioCotizacion.saveCotizacion(cotizacionEntidad);

        assertEquals(3, cotizacionEnDB.getItems().size());
        assertEquals(321, cotizacionEnDB.getTotal());

    }

    @Test
    public void removeItemFromCotizacionTest() {
        Mueble mueble = MuebleTest.buildMueble();
        mueble.setPrecioBase(10);
        Mueble muebleEnDB = servicioMueble.saveMueble(mueble);

        Item item = new Item();
        item.setCantidad(5);
        Item itemEnDB = servicioItem.setMuebleItemCreate(item, muebleEnDB);
        itemEnDB.setPrecioUnitario();
        servicioItem.saveItem(itemEnDB);

        Cotizacion cotizacion = servicioCotizacion.saveCotizacion(new Cotizacion());
        servicioCotizacion.addItemToCotizacion(cotizacion,itemEnDB);

        Cotizacion cotizacionConItem = servicioCotizacion.getCotizacion(
            cotizacion.getIdCotizacion()
        );
        assertEquals(1, cotizacionConItem.getItems().size());
        assertEquals(50, cotizacionConItem.getTotal()); 

        servicioCotizacion.removeItemFromCotizacion(cotizacionConItem, itemEnDB);
        
        Cotizacion cotizacionSinItem = servicioCotizacion.getCotizacion(cotizacion.getIdCotizacion());

        assertEquals(0, cotizacionSinItem.getItems().size());

        assertEquals(0, cotizacionSinItem.getTotal());

        Item itemSinCotizacion = servicioItem.getItem(itemEnDB.getIdItem());
        assertNull(itemSinCotizacion.getCotizacion());

    }


    @Test
    public void removeCotizacionTest() {
        Mueble mueble = servicioMueble.saveMueble(MuebleTest.buildMueble());
        mueble.setPrecioBase(5000);
        
        Item item1 = new Item();
        item1.setCantidad(1);
        Item item1Guardado = servicioItem.setMuebleItemCreate(item1, mueble);
        item1Guardado.setPrecioUnitario();
        item1Guardado = servicioItem.saveItem(item1Guardado);
        
        Item item2 = new Item();
        item2.setCantidad(2);
        Item item2Guardado = servicioItem.setMuebleItemCreate(item2, mueble);
        item2Guardado.setPrecioUnitario();
        item2Guardado = servicioItem.saveItem(item2Guardado);
        
        Cotizacion cotizacion = servicioCotizacion.saveCotizacion(new Cotizacion());
        servicioCotizacion.addItemToCotizacion(
            cotizacion, 
            item1Guardado
        );
        servicioCotizacion.addItemToCotizacion(
            cotizacion, 
            item2Guardado
        );
        
        int idCotizacion = cotizacion.getIdCotizacion();
        int idItem1 = item1Guardado.getIdItem();
        int idItem2 = item2Guardado.getIdItem();
        
        assertNotNull(servicioCotizacion.getCotizacion(idCotizacion));
        assertNotNull(servicioItem.getItem(idItem1));
        assertNotNull(servicioItem.getItem(idItem2));
        
        Cotizacion cotizacionAEliminar = servicioCotizacion.getCotizacion(idCotizacion);
        servicioCotizacion.removeCotizacion(cotizacionAEliminar);
        
        assertNull(servicioCotizacion.getCotizacion(idCotizacion));
        assertNull(servicioItem.getItem(idItem1));
        assertNull(servicioItem.getItem(idItem2));
    }

    @Test
    public void recalcularTotalTest() {
        Mueble mueble = MuebleTest.buildMueble();
        mueble.setPrecioBase(1000);
        Mueble muebleGuardado = servicioMueble.saveMueble(mueble);
        
        Item item = new Item();
        item.setCantidad(2);
        Item itemGuardado = servicioItem.setMuebleItemCreate(item, muebleGuardado);
        itemGuardado.setPrecioUnitario();  // 1000
        itemGuardado = servicioItem.saveItem(itemGuardado);
        
        Cotizacion cotizacion = servicioCotizacion.saveCotizacion(new Cotizacion());
        servicioCotizacion.addItemToCotizacion(
            cotizacion, 
            itemGuardado
        );
        
        Cotizacion cot1 = servicioCotizacion.getCotizacion(cotizacion.getIdCotizacion());
        assertEquals(2000, cot1.getTotal());  // 1000 * 2
        
        servicioCotizacion.addItemToCotizacion(
            cotizacion, 
            itemGuardado
        );
        
        Cotizacion cot2 = servicioCotizacion.getCotizacion(cotizacion.getIdCotizacion());
        assertEquals(4000, cot2.getTotal());  // 1000 * 2 * 2 items
    }
}