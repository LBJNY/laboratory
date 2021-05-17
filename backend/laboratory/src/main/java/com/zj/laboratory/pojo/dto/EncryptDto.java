package com.zj.laboratory.pojo.dto;

import lombok.Data;

@Data
public class EncryptDto {

    private String encryptedData;
    private String sessionKey;
    private String iv;
}
