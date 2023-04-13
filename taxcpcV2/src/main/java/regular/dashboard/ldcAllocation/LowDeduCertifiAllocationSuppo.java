package regular.dashboard.ldcAllocation;

import dao.generic.DbFunctionExecutorAsIntegar;
import static dao.generic.HibernateUtil.getSessionFactory;
import globalUtilities.Util;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

/**
 *
 * @author aniket.naik
 */
public class LowDeduCertifiAllocationSuppo {

    ArrayList<LowDeduCertifiAllocaBean> lowDeduCertifiAllocaBean_List = new ArrayList<LowDeduCertifiAllocaBean>();

    private String getLDCertificationAllocationDataQuery(String entity_code, String client_code, String acc_year, String tds_type_code, String filter_whrClause) {
        StringBuilder sb = new StringBuilder();

        try {

            sb.append("SELECT FROM_DATE,\n"
                    + "       TO_DATE,\n"
                    + "       TDS_CODE,\n"
                    + "       TDS_SECTION,\n"
                    + "       TDS_RATE,\n"
                    + "       CERTIFICATE_NO,\n"
                    + "       TDS_LIMIT_AMT,\n"
                    + "       AMOUNT_CONSUMED,\n"
                    + "       (NVL(TDS_LIMIT_AMT, 0) - NVL(AMOUNT_CONSUMED, 0)) BAL_LIMIT,\n"
                    + "       CERTIFICATE_VALID_UPTO,\n"
                    + "       CLIENT_CODE,\n"
                    + "       DEDUCTEE_PANNO,\n"
                    + "       ENTITY_CODE,\n"
                    + "       ACC_YEAR,\n"
                    + "       TDS_TYPE_CODE\n"
                    + "  FROM (SELECT FROM_DATE,\n"
                    + "               TO_DATE,\n"
                    + "               TDS_CODE,\n"
                    + "               lhs_utility.get_name('TDS_CODE',TDS_CODE) TDS_SECTION,\n"
                    + "               TDS_RATE,\n"
                    + "               TDS_LIMIT_AMT,\n"
                    + "               CERTIFICATE_NO,\n"
                    + "               TDS_TRAN_ROWID_SEQ,\n"
                    + "               A.FLAG,\n"
                    + "               CERTIFICATE_VALID_UPTO,\n"
                    + "               A.CLIENT_CODE,\n"
                    + "               DEDUCTEE_PANNO,\n"
                    + "               A.ENTITY_CODE,\n"
                    + "               ACC_YEAR,\n"
                    + "               TDS_TYPE_CODE,\n"
                    //                    + "               (SELECT SUM(NVL(TDS_AMT, 0))\n"
                    + "               (SELECT SUM(NVL(TRAN_AMT, 0))\n"
                    + "                  FROM TDS_TRAN_LOAD B\n"
                    + "                 WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                    + "                   AND B.ACC_YEAR = A.ACC_YEAR\n"
                    + "                   AND B.TDS_TYPE_CODE = A.TDS_TYPE_CODE\n"
                    + "                   AND B.CERTIFICATE_NO = A.CERTIFICATE_NO) AMOUNT_CONSUMED\n"
                    + "          FROM TDS_SPL_RATE_MAST A, CLIENT_MAST CM\n"
                    + "         WHERE A.ENTITY_CODE = '" + entity_code + "'\n"
                    + "           AND A.ENTITY_CODE = CM.ENTITY_CODE\n"
                    + "           AND A.CLIENT_CODE = CM.CLIENT_CODE\n"
                    + "           AND (CM.CLIENT_CODE = '" + client_code + "' OR\n"
                    + "                 CM.PARENT_CODE = '" + client_code + "' OR\n"
                    + "                 CM.G_PARENT_CODE = '" + client_code + "' OR\n"
                    + "                 CM.SG_PARENT_CODE = '" + client_code + "' OR\n"
                    + "                 CM.SSG_PARENT_CODE = '" + client_code + "' OR\n"
                    + "                 CM.SSSG_PARENT_CODE = '" + client_code + "')\n"
                    + "           AND ACC_YEAR = '" + acc_year + "'\n"
                    + "           AND TDS_TYPE_CODE = '" + tds_type_code + "') t\n"
                    + " WHERE 1 = 1 \n");

            sb.append(filter_whrClause);

        } catch (Exception e) {
            sb.setLength(0);
            e.printStackTrace();
        }
        return sb.toString();
    }

