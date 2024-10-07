package org.example.MDB;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.ejb.ActivationConfigProperty;
import jakarta.inject.Inject;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import jakarta.jms.JMSException;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.Services.InterfaceService.IHechoServiceRemote;
import org.example.Type.TipoCalificacion;
import org.example.Type.TipoCategoria;
import org.example.entity.Hecho;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import static org.hibernate.validator.internal.engine.messageinterpolation.el.RootResolver.FORMATTER;

@MessageDriven(name = "HechoMDB", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/queue/HechoMDB"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
})
public class HechoMDB implements MessageListener {

    private static final Logger LOGGER = Logger.getLogger(HechoMDB.class.getName());

    @EJB
    private IHechoServiceRemote hechoService;
    @Override
    public void onMessage(Message rcvMessage) {
        TextMessage msg = null;
        try {
            if (rcvMessage instanceof TextMessage) {
                msg = (TextMessage) rcvMessage;
                LOGGER.info("Received Message: " + msg.getText());

                String[] attributes = msg.getText().split("\\|");
                Hecho hecho = new Hecho();
                String FORMATTER = "yyyy-MM-dd'T'HH:mm";

                hecho.setDescripcion(attributes[0]);
                hecho.setEstado(attributes[1]);
                hecho.setFechaCreacion(LocalDateTime.parse(attributes[2], DateTimeFormatter.ofPattern(FORMATTER)));
                hecho.setFechaVerificacion(LocalDateTime.parse(attributes[3], DateTimeFormatter.ofPattern(FORMATTER)));
                hecho.setJustificacion(attributes[4]);
                hecho.setCalificacion(TipoCalificacion.valueOf(attributes[5]));
                hecho.setPublicado(Boolean.parseBoolean(attributes[6]));
                hecho.setAreaTematica(TipoCategoria.valueOf(attributes[7]));

                System.out.println("Armando el Hecho : \n"+hecho);
                hechoService.agregarHecho(hecho);
            } else {
                LOGGER.warning("Message of wrong type: " + rcvMessage.getClass().getName());
            }
        } catch (JMSException e) {
            throw new RuntimeException(e);
        }
    }
}
