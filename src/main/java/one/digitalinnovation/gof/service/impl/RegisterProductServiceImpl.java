package one.digitalinnovation.gof.service.impl;

import one.digitalinnovation.gof.controller.dto.request.ProductRequest;
import one.digitalinnovation.gof.controller.dto.response.ProductResponse;
import one.digitalinnovation.gof.repository.ProductStoreRepository;
import one.digitalinnovation.gof.service.RegisterProductService;
import one.digitalinnovation.gof.model.Product;
import one.digitalinnovation.gof.service.mapper.RegisterProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Slf4j
public class RegisterProductServiceImpl implements RegisterProductService {

    private final RegisterProductMapper mapper;

    @Autowired
    private ProductStoreRepository repository;

    @Override
    @Transactional
    public ProductResponse createProduct(String userLogin, String password, ProductRequest dto) {

//        ProductResponse productResponse = userApi
        var registerProduct = this.repository.save(savedProduct(dto));
        return this.mapper.toResponse(registerProduct);
    }

    @Override
    @Transactional
    public ResponseEntity<Optional<ProductRequest>> changeProduct(String id, ProductRequest dto) {
        Optional<Product> product = repository.findById(id);
        if (product.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        product.ifPresent(s -> {
            s.setIdInternal(dto.getIdInternal());
            s.setNameProduct(dto.getNameProduct());
            s.setValueProduct(dto.getValueProduct());
            s.setSizeProduct(dto.getSizeProduct());
            repository.save(s);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @Transactional
    public List<ProductResponse> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Product> products = repository.findAll();
        return Arrays.asList(modelMapper.map(products, ProductResponse[].class));
    }

    @Override
    @Transactional
    public Optional<ProductResponse> findById(String id){
        return repository.findById(id).map(ProductResponse::converter);
    }

    @Override
    @Transactional
    public String delete(final String id){
        repository.deleteById(id);
        return "successfully deleted";
    }

    private Product savedProduct (ProductRequest p){
        return Product.builder()
                .idInternal(p.getIdInternal())
                .nameProduct(p.getNameProduct())
                .sizeProduct(p.getSizeProduct())
                .valueProduct(p.getValueProduct())
                .user(p.getUser())
                .build();
    }

    public RegisterProductServiceImpl(RegisterProductMapper mapper) {
        this.mapper = mapper;
    }
}
