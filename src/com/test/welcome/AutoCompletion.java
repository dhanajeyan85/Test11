/*     */ package com.test.welcome;
/*     */ 
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.FocusAdapter;
/*     */ import java.awt.event.FocusEvent;
/*     */ import java.awt.event.FocusListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.beans.PropertyChangeEvent;
/*     */ import java.beans.PropertyChangeListener;
/*     */ import javax.swing.ComboBoxEditor;
/*     */ import javax.swing.ComboBoxModel;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.text.AttributeSet;
/*     */ import javax.swing.text.BadLocationException;
/*     */ import javax.swing.text.JTextComponent;
/*     */ import javax.swing.text.PlainDocument;
/*     */ 
/*     */ public class AutoCompletion extends PlainDocument {
/*     */   JComboBox comboBox;
/*     */   ComboBoxModel model;
/*     */   JTextComponent editor;
/*     */   boolean selecting = false;
/*     */   
/*     */   public AutoCompletion(final JComboBox comboBox) {
/*  29 */     this.comboBox = comboBox;
/*  30 */     this.model = comboBox.getModel();
/*  31 */     comboBox.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent param1ActionEvent) {
/*  34 */             if (!AutoCompletion.this.selecting) {
/*  35 */               AutoCompletion.this.highlightCompletedText(0);
/*     */             }
/*     */           }
/*     */         });
/*  39 */     comboBox.addPropertyChangeListener(new PropertyChangeListener()
/*     */         {
/*     */           public void propertyChange(PropertyChangeEvent param1PropertyChangeEvent) {
/*  42 */             if (param1PropertyChangeEvent.getPropertyName().equals("editor")) {
/*  43 */               AutoCompletion.this.configureEditor((ComboBoxEditor)param1PropertyChangeEvent.getNewValue());
/*     */             }
/*  45 */             if (param1PropertyChangeEvent.getPropertyName().equals("model")) {
/*  46 */               AutoCompletion.this.model = (ComboBoxModel)param1PropertyChangeEvent.getNewValue();
/*     */             }
/*     */           }
/*     */         });
/*  50 */     this.editorKeyListener = new KeyAdapter()
/*     */       {
/*     */         public void keyPressed(KeyEvent param1KeyEvent) {
/*  53 */           if (comboBox.isDisplayable()) {
/*  54 */             comboBox.setPopupVisible(true);
/*     */           }
/*  56 */           AutoCompletion.this.hitBackspace = false;
/*  57 */           switch (param1KeyEvent.getKeyCode()) {
/*     */             
/*     */             case 8:
/*  60 */               AutoCompletion.this.hitBackspace = true;
/*  61 */               AutoCompletion.this.hitBackspaceOnSelection = (AutoCompletion.this.editor.getSelectionStart() != AutoCompletion.this.editor.getSelectionEnd());
/*     */               break;
/*     */             
/*     */             case 127:
/*  65 */               param1KeyEvent.consume();
/*  66 */               comboBox.getToolkit().beep();
/*     */               break;
/*     */           } 
/*     */         
/*     */         }
/*     */       };
/*  72 */     this.hidePopupOnFocusLoss = System.getProperty("java.version").startsWith("1.5");
/*     */     
/*  74 */     this.editorFocusListener = new FocusAdapter()
/*     */       {
/*     */         public void focusGained(FocusEvent param1FocusEvent) {
/*  77 */           AutoCompletion.this.highlightCompletedText(0);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public void focusLost(FocusEvent param1FocusEvent) {
/*  83 */           if (AutoCompletion.this.hidePopupOnFocusLoss) {
/*  84 */             comboBox.setPopupVisible(false);
/*     */           }
/*     */         }
/*     */       };
/*  88 */     configureEditor(comboBox.getEditor());
/*     */     
/*  90 */     Object object = comboBox.getSelectedItem();
/*  91 */     if (object != null) {
/*  92 */       setText(object.toString());
/*     */     }
/*  94 */     highlightCompletedText(0);
/*     */   }
/*     */   boolean hidePopupOnFocusLoss; boolean hitBackspace = false; boolean hitBackspaceOnSelection; KeyListener editorKeyListener; FocusListener editorFocusListener;
/*     */   
/*     */   public static void enable(JComboBox paramJComboBox) {
/*  99 */     paramJComboBox.setEditable(true);
/*     */     
/* 101 */     new AutoCompletion(paramJComboBox);
/*     */   }
/*     */   
/*     */   void configureEditor(ComboBoxEditor paramComboBoxEditor) {
/* 105 */     if (this.editor != null) {
/* 106 */       this.editor.removeKeyListener(this.editorKeyListener);
/* 107 */       this.editor.removeFocusListener(this.editorFocusListener);
/*     */     } 
/*     */     
/* 110 */     if (paramComboBoxEditor != null) {
/* 111 */       this.editor = (JTextComponent)paramComboBoxEditor.getEditorComponent();
/* 112 */       this.editor.addKeyListener(this.editorKeyListener);
/* 113 */       this.editor.addFocusListener(this.editorFocusListener);
/* 114 */       this.editor.setDocument(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int paramInt1, int paramInt2) throws BadLocationException {
/* 121 */     if (this.selecting) {
/*     */       return;
/*     */     }
/* 124 */     if (this.hitBackspace) {
/*     */ 
/*     */       
/* 127 */       if (paramInt1 > 0) {
/* 128 */         if (this.hitBackspaceOnSelection) {
/* 129 */           paramInt1--;
/*     */         }
/*     */       } else {
/*     */         
/* 133 */         this.comboBox.getToolkit().beep();
/*     */       } 
/* 135 */       highlightCompletedText(paramInt1);
/*     */     } else {
/* 137 */       super.remove(paramInt1, paramInt2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertString(int paramInt, String paramString, AttributeSet paramAttributeSet) throws BadLocationException {
/* 144 */     if (this.selecting) {
/*     */       return;
/*     */     }
/*     */     
/* 148 */     super.insertString(paramInt, paramString, paramAttributeSet);
/*     */     
/* 150 */     Object object = lookupItem(getText(0, getLength()));
/* 151 */     if (object != null) {
/* 152 */       setSelectedItem(object);
/*     */     } else {
/*     */       
/* 155 */       object = this.comboBox.getSelectedItem();
/*     */       
/* 157 */       paramInt -= paramString.length();
/*     */       
/* 159 */       this.comboBox.getToolkit().beep();
/*     */     } 
/* 161 */     setText(object.toString());
/*     */     
/* 163 */     highlightCompletedText(paramInt + paramString.length());
/*     */   }
/*     */ 
/*     */   
/*     */   private void setText(String paramString) {
/*     */     try {
/* 169 */       super.remove(0, getLength());
/* 170 */       super.insertString(0, paramString, (AttributeSet)null);
/* 171 */     } catch (BadLocationException badLocationException) {
/* 172 */       throw new RuntimeException(badLocationException.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   private void highlightCompletedText(int paramInt) {
/* 177 */     this.editor.setCaretPosition(getLength());
/* 178 */     this.editor.moveCaretPosition(paramInt);
/*     */   }
/*     */   
/*     */   private void setSelectedItem(Object paramObject) {
/* 182 */     this.selecting = true;
/* 183 */     this.model.setSelectedItem(paramObject);
/* 184 */     this.selecting = false;
/*     */   }
/*     */   
/*     */   private Object lookupItem(String paramString) {
/* 188 */     Object object = this.model.getSelectedItem();
/*     */     
/* 190 */     if (object != null && startsWithIgnoreCase(object.toString(), paramString))
/* 191 */       return object; 
/*     */     byte b;
/*     */     int i;
/* 194 */     for (b = 0, i = this.model.getSize(); b < i; b++) {
/* 195 */       Object object1 = this.model.getElementAt(b);
/*     */       
/* 197 */       if (object1 != null && startsWithIgnoreCase(object1.toString(), paramString)) {
/* 198 */         return object1;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 203 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean startsWithIgnoreCase(String paramString1, String paramString2) {
/* 208 */     return paramString1.toUpperCase().startsWith(paramString2.toUpperCase());
/*     */   }
/*     */ 
/*     */   
/*     */   private static void createAndShowGUI() {
/* 213 */     JComboBox jComboBox = new JComboBox(new Object[] { "Ester", "Jordi", "Jordina", "Jorge", "Sergi" });
/* 214 */     enable(jComboBox);
/*     */ 
/*     */     
/* 217 */     JFrame jFrame = new JFrame();
/* 218 */     jFrame.setDefaultCloseOperation(3);
/* 219 */     jFrame.getContentPane().add(jComboBox);
/* 220 */     jFrame.pack();
/* 221 */     jFrame.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\AutoCompletion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */