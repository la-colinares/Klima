package com.lacolinares.klima.presensation.screens.main.home

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.lacolinares.klima.R
import com.lacolinares.klima.core.type.WeatherType
import com.lacolinares.klima.core.utils.DateUtils
import com.lacolinares.klima.presensation.composables.KlimaLoader
import com.lacolinares.klima.presensation.theme.BlackPearl
import com.lacolinares.klima.presensation.theme.Mirage
import com.lacolinares.klima.presensation.theme.Neptune
import com.lacolinares.klima.presensation.theme.White
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    state: HomeUiState,
    onGetWeather: (Location) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var currentLocation by remember { mutableStateOf<Location?>(null) }

    LaunchedEffect(currentLocation) {
        currentLocation?.let {
            onGetWeather.invoke(it)
        }
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
        val isGranted = permissions.values.all { it }
        if (isGranted) {
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val isGpsEnabled = with(locationManager) {
                isProviderEnabled(LocationManager.NETWORK_PROVIDER) || isProviderEnabled(LocationManager.GPS_PROVIDER)
            }

            if (!isGpsEnabled) {
                Toast.makeText(context, "GPS is disabled. Please enable it in settings.", Toast.LENGTH_LONG).show()
                return@rememberLauncherForActivityResult
            }

            scope.launch {
                try {
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context.applicationContext)
                        fusedLocationProviderClient.lastLocation
                            .addOnSuccessListener { location ->
                                location?.let {
                                    currentLocation = location
                                }
                            }
                            .addOnFailureListener {
                                Toast.makeText(context, "Failed to get location", Toast.LENGTH_SHORT).show()
                            }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        locationPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Mirage
    ) {
        if (state.isLoading || state.weatherInfo == null) {
            KlimaLoader()
        } else {
            val weather = state.weatherInfo

            val weatherIcon = when {
                DateUtils.isCurrentTimePastSixPM() -> R.drawable.ic_moon
                else -> {
                    when(weather.getWeatherType()) {
                        WeatherType.RAINY -> R.drawable.ic_rain
                        WeatherType.SUNNY -> R.drawable.ic_sun
                    }
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = rememberLazyListState(),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(Icons.Outlined.LocationOn, contentDescription = null, tint = White)
                            Text(text = weather.getFormattedLocation(), color = White, fontSize = 16.sp)
                        }
                        Text(text = weather.getFormattedCurrentDate(), color = White, fontSize = 16.sp)
                    }
                }
                item { Spacer(modifier = Modifier.height(24.dp)) }
                item {
                    Image(
                        painter = painterResource(weatherIcon),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
                item {
                    Text(
                        text = weather.getFormattedTemperature(),
                        color = White,
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Black
                    )
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        SunEventCard(
                            modifier = Modifier.weight(1f),
                            time = weather.getFormattedSunriseTime(),
                            day = weather.getFormattedSunriseDay(),
                            date = weather.getFormattedSunriseDate(),
                            drawableId = R.drawable.ic_sunrise
                        )
                        SunEventCard(
                            modifier = Modifier.weight(1f),
                            time = weather.getFormattedSunsetTime(),
                            day = weather.getFormattedSunsetDay(),
                            date = weather.getFormattedSunsetDate(),
                            drawableId = R.drawable.ic_sunset
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SunEventCard(
    modifier: Modifier,
    time: String,
    day: String,
    date: String,
    drawableId: Int
) {
    Card(
        modifier = Modifier.wrapContentSize().then(modifier),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlackPearl
        ),
        border = BorderStroke(1.dp, Neptune)
    ){
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(drawableId),
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = Neptune
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = time, color = White, fontSize = 32.sp, fontWeight = FontWeight.Black)
            Text(text = day, color = White, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            Text(text = date, color = White, fontSize = 20.sp)
        }
    }
}