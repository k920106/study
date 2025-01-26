import "./App.css";
import { Routes, Route } from "react-router-dom";
import {createContext, useEffect, useReducer, useRef, useState} from "react";
import Home from "./pages/Home";
import Diary from "./pages/Diary";
import New from "./pages/New";
import Edit from "./pages/Edit";
import Notfound from "./pages/Notfound";

//const mockData = [
//  {
//    id: 1,
//    createdDate: new Date("2025-01-10").getTime(),
//    emotionId: 1,
//    content: "1번 일기 내용",
//  },
//  {
//    id: 2,
//    createdDate: new Date("2025-01-11").getTime(),
//    emotionId: 2,
//    content: "2번 일기 내용",
//  },
//  {
//    id: 3,
//    createdDate: new Date("2024-12-07").getTime(),
//    emotionId: 3,
//    content: "3번 일기 내용",
//  },
//];

function reducer(state, action) {
  let nextState;

  switch (action.type) {
    case "INIT":
      return action.data;
    case "CREATE":
      // return [action.data, ...state];
      nextState = [action.data, ...state];
      break;
    case "UPDATE":
      // return state.map((item) =>
      //   String(item.id) === String(action.data.id)
      //     ? action.data
      //     : item
      // );
      nextState = state.map((item) =>
          String(item.id) === String(action.data.id)
              ? action.data
              : item
      );
      break;
    case "DELETE":
      // return state.filter(
      //   (item) => String(item.id) !== String(action.id)
      // );
      nextState = state.filter(
          (item) => String(item.id) !== String(action.id)
      );
      break;
    default:
      return state;
  }

  localStorage.setItem("diary", JSON.stringify(nextState));
  return nextState;
}

export const DiaryStateContext = createContext();
export const DiaryDispatchContext = createContext();

function App() {
  const [isLoading, setIsLoading] = useState(true);
  // const [data, dispatch] = useReducer(reducer, mockData);
  const [data, dispatch] = useReducer(reducer, []);
  // const idRef = useRef(3);
  const idRef = useRef(0);

  useEffect(() => {
    const storedData = localStorage.getItem("diary");
    if (!storedData) {
      setIsLoading(false);
      return;
    }

    const parsedData = JSON.parse(storedData);
    if (!Array.isArray(parsedData)) {
      setIsLoading(false);
      return;
    }

    let maxId = 0;
    parsedData.forEach((item) => {
      if (Number(item.id) > maxId) {
        maxId = item.id;
      }
    });

    idRef.current = maxId + 1;

    dispatch({
      type: "INIT",
      data: parsedData,
    });
    setIsLoading(false);
  }, []);

  // 새로운 일기 추가
  const onCreate = (createdDate, emotionId, content) => {
    dispatch({
      type: "CREATE",
      data: {
        id: idRef.current++,
        createdDate,
        emotionId,
        content,
      },
    });
  };

  // 기존 일기 수정
  const onUpdate = (id, createdDate, emotionId, content) => {
    dispatch({
      type: "UPDATE",
      data: {
        id,
        createdDate,
        emotionId,
        content,
      },
    });
  };

  // 기존 일기 삭제
  const onDelete = (id) => {
    dispatch({
      type: "DELETE",
      id,
    });
  };

  if (isLoading) {
    return <div>데이터 로딩중입니다 ...</div>;
  }

  return (
    <>
      <DiaryStateContext.Provider value={data}>
        <DiaryDispatchContext.Provider
          value={{
            onCreate,
            onUpdate,
            onDelete,
          }}
        >
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/new" element={<New />} />
            <Route path="/diary/:id" element={<Diary />} />
            <Route path="/edit/:id" element={<Edit />} />
            <Route path="*" element={<Notfound />} />
          </Routes>
        </DiaryDispatchContext.Provider>
      </DiaryStateContext.Provider>
    </>
  );
}

export default App;
