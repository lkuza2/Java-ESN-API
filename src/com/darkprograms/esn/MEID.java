package com.darkprograms.esn;

import com.darkprograms.esn.checkesn.network.NetworkUtil;
import com.darkprograms.esn.checkesn.props.ESNProperties;

/**
 * MEID Class containing methods to convert MEID types and get MEID properties
 * @author Luke Kuza
 */
public class MEID extends ESNObject {

    /**
     * Private MEID variable to hold MEID
     */
    private String MEID;
    /**
     * Private Carrier variable to hold carrier string for online requests
     */
    private String carrier;

    /**
     * Constructs an MEID with given parameters
     *
     * @param MEID    The MEID (DEC or HEX) to construct with
     * @param carrier The Carrier this MEID is expected to be tied to. Ex. Carrier.SPRINT <br>
     *                This can be null as it is only used to make requests to CheckESNFree.com . Use null if not being used
     * @throws Exception Throws exception if MEID is not an MEID
     */
    public MEID(String MEID, String carrier) throws Exception {
        if (!isMEID(MEID)) {
            throw new Exception("Not a valid MEID DEC/HEX");
        }
        setMEID(MEID);
        setCarrier(carrier);
    }

    /**
     * Checks if the given MEID is indeed an MEID, whether it be Hex or Dec
     * @param MEID Input DEC/HEX MEID
     * @return True if MEID, false otherwise
     */
    private boolean isMEID(String MEID) {
        return (MEID.length() == 18 || MEID.length() == 14);
    }

    /**
     * Sets the MEID of this class
     * @param MEID Input MEID
     */
    private void setMEID(String MEID) {
        this.MEID = MEID;
    }

    /**
     * Gets MEID of this MEID object
     * @return String of the MEID, can either be DEC or HEX, use getType to determine
     */
    public String getMEID() {
        return MEID;
    }

    /**
     * Gets the carrier this MEID is tied to
     * @param carrier Constant carrier string.  Ex. Carrier.SPRINT
     */
    private void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    /**
     * Gets the carrier tied to this MEID
     * @return Returns the string carrier, false if there is none
     */
    public String getCarrier() {
        return carrier;
    }

    /**
     * Returns the MEID type
     * @return DEC if decimal, HEX if hex
     */
    public String getMEIDType() {
        if (getMEID().length() == 18)
            return "DEC";
        else
            return "HEX";
    }

    /**
     * Calculates the MEID DEC of the MEID.  If this class's MEID is not DEC, it will convert it (all offline)
     * @return The MEID in DEC String Format
     */
    public String getMEIDDEC() {
        if (getMEID().length() == 18) {
            return getMEID();
        } else {
            String firstDEC = Long.toString(Long.parseLong(getStringForLength(getMEID(), 0, 8), 16));
            String secondDEC = Integer.toString(Integer.parseInt(getStringForLength(getMEID(), 8, 6), 16));
            return firstDEC + "0" + secondDEC;
        }
    }

    /**
     * Calculates the MEID HEX of the MEID.  If this class's MEID is not HEX, it will convert it (all offline)
     * @return The MEID in HEX String Format
     */
    public String getMEIDHEX() {
        if (getMEID().length() == 14) {
            return getMEID();
        } else {
            long firstHEX = Long.parseLong(getStringForLength(getMEID(), 0, 10));
            int secondHEX = Integer.parseInt(getStringForLength(getMEID(), 10, 8));
            return (Long.toHexString(firstHEX) + Integer.toHexString(secondHEX)).toUpperCase();
        }
    }

    /**
     * Calculates (P)ESN (DEC and HEX) for this MEID.  Returned object contains furthur methods
     * @return Returns ESN object
     */
    public ESN getESN() {
        return new ESN(getMEIDHEX());
    }

    /**
     * This method will go online to checkesnfree.com and parse the response<br>
     * It will return ESNProperties object containing if the ESN is clean and it's status (ex. lost/stolen)<br>
     * This is the only method that goes online, all others perform offline functions to calculate data
     * @return Returns ESNProperties Object with information about the ESN for the Carrier tied to this MEID
     * @throws Exception Throws an exception if a connection can not be reached or data can not be parsed
     */
    public ESNProperties getESNProperties() throws Exception {
        NetworkUtil networkUtil = new NetworkUtil();

        String[] parsedResponse = networkUtil.checkESNRequest(getMEIDHEX(), getCarrier()).split(":");

        return new ESNProperties(Boolean.parseBoolean(parsedResponse[1]), parsedResponse[3]);
    }


}
