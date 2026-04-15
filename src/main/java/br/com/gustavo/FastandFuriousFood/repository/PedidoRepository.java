
package br.com.gustavo.FastandFuriousFood.repository;

import br.com.gustavo.FastandFuriousFood.model.Pedido;
import br.com.gustavo.FastandFuriousFood.model.StatusPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long>{
    List<Pedido> findbyStatus(StatusPedido status);
    
    List<Pedido> findbyCategoria(String categoria);
            
}
