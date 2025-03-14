import style from "./page.module.css";
import {BookData, ReviewData} from "@/types";
import Image from "next/image";
import {notFound} from "next/navigation";
import ReviewItem from "@/components/review-item";
import ReviewEditor from "@/components/review-editor";

export function generateStaticParams() {
	return [{id: "1"}, {id: "2"}, {id: "3"}];
}

async function BookDetail({bookId}: { bookId: string }) {
	const response = await fetch(`${process.env.NEXT_PUBLIC_API_SERVER_URL}/book/${bookId}`);
	if (!response.ok) {
		if (response.status === 404) {
			notFound();
		}
		return <div>오류가 발생했습니다...</div>;
	}

	const book: BookData = await response.json();

	const {title, subTitle, description, author, publisher, coverImgUrl} = book;

	return (
			<section>
				<div
						className={style.cover_img_container}
						style={{backgroundImage: `url('${coverImgUrl}')`}}
				>
					<Image src={coverImgUrl} alt={`${title} 표지`} width={300} height={500} priority/>
				</div>
				<div className={style.title}>{title}</div>
				<div className={style.subTitle}>{subTitle}</div>
				<div className={style.author}>
					{author} | {publisher}
				</div>
				<div className={style.description}>{description}</div>
			</section>
	);
}

// function ReviewEditor({bookId}: { bookId: string }) {
// 	return (
// 			<section>
// 				<form action={createReviewAction}>
// 					<input name="bookId" value={bookId} hidden readOnly/>
// 					<input name="content" placeholder="리뷰 내용" required/>
// 					<input name="author" placeholder="작성자" required/>
// 					<button type="submit">작성하기</button>
// 				</form>
// 			</section>
// 	);
// }

async function ReviewList({bookId}: { bookId: string }) {
	const response = await fetch(
			`${process.env.NEXT_PUBLIC_API_SERVER_URL}/review/book/${bookId}`
	);
	if (!response.ok) {
		throw new Error(`Review fetch failed: ${response.statusText}`);
	}

  const reviews: ReviewData[] = await response.json();
  return (
      <section>
        {reviews.map((review) => (
            <ReviewItem key={`review-item-${review.id}`} {...review} />
        ))}
      </section>
  );
}

export default async function Page({
																		 params,
																	 }: {
	params: Promise<{ id: string }>
}) {
	const {id} = await params;
	return (
			<div className={style.container}>
				<BookDetail bookId={id}/>
				<ReviewEditor bookId={id}/>
				<ReviewList bookId={id}/>
			</div>
	);
}
