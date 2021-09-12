package br.com.app.myapi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;
	
	private Date date;
	
	private double shipping;
	
	private double totalPrice;
	
	private long idClient;
	
	private String name;
	
	private String status;
	
	private double totalProduct;
	
	private List<OrderItem> itens;

	public Order(long id, Date date, double shipping, double totalPrice, long idClient, String status) {
		super();
		this.id = id;
		this.date = date;
		this.shipping = shipping;
		this.totalPrice = totalPrice;
		this.idClient = idClient;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(double totalProduct) {
		this.totalProduct = totalProduct;
	}

	public List<OrderItem> getItens() {
		return itens;
	}

	public void setItens(List<OrderItem> itens) {
		this.itens = itens;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getShipping() {
		return shipping;
	}

	public void setShipping(double shipping) {
		this.shipping = shipping;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public long getIdClient() {
		return idClient;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	
	
}
