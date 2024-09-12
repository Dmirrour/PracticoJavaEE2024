package org.example.Beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.servlet.RequestDispatcher;
import lombok.Data;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Named
@SessionScoped
public class HechoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    private List<Hecho> hechosFiltrados;
    private String query;
    private Hecho hecho;
    private int idHechoSeleccionado;

    @PostConstruct
    public void init() {
        hechosFiltrados = hechoService.listarHechos();
        hecho = new Hecho();
    }

    public String altaHecho(){
        hechoService.agregarHecho(hecho);
        init();
        return "Hechos?faces-redirect=true";
    }
    public void resetHecho() {
        hecho = new Hecho();
    }

    public void buscarHechos() {
        if (query == null || query.trim().isEmpty()) {
            hechosFiltrados = hechoService.listarHechos();
        } else {
            hechosFiltrados = hechoService.buscarHechos(query);
        }
    }

    public String selecionarHecho(int idHecho) {
        hecho = hechoService.obtenerHechoPorId(idHecho);

        if (hecho == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El hecho no se encontró"));
            return null;
        }
        return "Modificar?faces-redirect=true";
    }

    public String modificarHecho() {
        Hecho hechoModificado = hechoService.modificarHecho(hecho);

        if (hechoModificado != null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "El hecho fue modificado correctamente."));
            init();
            return "Hechos?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo modificar el hecho."));
            return null;
        }
    }
    public void borrarHecho(){
        try {
            hechoService.eliminarHecho(idHechoSeleccionado);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Success", "Se a borrado con exito."));
            init();

        }
        catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo Borrar el hecho."));
        }
    }
    public String formatearFecha(LocalDateTime fecha) {
        if (fecha != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return fecha.format(formatter);
        }
        return "";
    }
}
