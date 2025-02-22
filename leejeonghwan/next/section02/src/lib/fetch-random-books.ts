import { BookData } from "@/types";

export default async function fetchRandomBooks(): Promise<
		BookData[]
> {
	// const url = `http://localhost:12345/book/random`;
	const url = `https://onebite-books-server-jet.vercel.app/book/random`;

	try {
		const response = await fetch(url);
		return await response.json();
	} catch (err) {
		console.error(err);
		return [];
	}
}
