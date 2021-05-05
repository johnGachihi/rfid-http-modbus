package rfidclient

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.runBlocking

class ChannelHandler(
    private val onRfidReceived: suspend (rfid: Int) -> Unit
) : SimpleChannelInboundHandler<Int>()
{
    override fun channelRead0(ctx: ChannelHandlerContext?, rfid: Int) = runBlocking {
        onRfidReceived(rfid)
    }
}