package com.incallup.backend.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.xml.stream.events.EndDocument;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Getter
@Setter
public class RSAKeyProperties {

    private RSAPublicKey rsaPublicKey;
    private RSAPrivateKey rsaPrivateKey;

    public RSAKeyProperties(){
        KeyPair keyPair = Encoder.generateKey();
        this.rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
        this.rsaPrivateKey =(RSAPrivateKey) keyPair.getPrivate();
    }
}
