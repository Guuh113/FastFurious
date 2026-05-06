package br.com.gustavo.FastandFuriousFood.repository;

import br.com.gustavo.FastandFuriousFood.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

   
    List<Produto> findByCategoria(String categoria);

}