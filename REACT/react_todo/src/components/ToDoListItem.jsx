import React from 'react';

import './ToDoListItem.scss'

import {
    MdCheckBoxOutlineBlank,
    MdCheckBox,
    MdRemoveCircleOutline
} from 'react-icons/md';

import cn from 'classnames';
import { useCallback } from 'react';

const ToDoListItem = ({ todo, onRemove, onToggle, style }) => {

    // 넘어온 값 들 중, 할일 적은 text와 수행여부 확인하는 checked 변수 가져옴
    const { id, text, checked } = todo;

    const onDelete = useCallback((e) => {
        const result = window.confirm(text + "를 삭제할래?");

        if (result) {
            onRemove(id);
        }
    }, [onRemove, id, text]);

    return (
        <div className='ToDoListItem-virtualized' style={style}>
        <div className='ToDoListItem'>
            <div className={cn('checkbox', { checked })} onClick={(e) => onToggle(id)}>
                {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />}
                <div className='text'>{text}</div>
            </div>
            <div className='remove' onClick={onDelete}>
                <MdRemoveCircleOutline />
            </div>
        </div>
        </div>
    )
}

export default React.memo(ToDoListItem);