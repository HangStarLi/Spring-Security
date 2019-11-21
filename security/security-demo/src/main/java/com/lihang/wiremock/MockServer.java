package com.lihang.wiremock;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class MockServer {
    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("file/json.txt");
        String content =StringUtils.join( FileUtils.readLines(resource.getFile(),"utf-8").toArray(),"\n");
        System.out.println(content);
        WireMock.configureFor(8081);
        WireMock.removeAllMappings();
        WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1")).willReturn(WireMock.aResponse().withBody(content).withStatus(200)));
    }
}
