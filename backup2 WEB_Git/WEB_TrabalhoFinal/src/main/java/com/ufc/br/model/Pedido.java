package com.ufc.br.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Pedido {
	
     //Gerando numero do pedido
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo_pedido;
	
	private Long codigo_pessoa;
	
    @OneToMany(cascade = CascadeType.PERSIST)
    List<Prato> pratos = new ArrayList<>();
    
    private double total;

    public Pedido(List<Prato> pratos) {
        this.pratos = pratos;
    }
    
    public List<Prato> getPratos() {
        return pratos;
    }

    public void setPratos(List<Prato> pratos) {
        this.pratos = pratos;
    }
    
    public Long getId() {
        return codigo_pedido;
    }

    public void setId(Long codigo_pedido) {
        this.codigo_pedido = codigo_pedido;
    }

	public Long getCodigo_pedido() {
		return codigo_pedido;
	}

	public void setCodigo_pedido(Long codigo_pedido) {
		this.codigo_pedido = codigo_pedido;
	}

	public Long getCodigo_pessoa() {
		return codigo_pessoa;
	}

	public void setCodigo_pessoa(Long codigo_pessoa) {
		this.codigo_pessoa = codigo_pessoa;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}	
}
