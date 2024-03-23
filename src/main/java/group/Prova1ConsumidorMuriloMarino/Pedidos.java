package group.Prova1ConsumidorMuriloMarino;

import java.util.ArrayList;

public class Pedidos extends ArrayList<Pedido> {
    @Override
    public boolean contains(Object o) {
        if (o instanceof Pedido) {
            ItemCardapio cardapio = (ItemCardapio) o;
            for (Pedido item : this) {
                if (item.getCardapio().equals(cardapio)) {
                    return true; 
                }
            }
        }
        return false;
    }

    public boolean remove(ItemCardapio cardapio) {
        for (int i = 0; i < this.size(); i++) {
            Pedido item = this.get(i);
            if (item.getCardapio().equals(cardapio)) {
                this.remove(i);
                return true; 
            }
        }
        return false;
    }
}
