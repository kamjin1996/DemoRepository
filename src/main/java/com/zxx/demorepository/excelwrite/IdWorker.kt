package com.zxx.demorepository.excelwrite

import org.slf4j.*
import java.lang.Exception
import java.io.InputStreamReader
import java.io.BufferedReader
import java.io.IOException
import java.lang.management.*

class IdWorker private constructor() {
    companion object {

        val logger = LoggerFactory.getLogger(IdWorker::class.java)

        private const val twepoch = 1489111610226L

        // 机器标识位数
        private const val workerIdBits = 5L

        // 数据中心标识位数
        private const val datacenterIdBits = 5L

        // 毫秒内自增位数
        private const val sequenceBits = 12L

        // 机器ID偏左移12位
        private const val workerIdShift = sequenceBits

        // 数据中心ID左移17位
        private const val datacenterIdShift = sequenceBits + workerIdBits

        // 时间毫秒左移22位
        private const val timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits

        //sequence掩码，确保sequnce不会超出上限
        private const val sequenceMask = -1L xor (-1L shl sequenceBits.toInt())

        //上次时间戳
        private var lastTimestamp = -1L
        private const val workerMask = -1L xor (-1L shl workerIdBits.toInt())
        private const val processMask = -1L xor (-1L shl datacenterIdBits.toInt())
        var keyWorker: IdWorker = IdWorker()

        @Synchronized
        fun nextId(): Long {
            return keyWorker.nextId
        }

    }

    //序列
    private var sequence = 0L

    //服务器ID
    private var workerId = 1L

    //进程编码
    private var processId = 1L// 当前毫秒内计数满了，则等待下一秒

    init {
        //获取机器编码
        workerId = getMachineNum(false)
        //获取进程编码
        val runtimeMXBean = ManagementFactory.getRuntimeMXBean()
        processId = java.lang.Long.valueOf(runtimeMXBean.name.split("@").toTypedArray()[0]).toLong()

        //避免编码超出最大值
        workerId = workerId and workerMask
        processId = processId and processMask
    }

    // ID偏移组合生成最终的ID，并返回ID
// 当前毫秒内，则+1，与sequenceMask确保sequence不会超出上限
    //获取时间戳
    //如果时间戳小于上次时间戳则报错
    //如果时间戳与上次时间戳相同
    @get:Synchronized
    val nextId: Long
        get() {
            //获取时间戳
            var timestamp = timeGen()
            //如果时间戳小于上次时间戳则报错
            if (timestamp < lastTimestamp) {
                try {
                    throw Exception("Clock moved backwards.  Refusing to generate id for " + (lastTimestamp - timestamp) + " milliseconds")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            //如果时间戳与上次时间戳相同
            if (lastTimestamp == timestamp) {
                // 当前毫秒内，则+1，与sequenceMask确保sequence不会超出上限
                sequence = sequence + 1 and sequenceMask
                if (sequence == 0L) {
                    // 当前毫秒内计数满了，则等待下一秒
                    timestamp = tilNextMillis(lastTimestamp)
                }
            } else {
                sequence = 0
            }
            lastTimestamp = timestamp
            // ID偏移组合生成最终的ID，并返回ID
            return timestamp - twepoch shl timestampLeftShift.toInt() or (processId shl datacenterIdShift.toInt()) or (workerId shl workerIdShift.toInt()) or sequence
        }

    /**
     * 再次获取时间戳直到获取的时间戳与现有的不同
     *
     * @param lastTimestamp
     * @return 下一个时间戳
     */
    private fun tilNextMillis(lastTimestamp: Long): Long {
        var timestamp = timeGen()
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen()
        }
        return timestamp
    }

    private fun timeGen() = System.currentTimeMillis()

    /**
     * 获取机器编码
     *
     * @return
     */
    // private long getMachineNum() {
    //     long machinePiece;
    //     StringBuilder sb = new StringBuilder();
    //     Enumeration<NetworkInterface> e = null;
    //     try {
    //         e = NetworkInterface.getNetworkInterfaces();
    //     } catch (SocketException e1) {
    //         e1.printStackTrace();
    //     }
    //     while (e.hasMoreElements()) {
    //         NetworkInterface ni = e.nextElement();
    //         sb.append(ni.toString());
    //     }
    //     machinePiece = sb.toString().hashCode();
    //     return machinePiece;
    // }
    private fun getMachineNum(isReturnPrefix: Boolean): Long {
        var result: String? = ""
        val rt = Runtime.getRuntime()
        return try {
            val proc = rt.exec("cat /sys/class/dmi/id/product_uuid")
            val isr = InputStreamReader(proc.inputStream)
            val br = BufferedReader(isr)
            var line: String?
            while (br.readLine().also { line = it } != null) {
                result += line
            }
            isr.close()
            logger.info("获取到的product_uuid为：{}", result)
            result = result!!.trim { it <= ' ' }
            if ("" != result) {
                if (isReturnPrefix) {
                    result = "CPUID:$result"
                }
            }
            result = result.replace(" ", "")
            val hashCode = result.hashCode()
            logger.info("该product_uuid经处理后 生成的hashcode为：{}", result)
            hashCode.toLong()
        } catch (e: IOException) {
            logger.error("获取linux/unix系統cpuId发生异常。原因：", e)
            "localhost".hashCode().toLong() //本地测试固定获取localhost
        }
    }
}

/**
 * 订单号生成
 */
object TradeNoUtil {

    /**
     * 生成唯一订单号
     */
    @Synchronized
    fun tradeNo(): String {
        return "${IdWorker.nextId()}"
    }
}