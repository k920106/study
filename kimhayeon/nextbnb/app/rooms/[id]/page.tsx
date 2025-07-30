import FeatureSection from '@/components/RoomDetail/FeatureSection'
import HeaderSection from '@/components/RoomDetail/HeaderSection'
import { ParamsProps, RoomType } from '@/interface'
import MapSection from '@/components/RoomDetail/MapSection'
import Comment from '@/components/Comment'
import { Metadata, ResolvingMetadata } from 'next'

export default async function RoomPage({ params }: ParamsProps) {
  const { id } = params
  const data: RoomType = await getData(id)
  return (
    <div className="mt-28 mb-20 max-w-6xl mx-auto">
      <HeaderSection data={data} />
      <FeatureSection data={data} />
      <Comment room={data} />
      <MapSection data={data} />
    </div>
  )
}

async function getData(id: string) {
  try {
    const res = await fetch(
      `${process.env.NEXT_PUBLIC_API_URL}/api/rooms?id=${id}`,
      {
        next: {
          revalidate: 60 * 60,
        },
      },
    )

    if (!res.ok) {
      throw new Error('Failed to fetch data')
    }

    return res.json()
  } catch (error) {
    console.error(error)
  }
}

export async function generateMetadata(
  { params }: ParamsProps,
  parent: ResolvingMetadata,
): Promise<Metadata> {
  // read route params
  const id = params.id

  // fetch data
  const room = await fetch(
    `${process.env.NEXT_PUBLIC_API_URL}/api/rooms?id=${id}`,
    {
      next: {
        revalidate: 60 * 60,
      },
    },
  ).then((res) => res.json())

  // optionally access and extend (rather than replace) parent metadata
  const prevKeywords = (await parent)?.keywords || []

  return {
    title: `Nextbnb 숙소 - ${room?.title}`,
    description: room?.description,
    keywords: [room?.category, ...prevKeywords],
  }
}
