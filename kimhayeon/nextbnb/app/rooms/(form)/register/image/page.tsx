'use client'

import { roomFormState } from '@/atom'
import { useRouter } from 'next/navigation'
import {useRecoilState, useResetRecoilState} from 'recoil'
import { v4 as uuidv4 } from 'uuid'

import { useForm } from 'react-hook-form'
import Stepper from '@/components/Form/Stepper'
import NextButton from '@/components/Form/NextButton'
import {AiFillCamera} from "react-icons/ai";
import toast from 'react-hot-toast'
import axios from 'axios'
import {useSession} from "next-auth/react";
import {useState} from "react";
import {getDownloadURL, ref, uploadString} from "@firebase/storage";
import {storage} from "@/utils/firebaseApp";

interface RoomImageProps {
	images?: string[]
}

// const IMAGE_URLS = [
// 	'https://a0.muscache.com/im/pictures/hosting/Hosting-1158302022899108659/original/f4c1c253-e353-4d99-b510-f9af15f8fb77.jpeg?im_w=960',
// 	'https://a0.muscache.com/im/pictures/hosting/Hosting-U3RheVN1cHBseUxpc3Rpbmc6MTE1ODMwMjAyMjg5OTEwODY1OQ%3D%3D/original/961de742-5e7f-475f-8186-b13fc8401fa6.jpeg?im_w=720',
// 	'https://a0.muscache.com/im/pictures/hosting/Hosting-U3RheVN1cHBseUxpc3Rpbmc6MTE1ODMwMjAyMjg5OTEwODY1OQ%3D%3D/original/b6145571-5c6d-4009-b2dd-0dc9d3bb8fc4.jpeg?im_w=720',
// 	'https://a0.muscache.com/im/pictures/hosting/Hosting-U3RheVN1cHBseUxpc3Rpbmc6MTE1ODMwMjAyMjg5OTEwODY1OQ%3D%3D/original/adb7afee-83bd-490a-a252-f7611c904d0f.jpeg?im_w=720',
// ]

export default function RoomRegisterImage() {
	const router = useRouter()
	const { data: session } = useSession()
	const [roomForm, setRoomForm] = useRecoilState(roomFormState)
	const [images, setImages] = useState<string[] | null>(null)
	const [disableSubmit, setDisableSubmit] = useState<boolean>(false)
	const resetRoomForm = useResetRecoilState(roomFormState)

	const {
		register,
		handleSubmit,
		setValue,
		// formState: { errors },
		formState: { errors, isSubmitting },
	} = useForm<RoomImageProps>()

	const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
		const {
			target: { files },
		} = e

		if (!files) return
		setImages([])

		Array.from(files).forEach((file: File) => {
			const fileReader = new FileReader()
			fileReader.readAsDataURL(file)

			fileReader.onloadend = (event: ProgressEvent<FileReader>) => {
				const { result } = event.target as FileReader
				if (result) {
					setImages((val) =>
							val ? [...val, result?.toString()] : [result?.toString()],
					)
				}
			}
		})
	}

	async function uploadImages(images: string[] | null) {
		const uploadedImageUrls = []

		if (!images) return

		for (const imageFile of images) {
			// 참조 만들기
			const imageRef = ref(storage, `${session?.user?.id}/${uuidv4()}`)
			try {
				// uploadString으로 firebase에 이미지 업로드하기
				const data = await uploadString(imageRef, imageFile, 'data_url')
				// downloadURL로 업로드 된 이미지 주소 가져오기
				const imageUrl = await getDownloadURL(data.ref)
				uploadedImageUrls.push(imageUrl)
			} catch (error) {
				console.error('Error uploading images: ', error)
			}
		}

		return uploadedImageUrls
	}

	const onSubmit = async (data: RoomImageProps) => {
		try {
			// const result = await axios.post('/api/rooms', {
			// 	...roomForm,
			// 	images: IMAGE_URLS,
			// })
			//
			// if (result.status === 200) {
			// 	toast.success('숙소를 등록했습니다.')
			// 	resetRoomForm()
			// 	router.push('/')
			// } else {
			// 	toast.error('데이터 생성중 문제가 발생했습니다.')
			// }
			setDisableSubmit(true)
			uploadImages(images)
					.then(async (imageUrls) => {
						const result = await axios.post('/api/rooms', {
							...roomForm,
							images: imageUrls,
						})

						if (result.status === 200) {
							toast.success('숙소를 등록했습니다.')
							resetRoomForm()
							router.push('/')
						} else {
							setDisableSubmit(false)
							toast.error('데이터 생성중 문제가 발생했습니다.')
						}
					})
					.catch((error) => {
						setDisableSubmit(false)
						console.error(error)
						toast.error('이미지 저장중에 문제가 발생했습니다. 다시 시도해주세요')
					})
		} catch (e) {
			setDisableSubmit(false)
			console.log(e)
			toast.error('다시 시도해주세요')
		}
	}

	return (
			<>
				<Stepper count={5} />
				<form
						className="mt-10 flex flex-col gap-6 px-4"
						onSubmit={handleSubmit(onSubmit)}
				>
					<h1 className="font-semibold text-lg md:text-2xl text-center">
						숙소의 사진을 추가해주세요
					</h1>
					<p className="text-sm md:text-base text-gray-500 text-center">
						숙소 사진은 최대 5장까지 추가할 수 있습니다.
					</p>
					<div className="flex flex-col gap-2">
						<div className="col-span-full">
							<div className="mt-2 flex justify-center rounded-lg border border-dashed border-gray-900/25 px-6 py-10">
								<div className="text-center">
									<AiFillCamera className="mx-auto h-12 w-12 text-gray-300" />
									<div className="mt-4 flex text-sm leading-6 text-gray-600">
										<label
												htmlFor="file-upload"
												className="relative cursor-pointer rounded-md bg-white font-semibold text-rose-600 focus-within:outline-none focus-within:ring-2 focus-within:ring-rose-600 focus-within:ring-offset-2 hover:text-rose-500"
										>
											<span>최대 5장의 사진을</span>
											{/*<input*/}
											{/*		id="file-upload"*/}
											{/*		name="file-upload"*/}
											{/*		type="file"*/}
											{/*		className="sr-only"*/}
											{/*/>*/}
											<input
													id="file-upload"
													type="file"
													multiple
													accept="image/*"
													className="sr-only"
													{...register('images', { required: true })}
													onChange={handleFileUpload}
											/>
										</label>
										<p className="pl-1">업로드 해주세요</p>
									</div>
									<p className="text-xs leading-5 text-gray-600">
										PNG, JPG, GIF 등 이미지 포맷만 가능
									</p>
								</div>
							</div>
						</div>
						{errors?.images && errors?.images?.type === 'required' && (
								<span className="text-red-600 text-sm">필수 항목입니다.</span>
						)}
					</div>
					<div className="mt-10 max-w-lg mx-auto flex flex-wrap gap-4">
						{images &&
								images?.map((image, index) => (
										<img
												key={index}
												src={image}
												alt="미리보기"
												width={100}
												height={100}
												className="rounded-md"
										/>
								))}
					</div>
					<NextButton
							type="submit"
							text="완료"
							disabled={isSubmitting || disableSubmit}
					/>
				</form>
			</>
	)
}
