import React, { Component } from "react";

class Iteration extends Component {

    // 내용이 변경되면 Component를 리랜더링하는 state 생성
    state = {
        names: ['Ning Ning', 'Taylor Swift', '허윤진', '마라샹궈', '타로밀크티'],
        name: ''
    }

    // input에 입력하면 name state 값을 변경하는 이벤트 처리 함수
    handleChange = (e) => {
        this.setState({
            name: e.target.value
        });
    }

    // name의 값을 names 에 추가하는 이벤트 처리 함수 - 버튼 누르면 동작하도록
    handleInsert = (e) => {
        this.setState({

            // push는 원본을 수정해야하기 때문에 여기서 사용할 수 없다.
            // 그래서 push 대신 배열을 복제해서 연결하는 concat 함수 사용
            names: this.state.names.concat(this.state.name),
            name: ''
        });
    }

    // 데이터 삭제 함수
    // index를 매개변수로 받아서 삭제
    handleRemove = index => {
        const { names } = this.state;
        // == const names = this.state.naems;

        // slice(매개변수 2개 받아서 배열 잘라내어 반환하는 함수)를 이용한 삭제
        /*
        this.setState({
            names: names.slice(0, index).concat(names.slice(index+1, names.length))
        });
        */

        this.setState({
            names: names.filter((item, idx) => idx !== index)
        })
    }

    render() {
        const nameList = this.state.names.map(
            (name, index) =>
                <li key={index} onDoubleClick={(e) => this.handleRemove(index)}>{name}
                    <button onClick={(e) => this.handleRemove(index)}>삭제</button>
                </li>);

        return (
            <>
                <ul>{nameList}</ul>
                {/* <p>{this.state.number.value}</p> */}
                이름 <input type="text" name="name" value={this.state.name} onChange={this.handleChange} />
                <button onClick={this.handleInsert}>삽입</button>
            </>
        );
    }
}

/*
const Iteration = () => {
    const names = ['Ning Ning', 'Taylor Swift', '허윤진', '마라샹궈', '타로밀크티'];

    const namesList = names.map(((name, index) => <li key={index}>{name}</li>));

    return (<ul>{namesList}</ul>)
}
*/

export default Iteration;