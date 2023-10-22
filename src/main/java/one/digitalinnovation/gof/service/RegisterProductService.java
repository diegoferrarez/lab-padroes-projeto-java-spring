package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.controller.dto.request.ProductRequest;
import one.digitalinnovation.gof.controller.dto.response.ProductResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface RegisterProductService {

   ProductResponse createProduct(String userLogin, String password, ProductRequest request);
   ResponseEntity<Optional<ProductRequest>> changeProduct(String id, ProductRequest request);
   List<ProductResponse> findAll();
   Optional<ProductResponse> findById(String id);
   String delete(String id);

}
