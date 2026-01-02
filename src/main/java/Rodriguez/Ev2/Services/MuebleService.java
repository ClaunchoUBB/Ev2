package Rodriguez.Ev2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Mueble;
import Rodriguez.Ev2.Repositories.MuebleRepository;

@Service
public class MuebleService {
    
    @Autowired
    private MuebleRepository muebleRepository;

    public List<Mueble> getAllMuebles(){
        return muebleRepository.findAll();
    }

    public Mueble saveMueble(Mueble muebleToBeSaved){
        return muebleRepository.save(muebleToBeSaved);
    }

    public Mueble getMueble(int idToBeRead){
        return muebleRepository.findById(Integer.valueOf(idToBeRead)).orElse(null);
    }

    public void deleteMueble(int idToBeDeleted){
        muebleRepository.deleteById(Integer.valueOf(idToBeDeleted));
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
}
