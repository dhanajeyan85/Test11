/*     */ package com.test.welcome;
/*     */ 
/*     */ import jpos.JposException;
/*     */ import jpos.Scale;
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
/*     */ public class ScaleDeviceTypeBinder
/*     */   extends DeviceTypeBinder
/*     */ {
/*     */   private final CmdLiveWeight liveWeightCommand;
/*     */   private final CmdStatusNotify statusNotifyCommand;
/*     */   private final CmdAsyncMode asyncModeCommand;
/*     */   private final CmdWeightUnit weightUnitCommand;
/*     */   private final CmdReadWeight readWeightCommand;
/*     */   private final CmdZeroScale zeroScaleCommand;
/*     */   
/*     */   public ScaleDeviceTypeBinder(Scale paramScale) {
/*  64 */     this.deviceCategory = "Scale  ";
/*  65 */     this.openCommand = paramScale::open;
/*  66 */     this.claimCommand = paramScale::claim;
/*  67 */     this.releaseCommand = paramScale::release;
/*  68 */     this.closeCommand = paramScale::close;
/*  69 */     this.dEnableCommand = paramScale::setDeviceEnabled;
/*  70 */     this.getDeviceEnableCommand = paramScale::getDeviceEnabled;
/*  71 */     this.dataEventCommand = paramScale::setDataEventEnabled;
/*  72 */     this.autoDisableCommand = paramScale::setAutoDisable;
/*  73 */     this.freezeEventsComand = paramScale::setFreezeEvents;
/*  74 */     this.healthCheckCommand = paramScale::checkHealth;
/*  75 */     this.healthCheckTextCommand = paramScale::getCheckHealthText;
/*  76 */     this.liveWeightCommand = paramScale::getScaleLiveWeight;
/*  77 */     this.statusNotifyCommand = paramScale::setStatusNotify;
/*  78 */     this.asyncModeCommand = paramScale::setAsyncMode;
/*  79 */     this.weightUnitCommand = paramScale::getWeightUnit;
/*  80 */     this.readWeightCommand = paramScale::readWeight;
/*  81 */     this.zeroScaleCommand = paramScale::zeroScale;
/*  82 */     this.directIOCommand = paramScale::directIO;
/*  83 */     this.clearInputCommand = paramScale::clearInput;
/*     */     
/*  85 */     this.controlVersionCommand = paramScale::getDeviceControlVersion;
/*  86 */     this.serviceVersionCommand = paramScale::getDeviceServiceVersion;
/*  87 */     this.controlDescriptionCommand = paramScale::getDeviceControlDescription;
/*  88 */     this.serviceDescriptionCommand = paramScale::getDeviceServiceDescription;
/*  89 */     this.physicalDeviceDescriptionCommand = paramScale::getPhysicalDeviceDescription;
/*  90 */     this.physicalDeviceNameCommand = paramScale::getPhysicalDeviceName;
/*     */     
/*  92 */     this.retrieveStatCommand = paramScale::retrieveStatistics;
/*  93 */     this.resetStatisticsCommand = paramScale::resetStatistics;
/*  94 */     this.powerNotifyCommand = paramScale::setPowerNotify;
/*  95 */     this.powerStateCommand = paramScale::getPowerState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLiveWeight() throws JposException {
/* 105 */     return this.liveWeightCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStatusNotify(int paramInt) throws JposException {
/* 115 */     this.statusNotifyCommand.Set(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAsyncMode(boolean paramBoolean) throws JposException {
/* 125 */     this.asyncModeCommand.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWeightUnits() throws JposException {
/* 136 */     return this.weightUnitCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getReadWeight(int[] paramArrayOfint, int paramInt) throws JposException {
/* 147 */     this.readWeightCommand.Get(paramArrayOfint, paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZeroScale() throws JposException {
/* 156 */     this.zeroScaleCommand.Set();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 161 */     return "Scale";
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\ScaleDeviceTypeBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */