
package minipro;

/*Name Ibtasum Iktadar
This a a Impossible Quiz Project
It asks user different question with multiple choice answers
one answer is correct and and user recieve a score depending on a random dice
one is wrong, user get no points and the users previous score remains the same
one is impossible, user loses all the previous points and score is reset to zero
*/
import java.util.Scanner;
import java.util.Random;
import java.io.*;


/*Methods:
    main method where the whole program is being run
    addques = this methods stored the question into a record array
    outputfile= this method takes the file input, the user can input a new question and add to the quiz
    inputfile= this method reads the user input from a txt file and stores into the QuestionBank ADT
    createqbank= this method stores the question into the QuestionBank
    AskQuestion= this method asks the question and sets the score
    printscore= this method prints out the final score
*/
public class Minipro {
    public static void main(String[] args)throws IOException {
        
        Quiz[] bank = new Quiz[5];//Array of Record to store the Question
        bank[0] = addques("Which of the following elements is a metal: Copper, Thrash or Carbon?","Copper","Carbon","Thrash");
        bank[1] = addques("What is two plus two: Burger, Four, Twentyone","Four","Burger","Twentyone");
        bank[2] = addques("Which country are we currenty in: UK, London, USA","UK","London","USA");
        bank[3] = addques("What is a Greek letter: pi, pie, pipe","pi","pipe","pie");
        bank[4] = addques("Who killed Frieza in DBZ: Goku, Trunks, Saitama","Trunks","Saitama","Goku");
        QuestionBank[] qbank = new QuestionBank[bank.length+1];
        outputfile();
        Quiz[] newques = inputfile(bank, qbank);
        createqbank(qbank, newques);
        String answer="";
        int score = 0;
        //find out the score accumalated
        score = AskQuestion(newques, score, answer);
        
        printscore(score);
        
       
    }
    
    public static int AskQuestion(Quiz[] bank, int score, String ans)
    {   
        //creating a array record of table to store user input and data to be sorted out
        table[] t = setarray(bank.length);
        
        for(int i = 0; i<bank.length; i++)
        {   
            table s = new table();
            
            s.question = getQuestion(bank[i]);//call input() and store the returning value in variable answer
            s.answer = input(s.question);
       
        boolean answer1 = false;//boolean variable to go into a while loop
          
         
            while(answer1 != true)
            {
                if (s.answer.equalsIgnoreCase(correctans(bank[i])) || (s.answer.equalsIgnoreCase(impossibleans(bank[i])) || (s.answer.equalsIgnoreCase(wrongans(bank[i])))))
                {
                    //taking the user input and going into a printquiz method to add up the score
                    score = printquiz(bank,score,s, i);
                    
                    
                    answer1 = true;
                }
                else
                {
                    print("Not a Valid Answer, please try again\n");
                    s.answer = input(getQuestion(bank[i]));
                }
            }
        t[i] = s;//recording all the data and storing into an array to sort
        }
        printtable(t);//sorting out the data and printing it out
        return score;
    }
    
    public static String getQuestion(Quiz q)//get question from Quiz record
    {
        return q.question;
    }
    
     public static String correctans(Quiz q)//get correct answer from Quiz record
    {
        return q.correctans;
    }
    
     public static String wrongans(Quiz q)//get wrong answer from Quiz record 
    {
        return q.wrongans;
    }
     
      public static String impossibleans(Quiz q)//get impossible answer from Quiz record
    {
        return q.impossibleans;
    }
    
    //this method assign the user input with a score
    public static int printquiz(Quiz[] bank, int score, table s, int i)
    {   int rolled = roll(6)+1;//random dice for the score
        if (s.answer.equalsIgnoreCase(correctans(bank[i])))
        {
            s.score = rolled;
            score+= rolled;//score is the random dice integer
            print("Correct Answer, you rolled a "+rolled+". Your score is "+score+"\n");
        }
        else if (s.answer.equalsIgnoreCase(wrongans(bank[i])))
        {
            print("Wrong Answer, you got no points. Your score is still "+score+"\n");
        }
        else if (s.answer.equalsIgnoreCase(impossibleans(bank[i])))
        {
            s.score = 0;
            score = 0;//score set to zero if impossible answer is the input
            print("Impossible Answer, your score has been reset to zero. Your score is "+score+"\n");
        }
        return score;
    }
    
    public static void printscore(int score)//printing the final score
    {
        print("\nThe total score is "+score);
    }
    
    
    
    public static void print(String message)//print method
    {
        System.out.println(message);
    }
    
    public static String input(String Message)//input method for the user to input, uses Scanner from the library
    {
        Scanner scanner = new Scanner (System.in);
        print(Message);
        return scanner.nextLine();
    }
    
    public static table[] sort(table[] a)//Sort Algorithm, uses Bubblesort 
	{
		for (int i=0;i<(a.length-1);i++)
		{
			for (int j=0;j<(a.length-1-i);j++)
			{
				if (a[j].score > a[j+1].score)
				{
					table m = a[j];
					table n = a[j+1];
					a[j+1] = m;
					a[j] = n;
				}
			}
		}
		return a;
	}
    
    public static table[] setarray(int size)//creating table record array
	{
		table[] st = new table[size];
		return st;
	}
    
    public static String getSTQ(table t)//get the question from the table array
	{
		return t.question;
	}
    
    
	public static String getSTA(table t)//get the answer from the table array
	{
		return t.answer;
	}

	public static int getSTS(table t)//get the score from the table array
	{
		return t.score;
	}
        
    public static void printtable(table[] s )//printing out the table
    {
        System.out.println("=======================OVERALL=======================\n");
        s = sort(s);//sorting algorithm(Bubble Sort)
        for (int i=(s.length-1);i>=0;i--)
        {
            System.out.println("Question: \n"+getSTQ(s[i])+"\nYour Answer: "+getSTA(s[i])+"\nScore gained: "+getSTS(s[i])+"\n------------------------------------------------------------------------\n");
        }
	System.out.println("====================================================\n");
    }
    
    public static int roll (int n) //roll a dice of a given number and return the number rolled
	{
		Random dice = new Random(); //Declare Random as dice
		int num = dice.nextInt(n); //roll the dice and store the number rolled in variable num
		return num; 
	}
    public static void createqbank(QuestionBank[] qb, Quiz [] q)//creating a Question Bank
    {
        QuestionBank bank = new QuestionBank();
        bank.bank = q;
        
    }
    
    public static Quiz addques(String que, String cor, String imp, String wro)//ADT to hold the question and answers
    {
        Quiz quiz = new Quiz();
        quiz.question = que;
        quiz.correctans = cor;
        quiz.impossibleans =imp;
        quiz.wrongans = wro;
        return quiz;
    }
    
    //file io: User input new question and answer to be stored into a text file. Text file could be changed similarly
    public static void outputfile()throws IOException
    {
        File file = new File("mini.txt");
        PrintWriter output = new PrintWriter(file);
        output.println("Which one is a colour: Apple, Orange, Tomatoe");
        output.println("Apple");
        output.println("Tomatoe");
        output.println("Orange");
        output.close();
    }
    
    
    //file io: reads the text file and stores the new question into the QuestionBank ADT
    public static Quiz[] inputfile(Quiz[] q, QuestionBank[] bank)throws IOException
    {
        
        
        BufferedReader inputstream = new BufferedReader (new FileReader ("mini.txt"));
        
        
            String question = inputstream.readLine();
            String wans = inputstream.readLine ();
            String ians = inputstream.readLine();
            String cans = inputstream.readLine();
            Quiz[] newquestion= new Quiz[6];
            
            Quiz quiz = new Quiz();
            quiz.question=question;
            quiz.correctans=cans;
            quiz.impossibleans=ians;
            quiz.wrongans=wans;
            
            for(int i=0; i<q.length;i++)
            {
                newquestion[i]=q[i];
                
            }
            newquestion[5] = addques(quiz.question,quiz.correctans,quiz.impossibleans,quiz.wrongans);
            return newquestion;
        
    }

    
}

/*Create a record called Quiz, including: 
	a question, Declared as String
	a correct answer, Declared as String
	a wrong answer, Declared as String
        a impossible answer, Declared as String
*/
class Quiz
{
    String question;
    String  correctans;
    String  wrongans;
    String impossibleans;
   
}

/*Create an abstract data type Question Bank, including:
	a set of questions, Declared as Quiz[]
*/
class QuestionBank 
{
    Quiz[] bank;
}

/*Created a table record that holds the user input in an array
 and also holds the score for each question
*/
class table
{
    String question;
    String answer;
    int score;
}



