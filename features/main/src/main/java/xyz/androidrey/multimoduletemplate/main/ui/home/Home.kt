package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.compose.AppTheme
import xyz.androidrey.multimoduletemplate.main.data.entity.Product
import xyz.androidrey.multimoduletemplate.main.ui.MainScreen
import xyz.androidrey.multimoduletemplate.theme.components.AppBar
import xyz.androidrey.multimoduletemplate.theme.components.ThePreview
import xyz.androidrey.multimoduletemplate.theme.utils.toast


@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val sort by viewModel.sortOption.collectAsState()
    val context = LocalContext.current
    val productsPagingItem = remember(sort) {
        viewModel.getSortedPagingFlow()
    }.collectAsLazyPagingItems()

    LaunchedEffect(productsPagingItem.loadState) {
        if (productsPagingItem.loadState.refresh is LoadState.Error) {
            val error = (productsPagingItem.loadState.refresh as LoadState.Error).error.message
            context.toast("Error: " + (productsPagingItem.loadState.refresh as LoadState.Error).error.message){

            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppBar("Products")

        Home(products = productsPagingItem) {
            viewModel.updateSort(it)
        }
    }
}

enum class SortOption(val label: String) {
    TITLE("Title"),
    PRICE("Price"),
    RATING("Rating")
}

@Composable
fun Home(products: LazyPagingItems<Product>, sortItemClicked: (SortOption) -> Unit) {
    var selectedSort by remember { mutableStateOf(SortOption.TITLE) }

    val isRefreshing = products.loadState.refresh is LoadState.Loading
    val isAppending = products.loadState.append is LoadState.Loading
    val isInitialLoading = isRefreshing && products.itemCount == 0

    Column {
        if (!isInitialLoading) {
            SortDropdown(selectedSort) { sortItemClicked(it) }
        }

        if (isInitialLoading) {
            ShimmerGridPlaceholder()
        } else {
            LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(4.dp)) {
                items(products.itemCount) { index ->
                    val product = products[index]
                    if (product != null) {
                        AnimatedGridItem {
                            ProductGridItem(product = product) { name ->

                            }
                        }
                    }
                }

                if (isAppending) {
                    item(span = { GridItemSpan(2) }) {
                        LoadingItem()
                    }
                }

                if (products.loadState.append is LoadState.Error) {
                    item(span = { GridItemSpan(2) }) {
                        ErrorItem {
                            products.retry()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SortDropdown(selected: SortOption, onSortChange: (SortOption) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Button(onClick = { expanded = true }) {
            Text("Sort by: ${selected.label}")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            SortOption.entries.forEach {
                DropdownMenuItem(
                    text = { Text(it.label) },
                    onClick = {
                        onSortChange(it)
                        expanded = false
                    }
                )
            }
        }
    }
}



@Composable
fun AnimatedGridItem(content: @Composable () -> Unit) {
    val enterTransition = remember {
        fadeIn(animationSpec = tween(300)) +
                slideInVertically(initialOffsetY = { 40 }, animationSpec = tween(300))
    }

    AnimatedVisibility(
        visible = true,
        enter = enterTransition
    ) {
        content()
    }
}

@Composable
fun ProductGridItem(product: Product, clickedUserName: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable { clickedUserName(product.title) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .size(120.dp)
                    .padding(4.dp)
            )
            Text(text = product.title, maxLines = 2, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(text = "৳${product.price}", color = Color.Black)
            Text(text = "${product.discountPercentage}% OFF", color = Color.Red)
            Text(text = product.brand, color = Color.Gray, fontSize = 12.sp)
            Text(text = "⭐ ${product.rating}", color = Color(0xFFf5a623), fontSize = 12.sp)
        }
    }
}

@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make sure the Box takes the full width
            .padding(16.dp)
    ) { // Optional padding
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ErrorItem(retry: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = retry, modifier = Modifier.align(Alignment.Center)) {
            Text("Retry")
        }
    }
}

@ThePreview
@Composable
fun PreviewHome() {
    AppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

        }
    }
}