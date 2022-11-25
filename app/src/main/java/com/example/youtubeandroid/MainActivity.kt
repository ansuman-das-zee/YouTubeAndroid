package com.example.youtubeandroid

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.data.network.api.VideoApiService
import com.example.domain.entities.search.Id
import com.example.domain.entities.search.Item
import com.example.youtubeandroid.event.Event
import com.example.youtubeandroid.player.Player
import com.example.youtubeandroid.player.PlayerImpl
import com.example.youtubeandroid.ui.theme.YouTubeAndroidTheme

import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import com.example.youtubeandroid.viewModel.ScrollScreenViewModel
import com.example.youtubeandroid.viewModel.ViewState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

//exoploplayer
//import com.google.android.exoplayer2.ExoPlayer
//import com.google.android.exoplayer2.source.ProgressiveMediaSource
//import com.google.android.exoplayer2.ui.PlayerView
//import com.google.android.exoplayer2.upstream.DefaultDataSource
//import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
//import com.google.android.exoplayer2.util.Util

class MainActivity : ComponentActivity() {

//    private var youtubePlayer : Player = PlayerImpl()
    lateinit var navController: NavHostController
//    private val api by inject<VideoApiService> ()
    //using by to get tge getters and setters
//    private val viewModel : ScrollScreenViewModel by viewModels()
    private val viewModel by inject<ScrollScreenViewModel>()



    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        getMyData()

        setContent {

            val collectAsState = viewModel.state.collectAsState().value
            val coroutineScope = rememberCoroutineScope()

            lifecycleScope.launch{
                Log.e("Lifecycle","Running")
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.startScreen()
//                    val videoItems : List<Item> = collectAsState.suggestedVideosListItems
//                    val videoItems2 : List<String> = collectAsState.suggestedVideosList


                    Log.e("inside lifecycle scope","The lifecycle is running")
//                viewModel.state.collect(state->
//
//
//
//                )
                }


            }


            YouTubeAndroidTheme {
                val lists = listOf<String>("31","23","34","Cerebus")

//                mainScreen(collectAsState.value)
//                val videoItems : List<Item> = collectAsState.suggestedVideosListItems
//                Log.e("inside",videoItems.toString())

                mainScreen(collectAsState)
//                navController = rememberNavController(navigators = )


                Log.e("setContent","Running")


            }

//             Calling the composable function
//             to display element and its contents
//            MainContent()




        }

//        coroutineScope {
//            Log.e("Lifecycle","Running")
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                viewModel.startScreen()
//
////                viewModel.state.collect(state->
////
////
////
////                )
//            }
//        }


    }

//    private fun getMyData(){
//        lifecycleScope.launch {
//            val retrofitData =  api.getSuggestedVideos("New","snippet","US","20","date")
//            print(retrofitData)
//        }
//
//
//    }








    ///////




    ////////


}

@Composable
fun Greeting(s: String) {

    Text(text = "Names")
}
//= List(1000){"$it"}
@Composable
//fun RecyclerItems(ids : List<String>, names : List<String>){
fun RecyclerItems(videoItems: List<Item>){

    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)){
        items(items = videoItems){ videoItem->
            ListItem(item = videoItem)
        }
    }
}



@Composable
//fun ListItem(id: String, name: String) {
fun ListItem(item: Item) {
    Log.e("ListItem",item.id.videoId)

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) {

            Row {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = "Video Title")
                    Text(
                        text = item.snippet.title, textAlign = TextAlign.Center,style = MaterialTheme.typography.h4.copy(
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                OutlinedButton(onClick = {

                    var urlData : String = "https://www.youtube.com/watch?v="+item.id.videoId
                    
//                    MyContent(mVideoUrl = urlData)
                    var youtubePlayer : Player = PlayerImpl()
                    readyPlayer(youtubePlayer,urlData)

                    Log.e("ListItem2","After ready player")
//                    viewModel.testViewmodel()
                }) {
                    Text(text = "Play Video")
                }

            }

        }
    }
}




// Calling this function as content in the above function
@Composable
fun MyContent(mVideoUrl : String){

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {

        // Fetching the Local Context
        val mContext = LocalContext.current

        // Declaring a string value
        // that stores raw video url
//        val mVideoUrl = "https://cdn.videvo.net/videvo_files/video/free/2020-05/large_watermarked/3d_ocean_1590675653_preview.mp4"


        // Declaring ExoPlayer
        val mExoPlayer = remember(mContext) {
            ExoPlayer.Builder(mContext).build().apply {
                val dataSourceFactory = DefaultDataSource.Factory(mContext)
                val source = ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(
                    MediaItem.fromUri(Uri.parse(mVideoUrl)))
                prepare(source)
            }
//            youtubePlayer
        }
//
        // Implementing ExoPlayer
        AndroidView(factory = { context ->
            PlayerView(context).apply {
                player = mExoPlayer
            }
        })





    }
}

//@Preview
//@Composable
//fun DefaultPreview(){
//    YouTubeAndroidTheme {
////        ListItem(name = "1")
//        RecyclerItems()
//    }
//}



@Composable
fun mainScreen(screenState: ViewState){

    val ids : List<String> = screenState.suggestedVideosList
    val names : List<String> = screenState.suggestedVideosListNames
    val videoItems : List<Item> = screenState.suggestedVideosListItems

    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
//                    Greeting("Android")
//                    RecyclerItems()



        Log.e("New","App is working")

//                    val lists = listOf()
//        RecyclerItems(ids , names)
        RecyclerItems(videoItems)

//                    viewModel.onEvent(Event.playButtonClick)



//                    ListItem(name = "1")


    }
}


fun readyPlayer(videoPlayer: Player,urlData:String){
    Log.e("readyPlayer1","ReadyPlayer is started")
//    val videoPlayer: Player = PlayerImpl()
    Log.e("readyPlayer1","videoPlayer is starter")
    videoPlayer.setUrlData(urlData)
//    Log.e("new",videoPlayer.)
    videoPlayer.initializePlayer()

}


