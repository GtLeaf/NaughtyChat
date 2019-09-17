package com.cmd.hit.test.itemView

data class CollectionMicroAppModel(
    val type: Int,
    val microAppModel: MicroAppModel) {
    companion object {
        const val TYPE_TITLE = 1
        const val TYPE_MICRO_APP = 2
        const val TYPE_NO_USE_RECORD = 3
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CollectionMicroAppModel

        if (type != other.type) return false
        if (microAppModel != other.microAppModel) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type
        result = 31 * result + microAppModel.hashCode()
        return result
    }


}