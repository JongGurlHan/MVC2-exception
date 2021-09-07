package hello.exception;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//기본 오류페이지를 커스텀
@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        //Http상태 코드 오류 - 404에러 발생시 /error-page/400 컨트롤러 호출
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");

        //Http상태 코드 오류 - 500에러
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        //Exception 발생, RuntimeException 뿐만 아니라 자식 예외도 포괄한다.
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        //등록
        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);

    }
}
