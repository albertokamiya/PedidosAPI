package br.com.app.myapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.myapi.dao.SQliteManager;
import br.com.app.myapi.model.Order;

@RestController
public class OrderApiController extends MyApiController {
	
	private static final Logger log = LogManager.getLogger(OrderApiController.class);

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> listOrder() {
		log.info(">listOrder()");
		boolean isOrderOpened = false;
		SQliteManager sq = new SQliteManager();
		List<Order> list = sq.getAllOrder(isOrderOpened);
		sq.connectionClose();
		log.info("<listOrder()");
		if (list.isEmpty()) {
			return new ResponseEntity<List<Order>>(list,HttpStatus.OK);	
		}
		return new ResponseEntity<List<Order>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/ordersOpened")
	public ResponseEntity<List<Order>> listOrderOpened() {
		log.info(">listOrder()");
		boolean isOrderOpened = true;
		SQliteManager sq = new SQliteManager();
		List<Order> list = sq.getAllOrder(isOrderOpened);
		sq.connectionClose();
		log.info("<listOrder()");
		if (list.isEmpty()) {
			return new ResponseEntity<List<Order>>(list,HttpStatus.OK);	
		}
		return new ResponseEntity<List<Order>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/order/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable(value="id") long id) {
		log.info(">getOrder()");
		SQliteManager sq = new SQliteManager();
		Order order = sq.getOrder(id);
		sq.connectionClose();
		log.info("<getOrder()");
		if (order.getId() == 0) {
			return new ResponseEntity<Order>(order,HttpStatus.NOT_FOUND);	
		}
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody @Valid Order order) {
		log.info(">addOrder()");
		SQliteManager sq = new SQliteManager();
		boolean isInserted = sq.insertOrder(order);
		sq.connectionClose();
		log.info("<addOrder()");
		if (isInserted) {
			return new ResponseEntity<Order>(order,HttpStatus.OK);	
		}
		return new ResponseEntity<Order>(order,HttpStatus.NOT_ACCEPTABLE);
	}
	
	@DeleteMapping("/order/{id}")
	public ResponseEntity<?> removeOrder(@PathVariable(value="id") long id) {
		log.info(">removeOrder()");
		SQliteManager sq = new SQliteManager();
		boolean isRemoved = sq.deleteOrderItens(id);
		if (isRemoved) isRemoved = sq.deleteOrder(id);
		sq.connectionClose();
		log.info("<removeOrder()");
		if (isRemoved) return new ResponseEntity<>(HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
	}
	
	@PutMapping("/order")
	public ResponseEntity<Order> updateOrder(@RequestBody @Valid Order order) {
		log.info(">updateOrder()");
		SQliteManager sq = new SQliteManager();
		boolean isModified = sq.updateOrder(order);
		sq.connectionClose();
		log.info("<updateOrder()");
		if (isModified) {
			return new ResponseEntity<Order>(order,HttpStatus.OK);			
		}
		return new ResponseEntity<Order>(order,HttpStatus.NOT_MODIFIED);
	}
	
	@PutMapping("/order/status/{id}")
	public ResponseEntity<?> updateStatusOrder(@PathVariable(value="id") long id) {
		log.info(">updateOrder()");
		SQliteManager sq = new SQliteManager();
		boolean isModified = sq.updateStatusOrder(id);
		sq.connectionClose();
		log.info("<updateOrder()");
		if (isModified) {
			return new ResponseEntity<Order>(HttpStatus.OK);			
		}
		return new ResponseEntity<Order>(HttpStatus.NOT_MODIFIED);
	}
	
	
}
