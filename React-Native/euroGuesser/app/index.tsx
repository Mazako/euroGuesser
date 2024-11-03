import {SafeAreaView, Text, View} from "react-native";
import {useColorScheme} from "@/hooks/useColorScheme";
import React, {useState} from "react";
import Select from "ajv-keywords/src/keywords/select";
import {PaperProvider, RadioButton, TextInput} from "react-native-paper";
import {CheckboxAndroid} from "react-native-paper/lib/typescript/components/Checkbox/CheckboxAndroid";
import {BasicButton} from "expo-dev-launcher/bundle/components/BasicButton";
import {Button, Snackbar} from 'react-native-paper';
import {useRouter} from "expo-router";

type InputLabelMessage = 'Number of countries' | 'Too little countries' | 'Too much countries' | 'Invalid input'
export type InputMode = 'select' | 'manual'

export default function Index() {
    const [mode, setMode] = React.useState<InputMode>('select');
    const [countriesSize, setCountriesSize] = useState('10')
    const [countriesInputLabel, setCountriesInputLabel] = useState<InputLabelMessage>('Number of countries')

    const router = useRouter()

    const handleCountiesChange = (v: string) => {
        setCountriesSize(v)
        if (!/^-?\d+$/.test(v)) {
            setCountriesInputLabel('Invalid input')
        } else if (Number.parseInt(v) < 0) {
            setCountriesInputLabel('Too little countries')
        } else if (Number.parseInt(v) > 49) {
            setCountriesInputLabel('Too much countries')
        } else {
            setCountriesInputLabel('Number of countries')
        }
    }

    return (
        <PaperProvider>
            <SafeAreaView style={{ flex: 1 }}>
                <Text style={{fontFamily: 'Roboto', fontSize: 16}}>Data entry mode</Text>

                <View style={{flexDirection: 'row', alignItems: 'center'}}>
                    <RadioButton
                        value="select"
                        status={mode === 'select' ? 'checked' : 'unchecked'}
                        onPress={() => setMode('select')}
                    />
                    <Text style={{fontFamily: 'Roboto', fontSize: 16, padding: 14}}>Select the correct one</Text>
                </View>
                <View style={{flexDirection: 'row', alignItems: 'center'}}>
                    <RadioButton
                        value="manual"
                        status={mode === 'manual' ? 'checked' : 'unchecked'}
                        onPress={() => setMode('manual')}
                    />
                    <Text style={{fontFamily: 'Roboto', fontSize: 16, padding: 14}}>Manual input</Text>
                </View>

                <Text style={{fontFamily: 'Roboto', fontSize: 16, paddingTop: 36}}>
                    Number of countries (max 49):
                </Text>

                <TextInput label={countriesInputLabel}
                           onChangeText={handleCountiesChange}
                           value={countriesSize}
                           error={countriesInputLabel !== 'Number of countries'}
                />

                <Button mode='contained' style={{marginTop: 'auto', marginBottom: 24}}
                        disabled={countriesInputLabel !== 'Number of countries'}
                        onPress={() => {router.push({pathname: '/game', params: {count: countriesSize, mode: mode}})}}>
                    Start
                </Button>
            </SafeAreaView>
        </PaperProvider>
    );
}
