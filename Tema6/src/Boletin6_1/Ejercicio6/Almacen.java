package Boletin6_1.Ejercicio6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Almacen {
    private List<Caja> listaDeCajas;
    private final static int MAX_CAJAS = 20;

    public Almacen(int numCajas) throws CajaException {
        if (numCajas <= 0 || numCajas > MAX_CAJAS) {
            throw new CajaException("El almacén debe de tener de 1 a "+MAX_CAJAS+" cajas.");
        }
        this.listaDeCajas = new ArrayList<>(numCajas);
        for (int i = 0; i < numCajas; i++) {
            listaDeCajas.add(new Caja(i+1));
        }
    }

    public void abrirCaja(int numCaja) throws CajaException {
        Caja caja = listaDeCajas.stream()
                .filter(c -> c.getNumCaja() == numCaja)
                .findFirst()
                .orElseThrow(()-> new CajaException("No se encuentra la caja número "+numCaja));
        caja.abrirCaja();
    }

    public void cerrarCaja(int numCaja) throws CajaException {
        Caja caja = listaDeCajas.stream()
                .filter(c -> c.getNumCaja() == numCaja)
                .findFirst()
                .orElseThrow(()-> new CajaException("No se encuentra la caja nº "+numCaja));
        caja.cerrarCaja();
    }

    public void nuevoCliente() {
        Cliente cliente = new Cliente();
        Caja caja = listaDeCajas.stream()
                .filter(Caja::isEstaAbierta)
                .min(Comparator.comparingInt(Caja::cantidadClientes))
                .orElseThrow(()-> new CajaException("No hay ninguna caja abierta"));
        caja.anyadirCliente(cliente);
        System.out.println("Es usted el cliente nº "+cliente.getNumCliente()+" y debe ir a la caja nº "+caja.getNumCaja());
    }

    public void atenderCliente(int numCaja) throws CajaException {
        Caja caja = listaDeCajas.stream()
                .filter(c -> c.getNumCaja() == numCaja)
                .findFirst()
                .orElseThrow(() -> new CajaException("No se encuentra la caja nº "+numCaja));
        Cliente clienteAtendido = caja.atenderCliente();
        System.out.println("Se ha atendido al cliente nº "+clienteAtendido.getNumCliente());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Almacen almacen = new Almacen(MAX_CAJAS);
        int opcion;

        do {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Abrir caja.");
            System.out.println("2. Cerrar caja.");
            System.out.println("3. Nuevo cliente.");
            System.out.println("4. Atender cliente.");
            System.out.println("5. Salir.");
            opcion = sc.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        System.out.println("Indique el número de caja que desea abrir.");
                        int numCajaAbrir = sc.nextInt();
                        almacen.abrirCaja(numCajaAbrir);
                        System.out.println("Se ha abierto la caja nº "+numCajaAbrir);
                        break;

                    case 2:
                        System.out.println("Indique el número de caja que desea cerrar.");
                        int numCajaCerrar = sc.nextInt();
                        almacen.cerrarCaja(numCajaCerrar);
                        System.out.println("Se ha cerrado la caja nº "+numCajaCerrar);
                        break;

                    case 3:
                        almacen.nuevoCliente();
                        break;

                    case 4:
                        System.out.println("Indique el número de caja en la que atender.");
                        int numCajaAtender = sc.nextInt();
                        almacen.atenderCliente(numCajaAtender);
                        break;

                    case 5:
                        System.out.println("Saliendo...");
                        System.out.println("¡Hasta pronto!");
                        break;
                }
            } catch (CajaException e) {
                System.out.println("Error: "+e.getMessage());
            }
        } while (opcion != 5);
    }
}
