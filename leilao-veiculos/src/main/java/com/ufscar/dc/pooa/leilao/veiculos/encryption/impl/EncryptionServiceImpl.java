package com.ufscar.dc.pooa.leilao.veiculos.encryption.impl;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.CleartextKeysetHandle;
import com.google.crypto.tink.JsonKeysetReader;
import com.google.crypto.tink.JsonKeysetWriter;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadConfig;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.ufscar.dc.pooa.leilao.veiculos.encryption.EncryptionService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Base64;

@Service
public class EncryptionServiceImpl implements EncryptionService {

    private Aead aead;

    @Value("${leilao.keyset.filename:leilao-keyset.json}")
    private String keysetFilename;

    @PostConstruct
    public void init() throws GeneralSecurityException, IOException {
        AeadConfig.register();

        File keysetFile = new File(keysetFilename);
        if (!keysetFile.exists()) {
            KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES256_GCM);
            CleartextKeysetHandle.write(keysetHandle, JsonKeysetWriter.withFile(keysetFile));
        }

        KeysetHandle keysetHandle = CleartextKeysetHandle.read(JsonKeysetReader.withFile(keysetFile));
        this.aead = keysetHandle.getPrimitive(Aead.class);
    }

    @Override
    public String encrypt(String data) {
        if (data == null) {
            return null;
        }
        try {
            byte[] ciphertext = aead.encrypt(data.getBytes(StandardCharsets.UTF_8), null);
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Erro ao criptografar os dados", e);
        }
    }

    @Override
    public String decrypt(String encryptedData) {
        if (encryptedData == null) {
            return null;
        }
        try {
            byte[] ciphertext = Base64.getDecoder().decode(encryptedData);
            byte[] decrypted = aead.decrypt(ciphertext, null);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException("Erro ao descriptografar os dados", e);
        }
    }
}