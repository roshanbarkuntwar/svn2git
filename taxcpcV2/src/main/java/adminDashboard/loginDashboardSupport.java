package adminDashboard;

import globalUtilities.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author akash.meshram
 */
public class loginDashboardSupport {

    String entityWiseBranchCountQuery(String bankBranchCode, Util utl) {

        String query = "SELECT COUNT(1) FROM (\n"
                + "SELECT count(*) no_of_login_count\n"
                + "  from (select v.entity_code,\n"
                + "               v.client_code,\n"
                + "               v.login_time,\n"
                + "               b.client_name,\n"
                + "               b.bank_branch_code,\n"
                + "               count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "          from lhssys_client_login_tran v,client_mast b\n"
                + "          WHERE b.client_code = v.client_code\n"
                + "          AND  b.entity_code = b.entity_code ";

        query = query.concat("group by v.client_code, v.login_time,v.entity_code,b.client_name,b.bank_branch_code) a ");

        if (!utl.isnull(bankBranchCode)) {

            query = query.concat("WHERE a.bank_branch_code ='" + bankBranchCode + "' ");
        }

        query = query.concat("group by a.client_code,a.client_name, a.entity_code,a.bank_branch_code)");

        return query;
    }

    String entityWiseBranchListQuery(int minVal, int maxVal, String bankBranchCode, Util utl) {

        String query = "select *\n"
                + "       from \n"
                + "       (select row_number() over (order by 1) SLNO,\n"
                + "               a.entity_code,\n"
                + "               a.client_code,    \n"
                + "               a.client_name,\n"
                + "               a.bank_branch_code,\n"
                + "               max(a.login_time) last_login_time,\n"
                + "               count(*) no_of_login_count               \n"
                + "  from (select v.entity_code,\n"
                + "               v.client_code,\n"
                + "               v.login_time,\n"
                + "               b.client_name,\n"
                + "               b.bank_branch_code,\n"
                + "               count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "          from lhssys_client_login_tran v,client_mast b\n"
                + "          WHERE b.client_code = v.client_code\n"
                + "          AND  b.entity_code = b.entity_code ";

        query = query.concat("group by v.client_code, v.login_time,v.entity_code,b.client_name,b.bank_branch_code) a ");
        if (!utl.isnull(bankBranchCode)) {
            query = query.concat("WHERE a.bank_branch_code ='" + bankBranchCode + "' ");
        }

        query = query.concat(" group by a.client_code,a.client_name, a.entity_code,a.bank_branch_code \n"
                + " )\n"
                + " where slno BETWEEN '" + minVal + "' AND '" + maxVal + "'");

        return query;
    }

    String entityWiseUserCountQuery(String userCode, String loginId, Util utl) {
        System.out.println("userCode--" + userCode);
        String L_query = "";
        String query = "select count(*) user_count from(select a.entity_code,\n"
                + "                       a.login_id,\n"
                + "                      a.user_code,\n"
                + "                      a.user_name,\n"
                + "                      max(a.login_time) last_login_time,\n"
                + "                      count(*) no_of_login_count\n"
                + "                 from (select v.user_code,\n"
                + "                               b.user_name,\n"
                + "                            b.login_id,\n"
                + "                          v.login_time,\n"
                + "                              v.entity_code,\n"
                + "                             count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "                        from lhssys_client_login_tran v,user_mast b WHERE b.user_code = v.user_code and v.entity_code = b.entity_code";

        query = query.concat(" group by v.user_code, v.login_time, v.entity_code,b.login_id,b.user_name) a ");
        //if (filterFlagR.equalsIgnoreCase("T")) {
        if ((!utl.isnull(userCode)) && (!utl.isnull(userCode))) {
            query = query.concat("WHERE a.user_code ='" + userCode + "' or a.login_id ='" + loginId + "'  ");
        } else if (!utl.isnull(loginId)) {
            query = query.concat("WHERE a.login_id ='" + loginId + "'  ");
        } else if (!utl.isnull(userCode)) {
            query = query.concat("WHERE a.user_code ='" + userCode + "'  ");
        }
        // }
        query = query.concat(" group by a.user_code,a.user_name, a.entity_code,a.login_id)");

        return query;
    }

