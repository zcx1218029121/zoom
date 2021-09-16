package com.zcx;

import io.netty.buffer.ByteBuf;

/**
 * Package:        pack.data
 *
 * @author loafer
 * Description:
 * Bcd 码的字节码实现
 * Date:    2021/4/13 13:11
 * Version:    1.0
 */
public abstract class Bcd extends BaseInformationElement {
    protected byte[] value;

    protected String strValue;

    public byte[] getValue() {
        return value;
    }

    public Bcd(ByteBuf byteBuffer) {
        super(byteBuffer);
        value = new byte[charSize()];
        byteBuffer.readBytes(value);
        this.strValue = bcd2Str(value);
    }

    public Bcd() {
        super(null);
    }

    public Bcd(String strValue) {
        super(null);
        this.strValue = String.format("%0" + charSize() * 2 + "d", Long.valueOf(strValue));
        if (strValue.length() > charSize()*2) {
            this.strValue = strValue.substring(0, charSize()*2);
        }
        this.value = str2Bcd(this.strValue);
    }

    public abstract int charSize();


    @Override
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeBytes(value);
    }

    public String getStrValue() {
        return strValue;
    }

    @Override
    public int getLen() {
        return charSize();
    }

    private String bcd2Str(byte[] bytes) {
        StringBuilder temp = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }
        return "0".equalsIgnoreCase(temp.toString().substring(0, 1)) ? temp
                .toString().substring(1) : temp.toString();

    }

    protected byte[] str2Bcd(String asc) {
        int len = asc.length();
        int mod = len % 2;
        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }
        byte[] abt;
        if (len >= 2) {
            len = len / 2;
        }
        byte[] bbt = new byte[len];
        abt = asc.getBytes();
        int j, k;
        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    @Override
    public String toString() {
        return strValue;
    }

}
