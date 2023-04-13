/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.generic;

import dao.AccYearMastDAO;
import dao.AccYearMastDAOImpl;
import dao.BankMastDAO;
import dao.BankMastDAOImpl;
import dao.CityMastDAO;
import dao.CityMastDAOImpl;
import dao.ClientMastCorrectionDAO;
import dao.ClientMastCorrectionDAOImpl;
import dao.ClientMastDAO;
import dao.ClientMastDAOImpl;
import dao.CountryMastDAO;
import dao.CountryMastDAOImpl;
import dao.DeducteeBflagAmtTranDAO;
import dao.DeducteeBflagAmtTranDAOImpl;
import dao.DeducteeBflagRefinfoSeqNoDAO;
import dao.DeducteeBflagRefinfoSeqNoDAOImpl;
import dao.DeducteeMast15ghDAO;
import dao.DeducteeMast15ghDAOImpl;
import dao.EntityLogoMastDAO;
import dao.EntityLogoMastDAOImpl;
import dao.EntityMastDAO;
import dao.EntityMastDAOImpl;
import dao.LhssysAlertDirectEmailDAO;
import dao.LhssysAlertDirectEmailDAOImpl;
import dao.LhssysAlertDirectEmailParaDAO;
import dao.LhssysAlertDirectEmailParaDAOImpl;
import dao.LhssysClientLoginTranDAO;
import dao.LhssysClientLoginTranDAOImpl;
import dao.LhssysEngineColsDAO;
import dao.LhssysEngineColsDAOImpl;
import dao.LhssysFileTranDAO;
import dao.LhssysFileTranDAOImpl;
import dao.LhssysNewsPortalDAO;
import dao.LhssysNewsPortalDAOImpl;
import dao.LhssysParametersDAO;
import dao.LhssysParametersDAOImpl;
import dao.LhssysProcessLogDAO;
import dao.LhssysProcessLogDAOImpl;
import dao.LhssysTdsReturnTranDAO;
import dao.LhssysTdsReturnTranDAOImpl;
import dao.LhssysTemplateDAO;
import dao.LhssysTemplateDAOImpl;
import dao.MisReportConfigMastDAO;
import dao.MisReportConfigMastDAOImpl;
import dao.MisReportConfigMastFilterDAO;
import dao.MisReportConfigMastFilterDAOImpl;
import dao.PanMastDAO;
import dao.PanMastDAOImpl;
import dao.PanMastFileUploadTranDAO;
import dao.PanMastFileUploadTranDAOImpl;
import dao.PinCodeMastDAO;
import dao.PinCodeMastDAOImpl;
import dao.QuickNavigationMastDAO;
import dao.QuickNavigationMastDAOImpl;
import dao.SalaryAmtTranDAO;
import dao.SalaryAmtTranDAOImpl;
import dao.SalaryConfigMastDAO;
import dao.SalaryConfigMastDAOImpl;
import dao.SalaryTranDAO;
import dao.SalaryTranDAOImpl;
import dao.SalaryTranLoadDAO;
import dao.SalaryTranLoadDAOImpl;
import dao.StateMastDAO;
import dao.StateMastDAOImpl;
import dao.TdsChallanTranCorrectionDAO;
import dao.TdsChallanTranCorrectionDAOImpl;
import dao.TdsChallanTranLoadDAO;
import dao.TdsChallanTranLoadDAOImpl;
import dao.TdsMastDAO;
import dao.TdsMastDAOImpl;
import dao.TdsMiscTranLoadDAO;
import dao.TdsMiscTranLoadDAOImpl;
import dao.TdsSplRateMastDAO;
import dao.TdsSplRateMastDAOImpl;
import dao.TdsTranCorrDAO;
import dao.TdsTranCorrDAOImpl;
import dao.TdsTranLoadDAO;
import dao.TdsTranLoadDAOImpl;
import dao.TempDataDAO;
import dao.TempDataDAOImpl;
import dao.UserMastDAO;
import dao.UserMastDAOImpl;
import dao.ViewClientCatgDAO;
import dao.ViewClientCatgDAOImpl;
import dao.ViewClientMastDAO;
import dao.ViewClientMastDAOImpl;
import dao.ViewClientTypeDAO;
import dao.ViewClientTypeDAOImpl;
import dao.ViewCorrTypeDAO;
import dao.ViewCorrTypeDAOImpl;
import dao.ViewDeducteeCatgDAO;
import dao.ViewDeducteeCatgDAOImpl;
import dao.ViewDeducteeTemplateDAO;
import dao.ViewDeducteeTemplateDAOImpl;
import dao.ViewDeducteeTypeDAO;
import dao.ViewDeducteeTypeDAOImpl;
import dao.ViewDisplayReportListDAO;
import dao.ViewDisplayReportListDAOImpl;
import dao.ViewEmpCatgDAO;
import dao.ViewEmpCatgDAOImpl;
import dao.ViewLhssysLatestAssYearDAO;
import dao.ViewLhssysLatestAssYearDAOImpl;
import dao.ViewLhssysTemplateErrorDAO;
import dao.ViewLhssysTemplateErrorDAOImpl;
import dao.ViewMinistryMastDAO;
import dao.ViewMinistryMastDAOImpl;
import dao.ViewPanStatusDAO;
import dao.ViewPanStatusDAOImpl;
import dao.ViewRemittanceNatureDAO;
import dao.ViewRemittanceNatureDAOImpl;
import dao.ViewResidentTypeDAO;
import dao.ViewResidentTypeDAOImpl;
import dao.ViewSalaryDataValidationDAO;
import dao.ViewSalaryDataValidationDAOImpl;
import dao.ViewSalaryEmployeeDAO;
import dao.ViewSalaryEmployeeDAOImpl;
import dao.ViewSubMinistryMastDAO;
import dao.ViewSubMinistryMastDAOImpl;
import dao.ViewTdsChallanTranLoadDAO;
import dao.ViewTdsChallanTranLoadDAOImpl;
import dao.ViewTdsDeductReasonDAO;
import dao.ViewTdsDeductReasonDAOImpl;
import dao.ViewTdsTranLoadDAO;
import dao.ViewTdsTranLoadDAOImpl;
import hibernateObjects.LhssysCorrFileUploadTranDAO;
import hibernateObjects.LhssysCorrFileUploadTranDAOImpl;
import hibernateObjects.ViewErrorProcessReports;
import org.hibernate.Session;

