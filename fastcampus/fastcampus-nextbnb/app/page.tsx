'use client'

import {RoomType} from "@/interface";
import CategoryList from "@/components/CategoryList";
import {GridLayout, RoomItem} from "@/components/RoomList";
import { useQuery } from 'react-query'
import Loader from '@/components/Loader'

// export default async function Home() {
export default function Home() {
	// const data: RoomType[] = await getRooms();
	const fetchRoom = async () => {
		const data = await fetch('/api/rooms')
		return data.json()
	}

	const { data, isError, isLoading } = useQuery('rooms', fetchRoom)

	if (isLoading) {
		return <Loader className="mt-60 mb-40" />
	}

	return (
			<>
				<CategoryList />
				<GridLayout>
					{/*{data?.map((room) => <RoomItem room={room} key={room.id} />)}*/}
					{data?.map((room: RoomType) => <RoomItem room={room} key={room.id}/>)}
				</GridLayout>
			</>
	)
}

// async function getRooms() {
// 	const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/rooms`, {
// 		cache: 'force-cache',
// 	})
//
// 	if (!res.ok) {
// 		throw new Error('Failed to fetch')
// 	}
//
// 	return res.json()
// }
