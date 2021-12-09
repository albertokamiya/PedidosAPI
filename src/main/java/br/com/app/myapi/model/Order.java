package br.com.app.myapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDateTime date = LocalDateTime.now();

	private double shipping;

	private double totalPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	private Client client;

	private String name;

	private String status;

	private double totalProduct;

	 @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	 private List<OrderItem> itens = new ArrayList<OrderItem>();

	public Order() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public double getShipping() {
		return shipping;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

}