    public long getTotalCount(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String filter_whrClause, String allocatedflag , Util utl) {
        long totalRecords = 0l;
        String query ;
        try {
            if(!utl.isnull(allocatedflag) && allocatedflag.equalsIgnoreCase("notAllocatedAFlag")){


                    query = "select  count(*)\n"
                        + "     from  tds_tran_load a, client_mast b, client_mast c\n"
                        + "     where b.entity_code=a.entity_code\n"
                        + "     and  b.client_code=a.client_code\n"
                        + "     and  c.entity_code=a.entity_code\n"
                        + "     and  c.client_code=a.validation_client_code\n"
                        + "     and a.entity_code= '" + entity_code + "'   \n"
                        + "     and (b.client_code= '" + client_code + "'  or\n"
                        + "          b.parent_code= '" + client_code + "'  or\n"
                        + "          b.g_parent_code= '" + client_code + "'  or\n"
                        + "          b.sg_parent_code= '" + client_code + "'  or\n"
                        + "          b.ssg_parent_code= '" + client_code + "'  or\n"
                        + "          b.sssg_parent_code= '" + client_code + "' \n"
                        + "          )                        \n"
                        + "    and  a.acc_year= '" + acc_year + "' \n"
                        + "     and  a.quarter_no= '" + quarter_no + "'\n"
                        + "     and  a.tds_type_code= '" + tds_type_code + "'     \n"
                        + "     and  nvl(to_number(a.tds_amt),0)=0\n"
                        + "     and  nvl(a.tds_deduct_reason,'#')<>'A'\n"
                        + "     and exists (select 1\n"
                        + "                    from   tds_spl_rate_mast c\n"
                        + "                    where  c.entity_code=a.entity_code\n"
                        + "                    and    c.acc_year=a.acc_year\n"
                        + "                    and    c.tds_type_code=a.tds_type_code\n"
                        + "                    and    c.deductee_panno=a.deductee_panno\n"
                        + "                    and    c.tds_code=a.tds_code\n"
                        + "                    and    c.client_code<>a.validation_client_code\n"
                        + "                    )";

               
            }else{
           query = "\n select count(*) from (\n" + getLDCertificationAllocationDataQuery(entity_code, client_code, acc_year, tds_type_code, filter_whrClause) + "\n)\n";
            }System.out.println("query count---" + query);
            totalRecords = new DbFunctionExecutorAsIntegar().execute_oracle_function_as_integar(query);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalRecords;
    }

    public String LowDeduCertifiAllocaDownloadQuery(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String allocatedFlag) {

        String allocatedWhereClause = "";
        if (allocatedFlag.equalsIgnoreCase("notAllocated")) {
            allocatedWhereClause = "--Not allocated\n ";
            allocatedWhereClause += " and a.certificate_no is null\n ";

        } else if (allocatedFlag.equalsIgnoreCase("allocated")) {
            allocatedWhereClause = "--Allocated\n ";
            allocatedWhereClause += "and a.certificate_no is not null\n ";

        }

        String returnString = "select bank_branch_code,\n"
                + "       deductee_panno,\n"
                + "       deductee_name,\n"
                + "       total_Records,\n"
                + "       tran_amt,\n"
                + "       tds_section,\n"
                + "       certificate_no,\n"
                + "       tds_limit_amt,\n"
                + "       from_date\n"
                + "  from (select a.entity_code,\n"
                + "               a.client_code,\n"
                + "               a.bank_branch_code,\n"
                + "               a.acc_year,\n"
                + "               a.quarter_no,\n"
                + "               a.tds_type_code,\n"
                + "               a.deductee_panno,\n"
                + "               a.tds_code,\n"
                + "               a.tds_section,\n"
                + "               min(a.deductee_name) deductee_name,\n"
                + "               count(*) total_Records,\n"
                + "               sum(nvl(tran_amt, 0)) tran_amt,\n"
                + "               a.certificate_no,\n"
                + "               a.tds_limit_amt, a.from_date\n"
                + "          from (select a.entity_code,\n"
                + "                       a.client_code,\n"
                + "                       b.bank_branch_code,\n"
                + "                       a.acc_year,\n"
                + "                       a.quarter_no,\n"
                + "                       a.tds_type_code,\n"
                + "                       a.deductee_panno,\n"
                + "                       a.deductee_name,\n"
                + "                       a.tds_code,\n"
                + "                       lhs_utility.get_name('tds_code', a.tds_code) tds_section,\n"
                + "                       a.certificate_no,\n"
                + "                       nvl(to_number(a.tran_amt), 0) tran_amt,\n"
                + "                       \n"
                + "                       (select\n"
                + "                        \n"
                + "                         c.tds_limit_amt\n"
                + "                          from tds_spl_rate_mast c\n"
                + "                         where c.entity_code = a.entity_code\n"
                + "                           and c.client_code = a.entity_code\n"
                + "                           and c.deductee_panno = a.deductee_panno\n"
                + "                           and c.acc_year = a.acc_year\n"
                + "                           and c.tds_code = a.tds_code\n"
                + "                        \n"
                + "                        ) tds_limit_amt,\n"
                + "                       (select\n"
                + "                        \n"
                + "                         c.from_date\n"
                + "                          from tds_spl_rate_mast c\n"
                + "                         where c.entity_code = a.entity_code\n"
                + "                           and c.client_code = a.entity_code\n"
                + "                           and c.deductee_panno = a.deductee_panno\n"
                + "                           and c.acc_year = a.acc_year\n"
                + "                           and c.tds_code = a.tds_code\n"
                + "                        \n"
                + "                        ) from_date\n"
                + "                  from tds_tran_load a, client_mast b\n"
                + "         where a.entity_code = '" + entity_code + "'\n"
                + "           and b.entity_code = a.entity_code\n"
                + "           and b.client_code = a.client_code\n"
                + "           and (b.client_code = '" + client_code + "' or b.parent_code = '" + client_code + "' or\n"
                + "               b.g_parent_code = '" + client_code + "' or b.sg_parent_code = '" + client_code + "' or\n"
                + "               b.ssg_parent_code = '" + client_code + "' or b.sssg_parent_code = '" + client_code + "')\n"
                + "           and a.acc_year = '" + acc_year + "'\n"
                + "           and a.quarter_no = " + quarter_no + "\n"
                + "           and a.tds_type_code = '" + tds_type_code + "'\n"
                + "          and a.tds_deduct_Reason = 'A'\n"
                + allocatedWhereClause
                + "        ) a"
                + "         group by a.entity_code,\n"
                + "                  a.client_code,\n"
                + "                  a.bank_branch_code,\n"
                + "                  a.acc_year,\n"
                + "                  a.quarter_no,\n"
                + "                  a.tds_type_code,\n"
                + "                  a.deductee_panno,\n"
                + "                  a.certificate_no,\n"
                + "                  a.tds_code,\n"
                + "                  a.tds_section,\n"
                + "                a.tds_limit_amt,\n"
                + "                a.from_date\n"
                + "                  )";

        return returnString;

    }

    public ArrayList<LowDeduCertifiAllocaBean> getLowDeduCertifiAllocaBean_List(String entity_code, String client_code, String acc_year, String quarter_no, String tds_type_code, String filter_whrClause, Util utl, int recordsPerPage, int pageNumber, String allocationflag) {
        ArrayList<LowDeduCertifiAllocaBean> entityList = null;
        try {
             String query ;
            if(!utl.isnull(allocationflag) && allocationflag.equalsIgnoreCase("notAllocatedAFlag")){


                       query = "select  a.rowid_seq,\n"
                        + "         b.bank_branch_code,\n"
                        + "         c.bank_branch_code challan_bank_branch_code, \n"
                        + "         a.deductee_panno, \n"
                        + "         a.deductee_name, \n"
                        + "         a.deductee_ref_code1, \n"
                        + "         a.tran_ref_no, \n"
                        + "         a.tran_ref_date, \n"
                        + "         a.tran_amt, \n"
                        + "         a.tds_amt, \n"
                        + "         a.tds_section, \n"
                        + "         a.tds_deduct_reason, \n"
                        + "         a.certificate_no \n"
                        + "     from  tds_tran_load a, client_mast b, client_mast c\n"
                        + "     where b.entity_code=a.entity_code\n"
                        + "     and  b.client_code=a.client_code\n"
                        + "     and  c.entity_code=a.entity_code\n"
                        + "     and  c.client_code=a.validation_client_code\n"
                        + "     and a.entity_code= '" + entity_code + "'   \n"
                        + "     and (b.client_code= '" + client_code + "'  or\n"
                        + "          b.parent_code= '" + client_code + "'  or\n"
                        + "          b.g_parent_code= '" + client_code + "'  or\n"
                        + "          b.sg_parent_code= '" + client_code + "'  or\n"
                        + "          b.ssg_parent_code= '" + client_code + "'  or\n"
                        + "          b.sssg_parent_code= '" + client_code + "' \n"
                        + "          )                        \n"
                        + "    and  a.acc_year= '" + acc_year + "' \n"
                        + "     and  a.quarter_no= '" + quarter_no + "'\n"
                        + "     and  a.tds_type_code= '" + tds_type_code + "'     \n"
                        + "     and  nvl(to_number(a.tds_amt),0)=0\n"
                        + "     and  nvl(a.tds_deduct_reason,'#')<>'A'\n"
                        + "     and exists (select 1\n"
                        + "                    from   tds_spl_rate_mast c\n"
                        + "                    where  c.entity_code=a.entity_code\n"
                        + "                    and    c.acc_year=a.acc_year\n"
                        + "                    and    c.tds_type_code=a.tds_type_code\n"
                        + "                    and    c.deductee_panno=a.deductee_panno\n"
                        + "                    and    c.tds_code=a.tds_code\n"
                        + "                    and    c.client_code<>a.validation_client_code\n"
                        + "                    )";


            }else{
             query = "select * from (select  t.*,  ceil(rownum/" + recordsPerPage + ") page_no from(  \n " + getLDCertificationAllocationDataQuery(entity_code, client_code, acc_year, tds_type_code, filter_whrClause) + " \n ) t )where page_no = " + pageNumber + "\n";
            }System.out.println("query---" + query);
            entityList = executeLowDeduCertifiAlloca_Query(query, utl);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return entityList;
    }//End Method

    private ArrayList<LowDeduCertifiAllocaBean> executeLowDeduCertifiAlloca_Query(final String l_query, final Util utl) {

        Session ssn = getSessionFactory().openSession();
        try {
            Work work = new Work() {
                @Override
                public void execute(Connection cnctn) throws SQLException {
                    PreparedStatement pstmt = null;
                    ResultSet rs = null;
                    try {
                        pstmt = cnctn.prepareStatement(l_query);
                        rs = pstmt.executeQuery();
                        while (rs.next()) {
                            boolean record_fetched = true;
                            LowDeduCertifiAllocaBean result_data = new LowDeduCertifiAllocaBean();
                            try {
                                Field[] tim_tbl_flds = result_data.getClass().getDeclaredFields();
                                for (Field fld : tim_tbl_flds) {
                                    String fld_name = fld.getName();
                                    String fld_value = "";
                                    try {
                                        fld_value = rs.getString(fld_name);
                                    } catch (SQLException e) {
//                                        System.out.println("LD Field error: " + e.getMessage());
                                    }
                                    fld_value = utl.isnull(fld_value) ? "" : fld_value;
                                    fld.set(result_data, fld_value);
                                }//end for
                            } catch (SecurityException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                record_fetched = false;
                                e.printStackTrace();
                            }
                            if (record_fetched) {
                                lowDeduCertifiAllocaBean_List.add(result_data);
                            }
                        }//end while
                    } catch (SQLException ex) {
                    } finally {
                        if (pstmt != null) {
                            pstmt.close();
                        }
                        if (rs != null) {
                            rs.close();
                        }
                    }
                }
            };
            ssn.doWork(work);
        } catch (Exception e) {
            lowDeduCertifiAllocaBean_List = null;
        } finally {
            ssn.close();
        }
        return lowDeduCertifiAllocaBean_List;

    }//end method

}


//End Class
