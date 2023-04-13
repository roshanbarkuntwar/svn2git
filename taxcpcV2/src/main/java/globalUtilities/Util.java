/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package globalUtilities;

//import com.lighthouseindiangp.taxcpc.getLogFlag;
import com.lhs.taxcpcv2.bean.Assessment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author akash.dev
 */
public class Util {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern;
    private Matcher matcher;

    //***************************Random Number Generator**************************************************************
    int intRandomNumber = 0;
    double doubleRandomNumber = 0.0;

    public int getRandomNumber(int from, int to) throws ArithmeticException {
        try {
            Random randomNumber = new Random();
            if (from >= to) {
                ArithmeticException aex = new ArithmeticException();
                throw aex;
            }
            for (int i = from; i < to; ++i) {
                intRandomNumber = randomNumber.nextInt(to);
            }
            if (intRandomNumber <= from || intRandomNumber >= to) {
                getRandomNumber(from, to);
            }
        } catch (ArithmeticException ae) {
            intRandomNumber = 0;
            ae.getStackTrace();
        }
        return intRandomNumber;
    }

    public double getDoubleRandomNumber(double to) throws ArithmeticException {
        try {
            Random randomNumber = new Random();
            int int_from = 0;
            int int_to = (int) to;

            for (int i = int_from; i < int_to; ++i) {
                doubleRandomNumber = randomNumber.nextDouble();
            }
            if (doubleRandomNumber >= to) {
                getDoubleRandomNumber(to);
            }
        } catch (ArithmeticException ae) {
            doubleRandomNumber = 0.0;
            ae.getStackTrace();
        }
        return doubleRandomNumber;
    }

