package com.ufscar.dc.pooa.leilao.veiculos.encryption;

public interface EncryptionService {
    String encrypt(String data);
    String decrypt(String encryptedData);
}
