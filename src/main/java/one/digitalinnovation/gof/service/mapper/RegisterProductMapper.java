package one.digitalinnovation.gof.service.mapper;

import one.digitalinnovation.gof.controller.dto.response.ProductResponse;
import one.digitalinnovation.gof.controller.dto.response.ReservationResponse;
import one.digitalinnovation.gof.model.Reservation;
import one.digitalinnovation.gof.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterProductMapper {

    private final ObjectMapper mapper;

    @SneakyThrows
    public ProductResponse toResponse(Product product) {
        var json = this.mapper.writeValueAsString(product);
        return this.mapper.readValue(json, ProductResponse.class);
    }

    @SneakyThrows
    public ReservationResponse toAcessResponse(Reservation reservation){
        var json = this.mapper.writeValueAsString(reservation);
        return this.mapper.readValue(json, ReservationResponse.class);
    }
}
