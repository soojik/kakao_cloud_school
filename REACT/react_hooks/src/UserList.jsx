import React, { useEffect } from "react";

// 파라미터에 꼭 { }
const User = ({ user, onRemove, onToggle }) => {

    useEffect(() => {
        console.log('컴포넌트가 화면에 나타남');
        // 삽입, 수정 시 호출
        // 이 데이터의 id가 존재하면 수정, 존재하지 않으면 삽입
        console.log(user);
        // 함수 리턴하면 컴포넌트가 사라질 때 호출
        return () => {
            // 데이터 삭제한 경우 호출
            console.log('컴포넌트 사라짐');
            console.log(user);
        }
    }, [user]);

    return (
        <div>
            <b style={{
                cursor: 'pointer',
                color: user.active ? 'coral' : 'black'
            }} onClick={() => onToggle(user.id)}>{user.username}</b>
            <span>({user.email})</span>
            <button onClick={() => onRemove(user.id)}>삭제</button>
        </div>
    )
}

const UserList = ({ users, onRemove, onToggle }) => {
    return (
        <div>
            {users.map(user => (
                <User user={user} key={user.id} onRemove={onRemove}
                    onToggle={onToggle} />
            ))}
        </div>
    )
}

export default UserList;