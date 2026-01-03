package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.MuebleService;
import Rodriguez.Ev2.Services.VarianteService;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class ItemTest {

    // Primero hay que testear el crud

    @Autowired
    private ItemService servicioItem;
    @Autowired
    private MuebleService servicioMueble;
    @Autowired
    private VarianteService servicioVariante;

    @Test
    public void createItemTest() {
        Mueble muebleDePrueba = MuebleTest.buildMueble();
        Mueble muebleEnDatabase = servicioMueble.saveMueble(muebleDePrueba);

        Item itemCreado = new Item();
        itemCreado.setCantidad(20);
        itemCreado.setCotizacion(null);

        Item itemGuardado = servicioItem.setMuebleItemCreate(itemCreado, muebleEnDatabase);
        itemGuardado.setPrecioUnitario();
        itemGuardado = servicioItem.saveItem(itemGuardado);

        Mueble muebleActualizado = servicioMueble.getMueble(muebleEnDatabase.getIdMueble());
        assertTrue(muebleActualizado.getItems().contains(itemGuardado));
        assertEquals(1, muebleActualizado.getItems().size());
    }

    @Test
    public void removeItemTest() {

        Mueble muebleEnDatabase = servicioMueble.saveMueble(MuebleTest.buildMueble());

        Item itemCreado = new Item();
        itemCreado.setCantidad(20);
        Item itemGuardado = servicioItem.setMuebleItemCreate(itemCreado, muebleEnDatabase);

        assertTrue(servicioMueble.getMueble(muebleEnDatabase.getIdMueble())
                .getItems().contains(itemGuardado));

        servicioItem.deleteItem(itemGuardado.getIdItem());

        Mueble muebleActualizado = servicioMueble.getMueble(muebleEnDatabase.getIdMueble());
        assertFalse(muebleActualizado.getItems().contains(itemGuardado));

    }

    @Test
    public void updateItemTest() {
        Mueble mueble1 = MuebleTest.buildMueble();
        mueble1.setPrecioBase(10000);
        Mueble mueble1Guardado = servicioMueble.saveMueble(mueble1);

        Mueble mueble2 = MuebleTest.buildMueble();
        mueble2.setPrecioBase(15000);
        Mueble mueble2Guardado = servicioMueble.saveMueble(mueble2);

        Item itemInicial = new Item();
        itemInicial.setCantidad(10);
        Item itemGuardado = servicioItem.setMuebleItemCreate(itemInicial, mueble1Guardado);
        itemGuardado.setPrecioUnitario();
        itemGuardado = servicioItem.saveItem(itemGuardado);

        int idItemGuardado = itemGuardado.getIdItem();

        Item itemActualizado = new Item();
        itemActualizado.setMueble(mueble2Guardado);
        itemActualizado.setCantidad(25);
        itemActualizado.setVariantes(new ArrayList<>());

        Item resultado = servicioItem.updateItem(idItemGuardado, itemActualizado);

        assertEquals(idItemGuardado, resultado.getIdItem());
        assertEquals(25, resultado.getCantidad());
        assertEquals(mueble2Guardado.getIdMueble(), resultado.getMueble().getIdMueble());
        assertEquals(15000.0, resultado.getPrecioUnitario());

        Mueble mueble1Actualizado = servicioMueble.getMueble(mueble1Guardado.getIdMueble());
        Mueble mueble2Actualizado = servicioMueble.getMueble(mueble2Guardado.getIdMueble());

        assertFalse(mueble1Actualizado.getItems().contains(resultado),
                "El item NO debe estar en mueble1");
        assertTrue(mueble2Actualizado.getItems().contains(resultado),
                "El item DEBE estar en mueble2");
    }

    @Test
    public void removeVarianteTest() {
        Mueble mueble = MuebleTest.buildMueble();
        Mueble muebleEnDB = servicioMueble.saveMueble(mueble);

        Item item = new Item();
        item.setCantidad(5);
        Item itemEnDB = servicioItem.setMuebleItemCreate(item, muebleEnDB);

        Variante variante = VarianteTest.buildVariante();
        variante.setPrecioExtra(20000);
        Variante varianteEnDB = servicioVariante.saveVariante(variante);

        assertEquals(0, itemEnDB.getVariantes().size());
        assertEquals(10000, itemEnDB.getPrecioUnitario());

        Item itemActualizado = servicioItem.addVarianteToItem(
                itemEnDB.getIdItem(),
                varianteEnDB.getIdVariante());

        assertEquals(1, itemActualizado.getVariantes().size());
        assertTrue(itemActualizado.getVariantes().contains(varianteEnDB));

        assertEquals(30000, itemActualizado.getPrecioUnitario());

        Variante varianteActualizada = servicioVariante.readVariante(varianteEnDB.getIdVariante());
        Item itemActualizado2 = servicioItem.removeVarianteFromItem(itemActualizado.getIdItem(),varianteActualizada.getIdVariante());
        
        assertFalse(varianteActualizada.getItems().contains(itemActualizado2));
        assertFalse(itemActualizado2.getVariantes().contains(varianteActualizada));




    }

    @Test
    public void addVarianteTest() {
        Mueble mueble = MuebleTest.buildMueble();
        Mueble muebleEnDB = servicioMueble.saveMueble(mueble);

        Item item = new Item();
        item.setCantidad(5);
        Item itemEnDB = servicioItem.setMuebleItemCreate(item, muebleEnDB);

        Variante variante = VarianteTest.buildVariante();
        variante.setPrecioExtra(20000);
        Variante varianteEnDB = servicioVariante.saveVariante(variante);

        assertEquals(0, itemEnDB.getVariantes().size());
        assertEquals(10000, itemEnDB.getPrecioUnitario());

        Item itemActualizado = servicioItem.addVarianteToItem(
                itemEnDB.getIdItem(),
                varianteEnDB.getIdVariante());

        assertEquals(1, itemActualizado.getVariantes().size());
        assertTrue(itemActualizado.getVariantes().contains(varianteEnDB));

        assertEquals(30000, itemActualizado.getPrecioUnitario());

        Variante varianteActualizada = servicioVariante.readVariante(varianteEnDB.getIdVariante());
        assertTrue(varianteActualizada.getItems().contains(itemActualizado));
    }

}
