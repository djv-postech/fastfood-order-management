package com.fiap.postech.techchallenge.fastfoodordermanagement.infra;

import com.fiap.postech.techchallenge.fastfoodordermanagement.infra.persistence.repository.entity.ClienteEntity;
import lombok.NoArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ClienteEncoder {

    private static final String CHAVE = "tech-challenge-k";
    private static final Key chave = new SecretKeySpec(CHAVE.getBytes(), "AES");

    public static ClienteEntity encode(ClienteEntity cliente) {
       String nome = criptografar(cliente.getNome());
       String cpf = criptografar(cliente.getCpf());
       String email = criptografar(cliente.getEmail());
       return new ClienteEntity(nome, cpf, email);
    }

    public static ClienteEntity decode(ClienteEntity cliente) {
        String nome = descriptografar(cliente.getNome());
        String cpf = descriptografar(cliente.getCpf());
        String email = descriptografar(cliente.getEmail());
        return new ClienteEntity(nome, cpf, email);
    }

    public static String criptografar(String texto) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] textoCriptografado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(textoCriptografado);
        }catch (Exception e){
            throw new RuntimeException("Erro ao criptografar texto", e);
        }
    }

    private static String descriptografar(String textoCriptografado){
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] textoDescriptografado = cipher.doFinal(Base64.getDecoder()
                    .decode(textoCriptografado));
            return new String(textoDescriptografado);
        }catch (Exception e){
            throw new RuntimeException("Erro ao descriptografar texto", e);
        }
    }
}
