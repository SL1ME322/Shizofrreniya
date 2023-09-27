package com.example.estatebook_app



//@Factory
//class GetAllEstatesPaged( private val estatesRepository: EstatesRepository) {
//    fun call(): Flow<PagingData<EstateMain>> = Pager(
//        PagingConfig(pageSize = 10, prefetchDistance = 20)
//    )
//    {
//        estatesRepository.loadAllEstatesPaged()
//    }.flow.map { value : PagingData<EstateMainEntity> ->
//        value.map{
//        estateEntity: EstateMainEntity -> estateEntity.toEstateMain()
//    } }
//
//}