package com.msi.cards.repository;

import com.msi.cards.model.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
    public List<Cards> findByCustomerId(int customerId);
}
