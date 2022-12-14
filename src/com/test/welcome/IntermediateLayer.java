/*     */package com.test.welcome;
/*     */ 
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.swing.JOptionPane;
/*     */ import jpos.JposException;
/*     */ import jpos.events.StatusUpdateEvent;
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
/*     */ public class IntermediateLayer
/*     */ {
/*     */   public String exceptionDialog(JposException paramJposException, String paramString) {
/*  29 */     String str = "Exception: " + paramJposException.getMessage() + "  Error: " + paramJposException.getErrorCode() + " ExtError: " + paramJposException.getErrorCodeExtended();
/*  30 */     JOptionPane.showMessageDialog(null, paramString + paramJposException.getMessage(), "Failed", 0);
/*  31 */     return str;
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
/*     */   public String[] openAction(DeviceTypeBinder paramDeviceTypeBinder) {
/*  43 */     String[] arrayOfString = new String[2];
/*     */     try {
/*  45 */       paramDeviceTypeBinder.setOpen(JposSampleApp.logicalName);
/*  46 */       arrayOfString[0] = "Device Opened";
/*  47 */     } catch (JposException jposException) {
/*  48 */       arrayOfString[0] = "Device Not Opened";
/*  49 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to open \"" + JposSampleApp.logicalName + "\"\nException: ");
/*     */     } 
/*  51 */     return arrayOfString;
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
/*     */   public String[] claimAction(DeviceTypeBinder paramDeviceTypeBinder, int paramInt) {
/*  63 */     String[] arrayOfString = new String[2];
/*     */     try {
/*  65 */       paramDeviceTypeBinder.setClaim(paramInt);
/*  66 */       arrayOfString[0] = "Device Claimed";
/*  67 */     } catch (JposException jposException) {
/*  68 */       arrayOfString[0] = "Device Not Claimed";
/*  69 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to claim \"" + JposSampleApp.logicalName + "\"\nException: ");
/*     */     } 
/*  71 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] releaseAction(DeviceTypeBinder paramDeviceTypeBinder) {
/*  82 */     String[] arrayOfString = new String[2];
/*     */     try {
/*  84 */       paramDeviceTypeBinder.setRelease();
/*  85 */       arrayOfString[0] = "Device Released";
/*  86 */     } catch (JposException jposException) {
/*  87 */       arrayOfString[0] = "Device Not Released";
/*  88 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to release \"" + JposSampleApp.logicalName + "\"\nException: ");
/*     */     } 
/*  90 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] closeAction(DeviceTypeBinder paramDeviceTypeBinder) {
/* 101 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 103 */       paramDeviceTypeBinder.setClose();
/* 104 */       arrayOfString[0] = "Device Closed";
/* 105 */     } catch (JposException jposException) {
/* 106 */       arrayOfString[0] = "Device Unable to Close";
/* 107 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to close \"" + JposSampleApp.logicalName + "\"\nException: ");
/*     */     } 
/* 109 */     return arrayOfString;
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
/*     */   
/*     */   public String[] deviceEnableAction(DeviceTypeBinder paramDeviceTypeBinder, boolean paramBoolean) {
/* 122 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 124 */       paramDeviceTypeBinder.setDeviceEnable(paramBoolean);
/* 125 */       if (paramBoolean) {
/* 126 */         arrayOfString[0] = "Device Enabled";
/* 127 */         JposSampleApp.deviceEnableC = true;
/*     */       } else {
/* 129 */         arrayOfString[0] = "Device Disabled";
/* 130 */         JposSampleApp.deviceEnableC = false;
/*     */       } 
/* 132 */     } catch (JposException jposException) {
/* 133 */       JposSampleApp.deviceEnableC = !paramBoolean;
/* 134 */       arrayOfString[0] = "Device Disabled";
/* 135 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable device \nException: ");
/*     */     } 
/* 137 */     return arrayOfString;
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
/*     */   
/*     */   public String[] dataEventEnableAction(DeviceTypeBinder paramDeviceTypeBinder, boolean paramBoolean) {
/* 150 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 152 */       paramDeviceTypeBinder.setDataEventEnable(paramBoolean);
/* 153 */       if (paramBoolean) {
/* 154 */         arrayOfString[0] = "Data Event Enabled";
/* 155 */         JposSampleApp.dataEventEnableC = true;
/*     */       } else {
/* 157 */         arrayOfString[0] = "Data Event Disabled";
/* 158 */         JposSampleApp.dataEventEnableC = false;
/*     */       } 
/* 160 */     } catch (JposException jposException) {
/* 161 */       JposSampleApp.dataEventEnableC = !paramBoolean;
/* 162 */       arrayOfString[0] = "Data Event Disabled";
/* 163 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable data event \nException: ");
/*     */     } 
/* 165 */     return arrayOfString;
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
/*     */   
/*     */   public String[] autoDisable(DeviceTypeBinder paramDeviceTypeBinder, boolean paramBoolean) {
/* 178 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 180 */       paramDeviceTypeBinder.setAutoDisable(paramBoolean);
/* 181 */       if (paramBoolean) {
/* 182 */         arrayOfString[0] = "Auto Disable is true";
/* 183 */         JposSampleApp.autoDisableC = true;
/*     */       } else {
/* 185 */         arrayOfString[0] = "Auto Disable is False";
/* 186 */         JposSampleApp.autoDisableC = false;
/*     */       } 
/* 188 */     } catch (JposException jposException) {
/* 189 */       JposSampleApp.autoDisableC = !paramBoolean;
/* 190 */       arrayOfString[0] = "Auto Disable is False";
/* 191 */       arrayOfString[1] = exceptionDialog(jposException, "Failed in auto device enable \nException: ");
/*     */     } 
/* 193 */     return arrayOfString;
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
/*     */   
/*     */   public String[] freezeEventsAction(DeviceTypeBinder paramDeviceTypeBinder, boolean paramBoolean) {
/* 206 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 208 */       paramDeviceTypeBinder.setFreezeEvents(paramBoolean);
/* 209 */       if (paramBoolean) {
/* 210 */         arrayOfString[0] = "Freeze Events Enabled";
/* 211 */         JposSampleApp.freezeEventsC = true;
/*     */       } else {
/* 213 */         arrayOfString[0] = "Freeze Events Disabled";
/* 214 */         JposSampleApp.freezeEventsC = false;
/*     */       } 
/* 216 */     } catch (JposException jposException) {
/* 217 */       JposSampleApp.freezeEventsC = !paramBoolean;
/* 218 */       arrayOfString[0] = "Freeze Events Disabled";
/* 219 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable freeze events \nException: ");
/*     */     } 
/* 221 */     return arrayOfString;
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
/*     */   
/*     */   public String[] decodeDataAction(ScannerDeviceTypeBinder paramScannerDeviceTypeBinder, boolean paramBoolean) {
/* 234 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 236 */       paramScannerDeviceTypeBinder.setDecodeData(paramBoolean);
/* 237 */       if (paramBoolean) {
/* 238 */         arrayOfString[0] = "Decode Data Enabled";
/* 239 */         JposSampleApp.decodeDataEnableC = true;
/*     */       } else {
/* 241 */         arrayOfString[0] = "Decode Data Disabled";
/* 242 */         JposSampleApp.decodeDataEnableC = false;
/*     */       } 
/* 244 */     } catch (JposException jposException) {
/* 245 */       JposSampleApp.decodeDataEnableC = false;
/* 246 */       arrayOfString[0] = "Decode Data Disabled";
/* 247 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable decode data \nException: ");
/*     */     } 
/* 249 */     return arrayOfString;
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
/*     */   
/*     */   public String[] checkHealthAction(DeviceTypeBinder paramDeviceTypeBinder, int paramInt) {
/* 262 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 264 */       paramDeviceTypeBinder.setHealthCheck(paramInt);
/* 265 */       arrayOfString[0] = "Health Check Enabled";
/* 266 */     } catch (JposException jposException) {
/* 267 */       arrayOfString[0] = "Health Check Disabled";
/* 268 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable health check \nException: ");
/*     */     } 
/* 270 */     return arrayOfString;
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
/*     */   public String[] checkHealthTextAction(DeviceTypeBinder paramDeviceTypeBinder) {
/* 282 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 284 */       JposSampleApp.healthCheckText = paramDeviceTypeBinder.getHealthCheckText();
/* 285 */       arrayOfString[0] = "Health Check Text Enabled";
/* 286 */     } catch (JposException jposException) {
/* 287 */       arrayOfString[0] = "Health Check Text Disabled";
/* 288 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable health check text \nException: ");
/*     */     } 
/* 290 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] fastModeScannerAction(ScannerDeviceTypeBinder paramScannerDeviceTypeBinder) {
/* 301 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 303 */       paramScannerDeviceTypeBinder.setOpen(JposSampleApp.logicalName);
/* 304 */       paramScannerDeviceTypeBinder.setClaim(5000);
/* 305 */       paramScannerDeviceTypeBinder.setDeviceEnable(true);
/* 306 */       paramScannerDeviceTypeBinder.setDataEventEnable(true);
/* 307 */       paramScannerDeviceTypeBinder.setDecodeData(true);
/* 308 */       arrayOfString[0] = "Fast Mode Enabled";
/* 309 */     } catch (JposException jposException) {
/* 310 */       JposSampleApp.fastModeScannerC = false;
/* 311 */       arrayOfString[0] = "Fast Mode Disable";
/* 312 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable fast mode \nException: ");
/*     */     } 
/* 314 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] fastModeScaleAction(DeviceTypeBinder paramDeviceTypeBinder) {
/* 325 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 327 */       paramDeviceTypeBinder.setOpen(JposSampleApp.logicalName);
/* 328 */       paramDeviceTypeBinder.setClaim(5000);
/* 329 */       paramDeviceTypeBinder.setDeviceEnable(true);
/*     */       
/* 331 */       arrayOfString[0] = "Fast Mode Enabled";
/* 332 */     } catch (JposException jposException) {
/* 333 */       JposSampleApp.fastModeScaleC = false;
/* 334 */       arrayOfString[0] = "Fast Mode Disabled";
/* 335 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable fast mode \nException: ");
/*     */     } 
/* 337 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] clearInputPropertiesAction(ScannerDeviceTypeBinder paramScannerDeviceTypeBinder) {
/* 348 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 350 */       paramScannerDeviceTypeBinder.setClearInputProperties();
/* 351 */       arrayOfString[0] = "Set clear Input Properties true";
/* 352 */     } catch (JposException jposException) {
/* 353 */       arrayOfString[0] = "Set clear Input Properties false";
/* 354 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to clear input properties \nException: ");
/*     */     } 
/* 356 */     return arrayOfString;
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
/*     */   public String[] asyncModeAction(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder, boolean paramBoolean) {
/* 368 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 370 */       paramScaleDeviceTypeBinder.setAsyncMode(paramBoolean);
/* 371 */       if (paramBoolean) {
/* 372 */         arrayOfString[0] = "Async Mode Enabled";
/* 373 */         JposSampleApp.asyncModeC = true;
/*     */       } else {
/* 375 */         arrayOfString[0] = "Async Mode Disabled";
/* 376 */         JposSampleApp.asyncModeC = false;
/*     */       } 
/* 378 */     } catch (JposException jposException) {
/* 379 */       JposSampleApp.asyncModeC = false;
/* 380 */       arrayOfString[0] = "Async Mode Disabled";
/* 381 */       arrayOfString[1] = exceptionDialog(jposException, "Jpos exception in AsyncMode ");
/*     */     } 
/* 383 */     return arrayOfString;
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
/*     */   
/*     */   public String[] statusNotifyAction(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder, int paramInt) {
/* 396 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 398 */       paramScaleDeviceTypeBinder.setStatusNotify(paramInt);
/* 399 */       if (paramInt == 2) {
/* 400 */         arrayOfString[0] = "Live Weight Enabled";
/* 401 */         JposSampleApp.statusNotifyC = true;
/*     */       } else {
/* 403 */         arrayOfString[0] = "Live Weight Disabled";
/* 404 */         JposSampleApp.statusNotifyC = false;
/*     */       } 
/* 406 */     } catch (JposException jposException) {
/* 407 */       arrayOfString[0] = "Live Weight Disabled";
/* 408 */       JposSampleApp.statusNotifyC = false;
/* 409 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to enable live weight \nException: ");
/*     */     } 
/* 411 */     return arrayOfString;
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
/*     */   public String[] readWeightAction(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder, int paramInt) {
/* 423 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 425 */       JposSampleApp.readWeight = new int[1];
/* 426 */       JposSampleApp.fWeight = 0.0F;
/* 427 */       paramScaleDeviceTypeBinder.getReadWeight(JposSampleApp.readWeight, paramInt);
/* 428 */       JposSampleApp.fWeight = JposSampleApp.readWeight[0] / 1000.0F;
/* 429 */       JposSampleApp.units = getUnit(paramScaleDeviceTypeBinder);
/*     */       
/* 431 */       arrayOfString[0] = "Read Weight Performed";
/* 432 */     } catch (JposException jposException) {
/* 433 */       arrayOfString[0] = "Read Weight Unsuccessful";
/* 434 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to read weight \"" + JposSampleApp.logicalName + "\"\nException: ");
/*     */     } 
/* 436 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] zeroScaleAction(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder) {
/* 447 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 449 */       paramScaleDeviceTypeBinder.setZeroScale();
/* 450 */       arrayOfString[0] = "Zero Scale Performed";
/* 451 */     } catch (JposException jposException) {
/* 452 */       arrayOfString[0] = "Zero Scale Unsuccessful";
/* 453 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to zero \"" + JposSampleApp.logicalName + "\"\nException");
/*     */     } 
/* 455 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] clearInputAction(DeviceTypeBinder paramDeviceTypeBinder) {
/* 466 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 468 */       paramDeviceTypeBinder.setClearInput();
/* 469 */       arrayOfString[0] = "Clear Input Data";
/* 470 */     } catch (JposException jposException) {
/* 471 */       arrayOfString[0] = "No Input Data Cleared";
/* 472 */       arrayOfString[1] = exceptionDialog(jposException, "Exception: ");
/*     */     } 
/* 474 */     return arrayOfString;
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
/*     */   
/*     */   public String[] retrieveStatistics(DeviceTypeBinder paramDeviceTypeBinder, String[] paramArrayOfString) {
/* 487 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 489 */       paramDeviceTypeBinder.retrieveStatistics(paramArrayOfString);
/* 490 */       arrayOfString[0] = "Statistics Retrieved";
/*     */     }
/* 492 */     catch (JposException jposException) {
/* 493 */       arrayOfString[0] = "Statistics Retrieval failed";
/* 494 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to Retrieve Statistics. Exception: ");
/*     */     } 
/* 496 */     return arrayOfString;
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
/*     */   public String[] resetStatistics(DeviceTypeBinder paramDeviceTypeBinder, String paramString) {
/* 508 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 510 */       paramDeviceTypeBinder.resetStatistics(paramString);
/* 511 */       arrayOfString[0] = "Statistics Reset";
/* 512 */     } catch (JposException jposException) {
/* 513 */       arrayOfString[0] = "Statistics Reset failed";
/* 514 */       arrayOfString[1] = exceptionDialog(jposException, "Failed to Reset Statistics. Exception: ");
/*     */     } 
/* 516 */     return arrayOfString;
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
/*     */   
/*     */   public String[] PowerNotify(DeviceTypeBinder paramDeviceTypeBinder, int paramInt) {
/* 529 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 531 */       paramDeviceTypeBinder.setPowerNotify(paramInt);
/* 532 */       if (paramInt == 1) {
/* 533 */         arrayOfString[0] = "Power Notifications Enabled";
/* 534 */         JposSampleApp.powerNotifyC = true;
/*     */       } else {
/* 536 */         arrayOfString[0] = "Power Notifications Disabled";
/* 537 */         JposSampleApp.powerNotifyC = false;
/*     */       } 
/* 539 */     } catch (JposException jposException) {
/* 540 */       arrayOfString[0] = "Command Unsuccessful";
/* 541 */       JposSampleApp.powerNotifyC = (paramInt != 1);
/* 542 */       arrayOfString[1] = exceptionDialog(jposException, "Exception: ");
/*     */     } 
/* 544 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String[] PowerState(DeviceTypeBinder paramDeviceTypeBinder) {
/* 555 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 557 */       JposSampleApp.powerState = paramDeviceTypeBinder.getPowerState();
/* 558 */       switch (JposSampleApp.powerState) {
/*     */         case 2001:
/* 560 */           arrayOfString[0] = "Power State: " + JposSampleApp.powerState + " JPOS_PS_ONLINE";
/*     */           break;
/*     */         
/*     */         case 2004:
/* 564 */           arrayOfString[0] = "Power State: " + JposSampleApp.powerState + " JPOS_PS_OFF_OFFLINE";
/*     */           break;
/*     */         
/*     */         case 2000:
/* 568 */           arrayOfString[0] = "Power State: " + JposSampleApp.powerState + " JPOS_PS_UNKNOWN";
/*     */           break;
/*     */       } 
/* 571 */     } catch (JposException jposException) {
/* 572 */       arrayOfString[0] = "Unable to get Power State";
/* 573 */       arrayOfString[1] = exceptionDialog(jposException, "Exception: ");
/*     */     } 
/* 575 */     return arrayOfString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getDeviceInfo(DeviceTypeBinder paramDeviceTypeBinder) {
/*     */     String str1, str2, str3, str4, str5, str6;
/*     */     try {
/* 595 */       str1 = Integer.toString(paramDeviceTypeBinder.getControlVersion() / 1000);
/* 596 */       str2 = Integer.toString(paramDeviceTypeBinder.getServiceVersion() / 1000);
/* 597 */       str3 = paramDeviceTypeBinder.getControlDescription();
/* 598 */       str4 = paramDeviceTypeBinder.getServiceDescription();
/* 599 */       str5 = paramDeviceTypeBinder.getPhysicalDeviceName();
/* 600 */       str6 = paramDeviceTypeBinder.getPhysicalDeviceDescription();
/*     */     }
/* 602 */     catch (JposException jposException) {
/* 603 */       String str = "" + jposException.getMessage();
/* 604 */       JOptionPane.showMessageDialog(null, "Exception " + jposException, "Failed", 0);
/* 605 */       return str;
/*     */     } 
/* 607 */     String str8 = "Version :\nControl Object : v" + str1.replace("0", ".") + ".0\nService Object : v" + str2.replace("0", ".") + ".0\n\n";
/* 608 */     String str9 = "Physical Device Name :\n" + str5 + "\n\nPhysical Device Description :\n" + str6 + "\n\n\n";
/* 609 */     String str10 = "Device Description :\n\nControl Object Description:\n" + str3 + "\n\nService Object Description:\n" + str4;
/*     */     
/* 611 */     String str7 = str8.concat(str9);
/* 612 */     str7 = str7.concat(str10);
/* 613 */     return str7;
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
/*     */ 
/*     */   
/*     */   public String[] directIOAction(DeviceTypeBinder paramDeviceTypeBinder, int paramInt, int[] paramArrayOfint, Object paramObject) {
/* 627 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 629 */       paramDeviceTypeBinder.setDirectIO(paramInt, paramArrayOfint, paramObject);
/* 630 */       arrayOfString[0] = "Direct IO Successful";
/* 631 */       JposSampleApp.directIOC = true;
/* 632 */       if (paramArrayOfint[0] != 0) {
/* 633 */         JOptionPane.showMessageDialog(null, "Error", "Error performing DirectIO", 0);
/* 634 */         arrayOfString[0] = "Error in performing Direct IO";
/* 635 */         JposSampleApp.directIOC = false;
/*     */       } 
/* 637 */     } catch (JposException jposException) {
/* 638 */       JposSampleApp.directIOC = false;
/* 639 */       arrayOfString[0] = "Error in performing Direct IO";
/* 640 */       arrayOfString[1] = exceptionDialog(jposException, "Exception ");
/*     */     } 
/* 642 */     return arrayOfString;
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
/*     */ 
/*     */   
/*     */   public String[] ncrDirectIOAction(DeviceTypeBinder paramDeviceTypeBinder, int paramInt, int[] paramArrayOfint, Object paramObject) {
/* 656 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 658 */       paramDeviceTypeBinder.setDirectIO(paramInt, paramArrayOfint, paramObject);
/* 659 */       arrayOfString[0] = "Direct IO Successful";
/* 660 */       paramArrayOfint[0] = 0;
/* 661 */       JposSampleApp.directIOC = true;
/*     */     }
/* 663 */     catch (JposException jposException) {
/* 664 */       JposSampleApp.directIOC = false;
/* 665 */       paramArrayOfint[0] = -1;
/* 666 */       arrayOfString[0] = "Error in performing Direct IO";
/* 667 */       arrayOfString[1] = exceptionDialog(jposException, "Exception ");
/*     */     } 
/* 669 */     return arrayOfString;
/*     */   }
/*     */   
/*     */   public String[] ncrDirectIOLiveWeightAction(DeviceTypeBinder paramDeviceTypeBinder, int paramInt, int[] paramArrayOfint, Object paramObject) {
/* 673 */     String[] arrayOfString = new String[2];
/*     */     try {
/* 675 */       paramDeviceTypeBinder.setDirectIO(paramInt, paramArrayOfint, paramObject);
/* 676 */       arrayOfString[0] = "Direct IO Successful";
/* 677 */       JposSampleApp.directIOC = true;
/*     */     }
/* 679 */     catch (JposException jposException) {
/* 680 */       JposSampleApp.directIOC = false;
/* 681 */       arrayOfString[0] = "Error in performing Direct IO";
/* 682 */       arrayOfString[1] = exceptionDialog(jposException, "Exception ");
/*     */     } 
/* 684 */     return arrayOfString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkDeviceEnable(DeviceTypeBinder paramDeviceTypeBinder) {
/* 695 */     boolean bool = true;
/*     */     try {
/* 697 */       bool = paramDeviceTypeBinder.getDeviceEnable();
/* 698 */     } catch (JposException jposException) {
/* 699 */       Logger.getLogger(IntermediateLayer.class.getName()).log(Level.SEVERE, (String)null, (Throwable)jposException);
/*     */     } 
/* 701 */     return bool;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dataListenerEvent(ScannerDeviceTypeBinder paramScannerDeviceTypeBinder) {
/*     */     try {
/* 711 */       JposSampleApp.scanDataLabelText = new String(paramScannerDeviceTypeBinder.getScanDataLabel());
/* 712 */       JposSampleApp.scanDataLabelHex = getHexEncodedDataLabel(paramScannerDeviceTypeBinder.getScanDataLabel());
/* 713 */       JposSampleApp.scanData = new String(paramScannerDeviceTypeBinder.getScanData());
/* 714 */       JposSampleApp.scanDataType = paramScannerDeviceTypeBinder.getScanDataType();
/* 715 */       JposSampleApp.scanDataCount = paramScannerDeviceTypeBinder.getDataCount();
/* 716 */     } catch (JposException jposException) {
/* 717 */       JOptionPane.showMessageDialog(null, "Failed to Scan Data \nException: " + jposException.getMessage(), "Failed", 0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getHexEncodedDataLabel(byte[] paramArrayOfbyte) {
/* 728 */     StringBuilder stringBuilder = new StringBuilder();
/* 729 */     for (byte b : paramArrayOfbyte) {
/* 730 */       stringBuilder.append(String.format("0x%02X ", new Object[] { Integer.valueOf(b & 0xFF) }));
/*     */     } 
/* 732 */     return stringBuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String statusUpdateListenerEvent(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder, StatusUpdateEvent paramStatusUpdateEvent) {
/* 743 */     String str = "";
/*     */     
/*     */     try {
/* 746 */       JposSampleApp.liveWeight = paramScaleDeviceTypeBinder.getLiveWeight();
/* 747 */       if (paramStatusUpdateEvent.getStatus() == 11 || paramStatusUpdateEvent.getStatus() == 15 || paramStatusUpdateEvent
/* 748 */         .getStatus() == 12 || paramStatusUpdateEvent.getStatus() == 13 || paramStatusUpdateEvent
/* 749 */         .getStatus() == 16) {
/* 750 */         str = (JposSampleApp.liveWeight / 1000.0F) + " " + getUnit(paramScaleDeviceTypeBinder);
/*     */       }
/*     */     }
/* 753 */     catch (JposException jposException) {
/* 754 */       JOptionPane.showMessageDialog(null, "Failed to perform Live Weight \nException: " + jposException.getMessage(), "Failed", 0);
/*     */     } 
/* 756 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getUnit(ScaleDeviceTypeBinder paramScaleDeviceTypeBinder) {
/* 767 */     String str = "";
/*     */     try {
/* 769 */       int i = paramScaleDeviceTypeBinder.getWeightUnits();
/*     */       
/* 771 */       switch (i) {
/*     */         case 1:
/* 773 */           str = "g";
/*     */           break;
/*     */         case 2:
/* 776 */           str = "kg";
/*     */           break;
/*     */         case 3:
/* 779 */           str = "oz";
/*     */           break;
/*     */         case 4:
/* 782 */           str = "lb";
/*     */           break;
/*     */       } 
/* 785 */     } catch (JposException jposException) {
/* 786 */       Logger.getLogger(JposSampleApp.class.getName()).log(Level.SEVERE, (String)null, (Throwable)jposException);
/*     */     } 
/* 788 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\IntermediateLayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */