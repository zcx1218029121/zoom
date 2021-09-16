package com.zcx;

import io.netty.buffer.ByteBuf;

/**
 * Package:        protocol
 *
 * @author loafer
 * Description:
 * Date:    2021/4/13 10:59
 * Version:    1.0
 */
public abstract class BaseInformationElement {

    public BaseInformationElement(ByteBuf byteBuffer) {
        // sub class must overwrite  this constructor
        // to decode
        // so noting in here
    }


    /**
     * encode
     *
     * @param byteBuf byteBuf
     */
    public abstract void encode(ByteBuf byteBuf);


    /**
     * len of bytes
     * ===================  important ====================
     * 加法常量会被 jvm 优化 用加法是便于阅读
     * 建议按这 1+ 2 +3 +4 +5的形式实现
     *
     * @return bytes len
     */
    public abstract int getLen();
}
