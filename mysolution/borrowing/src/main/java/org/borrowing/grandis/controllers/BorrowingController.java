package org.borrowing.grandis.controllers;

import org.borrowing.grandis.model.Borrowing;
import org.borrowing.grandis.repos.BorrowingRepository;
import org.borrowing.grandis.services.NotificationClient;
import org.borrowing.grandis.services.TraceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@Slf4j
@RequestMapping(value = "/v2/borrow")
public class BorrowingController {

    @Autowired
    TraceService traceService;

    @Autowired
    NotificationClient notificationClient;

    @Value("${kafka.sms.message}")
    private String message;

    @Autowired
    private final BorrowingRepository borrowingRepository;

    public BorrowingController(BorrowingRepository borrowingRepository) {
        this.borrowingRepository = borrowingRepository;
    }

    // CREATE
    @RequestMapping(method = RequestMethod.PUT)
    public void addNewBorrow(@RequestBody Borrowing borrowing) {
        notificationClient.sendSMS(borrowing);
        borrowingRepository.save(borrowing);
    }


    // READ
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.GET)
    public Borrowing getBorrow(@PathVariable Long orderId) {
        Optional<Borrowing> orderOptional = borrowingRepository.findById(orderId);
        return orderOptional.get();
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Borrowing> getAllOrders() {
        log.info("Get all borrow");
        List<Borrowing> result = new ArrayList<Borrowing>();
        Iterable<Borrowing> iterable = borrowingRepository.findAll();
        iterable.forEach(result::add);
        return result;
    }


    // UPDATE
    @RequestMapping(value = "/{borrowId}", method = RequestMethod.POST)
    public Borrowing modifyBorrow(@RequestBody Borrowing borrowing, @RequestBody String orderId ) {
        return borrowingRepository.save(borrowing);
    }


    // DELETE
    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllBorrow() {
        borrowingRepository.deleteAll();
    }

    @RequestMapping(value = "/{borrowId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable Long orderId) {
        borrowingRepository.deleteById(orderId);
    }
}
