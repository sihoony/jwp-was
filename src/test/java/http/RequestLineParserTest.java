package http;

import http.exceptions.UnsupportedHttpMethodException;
import http.requests.RequestLine;
import http.requests.parameters.QueryString;
import http.requests.types.HttpMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.HttpRequestParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RequestLineParserTest {

    @DisplayName("정상적인 GET 요청을 테스트한다.")
    @Test
    void parse_get_request() {
        final RequestLine requestLine = new RequestLine("GET /users HTTP/1.1");
        assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.GET);
        assertThat(requestLine.getPath()).isEqualTo("/users");
        assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
        assertThat(requestLine.getVersion()).isEqualTo("1.1");
    }

    @DisplayName("정상적인 POST 요청을 테스트한다.")
    @Test
    void parse_post_request() {
        final RequestLine requestLine = new RequestLine("POST /users HTTP/1.1");
        assertThat(requestLine.getMethod()).isEqualTo(HttpMethod.POST);
        assertThat(requestLine.getPath()).isEqualTo("/users");
        assertThat(requestLine.getProtocol()).isEqualTo("HTTP");
        assertThat(requestLine.getVersion()).isEqualTo("1.1");
    }

    @DisplayName("지원하지 않는 method가 request line에 들어오면 UnsupportedHttpMethodException이 발생한다.")
    @Test
    void given_non_supported_method_invokes_parse_then_throws_UnsupportedHttpMethodException() {
        assertThatThrownBy(() -> {
            new RequestLine("TENGO_MIEDO /users HTTP/1.1");
        }).isInstanceOf(UnsupportedHttpMethodException.class);
    }

    @DisplayName("같은 path라면 생성되는 QueryString 오브젝트도 동일해야한다")
    @Test
    void test_two_query_string_objects_are_identical() {
        final RequestLine requestLine = new RequestLine("GET /users?userId=hyeyoom&password=1234abcd&name=Chiho HTTP/1.1");
        final QueryString queryStringFromRequestLine = requestLine.getQueryString();
        final QueryString queryString = HttpRequestParser.parseQueryString("/users?userId=hyeyoom&password=1234abcd&name=Chiho");
        assertThat(queryStringFromRequestLine).isEqualTo(queryString);
    }
}