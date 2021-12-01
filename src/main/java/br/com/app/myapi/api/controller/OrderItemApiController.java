//package br.com.app.myapi.api.controller;
//
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.app.myapi.dao.SQliteManager;
//import br.com.app.myapi.model.OrderItem;
//
//
//public class OrderItemApiController {
//
//	private static final Logger log = LogManager.getLogger(OrderItemApiController.class);
//	
//	@GetMapping("/orderDetails/{id}")
//	public ResponseEntity<List<OrderItem>> listOrderItem(@PathVariable(value="id") long id) {
//		log.info(">listOrder()");
//		SQliteManager sq = new SQliteManager();
//		List<OrderItem> itens = sq.getOrderItens(id);
//		sq.connectionClose();
//		log.info("<listOrder()");
//		if (itens.isEmpty()) {
//			return new ResponseEntity<List<OrderItem>>(itens,HttpStatus.OK);	
//		}
//		return new ResponseEntity<List<OrderItem>>(itens,HttpStatus.OK);
//	}
//	
//	@PostMapping("/orderItem")
//	public ResponseEntity<OrderItem> addOrderItem(@RequestBody @Valid OrderItem item) {
//		log.info(">addOrderItem()");
//		SQliteManager sq = new SQliteManager();
//		boolean isInserted = sq.insertOrderItens(item);
//		sq.connectionClose();
//		log.info("<addOrderItem()");
//		if (isInserted) {
//			return new ResponseEntity<OrderItem>(item,HttpStatus.OK);			
//		}
//		return new ResponseEntity<OrderItem>(item,HttpStatus.NOT_ACCEPTABLE);
//	}
//	
//	@DeleteMapping("/orderItem/{id}")
//	public ResponseEntity<?> removeOrderItem(@PathVariable(value="id") long id) {
//		log.info(">removeOrderItem()");
//		SQliteManager sq = new SQliteManager();
//		boolean isInserted = sq.deleteOrderItens(id);
//		sq.connectionClose();
//		log.info("<removeOrderItem()");
//		if (isInserted) {
//			return new ResponseEntity<OrderItem>(HttpStatus.OK);			
//		}
//		return new ResponseEntity<OrderItem>(HttpStatus.NOT_MODIFIED);
//	}
//	
//	@PutMapping("/orderItem")
//	public ResponseEntity<OrderItem> updateOrderItem(@RequestBody @Valid OrderItem item) {
//		log.info(">updateOrderItem()");
//		SQliteManager sq = new SQliteManager();
//		boolean isModified = sq.updateOrderItens(item);
//		sq.connectionClose();
//		log.info("<updateOrderItem()");
//		if (isModified) {
//			return new ResponseEntity<OrderItem>(item,HttpStatus.OK);
//		}
//		return new ResponseEntity<OrderItem>(item,HttpStatus.NOT_MODIFIED);
//	}
//	
//}
