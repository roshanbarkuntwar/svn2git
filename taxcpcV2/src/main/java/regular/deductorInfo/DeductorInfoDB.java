package regular.deductorInfo;

/**
 *
 * @author aniket.naik
 */
public class DeductorInfoDB {

    public String clientGroupListQuery(String entity_code, String client_level_type, String client_login_level, String login_client_code) {

        String client_group_list_query
                = "select client_code, client_name\n"
                + "  from (select client_code,\n"
                + "               client_name,\n"
                + "               parent_code,\n"
                + "               g_parent_code,\n"
                + "               sg_parent_code,\n"
                + "               a.is_client_parent_record,\n"
                + "               a.is_client_tran_level,\n"
                + "               a.is_client_login_level,\n"
                + "               a.client_level_type,\n"
                + "               code_level,\n"
                + "               a.client_parent_code_Str\n"
                + "          from client_mast a\n"
                + "         where entity_code = '" + entity_code + "') a\n"
                + " where is_client_login_level < client_level_type\n"
                + "   and client_level_type = " + client_level_type + " \n"
                + "   and is_client_login_level > " + client_login_level + "\n"
                + "   and client_parent_code_Str like '%~" + login_client_code + "~%'\n"
                + " order by code_level";
        return client_group_list_query;

    }

}
