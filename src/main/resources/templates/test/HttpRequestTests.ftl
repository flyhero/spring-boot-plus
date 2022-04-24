package ${packageName};

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testHello() {
        String requestResult = this.restTemplate.getForObject("http://127.0.0.1:" + port + "/test",
                String.class);
        Assertions.assertThat(requestResult).contains("Hello, https://github.com/flyhero/spring-boot-plus");
    }
}