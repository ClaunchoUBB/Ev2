package Rodriguez.Ev2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "conexion_item_variable")
public class ConexionItemVariante{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConexion;


    //Conexión muchos a uno de item
    @ManyToOne
    @JoinColumn(name = "ID_item")
    private Item item;


    //Conexión muchos a 1 con variante
    @ManyToOne
    @JoinColumn(name = "ID_variante")
    private Variante variante;









    public int getIdConexion() {
        return idConexion;
    }

    public void setIdConexion(int idConexion) {
        this.idConexion = idConexion;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Variante getVariante() {
        return variante;
    }

    public void setVariante(Variante variante) {
        this.variante = variante;
    }



    

}