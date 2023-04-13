/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhs.taxcpcv2;

import dao.LhssysParametersDAO;
import dao.generic.DAOFactory;
import dao.generic.DbFunctionExecutorAsString;
import globalUtilities.Util;
import hibernateObjects.EntityLogoMast;
import hibernateObjects.LhssysParameters;
import hibernateObjects.ViewClientMast;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author akash.dev
 */
public class LoginValidatorSupport {

    private final globalUtilities.Util utl;

    public LoginValidatorSupport() {
        utl = new Util();
    }
    DbFunctionExecutorAsString dbFunctionExecutorAsString = new DbFunctionExecutorAsString();

    public boolean checkApprovedDate(Date approvedDate, Long expDays) {
        boolean flag = false;
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(approvedDate);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            String dateStr = day + "-" + month + "-" + year + " 09:09:09";

            SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            //String dateStart = form.format(approvedDate);
            String dateStop = form.format(new Date());
            Date d1 = null;
            Date d2 = null;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            d1 = format.parse(dateStr);
            d2 = format.parse(dateStop);
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);
            if (diffDays <= expDays) {
                flag = true;
            } else {
                flag = false;
            }

        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }//end method

    public String checkLoginValidity(Date approvedate, Long l_loginExpDays, Long l_trialValidRunsDays) {
        String flag = "";
        try {
            if (approvedate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(approvedate);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH) + 1;
                int year = cal.get(Calendar.YEAR);
                String dateStr = day + "-" + month + "-" + year + " 09:09:09";

                SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                //String dateStart = form.format(approvedDate);
                String dateStop = form.format(new Date());
                Date d1 = null;
                Date d2 = null;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                d1 = format.parse(dateStr);
                d2 = format.parse(dateStop);
                long diff = d2.getTime() - d1.getTime();
                long diffDays = diff / (24 * 60 * 60 * 1000);

                if (diffDays <= l_trialValidRunsDays) {
                    long trialDiffDays = l_trialValidRunsDays - diffDays;
                    if (trialDiffDays <= l_loginExpDays) {
                        flag = "***** YOUR TRIAL PERIOD EXPIRES AFTER  " + trialDiffDays + " DAYS. *****";
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            flag = "";
        }
        return flag;
    }//end method

    public Long getValidateGenerateFUV(String entity_code) {
        Long l_return_value = 0l;
        try {
            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
            LhssysParameters lhssysparameter = lhssysParametersDAO.readDataAsParameterAndEntity("TRIAL_RUN_VALID_DAYS", entity_code);
            if (lhssysparameter != null) {
                String expiryDays = lhssysparameter.getParameter_value();
                expiryDays = utl.isnull(expiryDays) ? "0" : expiryDays;
                l_return_value = Long.parseLong(expiryDays);
            }
        } catch (Exception e) {
            l_return_value = 0l;
        }
        return l_return_value;
    }//end method

    public Long getLoginExpiryDays(ViewClientMast client) {
        Long l_return_value = 0l;
        try {
            if (client != null) {
                String l_login_exp_days = client.getLogin_pwd_expiry_days();
                if (utl.isnull(l_login_exp_days)) {
                    DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                    LhssysParametersDAO lhssysParametersDAO = factory.getLhssysParametersDAO();
                    LhssysParameters lhssysparameter = lhssysParametersDAO.readDataAsParameterAndEntity("LOGIN_EXPIRY_DAYS", client.getEntity_code());
                    if (lhssysparameter != null) {
                        String expiryDays = lhssysparameter.getParameter_value();
                        expiryDays = utl.isnull(expiryDays) ? "0" : expiryDays;
                        l_return_value = Long.parseLong(expiryDays);
                    }
                } else {
                    l_return_value = Long.parseLong(l_login_exp_days);
                }
            }
        } catch (Exception e) {
            l_return_value = 0l;
        }
        return l_return_value;
    }//end method

//    public Properties getProjectProperties(String filePath) {
//        Properties props = null;
//        try (FileInputStream fileInput = new FileInputStream(new File(filePath));) {
//            if (fileInput != null) {
//                props = new Properties();
//                props.load(fileInput);
//            }
//            System.out.println("Properties: " + props);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return props;
//    }//end method
    public String getSessionEntityLogo(String entity_code) {
        StringBuilder logo_builder = new StringBuilder();
        String defaultLogo = "resources/images/global/taxcpcLogoWhite.jpg";
        try {
            if (!utl.isnull(entity_code)) {
//                BASE64Encoder base64Encoder = new BASE64Encoder();
                DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
                EntityLogoMast entityLogoMast = factory.getEntityLogoMastDAO().readEntityLogoByEntityCode(entity_code);
                if (entityLogoMast != null && entityLogoMast.getLogo() != null) {

                    logo_builder.append("data:image/png;base64,");
                    logo_builder.append(DatatypeConverter.printBase64Binary(entityLogoMast.getLogo()));
                } else {
                    logo_builder.append(defaultLogo);
                }
            } else {
                logo_builder.append(defaultLogo);
            }
        } catch (Exception e) {
            logo_builder.append(defaultLogo);
        }
        return logo_builder.toString();
    }//end method    

    public List<String> getDynAccYearOptions(String startYear) throws Exception {
        try {
//            System.out.println("startYear--"+startYear);
            String dynAccYrSql = "select v.acc_year from view_acc_year v where v.active_flag = 'A'";

            DbFunctionExecutorAsString funcExecutor = new DbFunctionExecutorAsString();
            ArrayList<String> accYearList = funcExecutor.getResultAsList(dynAccYrSql);

            if (accYearList == null || accYearList.isEmpty()) {
                return null;
            } else if (utl.isnull(startYear)) {
                return accYearList;
            } else {
                return accYearList.stream().filter(yr -> yr.startsWith(startYear)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            throw (e);
        }
    }//end method
}//end class
