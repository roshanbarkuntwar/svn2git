/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchHierarchy;

import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import java.util.ArrayList;

/**
 *
 * @author sakshi.bandhate
 */
public class BranchHierarchyDB {
    
    private final Util utl;

    public BranchHierarchyDB() {
        this.utl = new Util();
    }

    public ArrayList<TreeMenuBean> getAllBranchHierarchy(String entity_code) {
        ArrayList<TreeMenuBean> treeMenuBeanList = new ArrayList<>();
        try {
            String sqlQuery = "select \n"
                        + "       client_code as id ,\n"
                        + "       nvl(parent_code, '#') parent ,\n"
                        + "       '('||T.BANK_BRANCH_CODE||')'||'-{'|| CLIENT_NAME || '}-[' || T.code_level_desc  || '] 'AS TEXT\n"
                        + "  from VIEW_CLIENT_MAST_TREE t "
                        + "where  entity_code='" + entity_code + "'";
                utl.generateLog(sqlQuery, "");

                DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                treeMenuBeanList = dbQueryList.getGenericList(new TreeMenuBean(), sqlQuery);
        } catch (Exception e) {
        }
        return treeMenuBeanList;
    }

    ArrayList<TreeMenuBean> getLoggedInBranchHierarchy(String entity_code, String client_code) {
        ArrayList<TreeMenuBean> treeMenuBeanList = new ArrayList<>();
        try {
            String sqlQuery = "select a.client_code as id,\n" +
                    "       nvl(a.parent_code, '#') parent,\n" +
                    "       '(' || a.BANK_BRANCH_CODE || ')' || '-{' || a.CLIENT_NAME || '}-[' ||\n" +
                    "       a.code_level_desc || '] ' AS TEXT\n" +
                    "  from view_client_mast_tree a, client_mast b \n" +
                    "  where b.entity_code = a.entity_code\n" +
                    "   and (b.client_code = a.client_code or b.parent_code = a.client_code or\n" +
                    "       b.g_parent_code = a.client_code or b.sg_parent_code = a.client_code or\n" +
                    "       b.ssg_parent_code = a.client_code or\n" +
                    "       b.ssg_parent_code = a.client_code or\n" +
                    "       b.sssg_parent_code = a.client_code)\n" +
                    "   and a.entity_code = '"+entity_code+"'\n" +
                    "   and b.client_code = '"+client_code+"'\n" +
                    " order by a.code_level";
                utl.generateLog(sqlQuery, "");

                DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                treeMenuBeanList = dbQueryList.getGenericList(new TreeMenuBean(), sqlQuery);
        } catch (Exception e) {
        }
        return treeMenuBeanList;
    }
    
}
