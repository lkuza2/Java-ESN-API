package com.darkprograms.esn;

/**
 * All ESN/MEID objects extend off of this Class
 * @author Luke Kuza
 */
public class ESNObject {

    /**
     * Constructor
     */
    protected ESNObject() {

    }

    /**
     * Takes an input string, starts at offset, and returns a string from offset to length from the input
     *
     * @param inputString Input string
     * @param offset      offset
     * @param length      Length to read into input string
     * @return New String from legnth
     */
    protected String getStringForLength(String inputString, int offset, int length) {
        StringBuffer buffer = new StringBuffer();

        for (int i = 0; i < length; i++) {
            buffer.append(inputString.charAt(i + offset));
        }
        return buffer.toString();
    }

}
