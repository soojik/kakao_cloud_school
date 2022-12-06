import React, { Component } from "react";

class ErrorBoundary extends Component {
    state = {
        error: false
    }

    // component에서 예외가 발생되면 호출되는 메서드
    componentDidCatch(error, info) {
        this.setState({
            error: true
        })

        console.log({ error, info });
    }

    render() {
        if (this.state.error) {
            return (<div>에러 발생</div>);
        } else {
            return this.props.children;
        }
    }
}

export default ErrorBoundary;