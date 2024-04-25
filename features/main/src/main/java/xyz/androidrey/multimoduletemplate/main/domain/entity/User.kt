package xyz.androidrey.multimoduletemplate.main.domain.entity

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val id: Int,
    val login: String,
    val node_id: String,
    val avatar_url: String
)
