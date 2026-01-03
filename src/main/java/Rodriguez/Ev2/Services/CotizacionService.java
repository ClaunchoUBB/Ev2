package Rodriguez.Ev2.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Cotizacion;
import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Repositories.CotizacionRepository;
import Rodriguez.Ev2.Repositories.ItemRepository;

@Service
public class CotizacionService {
    
    @Autowired
    private CotizacionRepository repositorioCotizacion;

    @Autowired
    private ItemRepository repositorioItem;


    public Cotizacion createCotizacion(Cotizacion cotizacionToBeSaved){
        return repositorioCotizacion.save(cotizacionToBeSaved);
    }

    public Cotizacion getCotizacion(int idCotizacionToBeRead){
        return repositorioCotizacion.findById(idCotizacionToBeRead).orElse(null);
    }

    public void addItemToCotizacion(Cotizacion cotizacionToBeUpdated, Item itemToBeAdded){
        cotizacionToBeUpdated.getItems().add(itemToBeAdded);
        itemToBeAdded.setCotizacion(cotizacionToBeUpdated);
        cotizacionToBeUpdated.setTotal();
        repositorioCotizacion.save(cotizacionToBeUpdated);
    }

    public void removeItemFromCotizacion(Cotizacion cotizacionToBeUpdated, Item itemToBeRemoved){
        cotizacionToBeUpdated.getItems().remove(itemToBeRemoved);
        itemToBeRemoved.setCotizacion(null);
        cotizacionToBeUpdated.setTotal();
        repositorioCotizacion.save(cotizacionToBeUpdated);
    }

    public void addItemToCotizacion(int idCotizacionToBeUpdated, int idItemToBeAdded){
        Cotizacion cotizacionToBeUpdated = repositorioCotizacion.findById(idCotizacionToBeUpdated).get();
        Item itemToBeAdded = repositorioItem.findById(idItemToBeAdded).get();

        cotizacionToBeUpdated.getItems().add(itemToBeAdded);
        itemToBeAdded.setCotizacion(cotizacionToBeUpdated);
        cotizacionToBeUpdated.setTotal();
        repositorioCotizacion.save(cotizacionToBeUpdated);
    }


    public void removeItemToCotizacion(int idCotizacionToBeUpdated, int idItemToBeAdded){
        Cotizacion cotizacionToBeUpdated = repositorioCotizacion.findById(idCotizacionToBeUpdated).get();
        Item itemToBeAdded = repositorioItem.findById(idItemToBeAdded).get();

        cotizacionToBeUpdated.getItems().remove(itemToBeAdded);
        itemToBeAdded.setCotizacion(null);
        cotizacionToBeUpdated.setTotal();
        repositorioCotizacion.save(cotizacionToBeUpdated);
    }

    public void removeCotizacion(Cotizacion cotizacionToBeDeleted){
        
        List<Item> itemsToBeRemoved = cotizacionToBeDeleted.getItems();
        
        for (Item item : itemsToBeRemoved) {

            repositorioItem.delete(item);
        
        }

        repositorioCotizacion.delete(cotizacionToBeDeleted);
    
    }
}
