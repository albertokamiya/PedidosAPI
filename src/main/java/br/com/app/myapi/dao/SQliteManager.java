package br.com.app.myapi.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import br.com.app.myapi.model.Client;
import br.com.app.myapi.model.Order;
import br.com.app.myapi.model.OrderItem;
import br.com.app.myapi.model.Product;
import br.com.app.myapi.model.Report;

public class SQliteManager {
	
	private static final Logger log = LogManager.getLogger(SQliteManager.class);

	private Connection conn;
	
	public SQliteManager() {
		log.info("Crindo instancia");

		File file = new File("C:\\myapi\\myapiDB.db");
		
		try {
			if (file.exists() & !file.isDirectory()) {
				log.info("BD localizando no diretorio raiz");
				conn = DriverManager.getConnection("jdbc:sqlite:C:\\myapi\\myapiDB.db");				
			} else {
				log.info("BD do projeto");
				conn = DriverManager.getConnection("jdbc:sqlite:myapiDB.db");
			}
		} catch (SQLException e) {
			log.error("Erro ao criar instancia", e);
		}
	}
	
	public void connectionClose() {
		log.info("Fechando conexao");
		try {
			conn.close();
		} catch (SQLException e) {
			log.error("Erro ao fechar a conexao", e);
		}
	}
	
	public List<Client> getAllClient() {
		log.info("Buscando todos os clientes");
		List<Client> clients = new ArrayList<Client>();
		Client client;
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM cliente ORDER BY nome");
			long id;
			String nome;
			String endereco;
			String tel;
			while(result.next()) {
				id = result.getLong("id");
				nome = result.getString("nome");
				endereco = result.getString("endereco");
				tel = result.getString("telefone");
				client = new Client(id, nome, endereco, tel);
				clients.add(client);
			}
			log.info("busca de clientes realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar clientes", e);
		}
		
