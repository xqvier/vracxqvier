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
 * Wrapper d'une réponse HTTP pour permettre la surcharge de celle-ci.
 * 
 * @author Xavier Mourgues
 * 
 */
public class ResponseWrapper extends HttpServletResponseWrapper {

    private StringWriter stringWriter;

    /**
     * Constructeur utilisant la réponse HTTP pour la "wrap"
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
