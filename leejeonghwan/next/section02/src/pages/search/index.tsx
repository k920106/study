import {useRouter} from "next/router";

export default function Page() {
    const router = useRouter();
    console.log(router);
    const {name} = router.query
    return <h1>Search {name}</h1>;
}
