package com.example.youtubeandroid.viewModel
import com.example.domain.entities.search.Id
import com.example.domain.entities.search.Item
import com.example.domain.entities.search.PageInfo


data class ViewState(
//    val isVideoDetailsPage : Boolean = false,
    val suggestedVideosList: List<String> = emptyList(),
    val suggestedVideosListNames: List<String> = emptyList(),
    val suggestedVideosListItems: List<Item> = emptyList(),
    val kind: String? = null,
    val nextPageToken: String?=null,
    val pageInfo: PageInfo?=null,
    val regionCode: String?=null,

//    val
//    val channelId: String? = null,
//    val channelTitle: String? = null,
//    val description: String? = null,
//    val mediumThumbnail: Medium? = null,
//    val mediumThumbnailUrl: String?=null,
//    val videoDetailId: Id? = null,
//    val videoDetailUrl: String?=null,
//    val channelDetailData: Snippet? = null


)
