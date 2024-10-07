package org.example;

import jakarta.ejb.EJB;

import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.Type.*;
import org.example.entity.Hecho;


import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

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
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        new Main().start();
    }

    public void start() {
        int option;
        do {
            System.out.println("\nGestor de Hechos:");
            System.out.println("1. Agregar Hecho (EJB)");
            System.out.println("2. Agregar Hecho (JMS)");
            System.out.println("3. Listar Hechos");
            System.out.println("4. Buscar Hecho");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    agregarHechoEJB();
                    break;
                case 2:
                    agregarHechoJMS();
                    break;
                case 3:
                    listarHechos();
                    break;
                case 4:
                    buscarHecho();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        } while (option != 0);
    }

    private void agregarHechoEJB() {
        System.out.println("\n--- Agregar Hecho (EJB) ---");
        Hecho nuevoHecho = crearHechoDesdeConsola();
        hechoService.agregarHecho(nuevoHecho);
        System.out.println("Hecho agregado exitosamente.");
    }
    private static final String DEFAULT_USERNAME = "admin"; // Cambia esto por tu valor predeterminado
    private static final String DEFAULT_PASSWORD = "admin"; // Cambia esto por tu valor predeterminado
    private static final String INITIAL_CONTEXT_FACTORY = "org.wildfly.naming.client.WildFlyInitialContextFactory"; // Asegúrate de que sea correcto
    private static final String PROVIDER_URL = "http-remoting://localhost:8080"; // Cambia según tu configuración
    private static final String DEFAULT_CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
    private static final String DEFAULT_DESTINATION = "java:/queue/HechoMDB"; // Cambia según tu configuraciónjava:/queue/HechoMDB
    private static final String DEFAULT_MESSAGE_COUNT = "10"; // Número de mensajes a enviar
    private static final String DEFAULT_MESSAGE = "Mensaje de prueba"; // Contenido del mensaje
    private void agregarHechoJMS() {
        System.out.println("\n--- Agregar Hecho (JMS) ---");

        try {
            String userName = System.getProperty("username", DEFAULT_USERNAME);
            String password = System.getProperty("password", DEFAULT_PASSWORD);

            // Configurar el contexto para la búsqueda JNDI
            final Properties env = new Properties();
            env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
            env.put(Context.PROVIDER_URL, System.getProperty(Context.PROVIDER_URL, PROVIDER_URL));
            env.put(Context.SECURITY_PRINCIPAL, userName);
            env.put(Context.SECURITY_CREDENTIALS, password);
            Context namingContext = new InitialContext(env);

            // Realizar búsquedas JNDI
            String connectionFactoryString = System.getProperty("connection.factory", DEFAULT_CONNECTION_FACTORY);
            System.out.println("Intentando adquirir la fábrica de conexiones \"" + connectionFactoryString + "\"");
            ConnectionFactory connectionFactory = (ConnectionFactory) namingContext.lookup(connectionFactoryString);
            System.out.println("Fábrica de conexiones encontrada: \"" + connectionFactoryString + "\" en JNDI");
            Queue queue = (Queue) namingContext.lookup("queue/HechoMDB");

            String destinationString = System.getProperty("destination", DEFAULT_DESTINATION);
            System.out.println("Intentando adquirir la cola/destino \"" + destinationString + "\"");
            Destination destination = (Destination) namingContext.lookup(destinationString);
            System.out.println("Destino encontrado: \"" + destinationString + "\" en JNDI");

            int count = Integer.parseInt(System.getProperty("message.count", DEFAULT_MESSAGE_COUNT));
            String content = System.getProperty("message.content", DEFAULT_MESSAGE);

            // Enviar y recibir mensajes
            try (JMSContext context = connectionFactory.createContext(userName, password)) {
                System.out.println("Enviando " + count + " mensajes con contenido: " + content);
                // Enviar el número especificado de mensajes
                for (int i = 0; i < count; i++) {
                    context.createProducer().send(destination, content);
                }

                // Crear el consumidor JMS
                JMSConsumer consumer = context.createConsumer(destination);
                // Recibir el mismo número de mensajes que se enviaron
                for (int i = 0; i < count; i++) {
                    String text = consumer.receiveBody(String.class, 5000);
                    System.out.println("Mensaje recibido con contenido: " + text);
                }
            }
        } catch (NamingException e) {
            System.err.println("Error de JNDI: " + e.getMessage());
            System.out.println(e);
        } catch (NumberFormatException e) {
            System.err.println("Error de formato de número: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
        }
    }


    private Hecho crearHechoDesdeConsola() {
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

        return new Hecho(0, estado, descripcion, TipoCalificacion.values()[calificacionIndex], TipoCategoria.values()[categoriaIndex],
                LocalDateTime.now(), LocalDateTime.now(), "Sin justificación", false);
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
}
