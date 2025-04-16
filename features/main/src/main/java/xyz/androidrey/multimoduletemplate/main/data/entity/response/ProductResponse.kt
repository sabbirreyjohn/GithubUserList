package xyz.androidrey.multimoduletemplate.main.data.entity.response

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
import xyz.androidrey.multimoduletemplate.main.data.entity.Product

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ProductResponse(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int
)



