package Rodriguez.Ev2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Repositories.ItemRepository;
import Rodriguez.Ev2.Repositories.MuebleRepository;
import Rodriguez.Ev2.Repositories.VarianteRepository;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repositoryItem;

    @Autowired
    private VarianteRepository repositoryVariante;

    @Autowired
    private MuebleRepository repositoryMueble;


    public List<Item> getAllItems(){
        return repositoryItem.findAll();
    }

    public Item saveItem(Item itemToBeSaved){
        itemToBeSaved.setPrecioUnitario();
        return repositoryItem.save(itemToBeSaved);
    }

    public Item getItem(int id){
        return repositoryItem.findById(id).orElse(null);
    }

    public Item updateItem(int idToBeUpdated,Item reemplazo){
        Optional<Item> optionalItem = repositoryItem.findById(idToBeUpdated);
        
        if (optionalItem.isPresent()) {
            Item existingItem = optionalItem.get();

            existingItem.setCotizacion(reemplazo.getCotizacion());
            existingItem.setVariantes(reemplazo.getVariantes());
            existingItem.setCantidad(reemplazo.getCantidad());
            existingItem.setMueble(reemplazo.getMueble());

            
            existingItem.setPrecioUnitario();


            return repositoryItem.save(existingItem);
        }else{
            return null;
        }


    }


    public Item addVarianteToItem(int idItemToBeUpdated, int idVarianteToBeAdded) {
        Item item = repositoryItem.findById(idItemToBeUpdated)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        Variante variante = repositoryVariante.findById(idVarianteToBeAdded)
            .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
        
        item.addVariante(variante);
        item.setPrecioUnitario();
        return repositoryItem.save(item);
    }

    public Item removeVarianteFromItem(int idItemToBeUpdated, int idVarianteToBeRemoved){
        Item item = repositoryItem.findById(idItemToBeUpdated)
            .orElseThrow(() -> new RuntimeException("Item no encontrado"));
        
        Variante variante = repositoryVariante.findById(idVarianteToBeRemoved)
            .orElseThrow(() -> new RuntimeException("Variante no encontrada"));
        
        item.removeVariante(variante);
        item.setPrecioUnitario();
        return repositoryItem.save(item);
    }


    public Item setMuebleItemCreate(Item itemToBeUpdated, Mueble muebleToBeUpdated){
    Mueble muebleEnDatabase = repositoryMueble.findById(muebleToBeUpdated.getIdMueble())
        .orElseThrow(() -> new RuntimeException("Mueble no encontrado"));
    
    itemToBeUpdated.setMueble(muebleEnDatabase);
    muebleEnDatabase.getItems().add(itemToBeUpdated);
    
    return repositoryItem.save(itemToBeUpdated);
}

    public void deleteItem(int idItem) {
        Item item = repositoryItem.findById(idItem)
        .orElseThrow(() -> new RuntimeException("Item no encontrado"));
    
        Mueble mueble = item.getMueble();
        if (mueble != null) {
            mueble.getItems().remove(item);
        }
        
        repositoryItem.delete(item);
    }
}
