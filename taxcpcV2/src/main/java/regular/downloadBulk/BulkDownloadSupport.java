/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.downloadBulk;

import dao.DbProc.ProcLhssysProcessLogIud;
import java.util.Date;

/**
 *
 * @author gaurav.khanzode
 */
public class BulkDownloadSupport {

    public String generateTokenForBulkDownload(String entity_code, String client_code, String acc_year, String quarter_no, Date from_date,
            Date to_date, String tds_type_code, Long l_client_loginid_seq, String user_code) {

        String tokenResult = "";
        try {
            ProcLhssysProcessLogIud proc_log = new ProcLhssysProcessLogIud();
            tokenResult = proc_log.executeProcedure(
                    null,// Process seq no
                    entity_code, //entity code
                    client_code, //client code
                    acc_year, //acc year
                    quarter_no, //quarter no
                    from_date, to_date, // from date, to date
                    tds_type_code, //tds type code
                    l_client_loginid_seq, // client login seq
                    null,
                    null, null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "PROCESS_DOWNLOAD", // Process type
                    user_code, //User code
                    "PROCESS_INSERT", //IUD type
                    null, // Template code
                    null, "", "",""); // Process type
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tokenResult;
    }//end method
}