/**
 *
 * @author akash.dev
 */
public class HibernateDAOFactory extends DAOFactory {

    private GenericHibernateDAO instantiateDAO(Class daoClass) {
        try {
            GenericHibernateDAO dao = (GenericHibernateDAO) daoClass.newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        } catch (Exception ex) {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass, ex);
        }
    }//end method

    protected Session getCurrentSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }//end method

    @Override
    public LhssysParametersDAO getLhssysParametersDAO() {
        return (LhssysParametersDAO) instantiateDAO(LhssysParametersDAOImpl.class);
    }

    @Override
    public UserMastDAO getUserMastDAO() {
        return (UserMastDAO) instantiateDAO(UserMastDAOImpl.class);
    }

    @Override
    public ViewClientMastDAO getViewClientMastDAO() {
        return (ViewClientMastDAO) instantiateDAO(ViewClientMastDAOImpl.class);
    }

    @Override
    public ClientMastDAO getClientMastDAO() {
        return (ClientMastDAO) instantiateDAO(ClientMastDAOImpl.class);
    }

    @Override
    public QuickNavigationMastDAO getQuickNavigationMastDAO() {
        return (QuickNavigationMastDAO) instantiateDAO(QuickNavigationMastDAOImpl.class);
    }

    @Override
    public TdsMastDAO getTdsMastDAO() {
        return (TdsMastDAO) instantiateDAO(TdsMastDAOImpl.class);
    }

    @Override
    public ViewTdsDeductReasonDAO getViewTdsDeductReasonDAO() {
        return (ViewTdsDeductReasonDAO) instantiateDAO(ViewTdsDeductReasonDAOImpl.class);
    }

    @Override
    public TdsTranLoadDAO getTdsTranLoadDAO() {
        return (TdsTranLoadDAO) instantiateDAO(TdsTranLoadDAOImpl.class);
    }

    @Override
    public CityMastDAO getCityMastDAO() {
        return (CityMastDAO) instantiateDAO(CityMastDAOImpl.class);
    }

    @Override
    public CountryMastDAO getCountryMastDAO() {
        return (CountryMastDAO) instantiateDAO(CountryMastDAOImpl.class);
    }

    @Override
    public ViewClientCatgDAO getViewClientCatgDAO() {
        return (ViewClientCatgDAO) instantiateDAO(ViewClientCatgDAOImpl.class);
    }

    @Override
    public StateMastDAO getStateMastDAO() {
        return (StateMastDAO) instantiateDAO(StateMastDAOImpl.class);
    }

    @Override
    public PanMastDAO getPanMastDAO() {
        return (PanMastDAO) instantiateDAO(PanMastDAOImpl.class);
    }

    @Override
    public ViewDeducteeTypeDAO getViewDeducteeTypeDAO() {
        return (ViewDeducteeTypeDAO) instantiateDAO(ViewDeducteeTypeDAOImpl.class);
    }

    @Override
    public ViewClientTypeDAO getViewClientTypeDAO() {
        return (ViewClientTypeDAO) instantiateDAO(ViewClientTypeDAOImpl.class);
    }

    @Override
    public LhssysFileTranDAO getLhssysFileTranDAO() {
        return (LhssysFileTranDAO) instantiateDAO(LhssysFileTranDAOImpl.class);
    }

    @Override
    public LhssysNewsPortalDAO getLhssysNewsPortalDAO() {
        return (LhssysNewsPortalDAO) instantiateDAO(LhssysNewsPortalDAOImpl.class);
    }

    @Override
    public ViewTdsChallanTranLoadDAO getViewTdsChallanTranLoadDAO() {
        return (ViewTdsChallanTranLoadDAO) instantiateDAO(ViewTdsChallanTranLoadDAOImpl.class);
    }

    @Override
    public TdsSplRateMastDAO getTdsSplRateMastDAO() {
        return (TdsSplRateMastDAO) instantiateDAO(TdsSplRateMastDAOImpl.class);
    }

    @Override
    public LhssysTdsReturnTranDAO getLhssysTdsReturnTranDAO() {
        return (LhssysTdsReturnTranDAO) instantiateDAO(LhssysTdsReturnTranDAOImpl.class);
    }

    @Override
    public BankMastDAO getBankMastDAO() {
        return (BankMastDAO) instantiateDAO(BankMastDAOImpl.class);
    }

    @Override
    public TdsChallanTranLoadDAO getTdsChallanTranLoadDAO() {
        return (TdsChallanTranLoadDAO) instantiateDAO(TdsChallanTranLoadDAOImpl.class);
    }

    @Override
    public ViewMinistryMastDAO getViewMinistryMastDAO() {
        return (ViewMinistryMastDAO) instantiateDAO(ViewMinistryMastDAOImpl.class);
    }

    @Override
    public ViewSubMinistryMastDAO getViewSubMinistryMastDAO() {
        return (ViewSubMinistryMastDAO) instantiateDAO(ViewSubMinistryMastDAOImpl.class);
    }

    @Override
    public DeducteeBflagRefinfoSeqNoDAO getDeducteeBflagRefinfoSeqNoDAO() {
        return (DeducteeBflagRefinfoSeqNoDAO) instantiateDAO(DeducteeBflagRefinfoSeqNoDAOImpl.class);
    }

    @Override
    public ViewTdsTranLoadDAO getViewTdsTranLoadDAO() {
        return (ViewTdsTranLoadDAO) instantiateDAO(ViewTdsTranLoadDAOImpl.class);
    }

    @Override
    public ViewDeducteeTemplateDAO getViewDeducteeTemplateDAO() {
        return (ViewDeducteeTemplateDAO) instantiateDAO(ViewDeducteeTemplateDAOImpl.class);
    }

    @Override
    public ViewCorrTypeDAO getViewCorrTypeDAO() {
        return (ViewCorrTypeDAO) instantiateDAO(ViewCorrTypeDAOImpl.class);
    }

    @Override
    public LhssysCorrFileUploadTranDAO getLhssysCorrFileUploadTranDAO() {
        return (LhssysCorrFileUploadTranDAO) instantiateDAO(LhssysCorrFileUploadTranDAOImpl.class);
    }

    @Override
    public LhssysClientLoginTranDAO getLhssysClientLoginTranDAO() {
        return (LhssysClientLoginTranDAO) instantiateDAO(LhssysClientLoginTranDAOImpl.class);
    }

    @Override
    public TdsChallanTranCorrectionDAO getTdsChallanTranCorrectionDAO() {
        return (TdsChallanTranCorrectionDAO) instantiateDAO(TdsChallanTranCorrectionDAOImpl.class);
    }

    
    @Override
    public TdsTranCorrDAO getTdsTranCorrDAO() {
        return (TdsTranCorrDAO) instantiateDAO(TdsTranCorrDAOImpl.class);
    }

    @Override
    public ViewRemittanceNatureDAO getViewRemittanceNatureDAO() {
        return (ViewRemittanceNatureDAO) instantiateDAO(ViewRemittanceNatureDAOImpl.class);
    }

    @Override
    public ViewDeducteeCatgDAO getViewDeducteeCatgDAO() {
        return (ViewDeducteeCatgDAO) instantiateDAO(ViewDeducteeCatgDAOImpl.class);
    }

    @Override
    public LhssysProcessLogDAO getLhssysProcessLogDAO() {
        return (LhssysProcessLogDAO) instantiateDAO(LhssysProcessLogDAOImpl.class);
    }

    @Override
    public ClientMastCorrectionDAO getClientMastCorrectionDAO() {
        return (ClientMastCorrectionDAO) instantiateDAO(ClientMastCorrectionDAOImpl.class);
    }

    @Override
    public ViewEmpCatgDAO getViewEmpCatgDAO() {
        return (ViewEmpCatgDAO) instantiateDAO(ViewEmpCatgDAOImpl.class);
    }

    @Override
    public ViewPanStatusDAO getViewPanStatusDAO() {
        return (ViewPanStatusDAO) instantiateDAO(ViewPanStatusDAOImpl.class);
    }

    @Override
    public ViewResidentTypeDAO getViewResidentTypeDAO() {
        return (ViewResidentTypeDAO) instantiateDAO(ViewResidentTypeDAOImpl.class);
    }

    @Override
    public DeducteeMast15ghDAO getDeducteeMast15ghDAO() {
        return (DeducteeMast15ghDAO) instantiateDAO(DeducteeMast15ghDAOImpl.class);
    }

    @Override
    public DeducteeBflagAmtTranDAO getDeducteeBflagAmtTranDAO() {
        return (DeducteeBflagAmtTranDAO) instantiateDAO(DeducteeBflagAmtTranDAOImpl.class);
    }

    @Override
    public TempDataDAO getTempDataDAO() {
        return (TempDataDAO) instantiateDAO(TempDataDAOImpl.class);
    }

    @Override
    public LhssysEngineColsDAO getLhssysEngineColsDAO() {
        return (LhssysEngineColsDAO) instantiateDAO(LhssysEngineColsDAOImpl.class);
    }

    @Override
    public LhssysTemplateDAO getLhssysTemplateDAO() {
        return (LhssysTemplateDAO) instantiateDAO(LhssysTemplateDAOImpl.class);
    }

    @Override
    public ViewLhssysTemplateErrorDAO getViewLhssysTemplateErrorDAO() {
        return (ViewLhssysTemplateErrorDAO) instantiateDAO(ViewLhssysTemplateErrorDAOImpl.class);
    }

    @Override
    public ViewSalaryDataValidationDAO getViewSalaryDataValidationDAO() {
        return (ViewSalaryDataValidationDAO) instantiateDAO(ViewSalaryDataValidationDAOImpl.class);
    }

    @Override
    public SalaryAmtTranDAO getSalaryAmtTranDAO() {
        return (SalaryAmtTranDAO) instantiateDAO(SalaryAmtTranDAOImpl.class);
    }

    @Override
    public SalaryTranDAO getSalaryTranDAO() {
        return (SalaryTranDAO) instantiateDAO(SalaryTranDAOImpl.class);
    }

    @Override
    public ViewSalaryEmployeeDAO getViewSalaryEmployeeDAO() {
        return (ViewSalaryEmployeeDAO) instantiateDAO(ViewSalaryEmployeeDAOImpl.class);
    }

    @Override
    public SalaryConfigMastDAO getSalaryConfigMastDAO() {
        return (SalaryConfigMastDAO) instantiateDAO(SalaryConfigMastDAOImpl.class);
    }

    @Override
    public PinCodeMastDAO getPinCodeMastDAO() {
        return (PinCodeMastDAO) instantiateDAO(PinCodeMastDAOImpl.class);
    }

    @Override
    public ViewClientTypeDAO getviewClientTypeDAO() {
        return (ViewClientTypeDAO) instantiateDAO(ViewClientTypeDAOImpl.class);
    }

    @Override
    public EntityMastDAO getEntityMastDAO() {
        return (EntityMastDAO) instantiateDAO(EntityMastDAOImpl.class);
    }

    @Override
    public AccYearMastDAO getAccYearMastDAO() {
        return (AccYearMastDAO) instantiateDAO(AccYearMastDAOImpl.class);
    }

    @Override
    public EntityLogoMastDAO getEntityLogoMastDAO() {
        return (EntityLogoMastDAO) instantiateDAO(EntityLogoMastDAOImpl.class);
    }

    @Override
    public LhssysAlertDirectEmailDAO getLhssysAlertDirectEmailDAO() {
        return (LhssysAlertDirectEmailDAO) instantiateDAO(LhssysAlertDirectEmailDAOImpl.class);
    }

    @Override
    public LhssysAlertDirectEmailParaDAO getLhssysAlertDirectEmailParaDAO() {
        return (LhssysAlertDirectEmailParaDAO) instantiateDAO(LhssysAlertDirectEmailParaDAOImpl.class);
    }

    @Override
    public MisReportConfigMastDAO getMisReportConfigMastDAO() {
        return (MisReportConfigMastDAO) instantiateDAO(MisReportConfigMastDAOImpl.class);
    }

    @Override
    public MisReportConfigMastFilterDAO getMisReportConfigMastFilterDAO() {
        return (MisReportConfigMastFilterDAO) instantiateDAO(MisReportConfigMastFilterDAOImpl.class);
    }

    @Override
    public ViewLhssysLatestAssYearDAO getViewLhssysLatestAssYearDAO() {
        return (ViewLhssysLatestAssYearDAO) instantiateDAO(ViewLhssysLatestAssYearDAOImpl.class);
    }

    @Override
    public SalaryTranLoadDAO getSalaryTranLoadDAO() {
        return (SalaryTranLoadDAO) instantiateDAO(SalaryTranLoadDAOImpl.class);
    }

    @Override
    public TdsMiscTranLoadDAO getTdsMiscTranLoadDAO() {
        return (TdsMiscTranLoadDAO) instantiateDAO(TdsMiscTranLoadDAOImpl.class);
    }

    @Override
    public PanMastFileUploadTranDAO getPanMastFileUploadTranDAO() {
         return (PanMastFileUploadTranDAO) instantiateDAO(PanMastFileUploadTranDAOImpl.class);
    }

    @Override
    public ViewDisplayReportListDAO getViewDisplayReportListDAO() {
       return (ViewDisplayReportListDAO) instantiateDAO(ViewDisplayReportListDAOImpl.class);
    }

}//end class
