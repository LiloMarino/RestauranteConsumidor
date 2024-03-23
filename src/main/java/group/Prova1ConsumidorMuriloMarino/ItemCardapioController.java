package group.Prova1ConsumidorMuriloMarino;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ItemCardapioController {
    @Autowired
    ItemCardapioRepository itemCardapioRepository;

    @Autowired
    RestauranteRepository restauranteRepository;

    @GetMapping("/cardapio/{id}")
    public String mostrarCardapioRestaurante(@PathVariable("id") int id, Model model) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID:" + id));
        Iterable<ItemCardapio> cardapios = itemCardapioRepository.findByRestauranteId(id);
        model.addAttribute("restaurante", restaurante);
        model.addAttribute("cardapios", cardapios.iterator().hasNext() ? cardapios : null);
        return "cardapio";
    }

}
