/*     */ package com.test.welcome;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarcodeType
/*     */ {
/*     */   public static String getBarcodeTypeName(int paramInt) {
/*  19 */     String str = "Unknown";
/*  20 */     switch (paramInt) {
/*     */       case 101:
/*  22 */         str = "UPC-A";
/*     */         break;
/*     */       case 102:
/*  25 */         str = " UPC-E";
/*     */         break;
/*     */       case 103:
/*  28 */         str = "JAN 8 / EAN 8";
/*     */         break;
/*     */       case 104:
/*  31 */         str = "JAN 13 / EAN 13";
/*     */         break;
/*     */       case 105:
/*  34 */         str = "2 of 5";
/*     */         break;
/*     */       case 106:
/*  37 */         str = "Interleaved 2 of 5";
/*     */         break;
/*     */       case 107:
/*  40 */         str = "Codabar";
/*     */         break;
/*     */       case 108:
/*  43 */         str = "Code 39";
/*     */         break;
/*     */       case 109:
/*  46 */         str = "Code 93";
/*     */         break;
/*     */       case 110:
/*  49 */         str = "Code 128";
/*     */         break;
/*     */       case 111:
/*  52 */         str = " UPC-A with Supplemental";
/*     */         break;
/*     */       case 112:
/*  55 */         str = "UPC-E with Supplemental";
/*     */         break;
/*     */       case 113:
/*  58 */         str = "UPC-D1";
/*     */         break;
/*     */       case 114:
/*  61 */         str = "UPC-D2";
/*     */         break;
/*     */       case 115:
/*  64 */         str = "UPC-D3";
/*     */         break;
/*     */       case 116:
/*  67 */         str = "UPC-D4";
/*     */         break;
/*     */       case 117:
/*  70 */         str = "UPC-D5";
/*     */         break;
/*     */       case 118:
/*  73 */         str = "EAN-8 with Supplemental";
/*     */         break;
/*     */       case 119:
/*  76 */         str = "EAN-13 with Supplemental";
/*     */         break;
/*     */       case 120:
/*  79 */         str = "EAN-128";
/*     */         break;
/*     */       case 121:
/*  82 */         str = "OCR \"A\"";
/*     */         break;
/*     */       case 122:
/*  85 */         str = "OCR \"B\"";
/*     */         break;
/*     */       case 201:
/*  88 */         str = "PDF 417";
/*     */         break;
/*     */       case 202:
/*  91 */         str = "MAXICODE";
/*     */         break;
/*     */       case 132:
/*  94 */         str = "GS1 Databar Expanded";
/*     */         break;
/*     */       case 206:
/*  97 */         str = "AZTEC";
/*     */         break;
/*     */       case 203:
/* 100 */         str = "Data Matrix";
/*     */         break;
/*     */       
/*     */       case 204:
/* 104 */         str = "QR Code";
/*     */         break;
/*     */       
/*     */       case 205:
/* 108 */         str = "Micro QR Code";
/*     */         break;
/*     */       
/*     */       case 207:
/* 112 */         str = "Micro PDF417";
/*     */         break;
/*     */       
/*     */       case 136:
/* 116 */         str = "Matrix 2 of 5";
/*     */         break;
/*     */       
/*     */       case 311:
/* 120 */         str = "US Planet";
/*     */         break;
/*     */       
/*     */       case 140:
/* 124 */         str = "Trioptic Code 39";
/*     */         break;
/*     */       
/*     */       case 141:
/* 128 */         str = "ISBT 128";
/*     */         break;
/*     */       
/*     */       case 142:
/* 132 */         str = "Code 11";
/*     */         break;
/*     */       
/*     */       case 143:
/* 136 */         str = "MSI";
/*     */         break;
/*     */       
/*     */       case 208:
/* 140 */         str = "GS1 DataMatrix";
/*     */         break;
/*     */       
/*     */       case 209:
/* 144 */         str = "GS1 QR Code";
/*     */         break;
/*     */       
/*     */       case 304:
/* 148 */         str = "Dutch Postal";
/*     */         break;
/*     */       
/*     */       case 306:
/* 152 */         str = "Japan Postal";
/*     */         break;
/*     */       
/*     */       case 301:
/* 156 */         str = "Australian Postal";
/*     */         break;
/*     */       
/*     */       case 309:
/* 160 */         str = "UK Postal";
/*     */         break;
/*     */       
/*     */       case 144:
/* 164 */         str = "Plessey Code";
/*     */         break;
/*     */       
/*     */       case 131:
/* 168 */         str = "GS1 Databar";
/*     */         break;
/*     */       
/*     */       case 312:
/* 172 */         str = "US Postnet";
/*     */         break;
/*     */       
/*     */       case 210:
/* 176 */         str = "Code 49";
/*     */         break;
/*     */       
/*     */       case 0:
/* 180 */         str = "Unknown";
/*     */         break;
/*     */       
/*     */       case 501:
/* 184 */         str = "Other";
/*     */         break;
/*     */     } 
/* 187 */     return str;
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\BarcodeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */