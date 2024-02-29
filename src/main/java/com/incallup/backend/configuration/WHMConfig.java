package com.incallup.backend.configuration;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WHMConfig {

    @Value("${whm.ftp.password}")
    private String PASS ;
    @Value("${whm.ftp.username}")
    private   String USER ;
    @Value("${whm.ftp.port}")
    private   int PORT ;
    @Value("${whm.ftp.server}")
    private String SERVER ;


    @Bean
    public FTPClient generateFTP(){

        FTPClient ftpClient = new FTPClient();

        try {

            ftpClient.connect(SERVER, PORT);
            ftpClient.login(USER, PASS);

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ftpClient;
    }


}