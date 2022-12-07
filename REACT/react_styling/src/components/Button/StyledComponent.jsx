import React from "react";
import styled, { css } from 'styled-components';

const Box = styled.div`
    background:${props => props.color || 'darkcyan'};
    padding:1rem;
    display:flex;
`;

const Button = styled.button`
    background-color: antiquewhite;
    color:crimson;
    border-radius:4px;
    padding:0.5rem;
    display:flex;
    align-items:center;
    justify-content:center;
    box-sizing:border-box;
    font-size:1rem;
    font-weight:500;

    &:hover{
        background-color:beige;
    }

    & + button {
        margin-left:1rem;
    }
`;

const StyledComponent = () => {
    <Box color='crimson'>
        <Button>안녕</Button>
        <Button>잘가</Button>
    </Box>
}

export default StyledComponent;