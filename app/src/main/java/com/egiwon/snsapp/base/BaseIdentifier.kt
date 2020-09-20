package com.egiwon.snsapp.base

abstract class BaseIdentifier : Any() {
    abstract val identifier: Any

    override fun hashCode(): Int {
        return identifier.hashCode()
    }

    override operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        (other as? BaseIdentifier)?.run {
            if (identifier != other.identifier) return false
            return true
        }

        return false
    }
}