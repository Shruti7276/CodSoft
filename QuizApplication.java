import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame implements ActionListener {
    private String[][] questions = {
        {"What is the capital of France?", "Berlin", "Madrid", "Paris", "Rome", "3"},
        {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "2"},
        {"What is the largest ocean on Earth?", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean", "Pacific Ocean", "4"}
    };
    private int currentQuestion = 0;
    private int score = 0;
    private Timer timer;
    private int timeLimit = 30; 
    
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup optionsGroup;
    private JButton submitButton;
    private JLabel timerLabel;
    
    public QuizApplication() {
        setTitle("Quiz Application with Timer");
        setSize(400, 300);
        setLayout(new GridLayout(6, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        questionLabel = new JLabel();
        add(questionLabel);
        
        options = new JRadioButton[4];
        optionsGroup = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton();
            optionsGroup.add(options[i]);
            add(options[i]);
        }
        
        timerLabel = new JLabel("Time left: " + timeLimit + " seconds");
        add(timerLabel);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        add(submitButton);
        
        displayQuestion();
        
        setVisible(true);
        
        startTimer();
    }
    
    private void displayQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
                options[i].setSelected(false);
            }
        } else {
            endQuiz();
        }
    }
    
    private void startTimer() {
        timer = new Timer(1000, new ActionListener() {
            int timeLeft = timeLimit;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + " seconds");
                if (timeLeft <= 0) {
                    timer.stop();
                    checkAnswer();
                }
            }
        });
        timer.start();
    }
    
    private void checkAnswer() {
        timer.stop();
        for (int i = 0; i < options.length; i++) {
            if (options[i].isSelected() && String.valueOf(i + 1).equals(questions[currentQuestion][5])) {
                score++;
            }
        }
        currentQuestion++;
        if (currentQuestion < questions.length) {
            displayQuestion();
            startTimer();
        } else {
            endQuiz();
        }
    }
    
    private void endQuiz() {
        JOptionPane.showMessageDialog(this, "Quiz over! Your score: " + score + "/" + questions.length);
        System.exit(0);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        checkAnswer();
    }
    
    public static void main(String[] args) {
        new QuizApplication();
    }
}
