package Rodriguez.Ev2.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name = "item")
public class Item {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItem;


    //Mapeo de la conexión con mueble
    @ManyToOne
    @JoinColumn(name = "ID_mueble")
    private int idMueble;


    //Mapeo de la conexión con "ConexionItemVariante"
    @OneToMany(mappedBy = "idConexion")
    private List<ConexionItemVariante> conexiones;


    //Mapeo de la conexión con Cotizacion
    @ManyToOne
    @JoinColumn(name="ID_Cotizacion")
    private List<Cotizacion> cotizaciones;















    
    @Column(name = "precio")
    private int precio;

    @Column(name ="cantidad")
    private int cantidad;

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public int getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(int idMueble) {
        this.idMueble = idMueble;
    }

    public List<ConexionItemVariante> getConexiones() {
        return conexiones;
    }

    public void setConexiones(List<ConexionItemVariante> conexiones) {
        this.conexiones = conexiones;
    }

    public List<Cotizacion> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(List<Cotizacion> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    

}