		return clients;
	}
	
	public Client getClient(long id) {
		log.info("Buscando cliente");
		Client client = new Client(0, "", "", "");
		PreparedStatement stm;
		try {
			String sql = "SELECT * FROM cliente WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setLong(1, id);
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				String nome = result.getString("nome");
				
				String endereco = result.getString("endereco");
				String tel = result.getString("telefone");
				client = new Client(id, nome, endereco, tel);
			}
			
			log.info("busca de cliente realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar cliente", e);
		}
		
		return client;
	}
	
	public int getTotalClient() {
		log.info("Buscando quantidade total de clientes");
		Statement stm;
		int total = 0;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT COUNT(id) FROM cliente");
			total = result.getInt(1);
			log.info("total: " + total);
			log.info("Busca quantidade total de clientes realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar clientes", e);
		}
		return total;
	}
	
	public boolean insertClient(Client client) {
		log.info("Inserindo cliente");
		PreparedStatement stm;
		long id = 0;
		try {
			String sql = "INSERT INTO cliente(nome,endereco,telefone) VALUES (?,?,?)";
			stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			
			stm.setString(1, client.getName());
			stm.setString(2, client.getAddress());
			stm.setString(3, client.getPhone());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        
	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                id = generatedKeys.getLong(1);
	                client.setId(id);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			
			log.info("Inserido cliente com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao inserir cliente", e);
			return false;
		}
	}
	
	public boolean updateClient(Client client) {
		log.info("Editando cliente");
		PreparedStatement stm;
		try {
			String sql = "UPDATE cliente SET nome = ?, endereco = ?, telefone = ? WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, client.getName());
			stm.setString(2, client.getAddress());
			stm.setString(3, client.getPhone());
			stm.setLong(4, client.getId());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        
	        log.info("Cliente editado com sucesso");
	        return true;
		} catch (SQLException e) {
			log.error("Erro ao editar cliente", e);
			return false;
		}
	}
	
	public void deleteClient(Client client) {
		log.info("Removendo cliente");
		PreparedStatement stm;
		try {
			String sql = "DELETE FROM cliente WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setLong(1, client.getId());
			
			stm.executeUpdate();
			log.info("Cliente removido com sucesso");
		} catch (SQLException e) {
			log.error("Erro ao remover cliente", e);
		}
	}
	
	public List<Product> getAllProduct() {
		log.info("Buscando todos os produtos");
		List<Product> list = new ArrayList<Product>();
		Product product;
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM produtos ORDER BY nome");
			long id;
			String nome;
			double preco;
			while(result.next()) {
				id = result.getLong("id");
				nome = result.getString("nome");
				preco = result.getDouble("preco");
				product = new Product(id, nome, preco);
				list.add(product);
			}
			log.info("busca de produtos realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar produtos", e);
		}
		return list;
	}
	
	public void getTotalProduct() {
		log.info("Buscando quantidade de todos os produtos");
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT COUNT(id) FROM produtos");
			int total = result.getInt(1);
			log.info("total: " + total);	
			log.info("Busca quantidade de produtos realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar produtos", e);
		}
	}
	
	public Product getProduct(long id) {
		log.info("Buscando produto");
		Product product = new Product(0, "", 0.0);
		PreparedStatement stm;
		try {
			String sql = "SELECT * FROM produtos WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setLong(1, id);
			ResultSet result = stm.executeQuery();
			long idres;
			String nome;
			double preco;
			if (result.next()) {
				idres = result.getLong("id");
				nome = result.getString("nome");
				preco = result.getDouble("preco");
				product = new Product(idres, nome, preco);
			}
			
			log.info("busca de produto realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar produto", e);
		}
		
		return product;
	}
	
	public boolean insertProduct(Product product) {
		log.info("Inserindo produto");
		PreparedStatement stm;
		long id = 0;
		try {
			String sql = "INSERT INTO produtos(nome,preco) VALUES (?,?)";
			stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stm.setString(1, product.getNome());
			stm.setDouble(2, product.getPrice());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        
	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                id = generatedKeys.getLong(1);
	                product.setId(id);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	        
			log.info("Produto inserido com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao inserir produto", e);
			return false;
		}
	}
	
	public boolean updateProduct(Product product) {
		log.info("Editando produto");
		PreparedStatement stm;
		try {
			String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, product.getNome());
			stm.setDouble(2, product.getPrice());
			stm.setLong(3, product.getId());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        log.info("Produto editado com sucesso");
	        return true;
		} catch (SQLException e) {
			log.error("Erro ao editar produto", e);
			return false;
		}
	}
	
	public void deleteProduct() {
		log.info("Removendo produto");
		Statement stm;
		try {
			stm = conn.createStatement();
			stm.executeUpdate("DELETE FROM produtos WHERE id = 1");
			log.info("Produto removido com sucesso");
		} catch (SQLException e) {
			log.error("Erro ao remover produto", e);
		}
	}
	
	public List<Order> getAllOrder(boolean isOrderOpened) {
		log.info("Buscando todos os pedidos");
		List<Order> list = new ArrayList<Order>();
		Order orde;
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT * FROM pedidos ORDER BY status, data");
			if (isOrderOpened) result = stm.executeQuery("SELECT * FROM pedidos WHERE status='Aberto' ORDER BY data");
			long id;
			Date data;
			double frete;
			double valorTotal;
			long idCliente;
			String status;
			String name;
			while(result.next()) {
				id = result.getLong("id");
				data = result.getDate("data");
				frete = result.getDouble("frete");
				valorTotal = result.getDouble("valorTotal");
				idCliente = result.getLong("idCliente");
				name = result.getString("nameClient");
				status = result.getString("status");
				orde = new Order(id, data, frete, valorTotal, idCliente, status);
				orde.setTotalProduct(result.getDouble("valorTotalProdutos"));
				orde.setName(name);
				//orde.setItens(getOrderItens(id));
				list.add(orde);
			}
			log.info("busca de pedidos realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar pedidos", e);
		}
		return list;
	}
	
	public int getTotalOrder() {
		log.info("Buscando todos os pedidos");
		int total = 0;
		Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet result = stm.executeQuery("SELECT COUNT(id) FROM pedidos");
			total = result.getInt(0);
			log.info("total: " + total);	
			log.info("busca de pedidos realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar pedidos", e);
		}
		
		return total;
	}
	
	public Order getOrder(long id) {
		log.info("Buscando pedido");
		Order order = new Order(0, new java.util.Date(), 0.0, 0.0, 0, "");
		PreparedStatement stm;
		try {
			String sql = "SELECT * FROM pedidos WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setLong(1, id);
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				long idRes = result.getLong("id");
				Date data = result.getDate("data");
				double frete = result.getDouble("frete");
				double valorTotal = result.getDouble("valorTotal");
				long idCliente = result.getLong("idCliente");
				String status = result.getString("status");
				String name = result.getString("nameClient");
				log.info(data);
				order = new Order(idRes, data, frete, valorTotal, idCliente, status);
				order.setItens(getOrderItens(id));
				order.setTotalProduct(result.getDouble("valorTotalProdutos"));
				order.setName(name);
			}
			
			log.info("busca de pedido realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar pedido", e);
		}
		
		return order;
	}
	
	public boolean insertOrder(Order ord) {
		log.info("Inserindo pedidos");
		PreparedStatement stm;
		long id = 0;
		try {
			String sql = "INSERT INTO pedidos(data,frete,valorTotal,status,idCliente,valorTotalProdutos,nameClient) VALUES (?,?,?,?,?,?,?)";
			stm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stm.setDate(1,new Date(new java.util.Date().getTime()));
			stm.setDouble(2, ord.getShipping());
			stm.setDouble(3, ord.getTotalPrice());
			stm.setString(4, ord.getStatus());
			stm.setLong(5, ord.getIdClient());
			stm.setDouble(6, ord.getTotalProduct());
			stm.setString(7, ord.getName());
			
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                id = generatedKeys.getLong(1);
	                insertOrderItens(id, ord.getItens());
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
	        log.info("Pedidos inserido com sucesso");	
			return true;
		} catch (SQLException e) {
			log.error("Erro ao inserir pedidos", e);
			return false;
		}
	}
	
	public Report getOrderByDateInterval(Report report) {
		log.info("Dados no intervalo");
		PreparedStatement stm;
		try {
			String sql = "SELECT COUNT(*) as total, SUM(valorTotal) as valorTotal, SUM(frete) as freteTotal, SUM(valorTotalProdutos) as produtoTotal FROM pedidos WHERE data >= ? AND data <= ? AND status = ?";
			stm = conn.prepareStatement(sql);
			
			Date d = new Date(report.getInit().getTime());
			stm.setDate(1,d);
			
			d = new Date(report.getEnd().getTime());
			stm.setDate(2,d);
			
			stm.setString(3, "Concluído");
			
			ResultSet result = stm.executeQuery();
			if (result.next()) {
				report.setTotalOrders(result.getLong("total"));
				report.setTotalSold(result.getDouble("valorTotal"));
				report.setTotalFrete(result.getDouble("freteTotal"));
				report.setTotalProduct(result.getDouble("produtoTotal"));
			}
			
			log.info("busca realizado");
		} catch (SQLException e) {
			log.error("Erro ao buscar os dados", e);
			return null;
		}
		
		return report;
	}
	
	public boolean updateOrder(Order ord) {
		log.info("Editando pedidos");
		PreparedStatement stm;
		try {
			String sql = "UPDATE pedidos SET valorTotal = ?, frete = ?, valorTotalProdutos = ? WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setDouble(1, ord.getTotalPrice());
			stm.setDouble(2, ord.getShipping());
			stm.setDouble(3, ord.getTotalProduct());
			
			stm.setLong(4, ord.getId());
						
			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
	        
	        if (!deleteOrderItens(ord.getId())) {
	        	throw new SQLException("não foi possivel remover os registros");
			}
	        List<OrderItem> values = ord.getItens();
	        for (int index = 0; index < values.size(); index++) {
	        	updateOrderItens(values.get(index));				
			}
	        
			log.info("Pedidos editado com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao editar pedidos", e);
			return false;
		}
	}
	
	public boolean updateStatusOrder(long id) {
		log.info("Editando status pedidos");
		PreparedStatement stm;
		try {
			String status = "Aberto";
			Order value = getOrder(id);
			if ("Aberto".equalsIgnoreCase(value.getStatus())) {
				status = "Concluído";
			}
			
			String sql = "UPDATE pedidos SET status = ? WHERE id = ?";
			stm = conn.prepareStatement(sql);
			
			stm.setString(1, status);
			stm.setLong(2, id);
						
			stm.executeUpdate();
			log.info("Pedidos status editado com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao editar status pedidos", e);
		}
		
		return false;
	}
	
	public boolean deleteOrder(long id) {
		log.info("Removendo pedidos");
		Statement stm;
		try {
			stm = conn.createStatement();
			int affectedRows = stm.executeUpdate("DELETE FROM pedidos WHERE id = " + id);
			
			if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }
			log.info("Pedidos removido com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao remover pedidos", e);
			return false;
		}
	}
	
	public boolean deleteOrderItens(long id) {
		log.info("Removendo itens pedidos");
		Statement stm;
		try {
			stm = conn.createStatement();
			stm.executeUpdate("DELETE FROM pedidosItens WHERE idPedido = " + id);
			
			log.info("Itens pedidos removido com sucesso");
			return true;
		} catch (SQLException e) {
			log.error("Erro ao remover itens pedidos", e);
			return false;
		}
	}

	public List<OrderItem> getOrderItens(long id) {
		log.info("Buscando itens do pedido");
		List<OrderItem> itens = new ArrayList<OrderItem>();
		OrderItem orderItem;
		PreparedStatement stm;
		try {
			String sql = "SELECT * FROM pedidosItens WHERE idPedido = ?";
			stm = conn.prepareStatement(sql);

			stm.setLong(1,id);

			ResultSet result = stm.executeQuery();
			while(result.next()) {
				long idPedido = result.getLong("idPedido");
				long idProduto = result.getLong("idProduto");
				int quantidade = result.getInt("quantidade");
				double valor = result.getDouble("valor");
				String nomeProduto = result.getString("nomeProduto");
				orderItem = new OrderItem(idPedido, idProduto, quantidade, valor);
				orderItem.setNomeProduto(nomeProduto);
				itens.add(orderItem);
			}

			log.info("Item inserido com sucesso");
		}catch (Exception e) {
			log.error("Erro ao inserir itens dos pedidos", e);
		}
		return itens;
	}
	
	public void insertOrderItens(long id, List<OrderItem> itens) {
		log.info("Inserindo itens do pedido");
		for (OrderItem item : itens) {
			PreparedStatement stm;
			try {
				String sql = "INSERT INTO pedidosItens(idPedido,idProduto,quantidade,valor, nomeProduto) VALUES (?,?,?,?,?)";
				stm = conn.prepareStatement(sql);

				stm.setLong(1,id);
				stm.setLong(2, item.getIdProduto());
				stm.setInt(3, item.getQuantidade());
				stm.setDouble(4, item.getPrice());
				stm.setString(5, item.getNomeProduto());

				int affectedRows = stm.executeUpdate();

		        if (affectedRows == 0) {
		            throw new SQLException("Creating user failed, no rows affected.");
		        }

				log.info("Item inserido com sucesso");
			}catch (Exception e) {
				log.error("Erro ao inserir itens dos pedidos", e);
			}
		}
	}

	public boolean insertOrderItens(@Valid OrderItem item) {
		PreparedStatement stm;
		try {
			String sql = "INSERT INTO pedidosItens(idPedido,idProduto,quantidade,valor,nomeProduto) VALUES (?,?,?,?,?)";
			stm = conn.prepareStatement(sql);

			stm.setLong(1,item.getIdPedido());
			stm.setLong(2, item.getIdProduto());
			stm.setInt(3, item.getQuantidade());
			stm.setDouble(4, item.getPrice());
			stm.setString(5, item.getNomeProduto());

			int affectedRows = stm.executeUpdate();

	        if (affectedRows == 0) {
	            throw new SQLException("Creating user failed, no rows affected.");
	        }

	        log.info(item.getIdPedido());
	        log.info(item.getIdProduto());
	        log.info(item.getQuantidade());
	        log.info(item.getPrice());
	        log.info(item.getNomeProduto());
	        
			log.info("Item inserido com sucesso");
			return true;
		}catch (Exception e) {
			log.error("Erro ao inserir itens dos pedidos", e);
			return false;
		}	
	}
	
	public boolean updateOrderItens(@Valid OrderItem item) {
		if (!insertOrderItens(item)) {
			return false;
		}
		return true;
	}
	
	
}
