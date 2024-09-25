package org.example;

import jakarta.ejb.EJB;
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

public class Main {

    @EJB
    private IHechoServiceRemote hechoService;

    public Main() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Seleccione el tipo de conexión:");
            System.out.println("1. Conectar al servidor WildFly Local");
            System.out.println("2. Conectar al servidor desplegado en Railway");
            int opcion = scanner.nextInt();

            Properties props = new Properties();
            props.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
            props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

            switch (opcion) {
                case 1:
                    System.out.println("Conectándose al servidor local...");
                    props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
                    break;
                case 2:
                    System.out.println("Conectándose al servidor desplegado en Railway...");
                    props.put(Context.PROVIDER_URL, "http-remoting://practicojavaee2024-production.up.railway.app:8080");
                    break;
                default:
                    System.out.println("Opción inválida. Conectándose al servidor local por defecto.");
                    props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
                    break;
            }

            Context ctx = new InitialContext(props);

            System.out.println("Iniciando el contexto JNDI...");
            String jndiName = "ejb:PracticoJavaEE2024/PracticoJavaEE2024-ejb/HechoServiceBean!org.example.Services.InterfaceService.IHechoServiceRemote";
            //No puede hacer andar esto afuera del modulo ejb
            //sacandolo de aca de la intalacion local nunca me encontrava el jndiName
            //Error al realizar la búsqueda del EJB: EJBCLIENT000062: Failed to look up "PracticoJavaEE2024/PracticoJavaEE2024-ejb/HechoServiceBean!org.example.Services.InterfaceService.IHechoServiceRemote"

            System.out.println("Buscando el EJB HechoService...");
            hechoService = (IHechoServiceRemote) ctx.lookup(jndiName);

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
        new Main().start();
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
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarHecho();
                    break;
                case 2:
                    listarHechos();
                    break;
                case 3:
                    buscarHecho();
                    break;
              /*  case 4:
                    //modificarHecho();
                    break;
                case 5:
                    //eliminarHecho();
                    break;*/ //Estas intefases se puede usar solo con Local por como esta configurada pero para el deploy no me sirve local
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void agregarHecho() {
        System.out.println("\n--- Agregar Hecho ---");
        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Estado: ");
        String estado = scanner.nextLine();

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
    }


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
        scanner.nextLine();
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
        scanner.nextLine();

        hechoService.eliminarHecho(id);
        System.out.println("Hecho eliminado exitosamente.");
    }*/
}