import React from "react";
import styles from './Button.scss'
import classNames from 'classnames/bind';

const cx = classNames.bind(styles);

// props 받아오는데, children으로 넘어온 것은 children으로 받고
// 나머지는 rest로 받는다.
const Button = ({children, ...rest}) => {
    return (
        <div className={cx('button')} {...rest}>
            {children}
        </div>
    )
}

export default Button;