    public String getAlphaNumericRandomNumber(int length) {
        String randomNumber = "";
        Random generator = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i = 0;
            while (i < length) {
                char temp = (char) (generator.nextInt(92) + 32);
                if (Character.isLetterOrDigit(temp)) {
                    stringBuilder.append(temp);
                    ++i;
                }
            }
        } catch (Exception e) {
        }
        if (stringBuilder.length() > 0) {
            randomNumber = stringBuilder.toString();
        }
        return randomNumber;
    }

    //*****************************************************************************************
    public boolean isnull(String comparion_value) {
        boolean null_value = true;
        try {
            if (comparion_value != null && !"".equals(comparion_value) && !"null".equalsIgnoreCase(comparion_value) && comparion_value.length() > 0) {
                null_value = false;
            }
        } catch (NullPointerException npe) {
            null_value = true;
        } catch (Exception ex) {
            null_value = true;
        }
        return null_value;
    }// end isnull method

    public boolean isNumber(String value) {
        boolean ret = true;
        try {
            Double v = Double.parseDouble(value);
            //System.out.println("given value is number(Double) : " + v);
        } catch (NumberFormatException e) {
            ret = false;
            //System.out.println(e.getMessage());
        }
        return ret;
    }// end method isNumber

    public boolean isNumber_integer(String value) {
        boolean ret = true;
        try {
            int v = Integer.parseInt(value);
            //System.out.println("given value is number(Integer) : " + v);
        } catch (NumberFormatException e) {
            ret = false;
            //System.out.println(e.getMessage());
        }
        return ret;
    }

    /**
     * Check if given string is Number
     *
     * @param value
     * @return
     */
    public boolean isNumeric(String value) {
        return value.matches("[0-9]+");
    }// end method

    public boolean isEmail(String email) {
        boolean ret = true;
        try {
            if (email.contains("@")) {
                String[] tmp = email.split("@");
                if (tmp.length == 2) {
                    if (tmp[0].trim().length() > 0 && tmp[1].trim().length() > 0 && tmp[1].contains(".")) {
                        String tmp2[] = tmp[1].trim().split("\\.");
                        if (tmp2.length == 2) {
                            if (tmp2[0].trim().length() <= 0 || tmp2[1].trim().length() <= 0) {
                                ret = false;
                            }
                        } else {
                            ret = false;
                        }
                    } else {
                        ret = false;
                    }
                } else {
                    ret = false;
                }
            } else {
                ret = false;
            }
        } catch (Exception e) {
            ret = false;
            //System.out.println("email validation exception : " + e.getMessage());
        }
        //System.out.println("email[" + email + "] validated : " + ret);
        return ret;
    }//end method

    public boolean EmailValidator(String email) {
        boolean result = false;
        try {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(email);
            result = matcher.matches();
        } catch (Exception e) {
        }
        return result;
    }//end method

    public String toInitCap(String a_string) {
        char[] init_chars = a_string.toLowerCase().toCharArray();
        boolean char_found_spcl = false;
        for (int i = 0; i < init_chars.length; i++) {
            if (!char_found_spcl && Character.isLetter(init_chars[i])) {
                init_chars[i] = Character.toUpperCase(init_chars[i]);
                char_found_spcl = true;
            } else if (Character.isWhitespace(init_chars[i]) || '_' == init_chars[i]) { // You can add other  Special character here
                char_found_spcl = false;
            }
        }
        return String.valueOf(init_chars);
    }

    public String ChangeAccYearToAssessmentAccYear(String Acc_year) {
        String assesment_acc_year = "";
        try {
            String[] acc_year_split = Acc_year.split("-");
            String acc_year_p1 = acc_year_split[0];
            int newaccyear_p1 = Integer.valueOf(acc_year_p1) + 1;
            String acc_year_p2 = acc_year_split[1];
            int newaccyear_p2 = Integer.valueOf(acc_year_p2) + 1;
            assesment_acc_year = newaccyear_p1 + "-" + newaccyear_p2;
        } catch (Exception e) {
            assesment_acc_year = "";
        }
        return assesment_acc_year;
    }

    public int get_integer_value_of_string_positive(String string_value) {
        int return_value;
        try {
            return_value = Integer.parseInt(string_value);
        } catch (Exception e) {
            return_value = -1;
            e.printStackTrace();
        }
        return return_value;
    }//end method

    public String printObjectAsString(Object o) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("**********************************************************\n");
        sb.append("**********************************************************\n");
        sb.append("Object :: ").append(o.getClass().getSimpleName()).append("\n");
        sb.append("----------------------------------------------------------");
        sb.append("\nvalues contained by object are as follows\n");
        sb.append("----------------------------------------------------------\n");
        Method[] mthds = o.getClass().getDeclaredMethods();
        for (Method mthd : mthds) {
            try {
                if (mthd.getName().startsWith("get")) {
                    sb.append(mthd.getName().replace("get", "")).append(" : ");
                    if (mthd.getReturnType().equals(Date.class)) {
                        sb.append((mthd.invoke(o, (Object[]) null)).toString()).append("\n");
                    } else {
                        sb.append(mthd.invoke(o, (Object[]) null)).append("\n");
                    }
                }
            } catch (Exception e) {
            }
        }//end for
        sb.append("______________________________________________________\n");
        return sb.toString();
    }//end method

    public String getAmountFormat(Locale loc, Double amount) {
        String format = null;
        try {
            format = NumberFormat.getCurrencyInstance(loc).format(amount);
        } catch (Exception e) {
        }
        return format;
    }//end method

    /**
     * This method is used to get amount in specified format
     *
     * @param amtFormat
     * @param amount
     * @return String Amount in given format
     */
    public String getCustomAmountFormat(String amtFormat, Double amount) {
        String format = null;
        try {
            DecimalFormat myFormatter = new DecimalFormat(amtFormat);
            format = myFormatter.format(amount);
        } catch (Exception e) {
        }
        return format;
    }//end method

    public String getIndianAmountFormat(Double amt) {
        String returnValue = "0.00";
        try {
            Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            String formatAmt = format.format(amt);
            if (!isnull(formatAmt) && formatAmt.length() > 0) {
                formatAmt = formatAmt.trim();
                String replaceVal = "₹";
                String replaceVa2 = "₹";
                if (formatAmt.contains(replaceVal)) {
                    formatAmt = formatAmt.replaceAll(replaceVal, "");
                } else if (formatAmt.contains(replaceVa2)) {
                    formatAmt = formatAmt.replaceAll(replaceVa2, "");
                }
                returnValue = formatAmt.trim();
            }
        } catch (Exception e) {
        }
        return returnValue;
    }//end method

    public String getAmountIndianFormat(Double amt) {
        String return_value = "0.00";
        try {
            Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
            String formatAmt = format.format(amt);
            if (formatAmt != null && !"".equals(formatAmt) && !"null".equalsIgnoreCase(formatAmt) && formatAmt.length() > 0) {
                formatAmt = formatAmt.trim();
                String replaceVal = "₹";
                String replaceVa2 = "₹";
                if (formatAmt.contains(replaceVal)) {
                    formatAmt = formatAmt.replaceAll(replaceVal, "");
                } else if (formatAmt.contains(replaceVa2)) {
                    formatAmt = formatAmt.replaceAll(replaceVa2, "");
                }
                return_value = formatAmt.trim();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return return_value;
    }//end method

    public String formatTdsTypeCode(String tds_name, String tds_remark) {
        String return_str = "";
        try {
            if (!isnull(tds_name) && !isnull(tds_remark)) {
                if (tds_name.length() < 6) {
                    String space = "";
                    for (int i = tds_name.length(); i <= 6; i++) {
                        space += " ";
                    }

                    return_str = tds_name + space + tds_remark;

                }
            } else {
                return_str = tds_name + tds_remark;
            }
        } catch (Exception e) {
            return_str = tds_name + tds_remark;
        }

        return return_str;

    }

    public void generateLog(String subject, Object o) {
        System.out.print(!isnull(subject) ? subject.toUpperCase() + " : " : "");
        System.out.println(o);
    }//end method

    public void generateSpecialLog(String subject, Object o) {
        try {
            String flag = GetSpecialLogFlag.getLogFlagValue();
            if (!isnull(flag) && flag.equalsIgnoreCase("T")) {
                System.out.print(!isnull(subject) ? subject.toUpperCase() + " : " : "");
                System.out.println(o);
            }
        } catch (Exception e) {
        }
    }//end method

    public boolean isValidRefNo(String value) {
        boolean status = false;
        try {
            if (!isnull(value)) {
                if (value.matches("[0-9]+")) {
                    status = true;
                }
            } else {
                status = true;
            }

        } catch (Exception e) {
            status = true;
            e.printStackTrace();
        }
        return status;
    }//end method

    public Date getQuarterFromDate(String accYear, Integer quarterNo) {
        Date d = null;
        try {
            if (!isnull(accYear)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String arr[] = accYear.split("-");
                if (null != quarterNo) {
                    switch (quarterNo) {
                        case 1: {
                            String dateStr = "01-04-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 2: {
                            String dateStr = "01-07-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 3: {
                            String dateStr = "01-10-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 4: {
                            String dateStr = "01-01-20" + arr[1];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return d;
    }
//

    public Date getQuarterToDate(String accYear, Integer quarterNo) {
        Date d = null;
        try {
            if (!isnull(accYear)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String arr[] = accYear.split("-");
                if (null != quarterNo) {
                    switch (quarterNo) {
                        case 1: {
                            String dateStr = "30-06-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 2: {
                            String dateStr = "30-09-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 3: {
                            String dateStr = "31-12-20" + arr[0];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        case 4: {
                            String dateStr = "31-03-20" + arr[1];
                            d = sdf.parse(dateStr);
                            break;
                        }
                        default:
                            break;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return d;
    }

    public Date getYearBegDate(String accYear) {
        Date d = null;

        try {
            if (!isnull(accYear)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String arr[] = accYear.split("-");

                String dateStr = "01-04-20" + arr[0];
                d = sdf.parse(dateStr);

            }
        } catch (Exception e) {

        }

        return d;
    }

    public Date getYearEndDate(String accYear) {
        Date d = null;

        try {
            if (!isnull(accYear)) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                String arr[] = accYear.split("-");

                String dateStr = "31-03-20" + arr[1];
                d = sdf.parse(dateStr);

            }
        } catch (Exception e) {

        }

        return d;
    }

    public boolean check_date_less_then_selected_dates(Date fromDate, Date toDate, Date selectedDate) { // This method is overload to check proper validation in manage deductee
        boolean flag = false;
        try {
            Calendar from_cal = Calendar.getInstance();
            from_cal.setTime(fromDate);
            Calendar to_cal = Calendar.getInstance();
            to_cal.setTime(toDate);
            Calendar sel_cal = Calendar.getInstance();
            sel_cal.setTime(selectedDate);
            if ((from_cal.before(sel_cal) || from_cal.equals(sel_cal)) && (to_cal.after(sel_cal) || to_cal.equals(sel_cal))) {
                flag = true;
            }

        } catch (Exception e) {
            flag = false;
        }

        return flag;
    }//end medhod;

//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        return d;
    // }
//    public Date getQuarterToDate(String accYear, Integer quarterNo) {
//        Date d = null;
//        try {
//            if (!isnull(accYear)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String arr[] = accYear.split("-");
//                if (null != quarterNo) {
//                    switch (quarterNo) {
//                        case 1: {
//                            String dateStr = "30-06-20" + arr[0];
//                            d = sdf.parse(dateStr);
//                            break;
//                        }
//                        case 2: {
//                            String dateStr = "30-09-20" + arr[0];
//                            d = sdf.parse(dateStr);
//                            break;
//                        }
//                        case 3: {
//                            String dateStr = "31-12-20" + arr[0];
//                            d = sdf.parse(dateStr);
//                            break;
//                        }
//                        case 4: {
//                            String dateStr = "31-03-20" + arr[1];
//                            d = sdf.parse(dateStr);
//                            break;
//                        }
//                        default:
//                            break;
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        return d;
//    }
//    public Date getYearBegDate(String accYear) {
//        Date d = null;
//
//        try {
//            if (!isnull(accYear)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String arr[] = accYear.split("-");
//
//                String dateStr = "01-04-20" + arr[0];
//                d = sdf.parse(dateStr);
//
//            }
//        } catch (Exception e) {
//
//        }
//
//        return d;
//    }
//    public Date getYearEndDate(String accYear) {
//        Date d = null;
//
//        try {
//            if (!isnull(accYear)) {
//                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//                String arr[] = accYear.split("-");
//
//                String dateStr = "31-03-20" + arr[1];
//                d = sdf.parse(dateStr);
//
//            }
//        } catch (Exception e) {
//
//        }
//
//        return d;
//    }
//     public boolean check_date_less_then_selected_dates(Date fromDate, Date toDate, Date selectedDate) { // This method is overload to check proper validation in manage deductee
//        boolean flag = false;
//        try {
//            Calendar from_cal = Calendar.getInstance();
//            from_cal.setTime(fromDate);
//            Calendar to_cal = Calendar.getInstance();
//            to_cal.setTime(toDate);
//            Calendar sel_cal = Calendar.getInstance();
//            sel_cal.setTime(selectedDate);
//            if ((from_cal.before(sel_cal) || from_cal.equals(sel_cal)) && (to_cal.after(sel_cal) || to_cal.equals(sel_cal))) {
//                flag = true;
//            }
//
//        } catch (Exception e) {
//            flag = false;
//        }
//
//        return flag;
//    }//end medhod;
    public String get_sysdate(String format) {
        String today = "";
        try {
            Calendar currentDate = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            today = formatter.format(currentDate.getTime());
        } catch (Exception ex) {
            today = "";
            //System.out.println(ex.getMessage());
        }
        return today;
    }// end get_sysdate

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
            generateLog("Exception while 90 days login validator...", e.getMessage());
        }
        return flag;
    }//end method

    public String getLogFileName(Assessment asmt, String clientCode, String entityCode, String processType, String tokenNo, String templateCode) {
        String returnFileName = "";
        try {
//            Sample log_ file_ name_ - B4PNBHO19326QIMP1234;

            String logAccYear = asmt.getAccYear().split("-")[0];
            returnFileName = entityCode + clientCode + logAccYear + asmt.getQuarterNo() + asmt.getTdsTypeCode() + processType + templateCode + tokenNo;
            returnFileName = returnFileName.toUpperCase() + ".log";
        } catch (Exception e) {
        }
        return returnFileName;
    }//end method

    public void createDirectory(String filePath) {
        File fileLoc = null;
        if (!isnull(filePath)) {
            fileLoc = new File(filePath);
            if (!fileLoc.exists()) {
                fileLoc.mkdir();
            }
        }
    }// end method

    public String getLogFile(File logFileName) {
        String logFile = "";
        if (logFileName.exists()) {
            Long millsec = logFileName.lastModified();
            Date lastModified = new Date(millsec);
            String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(lastModified);
            String fileSize = Math.ceil(logFileName.length() / 1024) + " KB";

            try (BufferedReader br = new BufferedReader(new FileReader(logFileName))) {
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                logFile = date + "#" + fileSize + "#" + sb.toString();
            } catch (Exception e) {
            }
        } else {
            logFile = "File Does Not Exists :" + logFileName;
        }
        return logFile;
    }

    public String padLeftZeros(String inputString, int length) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < length - inputString.length()) {
            sb.append('0');
        }
        sb.append(inputString);

        return sb.toString();
    }

    public String getLogProcess(String processType) {
        String dbColName = "";
        if (processType.equalsIgnoreCase("IMPORT_PROCESS_LOG")) {
            dbColName = "log_file_name2";
        }
        if (processType.equalsIgnoreCase("ERROR_PROCESS_LOG")) {
            dbColName = "log_file_name3";
        }
        if (processType.equalsIgnoreCase("TEXT_FILE_GEN_LOG")) {
            dbColName = "log_file_name4";
        }
        if (processType.equalsIgnoreCase("BULK_TEXT_FILE_GEN_LOG")) {
            dbColName = "log_file_name4";
        }
        return dbColName;
    }

}// end class

