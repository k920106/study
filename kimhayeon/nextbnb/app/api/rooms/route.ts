import prisma from '@/db'
import {NextResponse} from 'next/server'
import {getServerSession} from "next-auth";
import {authOptions} from "@/app/api/auth/[...nextauth]/route";
import axios from 'axios'

export async function GET(req: Request) {
	const { searchParams } = new URL(req.url)
	const page = searchParams.get('page') as string
	const limit = searchParams.get('limit') as string
	const id = searchParams.get('id') as string

	const session = await getServerSession(authOptions)

	if (id) {
		// 상세 페이지 로직
		const room = await prisma.room.findFirst({
			where: {
				id: parseInt(id),
			},
			include: {
				likes: {
					where: session ? { userId: session?.user?.id } : {},
				},
			},
		})

		return NextResponse.json(room, {
			status: 200,
		})
	} else if (page) {
		// 무한 스크롤 로직
		const count = await prisma.room.count()
		const skipPage = parseInt(page) - 1
		const rooms = await prisma.room.findMany({
			// orderBy: { id: 'asc' },
			orderBy: { createdAt: 'desc' },
			take: parseInt(limit),
			skip: skipPage * parseInt(limit),
		})

		return NextResponse.json(
				{
					page: parseInt(page),
					data: rooms,
					totalCount: count,
					totalPage: Math.ceil(count / parseInt(limit)),
				},
				{ status: 200 },
		)
	}

	const data = await prisma.room.findMany()

	return NextResponse.json(data, {
		status: 200,
	})
}

export async function POST(req: Request) {
	const session = await getServerSession(authOptions)

	if (!session?.user) {
		return NextResponse.json({ error: 'unauthorized user' }, { status: 401 })
	}

	// 데이터 생성 처리
	const formData = await req.json()
	const headers = {
		Authorization: `KakaoAK ${process.env.KAKAO_CLIENT_ID}`,
	}

	// const { data } = await axios.get(
	// 		`https://dapi.kakao.com/v2/local/search/address.json?query=${encodeURI(
	// 				formData.address,
	// 		)}`,
	// 		{
	// 			headers,
	// 		},
	// )

	const result = await prisma.room.create({
		data: {
			...formData,
			price: parseInt(formData.price),
			userId: session?.user?.id,
			// lat: data.documents[0].y,
			lat: '37.5563255',
			// lng: data.documents[0].x,
			lng: '126.8577302',
		},
	})

	return NextResponse.json(result, { status: 200 })
}
