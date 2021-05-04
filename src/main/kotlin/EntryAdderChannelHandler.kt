import httpclient.EntryRepository
import httpclient.Response
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import kotlinx.coroutines.runBlocking

class EntryAdderChannelHandler(
    private val entryRepository: EntryRepository = EntryRepository()
) : SimpleChannelInboundHandler<Int>()
{
    override fun messageReceived(ctx: ChannelHandlerContext?, rfid: Int) {
        println("Client received: $rfid")
        /*entryRepository.addEntryAsync(rfid) {
            when (it) {
                is Response.Success -> print("Success") // turn echo on
                is Response.Failure -> print("Failure")
            }
        }*/
    }
}