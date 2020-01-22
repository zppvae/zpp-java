package org.java.util;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.model.*;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.ClientConfiguration;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zpp
 * @date 2019/11/27 9:45
 */
public class AWSUtil {


    public static void main(String[] args) {
        final String accessKey = "0AEEAFD1596E94E13F6A91CF91A4E952";
        final String secretKey = "9DDC1A0493B7E0CE084B2EA7E4938E36";
        final String endpoint = "s3.cn-south-1.jdcloud-oss.com";
        ClientConfiguration config = new ClientConfiguration();

        AwsClientBuilder.EndpointConfiguration endpointConfig =
                new AwsClientBuilder.EndpointConfiguration(endpoint, "cn-south-1");

        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey,secretKey);
        AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(awsCredentials);

        AmazonS3 s3 = AmazonS3Client.builder()
                .withEndpointConfiguration(endpointConfig)
                .withClientConfiguration(config)
                .withCredentials(awsCredentialsProvider)
                .disableChunkedEncoding()
                .withPathStyleAccessEnabled(true)
                .build();

        // 初始化分片上传
        String bucket_name = "oss-svoc";
        String file_path = "F://jdcloud/base-vip-20.qcow2";
        String key = Paths.get(file_path).getFileName().toString();
        File file = new File(file_path);
        long contentLength = file.length();
        long partSize = 5 * 1024 * 1024; // 设置每个分片大小为5MB.

        try {
            // 创建对象的Etag列表，并取回每个分片的Etag。
            List<PartETag> partETags = new ArrayList<PartETag>();
            // 初始化分片上传
            InitiateMultipartUploadRequest initRequest = new InitiateMultipartUploadRequest(bucket_name, key);
            InitiateMultipartUploadResult initResponse = s3.initiateMultipartUpload(initRequest);

            // 上传分片
            long filePosition = 0;
            for (int i = 1; filePosition < contentLength; i++) {
                partSize = Math.min(partSize, (contentLength - filePosition));
                UploadPartRequest uploadRequest = new UploadPartRequest()
                        .withBucketName(bucket_name)
                        .withKey(key)
                        .withUploadId(initResponse.getUploadId())
                        .withPartNumber(i)
                        .withFileOffset(filePosition)
                        .withFile(file)
                        .withPartSize(partSize);
                // 上传分片并将返回的Etag加入列表中
                UploadPartResult uploadResult = s3.uploadPart(uploadRequest);
                partETags.add(uploadResult.getPartETag());
                filePosition += partSize;
            }

            // 完成分片上传
            CompleteMultipartUploadRequest compRequest = new CompleteMultipartUploadRequest(bucket_name, key,
                    initResponse.getUploadId(), partETags);
            s3.completeMultipartUpload(compRequest);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }
}
