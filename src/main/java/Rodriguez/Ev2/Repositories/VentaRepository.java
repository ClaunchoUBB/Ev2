package Rodriguez.Ev2.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Rodriguez.Ev2.Model.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta,Integer> {
    
}
