package com.dragonslotos.psb_test.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dragonslotos.psb_test.R
import com.dragonslotos.psb_test.domain.models.Rate
import com.example.compose.RatesTheme
import com.example.ui.theme.Roboto
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RatesTheme {
                Surface(modifier = Modifier.fillMaxSize(),
                        color = RatesTheme.colors.primary) {
                    mainMenu()
                }

            }
        }
    }

    @Composable
    fun mainMenu(){
        val rates: List<Rate> by viewModel.rateList.collectAsState()

        val likeeRate: List<Rate> by viewModel.rateLikeeList.collectAsState()

        val date: Date by viewModel.date.collectAsState()

        val refreshing: Boolean by viewModel.isRefreshing.collectAsState()
        val pullRefreshState = rememberPullRefreshState(
            refreshing = refreshing,
            onRefresh = {
                viewModel.loadRates()
            })
        val error: Boolean by viewModel.internetErrorr.collectAsState()



        Scaffold(
            modifier = Modifier.pullRefresh(pullRefreshState)) {padding ->
            Box(modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(RatesTheme.colors.primary)
                ) {


                LazyColumn(){


                    item {
                        Text(text = "Избранное",
                            color = RatesTheme.colors.containerPrimary,
                            fontFamily = Roboto,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 32.sp,
                            modifier = Modifier.padding(20.dp))
                    }

                    items(likeeRate){ rate ->
                        //animation parameters
                        var lapVisible by remember { mutableStateOf(false) }
                        val animatedOffset by animateOffsetAsState(
                            targetValue = if (lapVisible) Offset.Zero else Offset(-1000f, 0f),
                            label = "Lap alpha",
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearEasing,
                            )
                        )
                        val animatedLapAlpha by animateFloatAsState(
                            targetValue = if (lapVisible) 1f else 0f,
                            label = "Lap alpha",
                            animationSpec = tween(
                                durationMillis = 500,
                                easing = LinearEasing,
                            )
                        )

                        OutlinedCard(colors = CardDefaults.outlinedCardColors(containerColor = Color.White,
                        ),
                            border = BorderStroke(2.dp, Color.White) ,
                            modifier = Modifier
                                .padding(end = 20.dp, start = 20.dp, bottom = 10.dp)
                                .offset {
                                    lapVisible = true;IntOffset(
                                    animatedOffset.x.toInt(),
                                    animatedOffset.y.toInt()
                                )
                                }
                                .graphicsLayer {
                                    lapVisible = true;
                                    alpha = animatedLapAlpha
                                },
                        ) {
                            rateDATA(rate = rate, RatesTheme.colors.primary, RatesTheme.colors.containerPrimary, 5.dp)
                        }
                    }

                    item {
                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp),
                            colors = CardDefaults.cardColors(containerColor = RatesTheme.colors.background),
                            shape = RoundedCornerShape(45.dp, 45.dp, 0.dp, 0.dp)
                        ) {

                            Row (modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center){
                                Text(text = "Последнее обновление: ",
                                    fontFamily = Roboto,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                    color= RatesTheme.colors.fontColor,
                                    modifier = Modifier.padding(10.dp))

                                Text(text = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date),
                                    color= RatesTheme.colors.fontColor,
                                    modifier = Modifier.padding(10.dp))
                            }

                        }
                    }

                    items(rates){ rate ->
                        rateDATA(rate = rate, RatesTheme.colors.background, RatesTheme.colors.fontColor, 24.dp)
                        Surface(color = RatesTheme.colors.background, modifier = Modifier.fillMaxWidth()) {
                            HorizontalDivider(modifier = Modifier.padding(start = 24.dp, end = 24.dp))
                        }

                    }
                }

                PullRefreshIndicator(
                    refreshing = refreshing,
                    state = pullRefreshState,
                    Modifier.align(Alignment.TopCenter),
                    contentColor = RatesTheme.colors.primary,
                )
                when{error -> androidx.compose.material3.AlertDialog(onDismissRequest = { viewModel.loadRates(); viewModel.internetErrorr.value = false },

                    icon = {
                        Icon(painter = painterResource(id = R.drawable.baseline_wifi_off_24), contentDescription = "Example Icon")
                    },
                    title = {
                        Text(text = "Ошибка сети")
                    },
                    text = {
                        Text(text = "Прошу прощения, в загрузке произошёл сбой попробуйте ещё раз")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.loadRates(); viewModel.internetErrorr.value = false
                            }
                        ) {
                            Text("Принять")
                        }
                    })}
            }
        }


    }
    @Composable
    fun rateDATA(rate: Rate, color: Color, colorData: Color, padding: Dp){
        var icon_id = remember {
            R.drawable.star_border
        }
        if (rate.likee){
            icon_id = R.drawable.baseline_star_24
        }

        Row(modifier = Modifier
            .background(color)
            .fillMaxWidth()) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = rate.charCode,
                        color = colorData,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Medium,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(start = padding, top = 5.dp))

                    Text(text = rate.nominal.toString() + rate.charCode + "=" + rate.value + "RUB",
                        color = colorData,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .padding(end = 10.dp, top = 5.dp),
                        textAlign = TextAlign.End)
                    IconButton(onClick = {viewModel.likeRates(rate.charCode)}, modifier = Modifier
                        .fillMaxSize()
                        .padding(end = padding)) {
                        Image( modifier = Modifier.fillMaxSize(), painter = painterResource(id = icon_id), contentDescription = "")
                    }

                }
                Text(text = rate.name,
                    color = colorData,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Light,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = padding, top = 10.dp))
            }


        }
    }
}


