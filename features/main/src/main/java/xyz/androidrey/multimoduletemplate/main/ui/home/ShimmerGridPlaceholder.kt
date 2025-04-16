package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


@Composable
fun ShimmerGridPlaceholder() {
    val brush = ShimmerBrush()
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(6) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(brush)
            )
        }
    }
}