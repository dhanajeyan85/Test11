package com.test.welcome;
/*      */ import java.awt.Color;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Font;
/*      */ import java.awt.Insets;
import java.awt.Toolkit;
/*      */ import java.awt.datatransfer.Clipboard;
/*      */ import java.awt.datatransfer.StringSelection;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
import java.io.StringReader;
/*      */ import java.io.StringWriter;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.Enumeration;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;

/*      */ import javax.swing.BorderFactory;
/*      */ import javax.swing.ButtonGroup;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.GroupLayout;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JCheckBox;
/*      */ import javax.swing.JComboBox;
import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JRadioButton;
/*      */ import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
/*      */ import javax.swing.JTextArea;
/*      */ import javax.swing.JTextField;
/*      */ import javax.swing.LayoutStyle;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*      */ import javax.swing.plaf.metal.MetalTabbedPaneUI;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/*      */ import jpos.JposException;
import jpos.Scale;
import jpos.Scanner;
/*      */ import jpos.config.JposEntry;
import jpos.config.JposRegPopulator;
/*      */ import jpos.config.simple.SimpleEntryRegistry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;
/*      */ import jpos.events.DataEvent;
/*      */ import jpos.events.DataListener;
/*      */ import jpos.events.ErrorEvent;
/*      */ import jpos.events.ErrorListener;
/*      */ import jpos.events.StatusUpdateEvent;
/*      */ import jpos.events.StatusUpdateListener;
/*      */ 
/*      */ public class JposSampleApp extends JFrame implements DataListener, StatusUpdateListener, ErrorListener {
/*      */   private String deviceType;
/*   51 */   private String[] status = new String[2];
/*      */   public static String logicalName;
/*      */   private int claimTimeout;
/*      */   private boolean deviceEnabled = false;
/*      */   private boolean dataEventEnabled = false;
/*      */   private boolean autoDisable = false;
/*      */   private boolean autoDeviceEnable = false;
/*      */   private boolean freezeEventsEnabled = false;
/*   59 */   private int healthCheckType = -1;
/*      */   public static String healthCheckText;
/*   61 */   public static int powerState = -1;
/*      */   private String info;
/*   63 */   private String[] errorStatus = new String[2];
/*   64 */   private String[] setPropertyStatus = new String[2];
/*   65 */   private int powerNotifyEnabled = -1;
/*      */   
/*      */   private int opCode;
/*      */   
/*      */   private StringBuffer deviceParams;
/*      */   
/*      */   private int[] statusScanner;
/*   72 */   static Scanner scanner = new Scanner();
/*      */   
/*      */   private boolean decodeDataEnabled = false;
/*      */   private boolean autoDataEventEnableScanner = false;
/*      */   private String scannerPropertyValue;
/*      */   public static String scanDataLabelText;
/*      */   public static String scanDataLabelHex;
/*      */   public static String scanData;
/*      */   public static int scanDataType;
/*   81 */   public static int scanDataCount = 0;
/*      */   
/*      */   private String resetStatValue;
/*   84 */   private String[] retrieveStatValue = new String[1];
/*      */ 
/*      */   
/*   87 */   static Scale scale = new Scale();
/*      */   
/*      */   private boolean asyncModeEnabled = false;
/*      */   private boolean autoDataEventEnableScale = false;
/*      */   public static int liveWeight;
/*      */   public static int[] readWeight;
/*      */   public static float fWeight;
/*   94 */   public static String units = "";
/*      */   private String scalePropertyValue;
/*      */   private int scaleRWTimeout;
/*      */   private int statusNotifyEnabled;
/*      */   private float weightAM;
/*   99 */   private String[] weightInAsyncM = new String[2];
/*      */   
/*      */   public static boolean fastModeScannerC = true;
/*      */   
/*      */   public static boolean fastModeScaleC = true;
/*      */   
/*      */   public static boolean deviceEnableC = true;
/*      */   
/*      */   public static boolean dataEventEnableC = true;
/*      */   
/*      */   public static boolean decodeDataEnableC = true;
/*      */   public static boolean freezeEventsC = true;
/*      */   public static boolean autoDisableC = true;
/*      */   public static boolean asyncModeC = true;
/*      */   public static boolean directIOC = true;
/*      */   public static boolean statusNotifyC = true;
/*      */   public static boolean powerNotifyC = true;
/*      */   public static boolean error = false;
/*      */   private boolean setValue;
/*      */   Runnable doUpdateGUI;
/*      */   Runnable doUpdateScaleUI;
/*      */   Runnable doUpdateScaleUIAfterErrorEvent;
/*  121 */   IntermediateLayer intermediateLayer = new IntermediateLayer(); private JButton btnClaim; private JButton btnClearInput; private JButton btnClose; private JButton btnFastMode; private JButton btnLogClear; private JButton btnOpen; private JButton btnRelease; private JButton btnSclCHText; private JButton btnSclCheckHealth; private JButton btnSclClear; private JButton btnSclClearInput; private JButton btnSclCopy; private JButton btnSclExecute; private JButton btnSclPowerState; private JButton btnSclProperties; private JButton btnSclReadWeight; private JButton btnSclResetStat; private JButton btnSclRetreiveStat; private JButton btnSclZeroScale; private JButton btnScnCheckHealth; private JButton btnScnCheckHealthText; private JButton btnScnClear; private JButton btnScnClearInputProperties; private JButton btnScnCopy; private JButton btnScnExecute; private JButton btnScnPowerState; private JButton btnScnProperties; private JButton btnScnResetStat; private JButton btnScnRetreiveStat; private ButtonGroup buttonGroup1; private ButtonGroup checkHealthBtnGroup; private JCheckBox chkSclAsyncMode; private JCheckBox chkSclAutoDataEventEnable; private JCheckBox chkSclAutoDeviceEnable; private JCheckBox chkSclAutoDisable; private JCheckBox chkSclDataEventEnable; private JCheckBox chkSclDeviceEnable; private JCheckBox chkSclEnableLiveWeight; private JCheckBox chkSclFreezeEvents; private JCheckBox chkSclPowerNotify; private JCheckBox chkScnAutoDataEventEnable; private JCheckBox chkScnAutoDeviceEnable; private JCheckBox chkScnAutoDisable; private JCheckBox chkScnDataEventEnable; private JCheckBox chkScnDecodeData; private JCheckBox chkScnDeviceEnable; private JCheckBox chkScnFreezeEvents; private JCheckBox chkScnPowerNotify; private JComboBox cmbDeviceCategory; private JComboBox cmbLogicalDevice; private JComboBox cmbSclCommand; private JComboBox cmbSclProperties; private JComboBox cmbScnCommand; private JComboBox cmbScnProperties; private JLabel jLabel1; private JLabel jLabel2; private JLabel jLabel3; private JPanel jPanel1; private JPanel jPanel2; private JScrollPane jScrollPane1; private JScrollPane jScrollPane10; private JScrollPane jScrollPane11; private JScrollPane jScrollPane2; private JScrollPane jScrollPane3; private JScrollPane jScrollPane4; private JScrollPane jScrollPane5; private JScrollPane jScrollPane6; private JScrollPane jScrollPane7; private JScrollPane jScrollPane8; private JScrollPane jScrollPane9;
/*      */   private JTabbedPane jTabbedPane;
/*      */   private JLabel lblClaimTimeout;
/*      */   
/*      */   public JposSampleApp() {
/*      */     try {
/*  127 */       String str = System.getProperty("os.name").toLowerCase();
/*      */       
/*  129 */       if (isWindows(str)) {
/*  130 */         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*      */       } else {
/*      */         
/*  133 */         UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
/*      */       } 
/*  135 */       initComponents();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  141 */       this.chkSclPowerNotify.setVisible(true);
/*  142 */       this.btnSclPowerState.setVisible(false);
/*  143 */       this.txtSclPowerState.setVisible(false);
/*      */       
/*  145 */       DeviceCategorySelection deviceCategorySelection = new DeviceCategorySelection();
/*      */ 
/*      */       
/*  148 */       this.cmbDeviceCategory.setModel(deviceCategorySelection.deviceCategory());
/*      */ 
/*      */       
/*  151 */       logicalDeviceName("Scanner");
/*      */ 
/*      */       
/*  154 */       this.cmbScnProperties.setModel(deviceCategorySelection.scannerProperty());
/*  155 */       this.cmbSclProperties.setModel(deviceCategorySelection.scaleProperty());
/*      */ 
/*      */       
/*  158 */       this.cmbScnCommand.setModel(deviceCategorySelection.scnDirectIOCommand());
/*  159 */       this.cmbSclCommand.setModel(deviceCategorySelection.sclDirectIOCommand());
/*      */       
/*  161 */       AutoCompletion.enable(this.cmbScnProperties);
/*  162 */       AutoCompletion.enable(this.cmbSclProperties);
/*      */       
/*  164 */       this.scnInternalCH.setSelected(true);
/*  165 */       this.scanDataText.setSelected(true);
/*      */       
/*  167 */       scanner.addDataListener(this);
/*  168 */       scanner.addStatusUpdateListener(new StatusUpdateListener()
/*      */           {
/*      */             
/*      */             public void statusUpdateOccurred(StatusUpdateEvent param1StatusUpdateEvent)
/*      */             {
/*  173 */               DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)JposSampleApp.this.cmbDeviceCategory.getSelectedItem();
/*  174 */               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/*  175 */               Date date = new Date();
/*  176 */               String str1 = simpleDateFormat.format(date);
/*      */               
/*  178 */               String str2 = "";
/*  179 */               switch (param1StatusUpdateEvent.getStatus()) {
/*      */                 case 2001:
/*  181 */                   str2 = " JPOS_SUE_POWER_ONLINE";
/*      */                   break;
/*      */                 
/*      */                 case 2004:
/*  185 */                   str2 = " JPOS_SUE_POWER_OFF_OFFLINE";
/*      */                   break;
/*      */               } 
/*      */               
/*  189 */               JposSampleApp.this.txtLogView.setText(JposSampleApp.this.txtLogView.getText() + "\n" + str1 + " : " + deviceTypeBinder.getDevice() + "      :Status Update Event: " + param1StatusUpdateEvent.getStatus() + str2);
/*      */             }
/*      */           });
/*      */       
/*  193 */       scale.addStatusUpdateListener(new StatusUpdateListener()
/*      */           {
/*      */             
/*      */             public void statusUpdateOccurred(StatusUpdateEvent param1StatusUpdateEvent)
/*      */             {
/*  198 */               SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/*  199 */               Date date = new Date();
/*  200 */               String str = simpleDateFormat.format(date);
/*      */ 
/*      */               
/*  203 */               if (param1StatusUpdateEvent.getStatus() == 2001 || param1StatusUpdateEvent.getStatus() == 2004 || param1StatusUpdateEvent.getStatus() == 2000) {
/*  204 */                 DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)JposSampleApp.this.cmbDeviceCategory.getSelectedItem();
/*      */                 
/*  206 */                 String str1 = "";
/*  207 */                 switch (param1StatusUpdateEvent.getStatus()) {
/*      */                   case 2001:
/*  209 */                     str1 = " JPOS_SUE_POWER_ONLINE";
/*      */                     break;
/*      */                   
/*      */                   case 2004:
/*  213 */                     str1 = " JPOS_SUE_POWER_OFF_OFFLINE";
/*      */                     break;
/*      */                 } 
/*      */                 
/*  217 */                 JposSampleApp.this.txtLogView.setText(JposSampleApp.this.txtLogView.getText() + "\n" + str + " : " + deviceTypeBinder.getDevice() + "      :Status Update Event: " + param1StatusUpdateEvent.getStatus() + str1);
/*      */               } else {
/*      */                 
/*  220 */                 ScaleDeviceTypeBinder scaleDeviceTypeBinder = (ScaleDeviceTypeBinder)JposSampleApp.this.cmbDeviceCategory.getSelectedItem();
/*  221 */                 String str1 = JposSampleApp.this.intermediateLayer.statusUpdateListenerEvent(scaleDeviceTypeBinder, param1StatusUpdateEvent);
/*  222 */                 JposSampleApp.this.txtSclLiveWeight.setText(String.valueOf(str1));
/*      */                 
/*  224 */                 String str2 = "";
/*  225 */                 switch (param1StatusUpdateEvent.getStatus()) {
/*      */                   
/*      */                   case 11:
/*  228 */                     str2 = " SCAL_SUE_STABLE_WEIGHT";
/*      */                     break;
/*      */                   
/*      */                   case 15:
/*  232 */                     str2 = " SCAL_SUE_NOT_READY";
/*      */                     break;
/*      */                   
/*      */                   case 12:
/*  236 */                     str2 = " SCAL_SUE_WEIGHT_UNSTABLE";
/*      */                     break;
/*      */                   
/*      */                   case 13:
/*  240 */                     str2 = " SCAL_SUE_WEIGHT_ZERO";
/*      */                     break;
/*      */                   
/*      */                   case 16:
/*  244 */                     str2 = " SCAL_SUE_WEIGHT_UNDER_ZERO";
/*      */                     break;
/*      */                   
/*      */                   case 14:
/*  248 */                     str2 = " SCAL_SUE_WEIGHT_OVERWEIGHT";
/*      */                     break;
/*      */                 } 
/*      */                 
/*  252 */                 JposSampleApp.this.txtLogView.setText(JposSampleApp.this.txtLogView.getText() + "\n" + str + " : " + scaleDeviceTypeBinder.getDevice() + "      :Status Update Event: " + param1StatusUpdateEvent.getStatus() + str2);
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/*  257 */       scale.addErrorListener(new ErrorListener() {
/*  258 */             SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/*  259 */             Date today = new Date();
/*  260 */             String date = this.DATE_FORMAT.format(this.today);
/*      */ 
/*      */             
/*      */             public void errorOccurred(ErrorEvent param1ErrorEvent) {
/*      */               try {
/*  265 */                 DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)JposSampleApp.this.cmbDeviceCategory.getSelectedItem();
/*  266 */                 JposSampleApp.this.txtLogView.setText(JposSampleApp.this.txtLogView.getText() + "\n" + this.date + " : " + deviceTypeBinder.getDevice() + "      :Error Event - ErrorCode: " + param1ErrorEvent.getErrorCode() + " ExtErrorCode: " + param1ErrorEvent.getErrorCodeExtended() + " ErrorResponce: " + param1ErrorEvent.getErrorResponse());
/*  267 */                 SwingUtilities.invokeLater(JposSampleApp.this.doUpdateScaleUIAfterErrorEvent);
/*  268 */               } catch (Exception exception) {
/*  269 */                 JposSampleApp.this.txtLogView.setText("InvokeLater exception." + exception);
/*      */                 
/*  271 */                 Logger.getLogger(JposSampleApp.class
/*  272 */                     .getName()).log(Level.SEVERE, (String)null, exception);
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/*  277 */       scale.addDataListener(new DataListener()
/*      */           {
/*      */             public void dataOccurred(DataEvent param1DataEvent)
/*      */             {
/*      */               try {
/*  282 */                 JposSampleApp.this.weightAM = param1DataEvent.getStatus();
/*  283 */                 JposSampleApp.this.weightInAsyncM[0] = "Read Weight in Asynchronous Mode. Weight : " + (JposSampleApp.this.weightAM / 1000.0F) + " " + JposSampleApp.units;
/*  284 */                 SwingUtilities.invokeLater(JposSampleApp.this.doUpdateScaleUI);
/*  285 */               } catch (Exception exception) {
/*  286 */                 JposSampleApp.this.txtLogView.setText("InvokeLater exception." + exception);
/*      */                 
/*  288 */                 Logger.getLogger(JposSampleApp.class
/*  289 */                     .getName()).log(Level.SEVERE, (String)null, exception);
/*      */               } 
/*      */             }
/*      */           });
/*      */       
/*  294 */       this.doUpdateGUI = new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/*  298 */               JposSampleApp.this.updateScannerGUI();
/*  299 */             } catch (JposException jposException) {
/*  300 */               Logger.getLogger(JposSampleApp.class.getName()).log(Level.SEVERE, (String)null, (Throwable)jposException);
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/*  305 */       this.doUpdateScaleUI = new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/*  309 */               JposSampleApp.this.updateScaleGUI();
/*  310 */             } catch (JposException jposException) {
/*  311 */               Logger.getLogger(JposSampleApp.class.getName()).log(Level.SEVERE, (String)null, (Throwable)jposException);
/*      */             } 
/*      */           }
/*      */         };
/*      */       
/*  316 */       this.doUpdateScaleUIAfterErrorEvent = new Runnable()
/*      */         {
/*      */           public void run() {
/*      */             try {
/*  320 */               JposSampleApp.this.updateScaleUIAfterErrorEvent();
/*  321 */             } catch (JposException jposException) {
/*  322 */               Logger.getLogger(JposSampleApp.class.getName()).log(Level.SEVERE, (String)null, (Throwable)jposException);
/*      */             } 
/*      */           }
/*      */         };
/*      */ 
/*      */       
/*  328 */       this.jTabbedPane.setUI(new MetalTabbedPaneUI()
/*      */           {
/*      */ 
/*      */             
/*      */             protected int calculateTabAreaHeight(int param1Int1, int param1Int2, int param1Int3)
/*      */             {
/*  334 */               return 0;
/*      */             }
/*      */           });
/*      */     }
/*  338 */     catch (UnsupportedLookAndFeelException|ClassNotFoundException|InstantiationException|IllegalAccessException|JposException unsupportedLookAndFeelException) {
/*  339 */       Logger.getLogger(JposSampleApp.class.getName()).log(Level.SEVERE, (String)null, unsupportedLookAndFeelException);
/*      */     } 
/*      */   }
/*      */   private JLabel lblDataCount; private JLabel lblDeviceInfo; private JLabel lblDeviceType; private JLabel lblLogicalName; private JLabel lblScanData; private JLabel lblScanDataLabel; private JLabel lblScanDataType; private JLabel lblSclCHText; private JLabel lblSclCommand; private JLabel lblSclInXml; private JLabel lblSclOutXml; private JLabel lblSclPropertyValue; private JLabel lblSclReadWeght; private JTextField lblSclStatistic; private JLabel lblSclStatus; private JLabel lblSclWeight; private JLabel lblScnCheckHealthText; private JLabel lblScnCommand; private JLabel lblScnInXml; private JLabel lblScnOutXml; private JLabel lblScnPropertyValue; private JTextField lblScnStatistic; private JLabel lblScnStatus; private JPanel panelLogView; private JPanel scalePanel; private JRadioButton scanDataHex; private JRadioButton scanDataText; private JPanel scannerPanel; private JRadioButton sclExternal; private JRadioButton sclInteractive; private JRadioButton sclInternal; private JPanel sclPanelCheckHealth; private JPanel sclPanelDataEvent; private JPanel sclPanelDirectIO; private JPanel sclPanelLiveWeight; private JPanel sclPanelProperties; private JPanel sclPanelScaleWeight; private JPanel sclPanelStat; private JRadioButton scnExternalCH; private JRadioButton scnInteractiveCH; private JRadioButton scnInternalCH; private JPanel scnPanelCheckHealth; private JPanel scnPanelCommonMethods; private JPanel scnPanelDataEvent; private JPanel scnPanelDirectIO; private JPanel scnPanelProperties; private JPanel scnPanelRecData; private JPanel scnPanelStat; private JTextField txtClaimTimeout; private JTextField txtDataCount; private JTextArea txtDeviceInfo; private JTextArea txtLogView; private JTextArea txtScanData; private JTextArea txtScanDataLabel; private JTextField txtScanDataType; private JTextField txtSclCheckHealthText; private JTextField txtSclDisplayWeight; private JTextArea txtSclInXml; private JTextField txtSclLiveWeight; private JTextArea txtSclOutXml; private JTextField txtSclPowerState; private JTextField txtSclPropertyValue; private JTextField txtSclRWTimeout; private JTextArea txtSclStatOutput; private JTextField txtSclStatus; private JTextField txtScnHealthCheckText; private JTextArea txtScnInxml; private JTextArea txtScnOutXml; private JTextField txtScnPowerState; private JTextField txtScnPropertyValue; private JTextArea txtScnStatOutput; private JTextField txtScnStatus;
/*      */   
/*      */   private boolean isWindows(String paramString) {
/*  345 */     return paramString.contains("win");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initComponents() {
/*  357 */     this.checkHealthBtnGroup = new ButtonGroup();
/*  358 */     this.buttonGroup1 = new ButtonGroup();
/*  359 */     this.jScrollPane11 = new JScrollPane();
/*  360 */     this.jPanel2 = new JPanel();
/*  361 */     this.jPanel1 = new JPanel();
/*  362 */     this.jLabel1 = new JLabel();
/*  363 */     this.jLabel3 = new JLabel();
/*  364 */     this.jTabbedPane = new JTabbedPane();
/*  365 */     this.scannerPanel = new JPanel();
/*  366 */     this.scnPanelDataEvent = new JPanel();
/*  367 */     this.chkScnDeviceEnable = new JCheckBox();
/*  368 */     this.chkScnDataEventEnable = new JCheckBox();
/*  369 */     this.chkScnAutoDataEventEnable = new JCheckBox();
/*  370 */     this.chkScnFreezeEvents = new JCheckBox();
/*  371 */     this.chkScnAutoDisable = new JCheckBox();
/*  372 */     this.chkScnAutoDeviceEnable = new JCheckBox();
/*  373 */     this.chkScnPowerNotify = new JCheckBox();
/*  374 */     this.txtScnPowerState = new JTextField();
/*  375 */     this.btnScnPowerState = new JButton();
/*  376 */     this.scnPanelRecData = new JPanel();
/*  377 */     this.lblScanDataLabel = new JLabel();
/*  378 */     this.jScrollPane3 = new JScrollPane();
/*  379 */     this.txtScanDataLabel = new JTextArea();
/*  380 */     this.scanDataText = new JRadioButton();
/*  381 */     this.lblScanData = new JLabel();
/*  382 */     this.jScrollPane6 = new JScrollPane();
/*  383 */     this.txtScanData = new JTextArea();
/*  384 */     this.lblScanDataType = new JLabel();
/*  385 */     this.txtScanDataType = new JTextField();
/*  386 */     this.lblDataCount = new JLabel();
/*  387 */     this.txtDataCount = new JTextField();
/*  388 */     this.btnClearInput = new JButton();
/*  389 */     this.chkScnDecodeData = new JCheckBox();
/*  390 */     this.scanDataHex = new JRadioButton();
/*  391 */     this.scnPanelCheckHealth = new JPanel();
/*  392 */     this.scnInternalCH = new JRadioButton();
/*  393 */     this.scnExternalCH = new JRadioButton();
/*  394 */     this.scnInteractiveCH = new JRadioButton();
/*  395 */     this.btnScnCheckHealth = new JButton();
/*  396 */     this.lblScnCheckHealthText = new JLabel();
/*  397 */     this.txtScnHealthCheckText = new JTextField();
/*  398 */     this.btnScnCheckHealthText = new JButton();
/*  399 */     this.scnPanelStat = new JPanel();
/*  400 */     this.lblScnStatistic = new JTextField();
/*  401 */     this.btnScnRetreiveStat = new JButton();
/*  402 */     this.btnScnResetStat = new JButton();
/*  403 */     this.jScrollPane1 = new JScrollPane();
/*  404 */     this.txtScnStatOutput = new JTextArea();
/*  405 */     this.jLabel2 = new JLabel();
/*  406 */     this.scnPanelDirectIO = new JPanel();
/*  407 */     this.cmbScnCommand = new JComboBox();
/*  408 */     this.btnScnExecute = new JButton();
/*  409 */     this.lblScnStatus = new JLabel();
/*  410 */     this.txtScnStatus = new JTextField();
/*  411 */     this.btnScnClear = new JButton();
/*  412 */     this.lblScnInXml = new JLabel();
/*  413 */     this.lblScnOutXml = new JLabel();
/*  414 */     this.jScrollPane2 = new JScrollPane();
/*  415 */     this.txtScnOutXml = new JTextArea();
/*  416 */     this.jScrollPane4 = new JScrollPane();
/*  417 */     this.txtScnInxml = new JTextArea();
/*  418 */     this.btnScnCopy = new JButton();
/*  419 */     this.lblScnCommand = new JLabel();
/*  420 */     this.scnPanelProperties = new JPanel();
/*  421 */     this.cmbScnProperties = new JComboBox();
/*  422 */     this.lblScnPropertyValue = new JLabel();
/*  423 */     this.txtScnPropertyValue = new JTextField();
/*  424 */     this.btnScnProperties = new JButton();
/*  425 */     this.btnScnClearInputProperties = new JButton();
/*  426 */     this.scalePanel = new JPanel();
/*  427 */     this.sclPanelDataEvent = new JPanel();
/*  428 */     this.chkSclDeviceEnable = new JCheckBox();
/*  429 */     this.chkSclDataEventEnable = new JCheckBox();
/*  430 */     this.chkSclFreezeEvents = new JCheckBox();
/*  431 */     this.chkSclAutoDeviceEnable = new JCheckBox();
/*  432 */     this.chkSclAutoDisable = new JCheckBox();
/*  433 */     this.chkSclAutoDataEventEnable = new JCheckBox();
/*  434 */     this.chkSclPowerNotify = new JCheckBox();
/*  435 */     this.txtSclPowerState = new JTextField();
/*  436 */     this.btnSclPowerState = new JButton();
/*  437 */     this.sclPanelScaleWeight = new JPanel();
/*  438 */     this.lblSclReadWeght = new JLabel();
/*  439 */     this.txtSclRWTimeout = new JTextField();
/*  440 */     this.txtSclDisplayWeight = new JTextField();
/*  441 */     this.btnSclReadWeight = new JButton();
/*  442 */     this.btnSclZeroScale = new JButton();
/*  443 */     this.btnSclClearInput = new JButton();
/*  444 */     this.lblSclWeight = new JLabel();
/*  445 */     this.chkSclAsyncMode = new JCheckBox();
/*  446 */     this.sclPanelLiveWeight = new JPanel();
/*  447 */     this.txtSclLiveWeight = new JTextField();
/*  448 */     this.chkSclEnableLiveWeight = new JCheckBox();
/*  449 */     this.sclPanelCheckHealth = new JPanel();
/*  450 */     this.sclInternal = new JRadioButton();
/*  451 */     this.sclExternal = new JRadioButton();
/*  452 */     this.sclInteractive = new JRadioButton();
/*  453 */     this.btnSclCheckHealth = new JButton();
/*  454 */     this.txtSclCheckHealthText = new JTextField();
/*  455 */     this.btnSclCHText = new JButton();
/*  456 */     this.lblSclCHText = new JLabel();
/*  457 */     this.sclPanelDirectIO = new JPanel();
/*  458 */     this.lblSclCommand = new JLabel();
/*  459 */     this.cmbSclCommand = new JComboBox();
/*  460 */     this.btnSclExecute = new JButton();
/*  461 */     this.lblSclInXml = new JLabel();
/*  462 */     this.lblSclOutXml = new JLabel();
/*  463 */     this.jScrollPane8 = new JScrollPane();
/*  464 */     this.txtSclInXml = new JTextArea();
/*  465 */     this.jScrollPane9 = new JScrollPane();
/*  466 */     this.txtSclOutXml = new JTextArea();
/*  467 */     this.lblSclStatus = new JLabel();
/*  468 */     this.txtSclStatus = new JTextField();
/*  469 */     this.btnSclClear = new JButton();
/*  470 */     this.btnSclCopy = new JButton();
/*  471 */     this.sclPanelStat = new JPanel();
/*  472 */     this.lblSclStatistic = new JTextField();
/*  473 */     this.btnSclResetStat = new JButton();
/*  474 */     this.btnSclRetreiveStat = new JButton();
/*  475 */     this.jScrollPane10 = new JScrollPane();
/*  476 */     this.txtSclStatOutput = new JTextArea();
/*  477 */     this.sclPanelProperties = new JPanel();
/*  478 */     this.cmbSclProperties = new JComboBox();
/*  479 */     this.lblSclPropertyValue = new JLabel();
/*  480 */     this.txtSclPropertyValue = new JTextField();
/*  481 */     this.btnSclProperties = new JButton();
/*  482 */     this.scnPanelCommonMethods = new JPanel();
/*  483 */     this.lblDeviceType = new JLabel();
/*  484 */     this.cmbDeviceCategory = new JComboBox();
/*  485 */     this.lblLogicalName = new JLabel();
/*  486 */     this.cmbLogicalDevice = new JComboBox();
/*  487 */     this.btnOpen = new JButton();
/*  488 */     this.lblClaimTimeout = new JLabel();
/*  489 */     this.txtClaimTimeout = new JTextField();
/*  490 */     this.btnClaim = new JButton();
/*  491 */     this.btnRelease = new JButton();
/*  492 */     this.btnClose = new JButton();
/*  493 */     this.btnFastMode = new JButton();
/*  494 */     this.lblDeviceInfo = new JLabel();
/*  495 */     this.jScrollPane5 = new JScrollPane();
/*  496 */     this.txtDeviceInfo = new JTextArea();
/*  497 */     this.panelLogView = new JPanel();
/*  498 */     this.jScrollPane7 = new JScrollPane();
/*  499 */     this.txtLogView = new JTextArea();
/*  500 */     this.btnLogClear = new JButton();
/*      */     
/*  502 */     setDefaultCloseOperation(3);
/*  503 */     setTitle("JPOS Sample Application");
/*  504 */     setBackground(new Color(204, 204, 204));
/*      */     
/*  506 */     this.jPanel2.setBackground(new Color(204, 204, 204));
/*  507 */     this.jPanel2.setBorder(BorderFactory.createTitledBorder(""));
/*      */     
/*  509 */     this.jLabel1.setFont(new Font("Tahoma", 0, 24));
/*  510 */     this.jLabel1.setText("JPOS Sample Application");
/*      */     
/*  512 */     this.jLabel3.setFont(new Font("Tahoma", 0, 12));
/*  513 */     this.jLabel3.setText("v1.0");
/*  514 */     this.jLabel3.setToolTipText("");
/*      */     
/*  516 */     GroupLayout groupLayout1 = new GroupLayout(this.jPanel1);
/*  517 */     this.jPanel1.setLayout(groupLayout1);
/*  518 */     groupLayout1.setHorizontalGroup(groupLayout1
/*  519 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  520 */         .addGroup(groupLayout1.createSequentialGroup()
/*  521 */           .addContainerGap()
/*  522 */           .addComponent(this.jLabel1)
/*  523 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  524 */           .addComponent(this.jLabel3)
/*  525 */           .addContainerGap(-1, 32767)));
/*      */     
/*  527 */     groupLayout1.setVerticalGroup(groupLayout1
/*  528 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  529 */         .addGroup(groupLayout1.createSequentialGroup()
/*  530 */           .addGap(2, 2, 2)
/*  531 */           .addGroup(groupLayout1.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  532 */             .addComponent(this.jLabel1, -1, -1, 32767)
/*  533 */             .addComponent(this.jLabel3, -1, -1, 32767))
/*  534 */           .addContainerGap()));
/*      */ 
/*      */     
/*  537 */     this.jTabbedPane.setBackground(new Color(204, 204, 204));
/*  538 */     this.jTabbedPane.setBorder(BorderFactory.createEtchedBorder());
/*  539 */     this.jTabbedPane.setForeground(new Color(204, 204, 204));
/*      */     
/*  541 */     this.scnPanelDataEvent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Data Event ", 0, 0, new Font("Tahoma", 1, 12)));
/*  542 */     this.scnPanelDataEvent.setPreferredSize(new Dimension(489, 99));
/*  543 */     this.scnPanelDataEvent.setVerifyInputWhenFocusTarget(false);
/*      */     
/*  545 */     this.chkScnDeviceEnable.setFont(new Font("Tahoma", 0, 12));
/*  546 */     this.chkScnDeviceEnable.setText("Device Enable");
/*  547 */     this.chkScnDeviceEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  549 */             JposSampleApp.this.chkScnDeviceEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  553 */     this.chkScnDataEventEnable.setFont(new Font("Tahoma", 0, 12));
/*  554 */     this.chkScnDataEventEnable.setText("Data Event Enable");
/*  555 */     this.chkScnDataEventEnable.setMargin(new Insets(2, -1, 2, 2));
/*  556 */     this.chkScnDataEventEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  558 */             JposSampleApp.this.chkScnDataEventEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  562 */     this.chkScnAutoDataEventEnable.setFont(new Font("Tahoma", 0, 12));
/*  563 */     this.chkScnAutoDataEventEnable.setText("Auto Data Event Enable");
/*  564 */     this.chkScnAutoDataEventEnable.setMargin(new Insets(2, -2, 2, 2));
/*  565 */     this.chkScnAutoDataEventEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  567 */             JposSampleApp.this.chkScnAutoDataEventEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  571 */     this.chkScnFreezeEvents.setFont(new Font("Tahoma", 0, 12));
/*  572 */     this.chkScnFreezeEvents.setText("Freeze Events");
/*  573 */     this.chkScnFreezeEvents.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  575 */             JposSampleApp.this.chkScnFreezeEventsActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  579 */     this.chkScnAutoDisable.setFont(new Font("Tahoma", 0, 12));
/*  580 */     this.chkScnAutoDisable.setText("Auto Disable");
/*  581 */     this.chkScnAutoDisable.setMargin(new Insets(2, -1, 2, 2));
/*  582 */     this.chkScnAutoDisable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  584 */             JposSampleApp.this.chkScnAutoDisableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  588 */     this.chkScnAutoDeviceEnable.setFont(new Font("Tahoma", 0, 12));
/*  589 */     this.chkScnAutoDeviceEnable.setText("Auto Device Enable");
/*  590 */     this.chkScnAutoDeviceEnable.setMargin(new Insets(2, -2, 2, 2));
/*  591 */     this.chkScnAutoDeviceEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  593 */             JposSampleApp.this.chkScnAutoDeviceEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  597 */     this.chkScnPowerNotify.setFont(new Font("Tahoma", 0, 12));
/*  598 */     this.chkScnPowerNotify.setText("Power Notify");
/*  599 */     this.chkScnPowerNotify.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  601 */             JposSampleApp.this.chkScnPowerNotifyActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  605 */     this.txtScnPowerState.setEditable(false);
/*  606 */     this.txtScnPowerState.setFocusable(false);
/*      */     
/*  608 */     this.btnScnPowerState.setFont(new Font("Tahoma", 0, 12));
/*  609 */     this.btnScnPowerState.setText("Power State");
/*  610 */     this.btnScnPowerState.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  612 */             JposSampleApp.this.btnScnPowerStateActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  616 */     GroupLayout groupLayout2 = new GroupLayout(this.scnPanelDataEvent);
/*  617 */     this.scnPanelDataEvent.setLayout(groupLayout2);
/*  618 */     groupLayout2.setHorizontalGroup(groupLayout2
/*  619 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  620 */         .addGroup(groupLayout2.createSequentialGroup()
/*  621 */           .addContainerGap()
/*  622 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  623 */             .addComponent(this.chkScnDeviceEnable)
/*  624 */             .addComponent(this.chkScnFreezeEvents)
/*  625 */             .addComponent(this.chkScnPowerNotify))
/*  626 */           .addGap(25, 25, 25)
/*  627 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/*  628 */             .addComponent(this.chkScnAutoDisable)
/*  629 */             .addComponent(this.chkScnDataEventEnable, -1, -1, 32767)
/*  630 */             .addComponent(this.btnScnPowerState, -1, -1, 32767))
/*  631 */           .addGap(25, 25, 25)
/*  632 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  633 */             .addComponent(this.chkScnAutoDeviceEnable)
/*  634 */             .addComponent(this.chkScnAutoDataEventEnable)
/*  635 */             .addComponent(this.txtScnPowerState, -2, 144, -2))
/*  636 */           .addContainerGap(42, 32767)));
/*      */     
/*  638 */     groupLayout2.setVerticalGroup(groupLayout2
/*  639 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  640 */         .addGroup(groupLayout2.createSequentialGroup()
/*  641 */           .addGap(0, 0, 0)
/*  642 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  643 */             .addComponent(this.chkScnAutoDataEventEnable, -2, 23, -2)
/*  644 */             .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  645 */               .addComponent(this.chkScnDeviceEnable)
/*  646 */               .addComponent(this.chkScnDataEventEnable)))
/*  647 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  648 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  649 */             .addComponent(this.chkScnFreezeEvents)
/*  650 */             .addComponent(this.chkScnAutoDisable)
/*  651 */             .addComponent(this.chkScnAutoDeviceEnable))
/*  652 */           .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  653 */             .addGroup(groupLayout2.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  654 */               .addComponent(this.txtScnPowerState, -2, 27, -2)
/*  655 */               .addComponent(this.btnScnPowerState, -2, 28, -2))
/*  656 */             .addGroup(groupLayout2.createSequentialGroup()
/*  657 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  658 */               .addComponent(this.chkScnPowerNotify)))));
/*      */ 
/*      */     
/*  661 */     this.scnPanelRecData.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Barcode Scanning ", 0, 0, new Font("Tahoma", 1, 12)));
/*  662 */     this.scnPanelRecData.setAlignmentX(1.0F);
/*  663 */     this.scnPanelRecData.setPreferredSize(new Dimension(488, 262));
/*      */     
/*  665 */     this.lblScanDataLabel.setFont(new Font("Tahoma", 0, 12));
/*  666 */     this.lblScanDataLabel.setText("Scan Data Label");
/*  667 */     this.lblScanDataLabel.setAlignmentX(0.5F);
/*      */     
/*  669 */     this.jScrollPane3.setAutoscrolls(true);
/*      */     
/*  671 */     this.txtScanDataLabel.setEditable(false);
/*  672 */     this.txtScanDataLabel.setColumns(20);
/*  673 */     this.txtScanDataLabel.setLineWrap(true);
/*  674 */     this.txtScanDataLabel.setRows(5);
/*  675 */     this.txtScanDataLabel.setWrapStyleWord(true);
/*  676 */     this.txtScanDataLabel.setPreferredSize(new Dimension(0, 30));
/*  677 */     this.jScrollPane3.setViewportView(this.txtScanDataLabel);
/*      */     
/*  679 */     this.buttonGroup1.add(this.scanDataText);
/*  680 */     this.scanDataText.setFont(new Font("Tahoma", 0, 12));
/*  681 */     this.scanDataText.setText("Text View");
/*  682 */     this.scanDataText.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  684 */             JposSampleApp.this.scanDataTextActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  688 */     this.lblScanData.setFont(new Font("Tahoma", 0, 12));
/*  689 */     this.lblScanData.setText("Scan Data");
/*      */     
/*  691 */     this.txtScanData.setEditable(false);
/*  692 */     this.txtScanData.setColumns(20);
/*  693 */     this.txtScanData.setRows(5);
/*  694 */     this.txtScanData.setPreferredSize(new Dimension(0, 30));
/*  695 */     this.jScrollPane6.setViewportView(this.txtScanData);
/*      */     
/*  697 */     this.lblScanDataType.setFont(new Font("Tahoma", 0, 12));
/*  698 */     this.lblScanDataType.setText("Scan Data Type");
/*      */     
/*  700 */     this.txtScanDataType.setEditable(false);
/*  701 */     this.txtScanDataType.setFont(new Font("Tahoma", 0, 12));
/*  702 */     this.txtScanDataType.setPreferredSize(new Dimension(0, 30));
/*      */     
/*  704 */     this.lblDataCount.setFont(new Font("Tahoma", 0, 12));
/*  705 */     this.lblDataCount.setText("Data Count");
/*      */     
/*  707 */     this.txtDataCount.setEditable(false);
/*  708 */     this.txtDataCount.setFont(new Font("Tahoma", 0, 12));
/*  709 */     this.txtDataCount.setPreferredSize(new Dimension(0, 30));
/*      */     
/*  711 */     this.btnClearInput.setFont(new Font("Tahoma", 0, 12));
/*  712 */     this.btnClearInput.setText("Clear Input");
/*  713 */     this.btnClearInput.setPreferredSize(new Dimension(100, 30));
/*  714 */     this.btnClearInput.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  716 */             JposSampleApp.this.btnClearInputActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  720 */     this.chkScnDecodeData.setFont(new Font("Tahoma", 0, 12));
/*  721 */     this.chkScnDecodeData.setText("Decode Data");
/*  722 */     this.chkScnDecodeData.setMargin(new Insets(2, -2, 2, 2));
/*  723 */     this.chkScnDecodeData.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  725 */             JposSampleApp.this.chkScnDecodeDataActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  729 */     this.buttonGroup1.add(this.scanDataHex);
/*  730 */     this.scanDataHex.setFont(new Font("Tahoma", 0, 12));
/*  731 */     this.scanDataHex.setText("Hex View");
/*  732 */     this.scanDataHex.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  734 */             JposSampleApp.this.scanDataHexActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  738 */     GroupLayout groupLayout3 = new GroupLayout(this.scnPanelRecData);
/*  739 */     this.scnPanelRecData.setLayout(groupLayout3);
/*  740 */     groupLayout3.setHorizontalGroup(groupLayout3
/*  741 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  742 */         .addGroup(groupLayout3.createSequentialGroup()
/*  743 */           .addGap(12, 12, 12)
/*  744 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  745 */             .addComponent(this.lblScanDataType)
/*  746 */             .addComponent(this.lblDataCount)
/*  747 */             .addComponent(this.lblScanDataLabel)
/*  748 */             .addComponent(this.lblScanData))
/*  749 */           .addGap(12, 12, 12)
/*  750 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  751 */             .addGroup(groupLayout3.createSequentialGroup()
/*  752 */               .addComponent(this.txtDataCount, -2, 164, -2)
/*  753 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 15, 32767)
/*  754 */               .addComponent(this.btnClearInput, -2, 164, -2))
/*  755 */             .addComponent(this.txtScanDataType, -1, -1, 32767)
/*  756 */             .addComponent(this.jScrollPane6)
/*  757 */             .addComponent(this.jScrollPane3)
/*  758 */             .addGroup(GroupLayout.Alignment.TRAILING, groupLayout3.createSequentialGroup()
/*  759 */               .addComponent(this.chkScnDecodeData)
/*  760 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, 32767)
/*  761 */               .addComponent(this.scanDataText)
/*  762 */               .addGap(3, 3, 3)
/*  763 */               .addComponent(this.scanDataHex)))
/*  764 */           .addGap(10, 10, 10)));
/*      */     
/*  766 */     groupLayout3.setVerticalGroup(groupLayout3
/*  767 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  768 */         .addGroup(groupLayout3.createSequentialGroup()
/*  769 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  770 */             .addComponent(this.scanDataHex)
/*  771 */             .addComponent(this.scanDataText)
/*  772 */             .addComponent(this.chkScnDecodeData))
/*  773 */           .addGap(4, 4, 4)
/*  774 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  775 */             .addComponent(this.lblScanDataLabel)
/*  776 */             .addComponent(this.jScrollPane3, -2, 60, -2))
/*  777 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  778 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  779 */             .addComponent(this.lblScanData)
/*  780 */             .addComponent(this.jScrollPane6, -2, 60, -2))
/*  781 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  782 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  783 */             .addComponent(this.lblScanDataType)
/*  784 */             .addComponent(this.txtScanDataType, -2, 28, -2))
/*  785 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  786 */           .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  787 */             .addComponent(this.lblDataCount)
/*  788 */             .addGroup(groupLayout3.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  789 */               .addComponent(this.btnClearInput, -2, 28, -2)
/*  790 */               .addComponent(this.txtDataCount, -2, 28, -2)))
/*  791 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  794 */     this.scnPanelCheckHealth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Check Health ", 0, 0, new Font("Tahoma", 1, 12)));
/*  795 */     this.scnPanelCheckHealth.setPreferredSize(new Dimension(504, 89));
/*      */     
/*  797 */     this.checkHealthBtnGroup.add(this.scnInternalCH);
/*  798 */     this.scnInternalCH.setFont(new Font("Tahoma", 0, 12));
/*  799 */     this.scnInternalCH.setText("Internal");
/*  800 */     this.scnInternalCH.setMargin(new Insets(2, -1, 2, 2));
/*  801 */     this.scnInternalCH.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  803 */             JposSampleApp.this.scnInternalCHActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  807 */     this.checkHealthBtnGroup.add(this.scnExternalCH);
/*  808 */     this.scnExternalCH.setFont(new Font("Tahoma", 0, 12));
/*  809 */     this.scnExternalCH.setText("External");
/*  810 */     this.scnExternalCH.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  812 */             JposSampleApp.this.scnExternalCHActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  816 */     this.checkHealthBtnGroup.add(this.scnInteractiveCH);
/*  817 */     this.scnInteractiveCH.setFont(new Font("Tahoma", 0, 12));
/*  818 */     this.scnInteractiveCH.setText("Interactive");
/*  819 */     this.scnInteractiveCH.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  821 */             JposSampleApp.this.scnInteractiveCHActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  825 */     this.btnScnCheckHealth.setFont(new Font("Tahoma", 0, 12));
/*  826 */     this.btnScnCheckHealth.setText("Check Health");
/*  827 */     this.btnScnCheckHealth.setAlignmentX(0.5F);
/*  828 */     this.btnScnCheckHealth.setPreferredSize(new Dimension(100, 30));
/*  829 */     this.btnScnCheckHealth.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  831 */             JposSampleApp.this.btnScnCheckHealthActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  835 */     this.lblScnCheckHealthText.setFont(new Font("Tahoma", 0, 12));
/*  836 */     this.lblScnCheckHealthText.setText("HealthCheck Text  ");
/*      */     
/*  838 */     this.txtScnHealthCheckText.setPreferredSize(new Dimension(100, 30));
/*  839 */     this.txtScnHealthCheckText.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  841 */             JposSampleApp.this.txtScnHealthCheckTextActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  845 */     this.btnScnCheckHealthText.setFont(new Font("Tahoma", 0, 12));
/*  846 */     this.btnScnCheckHealthText.setText("Health Check Text");
/*  847 */     this.btnScnCheckHealthText.setAlignmentX(0.5F);
/*  848 */     this.btnScnCheckHealthText.setPreferredSize(new Dimension(100, 30));
/*  849 */     this.btnScnCheckHealthText.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  851 */             JposSampleApp.this.btnScnCheckHealthTextActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  855 */     GroupLayout groupLayout4 = new GroupLayout(this.scnPanelCheckHealth);
/*  856 */     this.scnPanelCheckHealth.setLayout(groupLayout4);
/*  857 */     groupLayout4.setHorizontalGroup(groupLayout4
/*  858 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  859 */         .addGroup(groupLayout4.createSequentialGroup()
/*  860 */           .addGap(10, 10, 10)
/*  861 */           .addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  862 */             .addGroup(groupLayout4.createSequentialGroup()
/*  863 */               .addComponent(this.scnInternalCH)
/*  864 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  865 */               .addComponent(this.scnExternalCH)
/*  866 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  867 */               .addComponent(this.scnInteractiveCH))
/*  868 */             .addGroup(groupLayout4.createSequentialGroup()
/*  869 */               .addComponent(this.btnScnCheckHealthText, -2, 156, -2)
/*  870 */               .addGap(16, 16, 16)
/*  871 */               .addComponent(this.lblScnCheckHealthText)))
/*  872 */           .addGap(0, 0, 0)
/*  873 */           .addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  874 */             .addComponent(this.btnScnCheckHealth, -2, 182, -2)
/*  875 */             .addComponent(this.txtScnHealthCheckText, -2, 182, -2))
/*  876 */           .addGap(10, 10, 10)));
/*      */     
/*  878 */     groupLayout4.setVerticalGroup(groupLayout4
/*  879 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  880 */         .addGroup(groupLayout4.createSequentialGroup()
/*  881 */           .addGap(1, 1, 1)
/*  882 */           .addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  883 */             .addComponent(this.scnInternalCH)
/*  884 */             .addComponent(this.scnExternalCH)
/*  885 */             .addComponent(this.scnInteractiveCH)
/*  886 */             .addComponent(this.btnScnCheckHealth, -2, 28, -2))
/*  887 */           .addGap(22, 22, 22)
/*  888 */           .addGroup(groupLayout4.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  889 */             .addComponent(this.btnScnCheckHealthText, -2, 28, -2)
/*  890 */             .addComponent(this.txtScnHealthCheckText, -2, 28, -2)
/*  891 */             .addComponent(this.lblScnCheckHealthText))
/*  892 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  895 */     this.scnPanelStat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Statistics ", 0, 0, new Font("Tahoma", 1, 12)));
/*  896 */     this.scnPanelStat.setPreferredSize(new Dimension(503, 130));
/*      */     
/*  898 */     this.lblScnStatistic.setFont(new Font("Tahoma", 0, 12));
/*      */     
/*  900 */     this.btnScnRetreiveStat.setFont(new Font("Tahoma", 0, 12));
/*  901 */     this.btnScnRetreiveStat.setText("Retrieve Statistics");
/*  902 */     this.btnScnRetreiveStat.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  904 */             JposSampleApp.this.btnScnRetreiveStatActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  908 */     this.btnScnResetStat.setFont(new Font("Tahoma", 0, 12));
/*  909 */     this.btnScnResetStat.setText("Reset Statistics");
/*  910 */     this.btnScnResetStat.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  912 */             JposSampleApp.this.btnScnResetStatActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  916 */     this.txtScnStatOutput.setColumns(4);
/*  917 */     this.txtScnStatOutput.setFont(new Font("Monospaced", 0, 12));
/*  918 */     this.txtScnStatOutput.setLineWrap(true);
/*  919 */     this.txtScnStatOutput.setRows(5);
/*  920 */     this.txtScnStatOutput.setWrapStyleWord(true);
/*  921 */     this.jScrollPane1.setViewportView(this.txtScnStatOutput);
/*      */     
/*  923 */     this.jLabel2.setText("Filter By");
/*      */     
/*  925 */     GroupLayout groupLayout5 = new GroupLayout(this.scnPanelStat);
/*  926 */     this.scnPanelStat.setLayout(groupLayout5);
/*  927 */     groupLayout5.setHorizontalGroup(groupLayout5
/*  928 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  929 */         .addGroup(groupLayout5.createSequentialGroup()
/*  930 */           .addContainerGap()
/*  931 */           .addGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  932 */             .addComponent(this.jScrollPane1, -2, 458, -2)
/*  933 */             .addGroup(groupLayout5.createSequentialGroup()
/*  934 */               .addComponent(this.jLabel2)
/*  935 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/*  936 */               .addComponent(this.lblScnStatistic, -2, 81, -2)
/*  937 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/*  938 */               .addComponent(this.btnScnRetreiveStat)
/*  939 */               .addGap(39, 39, 39)
/*  940 */               .addComponent(this.btnScnResetStat, -2, 144, -2)))
/*  941 */           .addContainerGap(-1, 32767)));
/*      */     
/*  943 */     groupLayout5.setVerticalGroup(groupLayout5
/*  944 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/*  945 */         .addGroup(groupLayout5.createSequentialGroup()
/*  946 */           .addGap(4, 4, 4)
/*  947 */           .addGroup(groupLayout5.createParallelGroup(GroupLayout.Alignment.BASELINE)
/*  948 */             .addComponent(this.lblScnStatistic, -2, 20, -2)
/*  949 */             .addComponent(this.btnScnRetreiveStat, -2, 28, -2)
/*  950 */             .addComponent(this.btnScnResetStat, -2, 28, -2)
/*  951 */             .addComponent(this.jLabel2))
/*  952 */           .addGap(7, 7, 7)
/*  953 */           .addComponent(this.jScrollPane1, -2, 67, -2)
/*  954 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/*  957 */     this.scnPanelDirectIO.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Direct IO ", 0, 0, new Font("Tahoma", 1, 12)));
/*  958 */     this.scnPanelDirectIO.setPreferredSize(new Dimension(485, 359));
/*      */     
/*  960 */     this.cmbScnCommand.setFont(new Font("Tahoma", 0, 12));
/*  961 */     this.cmbScnCommand.setAutoscrolls(true);
/*  962 */     this.cmbScnCommand.setPreferredSize(new Dimension(153, 30));
/*  963 */     this.cmbScnCommand.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  965 */             JposSampleApp.this.cmbScnCommandActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  969 */     this.btnScnExecute.setFont(new Font("Tahoma", 0, 12));
/*  970 */     this.btnScnExecute.setText("Execute");
/*  971 */     this.btnScnExecute.setPreferredSize(new Dimension(100, 30));
/*  972 */     this.btnScnExecute.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  974 */             JposSampleApp.this.btnScnExecuteActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  978 */     this.lblScnStatus.setFont(new Font("Tahoma", 0, 12));
/*  979 */     this.lblScnStatus.setText("Status ");
/*      */     
/*  981 */     this.txtScnStatus.setEditable(false);
/*  982 */     this.txtScnStatus.setFont(new Font("Tahoma", 0, 12));
/*  983 */     this.txtScnStatus.setPreferredSize(new Dimension(100, 30));
/*  984 */     this.txtScnStatus.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  986 */             JposSampleApp.this.txtScnStatusActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  990 */     this.btnScnClear.setFont(new Font("Tahoma", 0, 12));
/*  991 */     this.btnScnClear.setText("Clear");
/*  992 */     this.btnScnClear.setPreferredSize(new Dimension(100, 30));
/*  993 */     this.btnScnClear.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  995 */             JposSampleApp.this.btnScnClearActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/*  999 */     this.lblScnInXml.setFont(new Font("Tahoma", 0, 12));
/* 1000 */     this.lblScnInXml.setText("InXml :");
/*      */     
/* 1002 */     this.lblScnOutXml.setFont(new Font("Tahoma", 0, 12));
/* 1003 */     this.lblScnOutXml.setText("OutXml :");
/*      */     
/* 1005 */     this.jScrollPane2.setAutoscrolls(true);
/* 1006 */     this.jScrollPane2.setCursor(new Cursor(0));
/*      */     
/* 1008 */     this.txtScnOutXml.setEditable(false);
/* 1009 */     this.txtScnOutXml.setColumns(100);
/* 1010 */     this.txtScnOutXml.setFont(new Font("Monospaced", 0, 12));
/* 1011 */     this.txtScnOutXml.setLineWrap(true);
/* 1012 */     this.txtScnOutXml.setRows(50);
/* 1013 */     this.txtScnOutXml.setWrapStyleWord(true);
/* 1014 */     this.txtScnOutXml.setSelectionColor(new Color(153, 204, 255));
/* 1015 */     this.jScrollPane2.setViewportView(this.txtScnOutXml);
/*      */     
/* 1017 */     this.jScrollPane4.setAutoscrolls(true);
/*      */     
/* 1019 */     this.txtScnInxml.setColumns(100);
/* 1020 */     this.txtScnInxml.setFont(new Font("Monospaced", 0, 12));
/* 1021 */     this.txtScnInxml.setRows(50);
/* 1022 */     this.txtScnInxml.setCursor(new Cursor(0));
/* 1023 */     this.jScrollPane4.setViewportView(this.txtScnInxml);
/*      */     
/* 1025 */     this.btnScnCopy.setFont(new Font("Tahoma", 0, 12));
/* 1026 */     this.btnScnCopy.setText("Copy");
/* 1027 */     this.btnScnCopy.setPreferredSize(new Dimension(100, 30));
/* 1028 */     this.btnScnCopy.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1030 */             JposSampleApp.this.btnScnCopyActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1034 */     this.lblScnCommand.setFont(new Font("Tahoma", 0, 12));
/* 1035 */     this.lblScnCommand.setText("Command ");
/*      */     
/* 1037 */     GroupLayout groupLayout6 = new GroupLayout(this.scnPanelDirectIO);
/* 1038 */     this.scnPanelDirectIO.setLayout(groupLayout6);
/* 1039 */     groupLayout6.setHorizontalGroup(groupLayout6
/* 1040 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1041 */         .addGroup(GroupLayout.Alignment.TRAILING, groupLayout6.createSequentialGroup()
/* 1042 */           .addGap(10, 10, 10)
/* 1043 */           .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1044 */             .addGroup(groupLayout6.createSequentialGroup()
/* 1045 */               .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 1046 */                 .addGroup(GroupLayout.Alignment.LEADING, groupLayout6.createSequentialGroup()
/* 1047 */                   .addComponent(this.lblScnCommand)
/* 1048 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1049 */                   .addComponent(this.cmbScnCommand, 0, -1, 32767))
/* 1050 */                 .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1051 */                   .addComponent(this.lblScnInXml)
/* 1052 */                   .addComponent(this.jScrollPane4, -2, 225, -2)))
/* 1053 */               .addGap(10, 10, 10)
/* 1054 */               .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1055 */                 .addComponent(this.lblScnOutXml)
/* 1056 */                 .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1057 */                   .addComponent(this.btnScnExecute, GroupLayout.Alignment.TRAILING, -2, 182, -2)
/* 1058 */                   .addComponent(this.jScrollPane2, GroupLayout.Alignment.TRAILING, -2, 225, -2))))
/* 1059 */             .addGroup(groupLayout6.createSequentialGroup()
/* 1060 */               .addComponent(this.lblScnStatus)
/* 1061 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1062 */               .addComponent(this.txtScnStatus, -2, 81, -2)
/* 1063 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1064 */               .addComponent(this.btnScnCopy, -2, 87, -2)
/* 1065 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1066 */               .addComponent(this.btnScnClear, -2, 87, -2)))
/* 1067 */           .addGap(10, 10, 10)));
/*      */     
/* 1069 */     groupLayout6.setVerticalGroup(groupLayout6
/* 1070 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1071 */         .addGroup(groupLayout6.createSequentialGroup()
/* 1072 */           .addGap(2, 2, 2)
/* 1073 */           .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1074 */             .addComponent(this.cmbScnCommand, -2, 28, -2)
/* 1075 */             .addComponent(this.lblScnCommand)
/* 1076 */             .addComponent(this.btnScnExecute, -2, 28, -2))
/* 1077 */           .addGap(6, 6, 6)
/* 1078 */           .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1079 */             .addComponent(this.lblScnInXml)
/* 1080 */             .addComponent(this.lblScnOutXml))
/* 1081 */           .addGap(0, 0, 0)
/* 1082 */           .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.TRAILING)
/* 1083 */             .addComponent(this.jScrollPane2, -2, 179, -2)
/* 1084 */             .addComponent(this.jScrollPane4, -2, 179, -2))
/* 1085 */           .addGap(6, 6, 6)
/* 1086 */           .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1087 */             .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1088 */               .addComponent(this.btnScnCopy, -2, 28, -2)
/* 1089 */               .addComponent(this.btnScnClear, -2, 28, -2))
/* 1090 */             .addGroup(groupLayout6.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1091 */               .addComponent(this.txtScnStatus, -2, 20, -2)
/* 1092 */               .addComponent(this.lblScnStatus)))));
/*      */ 
/*      */     
/* 1095 */     this.scnPanelProperties.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Properties ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1096 */     this.scnPanelProperties.setPreferredSize(new Dimension(480, 147));
/*      */     
/* 1098 */     this.cmbScnProperties.setFont(new Font("Tahoma", 0, 12));
/* 1099 */     this.cmbScnProperties.setPreferredSize(new Dimension(153, 30));
/* 1100 */     this.cmbScnProperties.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1102 */             JposSampleApp.this.cmbScnPropertiesActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1106 */     this.lblScnPropertyValue.setFont(new Font("Tahoma", 0, 12));
/* 1107 */     this.lblScnPropertyValue.setText("Property Value ");
/*      */     
/* 1109 */     this.txtScnPropertyValue.setFont(new Font("Tahoma", 0, 12));
/* 1110 */     this.txtScnPropertyValue.setHorizontalAlignment(0);
/* 1111 */     this.txtScnPropertyValue.setPreferredSize(new Dimension(20, 30));
/*      */     
/* 1113 */     this.btnScnProperties.setFont(new Font("Tahoma", 0, 12));
/* 1114 */     this.btnScnProperties.setText("Set Properties");
/* 1115 */     this.btnScnProperties.setPreferredSize(new Dimension(100, 30));
/* 1116 */     this.btnScnProperties.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1118 */             JposSampleApp.this.btnScnPropertiesActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1122 */     this.btnScnClearInputProperties.setFont(new Font("Tahoma", 0, 12));
/* 1123 */     this.btnScnClearInputProperties.setText("Clear Input Properties");
/* 1124 */     this.btnScnClearInputProperties.setPreferredSize(new Dimension(100, 30));
/* 1125 */     this.btnScnClearInputProperties.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1127 */             JposSampleApp.this.btnScnClearInputPropertiesActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1131 */     GroupLayout groupLayout7 = new GroupLayout(this.scnPanelProperties);
/* 1132 */     this.scnPanelProperties.setLayout(groupLayout7);
/* 1133 */     groupLayout7.setHorizontalGroup(groupLayout7
/* 1134 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1135 */         .addGroup(groupLayout7.createSequentialGroup()
/* 1136 */           .addGap(10, 10, 10)
/* 1137 */           .addGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1138 */             .addGroup(groupLayout7.createSequentialGroup()
/* 1139 */               .addComponent(this.lblScnPropertyValue)
/* 1140 */               .addGap(4, 4, 4)
/* 1141 */               .addComponent(this.txtScnPropertyValue, -1, -1, 32767))
/* 1142 */             .addComponent(this.cmbScnProperties, -2, 224, -2))
/* 1143 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1144 */           .addGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1145 */             .addComponent(this.btnScnClearInputProperties, GroupLayout.Alignment.TRAILING, -2, 182, -2)
/* 1146 */             .addComponent(this.btnScnProperties, GroupLayout.Alignment.TRAILING, -2, 182, -2))
/* 1147 */           .addGap(10, 10, 10)));
/*      */     
/* 1149 */     groupLayout7.setVerticalGroup(groupLayout7
/* 1150 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1151 */         .addGroup(groupLayout7.createSequentialGroup()
/* 1152 */           .addGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1153 */             .addComponent(this.cmbScnProperties, -2, 28, -2)
/* 1154 */             .addComponent(this.btnScnClearInputProperties, -2, 28, -2))
/* 1155 */           .addGap(6, 6, 6)
/* 1156 */           .addGroup(groupLayout7.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1157 */             .addComponent(this.txtScnPropertyValue, -2, 28, -2)
/* 1158 */             .addComponent(this.btnScnProperties, -2, 28, -2)
/* 1159 */             .addComponent(this.lblScnPropertyValue))
/* 1160 */           .addGap(0, 0, 0)));
/*      */ 
/*      */     
/* 1163 */     GroupLayout groupLayout8 = new GroupLayout(this.scannerPanel);
/* 1164 */     this.scannerPanel.setLayout(groupLayout8);
/* 1165 */     groupLayout8.setHorizontalGroup(groupLayout8
/* 1166 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1167 */         .addGroup(groupLayout8.createSequentialGroup()
/* 1168 */           .addGap(6, 6, 6)
/* 1169 */           .addGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 1170 */             .addComponent(this.scnPanelRecData, GroupLayout.Alignment.LEADING, -2, 485, -2)
/* 1171 */             .addComponent(this.scnPanelDataEvent, -2, 485, -2)
/* 1172 */             .addComponent(this.scnPanelStat, -2, 485, -2))
/* 1173 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1174 */           .addGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1175 */             .addComponent(this.scnPanelProperties, -1, 488, 32767)
/* 1176 */             .addComponent(this.scnPanelCheckHealth, -1, 488, 32767)
/* 1177 */             .addComponent(this.scnPanelDirectIO, -1, 488, 32767))
/* 1178 */           .addGap(11, 11, 11)));
/*      */     
/* 1180 */     groupLayout8.setVerticalGroup(groupLayout8
/* 1181 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1182 */         .addGroup(groupLayout8.createSequentialGroup()
/* 1183 */           .addGap(6, 6, 6)
/* 1184 */           .addGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1185 */             .addComponent(this.scnPanelDataEvent, -2, 108, -2)
/* 1186 */             .addComponent(this.scnPanelCheckHealth, -2, 108, -2))
/* 1187 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1188 */           .addGroup(groupLayout8.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1189 */             .addGroup(groupLayout8.createSequentialGroup()
/* 1190 */               .addComponent(this.scnPanelProperties, -2, 95, -2)
/* 1191 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1192 */               .addComponent(this.scnPanelDirectIO, -2, 291, -2))
/* 1193 */             .addGroup(groupLayout8.createSequentialGroup()
/* 1194 */               .addComponent(this.scnPanelRecData, -2, 253, -2)
/* 1195 */               .addGap(6, 6, 6)
/* 1196 */               .addComponent(this.scnPanelStat, -2, 133, -2)))
/* 1197 */           .addGap(4, 4, 4)));
/*      */ 
/*      */     
/* 1200 */     this.scnPanelCheckHealth.getAccessibleContext().setAccessibleDescription("");
/*      */     
/* 1202 */     this.jTabbedPane.addTab("", this.scannerPanel);
/*      */     
/* 1204 */     this.sclPanelDataEvent.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Data Event ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1205 */     this.sclPanelDataEvent.setPreferredSize(new Dimension(480, 99));
/*      */     
/* 1207 */     this.chkSclDeviceEnable.setFont(new Font("Tahoma", 0, 12));
/* 1208 */     this.chkSclDeviceEnable.setText("Device Enable");
/* 1209 */     this.chkSclDeviceEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1211 */             JposSampleApp.this.chkSclDeviceEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1215 */     this.chkSclDataEventEnable.setFont(new Font("Tahoma", 0, 12));
/* 1216 */     this.chkSclDataEventEnable.setText("Data Event Enable");
/* 1217 */     this.chkSclDataEventEnable.setMargin(new Insets(2, -1, 2, 2));
/* 1218 */     this.chkSclDataEventEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1220 */             JposSampleApp.this.chkSclDataEventEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1224 */     this.chkSclFreezeEvents.setFont(new Font("Tahoma", 0, 12));
/* 1225 */     this.chkSclFreezeEvents.setText("Freeze Events");
/* 1226 */     this.chkSclFreezeEvents.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1228 */             JposSampleApp.this.chkSclFreezeEventsActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1232 */     this.chkSclAutoDeviceEnable.setFont(new Font("Tahoma", 0, 12));
/* 1233 */     this.chkSclAutoDeviceEnable.setText("Auto Device Enable");
/* 1234 */     this.chkSclAutoDeviceEnable.setMargin(new Insets(2, -2, 2, 2));
/* 1235 */     this.chkSclAutoDeviceEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1237 */             JposSampleApp.this.chkSclAutoDeviceEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1241 */     this.chkSclAutoDisable.setFont(new Font("Tahoma", 0, 12));
/* 1242 */     this.chkSclAutoDisable.setText("Auto Disable");
/* 1243 */     this.chkSclAutoDisable.setMargin(new Insets(2, -1, 2, 2));
/* 1244 */     this.chkSclAutoDisable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1246 */             JposSampleApp.this.chkSclAutoDisableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1250 */     this.chkSclAutoDataEventEnable.setFont(new Font("Tahoma", 0, 12));
/* 1251 */     this.chkSclAutoDataEventEnable.setText("Auto Data Event Enable");
/* 1252 */     this.chkSclAutoDataEventEnable.setMargin(new Insets(2, -2, 2, 2));
/* 1253 */     this.chkSclAutoDataEventEnable.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1255 */             JposSampleApp.this.chkSclAutoDataEventEnableActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1259 */     this.chkSclPowerNotify.setFont(new Font("Tahoma", 0, 12));
/* 1260 */     this.chkSclPowerNotify.setText("Power Notify");
/* 1261 */     this.chkSclPowerNotify.setMargin(new Insets(1, 2, 2, 2));
/* 1262 */     this.chkSclPowerNotify.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1264 */             JposSampleApp.this.chkSclPowerNotifyActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1268 */     this.txtSclPowerState.setEditable(false);
/* 1269 */     this.txtSclPowerState.setFont(new Font("Tahoma", 0, 12));
/* 1270 */     this.txtSclPowerState.setRequestFocusEnabled(false);
/*      */     
/* 1272 */     this.btnSclPowerState.setFont(new Font("Tahoma", 0, 12));
/* 1273 */     this.btnSclPowerState.setText("Power State");
/* 1274 */     this.btnSclPowerState.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1276 */             JposSampleApp.this.btnSclPowerStateActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1280 */     GroupLayout groupLayout9 = new GroupLayout(this.sclPanelDataEvent);
/* 1281 */     this.sclPanelDataEvent.setLayout(groupLayout9);
/* 1282 */     groupLayout9.setHorizontalGroup(groupLayout9
/* 1283 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1284 */         .addGroup(groupLayout9.createSequentialGroup()
/* 1285 */           .addContainerGap()
/* 1286 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1287 */             .addComponent(this.chkSclDeviceEnable)
/* 1288 */             .addComponent(this.chkSclFreezeEvents)
/* 1289 */             .addComponent(this.chkSclPowerNotify))
/* 1290 */           .addGap(25, 25, 25)
/* 1291 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1292 */             .addComponent(this.chkSclAutoDisable)
/* 1293 */             .addComponent(this.chkSclDataEventEnable, -1, -1, 32767)
/* 1294 */             .addComponent(this.btnSclPowerState, -1, -1, 32767))
/* 1295 */           .addGap(25, 25, 25)
/* 1296 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1297 */             .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 1298 */               .addComponent(this.txtSclPowerState, GroupLayout.Alignment.LEADING, -2, 115, -2)
/* 1299 */               .addComponent(this.chkSclAutoDeviceEnable, GroupLayout.Alignment.LEADING, -1, -1, 32767))
/* 1300 */             .addComponent(this.chkSclAutoDataEventEnable))
/* 1301 */           .addContainerGap(42, 32767)));
/*      */     
/* 1303 */     groupLayout9.setVerticalGroup(groupLayout9
/* 1304 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1305 */         .addGroup(groupLayout9.createSequentialGroup()
/* 1306 */           .addGap(0, 0, 0)
/* 1307 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1308 */             .addComponent(this.chkSclAutoDataEventEnable, GroupLayout.Alignment.TRAILING, -2, 23, -2)
/* 1309 */             .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1310 */               .addComponent(this.chkSclDeviceEnable)
/* 1311 */               .addComponent(this.chkSclDataEventEnable)))
/* 1312 */           .addGap(0, 0, 0)
/* 1313 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1314 */             .addComponent(this.chkSclFreezeEvents)
/* 1315 */             .addComponent(this.chkSclAutoDisable)
/* 1316 */             .addComponent(this.chkSclAutoDeviceEnable))
/* 1317 */           .addGap(0, 0, 0)
/* 1318 */           .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1319 */             .addComponent(this.chkSclPowerNotify, GroupLayout.Alignment.TRAILING, -2, 23, -2)
/* 1320 */             .addGroup(groupLayout9.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1321 */               .addComponent(this.txtSclPowerState, -2, 27, -2)
/* 1322 */               .addComponent(this.btnSclPowerState, -2, 28, -2)))
/* 1323 */           .addContainerGap(12, 32767)));
/*      */ 
/*      */     
/* 1326 */     this.sclPanelScaleWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Scale Weight ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1327 */     this.sclPanelScaleWeight.setPreferredSize(new Dimension(480, 391));
/*      */     
/* 1329 */     this.lblSclReadWeght.setFont(new Font("Tahoma", 0, 12));
/* 1330 */     this.lblSclReadWeght.setText("Timeout (ms) :");
/*      */     
/* 1332 */     this.txtSclRWTimeout.setFont(new Font("Ubuntu", 0, 12));
/* 1333 */     this.txtSclRWTimeout.setHorizontalAlignment(0);
/* 1334 */     this.txtSclRWTimeout.setText("1000");
/*      */     
/* 1336 */     this.txtSclDisplayWeight.setEditable(false);
/* 1337 */     this.txtSclDisplayWeight.setFont(new Font("Tahoma", 0, 12));
/* 1338 */     this.txtSclDisplayWeight.setPreferredSize(new Dimension(100, 28));
/*      */     
/* 1340 */     this.btnSclReadWeight.setFont(new Font("Tahoma", 0, 12));
/* 1341 */     this.btnSclReadWeight.setText("Read Weight");
/* 1342 */     this.btnSclReadWeight.setPreferredSize(new Dimension(100, 30));
/* 1343 */     this.btnSclReadWeight.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1345 */             JposSampleApp.this.btnSclReadWeightActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1349 */     this.btnSclZeroScale.setFont(new Font("Tahoma", 0, 12));
/* 1350 */     this.btnSclZeroScale.setText("Zero Scale");
/* 1351 */     this.btnSclZeroScale.setPreferredSize(new Dimension(100, 30));
/* 1352 */     this.btnSclZeroScale.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1354 */             JposSampleApp.this.btnSclZeroScaleActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1358 */     this.btnSclClearInput.setFont(new Font("Tahoma", 0, 12));
/* 1359 */     this.btnSclClearInput.setText("Clear Input");
/* 1360 */     this.btnSclClearInput.setActionCommand("btnClearInput");
/* 1361 */     this.btnSclClearInput.setPreferredSize(new Dimension(100, 30));
/* 1362 */     this.btnSclClearInput.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1364 */             JposSampleApp.this.btnSclClearInputActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1368 */     this.lblSclWeight.setFont(new Font("Tahoma", 0, 12));
/* 1369 */     this.lblSclWeight.setText("Weight :");
/*      */     
/* 1371 */     this.chkSclAsyncMode.setFont(new Font("Tahoma", 0, 12));
/* 1372 */     this.chkSclAsyncMode.setText("Async Mode");
/* 1373 */     this.chkSclAsyncMode.setMargin(new Insets(2, -1, 2, 2));
/* 1374 */     this.chkSclAsyncMode.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1376 */             JposSampleApp.this.chkSclAsyncModeActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1380 */     GroupLayout groupLayout10 = new GroupLayout(this.sclPanelScaleWeight);
/* 1381 */     this.sclPanelScaleWeight.setLayout(groupLayout10);
/* 1382 */     groupLayout10.setHorizontalGroup(groupLayout10
/* 1383 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1384 */         .addGroup(groupLayout10.createSequentialGroup()
/* 1385 */           .addContainerGap()
/* 1386 */           .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1387 */             .addComponent(this.lblSclReadWeght)
/* 1388 */             .addComponent(this.lblSclWeight))
/* 1389 */           .addGap(16, 16, 16)
/* 1390 */           .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1391 */             .addComponent(this.txtSclRWTimeout, -2, 71, -2)
/* 1392 */             .addComponent(this.txtSclDisplayWeight, -2, 162, -2))
/* 1393 */           .addGap(30, 30, 30)
/* 1394 */           .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1395 */             .addComponent(this.btnSclClearInput, GroupLayout.Alignment.TRAILING, -2, 164, -2)
/* 1396 */             .addComponent(this.btnSclZeroScale, GroupLayout.Alignment.TRAILING, -2, 164, -2)
/* 1397 */             .addComponent(this.btnSclReadWeight, GroupLayout.Alignment.TRAILING, -2, 164, -2)
/* 1398 */             .addComponent(this.chkSclAsyncMode, -2, 105, -2))
/* 1399 */           .addGap(10, 10, 10)));
/*      */     
/* 1401 */     groupLayout10.setVerticalGroup(groupLayout10
/* 1402 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1403 */         .addGroup(groupLayout10.createSequentialGroup()
/* 1404 */           .addGap(6, 6, 6)
/* 1405 */           .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1406 */             .addGroup(groupLayout10.createSequentialGroup()
/* 1407 */               .addGap(29, 29, 29)
/* 1408 */               .addComponent(this.btnSclReadWeight, -2, 28, -2)
/* 1409 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, 32767)
/* 1410 */               .addComponent(this.btnSclZeroScale, -2, 28, -2)
/* 1411 */               .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 1412 */               .addComponent(this.btnSclClearInput, -2, 28, -2))
/* 1413 */             .addGroup(groupLayout10.createSequentialGroup()
/* 1414 */               .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1415 */                 .addComponent(this.txtSclRWTimeout, -2, 20, -2)
/* 1416 */                 .addComponent(this.lblSclReadWeght)
/* 1417 */                 .addComponent(this.chkSclAsyncMode))
/* 1418 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1419 */               .addGroup(groupLayout10.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1420 */                 .addComponent(this.txtSclDisplayWeight, -2, -1, -2)
/* 1421 */                 .addComponent(this.lblSclWeight))
/* 1422 */               .addGap(78, 78, 78)))
/* 1423 */           .addContainerGap()));
/*      */ 
/*      */     
/* 1426 */     this.sclPanelLiveWeight.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Live Weight ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1427 */     this.sclPanelLiveWeight.setPreferredSize(new Dimension(481, 73));
/*      */     
/* 1429 */     this.txtSclLiveWeight.setFont(new Font("Tahoma", 0, 12));
/* 1430 */     this.txtSclLiveWeight.setPreferredSize(new Dimension(100, 28));
/*      */     
/* 1432 */     this.chkSclEnableLiveWeight.setFont(new Font("Tahoma", 0, 12));
/* 1433 */     this.chkSclEnableLiveWeight.setText("Enable Live Weight");
/* 1434 */     this.chkSclEnableLiveWeight.setToolTipText("Enable Live Weight (before Device Enables) . This enables Status Notify Events.");
/* 1435 */     this.chkSclEnableLiveWeight.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1437 */             JposSampleApp.this.chkSclEnableLiveWeightActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1441 */     GroupLayout groupLayout11 = new GroupLayout(this.sclPanelLiveWeight);
/* 1442 */     this.sclPanelLiveWeight.setLayout(groupLayout11);
/* 1443 */     groupLayout11.setHorizontalGroup(groupLayout11
/* 1444 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1445 */         .addGroup(groupLayout11.createSequentialGroup()
/* 1446 */           .addContainerGap()
/* 1447 */           .addComponent(this.txtSclLiveWeight, -2, 262, -2)
/* 1448 */           .addGap(29, 29, 29)
/* 1449 */           .addComponent(this.chkSclEnableLiveWeight, -2, 155, -2)
/* 1450 */           .addContainerGap(21, 32767)));
/*      */     
/* 1452 */     groupLayout11.setVerticalGroup(groupLayout11
/* 1453 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1454 */         .addGroup(groupLayout11.createSequentialGroup()
/* 1455 */           .addContainerGap()
/* 1456 */           .addGroup(groupLayout11.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1457 */             .addComponent(this.txtSclLiveWeight, -2, -1, -2)
/* 1458 */             .addComponent(this.chkSclEnableLiveWeight))
/* 1459 */           .addContainerGap(12, 32767)));
/*      */ 
/*      */     
/* 1462 */     this.sclPanelCheckHealth.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Check Health ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1463 */     this.sclPanelCheckHealth.setPreferredSize(new Dimension(480, 98));
/*      */     
/* 1465 */     this.checkHealthBtnGroup.add(this.sclInternal);
/* 1466 */     this.sclInternal.setFont(new Font("Tahoma", 0, 12));
/* 1467 */     this.sclInternal.setText("Internal");
/* 1468 */     this.sclInternal.setMargin(new Insets(2, -1, 2, 2));
/* 1469 */     this.sclInternal.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1471 */             JposSampleApp.this.sclInternalActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1475 */     this.checkHealthBtnGroup.add(this.sclExternal);
/* 1476 */     this.sclExternal.setFont(new Font("Tahoma", 0, 12));
/* 1477 */     this.sclExternal.setText("External");
/* 1478 */     this.sclExternal.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1480 */             JposSampleApp.this.sclExternalActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1484 */     this.checkHealthBtnGroup.add(this.sclInteractive);
/* 1485 */     this.sclInteractive.setFont(new Font("Tahoma", 0, 12));
/* 1486 */     this.sclInteractive.setText("Interactive");
/* 1487 */     this.sclInteractive.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1489 */             JposSampleApp.this.sclInteractiveActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1493 */     this.btnSclCheckHealth.setFont(new Font("Tahoma", 0, 12));
/* 1494 */     this.btnSclCheckHealth.setText("Check Health");
/* 1495 */     this.btnSclCheckHealth.setPreferredSize(new Dimension(100, 30));
/* 1496 */     this.btnSclCheckHealth.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1498 */             JposSampleApp.this.btnSclCheckHealthActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1502 */     this.txtSclCheckHealthText.setFont(new Font("Tahoma", 0, 12));
/* 1503 */     this.txtSclCheckHealthText.setPreferredSize(new Dimension(100, 30));
/*      */     
/* 1505 */     this.btnSclCHText.setFont(new Font("Tahoma", 0, 12));
/* 1506 */     this.btnSclCHText.setText("Health Check Text");
/* 1507 */     this.btnSclCHText.setAlignmentX(0.5F);
/* 1508 */     this.btnSclCHText.setPreferredSize(new Dimension(100, 30));
/* 1509 */     this.btnSclCHText.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1511 */             JposSampleApp.this.btnSclCHTextActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1515 */     this.lblSclCHText.setFont(new Font("Tahoma", 0, 12));
/* 1516 */     this.lblSclCHText.setText("HealthCheck Text  ");
/*      */     
/* 1518 */     GroupLayout groupLayout12 = new GroupLayout(this.sclPanelCheckHealth);
/* 1519 */     this.sclPanelCheckHealth.setLayout(groupLayout12);
/* 1520 */     groupLayout12.setHorizontalGroup(groupLayout12
/* 1521 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1522 */         .addGroup(groupLayout12.createSequentialGroup()
/* 1523 */           .addGap(10, 10, 10)
/* 1524 */           .addGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1525 */             .addGroup(groupLayout12.createSequentialGroup()
/* 1526 */               .addComponent(this.sclInternal)
/* 1527 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1528 */               .addComponent(this.sclExternal)
/* 1529 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1530 */               .addComponent(this.sclInteractive))
/* 1531 */             .addGroup(groupLayout12.createSequentialGroup()
/* 1532 */               .addComponent(this.btnSclCHText, -2, 156, -2)
/* 1533 */               .addGap(16, 16, 16)
/* 1534 */               .addComponent(this.lblSclCHText)))
/* 1535 */           .addGap(0, 0, 0)
/* 1536 */           .addGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1537 */             .addComponent(this.btnSclCheckHealth, -1, -1, 32767)
/* 1538 */             .addComponent(this.txtSclCheckHealthText, -2, 182, -2))
/* 1539 */           .addGap(10, 10, 10)));
/*      */     
/* 1541 */     groupLayout12.setVerticalGroup(groupLayout12
/* 1542 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1543 */         .addGroup(GroupLayout.Alignment.TRAILING, groupLayout12.createSequentialGroup()
/* 1544 */           .addGap(1, 1, 1)
/* 1545 */           .addGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1546 */             .addComponent(this.sclInternal)
/* 1547 */             .addComponent(this.sclExternal)
/* 1548 */             .addComponent(this.sclInteractive)
/* 1549 */             .addComponent(this.btnSclCheckHealth, -2, 28, -2))
/* 1550 */           .addGap(22, 22, 22)
/* 1551 */           .addGroup(groupLayout12.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1552 */             .addComponent(this.txtSclCheckHealthText, -2, 28, -2)
/* 1553 */             .addComponent(this.btnSclCHText, -2, 28, -2)
/* 1554 */             .addComponent(this.lblSclCHText))
/* 1555 */           .addGap(6, 6, 6)));
/*      */ 
/*      */     
/* 1558 */     this.sclPanelDirectIO.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Direct IO ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1559 */     this.sclPanelDirectIO.setPreferredSize(new Dimension(485, 359));
/*      */     
/* 1561 */     this.lblSclCommand.setFont(new Font("Tahoma", 0, 12));
/* 1562 */     this.lblSclCommand.setText("Command ");
/*      */     
/* 1564 */     this.cmbSclCommand.setFont(new Font("Tahoma", 0, 12));
/* 1565 */     this.cmbSclCommand.setAutoscrolls(true);
/* 1566 */     this.cmbSclCommand.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
/* 1567 */     this.cmbSclCommand.setPreferredSize(new Dimension(153, 28));
/* 1568 */     this.cmbSclCommand.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1570 */             JposSampleApp.this.cmbSclCommandActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1574 */     this.btnSclExecute.setFont(new Font("Tahoma", 0, 12));
/* 1575 */     this.btnSclExecute.setText("Execute");
/* 1576 */     this.btnSclExecute.setPreferredSize(new Dimension(100, 30));
/* 1577 */     this.btnSclExecute.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1579 */             JposSampleApp.this.btnSclExecuteActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1583 */     this.lblSclInXml.setFont(new Font("Tahoma", 0, 12));
/* 1584 */     this.lblSclInXml.setText("InXml :");
/*      */     
/* 1586 */     this.lblSclOutXml.setFont(new Font("Tahoma", 0, 12));
/* 1587 */     this.lblSclOutXml.setText("OutXml :");
/*      */     
/* 1589 */     this.jScrollPane8.setAutoscrolls(true);
/*      */     
/* 1591 */     this.txtSclInXml.setColumns(100);
/* 1592 */     this.txtSclInXml.setFont(new Font("Monospaced", 0, 12));
/* 1593 */     this.txtSclInXml.setRows(50);
/* 1594 */     this.txtSclInXml.setCursor(new Cursor(0));
/* 1595 */     this.txtSclInXml.setSelectionColor(new Color(153, 204, 255));
/* 1596 */     this.jScrollPane8.setViewportView(this.txtSclInXml);
/*      */     
/* 1598 */     this.jScrollPane9.setAutoscrolls(true);
/*      */     
/* 1600 */     this.txtSclOutXml.setEditable(false);
/* 1601 */     this.txtSclOutXml.setColumns(100);
/* 1602 */     this.txtSclOutXml.setFont(new Font("Monospaced", 0, 12));
/* 1603 */     this.txtSclOutXml.setLineWrap(true);
/* 1604 */     this.txtSclOutXml.setRows(50);
/* 1605 */     this.txtSclOutXml.setWrapStyleWord(true);
/* 1606 */     this.txtSclOutXml.setCursor(new Cursor(0));
/* 1607 */     this.txtSclOutXml.setSelectionColor(new Color(153, 204, 255));
/* 1608 */     this.jScrollPane9.setViewportView(this.txtSclOutXml);
/*      */     
/* 1610 */     this.lblSclStatus.setFont(new Font("Tahoma", 0, 12));
/* 1611 */     this.lblSclStatus.setText("Status ");
/*      */     
/* 1613 */     this.txtSclStatus.setEditable(false);
/* 1614 */     this.txtSclStatus.setFont(new Font("Tahoma", 0, 12));
/* 1615 */     this.txtSclStatus.setPreferredSize(new Dimension(100, 30));
/*      */     
/* 1617 */     this.btnSclClear.setFont(new Font("Tahoma", 0, 12));
/* 1618 */     this.btnSclClear.setText("Clear");
/* 1619 */     this.btnSclClear.setPreferredSize(new Dimension(100, 30));
/* 1620 */     this.btnSclClear.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1622 */             JposSampleApp.this.btnSclClearActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1626 */     this.btnSclCopy.setFont(new Font("Tahoma", 0, 12));
/* 1627 */     this.btnSclCopy.setText("Copy");
/* 1628 */     this.btnSclCopy.setPreferredSize(new Dimension(100, 30));
/* 1629 */     this.btnSclCopy.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1631 */             JposSampleApp.this.btnSclCopyActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1635 */     GroupLayout groupLayout13 = new GroupLayout(this.sclPanelDirectIO);
/* 1636 */     this.sclPanelDirectIO.setLayout(groupLayout13);
/* 1637 */     groupLayout13.setHorizontalGroup(groupLayout13
/* 1638 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1639 */         .addGroup(groupLayout13.createSequentialGroup()
/* 1640 */           .addContainerGap(-1, 32767)
/* 1641 */           .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1642 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1643 */               .addComponent(this.lblSclStatus)
/* 1644 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1645 */               .addComponent(this.txtSclStatus, -2, 81, -2))
/* 1646 */             .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1647 */               .addGroup(GroupLayout.Alignment.TRAILING, groupLayout13.createSequentialGroup()
/* 1648 */                 .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1649 */                   .addComponent(this.lblSclInXml)
/* 1650 */                   .addComponent(this.lblSclCommand))
/* 1651 */                 .addGap(4, 4, 4)
/* 1652 */                 .addComponent(this.cmbSclCommand, -2, 163, -2))
/* 1653 */               .addComponent(this.jScrollPane8, -2, 225, -2)))
/* 1654 */           .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1655 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1656 */               .addGap(10, 10, 10)
/* 1657 */               .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1658 */                 .addGroup(groupLayout13.createSequentialGroup()
/* 1659 */                   .addComponent(this.lblSclOutXml)
/* 1660 */                   .addGap(187, 187, 187))
/* 1661 */                 .addGroup(GroupLayout.Alignment.TRAILING, groupLayout13.createSequentialGroup()
/* 1662 */                   .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1663 */                     .addComponent(this.jScrollPane9, GroupLayout.Alignment.TRAILING, -2, 225, -2)
/* 1664 */                     .addGroup(GroupLayout.Alignment.TRAILING, groupLayout13.createSequentialGroup()
/* 1665 */                       .addComponent(this.btnSclCopy, -2, 87, -2)
/* 1666 */                       .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1667 */                       .addComponent(this.btnSclClear, -2, 87, -2)))
/* 1668 */                   .addGap(10, 10, 10))))
/* 1669 */             .addGroup(GroupLayout.Alignment.TRAILING, groupLayout13.createSequentialGroup()
/* 1670 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1671 */               .addComponent(this.btnSclExecute, -2, 182, -2)
/* 1672 */               .addGap(10, 10, 10)))));
/*      */     
/* 1674 */     groupLayout13.setVerticalGroup(groupLayout13
/* 1675 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1676 */         .addGroup(groupLayout13.createSequentialGroup()
/* 1677 */           .addGap(2, 2, 2)
/* 1678 */           .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1679 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1680 */               .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1681 */                 .addComponent(this.cmbSclCommand, -2, -1, -2)
/* 1682 */                 .addComponent(this.lblSclCommand))
/* 1683 */               .addGap(6, 6, 6)
/* 1684 */               .addComponent(this.lblSclInXml))
/* 1685 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1686 */               .addComponent(this.btnSclExecute, -2, 28, -2)
/* 1687 */               .addGap(6, 6, 6)
/* 1688 */               .addComponent(this.lblSclOutXml)))
/* 1689 */           .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1690 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1691 */               .addComponent(this.jScrollPane8, -2, 179, -2)
/* 1692 */               .addGap(6, 6, 6)
/* 1693 */               .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1694 */                 .addComponent(this.txtSclStatus, -2, 20, -2)
/* 1695 */                 .addComponent(this.lblSclStatus)))
/* 1696 */             .addGroup(groupLayout13.createSequentialGroup()
/* 1697 */               .addComponent(this.jScrollPane9, -2, 179, -2)
/* 1698 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1699 */               .addGroup(groupLayout13.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1700 */                 .addComponent(this.btnSclClear, -2, 28, -2)
/* 1701 */                 .addComponent(this.btnSclCopy, -2, 28, -2))))
/* 1702 */           .addGap(6, 6, 6)));
/*      */ 
/*      */     
/* 1705 */     this.sclPanelStat.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Statistics ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1706 */     this.sclPanelStat.setPreferredSize(new Dimension(503, 69));
/*      */     
/* 1708 */     this.lblSclStatistic.setFont(new Font("Tahoma", 0, 12));
/*      */     
/* 1710 */     this.btnSclResetStat.setFont(new Font("Tahoma", 0, 12));
/* 1711 */     this.btnSclResetStat.setText("Reset Statistics");
/* 1712 */     this.btnSclResetStat.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1714 */             JposSampleApp.this.btnSclResetStatActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1718 */     this.btnSclRetreiveStat.setFont(new Font("Tahoma", 0, 12));
/* 1719 */     this.btnSclRetreiveStat.setText("Retrieve Statistics");
/* 1720 */     this.btnSclRetreiveStat.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1722 */             JposSampleApp.this.btnSclRetreiveStatActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1726 */     this.txtSclStatOutput.setColumns(4);
/* 1727 */     this.txtSclStatOutput.setFont(new Font("Monospaced", 0, 12));
/* 1728 */     this.txtSclStatOutput.setRows(5);
/* 1729 */     this.jScrollPane10.setViewportView(this.txtSclStatOutput);
/*      */     
/* 1731 */     GroupLayout groupLayout14 = new GroupLayout(this.sclPanelStat);
/* 1732 */     this.sclPanelStat.setLayout(groupLayout14);
/* 1733 */     groupLayout14.setHorizontalGroup(groupLayout14
/* 1734 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1735 */         .addGroup(groupLayout14.createSequentialGroup()
/* 1736 */           .addContainerGap()
/* 1737 */           .addGroup(groupLayout14.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1738 */             .addComponent(this.jScrollPane10, -2, 458, -2)
/* 1739 */             .addGroup(groupLayout14.createSequentialGroup()
/* 1740 */               .addComponent(this.lblSclStatistic, -2, 81, -2)
/* 1741 */               .addGap(18, 18, 18)
/* 1742 */               .addComponent(this.btnSclRetreiveStat, -2, 164, -2)
/* 1743 */               .addGap(31, 31, 31)
/* 1744 */               .addComponent(this.btnSclResetStat, -2, 164, -2)))
/* 1745 */           .addContainerGap(-1, 32767)));
/*      */     
/* 1747 */     groupLayout14.setVerticalGroup(groupLayout14
/* 1748 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1749 */         .addGroup(groupLayout14.createSequentialGroup()
/* 1750 */           .addGap(4, 4, 4)
/* 1751 */           .addGroup(groupLayout14.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1752 */             .addComponent(this.lblSclStatistic, -2, 20, -2)
/* 1753 */             .addComponent(this.btnSclResetStat, -2, 28, -2)
/* 1754 */             .addComponent(this.btnSclRetreiveStat, -2, 28, -2))
/* 1755 */           .addGap(7, 7, 7)
/* 1756 */           .addComponent(this.jScrollPane10, -2, 67, -2)
/* 1757 */           .addGap(10, 10, 10)));
/*      */ 
/*      */     
/* 1760 */     this.sclPanelProperties.setBorder(BorderFactory.createTitledBorder(BorderFactory.createTitledBorder(""), "Properties ", 0, 0, new Font("Tahoma", 1, 12)));
/* 1761 */     this.sclPanelProperties.setPreferredSize(new Dimension(480, 112));
/*      */     
/* 1763 */     this.cmbSclProperties.setFont(new Font("Tahoma", 0, 12));
/* 1764 */     this.cmbSclProperties.setPreferredSize(new Dimension(100, 28));
/* 1765 */     this.cmbSclProperties.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1767 */             JposSampleApp.this.cmbSclPropertiesActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1771 */     this.lblSclPropertyValue.setFont(new Font("Tahoma", 0, 12));
/* 1772 */     this.lblSclPropertyValue.setText("Property Value ");
/*      */     
/* 1774 */     this.txtSclPropertyValue.setFont(new Font("Tahoma", 0, 12));
/* 1775 */     this.txtSclPropertyValue.setHorizontalAlignment(0);
/* 1776 */     this.txtSclPropertyValue.setPreferredSize(new Dimension(100, 28));
/* 1777 */     this.txtSclPropertyValue.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1779 */             JposSampleApp.this.txtSclPropertyValueActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1783 */     this.btnSclProperties.setFont(new Font("Tahoma", 0, 12));
/* 1784 */     this.btnSclProperties.setText("Set Properties");
/* 1785 */     this.btnSclProperties.setPreferredSize(new Dimension(100, 28));
/* 1786 */     this.btnSclProperties.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1788 */             JposSampleApp.this.btnSclPropertiesActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1792 */     GroupLayout groupLayout15 = new GroupLayout(this.sclPanelProperties);
/* 1793 */     this.sclPanelProperties.setLayout(groupLayout15);
/* 1794 */     groupLayout15.setHorizontalGroup(groupLayout15
/* 1795 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1796 */         .addGroup(groupLayout15.createSequentialGroup()
/* 1797 */           .addGap(10, 10, 10)
/* 1798 */           .addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1799 */             .addGroup(groupLayout15.createSequentialGroup()
/* 1800 */               .addComponent(this.lblSclPropertyValue)
/* 1801 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1802 */               .addComponent(this.txtSclPropertyValue, -1, -1, 32767))
/* 1803 */             .addComponent(this.cmbSclProperties, -2, 224, -2))
/* 1804 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1805 */           .addComponent(this.btnSclProperties, -2, 182, -2)
/* 1806 */           .addGap(10, 10, 10)));
/*      */     
/* 1808 */     groupLayout15.setVerticalGroup(groupLayout15
/* 1809 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1810 */         .addGroup(GroupLayout.Alignment.TRAILING, groupLayout15.createSequentialGroup()
/* 1811 */           .addGap(0, 0, 0)
/* 1812 */           .addComponent(this.cmbSclProperties, -2, -1, -2)
/* 1813 */           .addGap(6, 6, 6)
/* 1814 */           .addGroup(groupLayout15.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 1815 */             .addComponent(this.txtSclPropertyValue, -2, -1, -2)
/* 1816 */             .addComponent(this.lblSclPropertyValue)
/* 1817 */             .addComponent(this.btnSclProperties, -2, 28, -2))));
/*      */ 
/*      */     
/* 1820 */     GroupLayout groupLayout16 = new GroupLayout(this.scalePanel);
/* 1821 */     this.scalePanel.setLayout(groupLayout16);
/* 1822 */     groupLayout16.setHorizontalGroup(groupLayout16
/* 1823 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1824 */         .addGroup(groupLayout16.createSequentialGroup()
/* 1825 */           .addGap(6, 6, 6)
/* 1826 */           .addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
/* 1827 */             .addComponent(this.sclPanelLiveWeight, GroupLayout.Alignment.LEADING, -2, 485, -2)
/* 1828 */             .addComponent(this.sclPanelDataEvent, GroupLayout.Alignment.LEADING, -2, 485, -2)
/* 1829 */             .addComponent(this.sclPanelScaleWeight, GroupLayout.Alignment.LEADING, -2, 485, -2)
/* 1830 */             .addComponent(this.sclPanelStat, -2, 485, -2))
/* 1831 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1832 */           .addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1833 */             .addComponent(this.sclPanelDirectIO, -1, 488, 32767)
/* 1834 */             .addComponent(this.sclPanelCheckHealth, -1, 488, 32767)
/* 1835 */             .addComponent(this.sclPanelProperties, -1, 488, 32767))
/* 1836 */           .addGap(46, 46, 46)));
/*      */     
/* 1838 */     groupLayout16.setVerticalGroup(groupLayout16
/* 1839 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1840 */         .addGroup(groupLayout16.createSequentialGroup()
/* 1841 */           .addGap(6, 6, 6)
/* 1842 */           .addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1843 */             .addComponent(this.sclPanelDataEvent, -2, 108, -2)
/* 1844 */             .addComponent(this.sclPanelCheckHealth, -2, 108, -2))
/* 1845 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1846 */           .addGroup(groupLayout16.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 1847 */             .addGroup(groupLayout16.createSequentialGroup()
/* 1848 */               .addComponent(this.sclPanelProperties, -2, 95, -2)
/* 1849 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
/* 1850 */               .addComponent(this.sclPanelDirectIO, -2, 291, -2))
/* 1851 */             .addGroup(groupLayout16.createSequentialGroup()
/* 1852 */               .addComponent(this.sclPanelScaleWeight, -2, 177, -2)
/* 1853 */               .addGap(3, 3, 3)
/* 1854 */               .addComponent(this.sclPanelLiveWeight, -2, -1, -2)
/* 1855 */               .addGap(6, 6, 6)
/* 1856 */               .addComponent(this.sclPanelStat, -2, 133, -2)))
/* 1857 */           .addGap(4, 4, 4)));
/*      */ 
/*      */     
/* 1860 */     this.jTabbedPane.addTab("", this.scalePanel);
/*      */     
/* 1862 */     this.scnPanelCommonMethods.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
/* 1863 */     this.scnPanelCommonMethods.setPreferredSize(new Dimension(100, 30));
/*      */     
/* 1865 */     this.lblDeviceType.setFont(new Font("Tahoma", 0, 12));
/* 1866 */     this.lblDeviceType.setText("Device Type :");
/*      */     
/* 1868 */     this.cmbDeviceCategory.setFont(new Font("Tahoma", 1, 12));
/* 1869 */     this.cmbDeviceCategory.setModel(new DefaultComboBoxModel<>(new String[] { "Scanner", "Scale" }));
/* 1870 */     this.cmbDeviceCategory.setPreferredSize(new Dimension(153, 28));
/* 1871 */     this.cmbDeviceCategory.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1873 */             JposSampleApp.this.cmbDeviceCategoryActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1877 */     this.lblLogicalName.setFont(new Font("Tahoma", 0, 12));
/* 1878 */     this.lblLogicalName.setText("Logical Device Name :");
/*      */     
/* 1880 */     this.cmbLogicalDevice.setEditable(true);
/* 1881 */     this.cmbLogicalDevice.setFont(new Font("Tahoma", 0, 12));
/* 1882 */     this.cmbLogicalDevice.setPreferredSize(new Dimension(153, 28));
/* 1883 */     this.cmbLogicalDevice.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1885 */             JposSampleApp.this.cmbLogicalDeviceActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1889 */     this.btnOpen.setBackground(UIManager.getDefaults().getColor("Button.light"));
/* 1890 */     this.btnOpen.setFont(new Font("Tahoma", 0, 12));
/* 1891 */     this.btnOpen.setText("Open");
/* 1892 */     this.btnOpen.setActionCommand("open");
/* 1893 */     this.btnOpen.setAlignmentX(0.5F);
/* 1894 */     this.btnOpen.setHorizontalTextPosition(0);
/* 1895 */     this.btnOpen.setMaximumSize(new Dimension(81, 25));
/* 1896 */     this.btnOpen.setMinimumSize(new Dimension(81, 25));
/* 1897 */     this.btnOpen.setPreferredSize(new Dimension(120, 30));
/* 1898 */     this.btnOpen.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1900 */             JposSampleApp.this.btnOpenActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1904 */     this.lblClaimTimeout.setFont(new Font("Tahoma", 0, 12));
/* 1905 */     this.lblClaimTimeout.setText("Claim Timeout (ms) :");
/* 1906 */     this.lblClaimTimeout.setHorizontalTextPosition(0);
/*      */     
/* 1908 */     this.txtClaimTimeout.setFont(new Font("Tahoma", 0, 12));
/* 1909 */     this.txtClaimTimeout.setHorizontalAlignment(0);
/* 1910 */     this.txtClaimTimeout.setText("1000");
/* 1911 */     this.txtClaimTimeout.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1913 */             JposSampleApp.this.txtClaimTimeoutActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1917 */     this.btnClaim.setBackground(UIManager.getDefaults().getColor("Button.light"));
/* 1918 */     this.btnClaim.setFont(new Font("Tahoma", 0, 12));
/* 1919 */     this.btnClaim.setText("Claim");
/* 1920 */     this.btnClaim.setActionCommand("");
/* 1921 */     this.btnClaim.setHorizontalTextPosition(0);
/* 1922 */     this.btnClaim.setMaximumSize(new Dimension(81, 25));
/* 1923 */     this.btnClaim.setMinimumSize(new Dimension(81, 25));
/* 1924 */     this.btnClaim.setPreferredSize(new Dimension(120, 30));
/* 1925 */     this.btnClaim.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1927 */             JposSampleApp.this.btnClaimActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1931 */     this.btnRelease.setBackground(UIManager.getDefaults().getColor("Button.light"));
/* 1932 */     this.btnRelease.setFont(new Font("Tahoma", 0, 12));
/* 1933 */     this.btnRelease.setText("Release");
/* 1934 */     this.btnRelease.setActionCommand("");
/* 1935 */     this.btnRelease.setHorizontalTextPosition(0);
/* 1936 */     this.btnRelease.setMaximumSize(new Dimension(81, 25));
/* 1937 */     this.btnRelease.setMinimumSize(new Dimension(81, 25));
/* 1938 */     this.btnRelease.setPreferredSize(new Dimension(120, 30));
/* 1939 */     this.btnRelease.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1941 */             JposSampleApp.this.btnReleaseActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1945 */     this.btnClose.setBackground(UIManager.getDefaults().getColor("Button.light"));
/* 1946 */     this.btnClose.setFont(new Font("Tahoma", 0, 12));
/* 1947 */     this.btnClose.setText("Close");
/* 1948 */     this.btnClose.setActionCommand("");
/* 1949 */     this.btnClose.setHorizontalTextPosition(0);
/* 1950 */     this.btnClose.setMaximumSize(new Dimension(81, 25));
/* 1951 */     this.btnClose.setMinimumSize(new Dimension(81, 25));
/* 1952 */     this.btnClose.setPreferredSize(new Dimension(120, 30));
/* 1953 */     this.btnClose.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1955 */             JposSampleApp.this.btnCloseActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1959 */     this.btnFastMode.setFont(new Font("Tahoma", 0, 12));
/* 1960 */     this.btnFastMode.setText("Fast Mode");
/* 1961 */     this.btnFastMode.setPreferredSize(new Dimension(120, 30));
/* 1962 */     this.btnFastMode.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 1964 */             JposSampleApp.this.btnFastModeActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 1968 */     this.lblDeviceInfo.setFont(new Font("Tahoma", 0, 12));
/* 1969 */     this.lblDeviceInfo.setText("Device Information :");
/*      */     
/* 1971 */     this.txtDeviceInfo.setColumns(20);
/* 1972 */     this.txtDeviceInfo.setFont(new Font("Monospaced", 0, 12));
/* 1973 */     this.txtDeviceInfo.setLineWrap(true);
/* 1974 */     this.txtDeviceInfo.setTabSize(0);
/* 1975 */     this.txtDeviceInfo.setWrapStyleWord(true);
/* 1976 */     this.jScrollPane5.setViewportView(this.txtDeviceInfo);
/*      */     
/* 1978 */     GroupLayout groupLayout17 = new GroupLayout(this.scnPanelCommonMethods);
/* 1979 */     this.scnPanelCommonMethods.setLayout(groupLayout17);
/* 1980 */     groupLayout17.setHorizontalGroup(groupLayout17
/* 1981 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1982 */         .addGroup(groupLayout17.createSequentialGroup()
/* 1983 */           .addGap(7, 7, 7)
/* 1984 */           .addGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1985 */             .addGroup(groupLayout17.createSequentialGroup()
/* 1986 */               .addGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 1987 */                 .addComponent(this.jScrollPane5)
/* 1988 */                 .addComponent(this.cmbDeviceCategory, 0, -1, 32767)
/* 1989 */                 .addComponent(this.cmbLogicalDevice, 0, -1, 32767)
/* 1990 */                 .addComponent(this.btnOpen, -1, -1, 32767)
/* 1991 */                 .addComponent(this.btnClaim, -1, -1, 32767)
/* 1992 */                 .addComponent(this.btnRelease, -1, -1, 32767)
/* 1993 */                 .addComponent(this.btnClose, -1, -1, 32767)
/* 1994 */                 .addComponent(this.btnFastMode, -1, -1, 32767)
/* 1995 */                 .addComponent(this.lblDeviceType)
/* 1996 */                 .addGroup(groupLayout17.createSequentialGroup()
/* 1997 */                   .addComponent(this.lblClaimTimeout, -1, 135, 32767)
/* 1998 */                   .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 1999 */                   .addComponent(this.txtClaimTimeout, -2, 71, -2)))
/* 2000 */               .addGap(7, 7, 7))
/* 2001 */             .addGroup(groupLayout17.createSequentialGroup()
/* 2002 */               .addGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2003 */                 .addComponent(this.lblLogicalName)
/* 2004 */                 .addComponent(this.lblDeviceInfo))
/* 2005 */               .addGap(0, 0, 32767)))));
/*      */     
/* 2007 */     groupLayout17.setVerticalGroup(groupLayout17
/* 2008 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2009 */         .addGroup(GroupLayout.Alignment.TRAILING, groupLayout17.createSequentialGroup()
/* 2010 */           .addGap(4, 4, 4)
/* 2011 */           .addComponent(this.lblDeviceType)
/* 2012 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2013 */           .addComponent(this.cmbDeviceCategory, -2, -1, -2)
/* 2014 */           .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
/* 2015 */           .addComponent(this.lblLogicalName)
/* 2016 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2017 */           .addComponent(this.cmbLogicalDevice, -2, -1, -2)
/* 2018 */           .addGap(30, 30, 30)
/* 2019 */           .addComponent(this.btnOpen, -2, 30, -2)
/* 2020 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2021 */           .addGroup(groupLayout17.createParallelGroup(GroupLayout.Alignment.BASELINE)
/* 2022 */             .addComponent(this.txtClaimTimeout, -2, 20, -2)
/* 2023 */             .addComponent(this.lblClaimTimeout))
/* 2024 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2025 */           .addComponent(this.btnClaim, -2, -1, -2)
/* 2026 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2027 */           .addComponent(this.btnRelease, -2, -1, -2)
/* 2028 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2029 */           .addComponent(this.btnClose, -2, -1, -2)
/* 2030 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2031 */           .addComponent(this.btnFastMode, -2, -1, -2)
/* 2032 */           .addGap(30, 30, 30)
/* 2033 */           .addComponent(this.lblDeviceInfo)
/* 2034 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2035 */           .addComponent(this.jScrollPane5, -2, 341, -2)
/* 2036 */           .addContainerGap(-1, 32767)));
/*      */ 
/*      */     
/* 2039 */     this.panelLogView.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Log View :", 0, 0, new Font("Tahoma", 0, 12)));
/*      */     
/* 2041 */     this.jScrollPane7.setAutoscrolls(true);
/*      */     
/* 2043 */     this.txtLogView.setColumns(20);
/* 2044 */     this.txtLogView.setFont(new Font("Monospaced", 0, 12));
/* 2045 */     this.txtLogView.setRows(5);
/* 2046 */     this.jScrollPane7.setViewportView(this.txtLogView);
/*      */     
/* 2048 */     this.btnLogClear.setFont(new Font("Tahoma", 0, 12));
/* 2049 */     this.btnLogClear.setText("Clear");
/* 2050 */     this.btnLogClear.setPreferredSize(new Dimension(100, 30));
/* 2051 */     this.btnLogClear.addActionListener(new ActionListener() {
/*      */           public void actionPerformed(ActionEvent param1ActionEvent) {
/* 2053 */             JposSampleApp.this.btnLogClearActionPerformed(param1ActionEvent);
/*      */           }
/*      */         });
/*      */     
/* 2057 */     GroupLayout groupLayout18 = new GroupLayout(this.panelLogView);
/* 2058 */     this.panelLogView.setLayout(groupLayout18);
/* 2059 */     groupLayout18.setHorizontalGroup(groupLayout18
/* 2060 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2061 */         .addGroup(groupLayout18.createSequentialGroup()
/* 2062 */           .addGroup(groupLayout18.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2063 */             .addGroup(groupLayout18.createSequentialGroup()
/* 2064 */               .addGap(12, 12, 12)
/* 2065 */               .addComponent(this.jScrollPane7, -1, 961, 32767))
/* 2066 */             .addGroup(GroupLayout.Alignment.TRAILING, groupLayout18.createSequentialGroup()
/* 2067 */               .addGap(70, 70, 70)
/* 2068 */               .addComponent(this.btnLogClear, -2, 87, -2)))
/* 2069 */           .addContainerGap()));
/*      */     
/* 2071 */     groupLayout18.setVerticalGroup(groupLayout18
/* 2072 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2073 */         .addGroup(groupLayout18.createSequentialGroup()
/* 2074 */           .addComponent(this.jScrollPane7, -1, 169, 32767)
/* 2075 */           .addGap(3, 3, 3)
/* 2076 */           .addComponent(this.btnLogClear, -2, 27, -2)));
/*      */ 
/*      */     
/* 2079 */     GroupLayout groupLayout19 = new GroupLayout(this.jPanel2);
/* 2080 */     this.jPanel2.setLayout(groupLayout19);
/* 2081 */     groupLayout19.setHorizontalGroup(groupLayout19
/* 2082 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2083 */         .addComponent(this.jPanel1, -1, -1, 32767)
/* 2084 */         .addGroup(groupLayout19.createSequentialGroup()
/* 2085 */           .addComponent(this.scnPanelCommonMethods, -2, 228, -2)
/* 2086 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2087 */           .addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING, false)
/* 2088 */             .addComponent(this.jTabbedPane, -2, 997, -2)
/* 2089 */             .addComponent(this.panelLogView, -2, -1, -2))));
/*      */     
/* 2091 */     groupLayout19.setVerticalGroup(groupLayout19
/* 2092 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2093 */         .addGroup(groupLayout19.createSequentialGroup()
/* 2094 */           .addComponent(this.jPanel1, -2, -1, -2)
/* 2095 */           .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2096 */           .addGroup(groupLayout19.createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2097 */             .addGroup(groupLayout19.createSequentialGroup()
/* 2098 */               .addComponent(this.jTabbedPane, -2, -1, -2)
/* 2099 */               .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
/* 2100 */               .addComponent(this.panelLogView, -1, -1, 32767))
/* 2101 */             .addComponent(this.scnPanelCommonMethods, -1, 781, 32767))));
/*      */ 
/*      */     
/* 2104 */     this.jScrollPane11.setViewportView(this.jPanel2);
/*      */     
/* 2106 */     GroupLayout groupLayout20 = new GroupLayout(getContentPane());
/* 2107 */     getContentPane().setLayout(groupLayout20);
/* 2108 */     groupLayout20.setHorizontalGroup(groupLayout20
/* 2109 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2110 */         .addComponent(this.jScrollPane11));
/*      */     
/* 2112 */     groupLayout20.setVerticalGroup(groupLayout20
/* 2113 */         .createParallelGroup(GroupLayout.Alignment.LEADING)
/* 2114 */         .addComponent(this.jScrollPane11));
/*      */ 
/*      */     
/* 2117 */     pack();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void commonMethods(String paramString) {
/* 2123 */     logicalName = (String)this.cmbLogicalDevice.getSelectedItem();
/* 2124 */     DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/*      */     
/* 2126 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/* 2127 */     Date date = new Date();
/* 2128 */     String str = simpleDateFormat.format(date);
/*      */ 
/*      */     
/* 2131 */     switch (paramString) {
/*      */       case "open":
/* 2133 */         this.status = this.intermediateLayer.openAction(deviceTypeBinder);
/*      */ 
/*      */         
/* 2136 */         if (this.status[1] == null) {
/* 2137 */           this.info = this.intermediateLayer.getDeviceInfo(deviceTypeBinder);
/*      */         }
/* 2139 */         this.txtDeviceInfo.setText(this.info);
/*      */         break;
/*      */       case "claim":
/* 2142 */         this.status = this.intermediateLayer.claimAction(deviceTypeBinder, this.claimTimeout);
/*      */         break;
/*      */       case "release":
/* 2145 */         this.status = this.intermediateLayer.releaseAction(deviceTypeBinder);
/*      */         break;
/*      */       case "close":
/* 2148 */         this.status = this.intermediateLayer.closeAction(deviceTypeBinder);
/*      */         break;
/*      */       case "enableDevice":
/* 2151 */         this.status = this.intermediateLayer.deviceEnableAction(deviceTypeBinder, this.deviceEnabled);
/*      */         break;
/*      */       case "enableDataEvent":
/* 2154 */         this.status = this.intermediateLayer.dataEventEnableAction(deviceTypeBinder, this.dataEventEnabled);
/*      */         break;
/*      */       case "autoDisable":
/* 2157 */         this.status = this.intermediateLayer.autoDisable(deviceTypeBinder, this.autoDisable);
/*      */         break;
/*      */       case "freezeEvents":
/* 2160 */         this.status = this.intermediateLayer.freezeEventsAction(deviceTypeBinder, this.freezeEventsEnabled);
/*      */         break;
/*      */       case "healthCheck":
/* 2163 */         this.status = this.intermediateLayer.checkHealthAction(deviceTypeBinder, this.healthCheckType);
/*      */         break;
/*      */       case "healthCheckText":
/* 2166 */         this.status = this.intermediateLayer.checkHealthTextAction(deviceTypeBinder);
/*      */         break;
/*      */       case "directInputOutput":
/* 2169 */         if (this.opCode == 507 || this.opCode == 508 || this.opCode == 605) {
/*      */           
/* 2171 */           this.status = this.intermediateLayer.ncrDirectIOAction(deviceTypeBinder, this.opCode, this.statusScanner, this.deviceParams); break;
/* 2172 */         }  if (this.opCode == 501) {
/*      */           
/*      */           try {
/* 2175 */             this.statusScanner[0] = Integer.parseInt(this.txtScnInxml.getText());
/* 2176 */             this.status = this.intermediateLayer.ncrDirectIOAction(deviceTypeBinder, this.opCode, this.statusScanner, this.deviceParams);
/* 2177 */             this.statusScanner[0] = 0;
/* 2178 */           } catch (Exception exception) {
/* 2179 */             this.statusScanner[0] = -1;
/* 2180 */             this.status[0] = "Invalid NCR Tone Type";
/* 2181 */             this.status[1] = this.intermediateLayer.exceptionDialog(new JposException(0), "Invalid NCR Tone Type ");
/*      */           }  break;
/* 2183 */         }  if (this.opCode == 604) {
/*      */           
/* 2185 */           this.status = this.intermediateLayer.ncrDirectIOLiveWeightAction(deviceTypeBinder, this.opCode, this.statusScanner, this.deviceParams);
/*      */           
/* 2187 */           this.deviceParams.append(this.statusScanner[0]);
/*      */           
/* 2189 */           if (this.status[0].equals("Direct IO Successful")) {
/*      */             
/* 2191 */             this.statusScanner[0] = 0; break;
/*      */           } 
/* 2193 */           this.statusScanner[0] = -1;
/*      */           break;
/*      */         } 
/* 2196 */         this.status = this.intermediateLayer.directIOAction(deviceTypeBinder, this.opCode, this.statusScanner, this.deviceParams);
/*      */         break;
/*      */       
/*      */       case "clearInput":
/* 2200 */         this.status = this.intermediateLayer.clearInputAction(deviceTypeBinder);
/*      */         break;
/*      */       case "errorOccured":
/* 2203 */         this.status = this.errorStatus;
/*      */         break;
/*      */       case "setProperty":
/* 2206 */         this.status = this.setPropertyStatus;
/*      */         break;
/*      */       case "retrieveStatistics":
/* 2209 */         this.status = this.intermediateLayer.retrieveStatistics(deviceTypeBinder, this.retrieveStatValue);
/*      */         break;
/*      */       case "resetStatistics":
/* 2212 */         this.status = this.intermediateLayer.resetStatistics(deviceTypeBinder, this.resetStatValue);
/*      */         break;
/*      */       case "powerNotify":
/* 2215 */         this.status = this.intermediateLayer.PowerNotify(deviceTypeBinder, this.powerNotifyEnabled);
/*      */         break;
/*      */       case "powerState":
/* 2218 */         this.status = this.intermediateLayer.PowerState(deviceTypeBinder);
/*      */         break;
/*      */     } 
/* 2221 */     this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + deviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[0]);
/* 2222 */     if (this.status[1] != null) {
/* 2223 */       this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + deviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[1]);
/*      */     }
/*      */   }
/*      */   
/*      */   private void scannerMethods(String paramString) {
/* 2228 */     logicalName = (String)this.cmbLogicalDevice.getSelectedItem();
/* 2229 */     ScannerDeviceTypeBinder scannerDeviceTypeBinder = (ScannerDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/*      */     
/* 2231 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/* 2232 */     Date date = new Date();
/* 2233 */     String str = simpleDateFormat.format(date);
/*      */     
/* 2235 */     switch (paramString) {
/*      */       
/*      */       case "fastModeScanner":
/* 2238 */         this.status = this.intermediateLayer.fastModeScannerAction(scannerDeviceTypeBinder);
/* 2239 */         this.info = this.intermediateLayer.getDeviceInfo(scannerDeviceTypeBinder);
/* 2240 */         this.txtDeviceInfo.setText(this.info);
/*      */         break;
/*      */       
/*      */       case "decodeData":
/* 2244 */         this.status = this.intermediateLayer.decodeDataAction(scannerDeviceTypeBinder, this.decodeDataEnabled);
/*      */         break;
/*      */       
/*      */       case "clearInputProperties":
/* 2248 */         this.status = this.intermediateLayer.clearInputPropertiesAction(scannerDeviceTypeBinder);
/*      */         break;
/*      */     } 
/* 2251 */     this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + scannerDeviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[0]);
/* 2252 */     if (this.status[1] != null) {
/* 2253 */       this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + scannerDeviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[1]);
/*      */     }
/*      */   }
/*      */   
/*      */   private void scaleMethods(String paramString) {
/* 2258 */     logicalName = (String)this.cmbLogicalDevice.getSelectedItem();
/* 2259 */     ScaleDeviceTypeBinder scaleDeviceTypeBinder = (ScaleDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/*      */     
/* 2261 */     SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yy: HH:mm:ss");
/* 2262 */     Date date = new Date();
/* 2263 */     String str = simpleDateFormat.format(date);
/*      */     
/* 2265 */     switch (paramString) {
/*      */       
/*      */       case "fastModeScale":
/* 2268 */         this.status = this.intermediateLayer.fastModeScaleAction(scaleDeviceTypeBinder);
/* 2269 */         this.info = this.intermediateLayer.getDeviceInfo(scaleDeviceTypeBinder);
/* 2270 */         this.txtDeviceInfo.setText(this.info);
/*      */         break;
/*      */       
/*      */       case "statusNotify":
/* 2274 */         this.status = this.intermediateLayer.statusNotifyAction(scaleDeviceTypeBinder, this.statusNotifyEnabled);
/*      */         break;
/*      */       
/*      */       case "AsyncMode":
/* 2278 */         this.status = this.intermediateLayer.asyncModeAction(scaleDeviceTypeBinder, this.asyncModeEnabled);
/*      */         break;
/*      */       
/*      */       case "readWeight":
/* 2282 */         this.status = this.intermediateLayer.readWeightAction(scaleDeviceTypeBinder, this.scaleRWTimeout);
/*      */         break;
/*      */       
/*      */       case "zeroScale":
/* 2286 */         this.status = this.intermediateLayer.zeroScaleAction(scaleDeviceTypeBinder);
/*      */         break;
/*      */       case "clearInput":
/* 2289 */         this.status = this.intermediateLayer.clearInputAction(scaleDeviceTypeBinder);
/*      */         break;
/*      */       
/*      */       case "weightOnAsyncMode":
/* 2293 */         this.status = this.weightInAsyncM; break;
/*      */     } 
/* 2295 */     this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + scaleDeviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[0]);
/* 2296 */     if (this.status[1] != null) {
/* 2297 */       this.txtLogView.setText(this.txtLogView.getText() + "\n" + str + " : " + scaleDeviceTypeBinder.getDevice() + "      :" + logicalName + " " + this.status[1]);
/*      */     }
/*      */   }
/*      */   
/*      */   private void btnCloseActionPerformed(ActionEvent paramActionEvent) {
/* 2302 */     commonMethods("close");
/* 2303 */     this.chkScnDeviceEnable.setSelected(false);
/* 2304 */     this.chkScnDataEventEnable.setSelected(false);
/* 2305 */     this.chkScnDecodeData.setSelected(false);
/* 2306 */     this.chkScnAutoDataEventEnable.setSelected(false);
/* 2307 */     this.chkScnAutoDeviceEnable.setSelected(false);
/* 2308 */     this.chkScnFreezeEvents.setSelected(false);
/* 2309 */     this.chkScnAutoDisable.setSelected(false);
/* 2310 */     this.chkScnPowerNotify.setSelected(false);
/* 2311 */     this.chkSclPowerNotify.setSelected(false);
/*      */     
/* 2313 */     this.chkSclDeviceEnable.setSelected(false);
/* 2314 */     this.chkSclDataEventEnable.setSelected(false);
/* 2315 */     this.chkSclAsyncMode.setSelected(false);
/* 2316 */     this.chkSclAutoDeviceEnable.setSelected(false);
/* 2317 */     this.chkSclAutoDisable.setSelected(false);
/* 2318 */     this.chkSclEnableLiveWeight.setSelected(false);
/* 2319 */     this.chkSclFreezeEvents.setSelected(false);
/* 2320 */     this.chkSclEnableLiveWeight.setEnabled(true);
/* 2321 */     this.chkSclAutoDataEventEnable.setSelected(false);
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnClaimActionPerformed(ActionEvent paramActionEvent) {
/*      */     try {
/* 2327 */       this.claimTimeout = Integer.valueOf(this.txtClaimTimeout.getText()).intValue();
/* 2328 */       commonMethods("claim");
/* 2329 */     } catch (Exception exception) {
/* 2330 */       JOptionPane.showMessageDialog(null, "Invalid Timeout", "Failed", 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void btnReleaseActionPerformed(ActionEvent paramActionEvent) {
/* 2335 */     commonMethods("release");
/*      */   }
/*      */ 
/*      */   
/*      */   private void chkSclDataEventEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2340 */     if (this.chkSclDataEventEnable.isSelected()) {
/* 2341 */       this.dataEventEnabled = true;
/* 2342 */     } else if (!this.chkSclDataEventEnable.isSelected()) {
/* 2343 */       this.dataEventEnabled = false;
/*      */     } 
/* 2345 */     commonMethods("enableDataEvent");
/* 2346 */     this.chkSclDataEventEnable.setSelected(dataEventEnableC);
/*      */   }
/*      */   
/*      */   private void chkSclEnableLiveWeightActionPerformed(ActionEvent paramActionEvent) {
/* 2350 */     if (this.chkSclEnableLiveWeight.isSelected()) {
/* 2351 */       this.statusNotifyEnabled = 2;
/* 2352 */       scaleMethods("statusNotify");
/*      */       
/* 2354 */       if (this.autoDeviceEnable && !this.deviceEnabled) {
/* 2355 */         this.deviceEnabled = true;
/* 2356 */         commonMethods("enableDevice");
/* 2357 */         this.chkSclDeviceEnable.setSelected(true);
/* 2358 */         this.chkSclEnableLiveWeight.setEnabled(false);
/*      */       } 
/*      */     } else {
/* 2361 */       this.statusNotifyEnabled = 1;
/* 2362 */       scaleMethods("statusNotify");
/*      */     } 
/* 2364 */     if (!statusNotifyC) {
/* 2365 */       this.chkSclEnableLiveWeight.setSelected(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void chkSclAsyncModeActionPerformed(ActionEvent paramActionEvent) {
/* 2371 */     if (this.chkSclAsyncMode.isSelected()) {
/* 2372 */       this.asyncModeEnabled = true;
/* 2373 */     } else if (!this.chkSclAsyncMode.isSelected()) {
/* 2374 */       this.asyncModeEnabled = false;
/*      */     } 
/* 2376 */     scaleMethods("AsyncMode");
/* 2377 */     if (!asyncModeC) {
/* 2378 */       this.chkSclAsyncMode.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void chkSclFreezeEventsActionPerformed(ActionEvent paramActionEvent) {
/* 2383 */     if (this.chkSclFreezeEvents.isSelected()) {
/* 2384 */       this.freezeEventsEnabled = true;
/* 2385 */     } else if (!this.chkSclFreezeEvents.isSelected()) {
/* 2386 */       this.freezeEventsEnabled = false;
/*      */     } 
/* 2388 */     commonMethods("freezeEvents");
/* 2389 */     this.chkSclFreezeEvents.setSelected(freezeEventsC);
/*      */   }
/*      */   
/*      */   private void btnSclReadWeightActionPerformed(ActionEvent paramActionEvent) {
/*      */     try {
/* 2394 */       this.scaleRWTimeout = Integer.valueOf(this.txtSclRWTimeout.getText()).intValue();
/* 2395 */       scaleMethods("readWeight");
/* 2396 */       if (!this.asyncModeEnabled) {
/* 2397 */         this.txtSclDisplayWeight.setText(Float.toString(fWeight) + " " + units);
/*      */       }
/* 2399 */     } catch (Exception exception) {
/* 2400 */       JOptionPane.showMessageDialog(null, "Invalid Timeout", "Failed", 0);
/* 2401 */       this.txtSclRWTimeout.setText("1000");
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnSclZeroScaleActionPerformed(ActionEvent paramActionEvent) {
/* 2407 */     scaleMethods("zeroScale");
/*      */   }
/*      */   
/*      */   private void scnInternalCHActionPerformed(ActionEvent paramActionEvent) {
/* 2411 */     this.healthCheckType = 1;
/*      */   }
/*      */   
/*      */   private void scnExternalCHActionPerformed(ActionEvent paramActionEvent) {
/* 2415 */     this.healthCheckType = 2;
/*      */   }
/*      */   
/*      */   private void scnInteractiveCHActionPerformed(ActionEvent paramActionEvent) {
/* 2419 */     this.healthCheckType = 3;
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnScnCheckHealthTextActionPerformed(ActionEvent paramActionEvent) {
/* 2424 */     commonMethods("healthCheckText");
/* 2425 */     this.txtScnHealthCheckText.setText(healthCheckText);
/*      */   }
/*      */   
/*      */   private void chkSclDeviceEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2429 */     if (this.chkSclDeviceEnable.isSelected()) {
/* 2430 */       this.deviceEnabled = true;
/* 2431 */       commonMethods("enableDevice");
/*      */     } else {
/* 2433 */       this.deviceEnabled = false;
/* 2434 */       this.chkSclEnableLiveWeight.setEnabled(true);
/* 2435 */       commonMethods("enableDevice");
/*      */     } 
/*      */     
/* 2438 */     this.chkSclEnableLiveWeight.setEnabled(false);
/* 2439 */     this.chkSclDeviceEnable.setSelected(deviceEnableC);
/* 2440 */     this.chkSclEnableLiveWeight.setEnabled(!deviceEnableC);
/*      */   }
/*      */   
/*      */   private void btnSclClearInputActionPerformed(ActionEvent paramActionEvent) {
/* 2444 */     scaleMethods("clearInput");
/* 2445 */     this.txtSclDisplayWeight.setText("");
/*      */   }
/*      */   
/*      */   private void sclInternalActionPerformed(ActionEvent paramActionEvent) {
/* 2449 */     this.healthCheckType = 1;
/*      */   }
/*      */   
/*      */   private void sclExternalActionPerformed(ActionEvent paramActionEvent) {
/* 2453 */     this.healthCheckType = 2;
/*      */   }
/*      */   
/*      */   private void sclInteractiveActionPerformed(ActionEvent paramActionEvent) {
/* 2457 */     this.healthCheckType = 3;
/*      */   }
/*      */   
/*      */   private void btnSclCheckHealthActionPerformed(ActionEvent paramActionEvent) {
/* 2461 */     if (this.healthCheckType == -1) {
/* 2462 */       this.healthCheckType = 1;
/* 2463 */       this.sclInternal.setSelected(true);
/*      */     } 
/* 2465 */     commonMethods("healthCheck");
/*      */   }
/*      */   
/*      */   private void btnScnClearInputPropertiesActionPerformed(ActionEvent paramActionEvent) {
/* 2469 */     scannerMethods("clearInputProperties");
/*      */   }
/*      */ 
/*      */   
/*      */   private void updateChkScanner(String paramString, boolean paramBoolean) {
/* 2474 */     switch (paramString) {
/*      */       case "PIDX_AutoDisable":
/* 2476 */         this.chkScnAutoDisable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_DataEventEnabled":
/* 2479 */         this.chkScnDataEventEnable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_DeviceEnabled":
/* 2482 */         this.chkScnDeviceEnable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_FreezeEvents":
/* 2485 */         this.chkScnFreezeEvents.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDXScan_DecodeData":
/* 2488 */         this.chkScnDecodeData.setSelected(paramBoolean);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void btnScnPropertiesActionPerformed(ActionEvent paramActionEvent) {
/* 2494 */     this.scannerPropertyValue = this.txtScnPropertyValue.getText();
/* 2495 */     PropertyBinder propertyBinder = (PropertyBinder)this.cmbScnProperties.getSelectedItem();
/* 2496 */     String str1 = propertyBinder.getType();
/* 2497 */     String str2 = propertyBinder.getPropertyName();
/*      */     
/* 2499 */     if (null != this.scannerPropertyValue) {
/* 2500 */       switch (this.scannerPropertyValue) {
/*      */         case "false":
/* 2502 */           this.setValue = false;
/*      */           break;
/*      */         case "true":
/* 2505 */           this.setValue = true;
/*      */           break;
/*      */       } 
/*      */     
/*      */     }
/*      */     try {
/* 2511 */       switch (str1) {
/*      */         case "boolean":
/* 2513 */           propertyBinder.setBoolean(Boolean.valueOf(this.scannerPropertyValue).booleanValue());
/* 2514 */           updateChkScanner(str2, this.setValue);
/*      */           break;
/*      */         case "int":
/* 2517 */           propertyBinder.setInt(Integer.valueOf(this.scannerPropertyValue).intValue());
/*      */           break;
/*      */       } 
/* 2520 */       this.setPropertyStatus[0] = "Property Changed     :" + str2;
/* 2521 */       commonMethods("setProperty");
/* 2522 */     } catch (JposException jposException) {
/* 2523 */       JOptionPane.showMessageDialog(null, "Exception in set Properties : " + jposException.getMessage(), "Failed", 0);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void cmbScnPropertiesActionPerformed(ActionEvent paramActionEvent) {
/* 2529 */     PropertyBinder propertyBinder = (PropertyBinder)this.cmbScnProperties.getSelectedItem();
/* 2530 */     String str = propertyBinder.getType();
/* 2531 */     boolean bool = propertyBinder.isEditable();
/*      */ 
/*      */     
/* 2534 */     if (!bool) {
/* 2535 */       this.btnScnProperties.setEnabled(false);
/*      */     } else {
/* 2537 */       this.btnScnProperties.setEnabled(true);
/*      */     }  try {
/*      */       boolean bool1; int i; String str1; byte[] arrayOfByte;
/*      */       long l;
/* 2541 */       switch (str) {
/*      */         case "boolean":
/* 2543 */           bool1 = propertyBinder.getBoolean();
/* 2544 */           this.txtScnPropertyValue.setText(String.valueOf(bool1));
/*      */           break;
/*      */         case "int":
/* 2547 */           i = propertyBinder.getInt();
/* 2548 */           this.txtScnPropertyValue.setText(String.valueOf(i));
/*      */           break;
/*      */         case "string":
/* 2551 */           str1 = propertyBinder.getString();
/* 2552 */           this.txtScnPropertyValue.setText(str1);
/*      */           break;
/*      */         case "byte":
/* 2555 */           arrayOfByte = propertyBinder.getByte();
/* 2556 */           this.txtScnPropertyValue.setText(Arrays.toString(arrayOfByte));
/*      */           break;
/*      */         case "long":
/* 2559 */           l = propertyBinder.getLong();
/* 2560 */           this.txtScnPropertyValue.setText(String.valueOf(l));
/*      */           break;
/*      */       } 
/* 2563 */     } catch (Exception exception) {
/* 2564 */       JOptionPane.showMessageDialog(null, "Exception in Properties section: " + exception.getMessage(), "Failed", 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cmbSclCommandActionPerformed(ActionEvent paramActionEvent) {
/* 2569 */     DirectIOBinding directIOBinding = (DirectIOBinding)this.cmbSclCommand.getSelectedItem();
/* 2570 */     this.txtSclInXml.setText(directIOBinding.getInXml());
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnSclExecuteActionPerformed(ActionEvent paramActionEvent) {
/* 2575 */     DirectIOBinding directIOBinding = (DirectIOBinding)this.cmbSclCommand.getSelectedItem();
/* 2576 */     ScaleDeviceTypeBinder scaleDeviceTypeBinder = (ScaleDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/* 2577 */     this.deviceParams = new StringBuffer();
/* 2578 */     this.deviceParams.append(this.txtSclInXml.getText());
/* 2579 */     this.statusScanner = new int[] { -1 };
/* 2580 */     this.opCode = directIOBinding.getOpCode();
/*      */     
/* 2582 */     commonMethods("directInputOutput");
/* 2583 */     if (directIOC) {
/* 2584 */       this.txtSclOutXml.setText(this.deviceParams.toString());
/*      */     } else {
/* 2586 */       this.txtSclOutXml.setText("");
/*      */     } 
/*      */     
/* 2589 */     boolean bool = this.intermediateLayer.checkDeviceEnable(scaleDeviceTypeBinder);
/* 2590 */     this.chkSclDeviceEnable.setSelected(bool);
/*      */     
/* 2592 */     this.txtSclStatus.setText(Arrays.toString(this.statusScanner).replace("[", " ").replace("]", " "));
/*      */   }
/*      */   
/*      */   private void btnSclClearActionPerformed(ActionEvent paramActionEvent) {
/* 2596 */     this.txtSclOutXml.setText("");
/* 2597 */     this.txtSclStatus.setText("");
/*      */   }
/*      */   
/*      */   private void cmbSclPropertiesActionPerformed(ActionEvent paramActionEvent) {
/* 2601 */     PropertyBinder propertyBinder = (PropertyBinder)this.cmbSclProperties.getSelectedItem();
/* 2602 */     String str = propertyBinder.getType();
/* 2603 */     boolean bool = propertyBinder.isEditable();
/*      */ 
/*      */     
/* 2606 */     if (!bool) {
/* 2607 */       this.btnSclProperties.setEnabled(false);
/*      */     } else {
/* 2609 */       this.btnSclProperties.setEnabled(true);
/*      */     }  try {
/*      */       boolean bool1; long l1; String str1; byte[] arrayOfByte;
/*      */       long l2;
/* 2613 */       switch (str) {
/*      */         case "boolean":
/* 2615 */           bool1 = propertyBinder.getBoolean();
/* 2616 */           this.txtSclPropertyValue.setText(String.valueOf(bool1));
/*      */           break;
/*      */         case "int":
/* 2619 */           l1 = propertyBinder.getInt();
/* 2620 */           this.txtSclPropertyValue.setText(String.valueOf(l1));
/*      */           break;
/*      */         case "string":
/* 2623 */           str1 = propertyBinder.getString();
/* 2624 */           this.txtSclPropertyValue.setText(str1);
/*      */           break;
/*      */         case "byte":
/* 2627 */           arrayOfByte = propertyBinder.getByte();
/* 2628 */           this.txtSclPropertyValue.setText(Arrays.toString(arrayOfByte));
/*      */           break;
/*      */         case "long":
/* 2631 */           l2 = propertyBinder.getLong();
/* 2632 */           this.txtSclPropertyValue.setText(String.valueOf(l2));
/*      */           break;
/*      */       } 
/* 2635 */     } catch (Exception exception) {
/* 2636 */       JOptionPane.showMessageDialog(null, "Exception in Properties section: " + exception.getMessage(), "Failed", 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void updateChkScale(String paramString, boolean paramBoolean) {
/* 2641 */     switch (paramString) {
/*      */       case "PIDX_AutoDisable":
/* 2643 */         this.chkSclAutoDisable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_DataEventEnabled":
/* 2646 */         this.chkSclDataEventEnable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_DeviceEnabled":
/* 2649 */         this.chkSclDeviceEnable.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDX_FreezeEvents":
/* 2652 */         this.chkSclFreezeEvents.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDXScal_AsyncMode":
/* 2655 */         this.chkSclAsyncMode.setSelected(paramBoolean);
/*      */         break;
/*      */       case "PIDXScal_EnableLiveWeight":
/* 2658 */         if (!this.chkSclDeviceEnable.isSelected()) {
/* 2659 */           this.chkSclEnableLiveWeight.setSelected(paramBoolean);
/*      */         }
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void btnSclPropertiesActionPerformed(ActionEvent paramActionEvent) {
/* 2666 */     this.scalePropertyValue = this.txtSclPropertyValue.getText();
/* 2667 */     PropertyBinder propertyBinder = (PropertyBinder)this.cmbSclProperties.getSelectedItem();
/* 2668 */     String str1 = propertyBinder.getType();
/* 2669 */     String str2 = propertyBinder.getPropertyName();
/*      */     
/* 2671 */     if ("false".equals(this.scalePropertyValue)) {
/* 2672 */       this.setValue = false;
/* 2673 */     } else if ("true".equals(this.scalePropertyValue)) {
/* 2674 */       this.setValue = true;
/* 2675 */     } else if (Integer.valueOf(this.scalePropertyValue).intValue() == 2) {
/* 2676 */       this.setValue = true;
/* 2677 */     } else if (Integer.valueOf(this.scalePropertyValue).intValue() != 2) {
/* 2678 */       this.setValue = false;
/*      */     } 
/* 2680 */     updateChkScale(str2, this.setValue);
/*      */     
/*      */     try {
/* 2683 */       switch (str1) {
/*      */         case "boolean":
/* 2685 */           propertyBinder.setBoolean(Boolean.valueOf(this.scalePropertyValue).booleanValue());
/*      */           break;
/*      */         case "int":
/* 2688 */           propertyBinder.setInt(Integer.valueOf(this.scalePropertyValue).intValue());
/*      */           break;
/*      */         case "long":
/* 2691 */           propertyBinder.setLong(Long.valueOf(this.scalePropertyValue).longValue());
/*      */           break;
/*      */       } 
/*      */       
/* 2695 */       this.setPropertyStatus[0] = "Property Changed     :" + str2;
/* 2696 */       commonMethods("setProperty");
/* 2697 */     } catch (JposException jposException) {
/* 2698 */       JOptionPane.showMessageDialog(null, "Exception in set Properties : " + jposException.getMessage(), "Failed", 0);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void chkScnDeviceEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2703 */     if (this.chkScnDeviceEnable.isSelected()) {
/* 2704 */       this.deviceEnabled = true;
/* 2705 */       commonMethods("enableDevice");
/* 2706 */     } else if (!this.chkScnDeviceEnable.isSelected()) {
/* 2707 */       this.deviceEnabled = false;
/* 2708 */       commonMethods("enableDevice");
/*      */     } 
/* 2710 */     this.chkScnDeviceEnable.setSelected(deviceEnableC);
/*      */   }
/*      */   
/*      */   private String powerStateText(int paramInt) {
/* 2714 */     String str = "";
/* 2715 */     switch (paramInt) {
/*      */       case 2000:
/* 2717 */         str = "JPOS_PS_UNKNOWN";
/*      */         break;
/*      */       case 2001:
/* 2720 */         str = "JPOS_PS_ONLINE";
/*      */         break;
/*      */       case 2002:
/* 2723 */         str = "JPOS_PS_OFF";
/*      */         break;
/*      */       case 2003:
/* 2726 */         str = "JPOS_PS_OFFLINE";
/*      */         break;
/*      */       case 2004:
/* 2729 */         str = "JPOS_PS_OFF_OFFLINE";
/*      */         break;
/*      */     } 
/* 2732 */     return str;
/*      */   }
/*      */   
/*      */   private void chkScnDataEventEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2736 */     if (this.chkScnDataEventEnable.isSelected()) {
/* 2737 */       this.dataEventEnabled = true;
/* 2738 */     } else if (!this.chkScnDataEventEnable.isSelected()) {
/* 2739 */       this.dataEventEnabled = false;
/*      */     } 
/* 2741 */     commonMethods("enableDataEvent");
/* 2742 */     this.chkScnDataEventEnable.setSelected(dataEventEnableC);
/*      */   }
/*      */   
/*      */   private void btnFastModeActionPerformed(ActionEvent paramActionEvent) {
/* 2746 */     DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/* 2747 */     this.deviceType = deviceTypeBinder.getDevice();
/*      */     
/* 2749 */     if (null != this.deviceType) {
/* 2750 */       switch (this.deviceType) {
/*      */         case "Scanner":
/* 2752 */           scannerMethods("fastModeScanner");
/* 2753 */           if (fastModeScannerC) {
/* 2754 */             this.chkScnDeviceEnable.setSelected(true);
/* 2755 */             this.chkScnDataEventEnable.setSelected(false);
/* 2756 */             this.chkScnDecodeData.setSelected(false); break;
/* 2757 */           }  if (!fastModeScannerC) {
/* 2758 */             this.chkScnDeviceEnable.setSelected(false);
/* 2759 */             this.chkScnDataEventEnable.setSelected(false);
/* 2760 */             this.chkScnDecodeData.setSelected(false);
/*      */           } 
/*      */           break;
/*      */         case "Scale  ":
/* 2764 */           scaleMethods("fastModeScale");
/* 2765 */           if (fastModeScaleC) {
/* 2766 */             this.chkSclDeviceEnable.setSelected(true);
/* 2767 */             this.chkSclEnableLiveWeight.setEnabled(false); break;
/* 2768 */           }  if (!fastModeScaleC) {
/* 2769 */             this.chkSclDeviceEnable.setSelected(false);
/* 2770 */             this.chkSclEnableLiveWeight.setEnabled(true);
/*      */           } 
/*      */           break;
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void chkScnAutoDataEventEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2778 */     if (this.chkScnAutoDataEventEnable.isSelected()) {
/* 2779 */       this.dataEventEnabled = true;
/* 2780 */       this.autoDataEventEnableScanner = true;
/* 2781 */       this.chkScnDataEventEnable.setSelected(true);
/*      */     } else {
/* 2783 */       this.autoDataEventEnableScanner = false;
/*      */     } 
/* 2785 */     commonMethods("enableDataEvent");
/* 2786 */     if (!dataEventEnableC) {
/* 2787 */       this.chkScnAutoDataEventEnable.setSelected(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnScnCheckHealthActionPerformed(ActionEvent paramActionEvent) {
/* 2793 */     if (this.healthCheckType == -1) {
/* 2794 */       this.healthCheckType = 1;
/* 2795 */       this.scnInternalCH.setSelected(true);
/*      */     } 
/* 2797 */     commonMethods("healthCheck");
/*      */   }
/*      */   
/*      */   private void btnLogClearActionPerformed(ActionEvent paramActionEvent) {
/* 2801 */     this.txtLogView.setText("");
/*      */   }
/*      */   
/*      */   private void btnSclCHTextActionPerformed(ActionEvent paramActionEvent) {
/* 2805 */     commonMethods("healthCheckText");
/* 2806 */     this.txtSclCheckHealthText.setText(healthCheckText);
/*      */   }
/*      */   
/*      */   private void chkScnFreezeEventsActionPerformed(ActionEvent paramActionEvent) {
/* 2810 */     if (this.chkScnFreezeEvents.isSelected()) {
/* 2811 */       this.freezeEventsEnabled = true;
/* 2812 */     } else if (!this.chkScnFreezeEvents.isSelected()) {
/* 2813 */       this.freezeEventsEnabled = false;
/*      */     } 
/* 2815 */     commonMethods("freezeEvents");
/* 2816 */     this.chkScnFreezeEvents.setSelected(freezeEventsC);
/*      */   }
/*      */   private void logicalDeviceName(String paramString) {
/*      */     DefaultComboBoxModel defaultComboBoxModel1, defaultComboBoxModel2;
/* 2820 */     SimpleEntryRegistry simpleEntryRegistry = new SimpleEntryRegistry((JposRegPopulator)new SimpleXmlRegPopulator());
/* 2821 */     simpleEntryRegistry.load();
/*      */     
/* 2823 */     byte b1 = 0;
/* 2824 */     byte b2 = 0;
/*      */     
/* 2826 */     Enumeration<JposEntry> enumeration1 = simpleEntryRegistry.getEntries();
/*      */     
/* 2828 */     while (enumeration1.hasMoreElements()) {
/* 2829 */       JposEntry jposEntry = enumeration1.nextElement();
/* 2830 */       String[] arrayOfString = { jposEntry.getProp("deviceCategory").getValueAsString() };
/*      */       
/* 2832 */       switch (arrayOfString[0]) {
/*      */         case "Scanner":
/* 2834 */           b1++;
/*      */         
/*      */         case "Scale":
/* 2837 */           b2++;
/*      */       } 
/*      */ 
/*      */     
/*      */     } 
/* 2842 */     byte b3 = 0;
/* 2843 */     byte b4 = 0;
/*      */     
/* 2845 */     Object[] arrayOfObject1 = new Object[b1];
/* 2846 */     Object[] arrayOfObject2 = new Object[b2];
/*      */     
/* 2848 */     Enumeration<JposEntry> enumeration2 = simpleEntryRegistry.getEntries();
/* 2849 */     while (enumeration2.hasMoreElements()) {
/* 2850 */       JposEntry jposEntry = enumeration2.nextElement();
/*      */       
/* 2852 */       String[] arrayOfString = { jposEntry.getProp("deviceCategory").getValueAsString(), jposEntry.getLogicalName() };
/*      */       
/* 2854 */       switch (arrayOfString[0]) {
/*      */         case "Scanner":
/* 2856 */           arrayOfObject1[b3] = arrayOfString[1];
/* 2857 */           b3++;
/*      */         
/*      */         case "Scale":
/* 2860 */           arrayOfObject2[b4] = arrayOfString[1];
/* 2861 */           b4++;
/*      */       } 
/*      */ 
/*      */ 
/*      */     
/*      */     } 
/* 2867 */     Arrays.sort(arrayOfObject1);
/* 2868 */     Arrays.sort(arrayOfObject2);
/*      */     
/* 2870 */     switch (paramString) {
/*      */       case "Scanner":
/* 2872 */         defaultComboBoxModel1 = new DefaultComboBoxModel(arrayOfObject1);
/* 2873 */         this.cmbLogicalDevice.setModel(defaultComboBoxModel1);
/*      */         break;
/*      */       case "Scale  ":
/* 2876 */         defaultComboBoxModel2 = new DefaultComboBoxModel(arrayOfObject2);
/* 2877 */         this.cmbLogicalDevice.setModel(defaultComboBoxModel2);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void cmbDeviceCategoryActionPerformed(ActionEvent paramActionEvent) {
/* 2883 */     DeviceTypeBinder deviceTypeBinder = (DeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/* 2884 */     this.deviceType = deviceTypeBinder.getDevice();
/*      */     
/* 2886 */     logicalDeviceName(this.deviceType);
/*      */     
/* 2888 */     switch (this.deviceType) {
/*      */       case "Scanner":
/* 2890 */         this.jTabbedPane.setSelectedIndex(0);
/* 2891 */         this.scnInternalCH.setSelected(true);
/* 2892 */         this.scanDataText.setSelected(true);
/*      */         break;
/*      */       case "Scale  ":
/* 2895 */         this.jTabbedPane.setSelectedIndex(1);
/* 2896 */         this.sclInternal.setSelected(true);
/*      */         break;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void btnOpenActionPerformed(ActionEvent paramActionEvent) {
/* 2902 */     commonMethods("open");
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnSclCopyActionPerformed(ActionEvent paramActionEvent) {
/* 2907 */     String str = this.txtSclOutXml.getText();
/* 2908 */     StringSelection stringSelection = new StringSelection(str);
/* 2909 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 2910 */     clipboard.setContents(stringSelection, stringSelection);
/*      */   }
/*      */   
/*      */   private void chkScnAutoDisableActionPerformed(ActionEvent paramActionEvent) {
/* 2914 */     if (this.chkScnAutoDisable.isSelected()) {
/* 2915 */       this.autoDisable = true;
/* 2916 */     } else if (!this.chkScnAutoDisable.isSelected()) {
/* 2917 */       this.autoDisable = false;
/*      */     } 
/* 2919 */     commonMethods("autoDisable");
/* 2920 */     this.chkScnAutoDisable.setSelected(autoDisableC);
/*      */   }
/*      */   
/*      */   private void chkSclAutoDisableActionPerformed(ActionEvent paramActionEvent) {
/* 2924 */     if (this.chkSclAutoDisable.isSelected()) {
/* 2925 */       this.autoDisable = true;
/* 2926 */     } else if (!this.chkSclAutoDisable.isSelected()) {
/* 2927 */       this.autoDisable = false;
/*      */     } 
/* 2929 */     commonMethods("autoDisable");
/* 2930 */     this.chkSclAutoDisable.setSelected(autoDisableC);
/*      */   }
/*      */   
/*      */   private void chkSclAutoDeviceEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2934 */     if (this.chkSclAutoDeviceEnable.isSelected()) {
/* 2935 */       this.autoDeviceEnable = true;
/* 2936 */     } else if (!this.chkSclAutoDeviceEnable.isSelected()) {
/* 2937 */       this.autoDeviceEnable = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void chkScnAutoDeviceEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2942 */     if (this.chkScnAutoDeviceEnable.isSelected()) {
/* 2943 */       this.deviceEnabled = true;
/* 2944 */       this.autoDeviceEnable = true;
/* 2945 */       commonMethods("enableDevice");
/* 2946 */       this.chkScnDeviceEnable.setSelected(true);
/*      */     } else {
/* 2948 */       this.autoDeviceEnable = false;
/*      */     } 
/* 2950 */     if (!deviceEnableC) {
/* 2951 */       this.chkScnAutoDeviceEnable.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void chkSclAutoDataEventEnableActionPerformed(ActionEvent paramActionEvent) {
/* 2956 */     if (this.chkSclAutoDataEventEnable.isSelected()) {
/* 2957 */       this.dataEventEnabled = true;
/* 2958 */       this.autoDataEventEnableScale = true;
/* 2959 */       this.chkSclDataEventEnable.setSelected(true);
/*      */     } else {
/* 2961 */       this.autoDataEventEnableScale = false;
/*      */     } 
/* 2963 */     commonMethods("enableDataEvent");
/* 2964 */     if (!dataEventEnableC) {
/* 2965 */       this.chkSclAutoDataEventEnable.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void btnScnResetStatActionPerformed(ActionEvent paramActionEvent) {
/* 2970 */     this.resetStatValue = this.lblScnStatistic.getText();
/* 2971 */     commonMethods("resetStatistics");
/*      */   }
/*      */   
/*      */   private void btnScnRetreiveStatActionPerformed(ActionEvent paramActionEvent) {
/* 2975 */     this.retrieveStatValue[0] = this.lblScnStatistic.getText();
/* 2976 */     commonMethods("retrieveStatistics");
/* 2977 */     this.txtScnStatOutput.setText(formatXml(this.retrieveStatValue[0]));
/*      */   }
/*      */   
/*      */   public String formatXml(String paramString) {
/* 2981 */    StreamSource streamSource = new StreamSource(new StringReader(paramString));
/* 2982 */     StringWriter stringWriter = new StringWriter();
/*      */     try {
/* 2984 */       TransformerFactory transformerFactory = TransformerFactory.newInstance();
/* 2985 */       Transformer transformer = transformerFactory.newTransformer();
/* 2986 */       transformer.setOutputProperty("indent", "yes");
/* 2987 */       transformer.setOutputProperty("doctype-public", "yes");
/* 2988 */       transformer.transform(streamSource, new StreamResult(stringWriter));
/*      */       
/* 2990 */       return stringWriter.toString().trim();
/* 2991 */     } catch (IllegalArgumentException|TransformerException illegalArgumentException) {
/* 2992 */       System.err.println("Scanner: Failed to format the xml" + illegalArgumentException);
/* 2993 */       return paramString;
/*      */     } 
/*      */   }
/*      */   
/*      */   private void btnSclRetreiveStatActionPerformed(ActionEvent paramActionEvent) {
/* 2998 */     this.retrieveStatValue[0] = this.lblSclStatistic.getText();
/* 2999 */     commonMethods("retrieveStatistics");
/* 3000 */     this.txtSclStatOutput.setText(formatXml(this.retrieveStatValue[0]));
/*      */   }
/*      */   
/*      */   private void btnSclResetStatActionPerformed(ActionEvent paramActionEvent) {
/* 3004 */     this.resetStatValue = this.lblSclStatistic.getText();
/* 3005 */     commonMethods("resetStatistics");
/*      */   }
/*      */   
/*      */   private void chkScnPowerNotifyActionPerformed(ActionEvent paramActionEvent) {
/* 3009 */     if (this.chkScnPowerNotify.isSelected()) {
/* 3010 */       this.powerNotifyEnabled = 1;
/*      */     } else {
/* 3012 */       this.powerNotifyEnabled = 0;
/*      */     } 
/* 3014 */     commonMethods("powerNotify");
/*      */     
/* 3016 */     this.chkScnPowerNotify.setSelected(powerNotifyC);
/*      */   }
/*      */   
/*      */   private void chkSclPowerNotifyActionPerformed(ActionEvent paramActionEvent) {
/* 3020 */     if (this.chkSclPowerNotify.isSelected()) {
/* 3021 */       this.powerNotifyEnabled = 1;
/* 3022 */       commonMethods("powerNotify");
/*      */     } else {
/* 3024 */       this.powerNotifyEnabled = 0;
/* 3025 */       commonMethods("powerNotify");
/*      */     } 
/* 3027 */     if (!powerNotifyC) {
/* 3028 */       this.chkSclPowerNotify.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void btnScnPowerStateActionPerformed(ActionEvent paramActionEvent) {
/* 3033 */     commonMethods("powerState");
/* 3034 */     this.txtScnPowerState.setText(powerStateText(powerState));
/*      */   }
/*      */   
/*      */   private void btnSclPowerStateActionPerformed(ActionEvent paramActionEvent) {
/* 3038 */     commonMethods("powerState");
/* 3039 */     this.txtSclPowerState.setText(Integer.toString(powerState));
/*      */   }
/*      */ 
/*      */   
/*      */   private void btnScnCopyActionPerformed(ActionEvent paramActionEvent) {
/* 3044 */     String str = this.txtScnOutXml.getText();
/* 3045 */     StringSelection stringSelection = new StringSelection(str);
/* 3046 */     Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/* 3047 */     clipboard.setContents(stringSelection, stringSelection);
/*      */   }
/*      */   
/*      */   private void btnScnClearActionPerformed(ActionEvent paramActionEvent) {
/* 3051 */     this.txtScnOutXml.setText("");
/* 3052 */     this.txtScnStatus.setText("");
/*      */   }
/*      */   
/*      */   private void btnScnExecuteActionPerformed(ActionEvent paramActionEvent) {
/* 3056 */     DirectIOBinding directIOBinding = (DirectIOBinding)this.cmbScnCommand.getSelectedItem();
/* 3057 */     ScannerDeviceTypeBinder scannerDeviceTypeBinder = (ScannerDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/* 3058 */     this.deviceParams = new StringBuffer();
/* 3059 */     this.deviceParams.append(this.txtScnInxml.getText());
/* 3060 */     this.statusScanner = new int[] { -1 };
/* 3061 */     this.opCode = directIOBinding.getOpCode();
/*      */     
/* 3063 */     commonMethods("directInputOutput");
/* 3064 */     if (directIOC) {
/* 3065 */       if (this.deviceParams.length() <= 5) {
/* 3066 */         this.deviceParams = new StringBuffer(" ");
/*      */       }
/* 3068 */       this.txtScnOutXml.setText(this.deviceParams.toString());
/*      */     } 
/* 3070 */     boolean bool = this.intermediateLayer.checkDeviceEnable(scannerDeviceTypeBinder);
/* 3071 */     this.chkScnDeviceEnable.setSelected(bool);
/*      */     
/* 3073 */     this.txtScnStatus.setText(Arrays.toString(this.statusScanner).replace("[", " ").replace("]", " "));
/*      */   }
/*      */   
/*      */   private void cmbScnCommandActionPerformed(ActionEvent paramActionEvent) {
/* 3077 */     DirectIOBinding directIOBinding = (DirectIOBinding)this.cmbScnCommand.getSelectedItem();
/* 3078 */     this.txtScnInxml.setText(directIOBinding.getInXml());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void cmbLogicalDeviceActionPerformed(ActionEvent paramActionEvent) {}
/*      */ 
/*      */   
/*      */   private void txtClaimTimeoutActionPerformed(ActionEvent paramActionEvent) {}
/*      */ 
/*      */   
/*      */   private void scanDataHexActionPerformed(ActionEvent paramActionEvent) {
/* 3090 */     this.txtScanDataLabel.setText(scanDataLabelHex);
/*      */   }
/*      */   
/*      */   private void chkScnDecodeDataActionPerformed(ActionEvent paramActionEvent) {
/* 3094 */     if (this.chkScnDecodeData.isSelected()) {
/* 3095 */       this.decodeDataEnabled = true;
/* 3096 */     } else if (!this.chkScnDecodeData.isSelected()) {
/* 3097 */       this.decodeDataEnabled = false;
/*      */     } 
/* 3099 */     scannerMethods("decodeData");
/* 3100 */     if (!decodeDataEnableC) {
/* 3101 */       this.chkScnDecodeData.setSelected(false);
/*      */     }
/*      */   }
/*      */   
/*      */   private void btnClearInputActionPerformed(ActionEvent paramActionEvent) {
/* 3106 */     this.txtScanData.setText("");
/* 3107 */     this.txtScanDataLabel.setText("");
/* 3108 */     this.txtScanDataType.setText("");
/* 3109 */     this.txtDataCount.setText("");
/* 3110 */     scanData = "";
/* 3111 */     scanDataLabelText = "";
/* 3112 */     scanDataLabelHex = "";
/*      */     
/* 3114 */     commonMethods("clearInput");
/*      */   }
/*      */   
/*      */   private void scanDataTextActionPerformed(ActionEvent paramActionEvent) {
/* 3118 */     this.txtScanDataLabel.setText(scanDataLabelText);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void txtScnHealthCheckTextActionPerformed(ActionEvent paramActionEvent) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void txtSclPropertyValueActionPerformed(ActionEvent paramActionEvent) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void txtScnStatusActionPerformed(ActionEvent paramActionEvent) {}
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] paramArrayOfString) {
/*      */     try {
/* 3141 */       for (UIManager.LookAndFeelInfo lookAndFeelInfo : UIManager.getInstalledLookAndFeels()) {
/* 3142 */         if ("Nimbus".equals(lookAndFeelInfo.getName())) {
/* 3143 */           UIManager.setLookAndFeel(lookAndFeelInfo.getClassName());
/*      */           
/*      */           break;
/*      */         } 
/*      */       } 
/* 3148 */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|UnsupportedLookAndFeelException classNotFoundException) {
/* 3149 */       Logger.getLogger(JposSampleApp.class
/* 3150 */           .getName()).log(Level.SEVERE, (String)null, classNotFoundException);
/*      */     } 
/*      */ 
/*      */     
/* 3154 */     java.awt.EventQueue.invokeLater(new Runnable()
/*      */         {
/*      */           public void run() {
/* 3157 */             (new JposSampleApp()).setVisible(true);
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void updateScannerGUI() throws JposException {
/* 3311 */     if (this.autoDataEventEnableScanner) {
/* 3312 */       this.dataEventEnabled = true;
/* 3313 */       commonMethods("enableDataEvent");
/*      */     } else {
/* 3315 */       this.chkScnDataEventEnable.setSelected(false);
/*      */     } 
/*      */     
/* 3318 */     if (this.chkScnAutoDisable.isSelected()) {
/* 3319 */       if (this.chkScnAutoDeviceEnable.isSelected()) {
/* 3320 */         this.chkScnDeviceEnable.setSelected(true);
/* 3321 */         commonMethods("enableDevice");
/*      */       } else {
/* 3323 */         this.chkScnDeviceEnable.setSelected(false);
/* 3324 */         this.chkScnDataEventEnable.setSelected(false);
/* 3325 */         this.chkScnAutoDataEventEnable.setSelected(false);
/*      */       } 
/*      */     }
/*      */     
/* 3329 */     if (this.scanDataHex.isSelected()) {
/* 3330 */       this.txtScanDataLabel.setText(scanDataLabelHex);
/*      */     } else {
/* 3332 */       this.txtScanDataLabel.setText(scanDataLabelText);
/* 3333 */       this.scanDataText.setSelected(true);
/*      */     } 
/* 3335 */     this.txtScanData.setText(scanData);
/* 3336 */     this.txtScanDataType.setText(Integer.toString(scanDataType) + " (" + BarcodeType.getBarcodeTypeName(scanDataType) + ")");
/* 3337 */     this.txtDataCount.setText(String.valueOf(scanDataCount));
/*      */   }
/*      */ 
/*      */   
/*      */   public void updateScaleGUI() throws JposException {
/* 3342 */     scaleMethods("weightOnAsyncMode");
/*      */     
/* 3344 */     float f = this.weightAM / 1000.0F;
/* 3345 */     this.txtSclDisplayWeight.setText(String.valueOf(f) + "  " + units);
/* 3346 */     if (this.autoDataEventEnableScale) {
/* 3347 */       this.dataEventEnabled = true;
/* 3348 */       commonMethods("enableDataEvent");
/*      */     } else {
/* 3350 */       this.chkSclDataEventEnable.setSelected(false);
/*      */     } 
/*      */     
/* 3353 */     if (this.chkSclAutoDisable.isSelected()) {
/* 3354 */       if (this.chkSclAutoDeviceEnable.isSelected()) {
/* 3355 */         this.chkSclDeviceEnable.setSelected(true);
/* 3356 */         commonMethods("enableDevice");
/*      */       } else {
/* 3358 */         this.chkSclDeviceEnable.setSelected(false);
/* 3359 */         this.chkSclAutoDataEventEnable.setSelected(false);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void updateScaleUIAfterErrorEvent() throws JposException {
/* 3365 */     if (this.autoDataEventEnableScale) {
/* 3366 */       this.dataEventEnabled = true;
/* 3367 */       commonMethods("enableDataEvent");
/*      */     } else {
/* 3369 */       this.chkSclDataEventEnable.setSelected(false);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void dataOccurred(DataEvent paramDataEvent) {
/* 3376 */     ScannerDeviceTypeBinder scannerDeviceTypeBinder = (ScannerDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/*      */     try {
/* 3378 */       this.intermediateLayer.dataListenerEvent(scannerDeviceTypeBinder);
/* 3379 */       SwingUtilities.invokeLater(this.doUpdateGUI);
/* 3380 */     } catch (Exception exception) {
/* 3381 */       this.txtLogView.setText("InvokeLater exception." + exception);
/*      */       
/* 3383 */       Logger.getLogger(JposSampleApp.class
/* 3384 */           .getName()).log(Level.SEVERE, (String)null, exception);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void statusUpdateOccurred(StatusUpdateEvent paramStatusUpdateEvent) {
/* 3390 */     ScaleDeviceTypeBinder scaleDeviceTypeBinder = (ScaleDeviceTypeBinder)this.cmbDeviceCategory.getSelectedItem();
/* 3391 */     String str = this.intermediateLayer.statusUpdateListenerEvent(scaleDeviceTypeBinder, paramStatusUpdateEvent);
/* 3392 */     this.txtSclLiveWeight.setText(String.valueOf(str));
/*      */   }
/*      */ 
/*      */   
/*      */   public void errorOccurred(ErrorEvent paramErrorEvent) {
/* 3397 */     System.out.println("Scale error event recieved. Error code : " + paramErrorEvent.getErrorCode());
/* 3398 */     this.txtSclDisplayWeight.setText("Error Occurred. Error Code: " + paramErrorEvent.getErrorCode());
/*      */     
/* 3400 */     this.errorStatus[0] = "Scale error event recieved. Error code :  " + paramErrorEvent.getErrorCode();
/* 3401 */     commonMethods("errorOccured");
/*      */   }
/*      */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\JposSampleApp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */