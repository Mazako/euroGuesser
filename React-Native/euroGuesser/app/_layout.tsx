import {SplashScreen, Stack} from "expo-router";
import React, {useEffect} from "react";
import {Button, StyleProp, TextStyle} from "react-native";
import {DarkTheme, DefaultTheme, ThemeProvider} from "@react-navigation/native";
import {AppTheme} from "@/constants/AppTheme";
import {useFonts} from "expo-font";
import { Appearance } from 'react-native';

SplashScreen.preventAutoHideAsync();

export default function RootLayout() {
    const [loaded, error] = useFonts({
        Roboto: require('../assets/fonts/Roboto-Regular.ttf')
    })

    useEffect(() => {
        if (loaded || error) {
            SplashScreen.hideAsync();
        }
        Appearance.setColorScheme('light')
    }, [loaded, error]);

    if (!loaded && !error) {
        return null;
    }

    const headerTitleStyle: StyleProp<Pick<TextStyle, "fontFamily" | "fontSize" | "fontWeight">> = {
        fontSize: 24, fontWeight: 'normal', fontFamily: 'Roboto'
    };
    return (
        <ThemeProvider value={AppTheme}>
            <Stack>
                <Stack.Screen name="index"
                              options={{
                                  title: 'Main Menu',
                                  headerTitleStyle: headerTitleStyle,
                                  animation: 'fade'
                              }}/>

                <Stack.Screen name='game'
                    options={{
                        title: 'Game',
                        headerTitleStyle: headerTitleStyle,
                        animation: 'fade'

                    }}/>

                <Stack.Screen name='results'
                    options={{
                        title: 'Results',
                        headerTitleStyle: headerTitleStyle,
                        animation: 'fade'

                    }}/>
            </Stack>
        </ThemeProvider>

    );
}
