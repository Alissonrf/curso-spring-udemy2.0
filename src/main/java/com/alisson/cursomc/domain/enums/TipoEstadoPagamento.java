package com.alisson.cursomc.domain.enums;

public enum TipoEstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;

	private TipoEstadoPagamento() {
		// TODO Auto-generated constructor stub
	}

	private TipoEstadoPagamento(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}

	public static TipoEstadoPagamento toEnum(Integer cod) {
		
		if(cod == null) {
			return null;
		}
		
		for(TipoEstadoPagamento x: TipoEstadoPagamento.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id invalido" + cod);
	}
	
}
