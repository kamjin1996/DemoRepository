package com.zxx.demorepository.minio;

import io.minio.MinioClient;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author kam
 * @since 2021/10/20
 *
 * <p>
 * minio测试
 * </p>
 */
public class MinioTest {
    public static void main(String[] args) throws InvalidPortException, InvalidEndpointException, InvalidBucketNameException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        // MinioClient minioClient = new MinioClient("https://play.min.io", "Q3AM3UQ867SPQQA43P2F", "zuf+tfteSlswRu7BJ86wekitnifILbZam1KYY3TG");
        // minioClient.listBuckets().forEach(x -> System.out.println(x.name()));
        MinioClient minioClient = new MinioClient("http://172.17.0.4:9000", "admin", "12345678");
        minioClient.listBuckets().forEach(x -> System.out.println(x.name()));
    }
}