/*
 * ResponseWrapper.java                                    25 nov. 2013 
 * 3iL3 DOO-GL 2013-2014
 */
package com.xqvier.webmarket.web.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * TODO comment class responsabilities
 * 
 * @author Administrateur
 * 
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private StringWriter stringWriter;

    /**
     * TODO comment initialization state
     * 
     * @param response
     *            Réponse Http
     */
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
        stringWriter = new StringWriter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.ServletResponseWrapper#getWriter()
     */
    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(stringWriter);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return stringWriter.toString();
    }

}
