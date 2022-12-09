import colorContext from "./contexts/color";

const ColorBox = () => {
    return (
        <colorContext.Consumer>
        {value => (<div style={{
            width:'64px',
            height:'64px',
            background:value.color
        }} />)}
        </colorContext.Consumer>
    )
}

export default ColorBox;