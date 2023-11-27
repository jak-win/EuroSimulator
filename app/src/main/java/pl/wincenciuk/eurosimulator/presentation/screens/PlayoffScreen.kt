package pl.wincenciuk.eurosimulator.presentation.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pl.wincenciuk.eurosimulator.R
import pl.wincenciuk.eurosimulator.components.PoInputField
import pl.wincenciuk.eurosimulator.components.background_color
import pl.wincenciuk.eurosimulator.components.background_color2
import pl.wincenciuk.eurosimulator.presentation.viewmodel.EuroViewModel

@Composable
fun PlayoffScreen(viewModel: EuroViewModel) {

    var winnersFirstRound by remember { mutableStateOf(listOf("QF1", "QF2", "QF3", "QF4", "QF5", "QF6", "QF7", "QF8")) }
    var winnersSecondRound by remember { mutableStateOf(listOf("SF1", "SF2", "SF3", "SF4")) }
    var winnersThirdRound by remember { mutableStateOf(listOf("F1", "F2")) }
    var champion by remember { mutableStateOf("winner") }

    val allAdavancedTeams by viewModel.allAdvancingTeams.collectAsState(emptyList())
    Log.d("PlayoffScreen", "TopTeams: $allAdavancedTeams")


    LaunchedEffect(winnersFirstRound, winnersSecondRound, winnersThirdRound) {

    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = background_color
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Brush.linearGradient(colors = listOf(
                background_color, background_color2)))
        ) {
            Image(painterResource(id = R.drawable.knockout), contentDescription = "Playoff header")
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                //   1/8
                if (allAdavancedTeams.isNotEmpty()) {
                    Column(modifier = Modifier.padding(start = 15.dp)) {
                        SingleMatchComponent(allAdavancedTeams[0].shortName, allAdavancedTeams[5].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(0, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[1].shortName, allAdavancedTeams[4].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(1, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[2].shortName, allAdavancedTeams[3].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(2, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[6].shortName, allAdavancedTeams[11].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(3, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[7].shortName, allAdavancedTeams[10].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(4, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[8].shortName, allAdavancedTeams[9].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(5, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[12].shortName, allAdavancedTeams[16].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(6, winner) }
                        }
                        SingleMatchComponent(allAdavancedTeams[13].shortName, allAdavancedTeams[15].shortName) { winner ->
                            winnersFirstRound =
                                winnersFirstRound.toMutableList().apply { set(7, winner) }
                        }
                    }
                }
                // 1/4
                Column(modifier = Modifier.padding(start = 15.dp)) {
                    SingleMatchComponent(teamA = winnersFirstRound[0], teamB = winnersFirstRound[1]) {winner -> winnersSecondRound  = winnersSecondRound.toMutableList().apply { set(0, winner) }}
                    SingleMatchComponent(teamA = winnersFirstRound[2], teamB = winnersFirstRound[3]) {winner -> winnersSecondRound  = winnersSecondRound.toMutableList().apply { set(1, winner) }}
                    SingleMatchComponent(teamA = winnersFirstRound[4], teamB = winnersFirstRound[5]) {winner -> winnersSecondRound  = winnersSecondRound.toMutableList().apply { set(2, winner) }}
                    SingleMatchComponent(teamA = winnersFirstRound[6], teamB = winnersFirstRound[7]) {winner -> winnersSecondRound  = winnersSecondRound.toMutableList().apply { set(3, winner) }}
                }
                // 1/2
                Column(
                    modifier = Modifier.padding(start = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = R.drawable.medals),
                        contentDescription = "medals",
                        modifier = Modifier.size(80.dp)
                    )
                    SingleMatchComponent(teamA = winnersSecondRound[0], teamB = winnersSecondRound[1]) {winner -> winnersThirdRound = winnersThirdRound.toMutableList().apply { set(0 , winner) }}
                    SingleMatchComponent(teamA = winnersSecondRound[2], teamB = winnersSecondRound[3]) {winner -> winnersThirdRound = winnersThirdRound.toMutableList().apply { set(1 , winner) }}
                }
                //  Final
                Column(
                    modifier = Modifier.padding(start = 15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painterResource(id = R.drawable.trophy_drawing),
                        contentDescription = "trophy",
                        modifier = Modifier.size(60.dp)
                    )
                    SingleMatchComponent(teamA = winnersThirdRound[0], teamB = winnersThirdRound[1]) {winner -> champion = winner}
                }
            }
        }
    }
}

@Composable
private fun SingleMatchComponent(
    teamA: String,
    teamB: String,
    onNextRound: (String) -> Unit
) {

    val scoreA = rememberSaveable() { mutableStateOf("") }
    val scoreB = rememberSaveable() { mutableStateOf("") }
    val viewModel = EuroViewModel()

    Surface(
        modifier = Modifier
            .size(width = 140.dp, height = 77.dp)
            .padding(3.dp),
        color = Color.White,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(2.dp, Color.Gray),
        elevation = 5.dp
    ) {
        Column() {
            Row(
                modifier = Modifier.padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painterResource(id = viewModel.getCountryFlag(teamA)),
                    contentDescription = "flag",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = teamA,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))
                PoInputField(
                    valueState = scoreA,
                    modifier = Modifier.padding(end = 10.dp),
                )
            }
            Row(
                modifier = Modifier.padding(3.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painterResource(id = viewModel.getCountryFlag(teamB)),
                    contentDescription = "flag",
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = teamB,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(width = 5.dp, height = 1.dp))
                PoInputField(
                    valueState = scoreB,
                    modifier = Modifier.padding(end = 10.dp),
                )
            }
        }
    }
    LaunchedEffect(scoreA.value, scoreB.value) {
        if (scoreA.value.isNotEmpty() && scoreB.value.isNotEmpty()) {
            val winner = if (scoreA.value.toInt() > scoreB.value.toInt()) teamA else teamB
            onNextRound(winner)
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}