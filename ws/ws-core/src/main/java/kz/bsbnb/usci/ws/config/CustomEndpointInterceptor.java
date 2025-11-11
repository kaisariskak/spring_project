package kz.bsbnb.usci.ws.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE + 20)
public class CustomEndpointInterceptor extends OncePerRequestFilter { // ВАЖНО: extends (НЕ implements)

    private static final Logger log = LoggerFactory.getLogger(CustomEndpointInterceptor.class);
    private static final int MAX_BODY = 100_000; // 100 KB

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        // Логируем только наши REST-эндпоинты
        String uri = request.getRequestURI();
        // Логируем только /ws/** но исключаем swagger
        boolean skip = !uri.startsWith("/ws/")
                || uri.contains("swagger-ui")
                || uri.contains("api-docs");

        log.info("🔍 Will {}filter this request", skip ? "NOT " : "");
        return skip;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Оборачиваем, чтобы можно было прочитать тело после обработки контроллером
        ContentCachingRequestWrapper req = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper res = new ContentCachingResponseWrapper(response);

        long t0 = System.currentTimeMillis();
        try {
            filterChain.doFilter(req, res);
        } finally {
            String reqBody = bodyToString(req.getContentAsByteArray(),
                    request.getCharacterEncoding(), request.getContentType());
            String resBody = bodyToString(res.getContentAsByteArray(),
                    res.getCharacterEncoding(), res.getContentType());

            log.info("REST {} {} [{}] {} ms\nREQUEST:\n{}\nRESPONSE:\n{}\n",
                    request.getMethod(), request.getRequestURI(), res.getStatus(),
                    (System.currentTimeMillis() - t0), reqBody, resBody);

            // ОБЯЗАТЕЛЬНО вернуть тело клиенту
            res.copyBodyToResponse();
        }
    }

    private String bodyToString(byte[] buf, String enc, String contentType) {
        if (buf == null || buf.length == 0) return "";
        String ct = contentType == null ? "" : contentType.toLowerCase(Locale.ROOT);
        // Не логируем бинарники
        if (!(ct.contains("xml") || ct.contains("json") || ct.startsWith("text/")))
            return "[non-text body skipped]";

        Charset cs;
        try { cs = enc != null ? Charset.forName(enc) : StandardCharsets.UTF_8; }
        catch (Exception e) { cs = StandardCharsets.UTF_8; }

        int len = Math.min(buf.length, MAX_BODY);
        String s = new String(buf, 0, len, cs);
        if (buf.length > MAX_BODY) s += "\n... truncated " + (buf.length - MAX_BODY) + " bytes ...";

        // Маскируем чувствительные поля в XML (если попадутся)
        s = s.replaceAll("(?is)(<(userPass|userToken|signature)>)(.*?)(</\\2>)", "$1***$4");
        // Крупный <data> скрываем (обычно там много персональных данных)
        s = s.replaceAll("(?is)(<data>)(.*?)(</data>)", "$1...skipped...$3");
        return s;
    }


}