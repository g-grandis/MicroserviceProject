package org.book.grandis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Customer {

    @Id
    private String customerId;
    private String nome;
    private String cognome;


}
