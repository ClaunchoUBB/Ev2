package Rodriguez.Ev2.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Rodriguez.Ev2.Model.Variante;
import Rodriguez.Ev2.Repositories.VarianteRepository;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public List<Variante> getAllVariantes(){
        return varianteRepository.findAll();

    }

    public Variante saveVariante(Variante varianteToBeSaved){
        return varianteRepository.save(varianteToBeSaved);
    }

    public Variante readVariante(int id){
        return varianteRepository.findById(id).orElse(null);
    }

    public void deleteVariante(int id){
        varianteRepository.deleteById(Integer.valueOf(id));
    }

    public Variante updateVariante(int idToBeUpdated, Variante reemplazo){
        Optional<Variante> optionalVariante = varianteRepository.findById(idToBeUpdated);
        
        if (optionalVariante.isPresent()) {
            Variante existingVariante = optionalVariante.get();

            existingVariante.setDescripcion(reemplazo.getDescripcion());
            existingVariante.setNombreVariante(reemplazo.getNombreVariante());
            existingVariante.setPrecioExtra(reemplazo.getPrecioExtra());

            return varianteRepository.save(existingVariante);
        }else{
            return null;
        }


    }
    
}
