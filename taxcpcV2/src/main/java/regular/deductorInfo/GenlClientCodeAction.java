/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.deductorInfo;

import com.lhs.taxcpcv2.bean.Assessment;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import java.util.ArrayList;

/**
 *
 * @author aniket.naik
 */
public class GenlClientCodeAction {

    Util utl;

    GenlClientCodeAction() {
        utl = new Util();
    }//end constructor

    public String getgenLevelClientCodeValue(ViewClientMast viewClientMast, Assessment asmt, String action) {
        String l_return_message = "";
        try {
            String l_acc_year = asmt.getAccYear();
            String l_entity_code = viewClientMast.getEntity_code();
            //String l_client_code = viewClientMast.getClient_code();
            if (!utl.isnull(action)) {
                int currentCodeLevel = 1;
                //int nextCodeLevel = 1;
                try {
                    String getIs_client_login_level = new DbFunctionExecutorAsString().execute_oracle_function_as_string("SELECT lhs_utility.is_client_login_level('','',null,null,null,null,null,null,null,'R') is_client_login_level FROM dual");
                    currentCodeLevel = Integer.parseInt(getIs_client_login_level);
                    //nextCodeLevel = currentCodeLevel + 1;
                } catch (Exception e) {
                }
                String l_client_code_level_1 = "";
                String l_client_code_level_2 = "";
                String l_client_code_level_3 = "";
                String l_client_code_level_4 = "";
                String l_client_code_level_5 = "";
                String l_client_code_level_6 = "";
                String gen_level_client_where_clause = "";
                if (currentCodeLevel == 1) {
                    l_client_code_level_1 = viewClientMast.getClient_code();//1
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "') \n";
                } else if (currentCodeLevel == 2) {
                    l_client_code_level_1 = viewClientMast.getParent_code();//1
                    l_client_code_level_2 = viewClientMast.getClient_code();//2
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "', '" + l_client_code_level_2 + "') \n";
                } else if (currentCodeLevel == 3) {
                    l_client_code_level_1 = viewClientMast.getG_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getParent_code();//2
                    l_client_code_level_3 = viewClientMast.getClient_code();//3
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "', '" + l_client_code_level_3 + "') \n";
                } else if (currentCodeLevel == 4) {
                    l_client_code_level_1 = viewClientMast.getSg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getG_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getParent_code();//3
                    l_client_code_level_4 = viewClientMast.getClient_code();//4
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "', '" + l_client_code_level_4 + "') \n";
                } else if (currentCodeLevel == 5) {
                    l_client_code_level_1 = viewClientMast.getSsg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getSg_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getG_parent_code();//3
                    l_client_code_level_4 = viewClientMast.getParent_code();//4
                    l_client_code_level_5 = viewClientMast.getClient_code();//5
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "','" + l_client_code_level_4 + "', '" + l_client_code_level_5 + "') \n";
                } else if (currentCodeLevel == 6) {
                    l_client_code_level_1 = viewClientMast.getSssg_parent_code();//1
                    l_client_code_level_2 = viewClientMast.getSsg_parent_code();//2
                    l_client_code_level_3 = viewClientMast.getSg_parent_code();//3
                    l_client_code_level_4 = viewClientMast.getG_parent_code();//4
                    l_client_code_level_5 = viewClientMast.getParent_code();//5
                    l_client_code_level_6 = viewClientMast.getClient_code();//6
                    gen_level_client_where_clause = "and t.gen_level_client_code in('" + l_client_code_level_1 + "','" + l_client_code_level_2 + "','" + l_client_code_level_3 + "','" + l_client_code_level_4 + "','" + l_client_code_level_5 + "', '" + l_client_code_level_6 + "') \n";
                }
                if (action.equalsIgnoreCase("getConsolidateLevelCount")) {
                    String conClientCodeCount = "0";
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String client_count_query
                                = "select nvl(count(t.gen_level_client_code), '0')gen_level_client_code\n"
                                + "  from deductee_bflag_gen_level t\n"
                                + " where t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + l_acc_year + "'\n"
                                + gen_level_client_where_clause
                                + "   and (t.client_code_level1 = '" + l_client_code_level_1 + "'\n"
                                + "   or t.client_code_level2 = '" + l_client_code_level_2 + "'\n"
                                + "   or t.client_code_level3 = '" + l_client_code_level_3 + "'\n"
                                + "   or t.client_code_level4 = '" + l_client_code_level_4 + "'\n"
                                + "   or t.client_code_level5 = '" + l_client_code_level_5 + "'\n"
                                + "   or t.client_code_level6 = '" + l_client_code_level_6 + "')";
                        utl.generateSpecialLog("client_count_query..", client_count_query);
                        conClientCodeCount = objFn.execute_oracle_function_as_string(client_count_query);
                    } catch (Exception e) {
                    }
                    conClientCodeCount = utl.isnull(conClientCodeCount) ? "0" : conClientCodeCount;
                    l_return_message = conClientCodeCount;
                } else if (action.equalsIgnoreCase("getConsolidateLevelClient")) {
                    String conClientCode = "";
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String cons_client_query
                                = "select distinct t.gen_level_client_code\n"
                                + "  from deductee_bflag_gen_level t\n"
                                + " where t.entity_code = '" + l_entity_code + "'\n"
                                + "   and t.acc_year = '" + l_acc_year + "'\n"
                                + gen_level_client_where_clause
                                + "   and (t.client_code_level1 = '" + l_client_code_level_1 + "'\n"
                                + "   or t.client_code_level2 = '" + l_client_code_level_2 + "'\n"
                                + "   or t.client_code_level3 = '" + l_client_code_level_3 + "'\n"
                                + "   or t.client_code_level4 = '" + l_client_code_level_4 + "'\n"
                                + "   or t.client_code_level5 = '" + l_client_code_level_5 + "'\n"
                                + "   or t.client_code_level6 = '" + l_client_code_level_6 + "')";
                        utl.generateSpecialLog("cons_client_query..", cons_client_query);
                        conClientCode = objFn.execute_oracle_function_as_string(cons_client_query);
                        conClientCode = utl.isnull(conClientCode) ? "" : conClientCode;
                    } catch (Exception e) {
                    }
                    l_return_message = conClientCode;
                } else if (action.equalsIgnoreCase("getDeductorLevelBflagCount")) {
                    String leavelBflagRec = "0#0";
                    String get_client_where_clause = "";
                    if (currentCodeLevel == 1) {
                        get_client_where_clause = "and t.client_code_level1 = '" + l_client_code_level_1 + "' \n";
                    } else if (currentCodeLevel == 2) {
                        get_client_where_clause = "and t.client_code_level2 = '" + l_client_code_level_2 + "' \n";
                    } else if (currentCodeLevel == 3) {
                        get_client_where_clause = "and t.client_code_level3 = '" + l_client_code_level_3 + "' \n";
                    } else if (currentCodeLevel == 4) {
                        get_client_where_clause = "and t.client_code_level4 = '" + l_client_code_level_4 + "' \n";
                    } else if (currentCodeLevel == 5) {
                        get_client_where_clause = "and t.client_code_level5 = '" + l_client_code_level_5 + "' \n";
                    } else if (currentCodeLevel == 6) {
                        get_client_where_clause = "and t.client_code_level6 = '" + l_client_code_level_6 + "' \n";
                    }
                    try {
                        DbFunctionExecutorAsString objFn = new DbFunctionExecutorAsString();
                        String leavel_bflag_query
                                = "select NVL(sum(d.gen_record_count), '0') || '#' || NVL(COUNT(*), '0') rec_count\n"
                                + "  from deductee_bflag_refinfo_seq_no d\n"
                                + " where exists (select 1\n"
                                + "          from deductee_bflag_gen_level t\n"
                                + "         where t.acc_year = '" + l_acc_year + "'\n"
                                + get_client_where_clause
                                + "           and t.entity_code = '" + l_entity_code + "'\n"
                                + "           and d.client_code = t.gen_level_client_code)";
                        utl.generateSpecialLog("leavel_bflag_query..", leavel_bflag_query);
                        leavelBflagRec = objFn.execute_oracle_function_as_string(leavel_bflag_query);
                        leavelBflagRec = utl.isnull(leavelBflagRec) ? "0#0" : leavelBflagRec;
                    } catch (Exception e) {
                    }
                    l_return_message = leavelBflagRec;
                } else if (action.equalsIgnoreCase("getGeneratedBflagRecord")) {
                    ArrayList<GeneratedBflagPojo> entitylist = null;
                    String tableDetail = "No Record Found";
                    String get_client_where_clause = "";
                    if (currentCodeLevel == 1) {
                        get_client_where_clause = "and t.client_code_level1 = '" + l_client_code_level_1 + "' \n";
                    } else if (currentCodeLevel == 2) {
                        get_client_where_clause = "and t.client_code_level2 = '" + l_client_code_level_2 + "' \n";
                    } else if (currentCodeLevel == 3) {
                        get_client_where_clause = "and t.client_code_level3 = '" + l_client_code_level_3 + "' \n";
                    } else if (currentCodeLevel == 4) {
                        get_client_where_clause = "and t.client_code_level4 = '" + l_client_code_level_4 + "' \n";
                    } else if (currentCodeLevel == 5) {
                        get_client_where_clause = "and t.client_code_level5 = '" + l_client_code_level_5 + "' \n";
                    } else if (currentCodeLevel == 6) {
                        get_client_where_clause = "and t.client_code_level6 = '" + l_client_code_level_6 + "' \n";
                    }
                    try {
                        String getGeneratedBflagRecord_query
                                = "select lhs_utility.get_name('client_code', d.client_code)client_code, d.bflag, d.reference_no, d.gen_record_count\n"
                                + "  from deductee_bflag_refinfo_seq_no d\n"
                                + " where exists (select 1\n"
                                + "          from deductee_bflag_gen_level t\n"
                                + "         where t.acc_year = '" + l_acc_year + "'\n"
                                + get_client_where_clause
                                + "           and t.entity_code = '" + l_entity_code + "'\n"
                                + "           and d.client_code = t.gen_level_client_code)";
                        utl.generateSpecialLog("getGeneratedBflagRecord_query..", getGeneratedBflagRecord_query);
                        DbFunctionExecutorAsString execte = new DbFunctionExecutorAsString();
                        entitylist = execte.showFlagList(getGeneratedBflagRecord_query);

                        tableDetail = getTableGenDetail(entitylist);
                    } catch (Exception e) {
                    }
                    l_return_message = tableDetail;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return l_return_message;
    }//end method

    private String getTableGenDetail(ArrayList<GeneratedBflagPojo> entitylist) {
        String returnData = "";
        try {
            StringBuilder sb = new StringBuilder();

            sb.append(" <table class=\"table table-striped\" id=\"table\" style=\"margin-bottom: 0px;\">");
            sb.append("<thead>");
            sb.append("<tr>");
            sb.append("<th style=\"min-width: 40px;\">Slno</th>");
            sb.append("<th style=\"min-width: 300px;\">Client Name</th>");
            sb.append("<th style=\"min-width: 120px;\">File Type(G/H)</th>");
            sb.append("<th style=\"min-width: 120px;\">Refrence No</th>");
            sb.append("<th style=\"min-width: 120px;\">Refrence No Count</th>");
            sb.append("</tr>");
            sb.append("</thead>");
            sb.append("<tbody style=\"border: 1px solid #e0e0e0; border-radius: 3px;\">");

            if (entitylist != null) {
                int slcount = 0;
                for (GeneratedBflagPojo entity : entitylist) {
                    slcount++;
                    sb.append("<tr style=\"height: 25px;\">");
                    sb.append("<td>").append(slcount).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getClient_code()) ? "" : entity.getClient_code().toUpperCase()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getBflag()) ? "" : entity.getBflag()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getReference_no()) ? "" : entity.getReference_no()).append("</td>");
                    sb.append("<td>").append(utl.isnull(entity.getGen_record_count()) ? "0" : entity.getGen_record_count()).append("</td>");
                    sb.append("</tr>");

                }
                if (slcount <= 20) {
                    for (int i = slcount; i <= 20; i++) {
                        sb.append("<tr style=\"height: 25px;\">");
                        sb.append("<td >").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("<td>").append("</td>");
                        sb.append("</tr>");
                    }
                }
            } else {
                sb.append("<div class=\"well well-lg\" >");
                sb.append("No records found");
                sb.append("</div>");

            }
            sb.append("</tbody>");
            sb.append("</table>");

            returnData = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnData;
    }//end method

}
