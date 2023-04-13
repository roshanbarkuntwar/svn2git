/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernateObjects;

import globalUtilities.Util;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ayushi.jain
 */
@Entity
@Table(name = "salary_tran")
public class SalaryTran implements java.io.Serializable {

    @Column(name = "month", length = 10, nullable = true)
    private String month;
    @Column(name = "process_client_code", length = 15, nullable = true)
    private String process_client_code;
    @Column(name = "account_no", length = 25, nullable = true)
    private String account_no;
    @Column(name = "validate_client_code", length = 15, nullable = true)
    private String validate_client_code;
    @Column(name = "lpan_name3", length = 75, nullable = true)
    private String lpan_name3;
    @Column(name = "lpan_panno4", length = 10, nullable = true)
    private String lpan_panno4;
    @Column(name = "lpan_name4", length = 75, nullable = true)
    private String lpan_name4;
    @Column(name = "cptas_flag", length = 1, nullable = true)
    private String cptas_flag;
    @Column(name = "nsaf_name", length = 75, nullable = true)
    private String nsaf_name;
    @Column(name = "nsaf_from_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nsaf_from_date;
    @Column(name = "nsaf_to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date nsaf_to_date;
    @Column(name = "crpsf_amt", length = 22, nullable = true)
    private String crpsf_amt;
    @Column(name = "adp3y_rate", length = 22, nullable = true)
    private String adp3y_rate;
    @Column(name = "tdrsf_amt", length = 22, nullable = true)
    private String tdrsf_amt;
    @Column(name = "girsf_amt", length = 22, nullable = true)
    private String girsf_amt;
    @Id
    @Column(name = "rowid_seq", length = 22, nullable = true)
    private String rowid_seq;
    @Column(name = "dss_amt", length = 22, nullable = true)
    private String dss_amt;
    @Column(name = "dsd_amt", length = 22, nullable = true)
    private String dsd_amt;
    @Column(name = "dsrsd_amt", length = 22, nullable = true)
    private String dsrsd_amt;
    @Column(name = "b1_80_amt", length = 22, nullable = true)
    private String b1_80_amt;
    @Column(name = "dedss_amt", length = 22, nullable = true)
    private String dedss_amt;
    @Column(name = "allow_amt", length = 22, nullable = true)
    private String allow_amt;
    @Column(name = "lecm_amt", length = 22, nullable = true)
    private String lecm_amt;
    @Column(name = "bla_1_amt", length = 22, nullable = true)
    private String bla_1_amt;
    @Column(name = "ma_amt", length = 22, nullable = true)
    private String ma_amt;
    @Column(name = "scca_amt", length = 22, nullable = true)
    private String scca_amt;
    @Column(name = "tlpal_amt", length = 22, nullable = true)
    private String tlpal_amt;
    @Column(name = "ifcl_amt", length = 22, nullable = true)
    private String ifcl_amt;
    @Column(name = "oth_i_amt", length = 22, nullable = true)
    private String oth_i_amt;
    @Column(name = "osega_amt", length = 22, nullable = true)
    private String osega_amt;
    @Column(name = "ecs3_amt", length = 22, nullable = true)
    private String ecs3_amt;
    @Column(name = "dss80_amt", length = 22, nullable = true)
    private String dss80_amt;
    @Column(name = "d80ee_amt", length = 22, nullable = true)
    private String d80ee_amt;
    @Column(name = "u17_3_amt", length = 22, nullable = true)
    private String u17_3_amt;
    @Column(name = "tufee_amt", length = 22, nullable = true)
    private String tufee_amt;
    @Column(name = "oticm_amt", length = 22, nullable = true)
    private String oticm_amt;
    @Column(name = "td192_amt", length = 22, nullable = true)
    private String td192_amt;
    @Column(name = "brpay_amt", length = 22, nullable = true)
    private String brpay_amt;
    @Column(name = "cemp_amt", length = 22, nullable = true)
    private String cemp_amt;
    @Column(name = "hal_1_amt", length = 22, nullable = true)
    private String hal_1_amt;
    @Column(name = "gsal_amt", length = 22, nullable = true)
    private String gsal_amt;
    @Column(name = "stotl_amt", length = 22, nullable = true)
    private String stotl_amt;
    @Column(name = "ag4ab_amt", length = 22, nullable = true)
    private String ag4ab_amt;
    @Column(name = "txoti_amt", length = 22, nullable = true)
    private String txoti_amt;
    @Column(name = "rlf89_amt", length = 22, nullable = true)
    private String rlf89_amt;
    @Column(name = "dsf_amt", length = 22, nullable = true)
    private String dsf_amt;
    @Column(name = "d80ds_amt", length = 22, nullable = true)
    private String d80ds_amt;
    @Column(name = "d80gg_amt", length = 22, nullable = true)
    private String d80gg_amt;
    @Column(name = "dggc_amt", length = 22, nullable = true)
    private String dggc_amt;
    @Column(name = "lip_amt", length = 22, nullable = true)
    private String lip_amt;
    @Column(name = "etal_amt", length = 22, nullable = true)
    private String etal_amt;
    @Column(name = "schcs_amt", length = 22, nullable = true)
    private String schcs_amt;
    @Column(name = "txpy_amt", length = 22, nullable = true)
    private String txpy_amt;
    @Column(name = "cmte_amt", length = 22, nullable = true)
    private String cmte_amt;
    @Column(name = "osa_amt", length = 22, nullable = true)
    private String osa_amt;
    @Column(name = "gft_amt", length = 22, nullable = true)
    private String gft_amt;
    @Column(name = "mre_amt", length = 22, nullable = true)
    private String mre_amt;
    @Column(name = "s17_2_amt", length = 22, nullable = true)
    private String s17_2_amt;
    @Column(name = "gtoti_amt", length = 22, nullable = true)
    private String gtoti_amt;
    @Column(name = "se80c_amt", length = 22, nullable = true)
    private String se80c_amt;
    @Column(name = "dcrpf_amt", length = 22, nullable = true)
    private String dcrpf_amt;
    @Column(name = "dlipp_amt", length = 22, nullable = true)
    private String dlipp_amt;
    @Column(name = "dnsc_amt", length = 22, nullable = true)
    private String dnsc_amt;
    @Column(name = "a80_2_amt", length = 22, nullable = true)
    private String a80_2_amt;
    @Column(name = "nsal_amt", length = 22, nullable = true)
    private String nsal_amt;
    @Column(name = "pftx_amt", length = 22, nullable = true)
    private String pftx_amt;
    @Column(name = "advia_amt", length = 22, nullable = true)
    private String advia_amt;
    @Column(name = "ifhp_amt", length = 22, nullable = true)
    private String ifhp_amt;
    @Column(name = "rfu86_amt", length = 22, nullable = true)
    private String rfu86_amt;
    @Column(name = "cmpsn_amt", length = 22, nullable = true)
    private String cmpsn_amt;
    @Column(name = "othr_amt", length = 22, nullable = true)
    private String othr_amt;
    @Column(name = "daa_1_amt", length = 22, nullable = true)
    private String daa_1_amt;
    @Column(name = "fce_amt", length = 22, nullable = true)
    private String fce_amt;
    @Column(name = "cpg_amt", length = 22, nullable = true)
    private String cpg_amt;
    @Column(name = "pha_1_amt", length = 22, nullable = true)
    private String pha_1_amt;
    @Column(name = "incom_amt", length = 22, nullable = true)
    private String incom_amt;
    @Column(name = "d80_amt", length = 22, nullable = true)
    private String d80_amt;
    @Column(name = "txp13_amt", length = 22, nullable = true)
    private String txp13_amt;
    @Column(name = "difb_amt", length = 22, nullable = true)
    private String difb_amt;
    @Column(name = "dedmf_amt", length = 22, nullable = true)
    private String dedmf_amt;
    @Column(name = "dedsp_amt", length = 22, nullable = true)
    private String dedsp_amt;
    @Column(name = "dd80_amt", length = 22, nullable = true)
    private String dd80_amt;
    @Column(name = "dds80_amt", length = 22, nullable = true)
    private String dds80_amt;
    @Column(name = "dgga_amt", length = 22, nullable = true)
    private String dgga_amt;
    @Column(name = "txpbl_amt", length = 22, nullable = true)
    private String txpbl_amt;
    @Column(name = "surch_amt", length = 22, nullable = true)
    private String surch_amt;
    @Column(name = "cos_1_amt", length = 22, nullable = true)
    private String cos_1_amt;
    @Column(name = "gty_1_amt", length = 22, nullable = true)
    private String gty_1_amt;
    @Column(name = "rcptn_amt", length = 22, nullable = true)
    private String rcptn_amt;
    @Column(name = "vrc_1_amt", length = 22, nullable = true)
    private String vrc_1_amt;
    @Column(name = "haa_2_amt", length = 22, nullable = true)
    private String haa_2_amt;
    @Column(name = "rla_amt", length = 22, nullable = true)
    private String rla_amt;
    @Column(name = "pa_amt", length = 22, nullable = true)
    private String pa_amt;
    @Column(name = "ua_amt", length = 22, nullable = true)
    private String ua_amt;
    @Column(name = "ta_2_amt", length = 22, nullable = true)
    private String ta_2_amt;
    @Column(name = "a80cd_amt", length = 22, nullable = true)
    private String a80cd_amt;
    @Column(name = "dainc_amt", length = 22, nullable = true)
    private String dainc_amt;
    @Column(name = "dulip_amt", length = 22, nullable = true)
    private String dulip_amt;
    @Column(name = "usdd_amt", length = 22, nullable = true)
    private String usdd_amt;
    @Column(name = "advib_amt", length = 22, nullable = true)
    private String advib_amt;
    @Column(name = "ttdsd_amt", length = 22, nullable = true)
    private String ttdsd_amt;
    @Column(name = "ccc80_amt", length = 22, nullable = true)
    private String ccc80_amt;
    @Column(name = "ccd80_amt", length = 22, nullable = true)
    private String ccd80_amt;
    @Column(name = "ded80_amt", length = 22, nullable = true)
    private String ded80_amt;
    @Column(name = "ccf80_amt", length = 22, nullable = true)
    private String ccf80_amt;
    @Column(name = "ccg80_amt", length = 22, nullable = true)
    private String ccg80_amt;
    @Column(name = "dedoh_amt", length = 22, nullable = true)
    private String dedoh_amt;
    @Column(name = "dsss_amt", length = 22, nullable = true)
    private String dsss_amt;
    @Column(name = "dedp_amt", length = 22, nullable = true)
    private String dedp_amt;
    @Column(name = "d80e_amt", length = 22, nullable = true)
    private String d80e_amt;
    @Column(name = "d80g1_amt", length = 22, nullable = true)
    private String d80g1_amt;
    @Column(name = "tta80_amt", length = 22, nullable = true)
    private String tta80_amt;
    @Column(name = "dos_amt", length = 22, nullable = true)
    private String dos_amt;
    @Column(name = "tti_1_amt", length = 22, nullable = true)
    private String tti_1_amt;
    @Column(name = "itti_amt", length = 22, nullable = true)
    private String itti_amt;
    @Column(name = "srch_amt", length = 22, nullable = true)
    private String srch_amt;
    @Column(name = "edcs_amt", length = 22, nullable = true)
    private String edcs_amt;
    @Column(name = "itr_1_amt", length = 22, nullable = true)
    private String itr_1_amt;
    @Column(name = "ttaxd_amt", length = 22, nullable = true)
    private String ttaxd_amt;
    @Column(name = "tds4q_amt", length = 22, nullable = true)
    private String tds4q_amt;
    @Column(name = "tds3q_amt", length = 22, nullable = true)
    private String tds3q_amt;
    @Column(name = "tatpe_amt", length = 22, nullable = true)
    private String tatpe_amt;
    @Column(name = "tdspe_amt", length = 22, nullable = true)
    private String tdspe_amt;
    @Column(name = "tdsce_amt", length = 22, nullable = true)
    private String tdsce_amt;
    @Column(name = "std_1_amt", length = 22, nullable = true)
    private String std_1_amt;
    @Column(name = "sibpe_amt", length = 22, nullable = true)
    private String sibpe_amt;
    @Column(name = "sibce_amt", length = 22, nullable = true)
    private String sibce_amt;
    @Column(name = "txinc_amt", length = 22, nullable = true)
    private String txinc_amt;
    @Column(name = "rpe1l_flag", length = 1, nullable = true)
    private String rpe1l_flag;
    @Column(name = "rpan_nos", length = 22, nullable = true)
    private String rpan_nos;
    @Column(name = "rpan_panno1", length = 10, nullable = true)
    private String rpan_panno1;
    @Column(name = "rpan_name1", length = 75, nullable = true)
    private String rpan_name1;
    @Column(name = "rpan_panno2", length = 10, nullable = true)
    private String rpan_panno2;
    @Column(name = "rpan_name2", length = 75, nullable = true)
    private String rpan_name2;
    @Column(name = "rpan_panno3", length = 10, nullable = true)
    private String rpan_panno3;
    @Column(name = "rpan_name3", length = 75, nullable = true)
    private String rpan_name3;
    @Column(name = "rpan_panno4", length = 10, nullable = true)
    private String rpan_panno4;
    @Column(name = "rpan_name4", length = 75, nullable = true)
    private String rpan_name4;
    @Column(name = "iplih_flag", length = 4000, nullable = true)
    private String iplih_flag;
    @Column(name = "lpan_nos", length = 4000, nullable = true)
    private String lpan_nos;
    @Column(name = "lpan_panno1", length = 10, nullable = true)
    private String lpan_panno1;
    @Column(name = "lpan_name1", length = 75, nullable = true)
    private String lpan_name1;
    @Column(name = "lpan_panno2", length = 10, nullable = true)
    private String lpan_panno2;
    @Column(name = "lpan_name2", length = 75, nullable = true)
    private String lpan_name2;
    @Column(name = "lpan_panno3", length = 10, nullable = true)
    private String lpan_panno3;
    @Column(name = "entity_code", length = 2, nullable = false)
    private String entity_code;
    @Column(name = "client_code", length = 15, nullable = false)
    private String client_code;
    @Column(name = "acc_year", length = 5, nullable = true)
    private String acc_year;
    @Column(name = "tds_type_code", length = 3, nullable = true)
    private String tds_type_code;
    @Column(name = "quarter_no", length = 22, nullable = true)
    private String quarter_no;
    @Id
    @Column(name = "deductee_code", length = 15, nullable = false)
    private String deductee_code;
    @Column(name = "deductee_name", length = 75, nullable = true)
    private String deductee_name;
    @Column(name = "sex", length = 1, nullable = true)
    private String sex;
    @Column(name = "from_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date from_date;
    @Column(name = "to_date", nullable = true)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date to_date;
    @Column(name = "bsal_amt", length = 22, nullable = true)
    private String bsal_amt;
    @Column(name = "bns_1_amt", length = 22, nullable = true)
    private String bns_1_amt;
    @Column(name = "inct_amt", length = 22, nullable = true)
    private String inct_amt;
    @Column(name = "asal_amt", length = 22, nullable = true)
    private String asal_amt;
    @Column(name = "arsal_amt", length = 22, nullable = true)
    private String arsal_amt;
    @Column(name = "otr_1_amt", length = 22, nullable = true)
    private String otr_1_amt;
    @Column(name = "da_1_amt", length = 22, nullable = true)
    private String da_1_amt;
    @Column(name = "hra_1_amt", length = 22, nullable = true)
    private String hra_1_amt;
    @Column(name = "enrb_amt", length = 22, nullable = true)
    private String enrb_amt;
    @Column(name = "ta_amt", length = 22, nullable = true)
    private String ta_amt;
    @Column(name = "mscal_amt", length = 22, nullable = true)
    private String mscal_amt;
    @Column(name = "plsy_amt", length = 22, nullable = true)
    private String plsy_amt;
    @Column(name = "pqst_amt", length = 22, nullable = true)
    private String pqst_amt;
    @Column(name = "princ_amt", length = 22, nullable = true)
    private String princ_amt;
    @Column(name = "salcu_amt", length = 22, nullable = true)
    private String salcu_amt;
    @Column(name = "sea_1_amt", length = 22, nullable = true)
    private String sea_1_amt;
    @Column(name = "spt_1_amt", length = 22, nullable = true)
    private String spt_1_amt;
    @Column(name = "ipsoh_amt", length = 22, nullable = true)
    private String ipsoh_amt;
    @Column(name = "aoi_amt", length = 22, nullable = true)
    private String aoi_amt;
    @Column(name = "inc_amt", length = 22, nullable = true)
    private String inc_amt;
    @Column(name = "gtiaa_amt", length = 22, nullable = true)
    private String gtiaa_amt;
    @Column(name = "dlip_amt", length = 22, nullable = true)
    private String dlip_amt;
    @Column(name = "drhl_amt", length = 22, nullable = true)
    private String drhl_amt;
    @Column(name = "dspf_amt", length = 22, nullable = true)
    private String dspf_amt;
    @Column(name = "d80c_amt", length = 22, nullable = true)
    private String d80c_amt;
    @Column(name = "dtff_amt", length = 22, nullable = true)
    private String dtff_amt;
    @Column(name = "oth80_amt", length = 22, nullable = true)
    private String oth80_amt;
    @Column(name = "itax_catg", length = 1, nullable = true)
    private String itax_catg;
    @Column(name = "deductee_catg", length = 5, nullable = true)
    private String deductee_catg;
    @Column(name = "deductee_panno", length = 10, nullable = true)
    private String deductee_panno;
    @Column(name = "identification_no", length = 50, nullable = true)
    private String identification_no;
    @Column(name = "deductee_panno_verified", length = 1, nullable = true)
    private String deductee_panno_verified;
    @Column(name = "deductee_panno_valid", length = 1, nullable = true)
    private String deductee_panno_valid;
    @Column(name = "deductee_ref_code1", length = 10, nullable = true)
    private String deductee_ref_code1;
    @Column(name = "deductee_ref_code2", length = 10, nullable = true)
    private String deductee_ref_code2;
    @Column(name = "u17_2_amt", length = 22, nullable = true)
    private String u17_2_amt;
    @Column(name = "tdsdt_amt", length = 22, nullable = true)
    private String tdsdt_amt;
    @Column(name = "s17_4_amt", length = 22, nullable = true)
    private String s17_4_amt;
    @Column(name = "dedct_amt", length = 22, nullable = true)
    private String dedct_amt;
    @Column(name = "pnsn_amt", length = 22, nullable = true)
    private String pnsn_amt;
    @Column(name = "cca_1_amt", length = 22, nullable = true)
    private String cca_1_amt;
    @Column(name = "haa_1_amt", length = 22, nullable = true)
    private String haa_1_amt;
    @Column(name = "pmpe_amt", length = 22, nullable = true)
    private String pmpe_amt;
    @Column(name = "l_alw_amt", length = 22, nullable = true)
    private String l_alw_amt;
    @Column(name = "sec80_amt", length = 22, nullable = true)
    private String sec80_amt;
    @Column(name = "oin80_amt", length = 22, nullable = true)
    private String oin80_amt;
    @Column(name = "dgpf_amt", length = 22, nullable = true)
    private String dgpf_amt;
    @Column(name = "dppf_amt", length = 22, nullable = true)
    private String dppf_amt;
    @Column(name = "dsi_amt", length = 22, nullable = true)
    private String dsi_amt;
    @Column(name = "dssy_amt", length = 22, nullable = true)
    private String dssy_amt;
    @Column(name = "ddb80_amt", length = 22, nullable = true)
    private String ddb80_amt;
    @Column(name = "tdsdq_amt", length = 22, nullable = true)
    private String tdsdq_amt;
    @Column(name = "ttdds_amt", length = 22, nullable = true)
    private String ttdds_amt;
    @Column(name = "ttinc_amt", length = 22, nullable = true)
    private String ttinc_amt;
    @Column(name = "ttinm_amt", length = 22, nullable = true)
    private String ttinm_amt;
    @Column(name = "edces_amt", length = 22, nullable = true)
    private String edces_amt;
    @Column(name = "alw_amt", length = 22, nullable = true)
    private String alw_amt;
    @Column(name = "edual_amt", length = 22, nullable = true)
    private String edual_amt;
    @Column(name = "ca_amt", length = 22, nullable = true)
    private String ca_amt;
    @Column(name = "rape_amt", length = 22, nullable = true)
    private String rape_amt;
    @Column(name = "s17_1_amt", length = 22, nullable = true)
    private String s17_1_amt;
    @Column(name = "t_alw_amt", length = 22, nullable = true)
    private String t_alw_amt;
    @Column(name = "dcvia_amt", length = 22, nullable = true)
    private String dcvia_amt;
    @Column(name = "txp15_amt", length = 22, nullable = true)
    private String txp15_amt;
    @Column(name = "dedap_amt", length = 22, nullable = true)
    private String dedap_amt;
    @Column(name = "dtfs_amt", length = 22, nullable = true)
    private String dtfs_amt;
    @Column(name = "udd80_amt", length = 22, nullable = true)
    private String udd80_amt;
    @Column(name = "ppf_amt", length = 22, nullable = true)
    private String ppf_amt;
    @Column(name = "pf_amt", length = 22, nullable = true)
    private String pf_amt;
    @Column(name = "tdpem_amt", length = 22, nullable = true)
    private String tdpem_amt;
    @Column(name = "sal_amt", length = 22, nullable = true)
    private String sal_amt;
    @Column(name = "ha_1_amt", length = 22, nullable = true)
    private String ha_1_amt;
    @Column(name = "ta_1_amt", length = 22, nullable = true)
    private String ta_1_amt;
    @Column(name = "gewhc_amt", length = 22, nullable = true)
    private String gewhc_amt;
    @Column(name = "vfn_1_amt", length = 22, nullable = true)
    private String vfn_1_amt;
    @Column(name = "mac_1_amt", length = 22, nullable = true)
    private String mac_1_amt;
    @Column(name = "s17_3_amt", length = 22, nullable = true)
    private String s17_3_amt;
    @Column(name = "bl1_2_amt", length = 22, nullable = true)
    private String bl1_2_amt;
    @Column(name = "e_alw_amt", length = 22, nullable = true)
    private String e_alw_amt;
    @Column(name = "t_emp_amt", length = 22, nullable = true)
    private String t_emp_amt;
    @Column(name = "ichrs_amt", length = 22, nullable = true)
    private String ichrs_amt;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getProcess_client_code() {
        return process_client_code;
    }

    public void setProcess_client_code(String process_client_code) {
        this.process_client_code = process_client_code;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getValidate_client_code() {
        return validate_client_code;
    }

    public void setValidate_client_code(String validate_client_code) {
        this.validate_client_code = validate_client_code;
    }

    public String getLpan_name3() {
        return lpan_name3;
    }

    public void setLpan_name3(String lpan_name3) {
        this.lpan_name3 = lpan_name3;
    }

    public String getLpan_panno4() {
        return lpan_panno4;
    }

    public void setLpan_panno4(String lpan_panno4) {
        this.lpan_panno4 = lpan_panno4;
    }

    public String getLpan_name4() {
        return lpan_name4;
    }

    public void setLpan_name4(String lpan_name4) {
        this.lpan_name4 = lpan_name4;
    }

    public String getCptas_flag() {
        return cptas_flag;
    }

    public void setCptas_flag(String cptas_flag) {
        this.cptas_flag = cptas_flag;
    }

    public String getNsaf_name() {
        return nsaf_name;
    }

    public void setNsaf_name(String nsaf_name) {
        this.nsaf_name = nsaf_name;
    }

    public Date getNsaf_from_date() {
        return nsaf_from_date;
    }

    public void setNsaf_from_date(Date nsaf_from_date) {
        this.nsaf_from_date = nsaf_from_date;
    }

    public Date getNsaf_to_date() {
        return nsaf_to_date;
    }

    public void setNsaf_to_date(Date nsaf_to_date) {
        this.nsaf_to_date = nsaf_to_date;
    }

    public String getCrpsf_amt() {
        return crpsf_amt;
    }

    public void setCrpsf_amt(String crpsf_amt) {
        this.crpsf_amt = crpsf_amt;
    }

    public String getAdp3y_rate() {
        return adp3y_rate;
    }

    public void setAdp3y_rate(String adp3y_rate) {
        this.adp3y_rate = adp3y_rate;
    }

    public String getTdrsf_amt() {
        return tdrsf_amt;
    }

    public void setTdrsf_amt(String tdrsf_amt) {
        this.tdrsf_amt = tdrsf_amt;
    }

    public String getGirsf_amt() {
        return girsf_amt;
    }

    public void setGirsf_amt(String girsf_amt) {
        this.girsf_amt = girsf_amt;
    }

    public String getRowid_seq() {
        return rowid_seq;
    }

    public void setRowid_seq(String rowid_seq) {
        this.rowid_seq = rowid_seq;
    }

    public String getDss_amt() {
        return dss_amt;
    }

    public void setDss_amt(String dss_amt) {
        this.dss_amt = dss_amt;
    }

    public String getDsd_amt() {
        return dsd_amt;
    }

    public void setDsd_amt(String dsd_amt) {
        this.dsd_amt = dsd_amt;
    }

    public String getDsrsd_amt() {
        return dsrsd_amt;
    }

    public void setDsrsd_amt(String dsrsd_amt) {
        this.dsrsd_amt = dsrsd_amt;
    }

    public String getB1_80_amt() {
        return b1_80_amt;
    }

    public void setB1_80_amt(String b1_80_amt) {
        this.b1_80_amt = b1_80_amt;
    }

    public String getDedss_amt() {
        return dedss_amt;
    }

    public void setDedss_amt(String dedss_amt) {
        this.dedss_amt = dedss_amt;
    }

    public String getAllow_amt() {
        return allow_amt;
    }

    public void setAllow_amt(String allow_amt) {
        this.allow_amt = allow_amt;
    }

    public String getLecm_amt() {
        return lecm_amt;
    }

    public void setLecm_amt(String lecm_amt) {
        this.lecm_amt = lecm_amt;
    }

    public String getBla_1_amt() {
        return bla_1_amt;
    }

    public void setBla_1_amt(String bla_1_amt) {
        this.bla_1_amt = bla_1_amt;
    }

    public String getMa_amt() {
        return ma_amt;
    }

    public void setMa_amt(String ma_amt) {
        this.ma_amt = ma_amt;
    }

    public String getScca_amt() {
        return scca_amt;
    }

    public void setScca_amt(String scca_amt) {
        this.scca_amt = scca_amt;
    }

    public String getTlpal_amt() {
        return tlpal_amt;
    }

    public void setTlpal_amt(String tlpal_amt) {
        this.tlpal_amt = tlpal_amt;
    }

    public String getIfcl_amt() {
        return ifcl_amt;
    }

    public void setIfcl_amt(String ifcl_amt) {
        this.ifcl_amt = ifcl_amt;
    }

    public String getOth_i_amt() {
        return oth_i_amt;
    }

    public void setOth_i_amt(String oth_i_amt) {
        this.oth_i_amt = oth_i_amt;
    }

    public String getOsega_amt() {
        return osega_amt;
    }

    public void setOsega_amt(String osega_amt) {
        this.osega_amt = osega_amt;
    }

    public String getEcs3_amt() {
        return ecs3_amt;
    }

    public void setEcs3_amt(String ecs3_amt) {
        this.ecs3_amt = ecs3_amt;
    }

    public String getDss80_amt() {
        return dss80_amt;
    }

    public void setDss80_amt(String dss80_amt) {
        this.dss80_amt = dss80_amt;
    }

    public String getD80ee_amt() {
        return d80ee_amt;
    }

    public void setD80ee_amt(String d80ee_amt) {
        this.d80ee_amt = d80ee_amt;
    }

    public String getU17_3_amt() {
        return u17_3_amt;
    }

    public void setU17_3_amt(String u17_3_amt) {
        this.u17_3_amt = u17_3_amt;
    }

    public String getTufee_amt() {
        return tufee_amt;
    }

    public void setTufee_amt(String tufee_amt) {
        this.tufee_amt = tufee_amt;
    }

    public String getOticm_amt() {
        return oticm_amt;
    }

    public void setOticm_amt(String oticm_amt) {
        this.oticm_amt = oticm_amt;
    }

    public String getTd192_amt() {
        return td192_amt;
    }

    public void setTd192_amt(String td192_amt) {
        this.td192_amt = td192_amt;
    }

    public String getBrpay_amt() {
        return brpay_amt;
    }

    public void setBrpay_amt(String brpay_amt) {
        this.brpay_amt = brpay_amt;
    }

    public String getCemp_amt() {
        return cemp_amt;
    }

    public void setCemp_amt(String cemp_amt) {
        this.cemp_amt = cemp_amt;
    }

    public String getHal_1_amt() {
        return hal_1_amt;
    }

    public void setHal_1_amt(String hal_1_amt) {
        this.hal_1_amt = hal_1_amt;
    }

    public String getGsal_amt() {
        return gsal_amt;
    }

    public void setGsal_amt(String gsal_amt) {
        this.gsal_amt = gsal_amt;
    }

    public String getStotl_amt() {
        return stotl_amt;
    }

    public void setStotl_amt(String stotl_amt) {
        this.stotl_amt = stotl_amt;
    }

    public String getAg4ab_amt() {
        return ag4ab_amt;
    }

    public void setAg4ab_amt(String ag4ab_amt) {
        this.ag4ab_amt = ag4ab_amt;
    }

    public String getTxoti_amt() {
        return txoti_amt;
    }

    public void setTxoti_amt(String txoti_amt) {
        this.txoti_amt = txoti_amt;
    }

    public String getRlf89_amt() {
        return rlf89_amt;
    }

    public void setRlf89_amt(String rlf89_amt) {
        this.rlf89_amt = rlf89_amt;
    }

    public String getDsf_amt() {
        return dsf_amt;
    }

    public void setDsf_amt(String dsf_amt) {
        this.dsf_amt = dsf_amt;
    }

    public String getD80ds_amt() {
        return d80ds_amt;
    }

    public void setD80ds_amt(String d80ds_amt) {
        this.d80ds_amt = d80ds_amt;
    }

    public String getD80gg_amt() {
        return d80gg_amt;
    }

    public void setD80gg_amt(String d80gg_amt) {
        this.d80gg_amt = d80gg_amt;
    }

    public String getDggc_amt() {
        return dggc_amt;
    }

    public void setDggc_amt(String dggc_amt) {
        this.dggc_amt = dggc_amt;
    }

    public String getLip_amt() {
        return lip_amt;
    }

    public void setLip_amt(String lip_amt) {
        this.lip_amt = lip_amt;
    }

    public String getEtal_amt() {
        return etal_amt;
    }

    public void setEtal_amt(String etal_amt) {
        this.etal_amt = etal_amt;
    }

    public String getSchcs_amt() {
        return schcs_amt;
    }

    public void setSchcs_amt(String schcs_amt) {
        this.schcs_amt = schcs_amt;
    }

    public String getTxpy_amt() {
        return txpy_amt;
    }

    public void setTxpy_amt(String txpy_amt) {
        this.txpy_amt = txpy_amt;
    }

    public String getCmte_amt() {
        return cmte_amt;
    }

    public void setCmte_amt(String cmte_amt) {
        this.cmte_amt = cmte_amt;
    }

    public String getOsa_amt() {
        return osa_amt;
    }

    public void setOsa_amt(String osa_amt) {
        this.osa_amt = osa_amt;
    }

    public String getGft_amt() {
        return gft_amt;
    }

    public void setGft_amt(String gft_amt) {
        this.gft_amt = gft_amt;
    }

    public String getMre_amt() {
        return mre_amt;
    }

    public void setMre_amt(String mre_amt) {
        this.mre_amt = mre_amt;
    }

    public String getS17_2_amt() {
        return s17_2_amt;
    }

    public void setS17_2_amt(String s17_2_amt) {
        this.s17_2_amt = s17_2_amt;
    }

    public String getGtoti_amt() {
        return gtoti_amt;
    }

    public void setGtoti_amt(String gtoti_amt) {
        this.gtoti_amt = gtoti_amt;
    }

    public String getSe80c_amt() {
        return se80c_amt;
    }

    public void setSe80c_amt(String se80c_amt) {
        this.se80c_amt = se80c_amt;
    }

    public String getDcrpf_amt() {
        return dcrpf_amt;
    }

    public void setDcrpf_amt(String dcrpf_amt) {
        this.dcrpf_amt = dcrpf_amt;
    }

    public String getDlipp_amt() {
        return dlipp_amt;
    }

    public void setDlipp_amt(String dlipp_amt) {
        this.dlipp_amt = dlipp_amt;
    }

    public String getDnsc_amt() {
        return dnsc_amt;
    }

    public void setDnsc_amt(String dnsc_amt) {
        this.dnsc_amt = dnsc_amt;
    }

    public String getA80_2_amt() {
        return a80_2_amt;
    }

    public void setA80_2_amt(String a80_2_amt) {
        this.a80_2_amt = a80_2_amt;
    }

    public String getNsal_amt() {
        return nsal_amt;
    }

    public void setNsal_amt(String nsal_amt) {
        this.nsal_amt = nsal_amt;
    }

    public String getPftx_amt() {
        return pftx_amt;
    }

    public void setPftx_amt(String pftx_amt) {
        this.pftx_amt = pftx_amt;
    }

    public String getAdvia_amt() {
        return advia_amt;
    }

    public void setAdvia_amt(String advia_amt) {
        this.advia_amt = advia_amt;
    }

    public String getIfhp_amt() {
        return ifhp_amt;
    }

    public void setIfhp_amt(String ifhp_amt) {
        this.ifhp_amt = ifhp_amt;
    }

    public String getRfu86_amt() {
        return rfu86_amt;
    }

    public void setRfu86_amt(String rfu86_amt) {
        this.rfu86_amt = rfu86_amt;
    }

    public String getCmpsn_amt() {
        return cmpsn_amt;
    }

    public void setCmpsn_amt(String cmpsn_amt) {
        this.cmpsn_amt = cmpsn_amt;
    }

    public String getOthr_amt() {
        return othr_amt;
    }

    public void setOthr_amt(String othr_amt) {
        this.othr_amt = othr_amt;
    }

    public String getDaa_1_amt() {
        return daa_1_amt;
    }

    public void setDaa_1_amt(String daa_1_amt) {
        this.daa_1_amt = daa_1_amt;
    }

    public String getFce_amt() {
        return fce_amt;
    }

    public void setFce_amt(String fce_amt) {
        this.fce_amt = fce_amt;
    }

    public String getCpg_amt() {
        return cpg_amt;
    }

    public void setCpg_amt(String cpg_amt) {
        this.cpg_amt = cpg_amt;
    }

    public String getPha_1_amt() {
        return pha_1_amt;
    }

    public void setPha_1_amt(String pha_1_amt) {
        this.pha_1_amt = pha_1_amt;
    }

    public String getIncom_amt() {
        return incom_amt;
    }

    public void setIncom_amt(String incom_amt) {
        this.incom_amt = incom_amt;
    }

    public String getD80_amt() {
        return d80_amt;
    }

    public void setD80_amt(String d80_amt) {
        this.d80_amt = d80_amt;
    }

    public String getTxp13_amt() {
        return txp13_amt;
    }

    public void setTxp13_amt(String txp13_amt) {
        this.txp13_amt = txp13_amt;
    }

    public String getDifb_amt() {
        return difb_amt;
    }

    public void setDifb_amt(String difb_amt) {
        this.difb_amt = difb_amt;
    }

    public String getDedmf_amt() {
        return dedmf_amt;
    }

    public void setDedmf_amt(String dedmf_amt) {
        this.dedmf_amt = dedmf_amt;
    }

    public String getDedsp_amt() {
        return dedsp_amt;
    }

    public void setDedsp_amt(String dedsp_amt) {
        this.dedsp_amt = dedsp_amt;
    }

    public String getDd80_amt() {
        return dd80_amt;
    }

    public void setDd80_amt(String dd80_amt) {
        this.dd80_amt = dd80_amt;
    }

    public String getDds80_amt() {
        return dds80_amt;
    }

    public void setDds80_amt(String dds80_amt) {
        this.dds80_amt = dds80_amt;
    }

    public String getDgga_amt() {
        return dgga_amt;
    }

    public void setDgga_amt(String dgga_amt) {
        this.dgga_amt = dgga_amt;
    }

    public String getTxpbl_amt() {
        return txpbl_amt;
    }

    public void setTxpbl_amt(String txpbl_amt) {
        this.txpbl_amt = txpbl_amt;
    }

    public String getSurch_amt() {
        return surch_amt;
    }

    public void setSurch_amt(String surch_amt) {
        this.surch_amt = surch_amt;
    }

    public String getCos_1_amt() {
        return cos_1_amt;
    }

    public void setCos_1_amt(String cos_1_amt) {
        this.cos_1_amt = cos_1_amt;
    }

    public String getGty_1_amt() {
        return gty_1_amt;
    }

    public void setGty_1_amt(String gty_1_amt) {
        this.gty_1_amt = gty_1_amt;
    }

    public String getRcptn_amt() {
        return rcptn_amt;
    }

    public void setRcptn_amt(String rcptn_amt) {
        this.rcptn_amt = rcptn_amt;
    }

    public String getVrc_1_amt() {
        return vrc_1_amt;
    }

    public void setVrc_1_amt(String vrc_1_amt) {
        this.vrc_1_amt = vrc_1_amt;
    }

    public String getHaa_2_amt() {
        return haa_2_amt;
    }

    public void setHaa_2_amt(String haa_2_amt) {
        this.haa_2_amt = haa_2_amt;
    }

    public String getRla_amt() {
        return rla_amt;
    }

    public void setRla_amt(String rla_amt) {
        this.rla_amt = rla_amt;
    }

    public String getPa_amt() {
        return pa_amt;
    }

    public void setPa_amt(String pa_amt) {
        this.pa_amt = pa_amt;
    }

    public String getUa_amt() {
        return ua_amt;
    }

    public void setUa_amt(String ua_amt) {
        this.ua_amt = ua_amt;
    }

    public String getTa_2_amt() {
        return ta_2_amt;
    }

    public void setTa_2_amt(String ta_2_amt) {
        this.ta_2_amt = ta_2_amt;
    }

    public String getA80cd_amt() {
        return a80cd_amt;
    }

    public void setA80cd_amt(String a80cd_amt) {
        this.a80cd_amt = a80cd_amt;
    }

    public String getDainc_amt() {
        return dainc_amt;
    }

    public void setDainc_amt(String dainc_amt) {
        this.dainc_amt = dainc_amt;
    }

    public String getDulip_amt() {
        return dulip_amt;
    }

    public void setDulip_amt(String dulip_amt) {
        this.dulip_amt = dulip_amt;
    }

    public String getUsdd_amt() {
        return usdd_amt;
    }

    public void setUsdd_amt(String usdd_amt) {
        this.usdd_amt = usdd_amt;
    }

    public String getAdvib_amt() {
        return advib_amt;
    }

    public void setAdvib_amt(String advib_amt) {
        this.advib_amt = advib_amt;
    }

    public String getTtdsd_amt() {
        return ttdsd_amt;
    }

    public void setTtdsd_amt(String ttdsd_amt) {
        this.ttdsd_amt = ttdsd_amt;
    }

    public String getCcc80_amt() {
        return ccc80_amt;
    }

    public void setCcc80_amt(String ccc80_amt) {
        this.ccc80_amt = ccc80_amt;
    }

    public String getCcd80_amt() {
        return ccd80_amt;
    }

    public void setCcd80_amt(String ccd80_amt) {
        this.ccd80_amt = ccd80_amt;
    }

    public String getDed80_amt() {
        return ded80_amt;
    }

    public void setDed80_amt(String ded80_amt) {
        this.ded80_amt = ded80_amt;
    }

    public String getCcf80_amt() {
        return ccf80_amt;
    }

    public void setCcf80_amt(String ccf80_amt) {
        this.ccf80_amt = ccf80_amt;
    }

    public String getCcg80_amt() {
        return ccg80_amt;
    }

    public void setCcg80_amt(String ccg80_amt) {
        this.ccg80_amt = ccg80_amt;
    }

    public String getDedoh_amt() {
        return dedoh_amt;
    }

    public void setDedoh_amt(String dedoh_amt) {
        this.dedoh_amt = dedoh_amt;
    }

    public String getDsss_amt() {
        return dsss_amt;
    }

    public void setDsss_amt(String dsss_amt) {
        this.dsss_amt = dsss_amt;
    }

    public String getDedp_amt() {
        return dedp_amt;
    }

    public void setDedp_amt(String dedp_amt) {
        this.dedp_amt = dedp_amt;
    }

    public String getD80e_amt() {
        return d80e_amt;
    }

    public void setD80e_amt(String d80e_amt) {
        this.d80e_amt = d80e_amt;
    }

    public String getD80g1_amt() {
        return d80g1_amt;
    }

    public void setD80g1_amt(String d80g1_amt) {
        this.d80g1_amt = d80g1_amt;
    }

    public String getTta80_amt() {
        return tta80_amt;
    }

    public void setTta80_amt(String tta80_amt) {
        this.tta80_amt = tta80_amt;
    }

    public String getDos_amt() {
        return dos_amt;
    }

    public void setDos_amt(String dos_amt) {
        this.dos_amt = dos_amt;
    }

    public String getTti_1_amt() {
        return tti_1_amt;
    }

    public void setTti_1_amt(String tti_1_amt) {
        this.tti_1_amt = tti_1_amt;
    }

    public String getItti_amt() {
        return itti_amt;
    }

    public void setItti_amt(String itti_amt) {
        this.itti_amt = itti_amt;
    }

    public String getSrch_amt() {
        return srch_amt;
    }

    public void setSrch_amt(String srch_amt) {
        this.srch_amt = srch_amt;
    }

    public String getEdcs_amt() {
        return edcs_amt;
    }

    public void setEdcs_amt(String edcs_amt) {
        this.edcs_amt = edcs_amt;
    }

    public String getItr_1_amt() {
        return itr_1_amt;
    }

    public void setItr_1_amt(String itr_1_amt) {
        this.itr_1_amt = itr_1_amt;
    }

    public String getTtaxd_amt() {
        return ttaxd_amt;
    }

    public void setTtaxd_amt(String ttaxd_amt) {
        this.ttaxd_amt = ttaxd_amt;
    }

    public String getTds4q_amt() {
        return tds4q_amt;
    }

    public void setTds4q_amt(String tds4q_amt) {
        this.tds4q_amt = tds4q_amt;
    }

    public String getTds3q_amt() {
        return tds3q_amt;
    }

    public void setTds3q_amt(String tds3q_amt) {
        this.tds3q_amt = tds3q_amt;
    }

    public String getTatpe_amt() {
        return tatpe_amt;
    }

    public void setTatpe_amt(String tatpe_amt) {
        this.tatpe_amt = tatpe_amt;
    }

    public String getTdspe_amt() {
        return tdspe_amt;
    }

    public void setTdspe_amt(String tdspe_amt) {
        this.tdspe_amt = tdspe_amt;
    }

    public String getTdsce_amt() {
        return tdsce_amt;
    }

    public void setTdsce_amt(String tdsce_amt) {
        this.tdsce_amt = tdsce_amt;
    }

    public String getStd_1_amt() {
        return std_1_amt;
    }

    public void setStd_1_amt(String std_1_amt) {
        this.std_1_amt = std_1_amt;
    }

    public String getSibpe_amt() {
        return sibpe_amt;
    }

    public void setSibpe_amt(String sibpe_amt) {
        this.sibpe_amt = sibpe_amt;
    }

    public String getSibce_amt() {
        return sibce_amt;
    }

    public void setSibce_amt(String sibce_amt) {
        this.sibce_amt = sibce_amt;
    }

    public String getTxinc_amt() {
        return txinc_amt;
    }

    public void setTxinc_amt(String txinc_amt) {
        this.txinc_amt = txinc_amt;
    }

    public String getRpe1l_flag() {
        return rpe1l_flag;
    }

    public void setRpe1l_flag(String rpe1l_flag) {
        this.rpe1l_flag = rpe1l_flag;
    }

    public String getRpan_nos() {
        return rpan_nos;
    }

    public void setRpan_nos(String rpan_nos) {
        this.rpan_nos = rpan_nos;
    }

    public String getRpan_panno1() {
        return rpan_panno1;
    }

    public void setRpan_panno1(String rpan_panno1) {
        this.rpan_panno1 = rpan_panno1;
    }

    public String getRpan_name1() {
        return rpan_name1;
    }

    public void setRpan_name1(String rpan_name1) {
        this.rpan_name1 = rpan_name1;
    }

    public String getRpan_panno2() {
        return rpan_panno2;
    }

    public void setRpan_panno2(String rpan_panno2) {
        this.rpan_panno2 = rpan_panno2;
    }

    public String getRpan_name2() {
        return rpan_name2;
    }

    public void setRpan_name2(String rpan_name2) {
        this.rpan_name2 = rpan_name2;
    }

    public String getRpan_panno3() {
        return rpan_panno3;
    }

    public void setRpan_panno3(String rpan_panno3) {
        this.rpan_panno3 = rpan_panno3;
    }

    public String getRpan_name3() {
        return rpan_name3;
    }

    public void setRpan_name3(String rpan_name3) {
        this.rpan_name3 = rpan_name3;
    }

    public String getRpan_panno4() {
        return rpan_panno4;
    }

    public void setRpan_panno4(String rpan_panno4) {
        this.rpan_panno4 = rpan_panno4;
    }

    public String getRpan_name4() {
        return rpan_name4;
    }

    public void setRpan_name4(String rpan_name4) {
        this.rpan_name4 = rpan_name4;
    }

    public String getIplih_flag() {
        return iplih_flag;
    }

    public void setIplih_flag(String iplih_flag) {
        this.iplih_flag = iplih_flag;
    }

    public String getLpan_nos() {
        return lpan_nos;
    }

    public void setLpan_nos(String lpan_nos) {
        this.lpan_nos = lpan_nos;
    }

    public String getLpan_panno1() {
        return lpan_panno1;
    }

    public void setLpan_panno1(String lpan_panno1) {
        this.lpan_panno1 = lpan_panno1;
    }

    public String getLpan_name1() {
        return lpan_name1;
    }

    public void setLpan_name1(String lpan_name1) {
        this.lpan_name1 = lpan_name1;
    }

    public String getLpan_panno2() {
        return lpan_panno2;
    }

    public void setLpan_panno2(String lpan_panno2) {
        this.lpan_panno2 = lpan_panno2;
    }

    public String getLpan_name2() {
        return lpan_name2;
    }

    public void setLpan_name2(String lpan_name2) {
        this.lpan_name2 = lpan_name2;
    }

    public String getLpan_panno3() {
        return lpan_panno3;
    }

    public void setLpan_panno3(String lpan_panno3) {
        this.lpan_panno3 = lpan_panno3;
    }

    public String getEntity_code() {
        return entity_code;
    }

    public void setEntity_code(String entity_code) {
        this.entity_code = entity_code;
    }

    public String getClient_code() {
        return client_code;
    }

    public void setClient_code(String client_code) {
        this.client_code = client_code;
    }

    public String getAcc_year() {
        return acc_year;
    }

    public void setAcc_year(String acc_year) {
        this.acc_year = acc_year;
    }

    public String getTds_type_code() {
        return tds_type_code;
    }

    public void setTds_type_code(String tds_type_code) {
        this.tds_type_code = tds_type_code;
    }

    public String getQuarter_no() {
        return quarter_no;
    }

    public void setQuarter_no(String quarter_no) {
        this.quarter_no = quarter_no;
    }

    public String getDeductee_code() {
        return deductee_code;
    }

    public void setDeductee_code(String deductee_code) {
        this.deductee_code = deductee_code;
    }

    public String getDeductee_name() {
        return deductee_name;
    }

    public void setDeductee_name(String deductee_name) {
        this.deductee_name = deductee_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getFrom_date() {
        return from_date;
    }

    public void setFrom_date(Date from_date) {
        this.from_date = from_date;
    }

    public Date getTo_date() {
        return to_date;
    }

    public void setTo_date(Date to_date) {
        this.to_date = to_date;
    }

    public String getBsal_amt() {
        return bsal_amt;
    }

    public void setBsal_amt(String bsal_amt) {
        this.bsal_amt = bsal_amt;
    }

    public String getBns_1_amt() {
        return bns_1_amt;
    }

    public void setBns_1_amt(String bns_1_amt) {
        this.bns_1_amt = bns_1_amt;
    }

    public String getInct_amt() {
        return inct_amt;
    }

    public void setInct_amt(String inct_amt) {
        this.inct_amt = inct_amt;
    }

    public String getAsal_amt() {
        return asal_amt;
    }

    public void setAsal_amt(String asal_amt) {
        this.asal_amt = asal_amt;
    }

    public String getArsal_amt() {
        return arsal_amt;
    }

    public void setArsal_amt(String arsal_amt) {
        this.arsal_amt = arsal_amt;
    }

    public String getOtr_1_amt() {
        return otr_1_amt;
    }

    public void setOtr_1_amt(String otr_1_amt) {
        this.otr_1_amt = otr_1_amt;
    }

    public String getDa_1_amt() {
        return da_1_amt;
    }

    public void setDa_1_amt(String da_1_amt) {
        this.da_1_amt = da_1_amt;
    }

    public String getHra_1_amt() {
        return hra_1_amt;
    }

    public void setHra_1_amt(String hra_1_amt) {
        this.hra_1_amt = hra_1_amt;
    }

    public String getEnrb_amt() {
        return enrb_amt;
    }

    public void setEnrb_amt(String enrb_amt) {
        this.enrb_amt = enrb_amt;
    }

    public String getTa_amt() {
        return ta_amt;
    }

    public void setTa_amt(String ta_amt) {
        this.ta_amt = ta_amt;
    }

    public String getMscal_amt() {
        return mscal_amt;
    }

    public void setMscal_amt(String mscal_amt) {
        this.mscal_amt = mscal_amt;
    }

    public String getPlsy_amt() {
        return plsy_amt;
    }

    public void setPlsy_amt(String plsy_amt) {
        this.plsy_amt = plsy_amt;
    }

    public String getPqst_amt() {
        return pqst_amt;
    }

    public void setPqst_amt(String pqst_amt) {
        this.pqst_amt = pqst_amt;
    }

    public String getPrinc_amt() {
        return princ_amt;
    }

    public void setPrinc_amt(String princ_amt) {
        this.princ_amt = princ_amt;
    }

    public String getSalcu_amt() {
        return salcu_amt;
    }

    public void setSalcu_amt(String salcu_amt) {
        this.salcu_amt = salcu_amt;
    }

    public String getSea_1_amt() {
        return sea_1_amt;
    }

    public void setSea_1_amt(String sea_1_amt) {
        this.sea_1_amt = sea_1_amt;
    }

    public String getSpt_1_amt() {
        return spt_1_amt;
    }

    public void setSpt_1_amt(String spt_1_amt) {
        this.spt_1_amt = spt_1_amt;
    }

    public String getIpsoh_amt() {
        return ipsoh_amt;
    }

    public void setIpsoh_amt(String ipsoh_amt) {
        this.ipsoh_amt = ipsoh_amt;
    }

    public String getAoi_amt() {
        return aoi_amt;
    }

    public void setAoi_amt(String aoi_amt) {
        this.aoi_amt = aoi_amt;
    }

    public String getInc_amt() {
        return inc_amt;
    }

    public void setInc_amt(String inc_amt) {
        this.inc_amt = inc_amt;
    }

    public String getGtiaa_amt() {
        return gtiaa_amt;
    }

    public void setGtiaa_amt(String gtiaa_amt) {
        this.gtiaa_amt = gtiaa_amt;
    }

    public String getDlip_amt() {
        return dlip_amt;
    }

    public void setDlip_amt(String dlip_amt) {
        this.dlip_amt = dlip_amt;
    }

    public String getDrhl_amt() {
        return drhl_amt;
    }

    public void setDrhl_amt(String drhl_amt) {
        this.drhl_amt = drhl_amt;
    }

    public String getDspf_amt() {
        return dspf_amt;
    }

    public void setDspf_amt(String dspf_amt) {
        this.dspf_amt = dspf_amt;
    }

    public String getD80c_amt() {
        return d80c_amt;
    }

    public void setD80c_amt(String d80c_amt) {
        this.d80c_amt = d80c_amt;
    }

    public String getDtff_amt() {
        return dtff_amt;
    }

    public void setDtff_amt(String dtff_amt) {
        this.dtff_amt = dtff_amt;
    }

    public String getOth80_amt() {
        return oth80_amt;
    }

    public void setOth80_amt(String oth80_amt) {
        this.oth80_amt = oth80_amt;
    }

    public String getItax_catg() {
        return itax_catg;
    }

    public void setItax_catg(String itax_catg) {
        this.itax_catg = itax_catg;
    }

    public String getDeductee_catg() {
        return deductee_catg;
    }

    public void setDeductee_catg(String deductee_catg) {
        this.deductee_catg = deductee_catg;
    }

    public String getDeductee_panno() {
        return deductee_panno;
    }

    public void setDeductee_panno(String deductee_panno) {
        this.deductee_panno = deductee_panno;
    }

    public String getIdentification_no() {
        return identification_no;
    }

    public void setIdentification_no(String identification_no) {
        this.identification_no = identification_no;
    }

    public String getDeductee_panno_verified() {
        return deductee_panno_verified;
    }

    public void setDeductee_panno_verified(String deductee_panno_verified) {
        this.deductee_panno_verified = deductee_panno_verified;
    }

    public String getDeductee_panno_valid() {
        return deductee_panno_valid;
    }

    public void setDeductee_panno_valid(String deductee_panno_valid) {
        this.deductee_panno_valid = deductee_panno_valid;
    }

    public String getDeductee_ref_code1() {
        return deductee_ref_code1;
    }

    public void setDeductee_ref_code1(String deductee_ref_code1) {
        this.deductee_ref_code1 = deductee_ref_code1;
    }

    public String getDeductee_ref_code2() {
        return deductee_ref_code2;
    }

    public void setDeductee_ref_code2(String deductee_ref_code2) {
        this.deductee_ref_code2 = deductee_ref_code2;
    }

    public String getU17_2_amt() {
        return u17_2_amt;
    }

    public void setU17_2_amt(String u17_2_amt) {
        this.u17_2_amt = u17_2_amt;
    }

    public String getTdsdt_amt() {
        return tdsdt_amt;
    }

    public void setTdsdt_amt(String tdsdt_amt) {
        this.tdsdt_amt = tdsdt_amt;
    }

    public String getS17_4_amt() {
        return s17_4_amt;
    }

    public void setS17_4_amt(String s17_4_amt) {
        this.s17_4_amt = s17_4_amt;
    }

    public String getDedct_amt() {
        return dedct_amt;
    }

    public void setDedct_amt(String dedct_amt) {
        this.dedct_amt = dedct_amt;
    }

    public String getPnsn_amt() {
        return pnsn_amt;
    }

    public void setPnsn_amt(String pnsn_amt) {
        this.pnsn_amt = pnsn_amt;
    }

    public String getCca_1_amt() {
        return cca_1_amt;
    }

    public void setCca_1_amt(String cca_1_amt) {
        this.cca_1_amt = cca_1_amt;
    }

    public String getHaa_1_amt() {
        return haa_1_amt;
    }

    public void setHaa_1_amt(String haa_1_amt) {
        this.haa_1_amt = haa_1_amt;
    }

    public String getPmpe_amt() {
        return pmpe_amt;
    }

    public void setPmpe_amt(String pmpe_amt) {
        this.pmpe_amt = pmpe_amt;
    }

    public String getL_alw_amt() {
        return l_alw_amt;
    }

    public void setL_alw_amt(String l_alw_amt) {
        this.l_alw_amt = l_alw_amt;
    }

    public String getSec80_amt() {
        return sec80_amt;
    }

    public void setSec80_amt(String sec80_amt) {
        this.sec80_amt = sec80_amt;
    }

    public String getOin80_amt() {
        return oin80_amt;
    }

    public void setOin80_amt(String oin80_amt) {
        this.oin80_amt = oin80_amt;
    }

    public String getDgpf_amt() {
        return dgpf_amt;
    }

    public void setDgpf_amt(String dgpf_amt) {
        this.dgpf_amt = dgpf_amt;
    }

    public String getDppf_amt() {
        return dppf_amt;
    }

    public void setDppf_amt(String dppf_amt) {
        this.dppf_amt = dppf_amt;
    }

    public String getDsi_amt() {
        return dsi_amt;
    }

    public void setDsi_amt(String dsi_amt) {
        this.dsi_amt = dsi_amt;
    }

    public String getDssy_amt() {
        return dssy_amt;
    }

    public void setDssy_amt(String dssy_amt) {
        this.dssy_amt = dssy_amt;
    }

    public String getDdb80_amt() {
        return ddb80_amt;
    }

    public void setDdb80_amt(String ddb80_amt) {
        this.ddb80_amt = ddb80_amt;
    }

    public String getTdsdq_amt() {
        return tdsdq_amt;
    }

    public void setTdsdq_amt(String tdsdq_amt) {
        this.tdsdq_amt = tdsdq_amt;
    }

    public String getTtdds_amt() {
        return ttdds_amt;
    }

    public void setTtdds_amt(String ttdds_amt) {
        this.ttdds_amt = ttdds_amt;
    }

    public String getTtinc_amt() {
        return ttinc_amt;
    }

    public void setTtinc_amt(String ttinc_amt) {
        this.ttinc_amt = ttinc_amt;
    }

    public String getTtinm_amt() {
        return ttinm_amt;
    }

    public void setTtinm_amt(String ttinm_amt) {
        this.ttinm_amt = ttinm_amt;
    }

    public String getEdces_amt() {
        return edces_amt;
    }

    public void setEdces_amt(String edces_amt) {
        this.edces_amt = edces_amt;
    }

    public String getAlw_amt() {
        return alw_amt;
    }

    public void setAlw_amt(String alw_amt) {
        this.alw_amt = alw_amt;
    }

    public String getEdual_amt() {
        return edual_amt;
    }

    public void setEdual_amt(String edual_amt) {
        this.edual_amt = edual_amt;
    }

    public String getCa_amt() {
        return ca_amt;
    }

    public void setCa_amt(String ca_amt) {
        this.ca_amt = ca_amt;
    }

    public String getRape_amt() {
        return rape_amt;
    }

    public void setRape_amt(String rape_amt) {
        this.rape_amt = rape_amt;
    }

    public String getS17_1_amt() {
        return s17_1_amt;
    }

    public void setS17_1_amt(String s17_1_amt) {
        this.s17_1_amt = s17_1_amt;
    }

    public String getT_alw_amt() {
        return t_alw_amt;
    }

    public void setT_alw_amt(String t_alw_amt) {
        this.t_alw_amt = t_alw_amt;
    }

    public String getDcvia_amt() {
        return dcvia_amt;
    }

    public void setDcvia_amt(String dcvia_amt) {
        this.dcvia_amt = dcvia_amt;
    }

    public String getTxp15_amt() {
        return txp15_amt;
    }

    public void setTxp15_amt(String txp15_amt) {
        this.txp15_amt = txp15_amt;
    }

    public String getDedap_amt() {
        return dedap_amt;
    }

    public void setDedap_amt(String dedap_amt) {
        this.dedap_amt = dedap_amt;
    }

    public String getDtfs_amt() {
        return dtfs_amt;
    }

    public void setDtfs_amt(String dtfs_amt) {
        this.dtfs_amt = dtfs_amt;
    }

    public String getUdd80_amt() {
        return udd80_amt;
    }

    public void setUdd80_amt(String udd80_amt) {
        this.udd80_amt = udd80_amt;
    }

    public String getPpf_amt() {
        return ppf_amt;
    }

    public void setPpf_amt(String ppf_amt) {
        this.ppf_amt = ppf_amt;
    }

    public String getPf_amt() {
        return pf_amt;
    }

    public void setPf_amt(String pf_amt) {
        this.pf_amt = pf_amt;
    }

    public String getTdpem_amt() {
        return tdpem_amt;
    }

    public void setTdpem_amt(String tdpem_amt) {
        this.tdpem_amt = tdpem_amt;
    }

    public String getSal_amt() {
        return sal_amt;
    }

    public void setSal_amt(String sal_amt) {
        this.sal_amt = sal_amt;
    }

    public String getHa_1_amt() {
        return ha_1_amt;
    }

    public void setHa_1_amt(String ha_1_amt) {
        this.ha_1_amt = ha_1_amt;
    }

    public String getTa_1_amt() {
        return ta_1_amt;
    }

    public void setTa_1_amt(String ta_1_amt) {
        this.ta_1_amt = ta_1_amt;
    }

    public String getGewhc_amt() {
        return gewhc_amt;
    }

    public void setGewhc_amt(String gewhc_amt) {
        this.gewhc_amt = gewhc_amt;
    }

    public String getVfn_1_amt() {
        return vfn_1_amt;
    }

    public void setVfn_1_amt(String vfn_1_amt) {
        this.vfn_1_amt = vfn_1_amt;
    }

    public String getMac_1_amt() {
        return mac_1_amt;
    }

    public void setMac_1_amt(String mac_1_amt) {
        this.mac_1_amt = mac_1_amt;
    }

    public String getS17_3_amt() {
        return s17_3_amt;
    }

    public void setS17_3_amt(String s17_3_amt) {
        this.s17_3_amt = s17_3_amt;
    }

    public String getBl1_2_amt() {
        return bl1_2_amt;
    }

    public void setBl1_2_amt(String bl1_2_amt) {
        this.bl1_2_amt = bl1_2_amt;
    }

    public String getE_alw_amt() {
        return e_alw_amt;
    }

    public void setE_alw_amt(String e_alw_amt) {
        this.e_alw_amt = e_alw_amt;
    }

    public String getT_emp_amt() {
        return t_emp_amt;
    }

    public void setT_emp_amt(String t_emp_amt) {
        this.t_emp_amt = t_emp_amt;
    }

    public String getIchrs_amt() {
        return ichrs_amt;
    }

    public void setIchrs_amt(String ichrs_amt) {
        this.ichrs_amt = ichrs_amt;
    }

    @Override
    public String toString() {
        globalUtilities.Util utl = new Util();
        return utl.printObjectAsString(this);
    }

}
