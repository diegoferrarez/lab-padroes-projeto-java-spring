package one.digitalinnovation.gof.controller.dto.request;

import one.digitalinnovation.gof.model.enums.Documents;
import one.digitalinnovation.gof.model.enums.StatusReservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequest {

    @Id
    private String id;
    private Integer numberReservation;
    private Integer numberReservationRoom;
    private String nameReservationClient;
    private BigDecimal valuePerDayRoom;
    private String phoneNumber;
    private String documentNumber;
    private Documents documentType;
    private StatusReservation status;

}