    String entityWiseUserListQuery(int minVal, int maxVal, String userCode, String loginId, Util utl) {

        String query = "SELECT * FROM (select row_number() over (order by 1) SLNO,\n"
                + "                      a.entity_code,\n"
                + "                      a.login_id,\n"
                + "                      a.user_code,\n"
                + "                      a.user_name,\n"
                + "                      max(a.login_time) last_login_time,\n"
                + "                      count(*) no_of_login_count\n"
                + "                 from (select v.user_code,\n"
                + "                               b.user_name,\n"
                + "                             b.login_id,\n"
                + "                              v.login_time,\n"
                + "                               v.entity_code,\n"
                + "                             count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "                        from lhssys_client_login_tran v,user_mast b WHERE b.user_code = v.user_code and v.entity_code = b.entity_code";

        query = query.concat(" group by v.user_code, v.login_time, v.entity_code,b.login_id,b.user_name) a ");

        if (!utl.isnull(userCode) && !utl.isnull(userCode)) {
            query = query.concat("WHERE a.user_code ='" + userCode + "' or a.login_id ='" + loginId + "'  ");
        } else if (!utl.isnull(loginId)) {
            query = query.concat("WHERE a.login_id ='" + loginId + "'  ");
        } else if (!utl.isnull(userCode)) {
            query = query.concat("WHERE a.user_code ='" + userCode + "'  ");
        }
        query = query.concat(" group by a.user_code,a.user_name, a.entity_code,a.login_id )\n"
                + "  where 1=1 AND slno BETWEEN " + minVal + " AND " + maxVal + "");
        System.out.println("querrryyy--" + query);
        return query;
    }

    String clientViewCountQuery(String clientCode, String date, Util utl) {
        System.out.println("clientCodeclientCode-" + clientCode);

        String query = "select  count(*)\n"
                + "         from (select v.entity_code,\n"
                + "                      v.client_code,\n"
                + "                      v.login_time,\n"
                + "                      b.client_name,\n"
                + "                      b.bank_branch_code,\n"
                + "                      count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "          from lhssys_client_login_tran v,client_mast b\n"
                + "          where b.client_code = v.client_code\n"
                + "          and  b.entity_code = b.entity_code\n"
                + "          group by v.client_code, v.login_time,v.entity_code,b.client_name,b.bank_branch_code) a\n"
                + "      where a.client_code = '" + clientCode + "'";
        if (!utl.isnull(date)) {
            query = query.concat(" and  to_date(a.login_time) = to_date('"+date+"','DD-MM-RRRR')");
        }
        return query;
    }

    String clientCodeWiseViewListQuery(String client_code, String date, Util utl, int minVal, int maxVal) {

        String query = "select  slno, a.entity_code,\n"
                + "               a.client_code,    \n"
                + "               a.client_name,\n"
                + "               a.bank_branch_code,\n"
                + "               a.login_time\n"
                + "         from (select row_number() over (order by 1) slno, \n"
                + "                      v.entity_code,\n"
                + "                      v.client_code,\n"
                + "                      v.login_time,\n"
                + "                      b.client_name,\n"
                + "                      b.bank_branch_code,\n"
                + "                      count(v.login_time) over(partition by v.login_time) as a_login_time\n"
                + "          from lhssys_client_login_tran v,client_mast b\n"
                + "          where b.client_code = v.client_code\n"
                + "          and  b.entity_code = b.entity_code\n"
                + "          and  v.client_code = '" + client_code + "'";
        
        query = query.concat("group by v.client_code, v.login_time,v.entity_code,b.client_name,b.bank_branch_code) a     \n"
                + "      where slno between '" + minVal + "' and '" + maxVal + "'");
        if (!utl.isnull(date)) {
            query = query.concat(" and  to_date(a.login_time) = to_date('"+date+"','DD-MM-RRRR')");
        }

        return query;
    }

    String userViewCountQuery(String userCode, String date, Util utl) {

        String query = "select count(*)\n"
                + "                 from (select v.user_code,\n"
                + "                              b.user_name,\n"
                + "                              b.login_id,\n"
                + "                              v.entity_code,\n"
                + "                              v.login_time\n"
                + "                       from lhssys_client_login_tran v,user_mast b\n"
                + "                       where b.user_code = v.user_code\n"
                + "                       group by v.user_code, v.login_time, v.entity_code,b.login_id,b.user_name) a\n"
                + "                 where (a.user_code = '" + userCode + "')";

        if (!utl.isnull(date)) {
            query = query.concat(" and  to_date(a.login_time) = to_date('"+date+"','DD-MM-RRRR')");
        }
        return query;
    }

