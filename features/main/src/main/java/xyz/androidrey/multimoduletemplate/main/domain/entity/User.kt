package xyz.androidrey.multimoduletemplate.main.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class User(
    @SerialName("id") @PrimaryKey val userId: Int,
    @SerialName("login") val userLogin: String,
    @SerialName("node_id") val nodeId: String,
    @SerialName("avatar_url") val avatarUrl: String
)
