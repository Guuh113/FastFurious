package br.com.gustavo.FastandFuriousFood.controller;

import br.com.gustavo.FastandFuriousFood.dto.PedidoDTO;
import br.com.gustavo.FastandFuriousFood.model.Pedido;
import br.com.gustavo.FastandFuriousFood.model.StatusPedido;
import br.com.gustavo.FastandFuriousFood.service.PedidoService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping
    public Pedido cadastrar(@RequestBody PedidoDTO dados) {
        return service.criarNovoPedido(dados); 
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return service.listarTodos(); 
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id); 
    }

    @GetMapping("/status/{status}")
    public List<Pedido> listarPorStatus(@PathVariable StatusPedido status) {
        return service.buscarPorStatus(status);  
    }

    @PatchMapping("/{id}/status")
    public Pedido mudarStatus(@PathVariable Long id, @RequestParam StatusPedido novoStatus) {
        return service.alterarStatus(id, novoStatus); 
    }

    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id) {
        service.alterarStatus(id, StatusPedido.CANCELADO);  
    }

    @GetMapping("/faturamento")
    public BigDecimal verFaturamento() {
        return service.calcularFaturamento();
    }
}