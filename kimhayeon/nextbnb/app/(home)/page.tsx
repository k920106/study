'use client'

import {RoomType} from "@/interface";
import CategoryList from "@/components/CategoryList";
import {GridLayout, RoomItem} from "@/components/RoomList";
import {Loader, LoaderGrid} from '@/components/Loader'
import React, {useEffect, useRef} from "react";
import useIntersectionObserver from "@/hooks/useIntersectionObserver";
import {useInfiniteQuery} from "react-query";
import {useRouter} from 'next/navigation'
import axios from "axios";
import {MapButton} from "@/components/Map";
import {useRecoilValue} from "recoil";
import {filterState} from "@/atom";

export default function Home() {
	const router = useRouter()
	const ref = useRef<HTMLDivElement | null>(null)
	const filterValue = useRecoilValue(filterState)
	const pageRef = useIntersectionObserver(ref, {})
	const isPageEnd = !!pageRef?.isIntersecting

	const filterParams = {
		location: filterValue.location,
		category: filterValue.category,
	}

	const fetchRooms = async ({pageParam = 1}) => {
		const {data} = await axios('/api/rooms?page=' + pageParam, {
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
	// } = useInfiniteQuery('rooms', fetchRooms, {
	} = useInfiniteQuery(['rooms', filterParams], fetchRooms, {
		getNextPageParam: (lastPage, pages) =>
				lastPage?.data?.length > 0 ? lastPage.page + 1 : undefined,
	})

	if (isError) {
		throw new Error('Room API Fetching Error')
	}

	useEffect(() => {
		let timerId: NodeJS.Timeout | undefined

		if (isPageEnd && hasNextPage) {
			timerId = setTimeout(() => {
				fetchNextPage()
			}, 500)
		}
	}, [fetchNextPage, hasNextPage, isPageEnd])

	return (
			<>
				<CategoryList/>
				<GridLayout>
					{isLoading || isFetching ? (
							<LoaderGrid/>
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
				{(isFetching || hasNextPage || isFetchingNextPage) && <Loader/>}
				<div className="w-full touch-none h-10 mb-10" ref={ref}/>
			</>
	);
}
