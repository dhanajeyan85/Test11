package com.test.welcome;

import jpos.JposException;

@FunctionalInterface
interface CmdDirectIO<command, inxml, opcode> {
  void Set(String[] paramArrayOfString1, String[] paramArrayOfString2, int[] paramArrayOfint) throws JposException;
}


/* Location:              D:\Program Files\Zebra Technologies\Barcode Scanners\Scanner SDK\JPOS\Sample Applications\bin\JposTest.jar!\JposTest\src\CmdDirectIO.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */