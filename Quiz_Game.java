import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctIndex;

    public QuizQuestion(String question, List<String> options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    boolean isCorrect(int ansIndex) {
        return ansIndex == correctIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;
    private int currentIndex;
    private Timer timer;
    private List<Boolean> answers;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.currentIndex = 0;
        this.answers = new ArrayList<>();
    }

    public void start() {
        System.out.println("Welcome to CodSoft Quiz");
        System.out.println("Starting Quiz...");
        
        Scanner sc = new Scanner(System.in);
        while (currentIndex < questions.size()) {
            displayQ(sc);
        }
        sc.close();
        
        System.out.println("Quiz is Over");
        score = scoreCalculation();
        System.out.println("Your Score: " + score);
        
        if(score ==  questions.size()){
            System.out.println("You are a Genius!!");
        }
        else if(score >= (questions.size())/2){
            System.out.println("You are Good, but there is room for improvement");
        }
        else{
            System.out.println("You need to improve. Better luck next time");
        }

    }

    public void displayQ(Scanner sc) {
        QuizQuestion q = questions.get(currentIndex);
        System.out.println("Question " + (currentIndex + 1) + ": " + q.getQuestion());

        List<String> options = q.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                answers.add(false);
                nextQ();
            }
        }, 15000);

        System.out.print("Enter your answer (1-" + options.size() + "): ");
        int ansIndex = sc.nextInt() - 1;
        timer.cancel();

        answers.add(q.isCorrect(ansIndex));
        if (q.isCorrect(ansIndex)) {
            System.out.println("Correct");
        } else {
            System.out.println("Incorrect");
        }
        nextQ();
    }

    public void nextQ() {
        currentIndex++;
    }

    public int scoreCalculation() {
        int score = 0;
        for (boolean answer : answers) {
            if (answer) {
                score++;
            }
        }
        return score;
    }
}

public class Quiz_Game {
    public static void main(String[] args) {
        List<QuizQuestion> questions = new ArrayList<>();
        questions.add(new QuizQuestion("What is the default value of a boolean variable in Java?", Arrays.asList("true", "false", "null", "1"), 1));
        questions.add(new QuizQuestion("Which of the following is not a Java keyword?", Arrays.asList("final", "static", "string", "volatile"), 2));
        questions.add(new QuizQuestion("In which memory area are static variables stored in Java?", Arrays.asList("Stack", "Heap", "Class area", "Register"), 2));
        questions.add(new QuizQuestion("Which method is called when an object is created in Java?", Arrays.asList("class()", "new()", "constructor()", "None of the above"), 2));
        questions.add(new QuizQuestion("What does the hashCode() method in Java return?", Arrays.asList("Unique identifier of an object", "Memory address of an object", "An integer representation of the object", "All of the above"), 2));
        questions.add(new QuizQuestion("Which exception is thrown when a divide-by-zero error occurs in Java?", Arrays.asList("ArithmeticException", "NullPointerException", "NumberFormatException", "ClassCastException"), 0));
        questions.add(new QuizQuestion("Which operator is used to concatenate two strings in Java?", Arrays.asList("+", "*", "&", "||"), 0));
        questions.add(new QuizQuestion("What is the default size of an ArrayList when it is created without specifying a size?", Arrays.asList("5", "10", "15", "0"), 1));
        questions.add(new QuizQuestion("Which of the following is not a feature of Java?", Arrays.asList("Platform-independent", "Object-oriented", "Secure", "Pointer-based"), 3));
        questions.add(new QuizQuestion("What is the size of an int variable in Java?", Arrays.asList("8 bits", "16 bits", "32 bits", "64 bits"), 2));

        Quiz quiz = new Quiz(questions);
        quiz.start();
    }
}
