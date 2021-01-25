package org.borrowing.grandis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class BorrowingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BorrowingApplication.class, args);
    }
}
