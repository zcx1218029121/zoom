package com.zcx;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;


/**
 * ASCIIString string 封装校验长度
 * 如果长度不足用空格补全
 * 如果长度过长自动截取
 * @author loafer
 */
public abstract class ASCIIString extends BaseInformationElement {

    private final CharSequence charSequence;

    private Charset charset = CharsetUtil.US_ASCII;

    public ASCIIString(ByteBuf byteBuf) {
        super(byteBuf);
        this.charSequence = byteBuf.readCharSequence(getLen(), charset);
    }
    public ASCIIString(String s) {
        super(null);
        this. charSequence = String.format("%-"+getLen()+"s", s);
    }

    @Override
    public String toString() {
        return charSequence.toString();
    }

    @Override
    public void encode(ByteBuf byteBuf) {
        byteBuf.writeCharSequence(charSequence, charset);
    }



    public void setCharset(Charset charset) {
        this.charset = charset;
    }

    public Charset getCharset() {
        return charset;
    }


}
