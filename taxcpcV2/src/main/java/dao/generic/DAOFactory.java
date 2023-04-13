/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import dao.AccYearMastDAO;
import dao.BankMastDAO;
import dao.CityMastDAO;
import dao.ClientMastCorrectionDAO;
import dao.ClientMastDAO;
import dao.CountryMastDAO;
import dao.DeducteeBflagAmtTranDAO;
import dao.DeducteeBflagRefinfoSeqNoDAO;
import dao.DeducteeMast15ghDAO;
import dao.EntityLogoMastDAO;
import dao.EntityMastDAO;
import dao.LhssysAlertDirectEmailDAO;
import dao.LhssysAlertDirectEmailParaDAO;
import dao.LhssysClientLoginTranDAO;
import dao.LhssysEngineColsDAO;
import dao.LhssysFileTranDAO;
import dao.LhssysNewsPortalDAO;
import dao.LhssysParametersDAO;
import dao.LhssysProcessLogDAO;
import dao.LhssysTdsReturnTranDAO;
import dao.LhssysTemplateDAO;
import dao.MisReportConfigMastDAO;
import dao.MisReportConfigMastFilterDAO;
import dao.PanMastDAO;
import dao.PanMastFileUploadTranDAO;
import dao.PinCodeMastDAO;
import dao.QuickNavigationMastDAO;
import dao.SalaryAmtTranDAO;
import dao.SalaryConfigMastDAO;
import dao.SalaryTranDAO;
import dao.SalaryTranLoadDAO;
import dao.StateMastDAO;
import dao.TdsChallanTranCorrectionDAO;
import dao.TdsChallanTranLoadDAO;
import dao.TdsMastDAO;
import dao.TdsMiscTranLoadDAO;
import dao.TdsSplRateMastDAO;
import dao.TdsTranCorrDAO;
import dao.TdsTranLoadDAO;
import dao.TempDataDAO;
import dao.UserMastDAO;
import dao.ViewClientCatgDAO;
import dao.ViewClientMastDAO;
import dao.ViewClientTypeDAO;
import dao.ViewCorrTypeDAO;
import dao.ViewDeducteeCatgDAO;
import dao.ViewDeducteeTemplateDAO;
import dao.ViewDeducteeTypeDAO;
import dao.ViewDisplayReportListDAO;
import dao.ViewEmpCatgDAO;
import dao.ViewLhssysLatestAssYearDAO;
import dao.ViewLhssysTemplateErrorDAO;
import dao.ViewMinistryMastDAO;
import dao.ViewPanStatusDAO;
import dao.ViewRemittanceNatureDAO;
import dao.ViewResidentTypeDAO;
import dao.ViewSalaryDataValidationDAO;
import dao.ViewSalaryEmployeeDAO;
import dao.ViewSubMinistryMastDAO;
import dao.ViewTdsChallanTranLoadDAO;
import dao.ViewTdsDeductReasonDAO;
import dao.ViewTdsTranLoadDAO;
import hibernateObjects.LhssysCorrFileUploadTranDAO;
import hibernateObjects.ViewErrorProcessReports;

/**
 *
 * @author akash.dev
 */
public abstract class DAOFactory {

    public static final Class HIBERNATE = HibernateDAOFactory.class;

    public static DAOFactory instance(Class factory) {
        try {
            return (DAOFactory) factory.newInstance();
        } catch (Exception ex) {
            throw new RuntimeException("Couldn't create DAOFactory: " + factory);
        }
    }//end static block

    public abstract LhssysParametersDAO getLhssysParametersDAO();

    public abstract UserMastDAO getUserMastDAO();

    public abstract ViewClientMastDAO getViewClientMastDAO();

    public abstract ClientMastDAO getClientMastDAO();

    public abstract QuickNavigationMastDAO getQuickNavigationMastDAO();

    public abstract TdsMastDAO getTdsMastDAO();

    public abstract LhssysNewsPortalDAO getLhssysNewsPortalDAO();

    public abstract ViewTdsDeductReasonDAO getViewTdsDeductReasonDAO();

    public abstract ViewTdsChallanTranLoadDAO getViewTdsChallanTranLoadDAO();

    public abstract TdsTranLoadDAO getTdsTranLoadDAO();

    public abstract TdsMiscTranLoadDAO getTdsMiscTranLoadDAO();

    public abstract CityMastDAO getCityMastDAO();

