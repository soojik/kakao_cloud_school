import React, { Component } from "react";

import PropTypes from 'prop-types';

// 클래스형 컴포넌트
class MyComponent extends Component {
    render() {
        // props 가져오기
        const { name, year, children } = this.props;
        return (
            <>
            <div>안녕하세요~ 제 이름은 {name}, children 값은 {children}, 제가 태어난 해는 {year}</div>
            </>
        );
    }
}

MyComponent.defaultProps = {
    name:'기본 이름',
    year:'0'
}

MyComponent.propTypes = {
    name:PropTypes.string,
    year:PropTypes.number.isRequired
}

// 함수형 컴포넌트
// const MyComponent = ({ name, children, year }) => {
//     return (<div>
//         안녕, 내 이름은 {name}
//         <br />
//         <p>{children}</p>
//         <br />
//         <p>지금은 {year}년</p>

//     </div>);
// }

// MyComponent.propTypess = {
//     name:PropTypes.string,
//     year:PropTypes.number.isRequired
// }

// MyComponent.defaultProps = {
//     name: "기본값"
// }

export default MyComponent;