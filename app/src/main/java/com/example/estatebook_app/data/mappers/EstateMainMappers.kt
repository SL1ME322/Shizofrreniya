package com.example.estatebook_app

import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.data.remote.EstateMain
import com.example.estatebook_app.data.remote.EstatesMainDto

fun EstatesMainDto.toEstateMainEntity(): EstateMainEntity { //функция расширение к Dto которая возвращает entity для базы данных
    return EstateMainEntity( ID_Estate = ID_Estate,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
        Price_For_Month = Price_For_Month,
        Mortgage_Price = Mortgage_Price,
        Area = Area,
        House_Area =House_Area,
        Metro_Station = Metro_Station,
        Train_Station = Train_Station,
        Description = Description,
        Ad_Date = Ad_Date,
        Building_Date = Building_Date,


        Status = Status ,  //Estate_Rating = 0,
        Estate_Images_ID = Estate_Images_ID,
        User_ID = User_ID
    )
}

fun EstateMainEntity.toEstateMain() : EstateMain {   //преобразование таблицы бд в обычный класс
    return EstateMain( ID_Estate = ID_Estate,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
         Price_For_Month = Price_For_Month,
        Mortgage_Price = Mortgage_Price,
        Area = Area,
        House_Area =House_Area,
        Metro_Station = Metro_Station,
        Train_Station = Train_Station,
        Description = Description,
        Ad_Date = Ad_Date,
        Building_Date = Building_Date,


        Status = Status ,  //Estate_Rating = 0.0,
        Estate_Images_ID = Estate_Images_ID,
        User_ID = User_ID
 )

// )
}