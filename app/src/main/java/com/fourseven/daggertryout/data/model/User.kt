package com.fourseven.daggertryout.data.model

/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
class User() {

    var id: Long? = null
    var name: String? = null
    var address: String? = null
    var createdAt: String? = null
    var updatedAt: String? = null

    constructor(name: String, address: String) : this() {
        this.name = name
        this.address = address
    }

    override fun toString(): String {
        return "User {" +
                "id = $id" +
                ", name = $name '\'" +
                ", address = $address '\'" +
                ", createdAt = $createdAt '\'" +
                ", updatedAt = $updatedAt" +
                "}"
    }
}