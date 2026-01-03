package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Repositories.ItemRepository;
import Rodriguez.Ev2.Repositories.MuebleRepository;
import Rodriguez.Ev2.Repositories.VarianteRepository;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.MuebleService;
import Rodriguez.Ev2.Services.VarianteService;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class ItemTest {
    
    //Primero hay que testear el crud

    @Autowired 
    private ItemService servicioItem;
    @Autowired 
    private MuebleService servicioMueble;
    @Autowired 
    private VarianteService servicioVariante;

    
    @Test
    public void createItemTest(){
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
    public void removeItemTest(){
     
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
    public void updateItemTest(){

    }

    @Test
    public void removeVarianteTest(){

    }

    @Test
    public void addVarianteTest(){

    }



}
