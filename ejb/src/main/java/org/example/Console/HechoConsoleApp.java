package org.example.Console;

import jakarta.ejb.EJB;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.Type.*;
import org.example.entity.Hecho;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class HechoConsoleApp {
    @EJB
    private IHechoServiceRemote hechoService;

    public HechoConsoleApp() {
        try {
            // Configurar el contexto inicial para hacer la búsqueda del EJB
            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            props.put(Context.PROVIDER_URL, "remote+http://localhost:8080");
            Context ctx = new InitialContext(props);

            System.out.println("Iniciando el contexto JNDI...");
            String jndiName = "java:global/PracticoJavaEE2024-ejb/HechoServiceBean!org.example.Services.InterfaceService.IHechoServiceRemote";

            // Realizar la búsqueda del EJB por JNDI
            System.out.println("Buscando el EJB HechoService...");
            hechoService = (IHechoServiceRemote) ctx.lookup(jndiName);

            // Verificar si la conexión fue exitosa
            if (hechoService != null) {
                System.out.println("Conexión exitosa al servicio HechoService.");
            } else {
                System.out.println("Error: No se pudo conectar al servicio HechoService.");
            }

        } catch (NamingException e) {
            System.out.println("Error al realizar la búsqueda del EJB: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new HechoConsoleApp().start();
    }

    public void start() {
        int option;
        do {
            System.out.println("\nGestor de Hechos:");
            System.out.println("1. Agregar Hecho");
            System.out.println("2. Listar Hechos");
            System.out.println("3. Buscar Hecho");
            System.out.println("4. Modificar Hecho");
            System.out.println("5. Eliminar Hecho");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1:
                    //agregarHecho();
                    break;
                case 2:
                    listarHechos();
                    break;
                case 3:
                    buscarHecho();
                    break;
                case 4:
                    //modificarHecho();
                    break;
                case 5:
                    //eliminarHecho();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

/*    private void agregarHecho() {
        System.out.println("\n--- Agregar Hecho ---");
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

        // Suponemos que los tipos de calificación y categoría son seleccionados por números
        System.out.println("Seleccione una calificación: ");
        for (int i = 0; i < TipoCalificacion.values().length; i++) {
            System.out.println(i + 1 + ". " + TipoCalificacion.values()[i]);
        }
        int calificacionIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.println("Seleccione una categoría: ");
        for (int i = 0; i < TipoCategoria.values().length; i++) {
            System.out.println(i + 1 + ". " + TipoCategoria.values()[i]);
        }
        int categoriaIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        Hecho nuevoHecho = new Hecho(0, estado, descripcion, TipoCalificacion.values()[calificacionIndex], TipoCategoria.values()[categoriaIndex],
                LocalDateTime.now(), LocalDateTime.now(), "Sin justificación", false);

        hechoService.agregarHecho(nuevoHecho);
        System.out.println("Hecho agregado exitosamente.");
    }*/

    private void listarHechos() {
        System.out.println("\n--- Listar Hechos ---");
        List<Hecho> hechos = hechoService.listarHechos();
        for (Hecho hecho : hechos) {
            System.out.println(hecho);
        }
    }

    private void buscarHecho() {
        System.out.print("\nIngrese la descripción a buscar: ");
        String query = scanner.nextLine();
        List<Hecho> hechos = hechoService.buscarHechos(query);
        if (hechos.isEmpty()) {
            System.out.println("No se encontraron hechos.");
        } else {
            for (Hecho hecho : hechos) {
                System.out.println(hecho);
            }
        }
    }

/*    private void modificarHecho() {
        System.out.print("\nIngrese el ID del hecho a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        Hecho hecho = hechoService.obtenerHechoPorId(id);
        if (hecho == null) {
            System.out.println("Hecho no encontrado.");
            return;
        }

        System.out.print("Nueva Descripción (" + hecho.getDescripcion() + "): ");
        String descripcion = scanner.nextLine();
        hecho.setDescripcion(descripcion.isEmpty() ? hecho.getDescripcion() : descripcion);

        System.out.print("Nuevo Estado (" + hecho.getEstado() + "): ");
        String estado = scanner.nextLine();
        hecho.setEstado(estado.isEmpty() ? hecho.getEstado() : estado);

        hechoService.modificarHecho(hecho);
        System.out.println("Hecho modificado exitosamente.");
    }

    private void eliminarHecho() {
        System.out.print("\nIngrese el ID del hecho a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        hechoService.eliminarHecho(id);
        System.out.println("Hecho eliminado exitosamente.");
    }*/
}
