package Boletin6_1.Ejercicio6;

public class Cliente {
    private int numCliente;
    private static int contadorCliente = 0;

    public Cliente() {
        this.numCliente = ++contadorCliente;
    }

    public int getNumCliente() {
        return numCliente;
    }
}
