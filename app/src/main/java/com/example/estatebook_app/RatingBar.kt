package com.example.estatebook_app

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.lang.Math.ceil
import java.lang.Math.floor

//@Composable
//fun RatingBar(
//    modifier: Modifier,
//    rating: Float,
//    spaceBetween: Dp = 0.dp
//){
//    val image = ImageBitmap.imageResource(id = R.drawable.star)
//    val imageFull = ImageBitmap.imageResource(id = R.drawable.star)
//    val totalCount = 5
//    val height = LocalDensity.current.run{image.height.toDp()}
//    val width = LocalDensity.current.run { image.width.toDp() }
//    val space = LocalDensity.current.run{spaceBetween.toPx()}
//    val totalWidth = width * totalCount  + spaceBetween * (totalCount - 1) //ширина + промежутки
//
//    Box(modifier = Modifier.width(250.dp).height(250.dp).drawBehind{
//        drawRating(5f, image, imageFull, 5f)
//    })
//}
//
//private fun DrawScope.drawRating(
//    rating:Float,
//    image: ImageBitmap,
//    imageFull: ImageBitmap,
//    space: Float
//){
//    val totalCount = 5
//    val imageWidth = image.width.toFloat()
//    val imageHeight = size.height
//    val reminder =  rating.toInt()
//    val ratingInt = (rating - reminder).toInt()
//    for (i in 0 until totalCount){
//        val start = imageWidth * i + space * i
//        drawImage(image = image, topLeft = Offset(start,0f) )
//    }
//}

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
    starsColor: Color = Color.Yellow,
) { //4.25
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    val halfStar = !(rating.rem(1).equals(0.0))
    val quarterStart = halfStar && !(rating.rem(0.5).equals(0.0))
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon(imageVector = Icons.Outlined.Star, contentDescription = null, tint = starsColor, modifier = Modifier.size(65.dp))
        }

        if (halfStar && !quarterStart) {
            Icon( modifier = Modifier.size(65.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
        //if (quarterStart) {
        //    Icon(
        //        imageVector = Icons.Outlined.Home,
        //        contentDescription = null,
        //        tint = starsColor
        //    )
        //}
        repeat(unfilledStars) {
            Icon( modifier = Modifier.size(45.dp),
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = starsColor
            )
        }
    }
}
