package com.ufc.br.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ufc.br.model.Pedido;
import com.ufc.br.model.Prato;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	void save(Prato prato);

}
