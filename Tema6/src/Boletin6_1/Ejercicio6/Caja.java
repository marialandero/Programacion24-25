package Boletin6_1.Ejercicio6;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
    private int numCaja;
    private boolean estaAbierta;
    private Queue<Cliente> cola;

    public Caja(int numCaja) {
        this.numCaja = numCaja;
        this.estaAbierta = false;
        this.cola = new LinkedList<>();
    }

    public int getNumCaja() {
        return numCaja;
    }

    public boolean isEstaAbierta() {
        return estaAbierta;
    }

    public void setEstaAbierta(boolean estaAbierta) {
        this.estaAbierta = estaAbierta;
    }

    public void abrirCaja() throws CajaException {
        if(!estaAbierta) {
            estaAbierta = true;
        } else {
            throw new CajaException("La caja ya está abierta.");
        }
    }

    public void cerrarCaja() throws CajaException {
        if(estaAbierta) {
            if (!cola.isEmpty()) {
                throw new CajaException("No se puede cerrar esta caja porque hay clientes.");
            }
            estaAbierta = false;
        } else {
            throw new CajaException("La caja ya está cerrada.");
        }
    }

    public void anyadirCliente(Cliente cliente) throws CajaException {
        if (!estaAbierta) {
            throw new CajaException("No se puede añadir un cliente a una caja cerrada.");
        }
        if (!cola.contains(cliente)) {
            cola.add(cliente);
        } else {
            throw new CajaException("El cliente ya está en la cola.");
        }
    }

    public Cliente atenderCliente() throws CajaException {
        if (estaAbierta) {
            Cliente cliente = cola.poll();
            if (cliente == null) {
                throw new CajaException("No hay clientes en la cola.");
            }
            return cliente;
        }
        throw new CajaException("La caja está cerrada, no puede atender clientes.");
    }

    public int cantidadClientes() {
        return cola.size();
    }
}
