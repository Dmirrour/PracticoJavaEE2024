package org.example;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.example.Services.InterfaceService.IHechoServiceLocal;
import org.example.entity.Hecho;

import java.io.Serializable;
import java.util.List;

@Data
@Named
@ViewScoped
public class HechoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private IHechoServiceLocal hechoService;

    private List<Hecho> hechosFiltrados;
    private String query;

    @PostConstruct
    public void init() {
        hechosFiltrados = hechoService.listarHechos();
    }

    public void buscarHechos() {
        if (query == null || query.trim().isEmpty()) {
            hechosFiltrados = hechoService.listarHechos();
        } else {
            hechosFiltrados = hechoService.buscarHechos(query);
        }
    }
}
