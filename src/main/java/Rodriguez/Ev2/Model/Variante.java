package Rodriguez.Ev2.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "variante")
public class Variante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idVariante;

    @ManyToMany(mappedBy = "variantes")
    private List<Item> items = new ArrayList<>();


    @Column(name = "nombre_variante")
    private String nombreVariante;

    @Column(name= "precio_extra")
    private int precioExtra;

    @Column(name="descripcion")
    private String descripcion;






    
    public int getIdVariante() {
        return idVariante;
    }

    public void setIdVariante(int idVariante) {
        this.idVariante = idVariante;
    }

    public String getNombreVariante() {
        return nombreVariante;
    }

    public void setNombreVariante(String nombreVariante) {
        this.nombreVariante = nombreVariante;
    }

    public int getPrecioExtra() {
        return precioExtra;
    }

    public void setPrecioExtra(int precioExtra) {
        this.precioExtra = precioExtra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    
}
