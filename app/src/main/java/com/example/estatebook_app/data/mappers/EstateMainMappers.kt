package com.example.estatebook_app

import com.example.estatebook_app.data.local.EstateMainEntity
import com.example.estatebook_app.data.remote.EstateMain
import com.example.estatebook_app.data.remote.EstatesMainDto

fun EstatesMainDto.toEstateMainEntity(): EstateMainEntity { //функция расширение к Dto которая возвращает entity для базы данных
    return EstateMainEntity(
        ID_Estate = ID_Estate,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
        Price_For_Month = Price_For_Month,
        Mortgage_Price = Mortgage_Price,
        Area = Area,
        House_Area = House_Area,
        Metro_Station = Metro_Station,
        Train_Station = Train_Station,
        Description = Description,
        Ad_Date = Ad_Date,
        Building_Date = Building_Date,
        Status = Status,
        Estate_Rating = 0, // Значение по умолчанию для Estate_Rating
        Estate_Images_ID = Estate_Images_ID,
        User_ID = User_ID,
        Renovation = null, // Значение по умолчанию для Renovation
        Room_Amount = null, // Значение по умолчанию для Room_Amount
        Purpose = null, // Значение по умолчанию для Purpose
        Possible_Purpose = null, // Значение по умолчанию для Possible_Purpose
        Building_Type = null, // Значение по умолчанию для Building_Type
        Address = null, // Значение по умолчанию для Address
        Floor_Amount = null, // Значение по умолчанию для Floor_Amount
        Floor = null, // Значение по умолчанию для Floor
        Entrance = null, // Значение по умолчанию для Entrance
        City = null // Значение по умолчанию для City
    )
}

fun EstateMainEntity.toEstateMain(): EstateMain {
    return EstateMain(
        ID_Estate = ID_Estate,
        Ad_Name = Ad_Name,
        Location = Location,
        Price = Price,
        Price_For_Month = Price_For_Month,
        Mortgage_Price = Mortgage_Price,
        Area = Area,
        House_Area = House_Area,
        Metro_Station = Metro_Station,
        Train_Station = Train_Station,
        Description = Description,
        Ad_Date = Ad_Date,
        Building_Date = Building_Date,
        Status = Status,
        Estate_Rating = 0, // Присвойте значение по умолчанию или введите свою логику
        Estate_Images_ID = Estate_Images_ID,
        User_ID = User_ID,
        Renovation = null, // Добавьте значения по умолчанию или введите свою логику
        Room_Amount = null, // Добавьте значения по умолчанию или введите свою логику
        Purpose = null, // Добавьте значения по умолчанию или введите свою логику
        Possible_Purpose = null, // Добавьте значения по умолчанию или введите свою логику
        Building_Type = null, // Добавьте значения по умолчанию или введите свою логику
        Address = null, // Добавьте значения по умолчанию или введите свою логику
        Floor_Amount = null, // Добавьте значения по умолчанию или введите свою логику
        Floor = null, // Добавьте значения по умолчанию или введите свою логику
        Entrance = null, // Добавьте значения по умолчанию или введите свою логику
        City = null // Добавьте значения по умолчанию или введите свою логику
    )
}