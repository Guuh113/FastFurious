
package br.com.gustavo.FastandFuriousFood.controller;

import br.com.gustavo.FastandFuriousFood.model.Pedido;
import br.com.gustavo.FastandFuriousFood.model.StatusPedido;
import br.com.gustavo.FastandFuriousFood.repository.PedidoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fastforious/pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository repository;
@PostMapping
    public Pedido realizarPedido(@RequestBody Pedido pedido){
        pedido.setStatus(StatusPedido.ABERTO);
        return repository.save(pedido);
    }
    @GetMapping
    public List<Pedido> listarTodos(){
    return repository.findAll();
    }
    @GetMapping("/status/{status}")
    public List<Pedido> listarPorStatus(@PathVariable StatusPedido status){
        return repository.findByStatus(status);
    }
     @PatchMapping("/{id}/status")
     public Pedido atualizarStatus(@PathVariable Long id,@RequestParam StatusPedido novoStatus){
         Pedido pedido = repository.findById(id).orElseThrow();
         pedido.setStatus(novoStatus);
         return repository.save(pedido);
     }
}
