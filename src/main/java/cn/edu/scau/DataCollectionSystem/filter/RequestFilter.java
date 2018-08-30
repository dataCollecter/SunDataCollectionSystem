package cn.edu.scau.DataCollectionSystem.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestFilter implements Filter {

    private static Logger log = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info("Time - " + time.format(LocalDateTime.now()));
        log.info("IP - " + getRemoteAddress(request));
        log.info("URL - " + request.getRequestURL());
//        log.info("Method - " + request.getMethod());

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getRemoteAddress(HttpServletRequest request) {
        String address = request.getHeader("X-Forwarded-For");
        if (!canGetAddress(address))
            address = request.getHeader("Proxy-Client-IP");
            if (!canGetAddress(address))
                address = request.getHeader("WL-Proxy-Client-IP");
                if (!canGetAddress(address))
                    address = request.getRemoteAddr();
                    if (!canGetAddress(address))
                        address = request.getHeader("X-Real-IP");

        if (canGetAddress(address)) {
            int index = address.indexOf(',');
            return index == -1 ? address : address.substring(0, index);
        }
        return "";
    }

    private boolean canGetAddress(String address) {
        return !StringUtils.isEmpty(address) && address.trim().length() > 0 && !address.equals("unknown");
    }

    @Override
    public void destroy() {

    }
}
