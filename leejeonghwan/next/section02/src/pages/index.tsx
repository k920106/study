//import "./index.css";
import style from "./index.module.css"

export default function Home() {
  //return <h1 style={{color: "red"}}>인덱스</h1>;
  return <h1 className={style.h1}>인덱스</h1>;
}
