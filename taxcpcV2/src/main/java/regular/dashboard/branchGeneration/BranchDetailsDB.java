/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.dashboard.branchGeneration;

import globalUtilities.Util;
import hibernateObjects.ViewClientMast;

/**
 *
 * @author gaurav.khanzode
 */
public class BranchDetailsDB {

    private final Util utl;

    public BranchDetailsDB() {
        this.utl = new Util();
    }

    public String getBranchDetailsCountQuery(ViewClientMast client, BranchDetailsBean branchDetailsFilters, int minVal, int maxVal) {
        StringBuilder countQuery = new StringBuilder();
        countQuery.append("select count(*) from ( ")
                .append(getBranchDetailsQuery(client, branchDetailsFilters, minVal, maxVal))
                .append(") ");
        return countQuery.toString();
    }

    public String getBranchDetailsQuery(ViewClientMast client, BranchDetailsBean branchDetailsFilters, int minVal, int maxVal) {
        StringBuilder whrclsBuilder = new StringBuilder("");
        if (branchDetailsFilters != null) {
//            System.out.println("Filter object:\n" + branchDetailsFilters);
//            System.out.println("Min Val:\n" + minVal);
//            System.out.println("Max Val:\n" + maxVal);

            if (!utl.isnull(branchDetailsFilters.getCode_level())) {
                whrclsBuilder.append(" and a.Code_level = '").append(branchDetailsFilters.getCode_level()).append("' \n");
            }
            if (!utl.isnull(branchDetailsFilters.getBank_branch_code())) {
                whrclsBuilder.append(" and a.bank_branch_code = '").append(branchDetailsFilters.getBank_branch_code()).append("' \n");
            }
            if (!utl.isnull(branchDetailsFilters.getClient_name())) {
//                whrclsBuilder.append(" and lower(a.client_name) like '%").append(branchDetailsFilters.getClient_name().toLowerCase()).append("%' \n");
                whrclsBuilder.append(" and regexp_like(a.client_name,'").append(branchDetailsFilters.getClient_name()).append("','i') \n");
            }
            if (!utl.isnull(branchDetailsFilters.getParent_code())) {
                whrclsBuilder.append(" and a.Parent_code = '").append(branchDetailsFilters.getParent_code()).append("' \n");
//                whrclsBuilder.append(" and regexp_like(a.Parent_code,'").append(branchDetailsFilters.getParent_code()).append("','i') \n");
            }
            if (!utl.isnull(branchDetailsFilters.getPanno())) {
                whrclsBuilder.append(" and a.panno = '").append(branchDetailsFilters.getPanno().toUpperCase()).append("' \n");
            }
            if (!utl.isnull(branchDetailsFilters.getTanno())) {
                whrclsBuilder.append(" and a.tanno = '").append(branchDetailsFilters.getTanno().toUpperCase()).append("' \n");
            }

        }

        StringBuilder detailsQuery = new StringBuilder();
        detailsQuery.append("select * from( select row_number() over(order by 1) sr_no,\n")
                .append("               a.entity_code,\n")
                .append("               a.client_code,\n")
                .append("               a.code_level,\n")
                .append("               a.bank_branch_code,\n")
                .append("               a.client_name,\n")
                .append("               a.parent_branch_code,\n")
                .append("               a.parent_code,\n")
                .append("               a.sg_parent_code,\n")
                .append("               a.ssg_parent_code,\n")
                .append("               a.sssg_parent_code,\n")
                .append("               a.panno,\n")
                .append("               a.tanno,\n")
                .append("               a.state_code,\n")
                .append("               a.state_name,\n")
                .append("               a.client_catg_code,\n")
                .append("               a.client_category_name\n")
                .append("          from (select entity_code,\n")
                .append("                       client_code,\n")
                .append("                       a.code_level,\n")
                .append("                       bank_branch_code,\n")
                .append("                       client_name,\n")
                .append("                       (select bank_branch_code\n")
                .append("                          from client_mast b\n")
                .append("                         where b.entity_code = a.entity_code\n")
                .append("                           and b.client_code = a.parent_code) parent_branch_code,\n")
                .append("                       parent_code,\n")
                .append("                       sg_parent_code,\n")
                .append("                       ssg_parent_code,\n")
                .append("                       sssg_parent_code,\n")
                .append("                       panno,\n")
                .append("                       tanno,\n")
                .append("                       state_code,\n")
                .append("                       (select state_name\n")
                .append("                          from state_mast b\n")
                .append("                         where b.state_code = a.state_code) state_name,\n")
                .append("                       a.client_catg_code,\n")
                .append("                       (select b.client_catg_name\n")
                .append("                          from view_client_catg b\n")
                .append("                         where b.client_catg_code = a.client_catg_code) client_category_name\n")
                .append("                  from client_mast a\n")
                .append("                 where a.entity_code = '").append(client.getEntity_code()).append("'\n")
                .append("                   and (a.client_code = '").append(client.getClient_code()).append("' or a.parent_code = '").append(client.getClient_code()).append("' or\n")
                .append("                       a.g_parent_code = '").append(client.getClient_code()).append("' or\n")
                .append("                       a.sg_parent_code = '").append(client.getClient_code()).append("' or\n")
                .append("                       a.ssg_parent_code = '").append(client.getClient_code()).append("' or\n")
                .append("                       a.sssg_parent_code = '").append(client.getClient_code()).append("')\n")
                .append(whrclsBuilder.toString())
                .append("                 order by a.code_level, a.bank_branch_code) a )");

        if (minVal > 0 && maxVal > 0) {
            detailsQuery.append(" where sr_no between ").append(minVal).append(" and ").append(maxVal);
        }
        return detailsQuery.toString();
    }
}
