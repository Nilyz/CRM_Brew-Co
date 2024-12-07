package com.example.brewco.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.brewco.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun TradeReportCard(
    tradeType1: String,
    tradeType2: String,
    randomNum1: Int,
    randomNum2: Int,
    dinamic: Boolean

) {
    var ventas by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (dinamic) {
            delay(1000)
            ventas += 13
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Beige),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "${tradeType1}: ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            Text("${ventas + randomNum1}", fontSize = 16.sp, color = DarkBrown)
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            thickness = 2.dp,
            color = Brown,


            )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                "${tradeType2}: ",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBrown
            )
            Text("${ventas + randomNum2}", fontSize = 16.sp, color = DarkBrown)
        }
    }
}

@Composable
fun TradeReportCard2(
    tradeType1: String,
    tradeType2: String,
    randomNum1: Int,
    randomNum2: Int,
    dinamic: Boolean

) {
    var ventas by remember { mutableStateOf(1) }

    LaunchedEffect(Unit) {
        while (dinamic) {
            delay(1000)
            ventas += 13
        }
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .background(Brown),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            "INGRESOS: ",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp),
            color = Cream
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("${tradeType1}: ", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Cream)
            Text("${ventas + randomNum1}", fontSize = 16.sp, color = Cream)
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            thickness = 2.dp,
            color = Beige,


            )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("${tradeType2}: ", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Cream)
            Text("${randomNum2}", fontSize = 16.sp, color = Cream)
        }
    }
}