package ${packageName};

import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "XssFilter", urlPatterns = {"/*"})
@Component
public class XssFilter implements Filter {
    //声明要被忽略请求的数组
    private final String[] exclusionsUrls = {".js", ".gif", ".jpg", ".jpeg", ".png", ".css", ".ico", "health"};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        //获取请求的url路径
        String path = request.getServletPath();

        //请求参数不进行xss处理
        for (String str : exclusionsUrls) {
            if (path.contains(str)) {
                filterChain.doFilter(request, response);
                return;
            }
        }
        //对请求参数xss处理
        XssHttpServletRequestWrapper XssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(request);
        filterChain.doFilter(XssHttpServletRequestWrapper, response);
    }

    @Override
    public void destroy() {}


    /**
     * 对HttpServletRequest进行重写
     */
    public static class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
        private final HttpServletRequest request;

        public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            this.request = request;
        }

        @Override
        public String getParameter(String name) {
            String value = request.getParameter(name);
            if (!ObjectUtils.isEmpty(value)) {
                value = StringEscapeUtils.escapeHtml4(value);
            }
            return value;
        }

        @Override
        public String[] getParameterValues(String name) {
            String[] parameterValues = super.getParameterValues(name);
            if (parameterValues == null) {
                return null;
            }
            for (int i = 0; i < parameterValues.length; i++) {
                String value = parameterValues[i];
                parameterValues[i] = StringEscapeUtils.escapeHtml4(value);
            }
            return parameterValues;
        }
    }
}