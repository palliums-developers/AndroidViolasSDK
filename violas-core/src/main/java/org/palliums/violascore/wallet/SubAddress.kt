package org.palliums.violascore.wallet

import org.palliums.violascore.serialization.hexToBytes
import org.palliums.violascore.serialization.toHex
import java.util.*
import kotlin.random.Random


class SubAddress {
    companion object {
        const val SUB_ADDRESS_LENGTH = 8

        fun generate(): SubAddress {
            val b = ByteArray(SUB_ADDRESS_LENGTH)
            Random.nextBytes(b)
            return SubAddress(b)
        }
    }

    private val bytes: ByteArray

    constructor(bytes: ByteArray) {
        require(bytes.size == SUB_ADDRESS_LENGTH) {
            String.format(
                "Sub address should be %d bytes, but given %d bytes",
                SUB_ADDRESS_LENGTH,
                bytes.size
            )
        }
        this.bytes = bytes
    }

    constructor(subAddress: String) : this(subAddress.hexToBytes())

    fun toHex(): String {
        return bytes.toHex()
    }

    fun getBytes(): ByteArray {
        return bytes
    }

    override fun toString(): String {
        return "SubAddress{" +
                "bytes=" + toHex() +
                '}'
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as SubAddress
        return Arrays.equals(bytes, that.bytes)
    }

    override fun hashCode(): Int {
        return Arrays.hashCode(bytes)
    }
}