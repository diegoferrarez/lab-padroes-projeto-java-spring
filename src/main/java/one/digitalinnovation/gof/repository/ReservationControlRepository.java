package one.digitalinnovation.gof.repository;

import one.digitalinnovation.gof.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationControlRepository extends MongoRepository<Reservation, String> {

//    @Query("{ 'numberReservation' : ?0 }")
    @Query("SELECT t FROM AcessControlRooms t WHERE t.numberReservation")
    Reservation findBy(String numberReservation);
}
