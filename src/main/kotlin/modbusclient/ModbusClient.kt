package modbusclient

import com.digitalpetri.modbus.master.ModbusTcpMaster
import com.digitalpetri.modbus.master.ModbusTcpMasterConfig
import com.digitalpetri.modbus.requests.WriteSingleCoilRequest
import com.digitalpetri.modbus.responses.WriteSingleCoilResponse
import io.netty.util.ReferenceCountUtil
import kotlinx.coroutines.future.await

class ModbusClient(
    address: String,
    port: Int
) {
    private val modbusTcpMaster: ModbusTcpMaster

    init {
        val modbusTcpMasterConfig = ModbusTcpMasterConfig.Builder(address)
            .setPort(port)
            .build()
        modbusTcpMaster = ModbusTcpMaster(modbusTcpMasterConfig)
    }

    suspend fun writeSingleCoil(memAddress: Int, value: Boolean) {
        modbusTcpMaster.connect().await()

        val response = modbusTcpMaster
            .sendRequest<WriteSingleCoilResponse>(
                WriteSingleCoilRequest(memAddress, value), 255)
            .await()

       ReferenceCountUtil.release(response)
    }
}