    String userCodeWiseViewListQuery(String user_code, String date, Util utl, int minVal, int maxVal) {

        String query = "select slno, a.entity_code,\n"
                + "       a.login_id,\n"
                + "       a.user_code,\n"
                + "       a.user_name,\n"
                + "       a.login_time\n"
                + "                 from (select row_number() over (order by 1) slno,\n"
                + "                              v.user_code,\n"
                + "                              b.user_name,\n"
                + "                              b.login_id,\n"
                + "                              v.entity_code,\n"
                + "                              v.login_time\n"
                + "                       from lhssys_client_login_tran v,user_mast b\n"
                + "                       where b.user_code = v.user_code\n"
                + "                       and v.user_code = '" + user_code + "'";
        
        query = query.concat(" group by v.user_code, v.login_time, v.entity_code,b.login_id,b.user_name) a\n"
                + "                 where slno between '" + minVal + "' and '" + maxVal + "'");
        if (!utl.isnull(date)) {
            query = query.concat(" and  to_date(a.login_time) = to_date('"+date+"','DD-MM-RRRR')");
        }

        return query;
    }

    String branchListDataGrid(ArrayList<ArrayList<String>> DataRecordList, Util utl,int minVal) {
        StringBuilder sb = new StringBuilder();
        System.out.println("DataRecordList" + DataRecordList.size());
        SimpleDateFormat olddateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newdateformate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            sb.append("<table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">"
                    + "<thead>"
                    + "<th class=\"text-center header\">Sr No.</th>         "
                    + "<th class=\"text-center header\">Client Code</th>  "
                    + "<th class=\"text-center header\">Client Name</th>  "
                    + "<th class=\"text-center header\">Bank Branch Code.</th>  "
                    + "<th class=\"text-center header\">Last Login</th>   "
                    + "<th class=\"text-center header\">Login Count</th>    "
                    + "<th class=\"text-center\">Action</th>         "
                    + "</thead><tbody>");
            for (ArrayList<String> arrayList : DataRecordList) {

                sb.append("<tr>");
                sb.append("<td class=\"text-center\">").append(minVal++).append("</td>");
                sb.append("<td >").append(arrayList.get(2)).append("</td>");
                sb.append("<td >").append(arrayList.get(3)).append("</td>");
                sb.append("<td >").append(arrayList.get(4)).append("</td>");
                Date d1 = olddateformate.parse(arrayList.get(5));
                sb.append("<td class=\"text-center\">").append(newdateformate.format(d1)).append("</td>");
                sb.append("<td class=\"text-right\" >").append(arrayList.get(6)).append("</td>");
                sb.append("<td class=\"text-center\">").append("<i onclick=\"viewClientDetails('" + arrayList.get(2) + "');\" class=\"fa view text-success cursor-pointer mr-1\" style=\"cursor: pointer;color: green;\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"View\"></i>").append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody></table>");
        } catch (Exception e) {
            sb.append("<div style=\"font-weight: bold;text-align: center;\">No Record Found</div>");
        }
        return sb.toString();
    }

