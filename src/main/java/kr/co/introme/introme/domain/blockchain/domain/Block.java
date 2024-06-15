package kr.co.introme.introme.domain.blockchain.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "block")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String previousHash;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;

    @Column(nullable = false)
    private int contribution;

    @Column(nullable = false)
    private LocalDateTime timeStamp;

    @Column(nullable = false)
    private String hash;

    public Block(String previousHash, Member member, int contribution) {
        this.previousHash = previousHash;
        this.member = member;
        this.contribution = contribution;
        this.timeStamp = LocalDateTime.now();
        this.hash = calculateHash();
    }

    public Block(String previousHash, String hash) {
        this.previousHash = previousHash;
        this.member = null;
        this.contribution = 0;
        this.timeStamp = LocalDateTime.now();
        this.hash = hash;
    }

    public String calculateHash() {
        String dataToHash = previousHash + member.getId() + contribution + timeStamp.toString();
        System.out.println("make hash result = " + dataToHash);
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
