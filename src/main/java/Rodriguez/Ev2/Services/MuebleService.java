package Rodriguez.Ev2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Item;
import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Repositories.ItemRepository;
import Rodriguez.Ev2.Repositories.MuebleRepository;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepository;
    @Autowired
    private ItemRepository itemRepository;

    public List<Mueble> getAllMuebles() {
        return muebleRepository.findAll();
    }

    public Mueble saveMueble(Mueble muebleToBeSaved) {
        return muebleRepository.save(muebleToBeSaved);
    }

    public Mueble getMueble(int idToBeRead) {
        return muebleRepository.findById(idToBeRead).orElse(null);
    }

    public void deleteMueble(int idToBeDeleted) {
        muebleRepository.deleteById(idToBeDeleted);
    }

    public Mueble deactivateMueble(int idToBeDeactivated) {
        Optional<Mueble> optionalMueble = muebleRepository.findById(idToBeDeactivated);
        if (optionalMueble.isPresent()) {
            Mueble existingMueble = optionalMueble.get();
            existingMueble.setEstado(false);
            return muebleRepository.save(existingMueble);
        }
        return null;
    }

    public Mueble activateMueble(int idToBeActivated) {
        Optional<Mueble> optionalMueble = muebleRepository.findById(idToBeActivated);
        if (optionalMueble.isPresent()) {
            Mueble existingMueble = optionalMueble.get();
            existingMueble.setEstado(true);
            return muebleRepository.save(existingMueble);
        }
        return null;
    }

    public Mueble updateMueble(int idToBeUpdated, Mueble reemplazo) {
        Optional<Mueble> optionalMueble = muebleRepository.findById(idToBeUpdated);
        if (optionalMueble.isPresent()) {
            Mueble existingMueble = optionalMueble.get();

            existingMueble.setNombreMueble(reemplazo.getNombreMueble());
            existingMueble.setTipo(reemplazo.getTipo());
            existingMueble.setPrecioBase(reemplazo.getPrecioBase());
            existingMueble.setMaterial(reemplazo.getMaterial());
            existingMueble.setStock(reemplazo.getStock());
            existingMueble.setEstado(reemplazo.isActivo());

            return muebleRepository.save(existingMueble);
        } else {
            return null;
        }
    }

    public Mueble removeItemFromMueble(Mueble muebleToBeUpdated, Item itemToBeRemoved) {

        Mueble muebleEnDatabase = muebleRepository.findById(muebleToBeUpdated.getIdMueble()).orElse(null);
        Item itemEnDatabase = itemRepository.findById(itemToBeRemoved.getIdItem()).orElse(null);

        itemEnDatabase.setMueble(null);
        muebleEnDatabase.getItems().remove(itemEnDatabase);

        itemRepository.save(itemEnDatabase);
        return muebleRepository.save(muebleEnDatabase);

    }
}
