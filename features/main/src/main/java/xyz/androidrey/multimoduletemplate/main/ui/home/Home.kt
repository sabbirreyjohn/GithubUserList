package xyz.androidrey.multimoduletemplate.main.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavController
import androidx.navigation.Navigator
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.compose.AppTheme
import xyz.androidrey.multimoduletemplate.main.domain.entity.User
import xyz.androidrey.multimoduletemplate.main.ui.MainScreen
import xyz.androidrey.multimoduletemplate.theme.components.AppBar
import xyz.androidrey.multimoduletemplate.theme.components.ThePreview

@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize()) {
        AppBar("Users")
        HomeStateHandler(state = uiState) {
            Home(users = it) {
                navController.navigate("${MainScreen.Profile.route}?name=$it")
            }
        }
    }
}

@Composable
fun Home(users: List<User>, clickedUserName: (String) -> Unit) {
    LazyColumn {
        items(users) { user ->
            UserRow(user = user) {
                clickedUserName(it)
            }
        }
    }
}

@Composable
fun UserRow(user: User, clickedUserName: (String) -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .padding(3.dp)
        .clickable { clickedUserName(user.login) }) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {

            AsyncImage(
                model = user.avatar_url,
                contentDescription = "avatar",
                modifier = Modifier
                    .size(50.dp)
                    .padding(4.dp)
            )
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = user.login, color = Color.Black)
            }
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