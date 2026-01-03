package Rodriguez.Ev2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Services.VarianteService;
import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class VarianteTest {

    @Autowired
    private VarianteService servicioVariante;


    public static Variante buildVariante(){
        Variante varianteCreada = new Variante();

        varianteCreada.setDescripcion("Pues lleva ruedas");
        varianteCreada.setNombreVariante("Ruedas");
        varianteCreada.setPrecioExtra(2000);

        return varianteCreada;
    }


    //Vamos a crear tests para el CRUD
    //Create
    //Read (Es inherente a los demás)
    //Update
    //Delete

    @Test
    public void testCreateVariante(){
        Variante varianteTesteo = buildVariante();

        servicioVariante.saveVariante(varianteTesteo);

        Variante varianteEnDatabase = servicioVariante.readVariante(varianteTesteo.getIdVariante());

        assertEquals(varianteTesteo, varianteEnDatabase);

    } 

    @Test
    public void testDeleteVariante(){
        Variante varianteTesteo = buildVariante();

        servicioVariante.saveVariante(varianteTesteo);

        servicioVariante.deleteVariante(varianteTesteo.getIdVariante());

        assertNull(servicioVariante.readVariante(varianteTesteo.getIdVariante()));
    }


    @Test
    public void testUpdateVariante(){

        Variante varianteTesteo = buildVariante();

        servicioVariante.saveVariante(varianteTesteo);

        Variante otroVariante = buildVariante();

        otroVariante.setDescripcion("Barnizado con barniz premium");
        otroVariante.setNombreVariante("Barniz Premium");
        otroVariante.setPrecioExtra(5000);

        servicioVariante.updateVariante(varianteTesteo.getIdVariante(), otroVariante);
        
        Variante varianteEnDatabase = servicioVariante.readVariante(varianteTesteo.getIdVariante());

        assertEquals(varianteEnDatabase.getDescripcion(), "Barnizado con barniz premium");
        assertEquals(varianteEnDatabase.getNombreVariante(), "Barniz Premium");
        assertEquals(varianteEnDatabase.getPrecioExtra(), 5000 );

        assertEquals(varianteEnDatabase.getIdVariante(),varianteTesteo.getIdVariante());
    }


}
