package com.lacolinares.klima.presensation.screens.main.weather

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lacolinares.klima.R
import com.lacolinares.klima.core.type.WeatherType
import com.lacolinares.klima.core.utils.toFormattedDate
import com.lacolinares.klima.domain.model.WeatherInfo
import com.lacolinares.klima.presensation.composables.KlimaLoader
import com.lacolinares.klima.presensation.theme.BlackPearl
import com.lacolinares.klima.presensation.theme.Gray
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.Silver
import com.lacolinares.klima.presensation.theme.White

@Composable
fun WeatherListScreen(
    state: WeatherListUiState,
    onLoadWeathers: () -> Unit
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        onLoadWeathers.invoke()
    }

    LaunchedEffect(state.error) {
        if (state.error.isNotEmpty()) {
            Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
        }
    }

    if (state.isLoading) {
        KlimaLoader()
    } else if (state.weathers.isEmpty() || state.error.isNotEmpty()) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Mirage
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No records found.", color = White)
            }
        }
    } else {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Mirage
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(items = state.weathers, key = { weather -> weather.id }) { weather ->
                    WeatherCard(weather)
                }
            }
        }

    }
}

@Preview
@Composable
private fun WeatherCard(weatherInfo: WeatherInfo = WeatherInfo()){
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlackPearl
        ),
        border = BorderStroke(2.dp, Neptune)
    ) {
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                val weatherIcon = when(weatherInfo.getWeatherType()) {
                    WeatherType.RAINY -> R.drawable.ic_rain
                    else -> R.drawable.ic_sun
                }

                Image(
                    painter = painterResource(weatherIcon),
                    contentDescription = null,
                    modifier = Modifier.size(68.dp)
                )
                Column {
                    Text(
                        text = weatherInfo.getFormattedTemperature(),
                        color = White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = weatherInfo.getFormattedLocation(),
                        color = Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = weatherInfo.getFormattedCurrentDate(),
                        color = Gray,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            HorizontalDivider(color = Silver)
            Row(
                modifier = Modifier.height(IntrinsicSize.Min),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Text(
                    text = "Sunrise:\n${weatherInfo.sunriseTime.toFormattedDate("hh:mm a, MMMM d, yyyy")}",
                    modifier = Modifier.weight(1f),
                    color = White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
                VerticalDivider(color = Silver)
                Text(
                    text = "Sunset:\n${weatherInfo.sunsetTime.toFormattedDate("hh:mm a, MMMM d, yyyy")}",
                    modifier = Modifier.weight(1f),
                    color = White,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

    }
}