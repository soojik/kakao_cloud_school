import React, { useState, useRef } from 'react';

const InputSample = () => {
    // 2개의 속성을 가진 state 생성
    const [inputs, setInputs] = useState({
        name: '',
        category: ''
    })

    // state 편리하게 사용하기 위해서 비구조화 할당
    // state의 값 input을 참조한다.
    const { name, category } = inputs;

    // react에서 다른 컴퍼넌트나 DOM을 참조할 수 있는 변수 ref 이용해 생성
    const nameInput = useRef();

    const onChange = (e) => {
        setInputs({
            // 기존의 input 객체를 복사한 뒤
            ...inputs,

            // name 키를 가진 값을 value로 설정
            [e.target.name]: e.target.value
        })
    }

    const onReset = (e) => {
        setInputs({
            name:'',
            category:''
        })

        // 이름 입력란으로 포커스 옮기기
        nameInput.current.focus();
    }

    return (
        <>
            이름 <input type="text" name='name' value={name} onChange={onChange} ref={nameInput} />
            범주 <input type="text" name='category' value={category} onChange={onChange} />
            <button onClick={onReset}>초기화</button>
            <div>
                <b>값 : </b>
                {name} ({category})
            </div>
        </>
    )
}

export default InputSample;