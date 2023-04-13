/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.salarySevaarthProjection;

import com.lhs.taxcpcv2.bean.Assessment;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author kapil.gupta
 */
public class SalarySevaarthProjectionDB {
    public String getSalarySevaarthProjectionCountQuery(ViewClientMast client, Assessment assment, SalarySevaarthProjectionFilterEntity filterEntity) {
        String whereclause = filterWhereClause(filterEntity);
        String query
                = "SELECT COUNT(*)\n"
                + "FROM (SELECT *\n"
                + "FROM SALARY_TRAN_ITP_PROV A,CLIENT_MAST B\n"
                + " WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                + "AND B.CLIENT_CODE = A.CLIENT_CODE\n"
                + " AND (B.CLIENT_CODE = '" + client.getClient_code() + "' OR B.PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "B.G_PARENT_CODE = '" + client.getClient_code() + "' OR B.SG_PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "B.SSG_PARENT_CODE = '" + client.getClient_code() + "' OR B.SSSG_PARENT_CODE = '" + client.getClient_code() + "')\n"
                + "AND B.ENTITY_CODE = '" + client.getEntity_code() + "'\n";

        query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
        query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
        query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";

        query += whereclause
                + ")";
        return query;

    }

    public String filterWhereClause(SalarySevaarthProjectionFilterEntity salSevaarthProjectionFltrSearch) {
        String whereClause = "";

        if (salSevaarthProjectionFltrSearch != null) {
            if (!utl.isnull(salSevaarthProjectionFltrSearch.getDdoNo())) {
                whereClause += " and A.DDO_NO = '" + salSevaarthProjectionFltrSearch.getDdoNo().trim() + "' \n";
            }
            if (!utl.isnull(salSevaarthProjectionFltrSearch.getBillId())) {
                whereClause += " and A.BILL_ID = '" + salSevaarthProjectionFltrSearch.getBillId().trim() + "' \n";
            }
            if (!utl.isnull(salSevaarthProjectionFltrSearch.getSevarthId())) {
                whereClause += " and A.SEVARTH_ID = '" + salSevaarthProjectionFltrSearch.getSevarthId() + "' ";
            }
            if (!utl.isnull(salSevaarthProjectionFltrSearch.getDeducteePanno())) {
                whereClause += " and A.DEDUCTEE_PANNO = '" + salSevaarthProjectionFltrSearch.getDeducteePanno() + "'";
            }
        }

        return whereClause;
    }

    public String getSalarySevaarthProjectionDataGridQuery(ViewClientMast client, Assessment assment, SalarySevaarthProjectionFilterEntity filterEntity, int min, int max) {
        String whereclause = filterWhereClause(filterEntity);
        String query
                = "SELECT *\n"
                + "  FROM(select row_number() over (order by 1) SLNO,\n"
                + "        A.rowid_seq,\n"
                + "       A.entity_code,\n"
                + "       A.client_code,\n"
                + "       A.acc_year,\n"
                + "       A.assesment_acc_year,\n"
                + "       A.quarter_no,\n"
                + "       A.month,\n"
                + "       A.from_date,\n"
                + "       A.to_date,\n"
                + "       A.session_id,\n"
                + "       A.ddo_no,\n"
                + "       A.bill_id,\n"
                + "       A.bill_desc,\n"
                + "       A.bill_type,\n"
                + "       A.vrno,\n"
                + "       A.vrdate,\n"
                + "       A.bill_gross_amt,\n"
                + "       A.netamt,\n"
                + "       A.basic_pay,\n"
                + "       A.nps_employer_contr,\n"
                + "       A.total_gross,\n"
                + "       A.nps_employer_cont,\n"
                + "       A.pli,\n"
                + "       A.it,\n"
                + "       A.exe_pay_rec,\n"
                + "       A.total_deduction,\n"
                + "       A.net_pay,\n"
                + "       A.da,\n"
                + "       A.hra,\n"
                + "       A.ta,\n"
                + "       A.partially_fully_tax_free,\n"
                + "       A.other_taxable,\n"
                + "       A.gpf,\n"
                + "       A.nps_employee_contr,\n"
                + "       A.gis,\n"
                + "       A.pt,\n"
                + "       A.hba_principal,\n"
                + "       A.hba_interest,\n"
                + "       A.lic,\n"
                + "       A.other_deduction,\n"
                + "       A.washing_allowance,\n"
                + "       A.uniform_allowance,\n"
                + "       A.donations,\n"
                + "       A.naxal_area_allowance,\n"
                + "       A.pg_allowance,\n"
                + "       A.medical_education_allowance,\n"
                + "       A.medical_insurance_premium,\n"
                + "       A.sevarth_id,\n"
                + "       A.tds_type_code,\n"
                + "       A.salary_month,\n"
                + "       A.salary_year,\n"
                + "       A.salary_from_date,\n"
                + "       A.salary_to_date\n"
                + "  from salary_tran_itp_prov A, CLIENT_MAST B\n"
                + " WHERE B.ENTITY_CODE = A.ENTITY_CODE\n"
                + "   AND B.CLIENT_CODE = A.CLIENT_CODE\n"
                + "           AND (B.CLIENT_CODE = '" + client.getClient_code() + "' OR B.PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "           B.G_PARENT_CODE = '" + client.getClient_code() + "' OR B.SG_PARENT_CODE = '" + client.getClient_code() + "' OR\n"
                + "           B.SSG_PARENT_CODE = '" + client.getClient_code() + "' OR B.SSSG_PARENT_CODE = '" + client.getClient_code() + "')\n"
                + "           AND A.ENTITY_CODE = '" + client.getEntity_code() + "'\n";
        query += "AND A.ACC_YEAR = '" + assment.getAccYear() + "'\n";
        query += "AND A.QUARTER_NO = '" + assment.getQuarterNo() + "'\n";
        query += "AND A.TDS_TYPE_CODE = '" + assment.getTdsTypeCode() + "'\n";
        query += whereclause
                + "   )\n"
                + " WHERE SLNO BETWEEN " + min + " AND " + max + "";
        return query;
    }
    private Util utl;

    public SalarySevaarthProjectionDB() {
        utl = new Util();
    }
}
