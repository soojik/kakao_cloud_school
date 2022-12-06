import React, { Component, useState, useEffect, useRef, useMemo } from "react";
import Iteration from "./Iteration";
import ErrorBoundary from "./ErrorBoundary";
import InputSample from "./InputSample";
import UserList from "./UserList";
import CreateUser from "./CreateUser";

class ClassState extends Component {

  constructor(props) {
    super(props);

    this.state = {
      count: 0
    }
  }

  render() {
    return (
      <>
        <p>클릭 {this.state.count}번 수행</p>

        <button onClick={(e) => this.setState({
          count: this.state.count + 1
        })}>
          !! 클릭 !!</button>

      </>
    );
  }
}


const FunctionState = () => {
  const [count, setCount] = useState(0);

  return (
    <>
      <p>클릭 {count}번 수행</p>

      <button onClick={(e) =>
        setCount(count + 1)
      }>
        !! 클릭 !!</button>
    </>
  )
}

class ClassEffect extends Component {
  // 생성자
  constructor(props) {
    super(props);
    console.log("생성자 - 가장 먼저 호출되는 메서드");

    this.state = {
      count: 0
    }
  }

  componentDidMount() {
    console.log("마운트 된 후 호출되는 메서드");
    document.title = `You clicked ${this.state.count} times`;
  }

  componentDidUpdate() {
    console.log('업데이트 된 후 호출되는 메서드');
    document.title = `You clicked ${this.state.count} times`;
  }

  render() {
    return (
      <>
        <button onClick={(e) => this.setState(
          { count: this.state.count + 1 }
        )}>!! 클릭 !!</button>

      </>);
  }
}

const FunctionEffect = () => {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log('마운트와 업데이트가 끝나면 호출');
    document.title = `You clicked ${count} times`;
  }, [count])

  return (
    <>
      <p>You clicked {count} times</p>
      <button onClick={(e) => {
        setCount(count + 1);
      }}>!! 클릭 !!</button>
    </>
  );

}

// active가 true인 데이터 개수
const countActiveUser = users => {
  console.log("사용자 수 세기");
  return users.filter(user => user.active).length;
}


const App = () => {

  // 그냥 배열의 데이터를 수정하면 화면 랜더링이 안되기 때문에
  const [users, setUsers] = useState([
    { id: 1, username: 'Ning Ning', email: 'ning02@smtown.com', active: false },
    { id: 2, username: 'Taylor Swift', email: 'tswift13@midnight.com', active: true }
  ]);

  const [inputs, setInputs] = useState({
    username: '',
    email: ''
  });

  const { username, email } = inputs;

  const onChange = (e) => {
    setInputs({
      // 기존의 input 객체를 복사한 뒤
      ...inputs,

      [e.target.name]: e.target.value
    });
  }

  // ref(일반) 변수 생성
  const nextId = useRef(3);

  // 데이터 삽입 메서드
  const onCreate = (e) => {

    // 하나의 객체 생성
    const user = {
      id: nextId.current,
      username,
      email
    }

    // users에 user를 추가
    setUsers([...users, user]);

    setInputs({
      username: '',
      email: ''
    });

    nextId.current++;
  }

  const onRemove = id => {
    // users state에서 id가 id인 데이터 삭제
    // id가 일치하지 않는 데이터만 삭제
    // 실제로는 id가 일치하지 않는 데이터만 가지고 배열 만들어서 수정
    setUsers(users.filter(user => user.id !== id));
  }

  // 수정 메서드
  // id에 해당하는 데이터의 active 속성을 반전
  const onToggle = id => {
    setUsers(users.map(user => user.id === id ?
      { ...user, active: !user.active } : user))
  }

  // 활성화된 user 개수를 세는 함수 호출
  // users에 변화가 생긴 경우만 함수를 호출하고 그 이외의 경우는 결과를 복사하도록 수정
  const count_active_user = useMemo(() => countActiveUser(users), [users]);

  return (<>
    <CreateUser username={username} email={email} onChange={onChange} onCreate={onCreate}></CreateUser>
    <br /><br />
    <UserList users={users} onRemove={onRemove} onToggle={onToggle} />
    <p>현재 활성화된 유저 수 : {count_active_user}</p>
  </>)
}

export default App;