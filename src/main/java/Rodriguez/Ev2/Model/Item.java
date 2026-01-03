package Rodriguez.Ev2.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItem;

    // Mapeo de la conexión con mueble
    @ManyToOne
    @JoinColumn(name = "ID_mueble")
    private Mueble mueble;

    // Aquí mapeamos un ManyToMany usando el @ de JPA, primero lo había hecho con
    // tabla intermedia
    @ManyToMany
    @JoinTable(name = "conexion_item_variante", joinColumns = @JoinColumn(name = "ID_item"), inverseJoinColumns = @JoinColumn(name = "ID_variante"))
    private List<Variante> variantes = new ArrayList<>();

    // Mapeo de la conexión con Cotizacion
    @ManyToOne
    @JoinColumn(name = "ID_Cotizacion")
    private Cotizacion cotizacion;

    @Column(name = "precio_unitario")
    private int precioUnitario;

    @Column(name = "cantidad")
    private int cantidad;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public int getPrecioUnitario() {
        return this.precioUnitario;
    }

    public void setPrecioUnitario() {
        int precio = 0;
        precio += mueble.getPrecioBase();
        for (Variante varianteActual : this.variantes) {
            precio += varianteActual.getPrecioExtra();
        }
        this.precioUnitario = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Variante> getVariantes() {
        return variantes;
    }

    public void setVariantes(List<Variante> variantes) {
        this.variantes = variantes;
    }

    public void addVariante(Variante varianteToBeAdded) {
        this.variantes.add(varianteToBeAdded);
        varianteToBeAdded.getItems().add(this);
    }

    public void removeVariante(Variante variante) {
        this.variantes.remove(variante);
        variante.getItems().remove(this);
    }

}
