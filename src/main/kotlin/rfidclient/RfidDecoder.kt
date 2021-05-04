package rfidclient

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.DecoderException
import io.netty.util.CharsetUtil

class RfidDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext?, `in`: ByteBuf, out: MutableList<Any>?) {
        println(`in`.readableBytes())

        val frame = `in`.readBytes(`in`.readableBytes())

        // First frame must be 0x2
        if (frame.readByte().compareTo(0x2) != 0) {
            frame.release()
            throw DecoderException("Invalid RFID frame")
        }

        var rfid = frame.readBytes(8)

        // If 10th byte == 0xd, then the rfid is 8-bytes
        // and hex encoded, thus convert to decimal
        if (frame.getByte(frame.readerIndex()).compareTo(0xd) == 0) {
            out?.add(rfid.toString(CharsetUtil.US_ASCII).toInt(16))
            return
        }

        // Else, it should be a 10-byte hexadecimal,
        // thus read two more bytes
        rfid = Unpooled.copiedBuffer(rfid, frame.readBytes(2))

        if (frame.readByte().compareTo(0xd) != 0) {
            frame.release()
            throw DecoderException("Invalid RFID frame")
        }

        out?.add(rfid.toString(CharsetUtil.US_ASCII).toInt())
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        super.exceptionCaught(ctx, cause)
    }
}