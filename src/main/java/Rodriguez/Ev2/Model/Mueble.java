package Rodriguez.Ev2.Model;

import java.util.List;

import Rodriguez.Ev2.Model.Enum.tamano;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table( name= "mueble")
public class Mueble{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMueble;

    @OneToMany(mappedBy = "idItem")
    private List<Item> items;

    @Column(name = "nombre_mueble")
    private String nombreMueble;

    @Column(name= "tipo")
    private String tipo;

    @Column(name="precio_base")
    private int precioBase;

    @Column(name="stock")
    private int stock;

    @Column(name="estado")
    private boolean estado;

    @Column(name="tamano")
    private tamano tamano;

    @Column(name="material")
    private String material;

    public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(int precioBase) {
        this.precioBase = precioBase;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public tamano getTamano() {
        return tamano;
    }

    public void setTamano(tamano tamano) {
        this.tamano = tamano;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    
}

