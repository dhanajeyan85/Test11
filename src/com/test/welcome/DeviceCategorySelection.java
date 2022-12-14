package com.test.welcome;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import jpos.JposException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DeviceCategorySelection
/*     */ {
/*  19 */   private final String attribGetAllInXml = "<inArgs>\n <scannerID>1</scannerID>\n</inArgs>";
/*     */ 
/*     */ 
/*     */   
/*  23 */   private final String attribGetInXml = "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   public final String attribSetInXml = "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>\n    <attribute>\n      <id>1</id>\n      <datatype>F</datatype>\n      <value>True</value>\n    </attribute>\n   </attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  47 */   private final String attribGetNcrDirAccessInXml = "<inArgs>\n <scannerID>1</scannerID>\n <opcode>5000</opcode>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultComboBoxModel deviceCategory() throws JposException {
/*  59 */     ArrayList arrayList = new ArrayList();
/*     */     
/*  61 */     arrayList.add(new ScannerDeviceTypeBinder(JposSampleApp.scanner));
/*  62 */     arrayList.add(new ScaleDeviceTypeBinder(JposSampleApp.scale));
/*     */     
/*  64 */     return new DefaultComboBoxModel(arrayList.toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultComboBoxModel scannerProperty() throws JposException {
/*  71 */     ArrayList<PropertyBinder> arrayList = new ArrayList();
/*     */     
/*  73 */     arrayList.add(new PropertyBinder("PIDX_AutoDisable", "boolean", JposSampleApp.scanner::getAutoDisable, JposSampleApp.scanner::setAutoDisable, true));
/*  74 */     arrayList.add(new PropertyBinder("PIDX_CapCompareFirmwareVersion", "boolean", JposSampleApp.scanner::getCapCompareFirmwareVersion, null, false));
/*  75 */     arrayList.add(new PropertyBinder("PIDX_CapPowerReporting", "int", JposSampleApp.scanner::getCapPowerReporting, null, false));
/*  76 */     arrayList.add(new PropertyBinder("PIDX_CapStatisticsReporting", "boolean", JposSampleApp.scanner::getCapStatisticsReporting, null, false));
/*  77 */     arrayList.add(new PropertyBinder("PIDX_CapUpdateFirmware", "boolean", JposSampleApp.scanner::getCapUpdateFirmware, null, false));
/*     */     
/*  79 */     arrayList.add(new PropertyBinder("PIDX_CapUpdateStatistics", "boolean", JposSampleApp.scanner::getCapUpdateStatistics, null, false));
/*  80 */     arrayList.add(new PropertyBinder("PIDX_CheckHealthText", "string", JposSampleApp.scanner::getCheckHealthText, null, false));
/*  81 */     arrayList.add(new PropertyBinder("PIDX_Claimed", "boolean", JposSampleApp.scanner::getClaimed, null, false));
/*  82 */     arrayList.add(new PropertyBinder("PIDX_DataCount", "int", JposSampleApp.scanner::getDataCount, null, false));
/*  83 */     arrayList.add(new PropertyBinder("PIDX_DataEventEnabled", "boolean", JposSampleApp.scanner::getDataEventEnabled, JposSampleApp.scanner::setDataEventEnabled, true));
/*     */     
/*  85 */     arrayList.add(new PropertyBinder("PIDX_DeviceControlDescription", "string", JposSampleApp.scanner::getDeviceControlDescription, null, false));
/*  86 */     arrayList.add(new PropertyBinder("PIDX_DeviceControlVersion", "int", JposSampleApp.scanner::getDeviceControlVersion, null, false));
/*  87 */     arrayList.add(new PropertyBinder("PIDX_DeviceEnabled", "boolean", JposSampleApp.scanner::getDeviceEnabled, JposSampleApp.scanner::setDeviceEnabled, true));
/*  88 */     arrayList.add(new PropertyBinder("PIDX_DeviceServiceDescription", "string", JposSampleApp.scanner::getDeviceServiceDescription, null, false));
/*  89 */     arrayList.add(new PropertyBinder("PIDX_DeviceServiceVersion", "int", JposSampleApp.scanner::getDeviceServiceVersion, null, false));
/*     */     
/*  91 */     arrayList.add(new PropertyBinder("PIDX_FreezeEvents", "boolean", JposSampleApp.scanner::getFreezeEvents, JposSampleApp.scanner::setFreezeEvents, true));
/*  92 */     arrayList.add(new PropertyBinder("PIDX_PowerNotify", "int", JposSampleApp.scanner::getPowerNotify, JposSampleApp.scanner::setPowerNotify, true));
/*  93 */     arrayList.add(new PropertyBinder("PIDX_PowerState", "int", JposSampleApp.scanner::getPowerState, null, false));
/*  94 */     arrayList.add(new PropertyBinder("PIDX_PhysicalDeviceDescription", "string", JposSampleApp.scanner::getPhysicalDeviceDescription, null, false));
/*  95 */     arrayList.add(new PropertyBinder("PIDX_PhysicalDeviceName", "string", JposSampleApp.scanner::getPhysicalDeviceName, null, false));
/*     */     
/*  97 */     arrayList.add(new PropertyBinder("PIDX_State", "int", JposSampleApp.scanner::getState, null, false));
/*  98 */     arrayList.add(new PropertyBinder("PIDXScan_DecodeData", "boolean", JposSampleApp.scanner::getDecodeData, JposSampleApp.scanner::setDecodeData, true));
/*  99 */     arrayList.add(new PropertyBinder("PIDXScan_ScanData", "byte", JposSampleApp.scanner::getScanData, null, false));
/* 100 */     arrayList.add(new PropertyBinder("PIDXScan_ScanDataLabel", "byte", JposSampleApp.scanner::getScanDataLabel, null, false));
/* 101 */     arrayList.add(new PropertyBinder("PIDXScan_ScanDataType", "int", JposSampleApp.scanner::getScanDataType, null, false));
/*     */     
/* 103 */     return new DefaultComboBoxModel(arrayList.toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultComboBoxModel scaleProperty() throws JposException {
/* 111 */     ArrayList<PropertyBinder> arrayList = new ArrayList();
/*     */     
/* 113 */     arrayList.add(new PropertyBinder("PIDX_AutoDisable", "boolean", JposSampleApp.scale::getAutoDisable, JposSampleApp.scale::setAutoDisable, true));
/* 114 */     arrayList.add(new PropertyBinder("PIDX_CapCompareFirmwareVersion", "boolean", JposSampleApp.scale::getCapCompareFirmwareVersion, null, false));
/* 115 */     arrayList.add(new PropertyBinder("PIDX_CapPowerReporting", "int", JposSampleApp.scale::getCapPowerReporting, null, false));
/* 116 */     arrayList.add(new PropertyBinder("PIDX_CapStatisticsReporting", "boolean", JposSampleApp.scale::getCapStatisticsReporting, null, false));
/* 117 */     arrayList.add(new PropertyBinder("PIDX_CapUpdateFirmware", "boolean", JposSampleApp.scale::getCapUpdateFirmware, null, false));
/*     */     
/* 119 */     arrayList.add(new PropertyBinder("PIDX_CapUpdateStatistics", "boolean", JposSampleApp.scale::getCapUpdateStatistics, null, false));
/* 120 */     arrayList.add(new PropertyBinder("PIDX_CheckHealthText", "string", JposSampleApp.scale::getCheckHealthText, null, false));
/* 121 */     arrayList.add(new PropertyBinder("PIDX_Claimed", "boolean", JposSampleApp.scale::getClaimed, null, false));
/* 122 */     arrayList.add(new PropertyBinder("PIDX_DataCount", "int", JposSampleApp.scale::getDataCount, null, false));
/* 123 */     arrayList.add(new PropertyBinder("PIDX_DataEventEnabled", "boolean", JposSampleApp.scale::getDataEventEnabled, JposSampleApp.scale::setDataEventEnabled, true));
/*     */     
/* 125 */     arrayList.add(new PropertyBinder("PIDX_DeviceControlDescription", "string", JposSampleApp.scale::getDeviceControlDescription, null, false));
/* 126 */     arrayList.add(new PropertyBinder("PIDX_DeviceControlVersion", "int", JposSampleApp.scale::getDeviceControlVersion, null, false));
/* 127 */     arrayList.add(new PropertyBinder("PIDX_DeviceEnabled", "boolean", JposSampleApp.scale::getDeviceEnabled, JposSampleApp.scale::setDeviceEnabled, true));
/* 128 */     arrayList.add(new PropertyBinder("PIDX_DeviceServiceDescription", "string", JposSampleApp.scale::getDeviceServiceDescription, null, false));
/* 129 */     arrayList.add(new PropertyBinder("PIDX_DeviceServiceVersion", "int", JposSampleApp.scale::getDeviceServiceVersion, null, false));
/*     */     
/* 131 */     arrayList.add(new PropertyBinder("PIDX_FreezeEvents", "boolean", JposSampleApp.scale::getFreezeEvents, JposSampleApp.scale::setFreezeEvents, true));
/* 132 */     arrayList.add(new PropertyBinder("PIDX_PowerNotify", "int", JposSampleApp.scale::getPowerNotify, JposSampleApp.scale::setPowerNotify, true));
/* 133 */     arrayList.add(new PropertyBinder("PIDX_PowerState", "int", JposSampleApp.scale::getPowerState, null, false));
/* 134 */     arrayList.add(new PropertyBinder("PIDX_PhysicalDeviceDescription", "string", JposSampleApp.scale::getPhysicalDeviceDescription, null, false));
/* 135 */     arrayList.add(new PropertyBinder("PIDX_PhysicalDeviceName", "string", JposSampleApp.scale::getPhysicalDeviceName, null, false));
/*     */     
/* 137 */     arrayList.add(new PropertyBinder("PIDX_State", "int", JposSampleApp.scale::getState, null, false));
/* 138 */     arrayList.add(new PropertyBinder("PIDXScal_MaxDisplayTextChars", "int", JposSampleApp.scale::getMaxDisplayTextChars, null, false));
/* 139 */     arrayList.add(new PropertyBinder("PIDXScal_AsyncMode", "boolean", JposSampleApp.scale::getAsyncMode, JposSampleApp.scale::setAsyncMode, true));
/* 140 */     arrayList.add(new PropertyBinder("PIDXScal_MaximumWeight", "int", JposSampleApp.scale::getMaximumWeight, null, false));
/*     */     
/* 142 */     arrayList.add(new PropertyBinder("PIDXScal_ScaleLiveWeight", "int", JposSampleApp.scale::getScaleLiveWeight, null, false));
/* 143 */     arrayList.add(new PropertyBinder("PIDXScal_EnableLiveWeight", "int", JposSampleApp.scale::getStatusNotify, JposSampleApp.scale::setStatusNotify, true));
/* 144 */     arrayList.add(new PropertyBinder("PIDXScal_TarWeight", "int", JposSampleApp.scale::getTareWeight, JposSampleApp.scale::setTareWeight, true));
/* 145 */     arrayList.add(new PropertyBinder("PIDXScal_UnitPrice", JposSampleApp.scale::getUnitPrice, JposSampleApp.scale::setUnitPrice, "long", true));
/* 146 */     arrayList.add(new PropertyBinder("PIDXScal_SalesPrice", JposSampleApp.scale::getSalesPrice, null, "long", false));
/*     */     
/* 148 */     arrayList.add(new PropertyBinder("PIDXScal_WeightUnit", "int", JposSampleApp.scale::getWeightUnit, null, false));
/* 149 */     arrayList.add(new PropertyBinder("PIDXScal_ZeroValid", "boolean", JposSampleApp.scale::getZeroValid, JposSampleApp.scale::setZeroValid, true));
/* 150 */     arrayList.add(new PropertyBinder("PIDXScal_CapDisplay", "boolean", JposSampleApp.scale::getCapDisplay, null, false));
/* 151 */     arrayList.add(new PropertyBinder("PIDXScal_CapDisplayText", "boolean", JposSampleApp.scale::getCapDisplayText, null, false));
/* 152 */     arrayList.add(new PropertyBinder("PIDXScal_CapPriceCalculating", "boolean", JposSampleApp.scale::getCapPriceCalculating, null, false));
/*     */     
/* 154 */     arrayList.add(new PropertyBinder("PIDXScal_CapTareWeight", "boolean", JposSampleApp.scale::getCapTareWeight, null, false));
/* 155 */     arrayList.add(new PropertyBinder("PIDXScal_CapZeroScale", "boolean", JposSampleApp.scale::getCapZeroScale, null, false));
/* 156 */     arrayList.add(new PropertyBinder("PIDXScal_CapStatusUpdate", "boolean", JposSampleApp.scale::getCapStatusUpdate, null, false));
/*     */     
/* 158 */     return new DefaultComboBoxModel(arrayList.toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultComboBoxModel scnDirectIOCommand() {
/* 167 */     ArrayList<DirectIOBinding> arrayList = new ArrayList();
/*     */     
/* 169 */     String[] arrayOfString1 = { "GET_SCANNERS", "RSM_ATTR_GETALL", "RSM_ATTR_GET", "RSM_ATTR_GETNEXT", "RSM_ATTR_SET", "RSM_ATTR_STORE", "DIO_NCR_SCANNER_NOF", "DIO_NCR_SCANNER_TONE", "DIO_SCANNER_NOT_ON_FILE", "DIO_SCANNER_DIO_NOF", "NCRDIO_SCAN_RESET", "NCRDIO_SCAN_STATUS", "NCRDIO_SCAN_DIRECT" };
/* 170 */     String[] arrayOfString2 = { "", "<inArgs>\n <scannerID>1</scannerID>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>\n    <attribute>\n      <id>1</id>\n      <datatype>F</datatype>\n      <value>True</value>\n    </attribute>\n   </attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>\n    <attribute>\n      <id>1</id>\n      <datatype>F</datatype>\n      <value>True</value>\n    </attribute>\n   </attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "", "1001", "", "", "", "", "<inArgs>\n <scannerID>1</scannerID>\n <opcode>5000</opcode>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>" };
/* 171 */     int[] arrayOfInt = { 1, 5000, 5001, 5002, 5004, 5005, 508, 501, 4, 12, 502, 503, 507 };
/*     */     
/* 173 */     for (byte b = 0; b < arrayOfString1.length; b++) {
/* 174 */       DirectIOBinding directIOBinding = new DirectIOBinding(arrayOfString1[b], arrayOfString2[b], arrayOfInt[b]);
/* 175 */       arrayList.add(directIOBinding);
/*     */     } 
/*     */     
/* 178 */     return new DefaultComboBoxModel(arrayList.toArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DefaultComboBoxModel sclDirectIOCommand() {
/* 187 */     ArrayList<DirectIOBinding> arrayList = new ArrayList();
/*     */     
/* 189 */     String[] arrayOfString1 = { "GET_SCANNERS", "RSM_ATTR_GETALL", "RSM_ATTR_GET", "RSM_ATTR_GETNEXT", "RSM_ATTR_SET", "RSM_ATTR_STORE", "NCR_DIO_SCAL_LIVE_WEIGHT", "NCRDIO_SCAL_STATUS", "NCRDIO_SCAL_DIRECT" };
/* 190 */     String[] arrayOfString2 = { "", "<inArgs>\n <scannerID>1</scannerID>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>\n    <attribute>\n      <id>1</id>\n      <datatype>F</datatype>\n      <value>True</value>\n    </attribute>\n   </attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "<inArgs>\n <scannerID>1</scannerID>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>\n    <attribute>\n      <id>1</id>\n      <datatype>F</datatype>\n      <value>True</value>\n    </attribute>\n   </attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>", "", "", "<inArgs>\n <scannerID>1</scannerID>\n <opcode>5000</opcode>\n <cmdArgs>\n  <arg-xml>\n   <attrib_list>1</attrib_list>\n  </arg-xml>\n </cmdArgs>\n</inArgs>" };
/* 191 */     int[] arrayOfInt = { 1, 5000, 5001, 5002, 5004, 5005, 604, 601, 605 };
/*     */     
/* 193 */     for (byte b = 0; b < arrayOfString1.length; b++) {
/* 194 */       DirectIOBinding directIOBinding = new DirectIOBinding(arrayOfString1[b], arrayOfString2[b], arrayOfInt[b]);
/* 195 */       arrayList.add(directIOBinding);
/*     */     } 
/*     */     
/* 198 */     return new DefaultComboBoxModel(arrayList.toArray());
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\DeviceCategorySelection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */