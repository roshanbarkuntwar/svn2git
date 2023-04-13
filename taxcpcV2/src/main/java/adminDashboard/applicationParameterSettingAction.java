

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminDashboard;

import com.opensymphony.xwork2.ActionSupport;
import dao.LhssysParametersDAO;
import dao.LhssysProcessLogDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import dao.generic.DbGenericFunctionExecutor;
import globalUtilities.Util;
import hibernateObjects.LhssysParameters;
import hibernateObjects.LhssysProcessLog;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author akash.meshram
 */
public class applicationParameterSettingAction extends ActionSupport {

    @Override
    public String execute() throws Exception {
        String l_return_value = "success";
        String l_return_msg = "";
        if (utl.isnull(getAction())) {

            //DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            try {
                // LhssysParametersDAO LhssysParametersdao = factory.getLhssysParametersDAO();
                // List<LhssysParameters> list = LhssysParametersdao.readParametersByParameterType("JAVA");
                String query = "select rowid,T.* from lhssys_parameters T";
                // DbGenericFunctionExecutor dbQueryList = new DbGenericFunctionExecutor();
                List<LhssysParameters> list = new DbGenericFunctionExecutor().getGenericList(new LhssysParameters(), query);

                if (list != null) {
                    if (list.size() > 0) {
                        javaflagparameter = list
                                .stream()
                                .filter(c -> c.getParameter_value() != null)
                                .filter(c -> c.getParameter_value().matches("T") || c.getParameter_value().matches("F"))
                                .filter(c -> c.getParameter_type().matches("JAVA"))
                                .collect(Collectors.toList());
                        utl.generateLog("java paramter True False value", "");
                        javaflagparameter.forEach(s -> System.out.println(s.getParameter_value()));

                        javatextparameter = list
                                .stream()
                                .filter(c -> c.getParameter_value() != null)
                                .filter(c -> !c.getParameter_value().contains("T"))
                                .filter(c -> !c.getParameter_value().contains("F"))
                                .filter(c -> c.getParameter_type().matches("JAVA"))
                                .collect(Collectors.toList());
                        utl.generateLog("java parameter Text Field value", "");
                        javatextparameter.forEach(s -> System.out.println(s.getParameter_value()));

                        oracleflagparameter = list
                                .stream()
                                .filter(c -> c.getParameter_value() != null)
                                .filter(c -> c.getParameter_value().matches("T") || c.getParameter_value().matches("F"))
                                .filter(c -> c.getParameter_type().matches("ORACLE"))
                                .collect(Collectors.toList());
                        utl.generateLog("oracle parameter True False value", "");
                        oracleflagparameter.forEach(s -> System.out.println(s.getParameter_value()));

                        oracletextparameter = list
                                .stream()
                                .filter(c -> c.getParameter_value() != null)
                                .filter(c -> !c.getParameter_value().contains("T"))
                                .filter(c -> !c.getParameter_value().contains("F"))
                                .filter(c -> c.getParameter_type().matches("ORACLE"))
                                .collect(Collectors.toList());
                        utl.generateLog("oracle parameter Text Field value", "");
                        oracletextparameter.forEach(s -> System.out.println(s.getParameter_value()));

                    }
                }

            } catch (Exception e) {

            }

        } else if (!utl.isnull(getAction()) && getAction().equalsIgnoreCase("update")) {
            String updatequery = "update lhssys_parameters  set parameter_value= '" + getParameter_value() + "'\n"
                    + "where parameter_name='" + getParameter_name() + "'and rowid='"+getRowid()+"'" ;
             utl.generateLog("update query", updatequery);
            DbFunctionExecutorAsString objDbFunctionClass = new DbFunctionExecutorAsString();
            boolean updateResult = objDbFunctionClass.execute_oracle_update_function_as_string(updatequery);
            utl.generateLog("updateResult...", updateResult);
            if(updateResult){
                l_return_msg = "updated";
            }else{
                l_return_msg = "error";
            }
            l_return_value = "ajaxsuccess";

        }

        inputStream = new ByteArrayInputStream(l_return_msg.getBytes("UTF-8"));
        return l_return_value;
    }

    String datagrid() {

        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");
        sb.append("");

        return "";
    }

    private String token_no;
    private String action;
    private InputStream inputStream;
    private String parameter_name;
    private String parameter_value;
    private String rowid;

    Util utl;
    List<LhssysParameters> javaflagparameter;
    List<LhssysParameters> javatextparameter;
    List<LhssysParameters> oracleflagparameter;
    List<LhssysParameters> oracletextparameter;

    public applicationParameterSettingAction() {
        utl = new Util();
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }

    public Util getUtl() {
        return utl;
    }

    public void setUtl(Util utl) {
        this.utl = utl;
    }

    public List<LhssysParameters> getJavaflagparameter() {
        return javaflagparameter;
    }

    public void setJavaflagparameter(List<LhssysParameters> javaflagparameter) {
        this.javaflagparameter = javaflagparameter;
    }

    public List<LhssysParameters> getJavatextparameter() {
        return javatextparameter;
    }

    public void setJavatextparameter(List<LhssysParameters> javatextparameter) {
        this.javatextparameter = javatextparameter;
    }

    public List<LhssysParameters> getOracleflagparameter() {
        return oracleflagparameter;
    }

    public void setOracleflagparameter(List<LhssysParameters> oracleflagparameter) {
        this.oracleflagparameter = oracleflagparameter;
    }

    public List<LhssysParameters> getOracletextparameter() {
        return oracletextparameter;
    }

    public void setOracletextparameter(List<LhssysParameters> oracletextparameter) {
        this.oracletextparameter = oracletextparameter;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }

    public String getParameter_value() {
        return parameter_value;
    }

    public void setParameter_value(String parameter_value) {
        this.parameter_value = parameter_value;
    }

    public String getRowid() {
        return rowid;
    }

    public void setRowid(String rowid) {
        this.rowid = rowid;
    }

}
