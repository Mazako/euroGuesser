import {FC} from "react";
import values from "ajv/lib/vocabularies/jtd/values";
import {View} from "react-native";
import {Button, MD3Colors} from "react-native-paper";

export interface SelectInputProps {
    value: string,
    onValueChange: (v: string) => void
    options: string[]
}

export const SelectInput: FC<SelectInputProps> = ({value, onValueChange, options}) => {
    const renderButton = (id: number) => {
        return (
                <Button mode='contained'
                        style={{
                            borderRadius: 0,
                            width: '50%'
                        }}
                        contentStyle={{height: '100%'}}
                        buttonColor={value == options[id] ? '#625B71FF' : MD3Colors.primary40}
                        onPress={() => onValueChange(options[id])}>
                    {options[id]}
                </Button>
        )
    }

    return (
        <View style={{flexGrow: 1, flex: 1, justifyContent: 'center', alignItems: 'center', margin: 12}}>
            <View style={{gap: 8, flex: 1, flexDirection: 'row', justifyContent: 'space-between', marginBottom: 8}}>
                {renderButton(0)}
                {renderButton(1)}
            </View>
            <View style={{gap: 8, flex: 1, flexDirection: 'row', justifyContent: 'space-between'}}>
                {renderButton(2)}
                {renderButton(3)}
            </View>
        </View>
    )
}