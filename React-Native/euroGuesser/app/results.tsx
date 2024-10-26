import {useLocalSearchParams} from "expo-router";
import {SafeAreaView} from "react-native";
import {Text} from 'react-native'

export default function Results() {
    const {score} = useLocalSearchParams();
    return (
        <SafeAreaView style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
            <Text style={{fontFamily: 'Roboto', fontSize: 48}}>
                {`Final Score: ${score}`}
            </Text>
        </SafeAreaView>
    )

}