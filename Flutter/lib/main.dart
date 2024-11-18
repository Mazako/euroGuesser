import 'package:flutter/material.dart';
import 'dart:math';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        fontFamily: 'roboto',
      ),
      debugShowCheckedModeBanner: false,
      home: MainMenu(),
    );
  }
}

class MainMenu extends StatefulWidget {
  @override
  _MainMenuState createState() => _MainMenuState();
}

class _MainMenuState extends State<MainMenu> {
  int _dataEntryMode = 1; // 1: Manual input, 0: Select the correct one
  final TextEditingController _countryController = TextEditingController();
  String? _errorText;
  List<Map<String, String>> _countryList = [
    {"name": "Poland", "flag": "🇵🇱"},
    {"name": "Germany", "flag": "🇩🇪"},
    {"name": "France", "flag": "🇫🇷"},
    {"name": "Spain", "flag": "🇪🇸"},
    {"name": "Italy", "flag": "🇮🇹"},

  ];

  List<Map<String, String>> _selectedCountries = [];

  void _validateInput() {
    final int? numCountries = int.tryParse(_countryController.text);
    if (numCountries == null || numCountries < 1) {
      setState(() {
        _errorText = 'Invalid input';
      });
    } else {
      setState(() {
        _errorText = null;
      });
    }
  }


  void _startQuiz() {
    _validateInput();
    if (_errorText == null) {
      int numCountries = int.parse(_countryController.text);
      _selectedCountries = _getRandomCountries(numCountries);
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => QuizScreen(
            selectedCountries: _selectedCountries,
            dataEntryMode: _dataEntryMode,
            allCountries: _countryList,
          ),
        ),
      );
    }
  }

  List<Map<String, String>> _getRandomCountries(int count) {
    final random = Random();
    List<Map<String, String>> countries = List.from(_countryList);
    countries.shuffle(random);
    return countries.take(count).toList();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Main Menu"),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              "Data entry mode:",
              style: TextStyle(fontSize: 18),
            ),
            SizedBox(height: 10),
            Row(
              children: [
                Radio(
                  value: 0,
                  groupValue: _dataEntryMode,
                  onChanged: (int? value) {
                    setState(() {
                      _dataEntryMode = value!;
                    });
                  },
                ),
                Text("Select the correct one"),
              ],
            ),
            Row(
              children: [
                Radio(
                  value: 1,
                  groupValue: _dataEntryMode,
                  onChanged: (int? value) {
                    setState(() {
                      _dataEntryMode = value!;
                    });
                  },
                ),
                Text("Manual input"),
              ],
            ),
            SizedBox(height: 20),
            Text(
              "Number of countries:",
              style: TextStyle(fontSize: 18),
            ),
            SizedBox(height: 10),
            TextField(
              controller: _countryController,
              keyboardType: TextInputType.number,
              decoration: InputDecoration(
                border: OutlineInputBorder(),
                labelText: _errorText == null ? "Number of countries" : _errorText,
                //errorText: _errorText,
              ),
              onChanged: (text) {
                _validateInput();
              },
            ),
            Spacer(),
            Center(
              child: ElevatedButton(
                onPressed: _startQuiz,
                child: Text("Start", style: TextStyle(color: Colors.white),),
                style: ElevatedButton.styleFrom(
                  minimumSize: Size(1000, 50),
                  foregroundColor: Colors.white,
                  backgroundColor: Color(0xFF6854a4),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class QuizScreen extends StatefulWidget {
  final List<Map<String, String>> selectedCountries;
  final List<Map<String, String>> allCountries;
  final int dataEntryMode;

  TextEditingController _answerController = TextEditingController();
  QuizScreen({required this.selectedCountries, required this.dataEntryMode, required this.allCountries});


  @override
  _QuizScreenState createState() => _QuizScreenState();

}

class _QuizScreenState extends State<QuizScreen> {
  int _currentQuestion = 0;
  int _score = 0;
  bool? _isCorrect;
  bool _isAnswered = false;
  int answer = -1;
  List<String> _currentOptions = [];

  void _checkAnswer(String userAnswer) {
    if (_isAnswered) return;

    String correctAnswer = widget.selectedCountries[_currentQuestion]["name"]!;
    setState(() {
      _isCorrect = userAnswer == correctAnswer;
      _isAnswered = true;
      if (_isCorrect!) _score++;
    });
    answer = -1;
  }

  void _nextQuestion() {
    setState(() {
      _currentQuestion++;
      _isAnswered = false;
      _isCorrect = null;
    });

    if (_currentQuestion < widget.selectedCountries.length) {
      _currentOptions = _generateOptions(widget.selectedCountries[_currentQuestion]["name"]!);
    }

    if (_currentQuestion >= widget.selectedCountries.length) {
      Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => ResultScreen(score: _score, total: widget.selectedCountries.length),
        ),
      );
    }
  }

  List<String> _generateOptions(String correctAnswer) {
    final random = Random();
    Set<String> options = {correctAnswer};
    while (options.length < 4) {
      String option = widget.allCountries[random.nextInt(widget.allCountries.length)]["name"]!;
      options.add(option);
    }
    return options.toList()..shuffle();
  }


  @override
  void initState() {
    super.initState();
    _currentOptions = _generateOptions(widget.selectedCountries[_currentQuestion]["name"]!);
  }

  @override
  Widget build(BuildContext context) {
    if (_currentQuestion >= widget.selectedCountries.length) return SizedBox.shrink();

    Map<String, String> currentCountry = widget.selectedCountries[_currentQuestion];
    Color buttonColor = const Color(0xFF6854a4);
    Color buttonColorAns = const Color(0xFF685c74);

    return Scaffold(
      appBar: AppBar(title: Text("Quiz")),
      body: Container(
        //padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Text("Score: $_score", style: TextStyle(fontSize: 18)),
            SizedBox(height: 20),
            Text(
              currentCountry["flag"]!,
              style: TextStyle(fontSize: 130),
            ),
            SizedBox(height: 20),

            if(widget.dataEntryMode == 0)
              GridView.builder(
                shrinkWrap: true,
                gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                  crossAxisCount: 2,
                  mainAxisSpacing: 10,
                  crossAxisSpacing: 10,
                ),
                itemCount: _currentOptions.length,
                itemBuilder: (context, index) {
                  if (!_isAnswered) {
                    return ElevatedButton(
                      style: ElevatedButton.styleFrom(
                        backgroundColor: answer == index ? buttonColorAns : buttonColor,
                        foregroundColor: Colors.white,

                      ).copyWith(
                        shape: MaterialStateProperty.all(
                          RoundedRectangleBorder(
                            borderRadius: BorderRadius.zero, // No rounded corners
                          ),
                        ),
                      ),
                      onPressed: () {
                        setState(() {
                          answer = index;
                        });

                      },
                      child: Text(
                        _currentOptions[index],
                        style: TextStyle(fontSize: 18, color: Colors.white),
                      ),
                    );
                  }
                  return null;
                  },
                ),

            if(widget.dataEntryMode == 1)
              TextField(
                controller: widget._answerController,
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  //errorText: _errorText,
                ),
              ),


            SizedBox(height: 20),
            if (_isAnswered)
              Text(
                _isCorrect! ? "Correct answer!" : "Wrong answer!",
                style: TextStyle(
                  fontSize: 18,
                  color: _isCorrect! ? Colors.green : Colors.red,
                  fontWeight: FontWeight.bold,
                ),
              ),

          ],
        ),
      ),
      bottomNavigationBar: Padding(
        padding: const EdgeInsets.all(16.0),
          child: ElevatedButton(
            onPressed: _isAnswered ? () => _nextQuestion() : widget.dataEntryMode == 0 ? () => _checkAnswer(_currentOptions[answer]) : () => _checkAnswer(widget._answerController.text),
            child: Text(_isAnswered ? "Next" : "Check"),
            style: ElevatedButton.styleFrom(
            foregroundColor: Colors.white,
            backgroundColor:  Color(0xFF6854a4),
            padding: EdgeInsets.symmetric(vertical: 16.0),
            minimumSize: Size(double.infinity, 50),

          ),
        ),
      ),
    );
  }
}

class ResultScreen extends StatelessWidget {
  final int score;
  final int total;

  ResultScreen({required this.score, required this.total});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Result")),
      body: Center(
        child: Text(
          "Your Score: $score / $total",
          style: TextStyle(fontSize: 24),
        ),
      ),
    );
  }
}
