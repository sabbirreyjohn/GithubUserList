package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    val productsPagingItem = viewModel.lazyPagingMediatorItem.collectAsLazyPagingItems()
    val context = LocalContext.current
    LaunchedEffect(key1 = productsPagingItem.loadState) {
        if (productsPagingItem.loadState.refresh is LoadState.Error) {
            context.toast("Error: " + (productsPagingItem.loadState.refresh as LoadState.Error).error.message) {

            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        AppBar("Products")
        if (productsPagingItem.loadState.refresh is LoadState.Loading) {
            LoadingItem()
        } else {
            Home(products = productsPagingItem) {
                navController.navigate(MainScreen.Profile(name = it))
            }
        }
    }
}

@Composable
fun Home(products: LazyPagingItems<Product>, clickedUserName: (String) -> Unit) {
    LazyColumn {
        items(products.itemCount) { index ->
            val product = products[index]
            product?.let {
                UserRow(product = it) { name ->
                    clickedUserName(name)
                }
            }
        }


        if (products.loadState.append is LoadState.Loading) {
            item { LoadingItem() } // Show loading at the end of the list
        }

        if (products.loadState.append is LoadState.Error) {
            item {
                ErrorItem {
                    products.retry() // Provide a way to retry failed loads
                }
            }
        }
    }

}

@Composable
fun UserRow(product: Product, clickedUserName: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(3.dp)
        .clickable {
            clickedUserName(product.title)
        }) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = product.thumbnail,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
            )
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = product.title, color = Color.Black)
            }
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