    public abstract CountryMastDAO getCountryMastDAO();

    public abstract ViewClientCatgDAO getViewClientCatgDAO();

    public abstract StateMastDAO getStateMastDAO();

    public abstract PanMastDAO getPanMastDAO();

    public abstract ViewDeducteeTypeDAO getViewDeducteeTypeDAO();

    public abstract ViewClientTypeDAO getViewClientTypeDAO();

    public abstract LhssysFileTranDAO getLhssysFileTranDAO();

    public abstract TdsSplRateMastDAO getTdsSplRateMastDAO();

    public abstract LhssysTdsReturnTranDAO getLhssysTdsReturnTranDAO();

    public abstract BankMastDAO getBankMastDAO();

    public abstract TdsChallanTranLoadDAO getTdsChallanTranLoadDAO();

    public abstract DeducteeBflagRefinfoSeqNoDAO getDeducteeBflagRefinfoSeqNoDAO();

    public abstract ViewMinistryMastDAO getViewMinistryMastDAO();

    public abstract ViewSubMinistryMastDAO getViewSubMinistryMastDAO();

    public abstract ViewTdsTranLoadDAO getViewTdsTranLoadDAO();

    public abstract ViewDeducteeTemplateDAO getViewDeducteeTemplateDAO();

    public abstract TdsChallanTranCorrectionDAO getTdsChallanTranCorrectionDAO();


    public abstract ViewCorrTypeDAO getViewCorrTypeDAO();

    public abstract LhssysCorrFileUploadTranDAO getLhssysCorrFileUploadTranDAO();

    public abstract LhssysClientLoginTranDAO getLhssysClientLoginTranDAO();

    public abstract TdsTranCorrDAO getTdsTranCorrDAO();

    public abstract ViewRemittanceNatureDAO getViewRemittanceNatureDAO();

    public abstract ViewDeducteeCatgDAO getViewDeducteeCatgDAO();

    public abstract ClientMastCorrectionDAO getClientMastCorrectionDAO();

    public abstract LhssysProcessLogDAO getLhssysProcessLogDAO();

    public abstract ViewEmpCatgDAO getViewEmpCatgDAO();

    public abstract ViewPanStatusDAO getViewPanStatusDAO();

    public abstract ViewResidentTypeDAO getViewResidentTypeDAO();

    public abstract DeducteeMast15ghDAO getDeducteeMast15ghDAO();

    public abstract DeducteeBflagAmtTranDAO getDeducteeBflagAmtTranDAO();

    public abstract TempDataDAO getTempDataDAO();

    public abstract LhssysEngineColsDAO getLhssysEngineColsDAO();

    public abstract LhssysTemplateDAO getLhssysTemplateDAO();

    public abstract ViewLhssysTemplateErrorDAO getViewLhssysTemplateErrorDAO();

    public abstract ViewSalaryDataValidationDAO getViewSalaryDataValidationDAO();

    public abstract SalaryAmtTranDAO getSalaryAmtTranDAO();

    public abstract SalaryTranDAO getSalaryTranDAO();

    public abstract ViewSalaryEmployeeDAO getViewSalaryEmployeeDAO();

    public abstract SalaryConfigMastDAO getSalaryConfigMastDAO();

    public abstract PinCodeMastDAO getPinCodeMastDAO();

    public abstract ViewClientTypeDAO getviewClientTypeDAO();

    public abstract EntityMastDAO getEntityMastDAO();

    public abstract AccYearMastDAO getAccYearMastDAO();

    public abstract EntityLogoMastDAO getEntityLogoMastDAO();

    public abstract LhssysAlertDirectEmailDAO getLhssysAlertDirectEmailDAO();

    public abstract LhssysAlertDirectEmailParaDAO getLhssysAlertDirectEmailParaDAO();

    public abstract MisReportConfigMastDAO getMisReportConfigMastDAO();

    public abstract MisReportConfigMastFilterDAO getMisReportConfigMastFilterDAO();

    public abstract ViewLhssysLatestAssYearDAO getViewLhssysLatestAssYearDAO();

    public abstract SalaryTranLoadDAO getSalaryTranLoadDAO();
    
    public abstract PanMastFileUploadTranDAO getPanMastFileUploadTranDAO();
    
    public abstract ViewDisplayReportListDAO getViewDisplayReportListDAO();

}//end class
