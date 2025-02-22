import { BookData } from "@/types";

export default async function fetchOneBook(
		id: number
): Promise<BookData | null> {
	// const url = `http://localhost:12345/book/${id}`;
	const url = `https://onebite-books-server-jet.vercel.app/book/${id}`;

	try {
		const response = await fetch(url);
		if (!response.ok) throw new Error("");

		return await response.json();
	} catch (err) {
		console.error(err);
		return null;
	}
}
