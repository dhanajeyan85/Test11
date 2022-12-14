/*     */package com.test.welcome;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ public class DeviceTypeBinder
/*     */ {
/*     */   public String deviceCategory;
/*     */   public CmdOpen openCommand;
/*     */   public CmdClaim claimCommand;
/*     */   public CmdRelease releaseCommand;
/*     */   public CmdClose closeCommand;
/*     */   public CmdDeviceEnable dEnableCommand;
/*     */   public CmdGetDeviceEnable getDeviceEnableCommand;
/*     */   public CmdDataEventEnable dataEventCommand;
/*     */   public CmdFreezeEvents freezeEventsComand;
/*     */   public CmdHealthCheck healthCheckCommand;
/*     */   public CmdHCText healthCheckTextCommand;
/*     */   public CmdClearInput clearInputCommand;
/*     */   public CmdInputOutputC directIOCommand;
/*     */   public CmdAutoDisable autoDisableCommand;
/*     */   public CmdControlVersion controlVersionCommand;
/*     */   public CmdServiceVersion serviceVersionCommand;
/*     */   public CmdControlDescription controlDescriptionCommand;
/*     */   public CmdServiceDescription serviceDescriptionCommand;
/*     */   public CmdPhysicalDeviceDescription physicalDeviceDescriptionCommand;
/*     */   public CmdPhysicalDeviceName physicalDeviceNameCommand;
/*     */   public CmdUpdateStat updateStatCommand;
/*     */   public CmdRetreiveStat retrieveStatCommand;
/*     */   public CmdResetStat resetStatisticsCommand;
/*     */   public CmdPowerNotify powerNotifyCommand;
/*     */   public CmdPowerState powerStateCommand;
/*     */   
/*     */   public String getDevice() {
/* 190 */     return this.deviceCategory;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpen(String paramString) throws JposException {
/* 200 */     this.openCommand.Set(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClaim(int paramInt) throws JposException {
/* 210 */     this.claimCommand.Set(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRelease() throws JposException {
/* 220 */     this.releaseCommand.Set();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClose() throws JposException {
/* 230 */     this.closeCommand.Set();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDeviceEnable(boolean paramBoolean) throws JposException {
/* 241 */     this.dEnableCommand.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDeviceEnable() throws JposException {
/* 251 */     return this.getDeviceEnableCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDataEventEnable(boolean paramBoolean) throws JposException {
/* 262 */     this.dataEventCommand.Set(paramBoolean);
/*     */   }
/*     */   
/*     */   public void setAutoDisable(boolean paramBoolean) throws JposException {
/* 266 */     this.autoDisableCommand.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFreezeEvents(boolean paramBoolean) throws JposException {
/* 277 */     this.freezeEventsComand.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHealthCheck(int paramInt) throws JposException {
/* 287 */     this.healthCheckCommand.Set(paramInt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHealthCheckText() throws JposException {
/* 297 */     return this.healthCheckTextCommand.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClearInput() throws JposException {
/* 306 */     this.clearInputCommand.Set();
/*     */   }
/*     */   
/*     */   public int getControlVersion() throws JposException {
/* 310 */     return this.controlVersionCommand.Get();
/*     */   }
/*     */   
/*     */   public int getServiceVersion() throws JposException {
/* 314 */     return this.serviceVersionCommand.Get();
/*     */   }
/*     */   
/*     */   public String getControlDescription() throws JposException {
/* 318 */     return this.controlDescriptionCommand.Get();
/*     */   }
/*     */   
/*     */   public String getServiceDescription() throws JposException {
/* 322 */     return this.serviceDescriptionCommand.Get();
/*     */   }
/*     */   
/*     */   public String getPhysicalDeviceName() throws JposException {
/* 326 */     return this.physicalDeviceNameCommand.Get();
/*     */   }
/*     */   
/*     */   public String getPhysicalDeviceDescription() throws JposException {
/* 330 */     return this.physicalDeviceDescriptionCommand.Get();
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
/*     */   public void setDirectIO(int paramInt, int[] paramArrayOfint, Object paramObject) throws JposException {
/* 342 */     this.directIOCommand.Set(paramInt, paramArrayOfint, paramObject);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void retrieveStatistics(String[] paramArrayOfString) throws JposException {
/* 352 */     this.retrieveStatCommand.Get(paramArrayOfString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetStatistics(String paramString) throws JposException {
/* 362 */     this.resetStatisticsCommand.Set(paramString);
/*     */   }
/*     */   
/*     */   public void setPowerNotify(int paramInt) throws JposException {
/* 366 */     this.powerNotifyCommand.Set(paramInt);
/*     */   }
/*     */   
/*     */   public int getPowerState() throws JposException {
/* 370 */     return this.powerStateCommand.Get();
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\DeviceTypeBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */