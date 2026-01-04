package Rodriguez.Ev2.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Services.CotizacionService;
import Rodriguez.Ev2.Services.ItemService;
import Rodriguez.Ev2.Services.VarianteService;

@RestController
@RequestMapping("/ev2/items")
public class ItemController {
    
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private VarianteService varianteService;

    @Autowired
    private CotizacionService cotizacionService;
    
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        return ResponseEntity.ok(items);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable int id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item nuevoItem = itemService.saveItem(item);
        Cotizacion nuevaCotizacion = cotizacionService.saveCotizacion(new Cotizacion());
        cotizacionService.addItemToCotizacion(nuevaCotizacion, item);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable int id, @RequestBody Item item) {
        Item itemActualizado = itemService.updateItem(id, item);
        if (itemActualizado != null) {
            return ResponseEntity.ok(itemActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        Item item = itemService.getItem(id);
        if (item != null) {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
    @PutMapping("/{idItem}/variantes/{idVariante}")
    public ResponseEntity<Item> addVarianteToItem(@PathVariable int idItem, @PathVariable int idVariante) {
        Item item = itemService.getItem(idItem);
        Variante variante = varianteService.readVariante(idVariante);
        
        if (item == null || variante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        if (!item.getVariantes().contains(variante)) {
            item.getVariantes().add(variante);
            Item itemActualizado = itemService.saveItem(item);
            return ResponseEntity.ok(itemActualizado);
        }
        
        return ResponseEntity.ok(item);
    }
    
    @DeleteMapping("/{idItem}/variantes/{idVariante}")
    public ResponseEntity<Item> removeVarianteFromItem(@PathVariable int idItem, @PathVariable int idVariante) {
        Item item = itemService.getItem(idItem);
        Variante variante = varianteService.readVariante(idVariante);
        
        if (item == null || variante == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        item.getVariantes().remove(variante);
        Item itemActualizado = itemService.saveItem(item);
        return ResponseEntity.ok(itemActualizado);
    }
}