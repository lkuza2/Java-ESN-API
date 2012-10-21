package com.darkprograms.esn.checkesn.network;

import com.darkprograms.esn.checkesn.constants.CheckESNConstants;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Class that performs all network related checkesnfree.com tasks
 *
 * @author Luke Kuza
 */
public class NetworkUtil {

    /**
     * Makes a checkesnfree.com request for a given MEID and carrier
     *
     * @param MEID    MEID of the ESN you wish to check (DEC/HEX, preferred HEX)
     * @param carrier The carrier constant tied to the MEID ex. sprint or Carrier.SPRINT
     * @return Returns parsed data in format clean:true|false:status:phone stolen
     * @throws Exception Throws exception if network can not be reached or if there is a parsing error
     */
    public String checkESNRequest(String MEID, String carrier) throws Exception {
        try {
            URLConnection urlConnection = new URL(CheckESNConstants.CHECK_ESN_INDEX_URL).openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Referer", CheckESNConstants.CHECK_ESN_INDEX_URL);


            OutputStreamWriter outputStream = new OutputStreamWriter(urlConnection.getOutputStream());
            String request = URLEncoder.encode("CESN", "UTF-8") + "=" + URLEncoder.encode("true", "UTF-8");
            request += "&" + URLEncoder.encode("MODE", "UTF-8") + "=" + URLEncoder.encode(carrier, "UTF-8");
            request += "&" + URLEncoder.encode("MEID", "UTF-8") + "=" + URLEncoder.encode(MEID, "UTF-8");
            request += "&" + URLEncoder.encode("submitButton", "UTF-8") + "=" + URLEncoder.encode("Check ESN", "UTF-8");

            outputStream.write(request);
            outputStream.flush();


            String response = parseResponse(urlConnection.getInputStream());

            outputStream.close();

            return response;

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Parse the checkesnfree.com response after writing to it
     *
     * @param inputStream InputStream of connection
     * @return Returns fully parsed data from InputStream. Format =  clean:true|false:status:phone stolen
     * @throws Exception Exception if connection can not be made or if there was a parsing error
     */
    private String parseResponse(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while (!(line = bufferedReader.readLine()).trim().contains(CheckESNConstants.CHECK_ESN_RESPONSE)) {
            //Wait until the line with data we need
        }
        //Skip a line
        //bufferedReader.readLine();
        // line = bufferedReader.readLine();
        bufferedReader.close();
        return "clean:" + !line.contains(CheckESNConstants.CHECK_ESN_BAD_ESN) + ":status:" + line.split(CheckESNConstants.CHECK_ESN_PARSE_SPLIT)[2]
                .replace(CheckESNConstants.CHECK_ESN_PARSE_STATUS_REMOVAL, "").trim();
    }

}
