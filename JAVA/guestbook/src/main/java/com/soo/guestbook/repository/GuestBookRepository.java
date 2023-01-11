package com.soo.guestbook.repository;

import com.soo.guestbook.domain.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestBookRepository extends JpaRepository<GuestBook, Long>, QuerydslPredicateExecutor<GuestBook> {

}
