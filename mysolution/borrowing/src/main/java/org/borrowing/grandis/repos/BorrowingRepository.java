package org.borrowing.grandis.repos;


import org.borrowing.grandis.model.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {


}
