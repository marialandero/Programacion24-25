package Extra.AgenciaDeViajes;

import java.util.ArrayList;
import java.util.List;

public class Agencia {
    private List<Cliente> listadoClientes;

    public Agencia() {
        this.listadoClientes = new ArrayList<>();
    }

    public void addCliente (Cliente cliente) throws AgenciaException {
        if(listadoClientes.contains(cliente)){
            throw new AgenciaException("Este cliente ya esta en la lista de la agencia");
        }
        listadoClientes.add(cliente);
    }

    public List<Cliente> listadoDeClientesContieneParada(String nombreParada){
        return listadoClientes.stream()
                .filter(cliente -> cliente.getRutas().values().stream()
                        .anyMatch(ruta -> ruta.contieneParada(nombreParada))).toList();
    }

    public List<String> listadoDeParadasDeUnCliente(Cliente cliente){
        return cliente.getParadasUnicasOrdenadas();
    }
}
