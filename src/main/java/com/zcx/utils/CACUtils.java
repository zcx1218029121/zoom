package com.zcx.utils;

/**
 * Package:        com.example.demo.utils
 *
 * @author: loafer
 * Description:
 * Date:    2021/4/27 17:33
 * Version:    1.0
 */
public class CACUtils {

    public static Integer makeCrc(byte[] bytes) {

        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return CRC;
    }
}
