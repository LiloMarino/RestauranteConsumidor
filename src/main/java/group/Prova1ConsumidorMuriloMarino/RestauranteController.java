package group.Prova1ConsumidorMuriloMarino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

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
