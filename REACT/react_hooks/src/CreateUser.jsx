import React from "react";

const CreateUser = ({ username, email, onChange, onCreate }) => {

    return (
        <>
            <input type="text" name="username" value={username} onChange={onChange} placeholder='이름' />
            <input type="text" name="email" value={email} onChange={onChange} placeholder='이메일' />
            <button onClick={onCreate}>추가</button>
        </>
    )
}

export default CreateUser;