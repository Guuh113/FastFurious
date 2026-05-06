package br.com.gustavo.FastandFuriousFood.controller;

import br.com.gustavo.FastandFuriousFood.dto.ProdutoDTO;
import br.com.gustavo.FastandFuriousFood.model.Produto;
import br.com.gustavo.FastandFuriousFood.repository.ProdutoRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {
        return repository.findAll(); 
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado")); // Exibe produto por ID 
    }

    @PostMapping
    public Produto cadastrar(@RequestBody ProdutoDTO dados) {
        
        if (dados.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Erro: O preço do produto deve ser maior que zero!");
        }
        
        Produto p = new Produto();
        p.setNome(dados.nome());
        p.setDescricao(dados.descricao());
        p.setPreco(dados.preco());
        p.setCategoria(dados.categoria());
        return repository.save(p);  
    }

    @PutMapping("/{id}")
    public Produto alterar(@PathVariable Long id, @RequestBody ProdutoDTO dados) {
        Produto p = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        if (dados.preco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Erro: O preço não pode ser zero ou negativo!");
        }

        p.setNome(dados.nome());
        p.setDescricao(dados.descricao());
        p.setPreco(dados.preco());
        p.setCategoria(dados.categoria());
        return repository.save(p); 
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id); 
    }

    @GetMapping("/cat/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return repository.findByCategoria(categoria);  
    }
}