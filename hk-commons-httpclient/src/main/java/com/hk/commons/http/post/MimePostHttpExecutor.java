package com.hk.commons.http.post;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * 支持文件上传的POST请求
 *
 * @author kevin
 * @date 2017年10月12日下午4:58:33
 */
public class MimePostHttpExecutor extends AbstractPostHttpExecutor<String, Map<String, Object>> {

    public MimePostHttpExecutor() {
        super(BASIC_HANDLER);
    }

    public MimePostHttpExecutor(CloseableHttpClient httpClient, ResponseHandler<String> responseHandler) {
        super(httpClient, responseHandler);
    }

    @Override
    public HttpEntity generateEntity(Map<String, Object> params) {
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create()
                .setMode(HttpMultipartMode.BROWSER_COMPATIBLE)
                .setCharset(Consts.UTF_8);
        for (Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (Objects.nonNull(value)) {
                if (value instanceof File) {
                    entityBuilder.addBinaryBody(entry.getKey(), (File) value);
                } else if (value instanceof ContentBody) {
                    ContentBody body = (ContentBody) value;
                    entityBuilder.addPart(entry.getKey(), body);
                    //				entityBuilder.addBinaryBody(entry.getKey(),body.getInputStream()
                    //						,ContentType.create(body.getMediaType(),body.getCharset()),body.getFilename());//文件名??
                } else if (value instanceof InputStream) {
                    entityBuilder.addBinaryBody(entry.getKey(), (InputStream) value);
                } else {
                    entityBuilder.addPart(entry.getKey(), new StringBody(value.toString(), ContentType.create("text/plain", Consts.UTF_8)));
                }
            }
        }
        return entityBuilder.build();
    }

}
