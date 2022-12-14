/*    */ package com.test.welcome;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DirectIOBinding
/*    */ {
/*    */   private final String inXml;
/*    */   private final String command;
/*    */   private final int opCode;
/*    */   
/*    */   public DirectIOBinding(String paramString1, String paramString2, int paramInt) {
/* 33 */     this.inXml = paramString2;
/* 34 */     this.command = paramString1;
/* 35 */     this.opCode = paramInt;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getInXml() {
/* 46 */     return this.inXml;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOpCode() {
/* 56 */     return this.opCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 62 */     return this.command;
/*    */   }
/*    */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\DirectIOBinding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */