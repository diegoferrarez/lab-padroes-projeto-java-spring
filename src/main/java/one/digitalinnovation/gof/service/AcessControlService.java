package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.controller.dto.request.ReservationRequest;
import one.digitalinnovation.gof.controller.dto.response.ReservationResponse;

import java.util.List;

public interface AcessControlService {

    List<ReservationResponse> findAll();
    ReservationResponse createReservation(String userLogin, String password, ReservationRequest request);
    ReservationResponse cancelReservation(String userLogin, String password, String numberReservation);
}
