package br.com.app.myapi.model;

import java.util.Date;

public class Report {

	private Date init;
	
	private Date end;
	
	private long totalOrders;
	
	private double totalSold;
	
	private double totalFrete;
	
	private double totalProduct;

	public double getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(double totalProduct) {
		this.totalProduct = totalProduct;
	}

	public double getTotalFrete() {
		return totalFrete;
	}

	public void setTotalFrete(double totalFrete) {
		this.totalFrete = totalFrete;
	}

	public Date getInit() {
		return init;
	}

	public void setInit(Date init) {
		this.init = init;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public long getTotalOrders() {
		return totalOrders;
	}

	public void setTotalOrders(long totalOrders) {
		this.totalOrders = totalOrders;
	}

	public double getTotalSold() {
		return totalSold;
	}

	public void setTotalSold(double totalSold) {
		this.totalSold = totalSold;
	}
	
	
}
