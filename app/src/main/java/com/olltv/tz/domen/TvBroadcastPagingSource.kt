package com.olltv.tz.domen

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.olltv.tz.Resource
import com.olltv.tz.domen.models.ITvBroadcastRepository
import com.olltv.tz.domen.models.TvBroadcast

class TvBroadcastPagingSource(
    private val tvBroadcastRepository: ITvBroadcastRepository,
    private val serialNumber: String
) : PagingSource<Long, TvBroadcast>() {
    override suspend fun load(
        params: LoadParams<Long>
    ): LoadResult<Long, TvBroadcast> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 0
        val resource = tvBroadcastRepository.getTvBroadcasts(serialNumber, nextPageNumber, 1)
        when (resource) {
            is Resource.Success -> {
                return LoadResult.Page(
                    data = resource.data.items,
                    prevKey = null,
                    nextKey = resource.data.items.last().id
                )
            }
            is Resource.Error -> {
                return LoadResult.Error(resource.exception)
            }
            else -> throw IllegalStateException("Not supported PagingSource state: $resource")
        }
    }

    override fun getRefreshKey(state: PagingState<Long, TvBroadcast>): Long? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}