package kr.co.introme.introme.domain.blockchain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BlockResponse {
    Long id;
    String previousHash;
    Long member_id;
    int contribution;
    LocalDateTime timeStamp;
    String hash;

}
