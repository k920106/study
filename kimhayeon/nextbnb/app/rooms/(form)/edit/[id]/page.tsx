import RoomEditForm from '@/components/Form/RoomEditForm'
import { RoomType } from '@/interface'

interface ParamsProps {
  params: { id: string }
}

export default async function RoomEdit({ params }: ParamsProps) {
  const id = params.id
  const data: RoomType = await getData(id)
  return <RoomEditForm data={data} />
}

async function getData(id: string) {
  try {
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/api/rooms?id=${id}`,
      {
        cache: 'no-cache',
      },
    )

    if (!res.ok) {
      throw new Error('Failed to fetch data')
    }

    return res.json()
  } catch (error) {
    console.error('Failed to fetch room data:', error)
    // Return fallback data to prevent build failures
    return {
      id: '',
      title: '',
      category: '',
      description: '',
      price: 0,
      address: '',
      lat: 0,
      lng: 0,
      images: [],
      amenities: [],
      freeCancel: false,
      selfCheckIn: false,
      officeSpace: false,
      hasMountainView: false,
      hasShampoo: false,
      hasFreeLaundry: false,
      hasAirConditioner: false,
      hasWifi: false,
      hasBarbeque: false,
      hasFreeParking: false,
      user: null,
      userId: '',
      comments: [],
    }
  }
}
