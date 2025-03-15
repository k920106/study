"use client";

import {startTransition, useEffect} from "react";
import {useRouter} from "next/navigation";

export default function Error({
	error,
	reset,
}: {
	error: Error;
	reset: () => void;
}) {
	const router = useRouter();

	useEffect(() => {
		console.log(error.message)
	}, [error]);

	return (
			<div>
				<h3>상세 과정에서 오류가 발생했습니다</h3>
				<button onClick={() => {
					startTransition(() => {
						router.refresh(); // 현재 페이지에 필요한 서버 컴포넌트들을 다시 불러옴
						reset(); // 에러 상태를 초기화, 컴포넌트들을 다시 렌더링
					})
				}}>재시도</button>
			</div>
	)
}
