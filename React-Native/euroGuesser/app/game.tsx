import {SafeAreaView, Text, View} from "react-native";
import {useLocalSearchParams, useRouter} from "expo-router";
import {countries, Country, pickThreeRandomCountries} from "@/constants/Counties";
import React, {useEffect, useState} from "react";
import shuffleArray from "shuffle-array";
import {ManualInput} from "@/components/game/ManualInput";
import {Button} from "react-native-paper";
import {InputMode} from "@/app/index";
import {SelectInput} from "@/components/game/SelectInput";

export default function Game() {
    const {count, mode} = useLocalSearchParams();
    const router = useRouter()

    const [score, setScore] = useState(0)
    const [userInput, setUserInput] = useState('')
    const [selectOptions, setSelectOptions] = useState<string[]>([])
    const [madeGuess, setMadeGuess] = useState(false)
    const [gameOver, setGameOver] = useState(false)
    const [guessedCorrect, setGuessedCorrect] = useState(false)
    const [currentCountry, setCurrentCountry] = useState<Country>(countries[0])
    const [countriesToGuess] = useState<Country[]>(shuffleArray(countries, {copy: true}).slice(0, Number.parseInt(count as string)))

    useEffect(() => {
        handleNextClick()
    }, []);

    useEffect(() => {
        if (gameOver) {
            router.back()
            router.push({
                pathname: '/results',
                params: {score: score}
            })
        }
    }, [gameOver]);

    const handleButtonClick = () => {
        if (madeGuess) {
            handleNextClick()
        } else {
            handleGuessSubmit()
        }
    }

    const handleNextClick = () => {
        const country = countriesToGuess.pop()
        if (country) {
            setCurrentCountry(country)
            setUserInput('')
            setMadeGuess(false)
            setGuessedCorrect(false)
            if (mode as InputMode === 'select') {
                setSelectOptions(pickThreeRandomCountries(country))
            }
        } else {
            setGameOver(true)
        }
    }

    const handleGuessSubmit = () => {
        if (currentCountry.name.trim().toLowerCase() === userInput.trim().toLowerCase()) {
            setScore(prev => prev + 1)
            setGuessedCorrect(true)
        } else {
            setGuessedCorrect(false)
        }
        setMadeGuess(true)
    }

    const renderInputOrResponse = () => {
        if (madeGuess) {
            const message = guessedCorrect ? 'You guessed correct' : `Wrong. correct country is: ${currentCountry.name}`
            return (
                <View style={{flex: 1, justifyContent: 'center'}}>
                    <Text style={{fontFamily: 'Roboto', fontSize: 16}}>{message}</Text>
                </View>
            )
        } else {
            if (mode as InputMode === 'select') {
                return <SelectInput value={userInput} onValueChange={setUserInput} options={selectOptions}/>
            }
            return <ManualInput value={userInput} onValueChange={setUserInput}/>
        }
    }

    return (
        <SafeAreaView style={{flex: 1}}>
            <Text style={{fontFamily: 'Roboto', fontSize: 16}}>Score: {score}</Text>
            <View style={{height: '45%', display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
                <Text style={{fontFamily: 'Roboto', fontSize: 130}}>{currentCountry.flag}</Text>
            </View>
            {
                renderInputOrResponse()
            }
            <Button mode='contained'
                    style={{marginTop: 'auto', marginBottom: 24}}
                    onPress={handleButtonClick}>
                {madeGuess ? 'Next' : 'Check'}
            </Button>

        </SafeAreaView>
    )
}