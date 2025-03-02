package com.codeCreacker.apigateway.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.codeCreacker.apigateway.constants.ApplicationConstants.WHITELIST_URLS;

@Component
public class RequestFilter extends OncePerRequestFilter {
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();

        // Extract the microservice name if applicable
        String microservicePrefix = extractMicroservicePrefix(requestURI);

        for (String pattern : WHITELIST_URLS) {
            String fullPattern = microservicePrefix + pattern;
            if (pathMatcher.match(fullPattern, requestURI)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        // If the URI does not match, you can apply further logic or block the request
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("Access denied");
    }

    /**
     * Extracts the microservice prefix from the request URI.
     * Assumes the format /<microservice-name>/rest/of/the/path.
     *
     * @param requestURI the URI of the request
     * @return the microservice prefix (e.g., "/microservice-name/") or an empty string if not found
     */
    private String extractMicroservicePrefix(String requestURI) {
        String[] parts = requestURI.split("/", 3); // Split into up to 3 parts
        if (parts.length >= 3) {
            return "/" + parts[1]; // Return the first part after the root slash
        }
        return ""; // Return empty if no prefix exists
    }
}


