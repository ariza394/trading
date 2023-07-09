package com.carlos.usuarios.beusers.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.carlos.usuarios.beusers.clases.FileUtil;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    // private static final Logger log = LoggerFactory.getLogger(StorageService.class);
    
    
    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file, String folderName) {
        MultipartFile adjunto = file;
        String originalFilename = adjunto.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = FileUtil.generateFileName(extension);


        File fileObj = convertMultiPartFileToFile(file);
        String filePath = folderName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, filePath, fileObj));
        fileObj.delete();
        return "https://javaciaofilles.s3.us-east-1.amazonaws.com/" + filePath;
    }

    public String updateFile(MultipartFile file, String folderName, String fileDelete) {

        //Nombre de archivo
        MultipartFile adjunto = file;
        String originalFilename = adjunto.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = FileUtil.generateFileName(extension);

        File fileObj = convertMultiPartFileToFile(file);
        String filePath = folderName + "/" + fileName;
        s3Client.putObject(new PutObjectRequest(bucketName, filePath, fileObj));
        
        //elimina anterior
        String relativePath = fileDelete.substring(fileDelete.indexOf(".com/") + ".com/".length());
        s3Client.deleteObject(bucketName,relativePath);
        fileObj.delete();

        return "https://javaciaofilles.s3.us-east-1.amazonaws.com/" + filePath;
    }

    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            // log.error("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
