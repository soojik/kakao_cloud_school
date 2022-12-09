import React, { useCallback, useRef, useState } from 'react';

import produce from 'immer';

const App = () => {
  // 변수(수정해도 리랜더링되지 않음) 만들때 사용
  const nextId = useRef(1);

  // state(수정하면 리랜더링)) 생성하고 setter 함수 생성
  const [form, setForm] = useState({ name: '', username: '' });

  const [data, setData] = useState({
    array: [],
    uselessValue: null
  });

  // input에 입력받는 경우, 입력하는 데이터가 변경될때 state를 수정해주는 함수
  // state 수정해주는 함수
  /*
  const onChange = useCallback((e) => {
    setForm({
      // 원본을 수정하는 것이 아니라 복제본을 수정해서 수정된 부분만 업데이트해주는 것이기 때문에
      ...form,

      // 여러 state에 값 설정
      [e.target.name]: [e.target.value]
    });
  }, [form]);
  */

  const onChange = useCallback((e) => {
    setForm(
      // data가 form의 복제본이 되고
      // draft를 수정하면 immer가 알아서 form에 데이터 다시 전송
      produce(draft => {
        draft[e.target.name] = e.target.value;
      })
    );
  }, []);

  // 입력받은 데이터 등록하는 함수
  // form 에서 submit 이벤트가 발생할 때 호출

  /*
  const onSubmit = useCallback((e) => {
    // 기본적으로 제공되는 이벤트 수행하지 않도록

    e.preventDefault();

    const info = {
      id: nextId.current,
      name: form.name,
      username: form.username
    }

    setData({
      // spread 연산자 사용해 복사 후,
      ...data,
      // 데이터 설정
      array: data.array.concat(info)
    })

    setForm({
      name: '',
      username: ''
    });

    nextId.current++;
  }, [data, form.name, form.username]);
  */

  const onSubmit = useCallback(e => {
    e.preventDefault();

    const info = {
      id: nextId.current,
      name: form.name,
      username: form.username
    }

    // data를 draft에 깊은 복사를 하고
    // draft에 작업을 수행한 후에 다시 원본 데이터로 업데이트
    setData(produce((draft) => { draft.array.push(info) }));

  })

  /*
  const onRemove = useCallback((id) => {
    setData({
      ...data,
      array: data.array.filter(info => info.id !== id)
    })
  }, [data]);
  */

  const onRemove = useCallback((id) => {
    setData(produce(draft => {
      draft.array.splice(draft.array.findIndex((info) => info.id === id), 1)
    }))
  }, []);

  return (
    <div>
      <div>
        <form onSubmit={onSubmit}>
          <input name='username' placeholder='아이디를 입력하세요' value={form.username} onChange={onChange}></input>
          <input name='name' placeholder='이름을 입력하세요' value={form.name} onChange={onChange}></input>
          <button type='submit'>등록</button>
        </form>
      </div>
      <div>
        <ul>
          {data.array.map(info => (
            <li key={info.id} onClick={() => onRemove(info.id)}>
              {info.username} ({info.name})
            </li>
          ))}
        </ul>

      </div>
    </div>
  );
}

export default App;