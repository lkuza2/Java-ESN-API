package com.darkprograms.esn.checkesn.constants;

/**
 * Constants for checkesnfree.com
 * @author Luke Kuza
 */
public interface CheckESNConstants {

    /**
     * Main Index page to perform requests
     */
    public static final String CHECK_ESN_INDEX_URL = "http://checkesnfree.com/index.html";
    /**
     * Used to figure out where the response is coming from in HTML
     */
    public static final String CHECK_ESN_RESPONSE = "<div class=\"cesnreply\">";
    /**
     * What is returned if ESN is BAD
     */
    public static final String CHECK_ESN_BAD_ESN = "color=\"red\"";
    /**
     * What to delete on the status text returned from HTML
     */
    public static final String CHECK_ESN_PARSE_STATUS = "<br /";

}
