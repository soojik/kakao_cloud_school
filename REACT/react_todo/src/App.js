import ToDoTemplate from "./components/ToDoTemplate";
import ToDoInsert from "./components/ToDoInsert";
import ToDoList from "./components/ToDoList";

import './components/ToDoTemplate.scss';
// useRef는 변수를 생성하거나 변수를 만들어서 DOM에 할당하기 위해
// useCallback은 함수를 효율적으로 생성하기 위해서
import { useRef, useCallback, useReducer } from "react";

// 대량의 데이터를 생성해서 리턴하는 함수
const createBulkTodos = () => {
  const array = [];

  for (let i = 1; i <= 2000; i++) {
    array.push({
      id: i,
      text: `할 일 ${i}`,
      checked: false
    })
  }

  return array;
}

// state 조작할 reducer 함수 생성
const todoReducer = (todos, action) => {
  // 분기
  switch (action.type) {

    case 'INSERT':
      return todos.concat(action.todo);
    case 'REMOVE':
      return todos.filter(todo => todo.id !== action.id);
    case 'TOGGLE':
      return todos.map(todo => todo.id === action.id ? { ...todo, checked : !todo.checked } : todo);
    default:
      return todos;
  }
}

const App = () => {
  // useState에 데이터를 생성하는 함수 대입할 때
  // 함수 호출 구문을 대입하면 데이터가 만들어질 때마다 리랜더링
  // 함수 이름을 대입해야 함수를 전부 수행하고 1번만 리랜더링 수행
  // 따라서 `함수이름()` 가 아니라 `함수이름` 만 대입
  
  /* without useReducer
  const [todos, setTodos] = useState(createBulkTodos);
  */

  // 리듀서 설정 - 첫번째 매개변수는 호출될 함수
  // 두번째 매개변수는 초기값
  // 세번쨰 매개변수는 호출할 메서드로 리턴하는 값이 초기값으로 설정
  const [todos, dispatch] = useReducer(todoReducer, undefined, createBulkTodos);

  // 아이디를 위한 변수 생성
  const nextId = useRef(2001);

  // 삽입 처리하기 위한 함수
  // todos 값에 변화가 생길때만 함수 생성
  const onInsert = useCallback((text) => {
    const todo = {
      id: nextId.current,
      text,
      checked: false
    }

    /* without useReducer
    // 함수형 업데이트
    setTodos(todos => todos.concat(todo));
    */

    dispatch({type:'INSERT', todo});
    nextId.current++;

  }, []);

  // 데이터 삭제위한 함수
  const onRemove = useCallback(id => {
    /* without useReducer
    setTodos(todos => todos.filter(todo => todo.id !== id));
    */

    dispatch({type:'REMOVE', id});
  }, []);


  // 데이터 수정위한 함수
  const onToggle = useCallback((id) => {
    /* without useReducer
    setTodos(todos => todos.map(todo => todo.id === id ? { ...todo, checked: !todo.checked } : todo));
    */

    dispatch({type:'TOGGLE', id});
  }, [])


  return (
    <ToDoTemplate>
      <ToDoInsert onInsert={onInsert} />
      <ToDoList todos={todos} onRemove={onRemove} onToggle={onToggle} />
    </ToDoTemplate>
  );
}

export default App;
