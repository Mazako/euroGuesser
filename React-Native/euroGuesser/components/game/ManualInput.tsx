import React, {FC} from "react";
import {View} from "react-native";
import {TextInput} from "react-native-paper";

export interface ManualInputProps {
    value: string
    onValueChange: (v: string) => void
}

export const ManualInput: FC<ManualInputProps> = ({value, onValueChange}) => {
    return (
        <View style={{flexGrow: 1, flex: 1, justifyContent: 'center'}}>
            <TextInput label='Country'
                       onChangeText={onValueChange}
                       value={value}
            />
        </View>
    )
}