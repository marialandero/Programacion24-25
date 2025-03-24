package Boletin6_1.Ejercicio7;

public class Ingrediente {
    private String nombreIngrediente;
    private int cantidadIngrediente;

    public Ingrediente(String nombreIngrediente, int cantidadIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public String getNombreIngrediente() {
        return nombreIngrediente;
    }

    public void setNombreIngrediente(String nombreIngrediente) {
        this.nombreIngrediente = nombreIngrediente;
    }

    public int getCantidadIngrediente() {
        return cantidadIngrediente;
    }

    public void setCantidadIngrediente(int cantidadIngrediente) {
        this.cantidadIngrediente = cantidadIngrediente;
    }

    public Ingrediente() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
