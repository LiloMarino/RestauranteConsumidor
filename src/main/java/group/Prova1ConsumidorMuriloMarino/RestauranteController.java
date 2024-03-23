package group.Prova1ConsumidorMuriloMarino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestauranteController {
    @Autowired
    RestauranteRepository restauranteRepository;

    @GetMapping(value = { "/index", "/" })
    public String mostrarRestaurantes(Model model) {
        Iterable<Restaurante> restaurantes = restauranteRepository.findAll();
        model.addAttribute("restaurantes", restaurantes.iterator().hasNext() ? restaurantes : null);
        return "restaurantes";
    }

}
