package br.com.gustavo.FastandFuriousFood.repository;

import br.com.gustavo.FastandFuriousFood.model.Pedido;
import br.com.gustavo.FastandFuriousFood.model.StatusPedido;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido status);

    List<Pedido> findByCategoria(String categoria);

    @Query("SELECT SUM(p.preco) FROM Pedido p WHERE p.status = :status")
    BigDecimal somarTotalPorStatus(StatusPedido status);
}
