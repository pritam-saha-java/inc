package com.incallup.backend.utility;


import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WHMConfig {
    public static final String PASS = "2NNx]l-9tgBn";
    public static final String USER = "dev";
    public static final int PORT = 21;
    public static final String SERVER = "incallup.com";

//    @Value("${whm.ftp.server}")
//    private String ftpServer;
//
//    @Value("${whm.ftp.port}")
//    private int ftpPort;
//
//    @Value("${whm.ftp.username}")
//    private String ftpUsername;
//
//    @Value("${whm.ftp.password}")
//    private String ftpPassword;

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