package one.digitalinnovation.gof.service.impl;

import lombok.extern.slf4j.Slf4j;
import one.digitalinnovation.gof.controller.dto.request.ReservationRequest;
import one.digitalinnovation.gof.controller.dto.response.ReservationResponse;
import one.digitalinnovation.gof.model.Reservation;
import one.digitalinnovation.gof.model.enums.StatusReservation;
import one.digitalinnovation.gof.repository.ReservationControlRepository;
import one.digitalinnovation.gof.service.AcessControlService;
import one.digitalinnovation.gof.service.mapper.RegisterProductMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class AcessControlServiceImpl implements AcessControlService {

    private final RegisterProductMapper mapper;;

    private final ModelMapper modelMapper;
    @Autowired
    private ReservationControlRepository repository;
    @Override
    @Transactional
    public List<ReservationResponse> findAll() {
        ModelMapper modelMapper = new ModelMapper();
        List<Reservation> reservations = repository.findAll();
        return Arrays.asList(modelMapper.map(reservations, ReservationResponse[].class));
    }

    @Override
    @Transactional
    public ReservationResponse createReservation(String userLogin, String password, ReservationRequest dto) {
        var reservation = saveReservation(dto);
        reservation.setStatus(StatusReservation.ACTIVE);
        var reservationPersist = this.repository.save(reservation);
        return this.mapper.toAcessResponse(reservationPersist);
    }

    @Override
    @Transactional
    public ReservationResponse cancelReservation(String userLogin, String password, String numberReservation){
        var reservation = repository.findBy(numberReservation);
        reservation.setStatus(StatusReservation.CANCELED);
        final Reservation reservationUpdate = repository.save(reservation);
        return this.mapper.toAcessResponse(reservationUpdate);
    }

    @Override
    public List<ReservationResponse> download() {
        ModelMapper modelMapper = new ModelMapper();
        List<Reservation> reservations = repository.findAll();
        String fileName = "C:/novo.xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reservas");

        int rownum = 0;

        for (Reservation reservation : reservations){
            Row row = sheet.createRow(rownum++);
            int cellnum = 0;

            Cell cellOp = row.createCell(cellnum++);
            cellOp.setCellValue(reservation.getId());
        }
        try {
            FileOutputStream out
                    = new FileOutputStream(new File(fileName));
            workbook.write(out);
            out.close();
            System.out.println("Arquivo Excel criado com sucesso!");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Arquivo não encontrado!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro na edição do arquivo!");
        }

        return null;
        }


    private boolean validarToken() {
        return Boolean.TRUE;
    }

    private Reservation saveReservation(ReservationRequest a){
        return Reservation.builder()
                .numberReservation(a.getNumberReservation())
                .numberReservationRoom(a.getNumberReservationRoom())
                .valuePerDayRoom(a.getValuePerDayRoom())
                .nameReservationClient(a.getNameReservationClient())
                .documentNumber(a.getDocumentNumber())
                .documentType(a.getDocumentType())
                .phoneNumber(a.getPhoneNumber())
                .status(a.getStatus())
                .build();
    }


    public AcessControlServiceImpl(RegisterProductMapper mapper, ModelMapper modelMapper) {
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }
}
