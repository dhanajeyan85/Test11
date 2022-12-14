package com.test.welcome;
import jpos.JposException;

@FunctionalInterface
interface WritableInt {
  void Set(int paramInt) throws JposException;
}


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\WritableInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */