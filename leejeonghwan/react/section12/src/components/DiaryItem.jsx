import { useNavigate } from "react-router-dom";
import { getEmotionImage } from "../util/get-emotion-image";
import Button from "./Button";
import "./DiaryItem.css";

//const DiaryItem = () => {
const DiaryItem = ({ id, emotionId, createdDate, content }) => {
  //const emotionId = 5;
  const nav = useNavigate();

  const goDiaryPage = () => {
    nav(`/diary/${id}`);
  };

  const goEditPage = () => {
    nav(`/edit/${id}`);
  };

  return (
    <div className="DiaryItem">
      {/* <div className={`img_section img_section_${emotionId}`}> */}
      <div
        onClick={goDiaryPage}
        className={`img_section img_section_${emotionId}`}
      >
        <img src={getEmotionImage(emotionId)} />
      </div>
      {/* <div className="info_section"> */}
      <div onClick={goDiaryPage} className="info_section">
        <div className="created_date">
          {/* {new Date().toLocaleDateString()} */}
          {new Date(createdDate).toLocaleDateString()}
        </div>
        <div className="content">일기 컨텐츠</div>
      </div>
      <div className="button_section">
        {/* <Button text={"수정하기"} /> */}
        <Button onClick={goEditPage} text={"수정하기"} />
      </div>
    </div>
  );
};

export default DiaryItem;