    String userListDataGrid(ArrayList<ArrayList<String>> DataRecordList, Util utl,int minVal) {

        StringBuilder sb = new StringBuilder();
        System.out.println("DataRecordList" + DataRecordList.size());
        SimpleDateFormat olddateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newdateformate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            sb.append("<table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">"
                    + "<thead>"
                    + "<th class=\"text-center header\">Sr No.</th>         "
                    + "<th class=\"text-center header\">Login ID</th>  "
                    + "<th class=\"text-center header\">User Code</th>  "
                    + "<th class=\"text-center header\">User Name</th>  "
                    + "<th class=\"text-center header\">Last Login</th>   "
                    + "<th class=\"text-center header\">Login Count</th>    "
                    + "<th class=\"text-center\">Action</th>         "
                    + "</thead><tbody>");
            for (ArrayList<String> arrayList : DataRecordList) {

                sb.append("<tr>");
                sb.append("<td class=\"text-center\">").append(minVal++).append("</td>");
                sb.append("<td >").append(arrayList.get(2)).append("</td>");
                sb.append("<td >").append(arrayList.get(3)).append("</td>");
                sb.append("<td >").append(arrayList.get(4)).append("</td>");
                Date d1 = olddateformate.parse(arrayList.get(5));
                sb.append("<td class=\"text-center\">").append(newdateformate.format(d1)).append("</td>");
                sb.append("<td class=\"text-right\" >").append(arrayList.get(6)).append("</td>");
                sb.append("<td class=\"text-center\">").append("<i onclick=\"viewUserDetails('" + arrayList.get(3) + "');\" class=\"fa view text-success cursor-pointer mr-1\" style=\"cursor: pointer;color: green;\" aria-hidden=\"true\" data-toggle=\"tooltip\" data-placement=\"top\" title=\"View\"></i>").append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody></table>");
        } catch (Exception e) {
            sb.append("<div style=\"font-weight: bold;text-align: center;\">No Record Found</div>");
        }
        return sb.toString();
    }

    String clientViewListDataGrid(ArrayList<ArrayList<String>> DataRecordList, Util utl,int minVal) {
        StringBuilder sb = new StringBuilder();
        System.out.println("DataRecordList" + DataRecordList.size());
        SimpleDateFormat olddateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newdateformate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            sb.append("<table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">"
                    + "<thead>"
                    + "<th class=\"text-center header\">Sr No.</th>         "
                    + "<th class=\"text-center header\">Client Code</th>  "
                    + "<th class=\"text-center header\">Client Name</th>  "
                    + "<th class=\"text-center header\">Bank Branch Code.</th>  "
                    + "<th class=\"text-center header\">Login Date</th>   "
                    + "</thead><tbody>");
            for (ArrayList<String> arrayList : DataRecordList) {

                sb.append("<tr>");
                sb.append("<td class=\"text-center\">").append(minVal++).append("</td>");
                sb.append("<td >").append(arrayList.get(2)).append("</td>");
                sb.append("<td >").append(arrayList.get(3)).append("</td>");
                sb.append("<td >").append(arrayList.get(4)).append("</td>");
                Date d1 = olddateformate.parse(arrayList.get(5));
                sb.append("<td class=\"text-center\">").append(newdateformate.format(d1)).append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody></table>");

        } catch (Exception e) {
            sb.append("<div style=\"font-weight: bold;text-align: center;\">No Record Found</div>");
        }
        return sb.toString();
    }

    String userViewListDataGrid(ArrayList<ArrayList<String>> DataRecordList, Util utl,int minVal) {

        StringBuilder sb = new StringBuilder();
        System.out.println("DataRecordList" + DataRecordList.size());
        SimpleDateFormat olddateformate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat newdateformate = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        try {
            sb.append("<table id=\"table\" class=\"table table-bordered table-striped mb-1 tablesorter\">"
                    + "<thead>"
                    + "<th class=\"text-center header\">Sr No.</th>         "
                    + "<th class=\"text-center header\">Login ID</th>  "
                    + "<th class=\"text-center header\">User Code</th>  "
                    + "<th class=\"text-center header\">User Name</th>  "
                    + "<th class=\"text-center header\">Login Date</th>   "
                    + "</thead><tbody>");
            for (ArrayList<String> arrayList : DataRecordList) {

                sb.append("<tr>");
                sb.append("<td class=\"text-center\">").append(minVal++).append("</td>");
                sb.append("<td >").append(arrayList.get(2)).append("</td>");
                sb.append("<td >").append(arrayList.get(3)).append("</td>");
                sb.append("<td >").append(arrayList.get(4)).append("</td>");
                Date d1 = olddateformate.parse(arrayList.get(5));
                sb.append("<td class=\"text-center\">").append(newdateformate.format(d1)).append("</td>");
                sb.append("</tr>");
            }

            sb.append("</tbody></table>");
        } catch (Exception e) {
            sb.append("<div style=\"font-weight: bold;text-align: center;\">No Record Found</div>");
        }
        return sb.toString();
    }

}


