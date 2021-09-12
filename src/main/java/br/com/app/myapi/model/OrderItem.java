package br.com.app.myapi.model;

import java.io.Serializable;

public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private long idPedido;
	
	private long idProduto;
	
	private String nomeProduto;
	
	private int quantidade;
	
	private double price;

	public OrderItem(long idPedido, long idProduto, int quantidade, double price) {
		super();
		this.idPedido = idPedido;
		this.idProduto = idProduto;
		this.quantidade = quantidade;
		this.price = price;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(long idProduto) {
		this.idProduto = idProduto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
