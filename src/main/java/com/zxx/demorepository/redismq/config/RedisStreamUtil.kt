package com.zxx.demorepository.redismq.config

import org.springframework.beans.factory.annotation.*
import org.springframework.data.redis.connection.stream.*
import org.springframework.data.redis.core.*
import org.springframework.stereotype.*
import io.lettuce.core.RedisCommandExecutionException

import io.lettuce.core.RedisBusyException
import org.slf4j.*

import org.springframework.data.redis.RedisSystemException


/**
 * 该组件是对redis stream命令的一些实现，可单独使用
 */
@Component
class RedisStreamUtil {

    @Autowired
    lateinit var redisTemplate: StringRedisTemplate

    private val log = LoggerFactory.getLogger(RedisStreamUtil::class.java)

    /**
     * 创建消费组
     * 如果组已存在则忽略，否则创建，如果stream存在则忽略，否则创建
     */
    fun createGroup(key: String, group: String): String {
        try {
            return redisTemplate.opsForStream<String, Any>().createGroup(key, group)
        } catch (e: RedisSystemException) {
            return when (e.rootCause!!.javaClass) {
                RedisBusyException::class.java -> {
                    log.info("STREAM - Redis group already exists, skipping Redis group creation: $group")
                    "OK"
                }
                RedisCommandExecutionException::class.java -> {
                    log.info("STREAM - Stream does not yet exist, creating empty stream: $key")
                    // TODO: There has to be a better way to create a stream than this!?
                    redisTemplate.opsForStream<Any, Any>().add(key, mutableMapOf())
                    redisTemplate.opsForStream<Any, Any>().createGroup(key, group)
                }
                else -> throw e
            }
        }
    }

    /**
     * 消费组信息
     */
    fun consumers(key: String, group: String): StreamInfo.XInfoConsumers {
        return redisTemplate.opsForStream<String, Any>().consumers(key, group)
    }

    /**
     * 确认已消费
     */
    fun ack(key: String, group: String, vararg recordId: String): Long {
        return redisTemplate.opsForStream<String, Any>().acknowledge(key, group, *recordId) ?: 0L
    }

    /**
     * 确认已消费
     */
    fun ack(key: String, group: String, vararg recordId: RecordId): Long {
        return redisTemplate.opsForStream<String, Any>().acknowledge(key, group, *recordId) ?: 0L
    }

    /**
     * 追加消息
     * @param key
     * @param field
     * @param value
     * @return
     */
    fun add(key: String, field: String, value: Any?): String? {
        val content = mutableMapOf<String, Any?>()
        content.put(field, value)
        return add(key, content)
    }

    fun add(key: String, content: MutableMap<String, Any?>): String? {
        return redisTemplate.opsForStream<String, Any?>().add(key, content)?.value
    }

    /**
     * 删除消息，这里的删除仅仅是设置了标志位，不影响消息总长度
     * 消息存储在stream的节点下，删除时仅对消息做删除标记，当一个节点下的所有条目都被标记为删除时，销毁节点
     * @param key
     * @param recordIds
     * @return
     */
    fun del(key: String, vararg recordIds: String): Long {
        return redisTemplate.opsForStream<String, Any?>().delete(key, *recordIds) ?: 0L
    }

    /**
     * 消息长度
     * @param key
     * @return
     */
    fun len(key: String): Long {
        return redisTemplate.opsForStream<String, Any?>().size(key) ?: 0L
    }

    /**
     * 从开始读
     * @param key
     * @return
     */
    fun read(key: String): MutableList<MapRecord<String, String, Any>> {
        return redisTemplate.opsForStream<String, Any?>().read(StreamOffset.fromStart(key))
            ?: mutableListOf()
    }

    /**
     * 从指定的ID开始读
     * @param key
     * @param recordId
     * @return
     */
    fun read(key: String, recordId: String): MutableList<MapRecord<String, String, Any>> {
        return redisTemplate.opsForStream<String, Any?>()
            .read(StreamOffset.from(MapRecord.create(key, mutableMapOf<String, Any?>()).withId(RecordId.of(recordId))))
            ?: mutableListOf()
    }
}