import io.netty.bootstrap.Bootstrap
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import io.netty.handler.codec.DelimiterBasedFrameDecoder
import io.netty.handler.codec.FixedLengthFrameDecoder
import kotlinx.coroutines.*
import rfidclient.RfidClient
import java.net.InetSocketAddress

fun main() {
    RfidClient.listenForRfid("192.168.1.200", 4196) { rfid ->
        println("Hehe. Rfid: $rfid")
//        HttpClient("localhost", 8080).addEntry(rfid) // suspending
//        ModbusClient("192.168.1.10", 1000).open
    }
}

/*
fun addEntry(rfid: String) {
    val request = HttpRequest.newBuilder()
        .uri(URI("http://localhost:8080/entry"))
        .POST(HttpRequest.BodyPublishers.ofString(rfid))
        .build()

    val response = HttpClient.newBuilder()
        .build()
        .send(request, HttpResponse.BodyHandlers.ofString())

    println(response.body())
}*/
