package com.gthio.rpsgenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gthio.rpsgenerator.ui.theme.Purple500
import com.gthio.rpsgenerator.ui.theme.RPSGeneratorTheme

class MainActivity : ComponentActivity() {

    private val rpsViewModel: RpsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RPSGeneratorTheme {
                Scaffold(topBar = { RpsTopAppBar() }) {
                    Surface(color = MaterialTheme.colors.background) {
                        RpsActivityScreen(rpsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun RpsActivityScreen(viewModel: RpsViewModel) {
    val item: RpsItem by viewModel.selectedWeapon.observeAsState(RpsItem.Rock)
    val clickCount: Int by viewModel.clickCount.observeAsState(0)

    RpsScreen(
        item = item,
        clickCount = clickCount,
        onItemClick = viewModel::selectWeapon
    )
}

@Composable
fun RpsTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar {
        Text(text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.h6,
            modifier = modifier.padding(16.dp)
        )
    }
}

@Composable
fun RpsScreen(
    item: RpsItem,
    clickCount: Int,
    onItemClick: (RpsItem) -> Unit,
) {
    Column(modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = item.icon),
                contentDescription = stringResource(id = R.string.random_desc),
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(Purple500),
                modifier = Modifier.size(160.dp)
            )
            Text(
                text = stringResource(id = item.contentDescription),
                style = MaterialTheme.typography.h6,
                color = Purple500,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }
        Text(
            text = stringResource(id = R.string.clicked_counter, clickCount),
            style = MaterialTheme.typography.body1,
            color = Purple500,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        RpsIconRow(onClick = onItemClick)
    }
}

@Composable
fun RpsIconRow(onClick: (RpsItem) -> Unit) {
    Row(modifier = Modifier.padding(16.dp)) {
        for (item in RpsItem.values()) {
            RpsIconButton(onClick = onClick, item = item)
        }
    }
}

@Composable
fun RpsIconButton(onClick: (RpsItem) -> Unit, item: RpsItem, modifier: Modifier = Modifier) {
    Button(onClick = { onClick(item) }, modifier = modifier.padding(horizontal = 8.dp)) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = item.contentDescription)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FullPreview() {
    RPSGeneratorTheme {
        Surface(color = MaterialTheme.colors.background) { RpsActivityScreen(viewModel = RpsViewModel()) }
    }
}