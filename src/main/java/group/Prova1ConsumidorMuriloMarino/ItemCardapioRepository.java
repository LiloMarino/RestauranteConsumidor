package group.Prova1ConsumidorMuriloMarino;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCardapioRepository extends JpaRepository<ItemCardapio, Integer> {
    List<ItemCardapio> findByRestauranteId(int idRestaurante);
}
