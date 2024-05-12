package kr.co.introme.introme.domain.blockchain.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Blockchain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
