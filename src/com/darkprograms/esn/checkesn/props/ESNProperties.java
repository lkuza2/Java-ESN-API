package com.darkprograms.esn.checkesn.props;

/**
 * ESN Properties class containing ESN Properties retrieved from checkesnfree.com (Clean, status)
 */
public class ESNProperties {

    /**
     * Private variable holding ESN clean status
     */
    private boolean clean;
    /**
     * Private variable holding textual status
     */
    private String status;


    /**
     * Constructor for an ESNProperties object
     * @param clean True if ESN is clean, false otherwise
     * @param status Textual Status.  Ex. Phone is stolen
     */
    public ESNProperties(boolean clean, String status) {
        setClean(clean);
        setStatus(status);
    }

    /**
     * Sets the ESN clean status
     * @param clean True for clean, false otherwise
     */
    private void setClean(boolean clean) {
        this.clean = clean;
    }

    /**
     * Returns ESN Clean status
     * @return True if ESN is clean, false otherwise
     */
    public boolean isESNClean() {
        return clean;
    }

    /**
     * Sets the textual status
     * @param status Textual Status to set
     */
    private void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the ESN textual status ex. "Phone was stolen."
     * @return Returns textual status of ESN
     */
    public String getESNStatus() {
        return status;
    }

}
