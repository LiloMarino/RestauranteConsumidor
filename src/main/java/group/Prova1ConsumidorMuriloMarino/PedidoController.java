package group.Prova1ConsumidorMuriloMarino;

import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.util.CollectionUtils;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PedidoController {

    @Autowired
    ItemCardapioRepository itemCardapioRepository;
    private static final String SESSION_PEDIDOS = "sessionPedidos";

    @GetMapping("/pedidos")
    public String mostrarPedidos(Model model, HttpServletRequest request) {
        double total = 0;
        Pedidos pedidos = (Pedidos) request.getSession().getAttribute(SESSION_PEDIDOS);
        if (!CollectionUtils.isEmpty(pedidos)) {
            Pedidos pedidosAtualizados = new Pedidos();
            for (Pedido pedido : pedidos) {
                int id = pedido.getCardapio().getId();
                ItemCardapio cardapio = itemCardapioRepository.findById(id).orElse(null);
                if (cardapio != null) {
                    Pedido pedidoAtualizado = new Pedido(cardapio);
                    pedidoAtualizado.setQuantidade(pedido.getQuantidade());
                    total += pedidoAtualizado.getSubTotal();
                    pedidosAtualizados.add(pedidoAtualizado);
                }
            }
            pedidos = pedidosAtualizados;
            if (CollectionUtils.isEmpty(pedidos)) {
                pedidos = null;
            }
        } else {
            pedidos = null;
        }
        DecimalFormat df = new DecimalFormat("#0.00");
        request.getSession().setAttribute(SESSION_PEDIDOS, pedidos);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("total", df.format(total));
        return "pedidos";
    }

    @GetMapping("/adicionar/pedido/{id}")
    public String adicionarPedido(@PathVariable("id") int id, HttpServletRequest request) {
        ItemCardapio cardapio = (ItemCardapio) itemCardapioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID:" + id));

        Pedidos pedidos = (Pedidos) request.getSession().getAttribute(SESSION_PEDIDOS);
        pedidos = CollectionUtils.isEmpty(pedidos) ? new Pedidos() : pedidos;

        if (!pedidos.contains(cardapio)) {
            Pedido pedido = new Pedido(cardapio);
            pedidos.add(pedido);
        } else {
            for (Pedido pedido : pedidos) {
                if (pedido.getCardapio().equals(cardapio)) {
                    pedido.setQuantidade(pedido.getQuantidade() + 1);
                }
            }
        }

        request.getSession().setAttribute(SESSION_PEDIDOS, pedidos);

        return "redirect:/pedidos";

    }

    @GetMapping("/remover/pedido/{id}")
    public String removerPedido(@PathVariable("id") int id, HttpServletRequest request) {
        ItemCardapio cardapio = (ItemCardapio) itemCardapioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID:" + id));

        Pedidos pedidos = (Pedidos) request.getSession().getAttribute(SESSION_PEDIDOS);

        for (Pedido pedido : pedidos) {
            if (pedido.getCardapio().equals(cardapio)) {
                pedido.setQuantidade(pedido.getQuantidade() - 1);
                if (pedido.getQuantidade() <= 0) {
                    return "redirect:/deletar/pedido/" + id;
                }
            }
        }

        request.getSession().setAttribute(SESSION_PEDIDOS, pedidos);

        return "redirect:/pedidos";
    }

    @GetMapping("/deletar/pedido/{id}")
    public String deletarPedido(@PathVariable("id") int id, HttpServletRequest request) {
        Pedidos pedidos = (Pedidos) request.getSession().getAttribute(SESSION_PEDIDOS);

        ItemCardapio cardapio = itemCardapioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "ID:" + id));

        pedidos.remove(cardapio);

        request.getSession().setAttribute(SESSION_PEDIDOS, pedidos);

        return "redirect:/pedidos";
    }

}
