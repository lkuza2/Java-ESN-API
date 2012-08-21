Java-ESN-API
============

A Java ESN/MEID API used to convert MEID from different formats (DEC to HEX/HEX to DEC) and convert MEID to ESN.  Also can convert ESN to different formats (DEC to HEX/HEX to DEC). All calculations are done with Java offline. API also has an extra module to check if a given MEID/ESN is clean or dirty for a specific Carrier.
The Extra module that checks whether an ESN is clean requests checkesnfree.com for information. This is the only part of the API that uses network access.
Data is retrieved via Screen Scraping methods