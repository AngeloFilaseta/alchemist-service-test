package components

import adapters.Switch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.p
import react.useState

external interface TextSwitchProps : Props {
    var text: String
    var checked: Boolean
    var onChange: (Boolean, Any, Int) -> Unit
}

val TextSwitch = FC<TextSwitchProps> { props ->

    div {
        p {
            + props.text
        }
        Switch {
            checked = props.checked
            onChange = props.onChange
        }

    }

}