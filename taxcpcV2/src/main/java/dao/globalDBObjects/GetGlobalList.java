/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.globalDBObjects;

import com.lhs.taxcpcv2.bean.CodeNameDTO;
import dao.TdsMastDAO;
import dao.generic.DAOFactory;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author ayushi.jain
 */
public class GetGlobalList {

    public LinkedHashMap<String, String> getSectionList(String tdsType, Date quarterToDate) {
        LinkedHashMap<String, String> list = null;
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            TdsMastDAO tdsMastDAO = factory.getTdsMastDAO();
            list = tdsMastDAO.getTdsAsLinkedHashMap(tdsType, quarterToDate);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return list;

    }

    public LinkedHashMap<String, String> getOperatorList(String displayText, Util utl) {
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
        try {
            if (!utl.isnull(displayText)) {
                list.put("", displayText);
            }
            list.put(">=", ">=");
            list.put("<=", "<=");
            list.put("=", "=");
            list.put("between", "between");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedHashMap<String, String> getAllocationStatus(String displayText, Util utl) {
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
        try {
            if (!utl.isnull(displayText)) {
                list.put("", displayText);
            }
            list.put("UNA", "Unallocated");
            list.put("ALC", "Allocated");
//            list.put("ALL", "ALL");//REMOVE
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedHashMap<String, String> getUploadFileType() {
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();
        try {
            list.put("", " Select ");
            list.put("S", "Single");
            list.put("M", "Multiple");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedHashMap<String, String> getTemplateFlagList() {
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();

        try {
            String query = "select t.template_insert_flag as code, t.template_insert_flag_name as name\n"
                    + "  from view_template_insert_flag t order by t.template_insert_flag_name desc";
            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
            List<CodeNameDTO> data = db.getGenericList(new CodeNameDTO(), query);
            if (data != null && data.size() > 0) {
                for (CodeNameDTO codeNameDTO : data) {
                    list.put(codeNameDTO.getCode(), codeNameDTO.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LinkedHashMap<String, String> getTdsTypeList(String app_type) {
        LinkedHashMap<String, String> list = new LinkedHashMap<String, String>();

        try {
            String query = "select T.tds_type_code CODE,T.tds_type_name NAME  from View_TDS_TYPE T WHERE (T.app_type ='V2' OR T.app_type IS NULL)";
            DbGenericFunctionExecutor db = new DbGenericFunctionExecutor();
            List<CodeNameDTO> data = db.getGenericList(new CodeNameDTO(), query);
            if (data != null && data.size() > 0) {
                for (CodeNameDTO codeNameDTO : data) {
                    list.put(codeNameDTO.getCode(), codeNameDTO.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}
