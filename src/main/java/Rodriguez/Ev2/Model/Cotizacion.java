package Rodriguez.Ev2.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cotizacion")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCotizacion;

    @Column(name = "total")
    private int total;

    // Mappeo de la conexión con venta
    @OneToMany(mappedBy = "cotizacion", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Venta> ventas = new ArrayList<>();

    @OneToMany(mappedBy = "cotizacion",cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Item> items = new ArrayList<>();

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int idCotizacion) {
        this.idCotizacion = idCotizacion;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal() {
        this.total=0;
        if (!items.isEmpty()) {
            for (Item item : items) {
                this.total += item.getPrecioUnitario() * item.getCantidad();
            }
        }
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }


}
