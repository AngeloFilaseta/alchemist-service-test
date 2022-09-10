@file:JsModule("react-switch")
@file:JsNonModule

package adapters

import react.ComponentClass
import react.Props

@JsName("default")
external val Switch: ComponentClass<SwitchProps>

external interface SwitchProps : Props {
    var checked: Boolean
    var onChange: (Boolean, Any, Int) -> Unit
}
