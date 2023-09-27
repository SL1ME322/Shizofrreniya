package com.example.estatebook_app
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.estatebook_app.data.local.EstateMainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject
//view model нужен для сохранения данных в ui при изменении состояния приложения
@HiltViewModel
class EstateViewModel @Inject constructor( //внедрение pager'а во viewmodel для сохранения состояния в кэше
    pager: Pager<Int, EstateMainEntity>
) : ViewModel() { //pageflow возвращает поток с контейнерами Paging Data<EstateMainEntity>
    val estatePagingFlow = pager.flow.map { pagingData -> pagingData.map { it.toEstateMain() }  } //mapping в estate main для отображения в ui (как понял)
         .cachedIn(viewModelScope) //viewModelScope - корутина


}