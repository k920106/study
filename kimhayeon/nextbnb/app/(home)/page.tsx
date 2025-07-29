'use client'

import { RoomType } from '@/interface'
import CategoryList from '@/components/CategoryList'
import { GridLayout, RoomItem } from '@/components/RoomList'
import { Loader, LoaderGrid } from '@/components/Loader'
import React, { useEffect, useRef } from 'react'
import useIntersectionObserver from '@/hooks/useIntersectionObserver'
import { useInfiniteQuery } from 'react-query'
import { useRouter } from 'next/navigation'
import axios from 'axios'
import { MapButton } from '@/components/Map'
import { useRecoilValue } from 'recoil'
import { filterState } from '@/atom'

export default function Home() {
  const router = useRouter()
  const ref = useRef<HTMLDivElement | null>(null)
  const filterValue = useRecoilValue(filterState)
  const pageRef = useIntersectionObserver(ref, {
    rootMargin: '-100px 0px',
  })
  const isPageEnd = !!pageRef?.isIntersecting

  const filterParams = {
    location: filterValue.location,
    category: filterValue.category,
  }

  const fetchRooms = async ({ pageParam = 1 }) => {
    const { data } = await axios('/api/rooms?page=' + pageParam, {
      params: {
        limit: 12,
        page: pageParam,
        ...filterParams,
      },
    })

    return data
  }

  const {
    data: rooms,
    isFetching,
    fetchNextPage,
    isFetchingNextPage,
    hasNextPage,
    isError,
    isLoading,
  } = useInfiniteQuery(['rooms', filterParams], fetchRooms, {
    getNextPageParam: (lastPage, pages) =>
      lastPage?.data?.length > 0 ? lastPage.page + 1 : undefined,
  })

  if (isError) {
    throw new Error('Room API Fetching Error')
  }

  useEffect(() => {
    let timerId: NodeJS.Timeout | undefined

    // 최소한의 콘텐츠가 로드되었는지 확인
    const hasMinimumContent =
      rooms?.pages &&
      rooms.pages.length > 0 &&
      rooms.pages[0]?.data?.length >= 12

    // 전체 로드된 아이템 수 확인 (추가 안전장치)
    const totalItems =
      rooms?.pages?.reduce(
        (total, page) => total + (page?.data?.length || 0),
        0,
      ) || 0
    const hasEnoughItems = totalItems >= 12

    if (isPageEnd && hasNextPage && hasMinimumContent && hasEnoughItems) {
      timerId = setTimeout(() => {
        fetchNextPage()
      }, 500)
    }
    return () => {
      if (timerId) clearTimeout(timerId)
    }
  }, [fetchNextPage, hasNextPage, isPageEnd, rooms?.pages])

  return (
    <>
      <CategoryList />
      <GridLayout>
        {isLoading || isFetching ? (
          <LoaderGrid />
        ) : (
          rooms?.pages?.map((page, index) => (
            <React.Fragment key={index}>
              {page?.data?.map((room: RoomType) => (
                <RoomItem key={room.id} room={room} />
              ))}
            </React.Fragment>
          ))
        )}
      </GridLayout>
      <MapButton onClick={() => router.push('/map')} />
      {(isFetching || hasNextPage || isFetchingNextPage) && <Loader />}
      <div className="w-full touch-none h-10 mb-10" ref={ref} />
    </>
  )
}
