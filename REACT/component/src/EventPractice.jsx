import React, { Component } from 'react';
import { useState } from 'react';

const EventPractice = () => {
    /*
    const [name, setName] = useState('');
    const [message, setMessage] = useState('');
    */

    const [form, setForm] = useState({
        name: '',
        message: '',
    });

    const { name, message } = form;

    const onChange = e => {
        // 클래스형 컴포넌트에서는 [e.target.name] : e.target.value

        // form을 복제해서 e.target.name에 해당하는 속성만
        // e.target.value로 수정
        // react에서는 state를 수정할 떄 복제해서 수정
        // 하나의 항목으로 만들어진 데이터는 바로 수정하면 되지만
        // 지금처럼 여러 항목으로 구성된 객체나 배열은 복제해서 수정
        const nextForm = {
            ...form,
            [e.target.name]: e.target.value
        };

        setForm(nextForm);
    }

    const onClick = e => {
        alert(name + ' : ' + message);
        setForm({
            name: '',
            message: ''
        })
    }

    const onKeyPress = e => {
        if (e.key === 'Enter') {
            onClick();
        }
    }

    return (
        <>
        <br />
        <br />
            <input type='text' name='name' value={name} placeholder='이름 입력'
                onChange={onChange}
                onKeyPress={onKeyPress} />

            <br />

            <input type='text' name='message' value={message} placeholder='메세지 입력'
                onChange={onChange}
                onKeyPress={onKeyPress} />

            <button onClick={onClick}>확인</button>
        </>
    )
}

/* 클래스형 컴포넌트
class EventPractice extends Component {

    state = {
        name: '',
        message: ''
    }

    // babel이 인스턴스의 메서드로 변환을 자동으로 수행
    // this.handleChane로 메서드 사용 가능
    handleChange = (e) => {
        // 이벤트가 발생한 객체는 e.target
        // e.target.name 은 이벤트 발생한 객체의 name
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleClick = (e) => {
        alert(this.state.name + ' : ' + this.state.message);
        this.setState({ name: '', message: '' });
    }

    handleKeyPress = (e) => {
        // e.key - 누른 키
        // 실제 눌린 키는 문자열 형태로 반환
        if (e.key === 'Enter') {
            this.handleClick();
        }
    }

    render() {
        return (
            <>
                <h1>이벤트 연습</h1>

                <input type='text' name='message' placeholder='메세지를 입력하세요' value={this.state.message}
                    onChange={this.handleChange}
                    onKeyPress={this.handleKeyPress}
                />

                <br />

                <input type='text' name='name' placeholder='이름을 입력하세요' value={this.state.name}
                    onChange={this.handleChange}
                    onKeyPress={this.handleKeyPress}
                />
                <button
                    onClick={this.handleClick}
                >입력</button>
            </>
        );
    }
}
*/

export default EventPractice;