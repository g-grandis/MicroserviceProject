package org.borrowing.grandis.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
@Table(name = "borrow")
public class Borrowing {

    @Id
    private Long borrowId;

    private String customerId;

    private String notifyToPhoneNr;


}
