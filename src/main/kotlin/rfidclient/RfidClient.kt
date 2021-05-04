package rfidclient

import io.netty.bootstrap.Bootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import java.net.InetSocketAddress

object RfidClient {
    fun listenForRfid(address: String, port: Int, onRfidReceived: suspend (rfid: Int) -> Unit) {
        val group = NioEventLoopGroup()
        try {
            val clientBootstrap = Bootstrap()
            clientBootstrap.group(group)
            clientBootstrap.channel(NioSocketChannel::class.java)
            clientBootstrap.remoteAddress(InetSocketAddress(address, port))
            clientBootstrap.handler(object : ChannelInitializer<SocketChannel>() {
                override
                fun initChannel(ch: SocketChannel?) {
                    ch?.pipeline()?.addLast(
                        DelimiterBasedFrameDecoder(
                            14, false, Unpooled.wrappedBuffer(ByteArray(1) { 3 })),
                        RfidDecoder(),
                        ChannelHandler(onRfidReceived)
                    )
                }
            })
            val channelFuture = clientBootstrap.connect().sync()
            channelFuture.channel().closeFuture().sync()
        } finally {
            group.shutdownGracefully().sync()
        }
    }
}

