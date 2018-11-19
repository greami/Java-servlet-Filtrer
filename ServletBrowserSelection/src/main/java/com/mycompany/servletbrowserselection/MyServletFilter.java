/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servletbrowserselection;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author AMITH GRECA
 * 
 * THIS IS A WEBFILTER
 * 
 *  THE /* IN URL PATTERN MEANS THA ANY PARAMETER LINK TO THIS FILTER
 */
@WebFilter(filterName = "MyServletFilter", urlPatterns = {"/*"})
public class MyServletFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       //MUST BE OVERRIDED
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        /*
        CAST IS POSSIBILE from SErvletRequest to httpServletRequest
        */
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        /*
        WE READ IN THE HEADER OF THE REQUEST THE NAME OF THE USER-AGENT (BROWSER),
        THEN WE PASS THE REQUEST TO THE ASSOCIATED SERVLET
        */
        if(req.getHeader("user-agent").contains("Chrome")){
            req.getRequestDispatcher("ServletForChrome").forward(req, res);
        } else
            
        if(req.getHeader("user-agent").contains("Safari")){
            req.getRequestDispatcher("ServletForSafari").forward(req, res);
        } else
        if(req.getHeader("user-agent").contains("Firefox")){
            req.getRequestDispatcher("ServletForFirefox").forward(req, res);
        }
        else 
        {
            //IN CASE WE DON'T KNOW THE USER AGENT WE RESPONT WITH A SIMPLY MESSAGE
            res.getWriter().write("Your browser is not Safari, Chrome or Firefox, "
                    + "there is no servlet for your browser sorry ");
        }
    }
    
    

    @Override
    public void destroy() {
        //MUST BE OVERRIDED
        
    }
    
    
    

}
