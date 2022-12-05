import React, { Component } from 'react';
import './ValidationSample.css';

class ValidationSample extends Component {
    // Ref(다른 DOM 객체를 참조할 수 있는 속성) 생성
    // input = React.createRef();

    // 클래스 안의 멤버변수나 함수 안의 지역변수와 유사
    // state는 변경이 되면 화면에 바로 적용
    state = {
        password: '',
        clicked: false,
        validated: false
    }

    // 버튼 눌렀을 때 처리
    handleButtonClick = e => {
        this.setState({
            clicked: true,
            validated: this.state.password === '1234'
        });

        // input이 참조하는, 가리키는 객체에 focus 설정
        // createRef로 만들어 focus를 구현한 경우
        // this.input.current.focus();

        // 그게 아닌 경우는
        this.input.focus();
    }

    // input의 입력 값 변경했을 때 처리
    // 자신의 name 과 동일한 state를 입력한 값으로 변경
    handleChange = e => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    // 클래스형 컴포넌트에서 화면에 출력할 내용을 리턴하는 함수
    render() {
        return (
            <div>
                <input type='password' value={this.state.password}
                    ref={ref => {this.input = ref}}
                    name='password' onChange={this.handleChange}
                    className={
                        this.state.clicked ? this.state.validated ? 'success' : 'failure' : ''
                    } />
                <button
                    onClick={this.handleButtonClick}
                >검증하기</button>
            </div>
        )
    }
}

export default ValidationSample;