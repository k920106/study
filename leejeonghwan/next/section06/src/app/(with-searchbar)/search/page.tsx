import BookItem from "@/components/book-item";
import {BookData} from "@/types";
import {delay} from "@/util/delay";
import {Suspense} from "react";

async function SearchResult({ q }: { q: string }) {
  await delay(1500);
  const response = await fetch(
      `${process.env.NEXT_PUBLIC_API_SERVER_URL}/book/search?q=${q}`,
      { cache: "force-cache" }
  );
  if (!response.ok) {
    return <div>오류가 발생했습니다...</div>;
  }

  const books: BookData[] = await response.json();

  return (
      <div>
        {books.map((book) => (
            <BookItem key={book.id} {...book} />
        ))}
      </div>
  );
}

export default function Page({
  searchParams,
}: {
  searchParams: {q: string}
}) {
  const { q } = searchParams;

  return (
      <Suspense key={q} fallback={<div>Loading...</div>}>
        <SearchResult q={q} />
      </Suspense>
  )
}
