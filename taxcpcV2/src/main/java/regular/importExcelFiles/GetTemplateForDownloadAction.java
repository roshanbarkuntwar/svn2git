/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regular.importExcelFiles;

import com.lhs.taxcpcv2.bean.Assessment;
import com.opensymphony.xwork2.ActionSupport;
import dao.ViewDeducteeTemplateDAO;
import dao.generic.DAOFactory;
import globalUtilities.FileOptUtil;
import globalUtilities.Util;
import hibernateObjects.ViewClientMast;
import hibernateObjects.ViewClientTemplate;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author abhijeet.joshi
 */
public class GetTemplateForDownloadAction extends ActionSupport implements SessionAware {

    public GetTemplateForDownloadAction() {
        utl = new Util();
        fileUtl = new FileOptUtil();
    }

    @Override
    public String execute() {
        String returnView = "success";
        try {
            String fileNamePath = "";
            String newFileName = "";
            String template = "";
            String processInsertMode = "";

            ViewClientMast viewClientMast = (ViewClientMast) session.get("WORKINGUSER");
            Assessment assesment = (Assessment) session.get("ASSESSMENT");

            DAOFactory factory = DAOFactory.instance(DAOFactory.HIBERNATE);
            if (!utl.isnull(getTemplateType())) {
                String splitData[] = getTemplateType().split("#");
                String templateCode = splitData[0];

                ViewDeducteeTemplateDAO viewDeducteeTemplate = factory.getViewDeducteeTemplateDAO();
                ViewClientTemplate clientTemplate = viewDeducteeTemplate.getDataAsTempleteCode(templateCode);
                if (clientTemplate != null) {
                    fileNamePath = clientTemplate.getFile_location();
                    template = (!utl.isnull(clientTemplate.getEngine_type())) ? clientTemplate.getEngine_type() : "null";
                    processInsertMode = (!utl.isnull(clientTemplate.getProcess_type())) ? clientTemplate.getProcess_type(): "null";
//                    System.out.println("fileNamePath----" + fileNamePath);
                }
            }
            String extention = null;
            try {
//                LhssysParametersDAO lhssysParametersDAO;
//                lhssysParametersDAO = factory.getLhssysParametersDAO();
//                LhssysParameters readExternalDriveName = lhssysParametersDAO.readDataAsParameterAndEntity("EXCEL_FORMAT", "");
//                if (readExternalDriveName != null) {
                extention = (String) session.get("EXCELFORMAT");
//                System.out.println("extension..." + extention);
//                }
            } catch (Exception e) {

            }
            if (!utl.isnull(fileNamePath)) {
                try {
                    File file = new File(fileNamePath);
                    if (file.exists()) {
                        // Commented due to required change in template name as per requirement. 20.12.2019
//                        newFileName = fileUtl.getTaxcpcDownloadFilename(viewClientMast, assesment);
//                        newFileName = newFileName + extention;
                        newFileName = file.getName();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

//            System.out.println(fileNamePath + "#" + newFileName);
            inputStream = new ByteArrayInputStream((fileNamePath + "#" + newFileName + "#" + template + "#" + processInsertMode).getBytes("UTF-8"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return returnView;
    }//End Method

    private String templateType;
    private String excelFormat;
    private Map<String, Object> session;
    private Util utl;
    private FileOptUtil fileUtl;
    private InputStream inputStream;

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public String getExcelFormat() {
        return excelFormat;
    }

    public void setExcelFormat(String excelFormat) {
        this.excelFormat = excelFormat;
    }

}//End Class
