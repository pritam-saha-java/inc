package com.incallup.backend.configuration;
import com.incallup.backend.exception.ApplicationException;
import com.jcraft.jsch.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class WHMService  {

    public static final String INCTEST = "/inctest/";
    @Value("${whm.ftp.password}")
    private final String PASS ;
    @Value("${whm.ftp.username}")
    private final  String USER ;
    @Value("${whm.ftp.port}")
    private final   int PORT ;
    @Value("${whm.ftp.server}")
    private final String SERVER ;
//    @Value("${whm.ftp.sessionTimeout}")
    private final Integer SESSION_TIMEOUT = 15000;

//    @Value("${whm.ftp.channelTimeout}")
    private final Integer CHANNEL_TIMEOUT = 15000;
//    @Bean
//    public SSHClient setupSshj() throws IOException {
//        SSHClient client = new SSHClient();
//        client.addHostKeyVerifier(new PromiscuousVerifier());
//        client.connect(SERVER);
//        client.useCompression();
//        client.authPassword(USER, PASS);
//        return client;
//    }

    public boolean uploadFile(String localFilePath, String remoteFilePath) throws ApplicationException {
        ChannelSftp channelSftp = createChannelSftp();
        try {
            channelSftp.put(localFilePath, remoteFilePath);
            return true;
        } catch(SftpException ex) {
            log.error("Error upload file", ex);
        } finally {
            disconnectChannelSftp(channelSftp);
        }

        return false;
    }


    public void uploadFile(MultipartFile multipartFile,String categoryName)throws ApplicationException, SftpException, IOException{

           ChannelSftp channel = createChannelSftp();
       try {



               channel.cd(INCTEST);
           channel.put(multipartFile.getInputStream(), categoryName, ChannelSftp.OVERWRITE);

       }catch (NullPointerException e){
           throw ApplicationException.builder()
                   .title("No connection found")
                   .Description("connnection failed with remote server")
                   .build();
       }
        finally {
            disconnectChannelSftp(channel);
        }


    }

    public String downloadBase64String(String fileName){



        return fileName;
    }

    public boolean downloadFile(String localFilePath, String remoteFilePath) throws ApplicationException {
        ChannelSftp channelSftp = createChannelSftp();

//        channelSftp.get()
        OutputStream outputStream;
        try {
            File file = new File(localFilePath);
            outputStream = new FileOutputStream(file);
            channelSftp.get(remoteFilePath, outputStream);

            var state = file.createNewFile();
            return true;
        } catch(SftpException | IOException |NullPointerException ex) {
            log.error("Error download file", ex);
        } finally {
            disconnectChannelSftp(channelSftp);
        }

        return false;
    }

    private ChannelSftp createChannelSftp() throws ApplicationException {
        try {
            JSch jSch = new JSch();
            Session session = jSch.getSession(USER, SERVER, PORT);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(PASS);
            session.connect(SESSION_TIMEOUT);
            Channel channel = session.openChannel("sftp");
            channel.connect(CHANNEL_TIMEOUT);
            return (ChannelSftp) channel;


        } catch(JSchException ex) {
            throw ApplicationException
                    .builder()
                    .title("sftp exception")
                    .Description("Create ChannelSftp error")
                    .build();
        }


    }

    private void disconnectChannelSftp(ChannelSftp channelSftp) {
        try {
            if( channelSftp == null)
                return;

            if(channelSftp.isConnected())
                channelSftp.disconnect();

            if(channelSftp.getSession() != null)
                channelSftp.getSession().disconnect();

        } catch(Exception ex) {
            log.error("SFTP disconnect error", ex);
        }
    }



//    @Bean
//    public FTPClient generateFTP(){
//
//        FTPClient ftpClient = new FTPClient();
//
//        try {
//            ftpClient.connect(SERVER, PORT);
//            ftpClient.login(USER, PASS);
//
//            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//            ftpClient.enterLocalPassiveMode();
//        }catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        return ftpClient;
//    }


}