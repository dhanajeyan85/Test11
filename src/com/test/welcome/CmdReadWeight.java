package com.test.welcome;

import jpos.JposException;

@FunctionalInterface
interface CmdReadWeight {
  void Get(int[] paramArrayOfint, int paramInt) throws JposException;
}


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\CmdReadWeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */