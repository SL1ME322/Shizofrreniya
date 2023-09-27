package com.example.estatebook_app

import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.data.remote.EstateMain
import com.example.estatebook_app.data.remote.EstatesMainDto

fun EstatesMainDto.toEstateMainEntity(): EstateMainEntity { //функция расширение к Dto которая возвращает entity для базы данных
    return EstateMainEntity(
        id = id,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
        Area = Area,
        Rating = Rating
        //Estate_Images_ID = Estate_Images_ID
    )
}

fun EstateMainEntity.toEstateMain() : EstateMain { //преобразование таблицы бд в обычный класс
    return EstateMain( id = id,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
        Area = Area,
        Rating = Rating,)
        //Estate_Images_ID = Estate_Images_ID
// )
}