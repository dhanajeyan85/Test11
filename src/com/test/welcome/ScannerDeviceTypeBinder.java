/*     */ package com.test.welcome;
/*     */ 
/*     */ import jpos.JposException;
/*     */ import jpos.Scanner;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ScannerDeviceTypeBinder
/*     */   extends DeviceTypeBinder
/*     */ {
/*     */   private final CmdDecodeData decodeDataCommand;
/*     */   private final CmdClearInputProperties clearInputPropertiesCommand;
/*     */   private final CmdScanData scanDataCommand;
/*     */   private final CmdScanDataLabel scanDataLabelCommand;
/*     */   private final CmdScanDataType scanDataTypeCommand;
/*     */   private final CmdDataCount dataCountCommand;
/*     */   
/*     */   public ScannerDeviceTypeBinder(Scanner paramScanner) {
/*  64 */     this.deviceCategory = "Scanner";
/*  65 */     this.openCommand = paramScanner::open;
/*  66 */     this.claimCommand = paramScanner::claim;
/*  67 */     this.releaseCommand = paramScanner::release;
/*  68 */     this.closeCommand = paramScanner::close;
/*  69 */     this.dEnableCommand = paramScanner::setDeviceEnabled;
/*  70 */     this.getDeviceEnableCommand = paramScanner::getDeviceEnabled;
/*  71 */     this.dataEventCommand = paramScanner::setDataEventEnabled;
/*  72 */     this.autoDisableCommand = paramScanner::setAutoDisable;
/*  73 */     this.freezeEventsComand = paramScanner::setFreezeEvents;
/*  74 */     this.healthCheckCommand = paramScanner::checkHealth;
/*  75 */     this.healthCheckTextCommand = paramScanner::getCheckHealthText;
/*  76 */     this.decodeDataCommand = paramScanner::setDecodeData;
/*  77 */     this.clearInputPropertiesCommand = paramScanner::clearInputProperties;
/*  78 */     this.scanDataCommand = paramScanner::getScanData;
/*  79 */     this.scanDataLabelCommand = paramScanner::getScanDataLabel;
/*  80 */     this.scanDataTypeCommand = paramScanner::getScanDataType;
/*  81 */     this.dataCountCommand = paramScanner::getDataCount;
/*  82 */     this.directIOCommand = paramScanner::directIO;
/*  83 */     this.clearInputCommand = paramScanner::clearInput;
/*     */     
/*  85 */     this.controlVersionCommand = paramScanner::getDeviceControlVersion;
/*  86 */     this.serviceVersionCommand = paramScanner::getDeviceServiceVersion;
/*  87 */     this.controlDescriptionCommand = paramScanner::getDeviceControlDescription;
/*  88 */     this.serviceDescriptionCommand = paramScanner::getDeviceServiceDescription;
/*  89 */     this.physicalDeviceDescriptionCommand = paramScanner::getPhysicalDeviceDescription;
/*  90 */     this.physicalDeviceNameCommand = paramScanner::getPhysicalDeviceName;
/*     */ 
/*     */     
/*  93 */     this.updateStatCommand = paramScanner::updateStatistics;
/*  94 */     this.retrieveStatCommand = paramScanner::retrieveStatistics;
/*  95 */     this.resetStatisticsCommand = paramScanner::resetStatistics;
/*  96 */     this.powerNotifyCommand = paramScanner::setPowerNotify;
/*  97 */     this.powerStateCommand = paramScanner::getPowerState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecodeData(boolean paramBoolean) throws JposException {
/* 109 */     this.decodeDataCommand.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearInputProperties() throws JposException {
/* 119 */     this.clearInputPropertiesCommand.Set();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getScanData() throws JposException {
/* 130 */     return this.scanDataCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getScanDataLabel() throws JposException {
/* 140 */     return this.scanDataLabelCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getScanDataType() throws JposException {
/* 151 */     return this.scanDataTypeCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDataCount() throws JposException {
/* 161 */     return this.dataCountCommand.Get();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 166 */     return "Scanner";
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\ScannerDeviceTypeBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */