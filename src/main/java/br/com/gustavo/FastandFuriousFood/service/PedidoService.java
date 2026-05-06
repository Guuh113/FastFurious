package br.com.gustavo.FastandFuriousFood.service;

import br.com.gustavo.FastandFuriousFood.dto.ItemPedidoDTO;
import br.com.gustavo.FastandFuriousFood.dto.PedidoDTO;
import br.com.gustavo.FastandFuriousFood.model.ItensPedido;
import br.com.gustavo.FastandFuriousFood.model.Pedido;
import br.com.gustavo.FastandFuriousFood.model.Produto;
import br.com.gustavo.FastandFuriousFood.model.StatusPedido;
import br.com.gustavo.FastandFuriousFood.repository.PedidoRepository;
import br.com.gustavo.FastandFuriousFood.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido criarNovoPedido(PedidoDTO dados) {

        if (dados.itens() == null || dados.itens().isEmpty()) {
            throw new RuntimeException("Erro: O pedido deve ter pelo menos um item!");
        }

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.ABERTO);

        List<ItensPedido> listaDeItens = new ArrayList<>();
        BigDecimal totalDoPedido = BigDecimal.ZERO;

        for (ItemPedidoDTO itemDTO : dados.itens()) {

            if (itemDTO.quantidade() <= 0) {
                throw new RuntimeException("Erro: A quantidade do item deve ser maior que zero!");
            }

            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new RuntimeException("Erro: Produto ID " + itemDTO.produtoId() + " não existe!"));

            if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Erro: O produto " + produto.getNome() + " está com preço inválido (R$ " + produto.getPreco() + "). Comunique o administrador!");
            }

            ItensPedido novoItem = new ItensPedido();
            novoItem.setProduto(produto);
            novoItem.setQuantidade(itemDTO.quantidade());
            novoItem.setPrecoUnitario(produto.getPreco());
            novoItem.setPedido(pedido);

            listaDeItens.add(novoItem);

            BigDecimal subtotal = produto.getPreco().multiply(new BigDecimal(itemDTO.quantidade()));
            totalDoPedido = totalDoPedido.add(subtotal);
        }

        pedido.setItens(listaDeItens);
        pedido.setPreco(totalDoPedido);

        return repository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return repository.findAll();
    }

    public List<Pedido> listarAbertos() {
        return repository.findByStatus(StatusPedido.ABERTO);
    }

    public Pedido alterarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido de ID " + id + " não encontrado!"));

        pedido.setStatus(novoStatus);
        return repository.save(pedido);
    }

    public BigDecimal calcularFaturamento() {
        BigDecimal total = repository.somarTotalPorStatus(StatusPedido.PRONTO);
        return total != null ? total : BigDecimal.ZERO;
    }

    public List<Pedido> buscarPorStatus(StatusPedido status) {
        return repository.findByStatus(status);
    }
}
