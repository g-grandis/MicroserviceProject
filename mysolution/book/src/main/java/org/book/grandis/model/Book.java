package org.book.grandis.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document
public class Book {

    @Id
    private String bookId;
    private String titolo;
    private String autore;


}
