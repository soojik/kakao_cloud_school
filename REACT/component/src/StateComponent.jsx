import React, { Component } from 'react';
import { useState } from 'react';

/* 클래스형 컴포넌트
class StateComponent extends Component {
    state = {number : 0};

    // constructor(props) {
    //     // 상위 클래스의 생성자 호출
    //     super(props);

    //     //  state 생성
    //     this.state = { number: 0 };
    // }

    render() {
        return (
            <>
                <p>숫자 : {this.state.number}</p>
                <button onClick={(e) => {
                    this.setState(
                            {number : this.state.number + 1},
                            () => {
                                console.log('state 값이 변경됨');
                                console.log(this.state);
                            }
                    )
                }}>증가</button>
            </>
        );
    }
}
*/

const StateComponent = () => {

    // 함수형 컴포넌트에서 state 를 생성하는 방법
    const [message, setMessage] = useState('');
    const [color, setColor] = useState('black');

    const onClickEnter = (e) => {
        setMessage('안녕');
        setColor('forestgreen');
    }

    const onClickLeave = (e) => {
        setMessage('잘가');
        setColor('coral');
    }


    return (
        <>
            <button onClick={onClickEnter}>입장</button>
            <button onClick={onClickLeave}>퇴장</button>
            <h1 style={{ color }}>{message}</h1>
            <button onClick={() => {
                setColor('red')
            }}>빨강</button>
            <button onClick={() => {
                setColor('darkorchid');
            }}>보라</button>
        </>
    )
};

export default StateComponent;  