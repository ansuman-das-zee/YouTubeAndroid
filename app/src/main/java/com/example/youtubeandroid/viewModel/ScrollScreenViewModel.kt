package com.example.youtubeandroid.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.usecases.suggestedvideos.SuggestedVideosUsecase
import com.example.youtubeandroid.event.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class ScrollScreenViewModel(private val sugVideos:SuggestedVideosUsecase) : ViewModel(){


    private val _state: MutableStateFlow<ViewState> =
        MutableStateFlow(ViewState())
    val state: StateFlow<ViewState>
        get() = _state.asStateFlow()


    init {

        startScreen()

    }
    fun onEvent( appEvent: Event){
        when(appEvent){
            is Event.playButtonClick -> {
                viewModelScope.launch{
//                    sugVideos.exec(appEvent.id)
                    Log.e("Print","PlayButtonClick")
                }
            }

            is Event.backButtonClick -> {
                viewModelScope.launch{
                    Log.e("Print","backButton click")
                }
            }


        }
    }
//    fun videoList(){
//
//
//        return state.
//    }


    fun testViewmodel() : String{
        viewModelScope.launch{
            Log.e("Print","The view model functions are working")

        }

        val s : String = "Viewmodel started"
        return s

    }


    fun startScreen(){

        val resultCount:String = "20"

        var searchQuery = SuggestedVideosUsecase.Input(
            "New",
            "snippet,id",
            "US",
            "20",
            "date"
        )


        viewModelScope.launch{
//            val s : Result<SearchResponseDataClass> = sugVideos.exec(searchQuery).map { response ->
//                SearchResponseDataClass(
//                    items = response.items.map { item ->
//                        Item(
//                            id = item.id,
//                            kind = item.kind,
//                            snippet = item.snippet
//                        )
//
//                    },
//                    kind = response.kind,
//                    nextPageToken = response.nextPageToken,
//                    pageInfo = response.pageInfo,
//                    regionCode = response.regionCode
//                )
//            }
            val st : String = sugVideos.exec(searchQuery).toString()
            sugVideos.exec(searchQuery).onSuccess {output->
                _state.updateValue{
                    copy(
                        suggestedVideosList = output.items.map{
                            it.id.videoId
                        },
                        suggestedVideosListNames = output.items.map {
                            it.snippet.title
                        },
                        suggestedVideosListItems = output.items,
                        kind = output.kind,
                        nextPageToken = output.nextPageToken,
                        pageInfo = output.pageInfo,
                        regionCode = output.regionCode
                    )
                }
            }.onFailure {
                Log.e("Fail messege","Failed in the ScrollscreenViewmodel")
            }


            Log.e("Newlog",st)


        }

    }
}

private fun <T> MutableStateFlow<T>.updateValue(block: T.() -> T) {
    value = value.block()
}
