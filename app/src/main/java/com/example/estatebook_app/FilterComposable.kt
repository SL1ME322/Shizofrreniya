package com.example.estatebook_app
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Filter(){
    var minPriceInput by remember { mutableStateOf("0") }
    var minPriceInputStart by remember { mutableStateOf("0") }
    var minPriceInputEnd by remember { mutableStateOf("100000000") }
    //var sliderMinPrice by remember {
    //    mutableStateOf(0f)
    var sliderMinPrice by remember {
        mutableStateOf(0f..100000000f)
    }
   /* Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    startY = 0f,
                    endY = 2000f,
                    tileMode = TileMode.Clamp,
                    colors = listOf(Color(94, 178, 148), Color(21, 225, 176, 200))
                )
            )
    ) {

        //var minAreaStart by remember { mutableStateOf("0")}
        //var minAreaEnd by remember { mutableStateOf("10000f")}
        //var sliderArea by remember { //mutableStateOf(0f, 100000000f)
        //}
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            var regionClass = GetRegionsList();
            var regionList = regionClass.regions
            var filteringOptions by remember { mutableStateOf(regionList) }
            var expand by remember { mutableStateOf(false) }
            var selectedOptionText by remember { mutableStateOf("") }
            Column( horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    Modifier
                        .background(Color.Red)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .height(45.dp)
                            .width(45.dp),
                        painter = painterResource(id = R.drawable.filtercity),
                        contentDescription = ""
                    )
                    Text("Где искать", )
                }

                ExposedDropdownMenuBox(
                    expanded = expand,
                    onExpandedChange = { expand = !expand },
                ) {
                    OutlinedTextField(
                        value = selectedOptionText,
                        onValueChange = { selectedOptionText = it },
                        singleLine = true,
                        label = { Text("Регион") })
                    filteringOptions =
                        regionList.filter { it.contains(selectedOptionText, ignoreCase = true) }
                    ExposedDropdownMenu(
                        expanded = expand, onDismissRequest = { expand = false }

                    ) {

                        filteringOptions.forEach { selected ->
                            DropdownMenuItem(onClick = {
                                selectedOptionText = selected

                            }) {

                                Text(
                                    text = selected, style =
                                    if (regionList.indexOf(selected) == 0 || regionList.indexOf(
                                            selected
                                        ) == 1
                                    ) {
                                        TextStyle(fontWeight = FontWeight.Bold)
                                    } else {
                                        TextStyle(fontWeight = FontWeight.Normal)
                                    }
                                )


                            }

                        }
                    }
                }
            }

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(45.dp)
                        .width(45.dp),
                    painter = painterResource(id = R.drawable.filterestatetype),
                    contentDescription = ""
                )
                Text("Тип недвижимости")
            }
            ExposedDropdownMenuBox(
                expanded = expand,
                onExpandedChange = { expand = !expand },
            ) {
                OutlinedTextField(
                    value = selectedOptionText,
                    onValueChange = { selectedOptionText = it },
                    singleLine = true,
                    label = { Text("Регион") })
                filteringOptions =
                    regionList.filter { it.contains(selectedOptionText, ignoreCase = true) }
                ExposedDropdownMenu(
                    expanded = expand, onDismissRequest = { expand = false }

                ) {

                    filteringOptions.forEach { selected ->
                        DropdownMenuItem(onClick = {
                            selectedOptionText = selected

                        }) {

                            Text(
                                text = selected, style =
                                if (regionList.indexOf(selected) == 0 || regionList.indexOf(
                                        selected
                                    ) == 1
                                ) {
                                    TextStyle(fontWeight = FontWeight.Bold)
                                } else {
                                    TextStyle(fontWeight = FontWeight.Normal)
                                }
                            )


                        }

                    }
                }
            }
            Row() {

                Slider(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    value = sliderMinPrice.start,
                    onValueChange = { it -> sliderMinPrice = it..sliderMinPrice.endInclusive
                               minPriceInput = "%.0f".format(it)}       ,
                    valueRange = 0f..100000000f
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.3f),
                    value = minPriceInputStart,
                    onValueChange = { minPriceInputStart = it },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            try {
                                val startRange = minPriceInputStart.toFloat()
                                sliderMinPrice = sliderMinPrice.endInclusive..startRange

                            } catch (e: NumberFormatException) {
                            }
                        }
                    )
                )
                    RangeSlider(
                    modifier = Modifier.fillMaxWidth(0.3f),
                    value = sliderMinPrice,
                    onValueChange = { range ->
                        sliderMinPrice = range

                        minPriceInputStart = "%.0f".format(sliderMinPrice.start)
                        minPriceInputEnd = "%.0f".format(sliderMinPrice.endInclusive)
                    },

                    valueRange = 0f..100000000f
                )
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.7f),
                    value = minPriceInputEnd,
                    onValueChange = {
                        minPriceInputEnd = it
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            try {

                                val endRange = minPriceInputEnd.toFloat()

                                sliderMinPrice = sliderMinPrice.start..endRange

                            } catch (e: NumberFormatException) {
                            }
                        }

                    ))
                OutlinedTextField(modifier = Modifier.fillMaxWidth(0.7f),
                   value = minPriceInput,
                   onValueChange = {
                       minPriceInput = it
                   },
                   keyboardOptions = KeyboardOptions(
                       keyboardType = KeyboardType.Number,
                       imeAction = ImeAction.Done,
                   ),
                   keyboardActions = KeyboardActions(
                       onDone =  {
                           try{
                               //sliderMinPrice = minPriceInput.toFloat()
                           }
                           catch (e:java.lang.NumberFormatException){

                           }
                       }

                ))
            }

        }
    }*/
}