import java.util.*;

public class Juego {

    private List<Personaje> personajes = new ArrayList<>();

    public void agregarPersonaje(Personaje personaje) throws DBException {
        for (Personaje p : personajes) {
            if (p.equals(personaje)) {
                throw new DBException("Personaje duplicado");
            }
        }
        personajes.add(personaje);
    }

    public Personaje buscarPersonaje(String nombre, TRaza raza) throws DBException {
        for (Personaje p : personajes) {
            if (p.getNombre().equals(nombre) && p.getRaza() == raza) {
                return p;
            }
        }
        throw new DBException("Personaje no encontrado");
    }

    public void personajeConMasAtaques() {
        int max = 0;
        for (Personaje p : personajes) {
            if (p.getAtaques().size() > max) {
                max = p.getAtaques().size();
            }
        }

        for (Personaje p : personajes) {
            if (p.getAtaques().size() == max) {
                System.out.println(p.getNombre() + " tiene " + max + " ataques");
            }
        }
    }

    public void todosLosAtaquesOrdenadosNombre() {
        List<Ataque> ataques = new ArrayList<>();
        for (Personaje p : personajes) {
            ataques.addAll(p.getAtaques());
        }

        ataques.sort(Comparator.comparing(Ataque::getNombre));

        for (Ataque a : ataques) {
            System.out.println(a);
        }
    }

    public void todosLosAtaquesOrdenadosDamage() {
        List<Ataque> ataques = new ArrayList<>();
        for (Personaje p : personajes) {
            ataques.addAll(p.getAtaques());
        }

        ataques.sort((a1, a2) -> Integer.compare(a2.getDamage(), a1.getDamage()));

        for (Ataque a : ataques) {
            System.out.println(a);
        }
    }

    public Ataque ataqueMasDañino(Personaje p1, Personaje p2) {
        Ataque mejor = null;
        for (Ataque a : p1.getAtaques()) {
            if (mejor == null || a.getDamage() > mejor.getDamage()) {
                if (a.getKiNecesario() <= p1.getKi()) {
                    mejor = a;
                }
            }
        }
        return mejor;
    }

    public void atacar(Personaje p1, Personaje p2, String nombreAtaque) {
        if (p1.estaMuerto() || p2.estaMuerto()) {
            System.out.println("No se puede atacar, alguien está muerto");
            return;
        }

        Ataque elegido = null;

        for (Ataque a : p1.getAtaques()) {
            if (a.getNombre().equals(nombreAtaque)) {
                if (elegido == null || a.getDamage() > elegido.getDamage()) {
                    elegido = a;
                }
            }
        }

        if (elegido == null) {
            System.out.println("No tiene ese ataque");
            return;
        }

        if (p1.getKi() < elegido.getKiNecesario()) {
            System.out.println("No tiene suficiente ki");
            return;
        }

        p1.setKi(p1.getKi() - elegido.getKiNecesario());
        p2.setVida(p2.getVida() - elegido.getDamage());
        p1.getAtaques().remove(elegido);

        System.out.println(p1.getNombre() + " atacó a " + p2.getNombre());
        System.out.println("Vida de " + p2.getNombre() + ": " + p2.getVida());

        if (p2.getVida() <= 0) {
            System.out.println(p2.getNombre() + " ha muerto");
        }
    }

    public void eliminarAtaquesInferioresANivel(int nivel) {
        for (Personaje p : personajes) {
            List<Ataque> nuevos = new ArrayList<>();
            for (Ataque a : p.getAtaques()) {
                if (a.getNivel() >= nivel) {
                    nuevos.add(a);
                }
            }
            p.setAtaques(nuevos);
        }
    }

    public Map<TRaza, List<Personaje>> devuelveMapaRazas() {
        Map<TRaza, List<Personaje>> mapa = new HashMap<>();

        for (Personaje p : personajes) {
            if (!mapa.containsKey(p.getRaza())) {
                mapa.put(p.getRaza(), new ArrayList<>());
            }
            mapa.get(p.getRaza()).add(p);
        }

        return mapa;
    }

    public void personajeConAtaqueMasPoderoso() {
        Personaje mejor = null;
        Ataque maxAtaque = null;

        for (Personaje p : personajes) {
            for (Ataque a : p.getAtaques()) {
                if (maxAtaque == null || a.getDamage() > maxAtaque.getDamage()) {
                    maxAtaque = a;
                    mejor = p;
                }
            }
        }

        if (mejor != null) {
            System.out.println("El personaje con el ataque más poderoso es " + mejor.getNombre() + " con " + maxAtaque.getNombre());
        }
    }
}
