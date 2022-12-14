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
/*     */ public class PropertyBinder
/*     */ {
/*     */   public final String type;
/*     */   public final String propertyName;
/*     */   public final boolean editable;
/*     */   private ReadableString stringGetter;
/*     */   private ReadableBoolean booleanGetter;
/*     */   private ReadableByte byteGetter;
/*     */   private ReadableLong longGetter;
/*     */   private ReadableInt intGetter;
/*     */   private WritableString stringSetter;
/*     */   private WritableBoolean booleanSetter;
/*     */   private WritableByte byteSetter;
/*     */   private WritableLong longSetter;
/*     */   private WritableInt intSetter;
/*     */   
/*     */   public PropertyBinder(String paramString1, String paramString2, ReadableInt paramReadableInt, WritableInt paramWritableInt, boolean paramBoolean) {
/* 101 */     this.propertyName = paramString1;
/* 102 */     this.intGetter = paramReadableInt;
/* 103 */     this.intSetter = paramWritableInt;
/* 104 */     this.editable = paramBoolean;
/* 105 */     this.type = paramString2;
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
/*     */   public PropertyBinder(String paramString1, String paramString2, ReadableString paramReadableString, WritableString paramWritableString, boolean paramBoolean) {
/* 119 */     this.propertyName = paramString1;
/* 120 */     this.stringGetter = paramReadableString;
/* 121 */     this.stringSetter = paramWritableString;
/* 122 */     this.editable = paramBoolean;
/* 123 */     this.type = paramString2;
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
/*     */   public PropertyBinder(String paramString1, String paramString2, ReadableBoolean paramReadableBoolean, WritableBoolean paramWritableBoolean, boolean paramBoolean) {
/* 137 */     this.propertyName = paramString1;
/* 138 */     this.booleanGetter = paramReadableBoolean;
/* 139 */     this.booleanSetter = paramWritableBoolean;
/* 140 */     this.editable = paramBoolean;
/* 141 */     this.type = paramString2;
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
/*     */   public PropertyBinder(String paramString1, String paramString2, ReadableByte paramReadableByte, WritableByte paramWritableByte, boolean paramBoolean) {
/* 155 */     this.propertyName = paramString1;
/* 156 */     this.byteSetter = paramWritableByte;
/* 157 */     this.byteGetter = paramReadableByte;
/* 158 */     this.editable = paramBoolean;
/* 159 */     this.type = paramString2;
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
/*     */   public PropertyBinder(String paramString1, ReadableLong paramReadableLong, WritableLong paramWritableLong, String paramString2, boolean paramBoolean) {
/* 173 */     this.propertyName = paramString1;
/* 174 */     this.type = paramString2;
/* 175 */     this.longGetter = paramReadableLong;
/* 176 */     this.longSetter = paramWritableLong;
/* 177 */     this.editable = paramBoolean;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getType() {
/* 188 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPropertyName() {
/* 197 */     return this.propertyName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEditable() {
/* 208 */     return this.editable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getString() throws JposException {
/* 219 */     return this.stringGetter.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getBoolean() throws JposException {
/* 230 */     return this.booleanGetter.Get().booleanValue();
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
/*     */   public byte[] getByte() throws JposException {
/* 242 */     return this.byteGetter.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getLong() throws JposException {
/* 253 */     return this.longGetter.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getInt() throws JposException {
/* 264 */     return this.intGetter.Get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBoolean(boolean paramBoolean) throws JposException {
/* 275 */     this.booleanSetter.Set(paramBoolean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLong(long paramLong) throws JposException {
/* 286 */     this.longSetter.Set(paramLong);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInt(int paramInt) throws JposException {
/* 297 */     this.intSetter.Set(paramInt);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 302 */     return this.propertyName;
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\PropertyBinder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */