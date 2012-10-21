package com.darkprograms.esn;

import ae.javax.xml.bind.DatatypeConverter;

import java.security.MessageDigest;

/**
 * ESN class containing methods to convert ESN data (DEC/HEX)
 *
 * @author Luke Kuza
 */
public class ESN extends ESNObject {


    /**
     * Private Constructor
     */
    private ESN() {

    }

    /**
     * Contains MEID HEX so that this class can conver it to an ESN
     */
    private String MEIDHEX;

    /**
     * Constructor for ESN Object
     *
     * @param MEIDHEX The MEID hex to want to convert to an ESN
     */
    protected ESN(String MEIDHEX) {
        setMEIDHEX(MEIDHEX);
    }

    /**
     * @param MEIDHEX
     */
    private void setMEIDHEX(String MEIDHEX) {
        this.MEIDHEX = MEIDHEX;
    }

    /**
     * Gets the MEID HEX that this ESN object is using to convert
     *
     * @return MEID HEX String
     */
    private String getMEIDHEX() {
        return MEIDHEX;
    }

    /**
     * Calculates the HEX representation of the ESN
     *
     * @return String HEX representation
     */
    public String getESNHEX() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            byte[] bytes = messageDigest.digest(DatatypeConverter.parseHexBinary(getMEIDHEX()));
            return "80" + getStringForLength(DatatypeConverter.printHexBinary(bytes), 34, 6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Calculates the DEC representation of the ESN
     *
     * @return String DEC representation
     */
    public String getESNDEC() {
        String firstByte = Integer.toString(Integer.parseInt(getStringForLength(getESNHEX(), 0, 2), 16));
        String serial = Integer.toString(Integer.parseInt(getStringForLength(getESNHEX(), 2, 6), 16));

        if (firstByte.length() == 2) {
            firstByte = "0" + firstByte;
        }
        if (serial.length() < 8) {
            serial = "0" + serial;
        }

        return firstByte + serial;
    }

}
