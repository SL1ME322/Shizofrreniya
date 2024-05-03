package com.example.estatebook_app
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.estatebook_app.data.local.EstateMainEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
//view model нужен для сохранения данных в ui при изменении состояния приложения
val _searchQuery = MutableStateFlow<String?>("")
@HiltViewModel
class EstateViewModel @Inject constructor(
    pager: Pager<Int, EstateMainEntity>,
) : ViewModel() {

    val estatePagingFlow = pager.flow.map { pagingData -> pagingData.map { it.toEstateMain() } }
        .cachedIn(viewModelScope)

    // Function to update the search query
    fun setSearchQuery(query: String?) {
        _searchQuery.value = query
    }
}