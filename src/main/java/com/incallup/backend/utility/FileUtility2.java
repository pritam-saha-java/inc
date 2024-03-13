package com.incallup.backend.utility;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Component
@Slf4j
public class FileUtility2 {
    public static File convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        var booleanR = convertFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convertFile);
        fos.write(file.getBytes());
        fos.close();
        return convertFile;
    }

    public static File convertInputStreamToFile(InputStream inputStream, String savePath) {
        OutputStream outputStream = null;
        File file = new File(savePath);
        try {
            outputStream = new FileOutputStream(file);
            int read;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            log.info("convert InputStream to file done, savePath is : {}", savePath);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}
