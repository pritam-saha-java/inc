package com.incallup.backend.utility;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class FileUtility {
    public static final String DOCUMENTUPLOAD = "/documentupload/";

    public File convert(MultipartFile file) {
        if(file==null)
            throw new RuntimeException();

        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try {
            var filing = convFile.createNewFile();
            FileOutputStream fos = new FileOutputStream(convFile);
            fos.write(file.getBytes());
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return convFile;
    }





    public File getImageFromBase64(String imageByte, String fileName) {
        File file = null;
        try {
            // convert base64 string to binary data
            int ind = imageByte.indexOf(",");
            byte[] data = Base64.decodeBase64(imageByte.substring(ind + 1));

            file = new File(fileName);
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(data);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return file;
    }







    public String getBase64Format(String fileName)  {
        if(null != fileName && !fileName.isEmpty()) {
            String ext = fileName.substring(fileName.lastIndexOf('.')+1);
            if(ext.equalsIgnoreCase("jpg")) {
                return "data:image/jpg;base64,";
            }else if(ext.equalsIgnoreCase("jpeg")) {
                return "data:image/jpeg;base64,";
            }else if(ext.equalsIgnoreCase("png")) {
                return "data:image/png;base64,";
            }else {
                return "data:image/jpeg;base64,";
            }
        }else {
            throw new RuntimeException();
        }

    }






    /**
     * get image name by saving byte
     * */
    public String uploadFileToWHM(String bytes, String name) throws Exception {
        String server = "103.39.132.238";
        int port = 21;
        String user = "test@ah.mahabms.com";
        String pass = "y_w)XolXtRQZ";
        InputStream fis =null;
        FTPClient ftpClient = new FTPClient();
        try {

            System.out.println("hello");
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            System.out.println(ftpClient.isConnected());
            // Set file type and transfer mode (these might not be necessary if already configured in the WHMConfig)
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            int index = name.lastIndexOf('.');
            String finalName = name.substring(0, index) + System.currentTimeMillis() + name.substring(index);
            // Specify the remote file path on the WHM server
            String remoteFilePath =  FileUtility.DOCUMENTUPLOAD+ finalName;

            int ind = bytes.indexOf(",");
            byte[] content = Base64.decodeBase64(bytes.substring(ind + 1));
            fis = new ByteArrayInputStream(content);

            // Upload the file
            ftpClient.storeFile(remoteFilePath, fis);
            return finalName;
        } catch (Exception e) {

            throw new RuntimeException();
        }
        finally {
            assert fis != null;
            fis.close();
            ftpClient.logout();
            ftpClient.disconnect();

        }
    }
    public ArrayDeque<String> getPresignedBase64sFromWHM(List<String> fileNames) throws IOException {


        // Initialize an empty list for storing presigned URLs
        List<String> presignedURLs = new LinkedList<>();

        // Loop through each file name
        for (String fileName : fileNames) {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect("103.39.132.238", 21);
                ftpClient.login("test@ah.mahabms.com", "y_w)XolXtRQZ");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                if(null == fileName || fileName.isEmpty()) {
                    presignedURLs.add("");
                    continue; // Skip if file not found
                }
                // Specify the remote file path on the WHM server
                String remoteFilePath = FileUtility.DOCUMENTUPLOAD + fileName;

                // Download the file and convert it to base64
                InputStream remoteFile = ftpClient.retrieveFileStream(remoteFilePath);
                if (remoteFile == null) {
                    presignedURLs.add("");
                    continue; // Skip if file not found
                }
                String base64String = convertInputStreamToBase64(remoteFile);
                remoteFile.close();

                // Generate the presigned URL from base64 data
                String presignedURL = "data:image/png;base64," + base64String;
//            System.out.println(presignedURL);
                // Add the presigned URL to the list
                presignedURLs.add(presignedURL);
            }
            catch(Exception e) {
                presignedURLs.add("");
                continue; // Skip if file not found
            }
            finally {
                ftpClient.logout();
                ftpClient.disconnect();

            }
        }

        // Disconnect from the WHM server
//        ftpClient.logout();
//        ftpClient.disconnect();

        return new ArrayDeque<String>(presignedURLs);
    }

    /**
     * get byte 64 image from whm
     * */
    public String getPresignedBase64FromWHM(String file) {
        String server = "incallup.com";
        int port = 22;
        String user = "dev@incallup.com";
        String pass = "2NNx]l-9tgBn";
        InputStream fis =null;
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);

            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();


            String remoteFilePath = DOCUMENTUPLOAD + file;



            InputStream remoteFile = ftpClient.retrieveFileStream(remoteFilePath);
            if(remoteFile==null) {
                return "";
            }
            String fileString ="";

            fileString= this.convertInputStreamToBase64(remoteFile);
            String byte64URL = "data:image/png;base64," +fileString;


            ftpClient.logout();
            ftpClient.disconnect();
            remoteFile.close();
            return byte64URL;
        } catch (Exception e) {

            // Handle exception appropriately (e.g., log and return an error message)
            return "Error generating presigned URL: " + e.getMessage();
        }

    }
    public String convertInputStreamToBase64(InputStream inputStream) throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        byte[] bytes = outputStream.toByteArray();
        return java.util.Base64.getEncoder().encodeToString(bytes);
    }

}