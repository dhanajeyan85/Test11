package com.test.welcome;
import jpos.JposException;

@FunctionalInterface
interface ReadableString {
  String Get() throws JposException;
}


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\ReadableString.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */