package com.example.presentation.ui.article_compose


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.machinetest.R
import com.example.network.model.ArticleModel
import com.example.presentation.BaseApplication
import com.example.presentation.components.*
import com.example.presentation.theme.YarabTheme
import com.example.utils.AppEvents
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ArticleListFragment : Fragment() {

    private val viewModel: ArticleViewModel by viewModels()

    @Inject
    lateinit var baseApplication: BaseApplication


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.onTriggerEvent(ArticleEvent.INT_LIST_EVENT)
        return ComposeView(requireContext()).apply {
            setContent {
                val result: List<ArticleModel> = viewModel.list.value;
                val isLoading: Boolean = viewModel.isLoading.value;
                YarabTheme(isDark = baseApplication.isDark()) {
                    Scaffold(
                        topBar = {
                            AppBar(
                                onClick = {
                                    when (it) {
                                        is AppEvents.NAVIGATION_EVENT -> {
                                            findNavController().navigate(
                                                R.id.action_articleListFragment_to_recipeFragment
                                            )
                                        }
                                        is AppEvents.TOGGLE_THEME_EVENT -> {
                                            baseApplication.toggleTheme()
                                        }
                                    }

                                })
                        }
                    ) {

                        /*
                        val art = ArticleModel(
                            title = "demo",
                            des = "demo",
                            published_date = "demo",
                            byline = "demo",
                            section = "demo",
                            media = listOf(),
                            abstract = "demo",
                            desFacet = listOf()
                        );
                        var expand by rememberSaveable { mutableStateOf(false) }
                        ExpandableCardComposable(
                            topContent = { MovieCard(art) },
                            buttomContent = { BottomArticleDetails(art) },
                            isExpandable = expand,
                            onExpandChange = { expand = it; }
                        )
                        */


                        if (isLoading) {
                            LoadingArticleListShimmer(imageHeight = 200.dp, padding = 12.dp)
                        } else {
                            SwipeRefresh(
                                state = rememberSwipeRefreshState(isLoading),
                                onRefresh = { viewModel.onTriggerEvent(ArticleEvent.REFRESH_EVENT) },
                            ) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(result) {
                                        var expand by rememberSaveable { mutableStateOf(false) }
                                        ExpandableCardComposable(
                                            topContent = { MovieCard(it) },
                                            buttomContent = { BottomArticleDetails(it) },
                                            isExpandable = expand,
                                            onExpandChange = { expand = it; }
                                        )
                                    }
                                }
                            }
                        }


                    }
                }
            }
        }
    }


}



