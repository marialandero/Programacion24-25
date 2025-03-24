package Extra.AgenciaDeViajes;

public class Main {
    public static void main(String[] args) {
        Agencia nuevaAgencia = new Agencia();

        Ruta rutaDeLaLuz = new Ruta("A-49","Sevilla");
        Ruta ruta86 = new Ruta("Ruta86","Cuyahoga Community College West");

        Cliente turistaAmericano = new Cliente("Jordan");
        Cliente turistaEspanyol = new Cliente("Javier");

        try {
            // Turista Americano
            turistaAmericano.addRuta(ruta86);
            System.out.printf("Se ha añadido la ruta %s al cliente %s \n",ruta86.getNombre(),turistaAmericano.getNombre());
            turistaAmericano.removeRuta(ruta86);
            System.out.printf("Se ha eliminado la ruta %s al cliente %s \n",ruta86.getNombre(),turistaAmericano.getNombre());
            //   turistaAmericano.removeRuta(ruta86);  Debe arrojar la excepcion

            turistaAmericano.addRuta(ruta86); // Se añade la ruta antes de añadir paradas
            turistaAmericano.addParadaARuta("Ruta86","Cliff Falls"); // Se intenta añadir la misma
            //   turistaAmericano.addParadaARuta("Ruta86","Cliff Falls"); // dos veces

            // Este metodo no estaba hecho en cliente, avisar a Bermudo
            turistaAmericano.eliminarParadaDeRuta("Ruta86","Cliff Falls"); // Se intenta eliminar la misma
            //     turistaAmericano.eliminarParadaDeRuta("Ruta86","Cliff Falls"); // dos veces

            //  turistaAmericano.addRuta(ruta86); // Se añade la ruta antes de añadir paradas
            turistaAmericano.addParadaARuta("Ruta86","Cliff Falls"); // Se intenta añadir la misma
            turistaAmericano.addParadaARuta("Ruta86","Oregon Bay");
            turistaAmericano.addParadaARuta("Ruta86","Arcadia St");
            turistaAmericano.addParadaARuta("Ruta86","Colorado Canyon");

            System.out.println(turistaAmericano.mostrarRutas()); // Se muestran las rutas de turista americano

            // Se añade a la Agencia el turistaAmericano
            nuevaAgencia.addCliente(turistaAmericano); // Se añade el cliente a la nueva agencia creada

            // Turista Español
            turistaEspanyol.addRuta(rutaDeLaLuz);
            turistaEspanyol.addParadaARuta("A-49","Camas");
            turistaEspanyol.addParadaARuta("A-49","Rociana");
            turistaEspanyol.addParadaARuta("A-49","Oregon Bay"); // Para probar el metodo listadoDeClientesContieneParada

            // Se añade a la Agencia el turistaEspanyol
            nuevaAgencia.addCliente(turistaEspanyol);

            // Se muestran todos los clientes que contienen ese nombre de parada
            nuevaAgencia.listadoDeClientesContieneParada("Oregon Bay").stream()
                    .sorted((clienteA,clienteB)->clienteA.getNombre().compareTo(clienteB.getNombre()))
                    .forEach(System.out::println);
            //.sorted().forEach(System.out::println);

   /*         // Se muestran todas las paradas de un cliente de la agencia
            nuevaAgencia.listadoDeParadasDeUnCliente(turistaAmericano).forEach(System.out::println); */
        } catch (AgenciaException e) {
            System.out.println(e.getMessage());
        }
    }
}
