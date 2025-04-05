'use client'

import Map from '@/components/Map'
import SelectedRoom from "@/components/Map/SelectedRoom";

export default function MapPage() {
	// const [selectedRoom, setSelectedRoom] = useState<RoomType | null>(null)

	return (
			<>
				{/*<Map setSelectedRoom={setSelectedRoom} />*/}
				<Map />
				{/*<SelectedRoom*/}
				{/*		selectedRoom={selectedRoom}*/}
				{/*		setSelectedRoom={setSelectedRoom}*/}
				{/*/>*/}
				<SelectedRoom />
			</>
	)
}
