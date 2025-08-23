package com.example.stylish.Presentation.onBoarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.stylish.Presentation.onBoarding.Components.ButtomIndicator
import com.example.stylish.Presentation.onBoarding.Components.TopIndicator
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import androidx.navigation.compose.NavHost

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingPagerScreen(navController: NavController,onGetStarted: () -> Unit) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> Onboarding1Screen(navController)
                1 -> Onboarding2Screen(navController)
                2 -> Onboarding3Screen(navController)
            }
        }

        ButtomIndicator(
            totalSteps = 3,
            currentStep = pagerState.currentPage,
            onNextClick = {
                scope.launch {
                    if (pagerState.currentPage < 2) {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    } else {
                        onGetStarted()
                    }
                }
            },
            onPreviousClick = {
                scope.launch {
                    if(pagerState.currentPage>0){
                        pagerState.animateScrollToPage(pagerState.currentPage-1)
                    }
                }
            },
            onFinishClick = {
                onGetStarted()
            }



        )

    }
}
