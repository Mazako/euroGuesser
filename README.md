# Releasowanie Aplikacji
## Android
W katalogu ANDROID-KOTLIN wywołać komendę:
```bash
./gradlew app:assembleRelease
```

## React Native
Zainstalować eas:
```bash
npm i -g eas-cli
```
Trzeba zalogować się na EAS kontem expo:
```bash
eas login
```

W katalogu React-Native wywołać komendę: 
```bash
eas build -p android --profile preview
```

## Flutter
W katalogu Flutter wywołać komendę:
```bash
flutter build apk --release
```