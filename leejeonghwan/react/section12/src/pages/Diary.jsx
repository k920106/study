import { useParams } from "react-router-dom";

const Diary = () => {
  const params = useParams();
  console.log(params);

  //return <div>Diary</div>;
  return <div>{params.id}번 입니다</div>;
};

export default Diary;